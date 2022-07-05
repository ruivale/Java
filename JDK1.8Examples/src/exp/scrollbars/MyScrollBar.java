package exp.scrollbars;

import javax.swing.*;
import java.awt.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class MyScrollBar extends JPanel {
  JScrollPane jScrollPane1 = new JScrollPane();
  BorderLayout borderLayout1 = new BorderLayout();
  JButton jButton1 = new JButton();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();
  JButton jButton4 = new JButton();
  JButton jButton5 = new JButton();
  JButton jButton6 = new JButton();

  public MyScrollBar() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    MyScrollBar m = new MyScrollBar();
    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(m);
    f.setBounds(100,100,300,150);
    f.setVisible(true);

  }
  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    jButton1.setText("jButton1");
    jPanel1.setLayout(borderLayout2);
    jButton2.setText("jButton2");
    jButton3.setText("jButton3");
    jButton4.setText("jButton4");
    jButton5.setText("jButton5");
    jButton6.setText("jButton6");
    this.add(jScrollPane1,  BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jPanel1, null);
    jPanel1.add(jButton2, BorderLayout.CENTER);
    jPanel1.add(jButton3, BorderLayout.NORTH);
    jPanel1.add(jButton4, BorderLayout.SOUTH);
    jPanel1.add(jButton5, BorderLayout.WEST);
    jPanel1.add(jButton6, BorderLayout.EAST);

    JScrollBar hsb = new JScrollBar(JScrollBar.HORIZONTAL,50,10,0,100);
    JScrollBar vsb = new JScrollBar(JScrollBar.VERTICAL,50,10,0,100);

    hsb.setPreferredSize(new Dimension(300,12));
    vsb.setPreferredSize(new Dimension(12,150));

    this.jScrollPane1.setVerticalScrollBar(vsb);
    this.jScrollPane1.setHorizontalScrollBar(hsb);
    this.jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    this.jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


  }
}