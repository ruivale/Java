/**
 * <p>
 * Classname:  jdk1_6examples.email.validation.ValidatingEmails
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 Efacec Engenharia e Sistemas, S.A.
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

package jdk1_6examples.email.validation;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on 24/Jan/2011, 10:48:12
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class ValidatingEmails {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(ValidatingEmails.class.getName());


 /**
  * The ValidatingEmails default constuctor.
  */
  public ValidatingEmails(){
/************************************************************************************************
Often times you need to validate the e-mail address of your addressees, just before they want
 to send you a message. Here are the logical checks:
 
One or more characters before the "@"
An optional "[", because user@[255.255.255.0] is a valid e-mail
A sequence of letters, numbers, and periods, which are all valid domain or
IP address characters
A period followed by a 2-3 letter suffix
An optional "]"
Here is a function that validates this sequence:

function valid(form) {
  var field = form.email; // email field
  var str = field.value; // email string
  var reg1 = /(@.*@)|(\.\.)|(@\.)|(\.@)|(^\.)/; // not valid
  var reg2 = /^.+\@(\[?)[a-zA-Z0-9\-\.]+\.([a-zA-Z]{2,3}|[0-9]{1,3})(\]?)$/; // valid
  if (!reg1.test(str) && reg2.test(str)) { // if syntax is valid
    alert("Thank your for your feedback."); // this is optional
    return true;
  }
  alert("\"" + str + "\" is an invalid e-mail!"); // this is also optional
  field.focus();
  field.select();
  return false;
}
/**/
  }

  public static void main(final String[] args){
    final ValidatingEmails clazz = new ValidatingEmails();
  }
}
