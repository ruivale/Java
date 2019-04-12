
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package exp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestGlass extends JFrame {


  final JInternalFrame jif = new JInternalFrame("khjksd",true,true,true,true);

  private Component glassPane = new _CustomGlassPane();


  Container contentPane= null;

  JDesktopPane jdp = new JDesktopPane();



  public TestGlass() {

    JRadioButton rb = new JRadioButton();
    JButton button = new JButton("show glass pane");
    button.setBounds(0,0,200,20);

    contentPane = getContentPane();

    contentPane.setLayout(null);
    contentPane.add(button);

    jdp.setBounds(0,30,600,500);
    jdp.setVisible(true);
    contentPane.add(jdp);


    jif.getContentPane().setLayout(new FlowLayout());
    jif.getContentPane().add(new JButton("asdkfjwdklfklçs"));

    button.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {

                                 glassPane.setVisible(true);
                               }
                             });

    addJIF();
    jif.setGlassPane(glassPane);
    glassPane.setVisible(true);


    setSize(600,500);
    setVisible(true);


  }

  void addJIF(){

  System.err.println("ADICIONEI");

    jif.setVisible(true);
    jif.setBounds(10,10,300,200);
    jdp.add(jif);
  }

  public static void main(String args[]){new TestGlass();}

}

class _CustomGlassPane extends JPanel {
  private JButton button;
  private String displayString = "glass pane ...  ";

  public _CustomGlassPane() {
    addMouseListener(new MouseAdapter() {
                       public void mousePressed(MouseEvent e) {
                         //setVisible(false);
                       }
                     });
  }
  public void paintComponent(Graphics g) {
    Dimension size = getSize();
    FontMetrics fm = g.getFontMetrics();
    int sw = fm.stringWidth(displayString);
    int fh = fm.getHeight();

    g.setColor(Color.blue);

    for(int row=fh; row < size.height; row += fh)
      for(int col=0; col < size.width; col += sw)
        g.drawString(displayString, col, row);
  }
}
