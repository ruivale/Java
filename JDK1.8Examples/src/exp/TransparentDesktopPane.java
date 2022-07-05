
/**
 * Title:        GUI test<p>
 * Description:  Test for the GUI aplication!<p>
 * Copyright:    Copyright (c) Rui Vale<p>
 * Company:      ENT<p>
 * @author Rui Vale
 * @version 1.0
 */
package exp;

import javax.swing.JDesktopPane;
import java.awt.Graphics;

public class TransparentDesktopPane extends JDesktopPane {
  /**
   * Constructor
   */
  public TransparentDesktopPane(){
    super();
    setOpaque(false);
  }
  // Just for simulate the setOpaque(false) method.
  protected void paintComponent(Graphics gc){
    // do nothing
  }
  public boolean isOpaque(){
    return(false);
  }
}
