package exp.swing.icons.myown;


import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


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

public class ColorIcon
    implements Icon {
  private Color mainColor;
  private Color stateColor;
  private int w, h, x, y;

  public ColorIcon(Color mainColor,Color stateColor, int w, int h) {
    this.mainColor = mainColor;
    this.stateColor = stateColor;
    this.w = w;
    this.h = h;
  }

  public void paintIcon(Component c, Graphics g, int x, int y) {
    g.setColor(mainColor);
    g.fillOval(x, y, w, h);

    g.setColor(stateColor);
    g.fillOval(x+3, y+3, w-6, h-6);
    //g.fillOval(x+4, y+4, w-8, h-8);

    this.x = x;
    this.y = y;

  }

  public int getX(){
    return this.x;
  }

  public int getY(){
    return this.y;
  }

  public int getIconWidth() {
    return w;
  }

  public int getIconHeight() {
    return h;
  }

  public static void main(String s[]){
    IconTest it = new IconTest();
    JFrame f = new JFrame();
    f.getContentPane().add(it);
    f.setBounds(100,100,600,600);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }

}
