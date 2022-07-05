package jdk1_5examples.threads.locking;


/**
 * <p>Title: JDK1.5 Examples</p>
 *
 * <p>Description: Examples for the Java5.
 *
 *
 One of the popular features of the J2SE 5.0 libraries is the addition of concurrency utilities. Provided as part of JSR 166, the utilities provide advanced concurrency programming capabilities that take developers beyond the synchronized keyword and related synchronized blocks. One of the areas improved by the concurrency utilities is locking. Among the capabilities that it offers, the concurrency utilities support lock timeouts, multiple condition variables per lock, read-write locks, and the ability to interrupt a thread waiting for a lock. For more information about the additional lock support, see the documentation for the java.util.concurrent.locks package.

 The foundation of the package is the Lock interface, which offers a series of methods for acquiring and releasing a lock. The typical usage sequence is: get a lock, access the protected resource, and release the lock. Here is the typical usage pattern:

      Lock l = ...;
      l.lock();
      try {
          // protected resource access
      } finally {
          l.unlock();
      }

 When the Lock class is used in this way, it works similarly to a typical synchronized lock:

     synchronized(lockVariable) {
         // protected resource access
     }

 Similar but not the same. If you don't get the unlock() call in a finally block, your application won't function properly. With the synchronized block, that isn't necessary.

 Another method provided by the Lock interface that acquires a lock is lockInterruptibly(). After a thread requests a lock, it waits. The lockInterruptibly() method allows you to interrupt the waiting thread. The lock() method doesn't. If interrupt() is called on a thread that is waiting for the lock with lockInterruptibly(), the wait will be interrupted. The waiting thread then wakes up and an InterruptedException is thrown. In this case, no attempt is made to access the protected resource, and any other operations continue.

 The typical usage pattern for the lockInterruptibly() method is:

     Lock l = new ReentrantLock();
     try {
         l.lockInterruptibly();
         try {
             // protected resource access
         } finally {
             l.unlock();
         }
     } catch (InterruptedException e) {
         System.err.println("Interrupted wait");
     }

 Here, the inner try-finally handles exceptions while the resources are held. The outer try-catch handles exceptions acquiring the resource. You should never call unlock if the lock acquisition fails.

 A word of caution: calling lockInterruptibly() does not necessarily mean you can interrupt the wait. Not all Lock implementations support the operation.

 Another method provided by the Lock interface, tryLock(), brings timeouts into play. The tryLock() method comes in two versions. The first version accepts no arguments. It tries to get the lock, and immediately fails if the lock isn't available:

     Lock lock = ...;
     if (lock.tryLock()) {
       try {
         // protected resource access
       } finally {
         lock.unlock();
       }
     } else {
         // alternative operation
     }

 This is like going to the copy machine, seeing that someone is busy with a long copy job, and moving on to a slower but available machine for the task.

 The second version of the tryLock method accepts two parameters to signify a timeout. The first argument is a long variable to specify the maximum wait time. The second argument is a TimeUnit for specifying the unit of time.

 For instance, lock.tryLock(300, TimeUnit.MILLISECONDS) attempts to acquire a lock. If it can't get the lock after waiting for 300 milliseconds, it returns false, indicating the inability to get the lock. By comparison, lock.tryLock(30, TimeUnit.SECONDS) waits a maximum of 30 seconds. You need to explicitly specify both the value and the units.

 The TimeUnit class allows you to specify units as seconds, milliseconds, microseconds, or nanoseconds.

 1 second = 1 thousand milliseconds
 1 second = 1 million microseconds
 1 second = 1 billion nanoseconds

 Although you've seen how to use the Lock interface, you haven't seen how to actually create a Lock. The prior examples all show:

    Lock lock = ...;

 The java.util.concurrent.locks package includes three implementations of the Lock interface:

     * ReentrantLock
     * ReentrantReadWriteLock.ReadLock
     * ReentrantReadWriteLock.WriteLock

 The first implementation, ReentrantLock, is the one typically used. The other two implementations are inner classes of the ReentrantReadWriteLock class. The ReentrantReadWriteLock class contains two locks: ReadLock and WriteLock.

 For ReentrantLock, the typical usage pattern is:

      Lock l = new ReentrantLock();
      l.lock();
      try {
          // protected resource access
      } finally {
          l.unlock();
      }

 Use a read-write lock when reads are long and frequent, and writes are infrequent. Your access to a protected object would then use separate locks acquired as shown here:

   ReadWriteLock rwl = new ReentrantReadWriteLock();
   Lock readLock = rwl.readLock();
   Lock writeLock = rwl.writeLock();

 You then use the locks in the same manner as the ReentractLock, using the lock appropriate for the type of access you want to perform.

 The only implementation of the ReadWriteLock interface is the ReentrantReadWriteLock class itself.

 For more information on the Lock support, see the package description
 *
 *
 *
 * </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ??????????</p>
 *
 * @author rUI vALE
 * @version 1.0
 */
public class LockingTests {
  public LockingTests() {
  }

  public static void main(String[] args) {
    LockingTests lockingtests = new LockingTests();
  }
}
