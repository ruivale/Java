/**
 * <p>
 * Classname:  jdk1_6examples.deleteonexithookcleaner.DeleteOnExitHookCleaner
 * </p>
 *
 * <p>Copyright: Copyright (c) 2008 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua EngÂº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */
package jdk1_6examples.deleteonexithookcleaner;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedHashSet;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * Since Java 6, File#deleteOnExit() delegates to a Java class named java.io.DeleteOnExitHook (was native before).
 * This enables us to cleanup the structure (e.g. jtds creating thousands of inexistant temp files...) it causes an OOM.
 * WARNING: this class should be started only once per JVM. Putting this in multiple webapps is sub-obtimal.
 * WARNING: this may leave some files undeleted at shutdown, since we are removing possibly not-yet-existant files!
 *          (use case: new File().deleteOnExit().wait(a_few_seconds).create())
 *
 * IMPLEMENTATION NOTES:
 *   * this class manages its own Thread, since a Timer can not vary its scheduling period.
 *   * since LinkedHashSet keeps its order, we can iterate (and thus, remove) on "old" entries only,
 *     i.e. the ones that were already present the last time we went active.
 *     Thus, we won't remove recently-added files (use case: new File().deleteOnExit().create()).
 *
 * @see {@link java.io.DeleteOnExitHook}
 * @author Cédrik LIME
 */
public class DeleteOnExitHookCleaner {

  private static final String THREAD_NAME = "DeleteOnExitHook Cleaner";//$NON-NLS-1$
  private static final long MIN_SLEEP_TIME = 1000; // 1 second, in milliseconds
  private static final long MAX_SLEEP_TIME = 10 * 60 * 1000; // 10 minutes, in milliseconds
  private static final Logger LOGGER = Logger.getLogger(
      DeleteOnExitHookCleaner.class.getName());
  private static DeleteOnExitHookCleanerThread INSTANCE = null;


  private static final class DeleteOnExitHookCleanerThread extends Thread {

    protected volatile boolean doRun = true;
    private long sleepTime = 60 * 1000; // initial: 1 minute, in milliseconds
    private LinkedHashSet<String> files = null;
    private int filesLastSize = 0; // contains files.size() from last iteration

    public DeleteOnExitHookCleanerThread(LinkedHashSet<String> files) {
      super(THREAD_NAME);
      setFiles(files);
    }

    public DeleteOnExitHookCleanerThread(LinkedHashSet<String> files,
                                         ThreadGroup group) {
      super(group, THREAD_NAME);
      setFiles(files);
    }

    private void setFiles(LinkedHashSet<String> files) {
      assert files != null;
      this.files = files;
      synchronized (files) {
        filesLastSize = files.size();
      }
    }

    /** {@inheritDoc} */
    @Override
    public void run() {
      // let's give time to other threads to finish initializing
      try {
        Thread.sleep(sleepTime / 2);
      } catch (InterruptedException ie) {
      }
      while (doRun) {
        synchronized (files) {
          LOGGER.warning("DeleteOnExitHook went from " + Integer.valueOf(
              filesLastSize) +
                         " to " + Integer.valueOf(files.size()) +
                         " file entries.");
          int kepted = 0, removed = 0;
          int total = removed + kepted;
          for (Iterator<String> iterator = files.iterator();
               iterator.hasNext() && total < filesLastSize;) {
            String fileName = iterator.next();
            if (fileName == null || !new File(fileName).exists()) {
              // no file by given filename exists: remove (old), useless entry
              LOGGER.severe("removing file entry " + fileName + ".");
              iterator.remove();
              ++removed;
            } else {
              ++kepted;
            }
            ++total;
          }
          if (removed > 0) {
            LOGGER.warning("removed " + Integer.valueOf(removed) +
                           "/" + Integer.valueOf(total) + " entries");
          }
          float keptedPercent = (total == 0) ? 1.0f : (float) kepted / (total);
          assert 0f <= keptedPercent && keptedPercent <= 1.0f : keptedPercent;
          float sizeGrowth = (filesLastSize == 0 || files.size() + removed == 0)
                             ? 1.0f : (files.size() + removed) / filesLastSize;
          assert sizeGrowth >= 1.0f : sizeGrowth;
          // sets a short sleep time for lots of removed entries, and a long one for no removed entries
          // sets a short sleep time for a big growth, and a long one for no growth
          sleepTime *= (0.3 + keptedPercent) * (1 / sizeGrowth + 0.1);
          if (sleepTime < MIN_SLEEP_TIME) {
            sleepTime = MIN_SLEEP_TIME;
          } else {
            if (sleepTime > MAX_SLEEP_TIME) {
              sleepTime = MAX_SLEEP_TIME;
            }
          }
          filesLastSize = files.size();
        }
        try {
          LOGGER.warning("sleeping for " + Long.valueOf(sleepTime / 1000) +
                         " seconds");
          Thread.sleep(sleepTime);
        } catch (InterruptedException ie) {
          LOGGER.log(Level.SEVERE, "interrupted!", ie);
          // let's run one more time, without waiting!
        }
      }
      LOGGER.info("Thread '{}' shutdown successfully");
    }
  }

  private DeleteOnExitHookCleaner() {
    super();
  }

  /**
   * @return a dummy instance, so that it can be managed (init- and destroy-method) by Spring...
   */
  public static DeleteOnExitHookCleaner getInstance() {
    return new DeleteOnExitHookCleaner();
  }

  @PostConstruct
  @SuppressWarnings("unchecked")
  public void startCleaning() {
    synchronized (DeleteOnExitHookCleaner.class) {
      if (INSTANCE != null) {
        LOGGER.warning("DeleteOnExitHookCleaner is already running!");
        return;
      }
      try {
        Class deleteOnExitHookClass = Class.forName("java.io.DeleteOnExitHook");//$NON-NLS-1$
        Field filesField = deleteOnExitHookClass.getDeclaredField("files");//$NON-NLS-1$
        filesField.setAccessible(true);
        LinkedHashSet<String> files = (LinkedHashSet<String>) filesField.get(
            null);
        assert files != null :
            "DeleteOnExitHook not initialized (null 'files' field)!";
        INSTANCE = new DeleteOnExitHookCleanerThread(files);
        INSTANCE.setName(THREAD_NAME);
        INSTANCE.setDaemon(true);
        INSTANCE.start();
        LOGGER.info("Thread '" + INSTANCE.getName() + "' started successfully");
      } catch (Exception e) {
        // We are running Java 5-, or other error. Don't start, since it is useless.
        LOGGER.log(
            Level.SEVERE,
            "Can't initialize. Are you running Java 6+, or within a restricted SecurityManager?",
            e);
      }
    }
  }

  @PreDestroy
  public void stopCleaning() {
    synchronized (DeleteOnExitHookCleaner.class) {
      if (INSTANCE != null) {
        INSTANCE.doRun = false;
        LOGGER.info("Shutting down thread '" + INSTANCE.getName() + "'");
        INSTANCE.interrupt();
      }
      INSTANCE = null;
    }
  }
}
