package pt.intro.java.mthirteenth;


/**
 * <p>
 * Description: 
 * Record is a special purpose class in Java that is designed to provide an efficient
 * and easy way for programmers to carry aggregate data. Due to the introduction of this specific
 * class, a new, context-sensitive keyword called record has been added into the Java language.
 * 
 * Behind the scenes, the compiler automatically provides the necessary elements to store data,
 * constructors, getter methods to access data, toString(), equals(), and hashCode() methods without
 * any intervention of the programmer.
 * 
 * A few quick points to note about the above code example:
 *    - The canonical constructor provided by the record class contains the same parameter passed as the
 *      list of components and in the same order. The values passed are automatically assigned to the
 *      record fields. 
 *    - A record is instantiated by the new keyword, just like creating any other objects in Java. 
 *    - The data in the record is held in private final fields and there is only a getter
 *      method. Therefore, data in the record is immutable. 
 *    - A record cannot inherit another class. However, all records implicitly inherit java.lang.Record. 
 *      As such, it overrides equals(), hashCode(), toString() methods of the Object class. 
 *    - All record declarations are final, hence they cannot be extended. 
 *    - A record, however, can implement one or more interfaces. 
 *    - Any other fields, except the list of components, must be declared static.
 *
 * </p>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 */
public class RecordExample {

  public static void main(final String[] args){
    Person p1 = new Person(1,"Peter Parker");
    
    System.out.println(p1);
  }
}


record Person(int id, String name){}
