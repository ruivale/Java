package exp.splitpanes;


import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class PropertyChangesInJSplitPane extends JFrame{
  JSplitPane jSplitPane = new JSplitPane();

  public PropertyChangesInJSplitPane() {
    JFrame frame = this;
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(this.jSplitPane, BorderLayout.CENTER);

    frame.setBounds(50,50,600,400);
    frame.setVisible(true);


/*
    jSplitPane.addComponentListener(new ComponentAdapter(){
      public void componentResized(ComponentEvent e){
        Dimension dim = jSplitPane.getSize();
        firePropertyChange("jsplitsresize", dim, dim);

System.out.println("jSplitPaneEventsOperArea RESIZE");

      }
    });
*/
    jSplitPane.addPropertyChangeListener(new MyPanel());

  }
  public static void main(String[] args) {
    PropertyChangesInJSplitPane propertyChangesInJSplitPane1 = new PropertyChangesInJSplitPane();
  }
}

class MyPanel extends JPanel implements PropertyChangeListener{

  public MyPanel(){
    System.out.println("ADDING PropertyChangeListener");
    this.addPropertyChangeListener(this);
  }

  public void propertyChange(PropertyChangeEvent evt) {
    if(evt.getPropertyName().equals("dividerLocation")){
      System.out.println("EVT ("+evt.getPropertyName()+")");
    }
  }

}

