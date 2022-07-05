package exp.labels;

import javax.swing.*;
import java.awt.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class UnderlinedLabel extends java.awt.Label {

  public UnderlinedLabel() {
  }
  public UnderlinedLabel(String text) {
    super(text);
  }

  public void _paint(Graphics g) {
    Rectangle r;
    super.paint(g);
    //here's the trick
    r = g.getClipBounds();
    g.drawLine(0,
      r.height - this.getFontMetrics(this.getFont()).getDescent(),
      this.getFontMetrics(this.getFont()).stringWidth(this.getText()),
      r.height - this.getFontMetrics(this.getFont()).getDescent());
  }





  public static void main(String args[]){
    Frame f = new Frame();
    UnderlinedLabel l = new UnderlinedLabel("AAAAAaaaa");
    f.add(l);
    f.pack();
    f.setLocation(100,100);
    f.setVisible(true);
  }
}
