package exp.textpane;


import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.util.*;

import javax.swing.*;
import javax.swing.text.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class MyJTextPane extends JPanel {

    JTextPane tp = new JTextPane();
  private StyledDocument document = (StyledDocument)tp.getDocument();


  public MyJTextPane() {
    this.setLayout(new BorderLayout());

    tp.setEditable(false);
    //tp.setText("APP: STV\nSEV: 3\nDescrição: kjaksjkjskajs 8askjskajsk a9s89as as98 as89asdjlsfjskldfsl");
    tp.setText("<html>Label<br><a href=\"\">Efacec site</a></html>");

    SimpleAttributeSet sas = new SimpleAttributeSet();
    StyleConstants.setBold(sas, true);

    document.setCharacterAttributes(0, 4, sas, false);
    document.setCharacterAttributes(9, 4, sas, false);
    document.setCharacterAttributes(16, 11, sas, false);

    this.add(tp, BorderLayout.CENTER);

  }
  public static void main(String[] args) {
    MyJTextPane myJTextPane1 = new MyJTextPane();
    JFrame           f = new JFrame();
    f.setTitle("My text pane");
    f.getContentPane()
     .setLayout(new BorderLayout());
    f.getContentPane()
     .add(myJTextPane1, BorderLayout.CENTER);
    f.setBounds(100, 100, 400, 250);
    f.setVisible(true);
    f.addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          System.exit(0);
        }
      });
  }
}
