package exp.dnd.autoscrolling;


import javax.swing.*;
import java.awt.dnd.Autoscroll;
import java.awt.*;


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
public class MyList
    extends JList implements Autoscroll {
  AutoscrollSupport scrollSupport = new AutoscrollSupport(this,
      new Insets(10, 10, 10, 10));

  public MyList(ListModel model) {
    super(model);
  }

  public Insets getAutoscrollInsets() {
    return scrollSupport.getAutoscrollInsets();
  }

  public void autoscroll(Point cursorLocn) {
    scrollSupport.autoscroll(cursorLocn);
  }
}
