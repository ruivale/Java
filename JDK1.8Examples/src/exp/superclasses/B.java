/**
 * <p>
 * Classname: package exp.superclasses.B
 * </p>
 *
 * <p>Copyright: Copyright (c) 2008 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Engº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package exp.superclasses;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - rui dot vale at efacec dot pt
 * @version $Revision: 1.1 $
 */
public class B extends A /*implements ABInt */{
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(B.class.getName());

  private JFrame f = new JFrame();

 /**
  * The B default constuctor.
  */
  public B(){
    super("ahah");

    if(f != null){
      System.out.println("B f is NOT NULL");
    }else{
      System.out.println("B f is NULL");
    }
  }

  public JFrame getJFrame(){
    return this.f;
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("B").append("").toString();
  }
  
  public static void main(final String[] args){
    final B clazz = new B();
  }
}
