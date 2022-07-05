// %1210255173039:%
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exp.layouts.tile;


/*
 * Created on 13.09.2005
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;


/**
 * 
DOCUMENT ME!
 *
 * @author Manuel Alabor
 * @version 1.0
 */
public class TileList
    extends JComponent {
  /**
   * Creates a new TileList object.
   */
  public TileList() {
    setLayout(new TileLayout());

    for(int i = 0, l = 20; i < l; i++) {
      final int number = i + 1;
      JComponent panel =
        new JComponent() {
          protected void paintComponent(final Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.fillRect(
              0,
              0,
              getWidth(),
              getHeight());
            g.setColor(Color.WHITE);
            g.drawString(
              String.valueOf(number),
              10,
              20);
          }
        };

      panel.setMinimumSize(
          new Dimension(
            48,
            48));
      panel.setPreferredSize(
          new Dimension(
            48,
            48));

      add(panel);
    }
  }

  /**
   *
   *
   * @return 
   */
  public Dimension getPreferredSize() {
    int componentCount      = getComponentCount();
    Dimension preferredSize = null;

    if(componentCount > 0) {
      Component prototype              = getComponent(0);
      Dimension prototypePreferredSize = prototype.getPreferredSize();
      int prototypeH                   =
        new Double(prototypePreferredSize.getHeight()).intValue();

      prototypeH += getVGap();

      int realRows   =
        new Double(
            Math.round(
                new Double(componentCount * prototypeH).doubleValue() / new Double(
                     getWidth()).doubleValue())).intValue();

      int preferredW = (componentCount / realRows);
      int preferredH = realRows * prototypeH;

      preferredSize  = new Dimension(
          preferredW,
          preferredH);
    } else {
      preferredSize = super.getPreferredSize();
    }

    return preferredSize;
  }

  /**
   *
   *
   * @param args 
   */
  public static void main(final String[] args) {
    JFrame frame = new JFrame("Test");

    frame.setSize(
      400,
      300);

    JScrollPane scroller = new JScrollPane(new TileList());

    frame.getContentPane()
         .add(scroller);
//        frame.getContentPane().add(new TileList());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.show();
  }

  /**
   *
   *
   * @return 
   */
  protected int getHGap() {
    TileLayout layout = (TileLayout)getLayout();
    int hgap          = layout.getHGap();

    return hgap;
  }

  /**
   *
   *
   * @return 
   */
  protected int getVGap() {
    TileLayout layout = (TileLayout)getLayout();
    int vgap          = layout.getVGap();

    return vgap;
  }

  /**
   * Das TileLayout ordnet alle Components gekachelt an. Ist auf einer
   * Zeile kein Platz mehr für weitere Components, so wird eine neue Zeile begonnen.<br>
   * Mit dem TileLayout ergeben sich somit nur vertikal scrollbare Container.<br>
   * <br>
   * WICHTIG! Alle Components welche mit dem TileLayout ausgerichtet werden,
   * müssen unbedingt über die selben Abmessungen verfügen!
   *
   * @author Manuel Alabor
   */
  private class TileLayout
      implements LayoutManager {
    /**  */
    public static final int DEFAULT_GAPSIZE = 3;

    /**  */
    private int hgap;

    /**  */
    private int vgap;

    // Kontruktoren --------------------------------------------------------
    /**
     * Standartkonstruktor.
     */
    public TileLayout() {
      hgap   = DEFAULT_GAPSIZE;
      vgap   = DEFAULT_GAPSIZE;
    }

    /**
     * 
    DOCUMENT ME!
     *
     * @param hgap
     * @param vgap
     */
    public TileLayout(
      final int hgap,
      final int vgap) {
      this.hgap   = hgap;
      this.vgap   = vgap;
    }

    // Zugriff -------------------------------------------------------------
    /**
     *
     *
     * @return 
     */
    public int getHGap() {
      return hgap;
    }

    /**
     *
     *
     * @return 
     */
    public int getVGap() {
      return vgap;
    }

    /**
     *
     *
     * @param name 
     * @param comp 
     */
    public void addLayoutComponent(
      final String name,
      final Component comp) {
    }

    // LayoutManager-Implementierung ---------------------------------------
    /**
     *
     *
     * @param target 
     */
    public void layoutContainer(final Container target) {
      synchronized(target.getTreeLock()) {
        Insets insets     = target.getInsets();
        int x             = insets.left;
        int y             = insets.top;
        int avaibledWidth = target.getWidth();

        for(int i = 0, l = target.getComponentCount(); i < l; i++) {
          Component component = target.getComponent(i);

          if(component.isVisible()) {
            Dimension preferredSize = component.getPreferredSize();
            int width               = preferredSize.width;
            int height              = preferredSize.height;

            component.setBounds(
              x,
              y,
              width,
              height);

            x += (width + hgap);

            /* Prüfen: */
            // Hätte noch eine Component auf dieser Zeile platz?
            // Falls nicht, die Positionen für die nächste Zeile
            // entsprechend setzen.
            if((x + width) >= avaibledWidth) {
              x = insets.left;
              y += (height + vgap);
            }
          }
        }
      }
    }

    /**
     *
     *
     * @param target 
     *
     * @return 
     */
    public Dimension minimumLayoutSize(final Container target) {
      synchronized(target.getTreeLock()) {
        Dimension dim        = new Dimension(0, 0);
        Insets insets        = target.getInsets();
        int horizontalInsets = insets.left + insets.right;
        int avaibledWidth    =
          target.getParent()
                .getParent()
                .getWidth() - horizontalInsets;
        int componentCount   = target.getComponentCount();

        if(componentCount > 0) {
          // Da für das TileLayout ALLE Components gleich gross
          // sein müssen, wird hier entsprechend einfach die erste
          // Component für das Berechnen verwendet.
          Component component     = target.getComponent(0);
          Dimension preferredSize = component.getPreferredSize();

          //int totalTileWidth = preferredSize.width + hgap;
          int totalTileHeight = preferredSize.height + vgap;
          int realRows        =
            new Double(
                Math.round(
                    new Double(componentCount * totalTileHeight).doubleValue() / new Double(
                         avaibledWidth).doubleValue())).intValue();

          int height = realRows * totalTileHeight;

          dim.width    = avaibledWidth;
          dim.height   = height;
        }

        dim.width += horizontalInsets;
        dim.height += (insets.top + insets.bottom);

        return dim;
      }
    }

    /**
     *
     *
     * @param target 
     *
     * @return 
     */
    public Dimension preferredLayoutSize(final Container target) {
      synchronized(target.getTreeLock()) {
        Dimension dim        = new Dimension(0, 0);
        Insets insets        = target.getInsets();
        int horizontalInsets = insets.left + insets.right;
        int avaibledWidth    = target.getWidth(); // - horizontalInsets;
        int componentCount   = target.getComponentCount();

        if(componentCount > 0) {
          // Da für das TileLayout ALLE Components gleich gross
          // sein müssen, wird hier entsprechend einfach die erste
          // Component für das Berechnen verwendet.
          Component component     = target.getComponent(0);
          Dimension preferredSize = component.getPreferredSize();

          //int totalTileWidth = preferredSize.width + hgap;
          int totalTileHeight = preferredSize.height + vgap;
          int realRows        =
            new Double(
                Math.round(
                    new Double(componentCount * totalTileHeight).doubleValue() / new Double(
                         avaibledWidth).doubleValue())).intValue();

          int height = realRows * totalTileHeight;

          dim.width    = avaibledWidth;
          dim.height   = height;
        }

        dim.width += horizontalInsets;
        dim.height += (insets.top + insets.bottom);

        return dim;
      }
    }

    /**
     *
     *
     * @param comp 
     */
    public void removeLayoutComponent(final Component comp) {
    }
  }
}