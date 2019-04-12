


package exp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class TestLayeredPane extends JFrame {

  public Component glassPane;// = new CustomLayeredPane();

  TransparentDesktopPane jdp = new TransparentDesktopPane();
  JPanel jp = new JPanel();
  JInternalFrame jif = new JInternalFrame(" A P L I C A T I O N ");
  JInternalFrame jif2 = new JInternalFrame("... test ...");
  JButton jb = new JButton("BUTTON JB");

  private JLayeredPane lp = new JLayeredPane();

  private Integer layers[] = {
    JLayeredPane.FRAME_CONTENT_LAYER,
    JLayeredPane.DEFAULT_LAYER,
    JLayeredPane.PALETTE_LAYER,
    JLayeredPane.MODAL_LAYER,
    JLayeredPane.POPUP_LAYER,
    JLayeredPane.DRAG_LAYER,
  };
  private JPanel layerPanels[] = {new JPanel(), new JPanel(), new JPanel(),
                                  new JPanel(), new JPanel(), new JPanel()};


  /**
   * CONSTRUCTOR
   */
  public TestLayeredPane() {

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    jdp.putClientProperty("JDesktopPane.dragMode", "outline");

    JButton b = new JButton("BUTTON 1");
    b.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.err.println("THE DESKTOP IS OPAQUE: " + jdp.isOpaque());
          addToLayer();
        }
      }
    );
    b.setBounds(20, 20, 300, 50);

    JButton b2 = new JButton("BUTTON 2");
    b2.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.err.println("THE DESKTOP IS OPAQUE: " + jdp.isOpaque());
          addToLayer2();
        }
      }
    );
    b2.setBounds(20, 20, 300, 120);


    jif.setContentPane(lp);
    jif.setBounds(30, 30, 400, 300);
    jif.setVisible(true);

    jif2.setBounds(300, 400, 250, 150);
    jif2.setVisible(true);

    jp.setBounds(0, 0, 400, 300);
    jp.setLayout(new BorderLayout());
    jp.add(b, BorderLayout.CENTER);

    lp.setLayout(new BorderLayout());
    lp.setLayer(jp, layers[0].intValue());
    lp.add(jp, BorderLayout.CENTER);

    jdp.setLayout(null);
    jdp.setBounds(10, 10, 780, 520);
    jdp.setOpaque(false);
    jdp.add(jif );
    jdp.add(jif2);

    getContentPane().setLayout(null);
    getContentPane().add(jdp, BorderLayout.CENTER);
    setBounds(100, 100, 800, 550);
    setVisible(true);

  }

  void removeFromLayer() {

    glassPane.setVisible(false);

  }



  void addToLayer() {
    Component glassPane2 = new CustomLayeredPane(this);
    glassPane2.setBounds(0, 0, 395, 295);
    lp.setLayer(glassPane2, layers[1].intValue());
    lp.add(glassPane2, BorderLayout.CENTER);

    glassPane2.setVisible(true);

  }

  void addToLayer2() {

    Component glassPane2 = new CustomLayeredPane(this);
    glassPane2.setBounds(0, 0, 395, 295);
    lp.setLayer(glassPane2, layers[2].intValue());
    lp.add(glassPane2, BorderLayout.CENTER);

    glassPane2.setVisible(true);

  }





  public static void main(String args[]) {
    new TestLayeredPane();
  }

}







/**
 * CLASS
 */
class CustomLayeredPane extends JLayeredPane {

  public JButton button = new JButton("hfjsdhfj");

  JInternalFrame jif = new JInternalFrame("jif3", true, false, true, false);

  TransparentDesktopPane jdp = new TransparentDesktopPane();

  TestLayeredPane owner;


  public CustomLayeredPane(TestLayeredPane owner) {

    jdp.putClientProperty("JDesktopPane.dragMode", "outline");

    this.owner = owner;

    this.setOpaque(false);

    setLayout(new BorderLayout());

    jif.getContentPane().setLayout(new BorderLayout());


    JButton b1 = new JButton("close");
    b1.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          turnVisibilityNull();
        }
      }
    );
    JButton b2 = new JButton("Layer2");
    b2.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          addToLayer2();
        }
      }
    );
    JButton b3 = new JButton("addJIF");
    b3.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          addJIF();
        }
      }
    );


    jif.getContentPane().add(b1, BorderLayout.NORTH);
    jif.getContentPane().add(b3, BorderLayout.CENTER);

    jif.setPreferredSize(new Dimension(100, 100));
    jif.setBounds(10, 10, 150, 150);
    jif.setVisible(true);
    jif.requestFocus();

    jdp.setLayout(null);
    jdp.setOpaque(false);
    jdp.add(jif , BorderLayout.CENTER);

    setLayer(jdp, (JLayeredPane.DEFAULT_LAYER).intValue());
    add(jdp, BorderLayout.CENTER);


    addMouseListener(
      new MouseAdapter() {
        public void mousePressed(MouseEvent e) {}

      }
    );

  }


  void addToLayer2() {
    owner.addToLayer2();
  }



  void turnVisibilityNull() {

    this.setVisible(false);

  }



  void addJIF() {

    JInternalFrame jif = new JInternalFrame("jif4", true, true, true, true);
    jif.setSize(new Dimension(250, 130));
    jif.setLocation(10, 10);
    jif.setVisible(true);

    TransparentDesktopPane tdp = new TransparentDesktopPane();
    tdp.setOpaque(false);
    tdp.add(jif );
    tdp.setBounds(0, 0, this.getWidth(), this.getHeight());

    final JLayeredPane jlp1 = new JLayeredPane();
    jlp1.setLayout(new BorderLayout());
    jlp1.setOpaque(false);
    jlp1.setBounds(0, 0, this.getWidth(), this.getHeight());
    jlp1.setLayer(tdp, (JLayeredPane.DEFAULT_LAYER).intValue());
    jlp1.add(tdp, BorderLayout.CENTER);

    JButton b3 = new JButton("close");
    b3.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          jlp1.setVisible(false);
        }
      }
    );

    jif.getContentPane().add(b3, BorderLayout.CENTER);

    jlp1.addMouseListener(
      new MouseAdapter() {
        public void mousePressed(MouseEvent e) {}

      }
    );

    this.setLayer(jlp1, (JLayeredPane.DRAG_LAYER).intValue());
    this.add(jlp1, BorderLayout.CENTER);

  }


}



