/**
 * <p>
 * Classname:  exp.publishsubscriber.pubsub.Run
 * </p>
 *
 * <p>Copyright: Copyright (c) 2019 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */

package exp.publishsubscriber.pubsub;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class Run {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(Run.class.getName());


 /**
  * The Run default constructor.
  */
  public Run(){
    try {
      Subscriber subscriber = new Subscriber(1);
      Subscriber subscriber2 = new Subscriber(2);
      
      Subscriber subscriber3 = new Subscriber(3);
      Subscriber subscriber4 = new Subscriber(4);
      
      Event.operation.subscribe("action#create", subscriber);
      Event.operation.subscribe("action#create", subscriber2);
      
      Event.operation.subscribe("action#update", subscriber3);
      Event.operation.subscribe("action#delete", subscriber4);
      
      Message message = new Message("Create Action");
      Message message2 = new Message("Update Action");
      
      Event.operation.publish("action#create", message);
      Event.operation.publish("action#update", message2);      
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("Run").append("").toString();
  }

  public static void main(final String[] args){
    final Run clazz = new Run();
  }
}
