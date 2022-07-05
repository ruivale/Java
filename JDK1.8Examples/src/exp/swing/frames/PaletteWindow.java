package exp.swing.frames;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
//import com.borland.dbswing.DBTableModel;
//import com.borland.dbswing.JdbTable;
import java.awt.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;


public class PaletteWindow
    extends JFrame {
  public PaletteWindow() {
    try {
      jbInit();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    JDialog.setDefaultLookAndFeelDecorated(true);

    JDialog dialog = new JDialog((Frame)null, "Test");

    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    dialog.getRootPane().setBorder(new BevelBorder(BevelBorder.RAISED));

    dialog.setSize(400, 300);
    dialog.setVisible(true);

    JDialog dialog2 = new JDialog((Frame)null);

    dialog2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    JLayeredPane layeredPane = dialog2.getRootPane().getLayeredPane();

    Component[] comps = layeredPane.getComponentsInLayer(JLayeredPane.
        FRAME_CONTENT_LAYER.intValue());

    for (int i = 0; i < comps.length; i++) {
      Component component = comps[i];

      if (component != dialog2.getContentPane()) {
        component.setSize(new Dimension(12, 12));

        JComponent c = ((JComponent) component);

        Component[] subComponents = c.getComponents();

        for (int j = 0; j < subComponents.length; j++) {
          Component component2 = subComponents[j];

          if (component2 instanceof JButton) {
            JButton b = (JButton) component2;

            b.setIcon(UIManager.getIcon("InternalFrame.paletteCloseIcon"));

            b.setPreferredSize(new Dimension(8, 8));
            b.setMargin(new Insets(1, 1, 1, 1));
          }

        }
      }
    }

    dialog2.getRootPane().setBorder(new BevelBorder(BevelBorder.RAISED));

    dialog2.setSize(400, 300);
    dialog2.setVisible(true);

    JDialog.setDefaultLookAndFeelDecorated(false);

    JDialog dialog3 = new JDialog((Frame)null, "Test3");

    dialog3.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    dialog3.setSize(400, 300);
    dialog3.setVisible(true);

  }

  private void jbInit() throws Exception {
//    jdbTable1.addAncestorListener(new PaletteWindow_jdbTable1_ancestorAdapter(this));
//    this.getContentPane().add(jdbTable1, java.awt.BorderLayout.CENTER);
  }

//  DBTableModel dBTableModel1 = new DBTableModel();
//  JdbTable jdbTable1 = new JdbTable();
//  public void jdbTable1_ancestorAdded(AncestorEvent event) {
//
//  }
}


class PaletteWindow_jdbTable1_ancestorAdapter
    implements AncestorListener {
  private PaletteWindow adaptee;
  PaletteWindow_jdbTable1_ancestorAdapter(PaletteWindow adaptee) {
    this.adaptee = adaptee;
  }

  public void ancestorAdded(AncestorEvent event) {
//    adaptee.jdbTable1_ancestorAdded(event);
  }

  public void ancestorRemoved(AncestorEvent event) {
  }

  public void ancestorMoved(AncestorEvent event) {
  }
}
