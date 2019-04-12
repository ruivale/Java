package exp.layered;

import javax.swing.*;
import exp.*;

import java.awt.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class MyLayered extends JLayeredPane {

  public MyLayered() {

    TransparentDesktopPane jPanelNorth = new TransparentDesktopPane(/*new BorderLayout()*/);
    String strQuadrant = "23";
    JLabel jLabelQuadrant = new JLabel(strQuadrant);
    jLabelQuadrant.setForeground(Color.black);
    jLabelQuadrant.setBackground(new Color(230,230,230));
    jLabelQuadrant.setToolTipText(strQuadrant);
    jLabelQuadrant.setHorizontalTextPosition(SwingConstants.LEFT);
    jLabelQuadrant.setOpaque(false);
    jLabelQuadrant.setBounds(0,0,100,20);
    jPanelNorth.add(jLabelQuadrant/*, BorderLayout.NORTH*/);
    jPanelNorth.setOpaque(false);

    this.setLayout(new BorderLayout());


    JPanel jp = new JPanel();
    jp.setBackground(Color.red);


    this.setLayer(jp, (JLayeredPane.DEFAULT_LAYER).intValue());
    this.add(jp, BorderLayout.CENTER);

    this.setLayer(jPanelNorth, (JLayeredPane.POPUP_LAYER).intValue());
    this.add(jPanelNorth, BorderLayout.CENTER);

  }

  public static void main(String[] args) {
    MyLayered myLayered1 = new MyLayered();

    JFrame f = new JFrame();
    f.setContentPane(myLayered1);

  //  f.getContentPane().setLayout(new BorderLayout());
//    f.getContentPane().add(myLayered1, BorderLayout.CENTER);
    f.setBounds(200,200,300,200);
    f.setVisible(true);

  }
}