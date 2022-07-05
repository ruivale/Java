package jdk1_5examples.threads.future;


import java.util.concurrent.*;
import java.io.*;
import java.net.*;


/**
 * <p>Title: JDK1.5 Examples</p>
 *
 * <p>Description: Examples for the Java5. </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ??????????</p>
 *
 * @author rUI vALE
 * @version 1.0
 */

public class Cache<K, V> {
  ConcurrentMap<K, FutureTask<V>> map = new ConcurrentHashMap<K, FutureTask<V>>();
  Executor executor = Executors.newFixedThreadPool(8);

  public V get(final K key) {
    FutureTask<V> f = map.get(key);
    if (f == null) {
      Callable<V> c = new Callable<V> () {
        public V call() {
          return null;
        }
      };
      f = new FutureTask<V> (c);
      FutureTask old = map.putIfAbsent(key, f);
      if (old == null) {
        executor.execute(f);
      } else {
        f = old;
      }
    }
    V v = null;
    try {
      v = f.get();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return v;
  }
}
