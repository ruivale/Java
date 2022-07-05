package exp.swing.frames;

import javax.swing.*;
import java.awt.*;

import javax.swing.event.*;
import java.awt.event.*;

import exp.desktop.*;

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
public class FramesLayeredPaneTests extends JFrame {

  JLayeredPane jLayeredPane = null;

  public FramesLayeredPaneTests() {
    getContentPane().setLayout(new BorderLayout());
    JButton b = new JButton("1111111111111");
    b.addMouseListener(new MouseAdapter(){
      public void mouseClicked(final MouseEvent e){
        System.out.println("click in the button ...");

        showTDP();

      }
    });
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 500, 300);
    setVisible(true);

    jLayeredPane = new JLayeredPane();
    //jLayeredPane = getLayeredPane();
    jLayeredPane.setLayout(new BorderLayout());

    jLayeredPane.setLayer(b, JLayeredPane.DEFAULT_LAYER.intValue());
    jLayeredPane.add(b, BorderLayout.CENTER);

    getContentPane().add(jLayeredPane, BorderLayout.CENTER);

  }

  private void showTDP(){
    TransparentDesktopPane tdp = new TransparentDesktopPane(false);
    JInternalFrame jif1 = new JInternalFrame("jif1", true, true, true, true);
    JInternalFrame jif2 = new JInternalFrame("jif2", true, true, true, true);
    tdp.setLayout(null);
    jif1.setBounds(10,10,300,200);
    jif2.setBounds(320,220,200,120);
    tdp.add(jif1);
    tdp.add(jif2);
    jif1.setVisible(true);
    jif2.setVisible(true);
    try {
      jif1.setSelected(true);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    jLayeredPane.setLayer(tdp, JLayeredPane.DRAG_LAYER.intValue());
    jLayeredPane.add(tdp, BorderLayout.CENTER);


  }

  public static void main(String[] s){
    new FramesLayeredPaneTests();
  }
}
