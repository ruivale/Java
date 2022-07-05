package exp.swing.panel.keyhandler;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


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
public class CapturingEscapeKey
    extends JPanel {
  public CapturingEscapeKey() {

    JPanel p = new JPanel();
    p.setLayout(new BorderLayout());
    p.add(this,BorderLayout.CENTER);
    JDialog d = new JDialog();
    d.getContentPane().setLayout(new BorderLayout());
    d.getContentPane().add(p,BorderLayout.CENTER);


    //Has to be final because this instance is required in the inner class
    //final UpdateCustomerData updationPanel = new UpdateCustomerData(mainPanel);
    //Setting the size
    p.setSize(400, 150);
    //Creating an input map of the Root Pane
    InputMap map = d.getRootPane().getInputMap(
        JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    //Adding the key that needs to be captured
    map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancel");
    ActionMap actionMap = d.getRootPane().getActionMap();
    //Creating the action that needs to be executed when the key is pressed
    Action close = new AbstractAction() {
      public void actionPerformed(ActionEvent ae) {
        System.out.println("Capturing the ESCAPE key ....");
      }
    };
    actionMap.put("cancel", close);
    //Displaying the Panel
    d.setVisible(true);

  }

  public static void main(String[] args) {
    CapturingEscapeKey c = new CapturingEscapeKey();
    JFrame f = new JFrame(". . . . . . . . . . . . . .");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(c, BorderLayout.CENTER);
    f.setBounds(100, 100, 600, 500);
    f.setVisible(true);
  }
}

/*
 //Has to be final because this instance is required in the inner class
     final UpdateCustomerData updationPanel = new UpdateCustomerData(mainPanel);
     //Setting the size
     updationPanel.setSize(400,150);
     //Creating an input map of the Root Pane
     InputMap map = updationPanel.getRootPane().getInputMap(
                 JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT );
     //Adding the key that needs to be captured
     map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancel" );
     ActionMap actionMap = updationPanel.getRootPane().getActionMap();
     //Creating the action that needs to be executed when the key is pressed
     Action close = new AbstractAction() {
         public void actionPerformed(ActionEvent ae)
         {
             updationPanel.dispose();
         }
     };
     actionMap.put( "cancel", close);
     //Displaying the Panel
    updationPanel.setVisible(true);

 */


