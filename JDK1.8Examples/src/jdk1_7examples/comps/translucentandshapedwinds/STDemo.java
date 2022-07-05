/**
 * <p>
 * Classname:  jdk1_7examples.comps.translucentandshapedwinds.STDemo
 * </p>
 *
 * <p>Copyright: Copyright (c) 2010 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua EngÂº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */
package jdk1_7examples.comps.translucentandshapedwinds;

// STDemo.java
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class STDemo extends JFrame {

  public STDemo() {
    super("Simple Translucency Demo");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    final JSlider slider = new JSlider(0, 100, 100);
    ChangeListener cl;
    cl = new ChangeListener() {

      public void stateChanged(ChangeEvent ce) {
        JSlider source = (JSlider) ce.getSource();
        STDemo.this.setOpacity(source.getValue() / 100.0f);
      }
    };
    slider.addChangeListener(cl);
    getContentPane().setLayout(new FlowLayout());
    getContentPane().add(new JLabel("TRANSP"));
    getContentPane().add(new JPanel() {

      {
        add(slider);
      }
    });
    getContentPane().add(new JLabel("OPAQUE"));
    getRootPane().setDoubleBuffered(false);
    pack();
    setVisible(true);
  }

  public static void main(String[] args) {
    Runnable r;
    r = new Runnable() {

      public void run() {
        GraphicsEnvironment ge;
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        if (!ge.getDefaultScreenDevice().
            isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)) {
          System.err.println("simple translucency isn't " +
                             "supported");
          return;
        }
        new STDemo();
      }
    };
    EventQueue.invokeLater(r);
  }
}
