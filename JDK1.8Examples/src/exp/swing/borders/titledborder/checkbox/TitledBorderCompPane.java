package exp.swing.borders.titledborder.checkbox;


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
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.*;


public class TitledBorderCompPane extends JPanel {
  protected TitledCompBorder border;
  protected JComponent component;
  protected JPanel panel;
  protected boolean transmittingAllowed;
  protected StateChangingDescendant descendant;

  public TitledBorderCompPane(JComponent component) {
    this.component = component;
    border = new TitledCompBorder(component);
    setBorder(border);
    panel = new JPanel();
    setLayout(null);
    add(component);
    add(panel);
    transmittingAllowed = false;
    descendant = null;
  }

  public JComponent getTitleComponent() {
    return component;
  }

  public void setTitleComponent(JComponent newComponent) {
    remove(component);
    add(newComponent);
    border.setTitleComponent(newComponent);
    component = newComponent;
  }

  public JPanel getContentPane() {
    return panel;
  }

  public void doLayout() {
    Insets insets = getInsets();
    Rectangle rect = getBounds();
    rect.x = 0;
    rect.y = 0;

    Rectangle compR = border.getComponentRect(rect,insets);
    component.setBounds(compR);
    rect.x += insets.left;
    rect.y += insets.top;
    rect.width  -= insets.left + insets.right;
    rect.height -= insets.top  + insets.bottom;
    panel.setBounds(rect);
  }


  public void setTransmittingAllowed(boolean enable) {
    transmittingAllowed = enable;
  }

  public boolean getTransmittingAllowed() {
    return transmittingAllowed;
  }

  public void setTransmitter(StateChangingDescendant transmitter) {
    this.descendant = transmitter;
  }

  public StateChangingDescendant getTransmitter() {
    return descendant;
  }

  public void setEnabled(boolean enable) {
    super.setEnabled(enable);
    if (transmittingAllowed && descendant != null) {
      descendant.setDescendantsEnabled(enable);
    }
  }

}
