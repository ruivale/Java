package exp.screen;


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

public class GetAvailableScreenSize {

  public GetAvailableScreenSize() {
    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    Rectangle bounds = env.getMaximumWindowBounds();
    System.out.println("Rect("+bounds.x+","+bounds.y+","+bounds.width+","+bounds.height+")");
  }
  public static void main(String[] args) {
    GetAvailableScreenSize getAvailableScreenSize1 = new GetAvailableScreenSize();
  }
}