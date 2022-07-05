package exp.runtime.runprocess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Base64;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


/**
 * <p>Title: </p>
 *
 * <p>
 * Links:
 *    http://blogs.warwick.ac.uk/kieranshaw/entry/utf-8_internationalisation_with/
 *
 * </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class RunTestProcess {

  private static final String STR_JAVAW_PATH = "C:\\JDK\\jdk1.8.0_112\\bin\\javaw";

  private static final String STR_PARAM_APP = "-app";
  private static final String STR_PARAM_DIR = "-dir";
  private static final String STR_PARAM_FILE = "-file";
  private static final String STR_PARAM_TIMEOUT = "-timeout";

  private static String strRunApp;
  private static String strConfigFile = "config.dat";
  private static String strWorkingDir = ".\\";

  private static final Object OBJ = new Object();






  /**
   * Params:
   *    -app APP
   *    -dir DIR [defaults to .]
   *    -file CONFIG_FILE [defaults to config.dat]
   *    -timeout TIMEOUT [defaults to 60]
   *
   * @param args
   * @throws Exception
   */
  public static void main_(String[] args) throws Exception {

    if(args == null || args.length < 2){
      System.out.println(
          "\nError. Cannot obtain the APP to execute.\n"+
          "Usage:\n-app APP [-dir working_dir] [-file CONFIG_FILE] [-timeout TIMEOUT]\n\n");
      System.exit(1);
    }



    Thread threadFileContentsCheck = null;
    Calendar calNow;
    int _iMinutes = 60 * 5; // 5 hours



    for (int i = 0; i < args.length; i++) {
      switch ( args[i] ) {
        case STR_PARAM_DIR:
          strWorkingDir = args[i + 1];
          break;

        case STR_PARAM_FILE:
          strConfigFile = args[i + 1];
          break;

        case STR_PARAM_APP:
          strRunApp = args[i + 1];
          break;

        case STR_PARAM_TIMEOUT:
          try {
            _iMinutes = Integer.parseInt(args[i + 1]);
          } catch (NumberFormatException ex) {
            _iMinutes = 60 * 5;
          }
          break;
      }

      i++;
    }


    final int iMinutes = _iMinutes;
    final long wait = 1000 /*millis*/ * 60 /*secs*/ * iMinutes;


    while(true){

      System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");

//      if(args.length >= 1){

        // for each line in the file, execute the AvlsApp...
        try (Stream<String> stream = Files.lines(Paths.get(strConfigFile))) {
          stream.forEach(strLine -> {
            if(!strLine.isEmpty()){
              final String[] strArgs = strLine.split(" ");
              RunTestProcess.run(strWorkingDir, strRunApp, iMinutes/2, strArgs);
            }
          });

		} catch (IOException e) {
          e.printStackTrace();

          System.out.println("If -file is given, a VALID file must be sent...");
          System.out.println("PRESS ENTER TO EXIT...");
          System.in.read();
          System.exit(1);
		}


//      }else{
//        final int nArgs = args.length;
//
//        for (int i=1; i< nArgs; i++){
//          try {
//            System.out.println("\n\n--------------------------------------------------------------------------------------");
//            System.out.println("Will execute: "+args[i] + "\\" + args[i+1]);
//
//            RunTestProcess.run(args[i], args[i+1], iMinutes/2);
//
//            i++;
//
//          } catch (Exception e) {
//            e.printStackTrace();
//          }
//        }
//      }


      System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

      calNow = Calendar.getInstance();
      System.out.println("\nDone @ "+
            calNow.get(Calendar.YEAR)+"\\"+
            (calNow.get(Calendar.MONTH) + 1 )+"\\"+
            calNow.get(Calendar.DAY_OF_MONTH)+" "+
            calNow.get(Calendar.HOUR_OF_DAY)+":"+
            calNow.get(Calendar.MINUTE)+":"+
            calNow.get(Calendar.SECOND));


      if(strConfigFile != null){
        threadFileContentsCheck = new Thread(() -> {
          try {
            Thread.sleep(1500);

            final String strConfigFileAbsPath = Paths.get(strConfigFile).toFile().getAbsolutePath();
            final String strDirToMonitor = new File(strConfigFileAbsPath).getParent();
            final WatchService service = FileSystems.getDefault().newWatchService();
            final Path path = Paths.get(strDirToMonitor);	// Get the directory to be monitored
            path.register(service,
                //StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY
                //,StandardWatchEventKinds.ENTRY_DELETE
            );

            while (true) {
              System.out.println("Will listen for "+strConfigFile+" file changes...");
              final WatchKey key = service.take();	// retrieve the watchkey

              for (WatchEvent event : key.pollEvents()) {
                if(event.context().toString().equals(strConfigFile)){
                  System.out.println("Changes made to the "+strConfigFile+" file. Waking up...");

                  synchronized(OBJ){
                    OBJ.notifyAll();
                  }
                }
              }

              boolean valid = key.reset();

              if (!valid) {
                break;	// Exit if directory is deleted
              }

              Thread.sleep(1500);
            }

          } catch (Exception exc) {
            //exc.printStackTrace();
          }

          System.out.println("The listening thread finished...");

        }, "threadFileContentsCheck");
      }

      synchronized(OBJ){
        System.out.println("Will wait for "+(wait/1000)+" secs...");

        if(threadFileContentsCheck != null){
           threadFileContentsCheck.start();
        }

        OBJ.wait(wait);

        if(threadFileContentsCheck != null){
          threadFileContentsCheck.interrupt();
        }

        System.out.println("...waking up.\n\n");
      }

      System.out.println("-----------------------------------------------------------------------------------------------------------------------------\n\n");

    }
  }

  public static void main(String[] args) throws Exception {

    //System.setProperty("file.encoding", "utf-8");


//    runJava();
//    //runINOSS(true);
//    //runCSharp();
    runBat();
  }




  public static void runBat(){
    try {
      final File fileStartDir = new File("D:\\Projects\\exp");
      final String[] strCommand = {
        fileStartDir.getAbsolutePath() + "/runVLC.bat"
        //, "C:\\Program Files (x86)\\VideoLAN\\VLC"
        , "vlc://pause:5"
        , "vlc://quit"
      };

      if("true".equals(System.getProperty("sdkjskdjkjsk"))){
        System.out.println("Will try to destroy the SaveLayout process.");
      }else{
        System.out.println("THREE'S NO sdkjskdjkjsk VM prop.");
      }


      final ProcessBuilder builder = new ProcessBuilder(strCommand).directory(fileStartDir);
      final Process process = builder.start();

      final OutputStream stdin = process.getOutputStream();
      final InputStream stderr = process.getErrorStream();
      final InputStream stdout = process.getInputStream();
      final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
      final BufferedReader readerErr = new BufferedReader(new InputStreamReader(stderr));
      final BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));


      //<editor-fold defaultstate="collapsed" desc="STDIO">
      final Thread threadRead = new Thread(() -> {
        String line;

        try {
          while ((line = reader.readLine()) != null) {
            System.out.println("Stdout: " + line);
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
            System.out.println("Stderr: " + line);
          }
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }, "ThreadName");
      threadReadErr.start();
      //</editor-fold>


      System.out.println("Waiting for the process to terminate ...");
      process.waitFor();
      System.out.println("... terminated.");
      System.out.println("Exit code="+process.exitValue()+".");

      process.destroy();

      //try {Thread.sleep(2387); } catch (InterruptedException interruptedException) { }



    } catch (Exception e) {
      e.printStackTrace();
    }

  }



  public static void runINOSS(final boolean forceProcessKill) {

    final String strASCII = "ascii";
    final Charset charSetUTF8 = Charset.forName("UTF-8");
    final String strUTF8 = "utf8";
    final String strUTF_8 = "UTF-8";


    try {
      /*************************************************************/
      final ResourceBundle rb = ResourceBundle.getBundle("exp.runtime.runprocess.sample", Locale.CHINESE);
      final String strUser = rb.getString("user");
      final String strPass = rb.getString("pass");

      final String strUsernameEncoded = java.net.URLEncoder.encode(strUser, strUTF_8);
      final String strPasswordEncoded = java.net.URLEncoder.encode(strPass, strUTF_8);

      final String strStartingDir = "D:\\Projects\\TLC\\Projects\\TLC\\trunk\\tests\\TLC-CRRC";
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
        ".;"+
          "D:\\Projects\\TLC\\Projects\\TLC\\trunk\\build\\classes;"+
          "D:\\Projects\\GISTV\\Projects\\GISTV\\trunk\\build\\classes;"+
          "D:\\Projects\\ERCOM\\Projects\\ERCOM\\trunk\\build\\classes;"+
          "D:\\Projects\\TLC\\Projects\\TLC\\trunk\\tests\\TLC-CRRC\\lib\\*",

        "pt.efacec.se.inoss.tlc.PInt",

        //"-password=rgv",
        //"-username=inoss",

        "-username=" + strUsernameEncoded,

        //"-lang=nb-NO",
        "-lang=en-GB",

        "-font=SimSun",

        "-encoding="+strUTF8
      };



      {
        javax.swing.JFrame f = new javax.swing.JFrame("RunTestProcess starting INOSS");
        javax.swing.JLabel l = new javax.swing.JLabel();
        l.setFont(new java.awt.Font("SimSun", java.awt.Font.PLAIN, 10));
        l.setText("UsernameEncoded:"+strUsernameEncoded /*+ " PasswordEncoded:"+strPasswordEncoded*/);
        f.setLayout(new java.awt.BorderLayout());
        f.add(l, java.awt.BorderLayout.NORTH);
        javax.swing.JList<String> l2 = new javax.swing.JList<>();
        l2.setFont(new java.awt.Font("SimSun", java.awt.Font.PLAIN, 10));

//        for(String s: strCommand){
//          if(s.startsWith("-user") || s.startsWith("-p@ss")){
//            String strS = new String(s.getBytes(), Charset.forName("UTF-8"));
//            l2.add(new javax.swing.JLabel("s:"+s));
//            l2.add(new javax.swing.JLabel("    strS:"+strS));
//          }
//        }

        f.add(l2, java.awt.BorderLayout.CENTER);
        f.setBounds(700, 550, 550, 325);
        f.setVisible(true);
      }




      //;D:\\Projects\\TLC\\Projects\\TLC\\trunk\\tests\\TLC-MdB\\lib\\jacorb.properties
      final ProcessBuilder builder = new ProcessBuilder(strCommand).directory(fileInossDir);
      //builder.redirectErrorStream(true); // the out & err streams become ONE
      final Process process = builder.start();
      final OutputStream stdin = process.getOutputStream();
      final InputStream stderr = process.getErrorStream();
      final InputStream stdout = process.getInputStream();


      final BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, charSetUTF8));
      final BufferedReader readerErr = new BufferedReader(new InputStreamReader(stderr, charSetUTF8));
      final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin, charSetUTF8));
      /**/



      final Thread threadRead = new Thread(() -> {
        String line;

        try {
          while ((line = reader.readLine()) != null) {
            System.out.println((System.currentTimeMillis()/1000)+ " - Stdout: " + line);
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
            System.out.println((System.currentTimeMillis()/1000)+ " - Stderr: " + line);
          }
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }, "ThreadName");
      threadReadErr.start();

      final Thread threadWrite = new Thread(() -> {
        try {
          Thread.sleep(5000);







          javax.swing.JFrame f = new javax.swing.JFrame("RunTestProcess sending pass 2 STDIO");
          javax.swing.JLabel l = new javax.swing.JLabel();
          l.setFont(new java.awt.Font("SimSun", java.awt.Font.PLAIN, 10));
          l.setText("Pass:"+strPass +" PassEncodec:"+strPasswordEncoded);
          //l.setText("UsernameEncoded:"+strUsernameEncoded + " PasswordEncoded:"+strPasswordEncoded);
          f.setLayout(new java.awt.BorderLayout());
          f.add(l);
          f.setBounds(700,120,550,100);
          f.setVisible(true);

          //final String
          final String strAppPasswordWithValue = "APP_PASSWORD="+
              //strPass;
              strPasswordEncoded;



          System.out.println((System.currentTimeMillis()/1000)+" - Writing to INOSS: "+strAppPasswordWithValue);
          writer.write(strAppPasswordWithValue+"\n");
          writer.flush();


          Thread.sleep(45000);
          System.out.println((System.currentTimeMillis()/1000)+" - Writing to INOSS: \"APP_EXIT\"");
          writer.write("APP_EXIT\n");
          writer.flush();


          final long lTime2Wait = 30000;
          System.out.println((System.currentTimeMillis()/1000)+" - Will wait for "+lTime2Wait+" millis for the APP to end...");
          process.waitFor(lTime2Wait, TimeUnit.MILLISECONDS);

          if(process.isAlive()){
            System.out.println((System.currentTimeMillis()/1000)+" - Since the APP is still running, we destroy it forcibly...");
            process.destroyForcibly();
          }

        } catch (Exception exception) {
          exception.printStackTrace();
        }
      }, "ThreadName");

      if(forceProcessKill){
        threadWrite.start();
      }





      System.out.println((System.currentTimeMillis()/1000)+ " - Waiting for the process to terminate ...");
      process.waitFor();
      System.out.println("... terminated.");
      System.out.println((System.currentTimeMillis()/1000)+ " - Exit code="+process.exitValue()+".");

      try {Thread.sleep(1087); } catch (InterruptedException interruptedException) { }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private static void runJava() {
    try {
      /*************************************************************/
      final Charset charSetUTF8 = Charset.forName("UTF-8");
      final String strUser =
          ResourceBundle.getBundle("exp.runtime.runprocess.sample", Locale.CHINESE).getString("user");
      final String strPass =
          ResourceBundle.getBundle("exp.runtime.runprocess.sample", Locale.CHINESE).getString("pass");


      final String strEncodedUser =
          //new String(strUser.getBytes(charSetUTF8));
          java.net.URLEncoder.encode(strUser,"UTF-8");
      final String strUserNameInfo =
          "-username="+strUser;
          //new String(("-username="+strUserUTF).getBytes(), charSetUTF8);


      final File fileInossDir = new File(".");
      String[] strCommand = {
        STR_JAVAW_PATH,
        //"-Dfile.encoding=utf-8",
        "-Xms16m",
        "-Xmx32m",
        "-Demo",
        "-Dinit=true",
        "-classpath",
        ".\\build\\classes",
        "exp.runtime.runprocess.ShowSomeDialog",
        "-encodedUser=UserEncoded",
        "-encodedUser="+strEncodedUser,
        "-p@ss="+strPass,
        strUserNameInfo,
        //"strUser(Charset...encode):" + new String(Charset.forName("UTF-8").encode(strUser).array()),
        //"strUser(Base64...encode):" + Base64.getEncoder().encodeToString(strUser.getBytes("UTF-8")),
      };


      {
        javax.swing.JFrame f = new javax.swing.JFrame("RunTestProcess");
        javax.swing.JLabel l = new javax.swing.JLabel();
        l.setFont(new java.awt.Font("SimSun", java.awt.Font.PLAIN, 10));
        l.setText("user:"+strUser+" encodedUser:"+strEncodedUser+" userNameInfo:"+strUserNameInfo);
        f.setLayout(new java.awt.BorderLayout());
        f.add(l, java.awt.BorderLayout.NORTH);
        javax.swing.JTextArea l2 = new javax.swing.JTextArea();
        l2.setFont(new java.awt.Font("SimSun", java.awt.Font.PLAIN, 10));

        l2.append("-------------------------------------------------------------\n");
        for(String s: strCommand){
          //if(s.startsWith("-user") || s.startsWith("-p@ss")){
            String strS = new String(s.getBytes(), Charset.forName("UTF-8"));
            l2.append("s:"+s);
            l2.append("    strS:"+strS);
            l2.append("\n");
          //}
        }

        f.add(l2, java.awt.BorderLayout.CENTER);
        f.setBounds(700, 50, 550, 325);
        f.setVisible(true);
      }



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
            System.out.println("Stdout: " + line);
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
            System.out.println("Stderr: " + line);
          }
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }, "ThreadName");
      threadReadErr.start();

      final Thread threadWrite = new Thread(() -> {
        try {
          Thread.sleep(1600);
          System.out.println("Writing to PIntInfo: \"This is the EFAToolbar. DO YOU REACH?\"");
          writer.write("This is the EFAToolbar. DO YOU REACH?");
          writer.flush();
          //writer.close();
          Thread.sleep(870);
          System.out.println("Writing to PIntInfo: \"END\"");
          writer.write("END");
          writer.flush();

          final long lTime2Wait = 2500;
          System.out.println("Will wait for "+lTime2Wait+" millis for the APP to end...");
          process.waitFor(lTime2Wait, TimeUnit.MILLISECONDS);

          if(process.isAlive()){
            System.out.println("Since the APP is still running, we destroy it forcibly...");
            process.destroyForcibly();
          }

        } catch (Exception exception) {
          exception.printStackTrace();
        }
      }, "ThreadName");
