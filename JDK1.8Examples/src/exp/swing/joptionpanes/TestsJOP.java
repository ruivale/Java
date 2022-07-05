package exp.swing.joptionpanes;

import javax.swing.*;
/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class TestsJOP {

    private static final JOptionPane jOPNS = new JOptionPane();
    private static final JOptionPane jOPES = new JOptionPane();
    private static final JOptionPane jOPSS = new JOptionPane();


  /**
   * 
   * @param msg
   */
  public TestsJOP(String msg, JOptionPane jOP) {
//    //int yes = javax.swing.JOptionPane.showConfirmDialog(
//      //  com.ent.PInt.windows.FaultApplication.getParent(),
//        //"akjksjdkjskdjsk");
//    int yes = JOptionPane.showOptionDialog(
//        null,//com.ent.PInt.windows.FaultApplication.getParent(),
//        "message",
//        "title",
//        JOptionPane.OK_OPTION,
//        JOptionPane.QUESTION_MESSAGE,
//        null,
//        null,//new Object[]{"ok", "não"},
//        null);
//
//    System.out.println("yes="+yes+".");
//    System.exit(0);


    jOP.showMessageDialog(null, msg, "title", JOptionPane.ERROR_MESSAGE);
    System.out.println("exiting "+ msg);
  }


  public static void main(String[] s){
    new Thread(new Runnable() {
      public void run() {
        SwingUtilities.invokeLater(new Runnable() {

          public void run() {
            System.out.println("jOPNS:"+jOPNS.isVisible()+" jOPES:"+jOPES.isVisible()+" jOPSS:"+jOPSS.isVisible());

            new TestsJOP("msg 1", jOPNS);
          }
        });
      }
    }).start();
    new Thread(new Runnable() {
      public void run() {
        SwingUtilities.invokeLater(new Runnable() {

          public void run() {
            System.out.println("jOPNS:"+jOPNS.isVisible()+" jOPES:"+jOPES.isVisible()+" jOPSS:"+jOPSS.isVisible());
            new TestsJOP("msg 2", jOPES);
          }
        });
      }
    }).start();
    new Thread(new Runnable() {
      public void run() {
        SwingUtilities.invokeLater(new Runnable() {

          public void run() {
            System.out.println("jOPNS:"+jOPNS.isVisible()+" jOPES:"+jOPES.isVisible()+" jOPSS:"+jOPSS.isVisible());
            new TestsJOP("msg 3", jOPSS);
          }
        });
      }
    }).start();

    new Thread(new Runnable() {
      public void run() {
        SwingUtilities.invokeLater(new Runnable() {

          public void run() {
            try {
              jOPNS.setVisible(false);
              Thread.sleep(1200);
              jOPES.setVisible(false);
              Thread.sleep(1200);
              jOPSS.setVisible(false);

            } catch (Exception e) {
            }
          }
        });
      }
    }).start();

  }
}
