// %1221149737678:%
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swingfx.jxlayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jdesktop.jxlayer.JXLayer;
import org.jdesktop.jxlayer.plaf.ext.MouseScrollableUI;



/**
 * 
 *
 * @author $author$
 * @version $Revision$
  */
public class JXLayerTests extends JPanel{

  public JXLayerTests() {
    
    JButton jb = new JButton("sajdbvfbhsdvnlsdj");
    jb.setPreferredSize(new Dimension(600,600));        
    
    JScrollPane sp = new JScrollPane(jb);
    JXLayer<JScrollPane> l = new JXLayer<JScrollPane>(sp, new MouseScrollableUI());        
    
    setLayout(new BorderLayout());
    add(l);             
    
  }
  
  
  
  public static void main(String[] args) {
    final JXLayerTests j = new JXLayerTests();
    
    final JFrame f = new JFrame("JxLayer tests ... ");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setBounds(200,200,250,250);
    f.setLayout(new BorderLayout());
    f.add(j);
    f.setVisible(true);
    
  }
}
/***
    class MyLayerUI extends AbstractLayerUI<JComponent> {
        
        // This method catches all focus, mouse and keyboard events
        // for the layer and all its subcomponents
        @Override
        public void eventDispatched(AWTEvent e, JXLayer<JComponent> l) {
            super.eventDispatched(e, l);
            System.out.println("AWTEvent is dispatched: " + e);
        }

        // Utility methods which are called from eventDispatched()
        // to provide a hook for catching particular type of events
        @Override
        protected void processMouseEvent(MouseEvent e, JXLayer<JComponent> l) {
            System.out.println("MouseEvent on component: " + e.getComponent());
        }
        
        @Override
        protected void processMouseMotionEvent(MouseEvent e, JXLayer<JComponent> l) {
            System.out.println("MouseMotionEvent on component: " + e.getComponent());
        }    
    }
/**/