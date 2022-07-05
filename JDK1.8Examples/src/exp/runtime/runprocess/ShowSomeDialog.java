/**
 * <p>
 * Classname: exp.runtime.runprocess.ShowSomeDialog
 * </p>
 *
 * <p>Copyright: Copyright (c) 2014 Efacec Engenharia e Sistemas, S.A.
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

package exp.runtime.runprocess;


import java.awt.Font;
import java.awt.HeadlessException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Properties;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Nov 6, 2014, 2:36:49 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class ShowSomeDialog {

  /** This class CVS version */
  private static final String S_THIS_CLASS_VERSION = "1.1.4.63";

  public static void main(final String[] args) {



    javax.swing.SwingUtilities.invokeLater(() -> {
      try {
        //final Charset charSetUTF8 = Charset.forName("UTF-8");
        javax.swing.JFrame f = new javax.swing.JFrame("ShowSomeDialog");
        javax.swing.JLabel l = new javax.swing.JLabel();
        l.setFont(new java.awt.Font("SimSun", Font.PLAIN, 10));
        l.setText("ShowSomeDialog args:" + Arrays.toString(args));
        f.setLayout(new java.awt.BorderLayout());
        f.add(l, java.awt.BorderLayout.NORTH);
        javax.swing.JTextArea l2 = new javax.swing.JTextArea();
        l2.setFont(new java.awt.Font("SimSun", java.awt.Font.PLAIN, 10));

        l2.append("-------------------------------------------------------------\n");
        for (String s : args) {
          //if (s.startsWith("-user") || s.startsWith("-p@ss")) {
            //String strS = new String(s.getBytes(), Charset.forName("UTF-8"));
            l2.append("original:" + s);
            l2.append("\n\tDecoded(UTF-8):" + java.net.URLDecoder.decode(s,"UTF-8"));
            l2.append("\n\tUTF-8:" + new String(s.getBytes(),"UTF-8"));

//            String strBytes = "";
//            if (s.startsWith("-encodedUser=")) {
//              strBytes = s.substring(s.indexOf('=') + 1, s.length());
//            }
//
//            l2.append(" strBytes(" + strBytes + "): "+
//                      java.net.URLDecoder.decode(strBytes,"UTF-8")
//                      //new String(strBytes.getBytes())+" w/Encoding: " +
//                      //new String(strBytes.getBytes(charSetUTF8)));
//                      );

            l2.append("\n");
          //}
        }

        f.add(l2, java.awt.BorderLayout.CENTER);
        f.setBounds(700, 550, 550, 325);
        f.setVisible(true);

      } catch (Exception e) {
        e.printStackTrace();
      }
    });





//
//    try {
//
//      Runtime.getRuntime().addShutdownHook(new Thread() {
//        @Override
//        public void run() {
//          System.out.println("ShowSomeDialog -> Preparing to leave...");
//          try {
//            Runtime.getRuntime().exec("calc");
//
//            Thread.sleep(3500);
//
//          } catch (Exception e) {
//            e.printStackTrace();
//          }
//          System.out.println("ShowSomeDialog -> l  e  a      v        i           n            g...");
//        }
//      });
//
//      final Thread threadSTDIN = new Thread(() -> {
//        try {
//          System.out.println("ShowSomeDialog listening in the in...");
//          byte[] b = new byte[1024];
//
//          while (true) {
//            System.in.read(b);
//            final String s = new String(b);
//
//            if (s.trim().equalsIgnoreCase("END")) {
//              System.out.println("ShowSomeDialog just obtained from the IS: " + s.trim());
//
//
//              //Runtime.getRuntime().halt(0);
//              System.exit(0);
//
//
//            }
//            b = new byte[1024];
//            Thread.sleep(100);
//          }
//        } catch (Exception e) {
//          e.printStackTrace();
//        }
//      }, "ThreadSTVIN");
//      threadSTDIN.start();

      System.out.println("\n\n-----------------------------------------------------------");
      System.out.println("ShowSomeDialog args: ");
      for(String s: args){
        System.out.print(s+", ");
      }
//      System.out.println("\nVM vars: ");
//      final Properties props = System.getProperties();
//      for (String strPropName : props.stringPropertyNames()) {
//        System.out.println("\t"+strPropName+"="+System.getProperty(strPropName));
//      }
//      System.out.println("\n-----------------------------------------------------------\n\n");

//      ShowSomeDialog.showVersion();
//
//      System.out.println("ShowSomeDialog PInt Module version (official): " + S_THIS_CLASS_VERSION + "  ");
//      System.err.println("ShowSomeDialog ERROR ERROR ERROR ERROR ERROR ERROR ERROR ");
//
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
  }

  private static void showVersion(){
    final javax.swing.JFrame frame = new javax.swing.JFrame(" P I n t    M o d u l e ");
    final java.awt.Container contentPane = frame.getContentPane();
    contentPane.setLayout(new java.awt.GridLayout(2, 1));

    contentPane.add(new javax.swing.JLabel("  Version (official): " +S_THIS_CLASS_VERSION + "  "));
    contentPane.add(new javax.swing.JLabel("  Internal main module version: " + S_THIS_CLASS_VERSION+ "  "));
    frame.pack();
    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

    final java.awt.Dimension paneSize = frame.getSize();
    final java.awt.Dimension screenSize = frame.getToolkit().getScreenSize();

    frame.setLocation((screenSize.width - paneSize.width) / 2, (screenSize.height - paneSize.height) / 2);

    frame.setVisible(true);

  }
}
