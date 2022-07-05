package exp.swing.jcolorchooser;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import javax.swing.border.BevelBorder;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


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
public class ColorChooserInsidePanel extends JPanel{

  JColorChooser jcc = new JColorChooser();


  public ColorChooserInsidePanel() {

    final MyPreviewPanel myPreviewPanel = new MyPreviewPanel(" P R E V I E W ");

    final ColorSelectionModel colorSelectionModel = jcc.getSelectionModel();
    colorSelectionModel.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent e){
        System.out.println("stateChanged="+jcc.getColor()+".");

        myPreviewPanel.changeColor(jcc.getColor());

      }
    });

    setLayout(new BorderLayout());

    //jcc.setPreviewPanel(myPreviewPanel);

    add(jcc, BorderLayout.CENTER);

    add(myPreviewPanel, BorderLayout.SOUTH);

  }

  public static void main(String[] args) {
    ColorChooserInsidePanel c = new
        ColorChooserInsidePanel();
    JFrame f = new JFrame("...");
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(c);
    f.pack();
    f.setLocation(100,100);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }
}

class MyPreviewPanel extends JPanel //implements java.beans.PropertyChangeListener
{

  final JLabel l = new JLabel();

  public MyPreviewPanel(final String text){

    super(new BorderLayout());

    l.setText(text);
    l.setOpaque(true);
    l.setBackground(Color.BLACK);
    l.setForeground(Color.BLACK);

    add(l, BorderLayout.CENTER);

    setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

    setVisible(true);

    //addPropertyChangeListener(this);
  }

  public void changeColor(final Color color){
    if (SwingUtilities.isEventDispatchThread()) {
      l.setBackground(color);
      l.setForeground(color);
    } else {
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          l.setBackground(color);
          l.setForeground(color);
        }
      });
    }
  }


  public void propertyChange(final PropertyChangeEvent evt){
    final String strProName = evt.getPropertyName();

    System.out.println("propertyChange - "+strProName+".");

    if(strProName != null && strProName.equals("foreground")){
      System.out.println("propertyChange - foreground");

      final Object obj = evt.getNewValue();

      System.out.println("obj="+obj.toString()+".");

      if(obj instanceof java.awt.Color){
        final Color color = (Color)obj;


      }

    }
  }
}













