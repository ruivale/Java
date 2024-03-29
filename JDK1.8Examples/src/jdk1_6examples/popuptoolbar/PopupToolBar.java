/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdk1_6examples.popuptoolbar;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

public class PopupToolBar {
	/**
	 * Dispose all given windows if one of them lose the focus.
	 * @author Thierry LEFORT
	 * 26 mars 08
	 *
	 */
	private class DialogFocusListener implements WindowFocusListener {

		public void windowGainedFocus(WindowEvent e) { }

		public void windowLostFocus(WindowEvent e) {
			if (windows == null || windows.length == 0) {
				return;
			}
			if (!Arrays.asList(windows).contains(e.getOppositeWindow())) {
				disposeAllWindows();
			}
		}
	}
	
	private JToolBar verticalToolBar;
	
	private JToolBar horizontalToolBar;
	
	private int inset = 2;
	
	private Window[] windows;
	
	public PopupToolBar() {
		
	}
	
	public PopupToolBar(int inset) {
		this.inset = inset;
	}
	
	protected JToolBar createToolBar(int alignment) {
		JToolBar toolBar = new JToolBar(alignment) {
			@Override
			protected JButton createActionComponent(Action a) {
				final JButton button = super.createActionComponent(a);
				button.setFocusable(false);
//				button.addMouseMotionListener(new MouseMotionAdapter() {
//					public void mouseMoved(MouseEvent e) {
//					}
//				});
				return button;
			}
		};
		toolBar.setFloatable(false);
		return toolBar;
	}
	
	private Action encapsulateAction(final AbstractAction action) {
		AbstractAction result = new AbstractAction() {
		
			public void actionPerformed(ActionEvent e) {
				action.actionPerformed(e);
				disposeAllWindows();
			}
		};
//		result.putValue(Action.SHORT_DESCRIPTION, 
//				action.getValue(Action.SHORT_DESCRIPTION));
		result.setEnabled(action.isEnabled());
		PropertyChangeListener[] listeners = action.getPropertyChangeListeners();
		for (int i = 0; listeners != null && i < listeners.length; i++) {
			result.addPropertyChangeListener(listeners[i]);
		}
		Object[] keys = action.getKeys();
		for (int i = 0; i < keys.length; i++) {
			result.putValue((String) keys[i], action.getValue((String) keys[i]));
		}
		return result;
	}
	
	/**
	 * Add an action to 
	 * @param action the action to add
	 * @param alignment the Alignment of the ToolBar 
	 * either JToolBar.VERTICAL or JToolBar.HORIZONTAL
	 */
	public void add(AbstractAction action, int alignment) {
		switch (alignment) {
		case JToolBar.VERTICAL:
			if (verticalToolBar == null) {
				verticalToolBar = createToolBar(alignment);
			}
			verticalToolBar.add(encapsulateAction(action));
			break;
		case JToolBar.HORIZONTAL:
			if (horizontalToolBar == null) {
				horizontalToolBar = createToolBar(alignment);
			}
			horizontalToolBar.add(encapsulateAction(action));
			break;
		default:
			throw new IllegalArgumentException("Unknown alignment " + alignment);
		}
	}
	/**
	 * Retreive the tool bar
	 * @param alignment
	 * @return
	 */
	private JToolBar getToolBar(int alignment) {
		switch (alignment) {
		case JToolBar.VERTICAL:
			return verticalToolBar;
		case JToolBar.HORIZONTAL:
			return horizontalToolBar;
		default:
			throw new IllegalArgumentException("Unknown alignment " + alignment);
		}
	}
	/**
	 * Adjust the point depending on the alignment.
	 */
	private Point adujstPoint(Point initPoint, int popupWidth, int popupHeight, int alignment) {
		switch (alignment) {
		case JToolBar.VERTICAL:
			return new Point(initPoint.x - popupWidth - inset, initPoint.y);
		case JToolBar.HORIZONTAL:
			return new Point(initPoint.x, initPoint.y - popupHeight - inset);
		default:
			throw new IllegalArgumentException("Unknown alignment " + alignment);
		}
	}
	/**
	 * Create the Window for the given alignment and 
	 * @param comp father component
	 * @param alignment algenment 
	 * @param point location point
	 * @return the created window 
	 */
	protected Window createWindow(Component comp, 
			int alignment,
			Point point) {
		JToolBar bar = getToolBar(alignment);
		if (bar == null) {
			return null;
		}
		final JDialog dialog = new JDialog(JOptionPane.getFrameForComponent(comp));
		dialog.addMouseMotionListener(new MouseMotionAdapter() {
		
			public void mouseMoved(MouseEvent e) {
				if (!dialog.hasFocus())
				dialog.requestFocusInWindow();
			}
		
		});
		dialog.setUndecorated(true);
		dialog.setLayout(new BorderLayout());
		dialog.add(bar);
		dialog.pack();
		Point loc = adujstPoint(point, 
				dialog.getPreferredSize().width, 
				dialog.getPreferredSize().height, 
				alignment);
		SwingUtilities.convertPointToScreen(loc, comp);
		dialog.setLocation(loc);
		return dialog;
	}
	/**
	 * Creates the windows with the according toolbars and display them
	 * @param comp
	 * @param x
	 * @param y
	 */
	public void show(Component comp, int x, int y) {
		Window horizontalWindow = createWindow(
				comp, 
				JToolBar.HORIZONTAL, 
				new Point(x, y));
		Window verticalWindow = createWindow(comp, 
				JToolBar.VERTICAL, 
				new Point(x, y));
		if (horizontalWindow == null && verticalWindow == null) {
			return;
		}
		ArrayList<Window> windowList = new ArrayList<Window>();
		if (horizontalWindow != null) {
			windowList.add(horizontalWindow);
		}
		if (verticalWindow != null) {
			windowList.add(verticalWindow);
		}
		windows = windowList.toArray(new Window[windowList.size()]);
		windows[0].requestFocusInWindow();
		DialogFocusListener focusListener = new DialogFocusListener();
		for (int i = 0; i < windows.length; i++) {
			windows[i].addWindowFocusListener(focusListener);
			windows[i].setVisible(true);
		}
	}
	
	private void disposeAllWindows() {
		if (windows == null) {
			return;
		}
		for (int i = 0; i < windows.length; i++) {
			windows[i].dispose();
		}
	}
	

}
