package com.efacec.trp.efarail.efatoolbar;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;



public class EFAToolbarSim {

  private static final String STR_JAVAW_PATH = "C:\\Program Files\\EFACEC\\jre\\bin\\javaw";

  private static final String STR_RBUNDLE = "com.efacec.trp.efarail.efatoolbar.sample";
  private static final String STR_ASCII = "ascii";
  private static final String STR_UTF8 = "utf8";
  private static final String STR_UTF_8 = "UTF-8";
  private static final Charset CS_UTF8 = Charset.forName(STR_UTF_8);




  public static void main(final String[] args){
    
    if(true){
      //runTasklist();
      runWmic();
      return;
    }
    

//    runTK(
//        true,
//        10000,
//        new String[]{
//          "-encoding=" + STR_UTF8,
//          "-font=SimSun",
//          "-lang=en-GB"
//        });


    if(args != null && args.length > 0){
      boolean forceProccessKill = args.length > 1 && args[1].startsWith("-killAfter");
      int timeout = 0;

      try {
        timeout =
            forceProccessKill ?
                Integer.parseInt(args[1].substring(args[1].indexOf('=') + 1, args[1].length())) :
                0;

      } catch (Exception e) {
        timeout = 0;
      }

      switch(args[0].toLowerCase()){
        case "inoss":
          runINOSS(forceProccessKill, timeout * 1000);
          break;

        case "timekeeper":
          //Arrays.copyOf(args, args.length-2, Class<String[]> newType);
          final String[] tkArgs = new String[args.length-2];
          System.arraycopy(args, 2, tkArgs, 0, args.length-2);
          runTK(forceProccessKill, timeout * 1000, tkArgs);
          break;

        default:
          runJava();
          break;
      }
    }
  }


