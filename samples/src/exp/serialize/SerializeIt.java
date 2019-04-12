package exp.serialize;

import java.util.Vector;
import java.io.*;


/**
 * <p>
 * Title: </p>
 * <p>
 * Description: </p>
 * <p>
 * Copyright: Copyright (c) </p>
 * <p>
 * Company: </p>
 *
 * @author unascribed
 * @version 1.0
 */

public class SerializeIt extends Vector {

  /*
  ** FIFO
   */
  SerializeIt() {
    super();
  }

  void put(Object o) {
    addElement(o);
  }

  Object get() {
    if (isEmpty()) {
      return null;
    }
    Object o = firstElement();
    removeElement(o);
    return o;
  }

  Object peek() {
    if (isEmpty()) {
      return null;
    }
    return firstElement();
  }

  public static void main(String args[]) {
    SerializeIt theQueue;

    theQueue = new SerializeIt();
    theQueue.put("element 1");
    theQueue.put("element 2");
    theQueue.put("element 3");
    theQueue.put("element 4");
    System.out.println(theQueue.toString());

    // serialize the Queue
    System.out.println("serializing theQueue");
    try {
      FileOutputStream fout = new FileOutputStream("c:/temp/thequeue.dat");
      ObjectOutputStream oos = new ObjectOutputStream(fout);
      oos.writeObject(theQueue);
      oos.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main_load(String args[]) {
    SerializeIt theQueue;

    theQueue = new SerializeIt();

    // unserialize the Queue
    System.out.println("unserializing theQueue");
    try {
      FileInputStream fin = new FileInputStream("c:/temp/thequeue.dat");
      ObjectInputStream ois = new ObjectInputStream(fin);
      theQueue = (SerializeIt) ois.readObject();
      ois.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(theQueue.toString());
  }
}
