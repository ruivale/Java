package exp.java.lang.deepclone;

import java.io.*;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class DeepClone {
  private Integer integer = new Integer(909);
  private String str = "novecentos e nove";

  public DeepClone() {
  }

  private void serialize() throws Exception{
    ByteArrayOutputStream byteArrOs = new ByteArrayOutputStream();
    ObjectOutputStream objOs = new ObjectOutputStream(byteArrOs);
    objOs.writeObject(this);
  }

  private void deserealize() throws Exception{
    ByteArrayOutputStream byteArrOs = new ByteArrayOutputStream();
    ByteArrayInputStream byteArrIs = new ByteArrayInputStream(byteArrOs.toByteArray());
    ObjectInputStream objIs = new ObjectInputStream(byteArrIs);
    Object deepCopy = objIs.readObject();
  }

  public static void main(String[] args) {
    try {
      DeepClone deepclone = new DeepClone();
      deepclone.serialize();

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
