package exp;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

///////////////////////////////////////////
// MultiLineLabel.java

import javax.swing.*;

/**
 * JLabel-like component with support for multiple lines of text (using '\n'
 * character). This is actually achieved by using a JTextArea and setting
some
 * properties to disable highlighting/selection. For obvious reasons, this
 * component only supports display of text. No images.
 *
 * @author Jeroen Zwartepoorte (Jeroen@xs4all.nl)
 * @author $Author$
 * @version $Revision$
 */
public class MultiLineLabel extends JTextArea {
  /**
   * Create a new instance with default settings.
   */
  public MultiLineLabel() {
    this("");
  }

  /**
   * Create a new instance with specified text.
   */
  public MultiLineLabel(String sText) {
    super(sText);
    setBackground(UIManager.getColor("Button.background"));
    setEditable(false);
    setHighlighter(null);
    setSelectedTextColor(UIManager.getColor("Label.foreground"));
    setForeground(UIManager.getColor("Label.foreground"));
  }

  /**
   * NOOP.
   */
  public void copy() {
  }
}

