/**
 * <p>
 * Classname: exp.svn.TestingSVNExecution
 * </p>
 *
 * <p>Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
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

package exp.svn;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Feb 15, 2016, 4:03:26 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class TestingSVNExecution {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(TestingSVNExecution.class.getName());


 /**
  * The TestingSVNExecution default constructor.
  */
  private TestingSVNExecution(){
  }

  private void run(){

    try {
//      final String sMF = "D:\\Projects\\Avermedia\\Projects\\Avermedia\\trunk\\src\\META-INF\\MANIFEST.MF";
//
//      final File fileStartDir =
//          //new File("C:\\Progra~1\\TortoiseSVN\\bin");
//          new File("D:\\Projects\\Avermedia\\Projects\\Avermedia\\trunk");
//
//      final String[] strCommand = {
//        "C:\\Progra~1\\TortoiseSVN\\bin\\svn.exe info"/*,
//        fileStartDir.getAbsolutePath() +
//        "\\src\\"+
//         sMF*/
//      };
//
//
//      System.out.println("StartDir: "+fileStartDir.getAbsoluteFile().getAbsolutePath());
//      final String strCmm =
//          "\"C:\\Program Files\\TortoiseSVN\\bin\\svn.exe\" info " +
//          fileStartDir.getAbsolutePath() + "\\src\\" +
//            this.getBuildConfig().getClassWhereToUpdateVersionInfo().replaceAll("[.]", "\\\\") +
//            ".java";
//      System.out.println("Command: "+strCmm);
//      final ProcessBuilder builder = new ProcessBuilder(strCmm);



      final Process process = Runtime.getRuntime().exec(
          "svn info "+
              "D:\\Projects\\Avermedia\\Projects\\Avermedia\\trunk\\src\\META-INF\\MANIFEST.MF"
      );

      final InputStream stderr = process.getErrorStream();
      final InputStream stdout = process.getInputStream();

      final BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
      final BufferedReader readerErr = new BufferedReader(new InputStreamReader(stderr));


      final Thread threadReadOut = new Thread(() -> {
        String line;

        try {
          while ((line = reader.readLine()) != null) {
            System.out.println(line);
          }
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }, "threadReadOut");

      final Thread threadReadErr = new Thread(() -> {
        String line;

        try {
          while ((line = readerErr.readLine()) != null) {
            System.out.println("Stderr: " + line);
          }
        } catch (IOException iOException) {
          iOException.printStackTrace();
        }
      }, "threadReadErr");

      threadReadOut.start();
      threadReadErr.start();



      process.waitFor();


    }catch(Exception e){
      e.printStackTrace();
    }

  }


  private void runProcess(){

    try {
      final String sMF = "D:\\Projects\\Avermedia\\Projects\\Avermedia\\trunk\\src\\META-INF\\MANIFEST.MF";

      final File fileStartDir =
          //new File("C:\\Progra~1\\TortoiseSVN\\bin");
          new File("D:\\Projects\\Avermedia\\Projects\\Avermedia\\trunk");

      final String[] strCommand = {
        "C:\\Progra~1\\TortoiseSVN\\bin\\svn.exe info"/*,
        fileStartDir.getAbsolutePath() +
        "\\src\\"+
         sMF*/
      };


//      System.out.println("StartDir: "+fileStartDir.getAbsoluteFile().getAbsolutePath());
//      final String strCmm =
//          "\"C:\\Program Files\\TortoiseSVN\\bin\\svn.exe\" info " +
//          fileStartDir.getAbsolutePath() + "\\src\\" +
//            this.getBuildConfig().getClassWhereToUpdateVersionInfo().replaceAll("[.]", "\\\\") +
//            ".java";
//      System.out.println("Command: "+strCmm);
//      final ProcessBuilder builder = new ProcessBuilder(strCmm);


      final ProcessBuilder builder = new ProcessBuilder(strCommand).directory(fileStartDir.getAbsoluteFile());
      final Process process = builder.start();

      final InputStream stderr = process.getErrorStream();
      final InputStream stdout = process.getInputStream();

      final BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
      final BufferedReader readerErr = new BufferedReader(new InputStreamReader(stderr));


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

      threadRead.start();
      threadReadErr.start();



      process.waitFor();


    }catch(Exception e){
      e.printStackTrace();
    }

  }



  public static void main(final String[] args){
    final TestingSVNExecution clazz = new TestingSVNExecution();
    clazz.run();
  }
}
