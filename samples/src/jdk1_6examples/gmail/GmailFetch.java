/**
 * <p>
 * Classname:  jdk1_6examples.gmail.GmailFetch
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 EFACEC SE
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
package jdk1_6examples.gmail;

import java.io.UnsupportedEncodingException;
import java.io.FileWriter;
import java.security.*;
import java.util.Properties;
//import javax.mail.*;
//import javax.mail.internet.*;
//import com.sun.mail.pop3.POP3SSLStore;
import java.util.Date;

/**
 * <p>
 * Description:
 * Sample java code to read mails from your gmail account:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class GmailFetch {

//  public static void main(String args[]) throws Exception {
//
//    Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
//
//// Get a Properties object
//    Properties props = System.getProperties();
//    props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
//    props.setProperty("mail.pop3.socketFactory.fallback", "false");
//    props.setProperty("mail.pop3.port", "995");
//    props.setProperty("mail.pop3.socketFactory.port", "995");
//    props.setProperty("mail.pop3.host", "pop.gmail.com");
//    props.setProperty("mail.pop3.user", args[0]);
//    props.setProperty("mail.pop3.passwd", args[1]);
//    props.setProperty("mail.pop3.ssl", "true");
//
//    Session session = Session.getInstance(props, null);
//    URLName urln = new URLName("pop3","pop.gmail.com",995,null,args[0],args[1]);
//    Store store = new POP3SSLStore(session, urln);
//    //session.setDebug(true);
//
//    store.connect("pop.gmail.com", args[0], args[1]);
//    Folder folder = store.getDefaultFolder();
//    folder = folder.getFolder("INBOX");
//    folder.open(Folder.READ_ONLY);
//
//    System.out.println("Message Count " + folder.getMessageCount());
//    System.out.println("New Message Count" + folder.getNewMessageCount());
//    System.out.println("=========================================");
//
//    Message[] messages = folder.getMessages();
//
//    FetchProfile fp = new FetchProfile();
//    fp.add(FetchProfile.Item.ENVELOPE);
//    folder.fetch(messages, fp);
//
//    for (int i = 0; i < messages.length; i++) {
//      System.out.println("From:" + messages[i].getFrom());
//    }
//    folder.close(true);
//    store.close();
//  }


}
