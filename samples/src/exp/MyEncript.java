
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package exp;

import java.awt.*;


public class MyEncript extends Frame {
  BorderLayout borderLayout1 = new BorderLayout();

  public MyEncript() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    MyEncript myEncript1 = new MyEncript();
  }

  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
  }
}