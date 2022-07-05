package exp.swing.tooltips.changinginallswing;


import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.ColorUIResource;
import javax.swing.*;


/**
 * MyLookAndFeel subclasses MetalLookAndFeel to set a different background color
 * for the application Tooltip
 * @author Rahul Sapkal(rahul@javareference.com)
 */
public class MyLookAndFeel
    extends MetalLookAndFeel {
  /**
   * This method is overriden from MetalLookAndFeel to set a different color for
   * the ToolTip background
   */
  protected void initSystemColorDefaults(UIDefaults table) {
    //call the super method and populate the UIDefaults table
    super.initSystemColorDefaults(table);

    //After populating the UIDefaults table reset the info key value to the
    //desired color. In this case lightYellow
    table.put("info", new ColorUIResource(255, 255, 225));
  }

}
