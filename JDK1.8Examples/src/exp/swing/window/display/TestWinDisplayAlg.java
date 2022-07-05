package exp.swing.window.display;

import java.awt.*;
import javax.swing.*;

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
public class TestWinDisplayAlg {

  final int ww = 352;
  final int wh = 288;
  final Rectangle dBounds = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
  final int cols = dBounds.width / ww;
  final int rows = (dBounds.height / wh);// + 1;
  final JFrame[] wins = new JFrame[]{
    new JFrame("1111"),
    new JFrame("22222"),
    new JFrame("3333"),
    new JFrame("4444"),
    new JFrame("55555")
  };
  JFrame f = new JFrame("FRAME");

  /**
   *
   */
  public TestWinDisplayAlg() {

    f.setBounds(0,0,1152, 864);
    f.setVisible(true);

    try {
      Thread.sleep(5000);
    } catch (Exception ex) {}

    while (true) {
      for (int i = 0; i < 4; i++) {
        try {
          Thread.sleep(3000);
        } catch (Exception ex) {}

        //tile(wins, i);
        //line(wins, i);
        sharing(wins, i);
      }
    }

  }

  private void tile(JFrame[] wins, int intSelectedMode){
    int x = 0;
    int y = 0;
    for (int i = 0; i < wins.length; i++) {
      wins[i].setSize(ww, wh);
      switch (intSelectedMode) {
        case 0: // bottom to top v
          System.out.println("bottom to top");
          wins[i].setTitle(i+" bottom to top");
          x = (i % cols) * ww;
          y = ((rows-(i / rows)) * wh) - wh;
          break;

        case 1: // left to right v
          System.out.println("left to right");
          wins[i].setTitle(i+" left to right");
          x = (i / rows) * ww;
          y = (i % cols) * wh;
          break;

        case 2: // right to left
          System.out.println("right to left");
          wins[i].setTitle(i+" right to left");
          x = ((rows - (i / rows)) * ww) - ww;
          y = (i % cols) * wh;
          break;

        case 3: // top to bottom v
        default:
          System.out.println("top to bottom");
          wins[i].setTitle(i+" top to bottom");
          x = (i % cols) * ww;
          y = (i / rows) * wh;
      }
      wins[i].setLocation(x, y);
      wins[i].setVisible(true);
    }
    /* KOPIED
         int cols = (int) Math.sqrt(wins.length);
         int rows = (int) (Math.ceil( ( (double) wins.length) / cols));
         int lastRow = wins.length - cols * (rows - 1);
         int width, height;

         if (lastRow == 0) {
      rows--;
      height = dBounds.height / rows;
         } else {
      height = dBounds.height / rows;
      if (lastRow < cols) {
        rows--;
        width = dBounds.width / lastRow;
        for (int i = 0; i < lastRow; i++) {
          wins[cols * rows + i].setLocation(i * width, rows * height);
          wins[cols * rows + i].toFront();
        }
      }
         }

         width = dBounds.width / cols;
         for (int j = 0; j < rows; j++) {
      for (int i = 0; i < cols; i++) {
        wins[i + j * cols].setLocation(i * width, j * height);
        wins[i + j * cols].toFront();
      }
         }
     */
  }

  private void line(final JFrame[] wins, final int intSelectedMode){
    int x = 0;
    int y = 0;
    int spaceX = ww;
    int spaceY = wh;

    //
    if((wins.length * ww) > dBounds.width){
      spaceX = (dBounds.width - ww) / (wins.length -1);
    }

    if((wins.length * wh) > dBounds.height){
      spaceY = (dBounds.height - wh) / (wins.length -1);
    }

    for (int i = 0; i < wins.length; i++) {
      wins[i].setSize(ww, wh);
      switch (intSelectedMode) {
        case 0: // bottom
          System.out.println("bottom");
          wins[i].setTitle(i+" bottom");

          x = i*spaceX;
          y = dBounds.height - wh;
          break;

        case 1: // left
          System.out.println("left ");
          wins[i].setTitle(i+" left ");

          y = i*spaceY;
          break;

        case 2: // right
          System.out.println("right ");
          wins[i].setTitle(i+" right ");

          x = dBounds.width - ww;
          y =  i*spaceY;
          break;

        case 3: // top
        default:
          System.out.println("top ");
          wins[i].setTitle(i+" top ");

          x = i*spaceX;
      }
      wins[i].setLocation(x, y);
      wins[i].setVisible(true);
    }

  }

  private void sharing(final JFrame[] wins, final int intSelectedMode){

    line(wins, intSelectedMode);

    int intX = 0;
    int intY = 0;
    int intWW = 0; // Windows width
    int intWH = 0; // Windows height
    int intMainWinW = f.getWidth(); // Main windos width
    int intMainWinH = f.getHeight(); // Main  window height

    if(wins != null && wins.length > 0 && wins[0] != null){
      intWW = wins[0].getWidth();
      intWH = wins[0].getHeight();
    }

    switch (intSelectedMode) {
      case 0: // bootom
        intMainWinW = dBounds.width;
        intMainWinH = dBounds.height - intWH;
        break;

      case 1: // left
        intMainWinH = dBounds.height;
        intMainWinW = dBounds.width - intWW;
        intX = intWW;
        break;

      case 2: // right
        intMainWinH = dBounds.height;
        intMainWinW = dBounds.width - intWW;
        break;

      case 3: // top
      default:
        intY = intWH;
        intMainWinW = dBounds.width;
        intMainWinH = dBounds.height - intWH;
    }

    f.setBounds(intX,
                intY,
                intMainWinW,
                intMainWinH);

  }


  public static void main(String s[]){
    new TestWinDisplayAlg();
  }
}
