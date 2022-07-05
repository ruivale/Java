package exp.methods.findmethods;

import com.ent.corba.EventService.Property;


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
public class FindClassMethods/* implements FindClassMethodsInt */{

  public FindClassMethods() {
    super();
  }

  /**
   *
   * @param p Property[]
   * @return boolean
   */
  public boolean deliverEvent(Property[] p, long[] l){
    System.out.println("FindClassMethods - deliverEvent(Property[] p, long[] l)");

    for (int i = 0; i < p.length; i++) {
      System.out.println("Property["+i+"]=("+p[i].key+", "+p[i].value+").");
    }

    for (int i = 0; i < l.length; i++) {
      System.out.println("long["+i+"]="+l[i]+".");
    }

    return true;
  }

  /**
   *
   * @param p Property[]
   * @param l long[]
   * @param b byte[]
   * @return boolean
   */
  public boolean displayEvent(Property[] p, long[] l, byte[] b){
    System.out.println("FindClassMethods - displayEvent(Property[] p, long[] l, byte[] b)");

    for (int i = 0; i < p.length; i++) {
      System.out.println("Property["+i+"]=("+p[i].key+", "+p[i].value+").");
    }

    for (int i = 0; i < l.length; i++) {
      System.out.println("long["+i+"]="+l[i]+".");
    }

    for (int i = 0; i < b.length; i++) {
      System.out.println("byte["+i+"]="+b[i]+".");
    }

    return true;
  }

  /**
   *
   * @param p Property[]
   * @param l long
   * @param b byte
   * @return boolean
   */
  public boolean displayEvent(Property[] p, long l, byte b){
  //public boolean displayEvent(Property[] p, long[] l, byte[] b){
    System.out.println("FindClassMethods - displayEvent(Property[] p, long l, byte b)");

    for (int i = 0; i < p.length; i++) {
      System.out.println("Property["+i+"]=("+p[i].key+", "+p[i].value+").");
    }

    System.out.println("long="+l+".");
    System.out.println("byte="+b+".");

    return true;
  }


}
