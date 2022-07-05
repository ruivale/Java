package jdk1_6examples.javax.swing.jrootpane;


/* $Id: JRootPanes.java,v 1.3 2008/01/20 18:46:35 ljnelson Exp $
 * Copyright 2006-2008 Laird Nelson.
 */

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;

import java.awt.event.ActionEvent;
import java.awt.event.AWTEventListener;

import java.awt.event.HierarchyEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;


public final class JRootPanes {

  public static final KeyStroke ESC = KeyStroke.getKeyStroke("ESCAPE");

  public static final Action CLOSE_WINDOW_ACTION = new CloseWindowAction();

  private static JRootPaneInputMapInjector injector;

  private static boolean getActionMapHasBeenInvoked;

  private static boolean getInputMapHasBeenInvoked;

  static {
    assert ESC != null;
  }

  private JRootPanes() {
    throw new UnsupportedOperationException();
  }

  /*
   * ActionMap methods.
   */


  private static final ActionMap getActionMap() {
    assert EventQueue.isDispatchThread();
    final ActionMap actionMap;
    if (!getActionMapHasBeenInvoked) {
      getActionMapHasBeenInvoked = true;
      actionMap = new ActionMap() {
        public final ActionMap getParent() {
          assert EventQueue.isDispatchThread();
          final UIDefaults
              lookAndFeelDefaults = UIManager.getLookAndFeelDefaults();
          if (lookAndFeelDefaults != null) {
            return (ActionMap) lookAndFeelDefaults.get("RootPane.actionMap");
          }
          return null;
        }
      };
      UIManager.put("RootPane.actionMap", actionMap);
    } else {
      actionMap = (ActionMap) UIManager.get("RootPane.actionMap");
    }
    assert actionMap != null;
    assert actionMap == UIManager.get("RootPane.actionMap");
    assert actionMap !=
        UIManager.getLookAndFeelDefaults().get("RootPane.actionMap");
    assert actionMap.getParent() ==
        UIManager.getLookAndFeelDefaults().get("RootPane.actionMap");

    return actionMap;
  }

  public static final ActionMap put(final Object key, final Action action) {
    assert EventQueue.isDispatchThread();
    final ActionMap actionMap = getActionMap();
    assert actionMap != null; if (key != null && action != null) {
      actionMap.put(key, action);
    }
    return actionMap;
  }

  public static final ActionMap put(final Component component, final Object key,
                                    final Action action) {
    assert EventQueue.isDispatchThread(); ActionMap actionMap = null;
    if (component != null) {
      final JRootPane rootPane = SwingUtilities.getRootPane(component);
      if (rootPane != null) {
        actionMap = rootPane.getActionMap();
        if (actionMap != null && key != null) {
          actionMap.put(key, action);
        }
      }
    }
    return actionMap;
  }

  /*
   * InputMap methods.
   */


  public static final InputMap put(final KeyStroke keyStroke,
                                   final Object actionKey, final int condition) {
    assert EventQueue.isDispatchThread(); InputMap inputMap = null;
    Toolkit toolkit = null;
    if (condition == JComponent.WHEN_IN_FOCUSED_WINDOW) {
      if (keyStroke != null) {
        toolkit = Toolkit.getDefaultToolkit();
        assert toolkit != null; if (injector == null) {
          injector = new JRootPaneInputMapInjector(JComponent.
              WHEN_IN_FOCUSED_WINDOW);
          toolkit.addAWTEventListener(injector, AWTEvent.HIERARCHY_EVENT_MASK);
        }
        assert injector != null; inputMap = injector.inputMap;
      }
    } else if (condition == JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
      inputMap = getInputMap("RootPane.ancestorMap");
    } else if (condition == JComponent.WHEN_FOCUSED) {
      inputMap = getInputMap("RootPane.focusInputMap");
    } else {
      throw new IllegalArgumentException("condition: " + condition);
    }
    if (inputMap != null && keyStroke != null) {
      inputMap.put(keyStroke, actionKey);
      if (inputMap.size() <= 0 &&
          condition == JComponent.WHEN_IN_FOCUSED_WINDOW) {
        assert toolkit != null; toolkit.removeAWTEventListener(injector);
        injector = null;
      }
    }
    return inputMap;
  }

