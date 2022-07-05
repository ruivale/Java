package exp.external.commands;

import org.apache.commons.exec.*;
import org.apache.commons.exec.Executor;
import java.io.IOException;
import java.util.concurrent.*;


/**
 *
 * @author 2334
 */
public class ProcessExecutor {

  public static final Long WATCHDOG_EXIST_VALUE = -999L;

  /**
   *
   * @param commandline
   * @param handler
   * @param watchdogTimeout
   * @return
   * @throws IOException
   */
  public static Future<Long> runProcess(final CommandLine commandline,
                                        final ProcessExecutorHandler handler,
                                        final long watchdogTimeout) throws IOException {

    ExecutorService executor = Executors.newSingleThreadExecutor();
    return executor.submit(new ProcessCallable(watchdogTimeout, handler, commandline));

  }


  /**
   *
   *
   */
  private static class ProcessCallable implements Callable<Long> {

    private long watchdogTimeout;
    private ProcessExecutorHandler handler;
    private CommandLine commandline;


    /**
     *
     * @param watchdogTimeout
     * @param handler
     * @param commandline
     */
    private ProcessCallable(long watchdogTimeout,
                            ProcessExecutorHandler handler,
                            CommandLine commandline) {
      this.watchdogTimeout = watchdogTimeout;
      this.handler = handler;
      this.commandline = commandline;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public Long call() throws Exception {
      Executor executor = new DefaultExecutor();
      executor.setProcessDestroyer(new ShutdownHookProcessDestroyer());
      ExecuteWatchdog watchDog = new ExecuteWatchdog(watchdogTimeout);
      executor.setWatchdog(watchDog);
      executor.setStreamHandler(new PumpStreamHandler(new MyLogOutputStream(handler, true),
          new MyLogOutputStream(handler, false)));
      Long exitValue;
      try {
        exitValue = new Long(executor.execute(commandline));

      } catch (ExecuteException e) {
        exitValue = new Long(e.getExitValue());
      }
      if (watchDog.killedProcess()) {
        exitValue = WATCHDOG_EXIST_VALUE;
      }

      return exitValue;

    }
  }


  /**
   *
   *
   *
   */
  private static class MyLogOutputStream extends LogOutputStream {

    private ProcessExecutorHandler handler;
    private boolean forewordToStandardOutput;


    /**
     *
     * @param handler
     * @param forewordToStandardOutput
     */
    private MyLogOutputStream(ProcessExecutorHandler handler,
                              boolean forewordToStandardOutput) {
      this.handler = handler;
      this.forewordToStandardOutput = forewordToStandardOutput;
    }

    /**
     * 
     * @param line
     * @param level
     */
    @Override
    protected void processLine(String line,
                               int level) {
      if (forewordToStandardOutput) {
        handler.onStandardOutput(line);
      } else {
        handler.onStandardError(line);
      }
    }
  }

}