//      threadWrite.start();





      System.out.println("Process "+process.toString());

//      final Thread thread = new Thread(new Runnable() {
//        @Override
//        public void run() {
//          try {Thread.sleep(1400); } catch (InterruptedException interruptedException) { }
//
//          while (true) {
//            try {Thread.sleep(1687); } catch (InterruptedException interruptedException) { }
//            final boolean isAlive = process.isAlive();
//            System.out.println("isAlive?" + isAlive);
//
//            if(!isAlive){
//              break;
//            }
//          }
//        }
//      }, "ThreadName");
//      thread.start();

//      final Thread threadD = new Thread(new Runnable() {
//        @Override
//        public void run() {
//          try {Thread.sleep(6700); } catch (InterruptedException interruptedException) { }
//          System.out.println("process.destroyForcibly()...");
//          try {Thread.sleep(1000); } catch (InterruptedException interruptedException) { }
//          //process.destroy();
//          final Process p = process.destroyForcibly();
//          System.out.println("After destroy forcibly " + p.toString());
//        }
//      }, "ThreadName");
//      threadD.start();


      System.out.println("Waiting for the process to terminate ...");
      process.waitFor();
      System.out.println("... terminated.");
      System.out.println("Exit code="+process.exitValue()+".");

      try {Thread.sleep(4087); } catch (InterruptedException interruptedException) { }

      System.exit(0);

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private static void runCSharp() {
    try {
      /*************************************************************/

      final Charset charSetUTF8 = Charset.forName("UTF-8");
      final String strUser =
          ResourceBundle.getBundle("exp.runtime.runprocess.sample", Locale.CHINESE).getString("user");
      final String strPass =
          ResourceBundle.getBundle("exp.runtime.runprocess.sample", Locale.CHINESE).getString("pass");

      final String strUserUTF = new String(strUser.getBytes(), charSetUTF8);
      final String strUserNameInfo =
          "-username="+strUser;
      final String strEncodedUser =
          //new String(strUser.getBytes(charSetUTF8));
          java.net.URLEncoder.encode(strUser,"UTF-8");


      final File fileStartDir = new File("D:/CSharpPjr/exp/RenamePhotos/RenamePhotos/bin/Debug");
      String[] strCommand = {
        fileStartDir.getAbsolutePath()+ "/RenamePhotos.exe",
        //"-mayExit=true",
        //"-efarailExitTag=EFARAIL_EXIT$2983267ER#$%&=#&$23",
        //"-displayBounds=1921x0x1920x1080",
        //"-font='Lucida Sans',PLAIN,10",
        //"-font=Dialog,PLAIN,10",
        strUserNameInfo,
        "-userUTF="+strUserUTF,
        "-userPass="+strPass,
        "-encodedUser="+strEncodedUser,
      };




      {
        javax.swing.JFrame f = new javax.swing.JFrame("RunTestProcess");
        javax.swing.JLabel l = new javax.swing.JLabel();
        l.setFont(new java.awt.Font("SimSun", java.awt.Font.PLAIN, 10));
        l.setText("user:"+strUser+" encodedUser:"+strEncodedUser+" userNameInfo:"+strUserNameInfo);
        f.setLayout(new java.awt.BorderLayout());
        f.add(l, java.awt.BorderLayout.NORTH);
        javax.swing.JTextArea l2 = new javax.swing.JTextArea();
        l2.setFont(new java.awt.Font("SimSun", java.awt.Font.PLAIN, 10));

        l2.append("-------------------------------------------------------------\n");
        for(String s: strCommand){
          //if(s.startsWith("-user") || s.startsWith("-p@ss")){
            String strS = new String(s.getBytes(), Charset.forName("UTF-8"));
            l2.append("s:"+s);
            l2.append("    strS:"+strS);
            l2.append("\n");
          //}
        }

        f.add(l2, java.awt.BorderLayout.CENTER);
        f.setBounds(700, 50, 550, 325);
        f.setVisible(true);
      }





      final ProcessBuilder builder = new ProcessBuilder(strCommand).directory(fileStartDir);
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
            System.out.println("Stdout: " + line);
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
            System.out.println("Stderr: " + line);
          }
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }, "ThreadName");
      threadReadErr.start();


      System.out.println("Press any key to continue and stop the APP...");
      System.in.read();

      final Thread threadWrite = new Thread(() -> {
        try {
          Thread.sleep(100);
          System.out.println("\tWriting to APP: \"This is the EFAToolbar. DO YOU REACH?\"");
          writer.write("This is the EFAToolbar. DO YOU REACH?\n");
          writer.flush();
          //writer.close();
          Thread.sleep(870);
          System.out.println("\tWriting to APP: \"END\"");
          writer.write("END\n");
          writer.flush();
          writer.close();

          final long lTime2Wait = 2500;
          System.out.println("Will wait for "+lTime2Wait+" millis for the APP to end...");
          process.waitFor(lTime2Wait, TimeUnit.MILLISECONDS);

          if(process.isAlive()){
            System.err.println("Since the APP is still running, we destroy it forcibly...");
            process.destroyForcibly();
          }

        } catch (Exception exception) {
          exception.printStackTrace();
        }
      }, "ThreadName");
      threadWrite.start();


