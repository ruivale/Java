package exp.comboboxes;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;


/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description: Trying to show the PopUp upwards. Tip: computePopupBounds
 * method of the BasicComboPopup
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
public class MyJComboBox
    extends JComboBox {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  MyBasicComboPopup myBasicComboPopup = null;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new MyJComboBox object.
   */
  public MyJComboBox() {
    myBasicComboPopup = new MyBasicComboPopup(this);

/*
   this.addActionListener(new ActionListener() {
     public void actionPerformed(ActionEvent e) {
       System.out.println("AAAAAAA");
       myBasicComboPopup.setVisible(
           !myBasicComboPopup.isVisible());
       setPopupVisible(false);
     }
   });
 */
  }

  /**
   * Creates a new MyJComboBox object.
   *
   * @param model  Insert doc ...
   */
  public MyJComboBox(MutableComboBoxModel model) {
    super(model);
    myBasicComboPopup = new MyBasicComboPopup(this);
  }

  /**
   * Creates a new MyJComboBox object.
   *
   * @param b  Insert doc ...
   */
  public MyJComboBox(boolean b) {
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param a  Insert doc ...
   */
  public static void main(String[] a) {
    JPanel                       p = new JPanel(new BorderLayout());

    JComboBox_mouseAdapter       mouseAdapter       =
      new JComboBox_mouseAdapter();
    JComboBox_mouseMotionAdapter mouseMotionAdapter =
      new JComboBox_mouseMotionAdapter();

    MyJComboBox ecb = new MyJComboBox();

/*
   MyJComboBox ecb = new MyJComboBox(true);
   Component[] comps = ecb.getComponents();
   for (int i = 0; i < comps.length; i++) {
     if(comps[i] instanceof javax.swing.plaf.metal.MetalComboBoxButton){
       System.out.println("Comps["+i+"] = "+comps[i].toString());
       comps[i].addMouseListener(mouseAdapter);
       comps[i].addMouseMotionListener(mouseMotionAdapter);
     }
   }
 */
    ecb.addItem("Rui Vale");
    ecb.addItem("Jui Filipe Vale");
    ecb.addItem("Iui Filipe Fonseca Gomes do Vale");
    ecb.addItem("TGV");
    ecb.addItem("RV");
    ecb.addItem("R-G-V");
    ecb.addItem("GODOVA");

    p.add(ecb, BorderLayout.CENTER);

    JFrame f = new JFrame();
    f.setTitle("Filtro dos eventos na PInt");
    f.getContentPane()
     .setLayout(new BorderLayout());
    f.getContentPane()
     .add(p, BorderLayout.CENTER);
    f.setBounds(100, 100, 200, 70);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }

  // this is all you really need
  public synchronized Dimension getSize() {
    //return getPreferredSize();
    return new Dimension(500, 50);
  }

  /**
   * Insert doc ...
   */
  public void showPopup() {
    System.out.println("showPopup()");
    myBasicComboPopup.setVisible(true);
  }
}


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
class MyBasicComboPopup
    extends javax.swing.plaf.basic.BasicComboPopup {
  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new MyBasicComboPopup object.
   *
   * @param jcb  Insert doc ...
   */
  public MyBasicComboPopup(JComboBox jcb) {
    super(jcb);
    System.out.println("LIST= " + this.getList().toString() + ".");
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param px  Insert doc ...
   * @param py  Insert doc ...
   * @param pw  Insert doc ...
   * @param ph  Insert doc ...
   *
   * @return  Insert doc ...
   */
  protected Rectangle computePopupBounds(
    int px,
    int py,
    int pw,
    int ph) {
    System.out.println("computePopupBounds(" + px + "," + py + "," + pw + "," +
      ph + ")");

    return (new Rectangle(500, 500, 500, 50));
  }
}


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
class JComboBox_mouseMotionAdapter
    extends java.awt.event.MouseMotionAdapter {
  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new JComboBox_mouseMotionAdapter object.
   */
  JComboBox_mouseMotionAdapter() {
    System.out.println("new JComboBox_mouseMotionAdapter");
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param e  Insert doc ...
   */
  public void mouseDragged(MouseEvent e) {
    System.out.println("JComboBox_mouseMotionAdapter mouseDragged");
  }

  /**
   * Insert doc ...
   *
   * @param e  Insert doc ...
   */
  public void mouseMoved(MouseEvent e) {
    System.out.println("JComboBox_mouseMotionAdapter mouseMoved");
  }
}


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
class JComboBox_mouseAdapter
    extends java.awt.event.MouseAdapter {
  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new JComboBox_mouseAdapter object.
   */
  JComboBox_mouseAdapter() {
    System.out.println("new JComboBox_mouseAdapter");
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param e  Insert doc ...
   */
  public void mouseClicked(final MouseEvent e) {
    System.out.println("JComboBox_mouseAdapter mouseClicked");
  }

  /**
   * Insert doc ...
   *
   * @param e  Insert doc ...
   */
  public void mouseEntered(MouseEvent e) {
    System.out.println("JComboBox_mouseAdapter mouseEntered");
  }

  /**
   * Insert doc ...
   *
   * @param e  Insert doc ...
   */
  public void mouseExited(MouseEvent e) {
    System.out.println("JComboBox_mouseAdapter mouseExited");
  }

  /**
   * Insert doc ...
   *
   * @param e  Insert doc ...
   */
  public void mousePressed(MouseEvent e) {
    System.out.println("JComboBox_mouseAdapter mousePressed");
  }

  /**
   * Insert doc ...
   *
   * @param e  Insert doc ...
   */
  public void mouseReleased(MouseEvent e) {
    System.out.println("JComboBox_mouseAdapter mouseReleased");
  }
}
