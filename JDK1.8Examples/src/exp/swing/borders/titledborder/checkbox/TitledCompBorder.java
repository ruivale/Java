package exp.swing.borders.titledborder.checkbox;

import java.awt.*;

import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


public class TitledCompBorder
    extends TitledBorder {
  protected JComponent component;

  public TitledCompBorder(JComponent component) {
    this(null, component, TitledBorder.LEFT, TitledBorder.TOP);
  }

  public TitledCompBorder(Border border,
                          JComponent component,
                          int titleJustification,
                          int titlePosition) {
    super(border, null, titleJustification,
          titlePosition, null, null);
    this.component = component;
    if (border == null) {
      this.border = super.getBorder();
    }
  }


  public void paintBorder(Component c, Graphics g,
                          int x, int y, int width, int height) {
    Rectangle borderR = new Rectangle(x + TitledBorder.EDGE_SPACING,
                                      y + TitledBorder.EDGE_SPACING,
                                      width - (TitledBorder.EDGE_SPACING * 2),
                                      height - (TitledBorder.EDGE_SPACING * 2));
    Insets borderInsets;
    if (border != null) {
      borderInsets = border.getBorderInsets(c);
    } else {
      borderInsets = new Insets(0, 0, 0, 0);
    }

    Rectangle rect = new Rectangle(x, y, width, height);
    Insets insets = getBorderInsets(c);
    Rectangle compR = getComponentRect(rect, insets);
    int diff;
    switch (titlePosition) {
    case TitledBorder.ABOVE_TOP:
      diff = compR.height + TitledBorder.TEXT_SPACING;
      borderR.y += diff;
      borderR.height -= diff;
      break;
    case TitledBorder.TOP:
    case TitledBorder.DEFAULT_POSITION:
      diff = insets.top / 2 - borderInsets.top - TitledBorder.EDGE_SPACING;
      borderR.y += diff;
      borderR.height -= diff;
      break;
    case TitledBorder.BELOW_TOP:
    case TitledBorder.ABOVE_BOTTOM:
      break;
    case TitledBorder.BOTTOM:
      diff = insets.bottom / 2 - borderInsets.bottom - TitledBorder.EDGE_SPACING;
      borderR.height -= diff;
      break;
    case TitledBorder.BELOW_BOTTOM:
      diff = compR.height + TitledBorder.TEXT_SPACING;
      borderR.height -= diff;
      break;
    }
    border.paintBorder(c, g, borderR.x, borderR.y,
                       borderR.width, borderR.height);
    Color col = g.getColor();
    g.setColor(c.getBackground());
    g.fillRect(compR.x, compR.y, compR.width, compR.height);
    g.setColor(col);
    component.repaint();
  }


  public Insets getBorderInsets(Component c, Insets insets) {
    Insets borderInsets;
    if (border != null) {
      borderInsets = border.getBorderInsets(c);
    } else {
      borderInsets = new Insets(0, 0, 0, 0);
    }
    insets.top = TitledBorder.EDGE_SPACING + TitledBorder.TEXT_SPACING + borderInsets.top;
    insets.right = TitledBorder.EDGE_SPACING + TitledBorder.TEXT_SPACING + borderInsets.right;
    insets.bottom = TitledBorder.EDGE_SPACING + TitledBorder.TEXT_SPACING + borderInsets.bottom;
    insets.left = TitledBorder.EDGE_SPACING + TitledBorder.TEXT_SPACING + borderInsets.left;

    if (c == null || component == null) {
      return insets;
    }

    int compHeight = 0;
    if (component != null) {
      compHeight = component.getPreferredSize().height;
    }

    switch (titlePosition) {
    case TitledBorder.ABOVE_TOP:
      insets.top += compHeight + TitledBorder.TEXT_SPACING;
      break;
    case TitledBorder.TOP:
    case TitledBorder.DEFAULT_POSITION:
      insets.top += Math.max(compHeight, borderInsets.top) - borderInsets.top;
      break;
    case TitledBorder.BELOW_TOP:
      insets.top += compHeight + TitledBorder.TEXT_SPACING;
      break;
    case TitledBorder.ABOVE_BOTTOM:
      insets.bottom += compHeight + TitledBorder.TEXT_SPACING;
      break;
    case TitledBorder.BOTTOM:
      insets.bottom += Math.max(compHeight, borderInsets.bottom) -
          borderInsets.bottom;
      break;
    case TitledBorder.BELOW_BOTTOM:
      insets.bottom += compHeight + TitledBorder.TEXT_SPACING;
      break;
    }

    return insets;
  }

  public JComponent getTitleComponent() {
    return component;
  }

  public void setTitleComponent(JComponent component) {
    this.component = component;
  }


  public Rectangle getComponentRect(Rectangle rect, Insets borderInsets) {
    Dimension compD = component.getPreferredSize();
    Rectangle compR = new Rectangle(0, 0, compD.width, compD.height);
    switch (titlePosition) {
    case TitledBorder.ABOVE_TOP:
      compR.y = TitledBorder.EDGE_SPACING;
      break;
    case TitledBorder.TOP:
    case TitledBorder.DEFAULT_POSITION:
      compR.y = TitledBorder.EDGE_SPACING +
                (borderInsets.top - TitledBorder.EDGE_SPACING - TitledBorder.TEXT_SPACING - compD.height) /
                2;
      break;
    case TitledBorder.BELOW_TOP:
      compR.y = borderInsets.top - compD.height - TitledBorder.TEXT_SPACING;
      break;
    case TitledBorder.ABOVE_BOTTOM:
      compR.y = rect.height - borderInsets.bottom + TitledBorder.TEXT_SPACING;
      break;
    case TitledBorder.BOTTOM:
      compR.y = rect.height - borderInsets.bottom + TitledBorder.TEXT_SPACING +
                (borderInsets.bottom - TitledBorder.EDGE_SPACING - TitledBorder.TEXT_SPACING -
                 compD.height) / 2;
      break;
    case TitledBorder.BELOW_BOTTOM:
      compR.y = rect.height - compD.height - TitledBorder.EDGE_SPACING;
      break;
    }
    switch (titleJustification) {
    case TitledBorder.LEFT:
    case TitledBorder.DEFAULT_JUSTIFICATION:
      compR.x = TitledBorder.TEXT_INSET_H + borderInsets.left;
      break;
    case TitledBorder.RIGHT:
      compR.x = rect.width - borderInsets.right - TitledBorder.TEXT_INSET_H - compR.width;
      break;
    case TitledBorder.CENTER:
      compR.x = (rect.width - compR.width) / 2;
      break;
    }
    return compR;
  }

}
