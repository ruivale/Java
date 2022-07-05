/**
 * <p>
 * Classname: exp.util.list.ListCloneTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel: +351 22 940 2000
 * <br>
 * Fax: +351 22 948 5428
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package exp.util.list;

import java.util.ArrayList;
import java.util.List;




/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jun 8, 2017, 10:16:17 AM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class ListCloneTests {

  public ListCloneTests(){

    ArrayList<Event> listEvts = new ArrayList<>();
    listEvts.add(new Event(-9, false, "String from constructor 9"));
    listEvts.add(new Event(-99, true, "String from constructor 99"));
    listEvts.add(new Event(-999, false, "String from constructor 999"));

    System.out.println("First list:");
    for(Event evt: listEvts){
      System.out.println("\t"+evt.toString());
    }

    ArrayList<Event> listEvtsCloned = (ArrayList<Event>)listEvts.clone();

    int i = 0;
    System.out.println("Second list (after the clone):");
    for(Event evt: listEvts){
      System.out.println("\t"+evt.toString());

      evt.iPublic = i++;
      evt.boolPublic = false;
      evt.strPublic = evt.iPublic + " - " + evt.boolPublic;
    }



    System.out.println("First list ((after the changes)):");
    for(Event evt: listEvts){
      System.out.println("\t"+evt.toString());
    }
    System.out.println("Second list (after the changes):");
    for(Event evt: listEvts){
      System.out.println("\t"+evt.toString());
    }

  }


  public static void main(final String[] args){
    final ListCloneTests clazz = new ListCloneTests();
  }
}

class Event {
  public int iPublic = 1;
  public boolean boolPublic = true;
  public String strPublic = "Public String";
  private int iPrivate = -1;
  private boolean boolPrivate = false;
  private String strPrivate = "Private String";

  Event(int i, boolean vool, String str){
    this.iPrivate = i;
    this.boolPrivate = vool;
    this.strPrivate = str;
    this.iPublic = i;
    this.boolPublic = vool;
    this.strPublic = str;
  }

  public int getInt(){
    return this.iPrivate;
  }
  public boolean getBool(){
    return this.boolPrivate;
  }
  public String getString(){
    return this.strPrivate;
  }

  public String toString(){
    return "Event("+
           "private: " + this.iPrivate + ", public: "+ this.iPublic +
           " private: " + this.boolPrivate + ", public: "+ this.boolPublic +
           " private: " + this.strPrivate+ ", public: "+ this.strPublic +
           ")";
  }
}
