package jdk1_5examples.threads.synchronizers;


import java.util.concurrent.*;
import java.io.*;
import java.net.*;
import java.awt.image.DataBuffer;

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
class FillAndEmpty {
/*
  Exchanger<DataBuffer> exchanger = new Exchanger<DataBuffer> ();
  DataBuffer initialEmptyBuffer = new DataBuffer();
  DataBuffer initialFullBuffer = new DataBuffer();

  void addToBuffer(final DataBuffer currentBuffer){

  }

  void takeFromBuffer(final DataBuffer currentBuffer){

  }

  class FillingLoop
      implements Runnable {
    public void run() {
      DataBuffer currentBuffer = initialEmptyBuffer;
      try {
        while (currentBuffer != null) {
          addToBuffer(currentBuffer);
          if (currentBuffer.full()) {
            currentBuffer = exchanger.exchange(currentBuffer);
          }
        }
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
  }


  class EmptyingLoop
      implements Runnable {
    public void run() {
      DataBuffer currentBuffer = initialFullBuffer;
      try {
        while (currentBuffer != null) {
          takeFromBuffer(currentBuffer);
          if (currentBuffer.empty()) {
            currentBuffer = exchanger.exchange(currentBuffer);
          }
        }
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
  }


  void start() {
    new Thread(new FillingLoop()).start();
    new Thread(new EmptyingLoop()).start();
  }
*/
}