  /**
   *
   * @param forceProcessKill
   */
  private static void runINOSS(final boolean forceProcessKill, int iWait2Kill) {

    try {
      /*************************************************************/
      final ResourceBundle rb = ResourceBundle.getBundle(STR_RBUNDLE, Locale.CHINESE);
      final String strUser = rb.getString("user");
      final String strPass = rb.getString("pass");

      final String strUsernameEncoded = URLEncoder.encode(strUser, STR_UTF_8);
      final String strPasswordEncoded = URLEncoder.encode(strPass, STR_UTF_8);

      final String strStartingDir = "C:\\Program Files\\EFACEC\\INOSS";
      final File fileInossDir = new File(strStartingDir);
      String[] strCommand = {
        STR_JAVAW_PATH,
        "-Xms128m",
        "-Xmx256m",
        "-Dlaf=Windows",
        "-DEFARail",
        "-DtlcMonitor=1",
        "-DswMonitor=1",
        "-DevtAreaOn=true",
        "-DnotificationsAreaOn=false",
        "-DlogToConsole=false",
        "-DlogLevel=SEVERE",
        "-DsizeLogFile=1024000",
        "-DnumLogFiles=4",
        "-DERComNoRadio",
        "-DmayCloseINOSS",
        "-Djacorb.config.dir=.\\lib\\",

        "-classpath",
        ".;C:\\Program Files\\EFACEC\\INOSS\\lib\\*",

        "pt.efacec.se.inoss.tlc.PInt",

        //"-password=rgv",
        //"-username=inoss",

        "-username=" + strUsernameEncoded,

        //"-lang=nb-NO",
        "-lang=en-GB",

        "-font=SimSun",

        "-encoding="+STR_UTF8
      };



//      {
//        javax.swing.JFrame f = new javax.swing.JFrame("RunTestProcess starting INOSS");
//        javax.swing.JLabel l = new javax.swing.JLabel();
//        l.setFont(new java.awt.Font("SimSun", java.awt.Font.PLAIN, 10));
//        l.setText("UsernameEncoded:"+strUsernameEncoded /*+ " PasswordEncoded:"+strPasswordEncoded*/);
//        f.setLayout(new java.awt.BorderLayout());
//        f.add(l, java.awt.BorderLayout.NORTH);
//        javax.swing.JList<String> l2 = new javax.swing.JList<>();
//        l2.setFont(new java.awt.Font("SimSun", java.awt.Font.PLAIN, 10));
//
////        for(String s: strCommand){
////          if(s.startsWith("-user") || s.startsWith("-p@ss")){
////            String strS = new String(s.getBytes(), Charset.forName("UTF-8"));
////            l2.add(new javax.swing.JLabel("s:"+s));
////            l2.add(new javax.swing.JLabel("    strS:"+strS));
////          }
////        }
//
//        f.add(l2, java.awt.BorderLayout.CENTER);
//        f.setBounds(700, 550, 550, 325);
//        f.setVisible(true);
//      }




      final ProcessBuilder builder = new ProcessBuilder(strCommand).directory(fileInossDir);
      final Process process = builder.start();
      final OutputStream stdin = process.getOutputStream();
      final InputStream stderr = process.getErrorStream();
      final InputStream stdout = process.getInputStream();


      final BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, CS_UTF8));
      final BufferedReader readerErr = new BufferedReader(new InputStreamReader(stderr, CS_UTF8));
      final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin, CS_UTF8));
      /**/



      final Thread threadRead = new Thread(() -> {
        String line;

        try {
          while ((line = reader.readLine()) != null) {
            System.out.println(getNowDesc()+" - Stdout: " + line);
          }
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }, "ThreadName");
      threadRead.start();

      final Thread threadReadErr = new Thread(() -> {
        String line;

        try {
          while ((line = readerErr.readLine()) != null) {
            System.out.println(getNowDesc()+" - Stderr: " + line);
          }
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }, "ThreadName");
      threadReadErr.start();

      final Thread threadWrite = new Thread(() -> {
        try {
          Thread.sleep(5000);


//          javax.swing.JFrame f = new javax.swing.JFrame("RunTestProcess sending pass 2 STDIO");
//          javax.swing.JLabel l = new javax.swing.JLabel();
//          l.setFont(new java.awt.Font("SimSun", java.awt.Font.PLAIN, 10));
//          l.setText("Pass:"+strPass +" PassEncodec:"+strPasswordEncoded);
//          f.setLayout(new java.awt.BorderLayout());
//          f.add(l);
//          f.setBounds(700,120,550,100);
//          f.setVisible(true);

          //final String
          final String _strPass = "APP_PASSWORD="+
              //strPass;
              strPasswordEncoded;



          System.out.println(getNowDesc()+" - Writing to INOSS: "+_strPass);
          writer.write(_strPass+"\n");
          writer.flush();


          if(forceProcessKill){
            Thread.sleep(iWait2Kill);
            System.out.println(getNowDesc()+" - Writing to INOSS: \"APP_EXIT\"");
            writer.write("APP_EXIT\n");
            writer.flush();


            final long lTime2Wait = 30000;
            System.out.println(getNowDesc()+" - Will wait for "+lTime2Wait+" millis for the APP to end...");
            process.waitFor(lTime2Wait, TimeUnit.MILLISECONDS);

            if(process.isAlive()){
              System.out.println(getNowDesc()+" - Since the APP is still running, we destroy it forcibly...");
              process.destroyForcibly();
            }
          }

        } catch (Exception exception) {
          exception.printStackTrace();
        }
      }, "ThreadName");
      threadWrite.start();






      System.out.println(getNowDesc()+" - Waiting for the process to terminate ...");
      process.waitFor();
      System.out.println(getNowDesc()+" - ... terminated.");
      System.out.println(getNowDesc()+" - Exit code="+process.exitValue()+".");

      try {Thread.sleep(1087); } catch (InterruptedException interruptedException) { }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }


  /**
   *
   */
  private static void runJava() {
    try {
      /*************************************************************/
      final ResourceBundle rb = ResourceBundle.getBundle(STR_RBUNDLE, Locale.CHINESE);
      final String strUser = rb.getString("user");
      final String strPass = rb.getString("pass");

      final String strEncodedUser =
          //new String(strUser.getBytes(charSetUTF8));
          URLEncoder.encode(strUser,"UTF-8");
      final String strUserNameInfo =
          "-username="+
          strEncodedUser;
          //strUser;
          //new String(("-username="+strUserUTF).getBytes(), charSetUTF8);


      final File fileInossDir = new File("C:\\Program Files\\EFACEC\\_EFAToolbarSimulator");
      String[] strCommand = {
        STR_JAVAW_PATH,
        //"-Dfile.encoding=utf-8",
        "-Xms16m",
        "-Xmx32m",
        "-Demo",
        "-Dinit=true",
        "-classpath",
        ".\\;lib\\*",
        "com.efacec.trp.efarail.efatoolbar.ShowSomeDialog",
//        "-encodedUser=UserEncoded",
//        "-encodedUser="+strEncodedUser,
//        "-p@ss="+strPass,
        strUserNameInfo,
      };


//      {
//        javax.swing.JFrame f = new javax.swing.JFrame("RunTestProcess");
//        javax.swing.JLabel l = new javax.swing.JLabel();
//        l.setFont(new java.awt.Font("SimSun", java.awt.Font.PLAIN, 10));
//        l.setText("user:"+strUser+" encodedUser:"+strEncodedUser+" userNameInfo:"+strUserNameInfo);
//        f.setLayout(new java.awt.BorderLayout());
//        f.add(l, java.awt.BorderLayout.NORTH);
//        javax.swing.JTextArea l2 = new javax.swing.JTextArea();
//        l2.setFont(new java.awt.Font("SimSun", java.awt.Font.PLAIN, 10));
//
//        l2.append("-------------------------------------------------------------\n");
//        for(String s: strCommand){
//          //if(s.startsWith("-user") || s.startsWith("-p@ss")){
//            String strS = new String(s.getBytes(), Charset.forName("UTF-8"));
//            l2.append("s:"+s);
//            l2.append("    strS:"+strS);
//            l2.append("\n");
//          //}
//        }
//
//        f.add(l2, java.awt.BorderLayout.CENTER);
//        f.setBounds(700, 50, 550, 325);
//        f.setVisible(true);
//      }



      final ProcessBuilder builder = new ProcessBuilder(strCommand).directory(fileInossDir);
//      System.out.println("-------------------------------------------------------------------------\nEnvironment:");
//      Map<String,String> mapEnvs = builder.environment();
//      for(String s: mapEnvs.keySet()){
//        System.out.println("\t"+s+": "+mapEnvs.get(s));
//      }
//      System.out.println("-------------------------------------------------------------------------");

      //builder.redirectErrorStream(true); // the out & err streams become ONE
      final Process process = builder.start();
      final OutputStream stdin = process.getOutputStream();
      final InputStream stderr = process.getErrorStream();
      final InputStream stdout = process.getInputStream();

      final BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
      final BufferedReader readerErr = new BufferedReader(new InputStreamReader(stderr));
      final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
      /**/



      final Thread threadRead = new Thread(() -> {
        String line;

        try {
          while ((line = reader.readLine()) != null) {
            System.out.println(getNowDesc()+" - Stdout: " + line);
          }
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }, "ThreadName");
      threadRead.start();

      final Thread threadReadErr = new Thread(() -> {
        String line;

        try {
          while ((line = readerErr.readLine()) != null) {
            System.out.println(getNowDesc()+" - Stderr: " + line);
          }
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }, "ThreadName");
      threadReadErr.start();

      final Thread threadWrite = new Thread(() -> {
        try {
          Thread.sleep(1600);
          System.out.println(getNowDesc()+" - Writing to PIntInfo: \"This is the EFAToolbar. DO YOU REACH?\"");
          writer.write("This is the EFAToolbar. DO YOU REACH?");
          writer.flush();
          //writer.close();
          Thread.sleep(870);
          System.out.println(getNowDesc()+" - Writing to PIntInfo: \"END\"");
          writer.write("END");
          writer.flush();

          final long lTime2Wait = 2500;
          System.out.println(getNowDesc()+" - Will wait for "+lTime2Wait+" millis for the APP to end...");
          process.waitFor(lTime2Wait, TimeUnit.MILLISECONDS);

          if(process.isAlive()){
            System.out.println(getNowDesc()+" - Since the APP is still running, we destroy it forcibly...");
            process.destroyForcibly();
          }

        } catch (Exception exception) {
          exception.printStackTrace();
        }
      }, "ThreadName");
//      threadWrite.start();





      System.out.println(getNowDesc()+" - Process "+process.toString());



      System.out.println(getNowDesc()+" -Waiting for the process to terminate ...");
      process.waitFor();
      System.out.println(getNowDesc()+" - ... terminated.");
      System.out.println(getNowDesc()+" - Exit code="+process.exitValue()+".");

      try {Thread.sleep(4087); } catch (InterruptedException interruptedException) { }

      System.exit(0);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }


  /**
   *
   */
  private static void runTK(final boolean forceProcessKill, int iWait2Kill, String ... values) {
    try {
      /*************************************************************/
      final ResourceBundle rb = ResourceBundle.getBundle(STR_RBUNDLE, Locale.CHINESE);
      final String strUser = rb.getString("user");
      final String strPass = rb.getString("pass");

      final String strUsernameEncoded = URLEncoder.encode(strUser, STR_UTF_8);
      final String strPasswordEncoded = URLEncoder.encode(strPass, STR_UTF_8);

      final File fileStartDir = new File("c:\\Program Files\\EFACEC\\TimeKeeper\\SAE_Explo");
      String[] strCommand = new String[values!=null? values.length+2: 2];
      if(values != null && values.length > 0){
        System.arraycopy(values, 0, strCommand, 2, values.length);
      }
//      {
//        fileStartDir.getAbsolutePath()+ "\\SAE.exe",
//        "-encoding=" + STR_UTF8,
//        "-username=" + strUsernameEncoded,
//        "-font=SimSun",
//        "-lang=en-GB"
//      };
      strCommand[0] = fileStartDir.getAbsolutePath()+ "\\SAE.exe";
      strCommand[1] = "-username=" + strUsernameEncoded;



      final javax.swing.JLabel l = new javax.swing.JLabel();
      {
        javax.swing.JFrame f = new javax.swing.JFrame("RunTestProcess starting TK");
        l.setFont(new java.awt.Font("SimSun", java.awt.Font.PLAIN, 10));
        //l.setText("FromFile user:"+strUser+" -username=" + strUsernameEncoded);
        f.setLayout(new java.awt.BorderLayout());
        f.add(l, java.awt.BorderLayout.SOUTH);
        javax.swing.JLabel l2 = new javax.swing.JLabel();
        l2.setFont(new java.awt.Font("SimSun", java.awt.Font.PLAIN, 10));
        String s2 = "<html><body> Params: <br>";
        for(String s: strCommand){
          s2 += s;

          if(s.startsWith("-user") || s.startsWith("-p@ss")){
            s2 += ", which represents: "+URLDecoder.decode(s, STR_UTF_8);
          }

          s2 += "<br>";
        }
        s2 += "</body></html>";
        l2.setText(s2);
        f.add(l2, java.awt.BorderLayout.CENTER);
        f.setBounds(100, 100, 750, 150);
        f.setVisible(true);
      }



      final ProcessBuilder builder = new ProcessBuilder(strCommand).directory(fileStartDir);
      final Process process = builder.start();
      final OutputStream stdin = process.getOutputStream();
      final InputStream stderr = process.getErrorStream();
      final InputStream stdout = process.getInputStream();

      final BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, CS_UTF8));
      final BufferedReader readerErr = new BufferedReader(new InputStreamReader(stderr, CS_UTF8));
      final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin, CS_UTF8));
      /**/


      /**/
      final Thread threadRead = new Thread(() -> {
        String line;

        try {
          while ((line = reader.readLine()) != null) {
            System.out.println(getNowDesc()+" - TK Stdout: " + line);
          }
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }, "ThreadName");
      threadRead.start();
      /**/

      /**/
      final Thread threadReadErr = new Thread(() -> {
        String line;

        try {
          while ((line = readerErr.readLine()) != null) {
            System.out.println(getNowDesc()+" - TK Stderr: " + line);
          }
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }, "ThreadName");
      threadReadErr.start();
      /**/

      /**/
      final Thread threadWrite = new Thread(() -> {
        try {
          Thread.sleep(1000);

          final String _strPass = "APP_PASSWORD="+strPasswordEncoded;


          System.out.println(getNowDesc()+" - Writing to TK: "+_strPass);
          writer.write(_strPass+"\n");
          writer.flush();

          SwingUtilities.invokeLater(() -> {
            try {
              l.setText(_strPass + " which represents: " + URLDecoder.decode(_strPass, STR_UTF_8));
              l.validate();
              l.repaint();
            } catch (UnsupportedEncodingException unsupportedEncodingException) {
            }
          });

          if(forceProcessKill){
            Thread.sleep(iWait2Kill);
            System.out.println(getNowDesc()+" - Writing to TK: \"APP_EXIT\"");
            writer.write("APP_EXIT\n");
            writer.flush();


            final long lTime2Wait = 30000;
            System.out.println(getNowDesc()+" - Will wait for "+lTime2Wait+" millis for the APP to end...");
            process.waitFor(lTime2Wait, TimeUnit.MILLISECONDS);

            if(process.isAlive()){
              System.out.println(getNowDesc()+" - Since the APP is still running, we destroy it forcibly...");
              process.destroyForcibly();
            }
          }

        } catch (Exception exception) {
          exception.printStackTrace();
        }
      }, "ThreadName");
      threadWrite.start();
      /**/






      System.out.println(getNowDesc()+" - Waiting for the process to terminate ...");
      process.waitFor();
      System.out.println(getNowDesc()+" - ... terminated.");
      System.out.println(getNowDesc()+" - Exit code="+process.exitValue()+".");

      try {Thread.sleep(1087); } catch (InterruptedException interruptedException) { }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  
  
  /**
   * 
   */
  private static void runTasklist(){

    boolean isRunning = false;

    final String strApp = "Calculator.exe";
    
    try {

      // TASKLIST | FINDSTR AegisCCClient.exe
      final ProcessBuilder processBuilder = new ProcessBuilder("TASKLIST | FINDSTR "+strApp);

      final Process process = processBuilder.start();



      final InputStream in = process.getInputStream();
      final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      String line;

      while ((line = reader.readLine()) != null) {

        isRunning = line != null && !line.isEmpty();

        System.out.println("\t\tTasklist for "+strApp+" STDOUT: " + line);
      }  



      process.waitFor();
      final int iProcessExitValue = process.exitValue();

      System.out.println("Checking application: "+processBuilder.command()+" -> "+iProcessExitValue+".");

    } catch (Exception exc) {
      System.err.println("Cannot check if the "+strApp+" application is running.");
    }

            
    System.out.println("EFAToolbarSim.checkToolbarApplicationRunning("+strApp+") isRunning?"+ isRunning);
  }

  
  /**
   * 
   */
  private static void runWmic(){

    boolean isRunning = false;
    
    final String strProcessName = "Calculator.exe".toUpperCase();
    
    InputStream is = null;
    InputStreamReader isr = null;
    BufferedReader br = null;

    final List<String> command = new ArrayList<>(6);
    command.add("wmic");
    command.add("process");
    command.add("list");
    command.add("brief");

    try {
      final ProcessBuilder builder = new ProcessBuilder(command);
      final Process process = builder.start();
      is = process.getInputStream();
      isr = new InputStreamReader(is);
      br = new BufferedReader(isr);
      String line;
      
      //processName = processName.toUpperCase();
      while ((line = br.readLine()) != null) {
        if (line.toUpperCase().indexOf(strProcessName) > -1){
          isRunning = true;
        }
      }
      
    }catch(Exception exc){
      System.err.println("Cannot check if the "+strProcessName+" application is running.");
      
    } finally {
      //<editor-fold defaultstate="collapsed" desc="Closing stuff...">
      if (br != null){
        try {
          br.close();
        } catch (IOException iOException) {
        }
      }
      if (isr != null){
        try {
          isr.close();
        } catch (IOException iOException) {
        }
      }
      if (is != null) {
        try {
          is.close();
        } catch (IOException iOException) {
        }
      }
      //</editor-fold>
    }
                
    System.out.println("EFAToolbarSim.checkToolbarApplicationRunning("+strProcessName+") isRunning?"+ isRunning);    
  }

  private static String getNowDesc(){
    final Calendar c = Calendar.getInstance();

    return
        c.get(Calendar.HOUR_OF_DAY)+":"+
        c.get(Calendar.MINUTE)+":"+
        c.get(Calendar.SECOND)+"."+
        c.get(Calendar.MILLISECOND);
  }
}
