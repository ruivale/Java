package exp.swing.jsplitpanes;

import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
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
public class JSplitInsideJSplit extends JPanel{

  public JSplitInsideJSplit() {
    final JSplitPane jSplitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    final JSplitPane jSplitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    BorderLayout borderLayout1 = new BorderLayout();

    this.setLayout(borderLayout1);
    this.add(jSplitPane1, java.awt.BorderLayout.CENTER);

    jSplitPane1.add(new JButton("TOP"), JSplitPane.TOP);
    jSplitPane1.add(jSplitPane2, JSplitPane.BOTTOM);

    jSplitPane2.add(new JButton("TOP"), JSplitPane.TOP);
    jSplitPane2.add(new JButton("BOTTOM"), JSplitPane.BOTTOM);

    this.setPreferredSize(new Dimension(200,450));

    new Thread(new Runnable() {
      public void run() {
        try {
          while(true){
            Thread.sleep(4000);
System.out.println("DividerLocation 1="+jSplitPane1.getDividerLocation()+".");
System.out.println("DividerLocation 2="+jSplitPane2.getDividerLocation()+".");
          }
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }).start();


  }



  public static void main(final String[] args) {
    JSplitInsideJSplit m = new JSplitInsideJSplit();
    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(m);
    f.pack();
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

}
