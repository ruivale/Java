package jdk23examples.thread.virtual;

/**
 * <p>
 * Description:
 * Basic intro to virtual threads.
 * </p>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 *
 * @since 240926
 */
public class VirtualThreadExample {

  public static void main(String[] args) throws InterruptedException {
    // Create and start a virtual thread
    final Thread virtualThread = Thread.ofVirtual().start(() -> 
      System.out.println("This is running in a virtual thread ("+
          Thread.currentThread()+ " - " + Thread.currentThread().threadId()+")!")
    );

    // Wait for the virtual thread to complete execution
    virtualThread.join();

    // Creating multiple virtual threads to showcase concurrency
    for (int i = 1; i <= 5; i++) {
      final int taskId = i; // Effectively final variable for the lambda expression
      
      Thread.ofVirtual().start(() -> 
        System.out.println("Task " + taskId + " is running in a virtual thread ("+
          Thread.currentThread()+ " - " + Thread.currentThread().threadId()+")!")
      );
    }

    // Adding sleep to ensure all virtual threads finish before main thread ends
    Thread.sleep(1000); // Delay to allow all threads to finish
  }
}
