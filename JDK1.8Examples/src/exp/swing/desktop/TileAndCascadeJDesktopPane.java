package exp.swing.desktop;

import javax.swing.*;
import java.awt.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TileAndCascadeJDesktopPane
    extends JDesktopPane {
  public TileAndCascadeJDesktopPane() {
  }

  public static void tile(JDesktopPane desktopPane, int layer) {
    JInternalFrame[] frames = desktopPane.getAllFramesInLayer(layer);
    if (frames.length == 0) {
      return;
    }

    tile(frames, desktopPane.getBounds());
  }

  public static void tile(JDesktopPane desktopPane) {
    JInternalFrame[] frames = desktopPane.getAllFrames();
    if (frames.length == 0) {
      return;
    }

    tile(frames, desktopPane.getBounds());
  }

  private static void tile(JInternalFrame[] frames, Rectangle dBounds) {
    int cols = (int) Math.sqrt(frames.length);
    int rows = (int) (Math.ceil( ( (double) frames.length) / cols));
    int lastRow = frames.length - cols * (rows - 1);
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
          frames[cols * rows + i].setBounds(i * width, rows * height,
                                            width, height);
        }
      }
    }

    width = dBounds.width / cols;
    for (int j = 0; j < rows; j++) {
      for (int i = 0; i < cols; i++) {
        frames[i + j * cols].setBounds(i * width, j * height,
                                       width, height);
      }
    }
  }

  public static void cascade(JDesktopPane desktopPane, int layer) {
    JInternalFrame[] frames = desktopPane.getAllFramesInLayer(layer);
    if (frames.length == 0) {
      return;
    }

    cascade(frames, desktopPane.getBounds(), 24);
  }

  public static void cascade(JDesktopPane desktopPane) {
    JInternalFrame[] frames = desktopPane.getAllFrames();
    if (frames.length == 0) {
      return;
    }

    cascade(frames, desktopPane.getBounds(), 24);
  }

  private static void cascade(JInternalFrame[] frames, Rectangle dBounds,
                              int separation) {
    int margin = frames.length * separation + separation;
    int width = dBounds.width - margin;
    int height = dBounds.height - margin;
    for (int i = 0; i < frames.length; i++) {
      frames[i].setBounds(separation + dBounds.x + i * separation,
                          separation + dBounds.y + i * separation,
                          width, height);
    }
  }

  public static void main(String[] args) {
    TileAndCascadeJDesktopPane tileAndCascadeJDesktopPane1 = new
        TileAndCascadeJDesktopPane();
  }

}
