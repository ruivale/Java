package exp.frames;


/**
 * <p>Title: The STV's GI!</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: ENT</p>
 * @author Rui Vale
 * @version 1.0
 */


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;





/**
 *
 *
 */
public class InternalFrameKeyEvents extends JPanel {

  final static int OK = 0;

  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JButton jButtonNo = new JButton();
  JButton jButtonYes = new JButton();
  FlowLayout flowLayout1 = new FlowLayout();
  JLabel jLabelQuestion = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel3 = new JPanel();
  JTextField jTextFieldPreSetName = new JTextField();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  Border border1;
  Border border2;
  Border border3;


  int ID_Eq;
  int ID_Slot;
  String preSetName;
  JRadioButton jRadioButton;
  JButton jButtonCancel = new JButton();
  boolean isNew = false;

  ResourceBundle bundle;




  /**
   *
   */
  public InternalFrameKeyEvents(
    JRadioButton rb ,
    int ID_Eq,
    int ID_Slot,
    String preSetName,
    boolean isNew) {

    try {


      this.ID_Eq = ID_Eq;
      this.ID_Slot = ID_Slot;
      this.preSetName = preSetName;
      this.jRadioButton = rb;
      this.isNew = isNew;

      jbInit();




      this.jTextFieldPreSetName.addKeyListener(
        new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
              jTextFieldPreSetName.transferFocus();
              jButtonYes_actionPerformed(null);
            } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
              jButtonCancel.grabFocus();
              jButtonCancel_actionPerformed(null);
            }
          }
        }
      );
      this.addKeyListener(
        new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
              jButtonCancel.grabFocus();
              jButtonCancel_actionPerformed(null);
            }
          }
        }
      );


      /*
            SwingUtilities.invokeLater(
              new Runnable() {
                public void run() {
                  jTextFieldPreSetName.setSelectionStart(0);
                  jTextFieldPreSetName.setSelectionEnd(
                    jTextFieldPreSetName.getText().length());

                  jTextFieldPreSetName.setCaretPosition(
                    jTextFieldPreSetName.getText().length());

                  jTextFieldPreSetName.grabFocus();
                }
              }
            );
      */



    } catch (Exception e) {
      e.printStackTrace();
    }
  }



  public static void main(String args[]) {
    try {
      final InternalFrameKeyEvents p = new InternalFrameKeyEvents(new JRadioButton("radio"),
                                       2334, 21, "presetname", true);

      final JInternalFrame jif = new JInternalFrame();
      jif.getContentPane().setLayout(new BorderLayout());
      jif.setVisible(true);
      jif.getContentPane().add(p, BorderLayout.CENTER);
      jif.setResizable(true);
      jif.pack();

      JDesktopPane jdp = new JDesktopPane();
      jdp.add(jif );

      JFrame f = new JFrame();
      f.getContentPane().setLayout(new BorderLayout());
      f.getContentPane().add(jdp, BorderLayout.CENTER);
      f.pack();
      f.setBounds(100, 100, 400, 250);
      f.setVisible(true);


      SwingUtilities.invokeLater(
        new Runnable() {
          public void run() {
            try {
              jif.setSelected(true);
              p.jTextFieldPreSetName.grabFocus();
              p.jTextFieldPreSetName.setCaretPosition(p.jTextFieldPreSetName.getText().length());
              p.jTextFieldPreSetName.select(0, p.jTextFieldPreSetName.getText().length());
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
      );



    } catch (Exception e) {
      e.printStackTrace();
    }
  }




  /**
   *
   */
  private void jbInit() throws Exception {

    border1 = BorderFactory.createEmptyBorder(15, 10, 5, 10);
    border2 = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    border3 = BorderFactory.createEmptyBorder(5, 10, 10, 10);
    this.setLayout(borderLayout3);
    jPanel1.setLayout(borderLayout1);
    jPanel2.setLayout(flowLayout1);
    jButtonNo.addActionListener(
      new CameraPreSetSave_jButtonNo_actionAdapter(this));

    jButtonYes.addActionListener(
      new CameraPreSetSave_jButtonYes_actionAdapter(this));

    flowLayout1.setAlignment(FlowLayout.RIGHT);
    jPanel3.setLayout(borderLayout2);
    jPanel1.setBorder(border1);
    jPanel2.setBorder(border2);
    jPanel3.setBorder(border3);
    jButtonCancel.addActionListener(
      new CameraPreSetSave_jButtonCancel_actionAdapter(this));

    this.add(jPanel1, BorderLayout.NORTH);
    this.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jButtonYes, null);
    jPanel2.add(jButtonNo, null);
    jPanel2.add(jButtonCancel, null);
    this.add(jPanel3, BorderLayout.CENTER);
    jPanel3.add(jTextFieldPreSetName, BorderLayout.NORTH);
    jPanel1.add(jLabelQuestion, BorderLayout.CENTER);



    jButtonCancel.setText("Cancel");
    jButtonCancel.setMnemonic(jButtonCancel.getText().charAt(0));
    jButtonNo.setText("No");
    jButtonNo.setMnemonic(jButtonNo.getText().charAt(0));
    jButtonYes.setText("Yes");
    jButtonYes.setMnemonic(jButtonYes.getText().charAt(0));

    jLabelQuestion.setText("Do You want to set a name for this preset?");




    jTextFieldPreSetName.setText(this.preSetName);



  }












  /**
   *
   */
  void jButtonYes_actionPerformed(ActionEvent e) {
    System.out.println("jButtonYes_actionPerformed");
  }

  /**
   *
   */
  void jButtonNo_actionPerformed(ActionEvent e) {
    System.out.println("jButtonNo_actionPerformed");
  }

  /**
   *
   */
  void jButtonCancel_actionPerformed(ActionEvent e) {
    System.out.println("jButtonCancel_actionPerformed");
  }

}

class CameraPreSetSave_jButtonYes_actionAdapter implements java.awt.event.ActionListener {
  InternalFrameKeyEvents adaptee;

  CameraPreSetSave_jButtonYes_actionAdapter(InternalFrameKeyEvents adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonYes_actionPerformed(e);
  }
}

class CameraPreSetSave_jButtonNo_actionAdapter implements java.awt.event.ActionListener {
  InternalFrameKeyEvents adaptee;

  CameraPreSetSave_jButtonNo_actionAdapter(InternalFrameKeyEvents adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonNo_actionPerformed(e);
  }
}

class CameraPreSetSave_jButtonCancel_actionAdapter implements java.awt.event.ActionListener {
  InternalFrameKeyEvents adaptee;

  CameraPreSetSave_jButtonCancel_actionAdapter(InternalFrameKeyEvents adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonCancel_actionPerformed(e);
  }
}
