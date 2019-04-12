package exp.buttons;


import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


/**
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c)
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author unascribed
 * @version 1.0
 */
public class JButtonsIcons
    extends JPanel {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  JButton jButton1 = new JButton();

  /** .. */
  JButton jButton2 = new JButton();

  /** .. */
  int i = 0;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new JButtonsIcons object.
   */
  public JButtonsIcons() {
    try {
      jbInit();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   */
  public static void main(String[] args) {
    /***
    // Setting the look and feel.
    try {
      //UIManager.LookAndFeelInfo[] lf = UIManager.getInstalledLookAndFeels();
      //for (int i = 0; i < lf.length; i++) {
        //System.out.println("LookAndFeelInfo["+i+"]="+lf[i].toString()+".");
      //}
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }catch(Exception e) {
      e.printStackTrace();
    }
    /**/


    JButtonsIcons JButtonsIcons1 = new JButtonsIcons();
    JFrame        f = new JFrame();
    f.getContentPane().setLayout(new GridLayout(250,20));

    JPanel bs[] = new JPanel[5000];

    for (int i = 0; i < 5000; i++) {
      JFrame f_ = new JFrame();
      f_.setBounds(i,i,100,50);
      f_.setVisible(true);

      bs[i] = new JPanel(new BorderLayout());
      bs[i].add(new JButton(""+i));
      /**
      bs[i].addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          System.out.println("button="+((JButton)e.getSource()).getText()+".");
        }
      });
      */
      //bs[i].setBounds(i, i, 30, 10);
      f.getContentPane().add(bs[i]);

    }
    //f.setBounds(0,0,1000,700);
    f.pack();
    f.setVisible(true);


  }

  /**
   * Insert doc ...
   *
   * @param e  Insert doc ...
   */
  void jButton2_actionPerformed(ActionEvent e) {
    i++;

    if((i % 3)==0) {
      jButton1.setDisabledIcon(null);
    } else {
      jButton1.setDisabledIcon(
        new ImageIcon("/JBProjects/PIntV1_0/images/stv/Play.gif"));
    }

    this.jButton1.setEnabled(!this.jButton1.isEnabled());
  }

  /**
   * Insert doc ...
   *
   * @throws Exception  Insert doc ...
   */
  private void jbInit()
      throws Exception {
    jButton1.setText("jButton1");
    jButton2.setText("jButton2");
    jButton2.addActionListener(new JButtonsIcons_jButton2_actionAdapter(this));
    this.add(jButton1, null);
    this.add(jButton2, null);

    jButton1.setIcon(new ImageIcon("/JBProjects/PIntV1_0/images/stv/Play.gif"));
    jButton1.setDisabledIcon(
      new ImageIcon("/JBProjects/PIntV1_0/images/stv/Play.gif"));
  }
}


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
class JButtonsIcons_jButton2_actionAdapter
    implements java.awt.event.ActionListener {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  JButtonsIcons adaptee;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new JButtonsIcons_jButton2_actionAdapter object.
   *
   * @param adaptee  Insert doc ...
   */
  JButtonsIcons_jButton2_actionAdapter(JButtonsIcons adaptee) {
    this.adaptee = adaptee;
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param e  Insert doc ...
   */
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}
