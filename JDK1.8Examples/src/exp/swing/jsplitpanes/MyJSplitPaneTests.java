package exp.swing.jsplitpanes;


import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;


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
public class MyJSplitPaneTests extends JPanel{

  JSplitPane jSplitPane1 = new JSplitPane();
  BorderLayout borderLayout1 = new BorderLayout();
  JButton jbTop = new JButton("TOP");
  JButton jbBottom = new JButton("BOTTOM");

  /**
   *
   */
  public MyJSplitPaneTests() {
    try {
      jbInit();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }


  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
    this.add(jSplitPane1, java.awt.BorderLayout.CENTER);

    jSplitPane1.add(jbBottom, JSplitPane.BOTTOM);
    jSplitPane1.add(jbTop, JSplitPane.TOP);

    jbBottom.setPreferredSize(new Dimension(200,420));
    jbTop.setPreferredSize(new Dimension(200,150));

    this.setPreferredSize(new Dimension(200,450));

    new Thread(new Runnable() {
      public void run() {
        try {
          while(true){
            Thread.sleep(4000);
System.out.println("DividerLocation="+jSplitPane1.getDividerLocation()+".");
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }).start();


  }

  public static void main(final String[] args) {
    MyJSplitPaneTests m = new MyJSplitPaneTests();
    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(m);
    f.pack();
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
