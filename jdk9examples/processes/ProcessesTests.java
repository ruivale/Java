package jdk9examples.processes;


/**
 * From: https://zeroturnaround.com/rebellabs/the-best-java-9-language-and-api-improvements/
 * <p>
 * - Process management up to Java 9 was one of the things that weren?t as portable as Java developers
 * would like to believe. Indeed, dealing with subprocesses in different operating systems was quite
 * cumbersome. In Java 9 it?s quite straightforward. Java 9 adds the ProcessHandle class, which offers an
 * API to inspect the current process, processes found by their PID, their children processes and so on.
 * <p>
 * - Another very common task that will be easier with Java 9 is executing code when a certain process
 * finishes. Java 9 adds
 * CompletableFuture<Process> onExit()
 * method exactly to specify what to do when a process is finished. No more tears and third party libs
 * required.
 * <p>
 */
public class ProcessesTests {

  public ProcessesTests() {
    try {

      final ProcessHandle current = ProcessHandle.current();
      System.out.println("current.info(): " + current.info().toString());
      System.out.println("current.command(): " + current.info().command());

      // ... etc.:
      // current.info().\TAB
      //    arguments()          command()         commandLine()        equals(              getClass()
      //    hashCode()           notify()          notifyAll()          startInstant()       toString()
      //    totalCpuDuration()   user()            wait()
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(final String[] args) {
    final ProcessesTests clazz = new ProcessesTests();
  }
}
