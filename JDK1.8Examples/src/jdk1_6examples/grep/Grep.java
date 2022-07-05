package jdk1_6examples.grep;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

//import com.subhajit.argutils.ArgumentUtils;
//import com.sun.idm.svc.util.common.CommonUtils;
//import com.sun.idm.svc.util.streams.FileUtils;

public class Grep {
//
//  private static class FileContent {
//
//    private final File file;
//    private final String content;
//
//    public FileContent(File file, String content) {
//      super();
//      this.file = file;
//      this.content = content;
//    }
//
//    public File getFile() {
//      return file;
//    }
//
//    public String getContent() {
//      return content;
//    }
//  }
//
//  private static enum Argument {
//
//    text, dir, pattern
//  }
//
//  private static Map<Argument, ?> parseCommandLineArguments(String[] args)
//      throws IOException {
//    final String argumentSpec = "d:string:true:Base directory under which to search|" + "f:string:true:Comma separated file name patterns to search (eg. .java,.properties,.xml)|" + "i:boolean:false:Ignore case|" + "names:boolean:false:Show file names only if \"true\", else show verbose output";
//    if (args.length == 0) {
//      System.out.println("Usage:\nOption\t\tType\tRequired\tDescription\n" + ArgumentUtils.getUsage(argumentSpec));
//      System.exit(1);
//    }
//    Map<String, String> map = ArgumentUtils.parseArgs(args, argumentSpec);
//    if (!map.containsKey(CommonUtils.UNBOUND_ARGUMENT)) {
//      throw new IllegalArgumentException(
//          "Cannot continue, since search terms have not been specified.");
//    }
//    final String text = map.get(CommonUtils.UNBOUND_ARGUMENT);
//    final File dir = new File(map.get("d")).getCanonicalFile();
//    final String pattern = map.get("f");
//
//    Map<Argument, Object> ret = new HashMap<Argument, Object>();
//    ret.put(Argument.dir, dir);
//    ret.put(Argument.pattern, pattern);
//    ret.put(Argument.text, text);
//    return ret;
//  }
//
//  public static void main(String[] args) {
//    int status = 0;
//    long t0 = System.nanoTime();
//    try {
//      // Parse the command line arguments.
//      Map<Argument, ?> commandLineArguments = parseCommandLineArguments(args);
//      final String text = (String) commandLineArguments.get(Argument.text);
//      final File dir = (File) commandLineArguments.get(Argument.dir);
//      final String fileNameInput = (String) commandLineArguments.get(Argument.pattern);
//
//      // Setup the queue of files. This queue is populated by the
//      // directory scanner with all files and directories found under the
//      // base directory we are scanning.
//      BlockingQueue<File> fileQueue = new ArrayBlockingQueue<File>(5000);
//      final DirectoryScannerProducer scanner = new DirectoryScannerProducer(
//          fileQueue, 5, 20);
//
//      // Setup the content queue. This queue contains FileContent objects
//      // embodying the content of files read from the fileQueue that match
//      // the file name pattern we are interested in. This queue is
//      // populated by the fileReaders object.
//      final BlockingQueue<FileContent> contentQueue = new LinkedBlockingQueue<FileContent>();
//      final QueueProcessor<File, FileContent> fileReaders = new QueueProcessor<File, FileContent>(
//          fileQueue, contentQueue, 5,
//          new IProcessor<File, FileContent>() {
//
//            public FileContent process(File input)
//                throws InterruptedException,
//                       InvocationTargetException {
//              try {
//                if (input.isDirectory()) {
//                  return null;
//                }
//                if (!input.getName().endsWith(fileNameInput)) {
//                  return null;
//                }
//                return new FileContent(input, new String(FileUtils.loadFile(input)));
//              } catch (IOException exc) {
//                throw new InvocationTargetException(exc);
//              }
//            }
//          });
//
//      // Setup the output queue containing FileContent objects
//      // representing matches. The output queue is populated by the file
//      // finders object.
//      final BlockingQueue<FileContent> outputQueue = new LinkedBlockingQueue<FileContent>();
//      final QueueProcessor<FileContent, FileContent> fileFinders = new QueueProcessor<FileContent, FileContent>(
//          contentQueue, outputQueue, 5,
//          new IProcessor<FileContent, FileContent>() {
//
//            public FileContent process(FileContent input)
//                throws InterruptedException,
//                       InvocationTargetException {
//              if (input.getContent().contains(text)) {
//                return input;
//              } else {
//                return null;
//              }
//            }
//          });
//
//      // Start the file readers.
//      fileReaders.startup();
//      // Start the file finders.
//      fileFinders.startup();
//
//      // Start the thread that dumps the matching output to the console.
//      final AtomicInteger dumpedCount = new AtomicInteger(0);
//      final ExecutorService dumpService = Executors.newFixedThreadPool(10);
//      Thread dumpingThread = new Thread(new Runnable() {
//
//        public void run() {
//          try {
//            while (true) {
//              final FileContent matchingFileInfo = outputQueue.take();
//              dumpService.submit(new Runnable() {
//
//                public void run() {
//                  System.out.println(matchingFileInfo.getFile());
//                }
//              });
//              dumpedCount.incrementAndGet();
//            }
//          } catch (InterruptedException exc) {
//            System.out.println("Done (" + dumpedCount.get() + ")");
//            Thread.currentThread().interrupt();
//            return;
//          }
//        }
//      });
//      dumpingThread.start();
//
//      // Start the directory scanner.
//      int workItemCount0 = scanner.scan(dir);
//      // Wait for the file readers to process its inputs.
//      int workItemCount1 = fileReaders.waitFor(workItemCount0);
//      // Wait for the file finders object to process its inputs.
//      int workItemCount2 = fileFinders.waitFor(workItemCount1);
//      // Interrupt the dumping thread once it has printed all output.
//      while (true) {
//        if (dumpedCount.get() == workItemCount2) {
//          dumpingThread.interrupt();
//          break;
//        }
//        Thread.sleep(100);
//      }
//      dumpingThread.join();
//      dumpService.shutdownNow();
//    } catch (Throwable exc) {
//      status = 1;
//      exc.printStackTrace();
//    } finally {
//      t0 = System.nanoTime() - t0;
//      System.out.println((t0 / 1000000) + " ms.");
//      System.exit(status);
//    }
//  }
}