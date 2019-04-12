package exp;

import java.awt.*;
import javax.swing.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

public class MyFrame extends JFrame {
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  GridLayout gridLayout2 = new GridLayout();
  GridLayout gridLayout3 = new GridLayout();
  JToolBar jToolBar1 = new JToolBar();
  JToolBar jToolBar2 = new JToolBar();
  JScrollPane jScrollPane1 = new JScrollPane();
  JScrollPane jScrollPane2 = new JScrollPane();
  JEditorPane jEditorPane1 = new JEditorPane();
  JEditorPane jEditorPane2 = new JEditorPane();


  /**
   *
   */
  public MyFrame() {
    try {
      jbInit();
      show();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    MyFrame myFrame1 = new MyFrame();
  }

  /**
   *
   * @throws Exception
   */
  private void jbInit() throws Exception {
    this.getContentPane().setLayout(gridLayout1);
    gridLayout1.setRows(2);
    jPanel1.setLayout(gridLayout2);
    jPanel2.setLayout(gridLayout3);
    jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    this.getContentPane().add(jPanel1, null);
    jPanel1.add(jToolBar1, null);
    jToolBar1.add(jScrollPane2, null);
    jScrollPane2.getViewport().add(jEditorPane1, null);
    this.getContentPane().add(jPanel2, null);
    jPanel2.add(jToolBar2, null);
    jToolBar2.add(jScrollPane1, null);
    jScrollPane1.getViewport().add(jEditorPane2, null);
  }
}
