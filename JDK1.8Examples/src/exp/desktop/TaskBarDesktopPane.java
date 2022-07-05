package exp.desktop;

/*
 * TaskBarDesktopPane.java
 *
 * Created on 06 January 2004, 10:54
 */

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Me
 */
public class TaskBarDesktopPane
    extends JPanel {
  private JPanel taskBar;
  private JPanel invisiblePanel;
  private JDesktopPane desktopPane;
  private HashMap componentMap = new HashMap();

  /** Creates a new instance of TaskBarDesktopPane */
  public TaskBarDesktopPane() {
    desktopPane = createDesktopPane();
    taskBar = createTaskBar();
    invisiblePanel = createInvisiblePanel();
    this.setLayout(new BorderLayout());
    this.add(new JScrollPane(desktopPane), BorderLayout.CENTER);
    this.add(taskBar, BorderLayout.SOUTH);
  }

  private JPanel createTaskBar() {
    JPanel panel = new JPanel() {
      public Component add(Component c) {
        if (c instanceof JInternalFrame.JDesktopIcon) {
          final JInternalFrame.JDesktopIcon icon = (JInternalFrame.JDesktopIcon)
              c;
          JInternalFrame f = icon.getInternalFrame();
          final JButton button = new JButton(f.getTitle(), f.getFrameIcon());
          button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
              try {
                icon.getInternalFrame().setIcon(false);
              } catch (PropertyVetoException e2) {}
            }
          });
          super.add(button);
          invisiblePanel.add(c);
          componentMap.put(c, button);
          if (!isVisible()) {
            setVisible(true);
          }
          // the following as it wasn't always appearing immediately
          super.revalidate();
          super.repaint();
          // not sure if I should return c or button, doesn't seem to make any difference
          return button;
        } else {
          return desktopPane.add(c);
        }
      }

      public void remove(Component comp) {
        super.remove(comp);
        if (getComponentCount() == 0) {
          setVisible(false);
        }
        invalidate();
        revalidate();
        repaint();
      }
    };
    panel.setVisible(false);
    panel.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 1));
    panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    return panel;
  }

  /*
   * This invisible panel is used to hold JDesktopIcons while the TaskBar holds
   * their imposter JButtons. This panel is needed as the DesktopManager will
   * try to put the JInternaleFrame on the parent of the JDesktopIcon when
   * deiconifying. Without this, there would not be a parent.
   */
  private JPanel createInvisiblePanel() {
    JPanel panel = new JPanel() {
      public Component add(Component c) {
        if (c instanceof JInternalFrame.JDesktopIcon) {
          return super.add(c);
        } else {
          return desktopPane.add(c);
        }
      }

      public void remove(Component comp) {
        if (comp instanceof JInternalFrame.JDesktopIcon) {
          Component button = (Component) componentMap.remove(comp);
          super.remove(comp);
          taskBar.remove(button);
        } else {
          super.remove(comp);
        }
      }
    };
    panel.setVisible(false);
    return panel;
  }

  private JDesktopPane createDesktopPane() {
    JDesktopPane panel = new JDesktopPane() {
      public Component add(Component c) {
        if (c instanceof JInternalFrame.JDesktopIcon) {
          return taskBar.add(c);
        } else {
          Component out = super.add(c);
          return out;
        }
      }
    };
    return panel;
  }

  public Component add(Component c) {
    if (c instanceof JInternalFrame.JDesktopIcon) {
      return taskBar.add(c);
    } else {
      return desktopPane.add(c);
    }
  }

  //
  // TO DEMONSTRATE
  //

  public static void main(String[] args) {
    class MyJInternalFrame
        extends JInternalFrame {
      public MyJInternalFrame(String title, int x, int y, int width, int height) {
        super(title,
              true, //resizable
              true, //closable
              true, //maximizable
              true); //iconifiable
        //Set the window's location.
        setBounds(x, y, width, height);
        setVisible(true);
      }
    }

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    TaskBarDesktopPane tdp = new TaskBarDesktopPane();
    tdp.add(new MyJInternalFrame("One", 0, 0, 150, 100));
    tdp.add(new MyJInternalFrame("Two", 30, 30, 150, 100));
    frame.getContentPane().add(tdp);
    frame.setSize(600, 400);
    frame.setVisible(true);
  }
}