  private static final InputMap getInputMap(final String uiDefaultsKey) {
    assert EventQueue.isDispatchThread(); InputMap inputMap = null;
    if (uiDefaultsKey != null) {
      if (!getInputMapHasBeenInvoked) {
        getInputMapHasBeenInvoked = true;
        inputMap = new InputMap() {
          @Override public final InputMap getParent() {
            assert EventQueue.isDispatchThread(); final UIDefaults
                lookAndFeelDefaults = UIManager.getLookAndFeelDefaults();
            if (lookAndFeelDefaults != null) {
              return (InputMap) lookAndFeelDefaults.get(uiDefaultsKey);
            }
            return null;
          }
        };
        UIManager.put(uiDefaultsKey, inputMap);
      } else {
        inputMap = (InputMap) UIManager.get(uiDefaultsKey);
      }
    }
    return inputMap;
  }

  public static final InputMap put(final Component component,
                                   final KeyStroke keyStroke,
                                   final Object actionKey, final int condition) {
    assert EventQueue.isDispatchThread(); InputMap inputMap = null;
    if (component != null) {
      final JRootPane rootPane = SwingUtilities.getRootPane(component);
      if (rootPane != null) {
        inputMap = rootPane.getInputMap(condition);
        if (inputMap != null && keyStroke != null) {
          inputMap.put(keyStroke, actionKey);
        }
      }
    }
    return inputMap;
  }

  /*
   * Inner and nested classes.
   */


  public static class CloseWindowAction
      extends AbstractAction {

    public CloseWindowAction() {
      this("Close");
    }

    public CloseWindowAction(final String name) {
      super(name);
      assert EventQueue.isDispatchThread(); this.putValue(Action.
          ACTION_COMMAND_KEY, "close");
    }

    public CloseWindowAction(final String name, final Icon icon) {
      super(name, icon);
      assert EventQueue.isDispatchThread(); this.putValue(Action.
          ACTION_COMMAND_KEY, "close");
    }

    public final void actionPerformed(final ActionEvent event) {
      assert EventQueue.isDispatchThread(); if (event != null && this.isEnabled()) {
        final Object source = event.getSource();
        if (source instanceof Component) {
          final Component component = (Component) source;
          final Container container = SwingUtilities.getAncestorOfClass(
              RootPaneContainer.class, component);
          if (container instanceof JInternalFrame) {
            final JInternalFrame internalFrame = (JInternalFrame) container;
            if (internalFrame.isClosable()) {
              internalFrame.doDefaultCloseAction();
            }
          } else {
            final Window window;
            if (container instanceof Window) {
              window = (Window) container;
            } else {
              window = SwingUtilities.getWindowAncestor(component);
            }
            if (window != null) {
              window.dispatchEvent(new WindowEvent(window,
                  WindowEvent.WINDOW_CLOSING));
            }
          }
        }
      }
    }

  }


  private static final class JRootPaneInputMapInjector
      implements AWTEventListener {

    private final InputMap inputMap;

    private final int condition;

    private JRootPaneInputMapInjector() {
      this(JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private JRootPaneInputMapInjector(final int condition) {
      super();
      assert EventQueue.isDispatchThread(); this.inputMap = new InputMap();
      this.condition = condition;
      switch (condition) {
        case JComponent.WHEN_IN_FOCUSED_WINDOW:
          break;
        case JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT:
          break;
        case JComponent.WHEN_FOCUSED:
          break;
        default:
          throw new IllegalArgumentException("condition");
      }
    }

    public final void eventDispatched(final AWTEvent event) {
      assert EventQueue.isDispatchThread();
      assert event != null;

      if (event instanceof
          HierarchyEvent && event.getID() == HierarchyEvent.HIERARCHY_CHANGED &&
          this.inputMap != null) {
        final HierarchyEvent hevent = (HierarchyEvent) event;
        final Component changed = hevent.getChanged();
        if ( ( (hevent.getChangeFlags() & HierarchyEvent.DISPLAYABILITY_CHANGED) !=
              0) && changed.isDisplayable() && changed instanceof JRootPane) {
          final InputMap inputMap = ( (JRootPane) changed).getInputMap(this.
              condition);
          if (inputMap != null && this.inputMap.size() > 0) {
            final KeyStroke[] allKeys = this.inputMap.allKeys();
            if (allKeys != null && allKeys.length > 0) {
              for (final KeyStroke k : allKeys) {
                if (k != null) {
                  inputMap.put(k, this.inputMap.get(k));
                }
              }
            }
          }
        }
      }
    }

    public final int hashCode() {
      return 0xCAFEBABE;
    }

    public final boolean equals(final Object other) {
      return other instanceof JRootPaneInputMapInjector;
    }

  }

}
