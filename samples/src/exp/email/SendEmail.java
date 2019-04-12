/**
 * <p>
 * Classname: exp.email.SendEmail
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2018 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC SE
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
package exp.email;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author 2334
 */
public class SendEmail {

  public static void sendMail() {
    //Setting up configurations for the email connection to the Google SMTP server using TLS
    Properties props = new Properties();
    props.put("mail.smtp.host", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    
    //Establishing a session with required user details
    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("ruivale.tekie@gmail.com", "****");
      }
    });
    
    try {
      //Creating a Message object to set the email content
      MimeMessage msg = new MimeMessage(session);
      //Storing the comma seperated values to email addresses
      String to = "ruivale@gmail.com,rui.vale@efacec.com";
      /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
            addresses in an array of InternetAddress objects*/
      InternetAddress[] address = InternetAddress.parse(to, true);
      //Setting the recepients from the address variable
      msg.setRecipients(Message.RecipientType.TO, address);
      String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
      msg.setSubject("Sample Mail : " + timeStamp);
      msg.setSentDate(new Date());
      msg.setText("Sampel System Generated mail");
      msg.setHeader("XPriority", "1");
      Transport.send(msg);
      System.out.println("Mail has been sent successfully");
      
    } catch (MessagingException mex) {
      System.out.println("Unable to send an email" + mex);
    }
  }
  
  
  public static void main(String[] args) {
    
//    System.setProperty("java.net.useSystemProxies", "true");
    
    System.setProperty("http.proxyHost", "172.18.2.248");
    System.setProperty("http.proxyPort", "8080");
    System.setProperty("https.proxyHost", "172.18.2.248");
    System.setProperty("https.proxyPort", "8080");
    System.setProperty("http.proxyUser", "2334");
    System.setProperty("http.proxyPassword", "*****");
    System.setProperty("https.proxyUser", "2334");
    System.setProperty("https.proxyPassword", "*****");
 

    SendEmail.sendMail();
    
    
    System.clearProperty("http.proxyHost");
    System.clearProperty("http.proxyPort");
    System.clearProperty("https.proxyHost");
    System.clearProperty("https.proxyPort");    
    System.clearProperty("http.proxyUser");
    System.clearProperty("http.proxyPassword");
    System.clearProperty("https.proxyUser");
    System.clearProperty("https.proxyPassword");
  }
}

/***
C:\temp>java 
*   -Dhttps.proxyHost=host 
*   -Dhttps.proxyPort=port 
*   -Dhttps=proxyUser=user 
*   -Dhttps.proxyPassword="password" 
*   -Djavax.net.ssl.trustStore=c:/cacerts 
*   -Djavax.net.ssl.trustStorePassword=changeit 
*       com.andreas.JavaNetHttpConnection 
* 
/**/