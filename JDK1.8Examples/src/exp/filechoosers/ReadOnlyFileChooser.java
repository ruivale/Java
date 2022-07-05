package exp.filechoosers;

import java.awt.*;
import java.awt.event.*;

import java.beans.*;

import javax.swing.*;
import javax.swing.plaf.basic.*;
import javax.swing.table.*;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class ReadOnlyFileChooser extends JFileChooser {
  BasicFileChooserUI ui;

  /**
   * Creates a new ReadOnlyFileChooser object.
   *
   * @param path DOCUMENT ME!
   */
  public ReadOnlyFileChooser(String path) {
    super(path);

    if (
      System.getProperty("java.version")
              .startsWith("1.4") &&
        getUI() instanceof BasicFileChooserUI) {
      ui = (BasicFileChooserUI) getUI();

      // Disable "New Folder" button
      ui.getNewFolderAction()
        .setEnabled(false);

      addPropertyChangeListener(
        new PropertyChangeListener() {
          public void propertyChange(PropertyChangeEvent ev) {
            if (
              ev.getPropertyName() == JFileChooser.DIRECTORY_CHANGED_PROPERTY) {
              SwingUtilities.invokeLater(
                new Runnable() {
                  public void run() {
                    ui.getNewFolderAction()
                      .setEnabled(false);
                  }
                });
            }
          }
        });

      String className = ui.getClass()
                           .getName();

      if (
        className.equals("javax.swing.plaf.metal.MetalFileChooserUI") ||
          className.equals(
            "com.sun.java.swing.plaf.windows.WindowsFileChooserUI")) {
        // Disable editing (file renaming) in JList
        JList           list      = (JList) findByClass(this, JList.class);
        MouseListener[] listeners = list.getMouseListeners();

        for (int i = 0; i < listeners.length; i++) {
          className = listeners[i].getClass()
                                  .getName();

          if (className.endsWith("FileChooserUI$SingleClickListener")) {
            list.removeMouseListener(listeners[i]);

            break;
          }
        }

        // Disable editing (file renaming) in JTable
        JToggleButton detailsViewButton = findDetailsViewButton(this);
        detailsViewButton.addActionListener(
          new ActionListener() {
            boolean done = false;

            public void actionPerformed(ActionEvent ev) {
              if (!done && ((JToggleButton) ev.getSource()).isSelected()) {
                SwingUtilities.invokeLater(
                  new Runnable() {
                    public void run() {
                      JTable table =
                        (JTable) findByClass(
                          ReadOnlyFileChooser.this,
                          JTable.class);

                      if (table != null) {
                        TableColumn column =
                          table.getColumnModel()
                               .getColumn(0);
                        column.setCellEditor(null);
                      }
                    }
                  });
                done = true;
              }
            }
          });
      }
    }
  }

  private static Component findByClass(
    Component comp,
    Class     cls) {
    if (cls.isInstance(comp)) {
      return comp;
    } else if (comp instanceof Container) {
      Component[] comps = ((Container) comp).getComponents();

      for (int i = 0; i < comps.length; i++) {
        Component c = findByClass(comps[i], cls);

        if (c != null) {
          return c;
        }
      }
    }

    return null;
  }

  private static JToggleButton findDetailsViewButton(Component comp) {
    Icon detailsViewIcon = UIManager.getIcon("FileChooser.detailsViewIcon");

    if (
      comp instanceof JToggleButton &&
        (((JToggleButton) comp).getIcon() == detailsViewIcon)) {
      return (JToggleButton) comp;
    } else if (comp instanceof Container) {
      Component[] comps = ((Container) comp).getComponents();

      for (int i = 0; i < comps.length; i++) {
        JToggleButton button = findDetailsViewButton(comps[i]);

        if (button != null) {
          return button;
        }
      }
    }

    return null;
  }

  /**
   * DOCUMENT ME!
   *
   * @param args DOCUMENT ME!
   */
  public static void main(String[] args) {
    new ReadOnlyFileChooser(null).showOpenDialog(null);
    System.exit(0);
  }
}