//      final Thread threadD = new Thread(new Runnable() {
//        @Override
//        public void run() {
//          try {Thread.sleep(1300); } catch (InterruptedException interruptedException) { }
//          System.out.println("process.destroyForcibly()...");
//          try {Thread.sleep(1000); } catch (InterruptedException interruptedException) { }
//          //process.destroy();
//          final Process p = process.destroyForcibly();
//          System.out.println("After destroy forcibly " + p.toString());
//
//          while (process.isAlive()) {
//            System.out.println("Since the APP is still running, we wait 4 it to terminate...");
//          }
//
//        }
//      }, "ThreadName");
//      threadD.start();

      System.out.println("Waiting for the process to terminate ...");
      process.waitFor();
      System.out.println("... terminated.");
      System.out.println("Exit code="+process.exitValue()+".");

      try {Thread.sleep(2387); } catch (InterruptedException interruptedException) { }

    }catch(Exception e){
      e.printStackTrace();
    }

  }

  private static void runDelphi() {
    try {
      /*************************************************************/

      final File fileStartDir = new File("D:/Delphi/SampleDuarte");
      String[] strCommand = {
        fileStartDir.getAbsolutePath()+ "/Project1.exe"
      };
      final ProcessBuilder builder = new ProcessBuilder(strCommand).directory(fileStartDir);
      //builder.redirectErrorStream(true); // the out & err streams become ONE
      final Process process = builder.start();
      final OutputStream stdin = process.getOutputStream();
      final InputStream stderr = process.getErrorStream();
      final InputStream stdout = process.getInputStream();

      final BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
      final BufferedReader readerErr = new BufferedReader(new InputStreamReader(stderr));
      final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
      /**/


      /**/
      final Thread threadRead = new Thread(() -> {
        String line;

        try {
          while ((line = reader.readLine()) != null) {
            System.out.println("TK Stdout: " + line);
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
            System.out.println("TK Stderr: " + line);
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
          //Thread.sleep(7500);
          final String strPass = "APP_PASSWORD=timekeeper";
          System.out.println("Writing to TK: "+strPass);
          writer.write(strPass+"\n");
          writer.flush();


          Thread.sleep(7000);
          System.out.println("Writing to TK: \"APP_EXIT\"");
          writer.write("APP_EXIT\n");
          writer.flush();


          final long lTime2Wait = 5000;
          System.out.println("Will wait for "+lTime2Wait+" millis for the APP to end...");
          process.waitFor(lTime2Wait, TimeUnit.MILLISECONDS);

          if(process.isAlive()){
            System.out.println("Since the APP is still running, we destroy it forcibly...");
            process.destroyForcibly();
          }

        } catch (Exception exception) {
          exception.printStackTrace();
        }
      }, "ThreadName");
      threadWrite.start();
      /**/






      System.out.println("Waiting for the process to terminate ...");
      process.waitFor();
      System.out.println("... terminated.");
      System.out.println("Exit code="+process.exitValue()+".");

      try {Thread.sleep(1087); } catch (InterruptedException interruptedException) { }

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }


  private static void run(final String strDir, final String strCmd, int lTimeout){
    run(strDir, strCmd, lTimeout, new String[0]);
  }

  private static void run(final String strDir, final String strCmd, int lTimeout, String[] strArgs){

    try {
      /*************************************************************/
      final File fileStartDir = new File(strDir);

      final String[] strCommand = new String[strArgs.length + 1];
      System.arraycopy(strArgs, 0, strCommand, 1, strArgs.length);
      strCommand[0] = fileStartDir.getAbsolutePath()+ "/"+strCmd;


      final ProcessBuilder builder = new ProcessBuilder(strCommand).directory(fileStartDir);
      final Process process = builder.start();
      final InputStream stdio = process.getInputStream();
      final InputStream stderr = process.getErrorStream();

      final BufferedReader readerErr = new BufferedReader(new InputStreamReader(stderr));
      final BufferedReader reader = new BufferedReader(new InputStreamReader(stdio));
      /**/


      final Thread threadRead = new Thread(() -> {
        String line;

        try {
          while ((line = reader.readLine()) != null) {
            System.out.println(strCmd + ": "+ line);
          }

        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }, "threadRead");
      threadRead.start();

      final Thread threadReadErr = new Thread(() -> {
        String line;

        try {
          while ((line = readerErr.readLine()) != null) {
            System.out.println(strCmd + "."+ "stderr: " + line);
          }
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }, "threadReadErr");
      threadReadErr.start();


      System.out.println("Waiting for the process to terminate ...");
      process.waitFor(Math.min(lTimeout, 5), TimeUnit.MINUTES);

      if(process.isAlive()){
        System.out.println("Since the process is still running, destroy it!");
        process.destroy();
      }

      System.out.println("... terminated.");
      System.out.println("Exit code="+process.exitValue()+".");


    }catch(Exception e){
      e.printStackTrace();
    }
  }


}
