package exp.ftp;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

//import com.enterprisedt.net.ftp.*;

import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class FTPGuiTest
    extends JPanel
    implements Runnable {

//  private String host = "172.18.56.12";
//  private String user = "rvale";
//  private String pass = "rvale";
////  private FTPConnectMode connectMode = FTPConnectMode.PASV;
//  private FTPConnectMode connectMode = FTPConnectMode.ACTIVE;
//  private int timeout = 40000; // 40 seconds (Be aware of the firewall)
//  private String filename = "remote.jar";
//  private String testdir = "temp";
//  private String localBinaryFile = "D:/JBProjects/exp/local.jar";
//
  private boolean tranfering = false;
//
//  private Thread threadThis = new Thread(this);
//
//  private FTPClient ftp = null;
//
//  /**
//   * Create a FTPGuiTest class.
//   */
//  public FTPGuiTest() {
//  }
//
//  /**
//   * Create a FTPGuiTest class and starts the transfer.
//   */
//  public FTPGuiTest(boolean start) {
//    try {
//      transferBinary();
//    } catch (Exception ex) {
//      ex.printStackTrace();
//    }
//  }
//
//  /**
//   * ...
//   */
  public void run() {
    while (tranfering) {
      try {
        Thread.sleep(250);
        System.out.println("                                 ... sleeping ...");
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
  }
//
//  /**
//   *  Connect to the server and setup log stream
//   */
//  protected void connect() throws Exception {
//    // connect
//    ftp = new FTPClient(host, 21, new PrintWriter(System.out), timeout);
//    ftp.debugResponses(true);
//    ftp.setConnectMode(connectMode);
//  }
//
//  /**
//   *  Login to the server
//   */
//  protected void login() throws Exception {
//    ftp.login(user, pass);
//  }
//
//  /**
//   *
//   * @throws Exception
//   */
//  public void transferBinary() throws Exception {
//    try {
//      tranfering = true;
//      threadThis.start();
//
//      connect();
//      login();
//
//      ftp.setTimeout(timeout);
//
//      // monitor transfer progress
//      ftp.setProgressMonitor(new TestProgressMonitor(), 102400 /*100kb*/);
//
//      // move to test directory
//      ftp.chdir(testdir);
//      ftp.setType(FTPTransferType.BINARY);
//
//      ftp.put(localBinaryFile, filename);
//
//      ftp.quit();
//      tranfering = false;
//
//    } catch (Exception e) {
//      tranfering = false;
//      e.printStackTrace();
//    }
//  }
//
//  /**
//   *  Test transfering a text file
//   *
//     public void testTransferText() throws Exception {
//
//      log.println("testTransferText()");
//
//      connect();
//      login();
//
//      // monitor transfer progress
//      ftp.setProgressMonitor(new TestProgressMonitor(), 2048);
//
//      // move to test directory
//      ftp.chdir(testdir);
//      ftp.setType(FTPTransferType.ASCII);
//
//      // put to a random filename
//      String filename = generateRandomFilename();
//      ftp.put(localTextFile, filename);
//
//      // get it back
//      ftp.get(filename, filename);
//
//      // delete remote file
//      ftp.delete(filename);
//      try {
//          ftp.modtime(filename);
//          fail(filename + " should not be found");
//      }
//      catch (FTPException ex) {
//          log.println("Expected exception: " + ex.getMessage());
//      }
//
//      // check equality of local files
//      assertIdentical(localTextFile, filename);
//
//      // and delete local file
//      File local = new File(filename);
//      local.delete();
//
//      ftp.quit();
//     }
//   /**/
//
//   public static void main(String[] args) {
//     final FTPGuiTest FTPGuiTest1 = new FTPGuiTest();
//     JButton b = new JButton("Click me");
//     FTPGuiTest1.setLayout(new BorderLayout());
//     FTPGuiTest1.add(b, BorderLayout.CENTER);
//     b.addActionListener(new ActionListener() {
//       public void actionPerformed(ActionEvent e) {
//         SwingUtilities.invokeLater(new Runnable() {
//           public void run() {
//             try {
//               FTPGuiTest1.transferBinary();
//             } catch (Exception ex) {
//               ex.printStackTrace();
//             }
//           }
//         });
//
//       }
//     });
//
//     JFrame f = new JFrame();
//     f.getContentPane().setLayout(new BorderLayout());
//     f.getContentPane().add(FTPGuiTest1, BorderLayout.CENTER);
//     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//     f.pack();
//     f.setVisible(true);
//
//   }
//
//  /**
//   *  Test of progress monitor functionality
//   */
//  class TestProgressMonitor
//      implements FTPProgressMonitor {
//
//    /* (non-Javadoc)
//     * @see com.enterprisedt.net.ftp.FTPProgressMonitor#bytesTransferred(long)
//     */
//    public void bytesTransferred(long count) {
//      System.out.println(count + " bytes transferred");
//    }
//  }

}
