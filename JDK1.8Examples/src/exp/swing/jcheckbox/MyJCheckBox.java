package exp.swing.jcheckbox;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.awt.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MyJCheckBox extends JPanel{
  final JCheckBox jCheckBox1 = new JCheckBox("  CCMAN  ");

  public MyJCheckBox() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    FlowLayout flowLayout1 = new FlowLayout();
    flowLayout1.setAlignment(FlowLayout.LEFT);
    this.setLayout(flowLayout1);
    this.add(jCheckBox1, null);

    TitledBorder tb = new TitledBorder("Imagem");
    this.setBorder(tb);

//    this.jCheckBox1.addActionListener(new ActionListener(){
//          boolean boolIsSelected = true;
//          public void actionPerformed(ActionEvent e){
//            boolIsSelected = !boolIsSelected;
//            JOptionPane.showMessageDialog (null,"isSelected="+jCheckBox1.isSelected()+
//                                           ", boolIsSelected="+(boolIsSelected)+".");
//          }
//        });

    this.jCheckBox1.addMouseListener(new MouseListener() {
          boolean boolIsSelected = true;

      @Override
      public void mouseClicked(MouseEvent e) {

            boolIsSelected = !boolIsSelected;
            JOptionPane.showMessageDialog (null,"MouseClicked isSelected="+jCheckBox1.isSelected()+
                                           ", boolIsSelected="+(boolIsSelected)+".");
      }

      @Override
      public void mousePressed(MouseEvent e) {
      }

      @Override
      public void mouseReleased(MouseEvent e) {
      }

      @Override
      public void mouseEntered(MouseEvent e) {
      }

      @Override
      public void mouseExited(MouseEvent e) {
      }
    });

    new Thread(new Runnable() {
      boolean b = true;
      public void run() {
        while (true) {
          try { Thread.sleep(2500);} catch (Exception ex) {}
          if (SwingUtilities.isEventDispatchThread()) {
            jCheckBox1.setSelected(b);
          } else {
            SwingUtilities.invokeLater(new Runnable() {
              public void run() {
                jCheckBox1.setSelected(b);
              }
            });
          }
          b = !b;
        }
      }
    }).start();

  }
  public static void main(String[] args) {
    MyJCheckBox myJCheckBox1 = new MyJCheckBox();
    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(myJCheckBox1, BorderLayout.CENTER);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.pack();
    f.setVisible(true);
  }

}
