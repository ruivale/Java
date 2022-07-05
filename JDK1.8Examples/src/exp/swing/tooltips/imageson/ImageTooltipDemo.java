package exp.swing.tooltips.imageson;


import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.metal.MetalToolTipUI;
import exp.swing.tooltips.changinginallswing.DemoFrame;


;

/**
 * This class demonstrates displaying image
 * in a tooltip
 * @author Rahul Sapkal(rahul@javareference.com)
 */
public class ImageTooltipDemo
    extends JFrame {
  private String m_wonders[] = {"The Pyramids of Egypt",
                               "Hanging Gardens of Babylon",
                               "Statue of Zeus at Olympia",
                               "Colossus of Rhodes",
                               "Temple of Artemis at Ephesus",
                               "Mausoleum of Helicarnassus",
                               "Pharos (Lighthouse) of Alexandria"};

  private Image m_wonderIcons[], m_wonderImages[];

  public ImageTooltipDemo() {
    //get all images needed
    getAllImages();

    Container appCont = getContentPane();

    appCont.setLayout(new BorderLayout(5, 5));

    JPanel wondersPanel = new JPanel(new GridLayout(m_wonders.length, 1, 3, 3));

    for (int won = 0; won < m_wonders.length; won++) {
      final int wonCount = won;

      //create the JLabel
      JLabel wonLabel = new JLabel((won + 1) + ". " + m_wonders[won],
                                   new ImageIcon(m_wonderIcons[won]),
                                   JLabel.LEFT) {
        /**
         * This method is overriden from JLabel
         * to create and return the ImageToolTip,
         */
        public JToolTip createToolTip() {
          //create the ImageToolTip by passing the
          //image to appear on it
          return new ImageToolTip(m_wonderImages[wonCount]);
        }
      };

      //set the tooltip text
      wonLabel.setToolTipText(m_wonders[won]);

      wondersPanel.add(wonLabel);
    }

    appCont.add(BorderLayout.NORTH,
                new JLabel("<html>THE SEVEN WONDERS OF THE ANCIENT WORLD<br>" +
                           "(Mouse Over to see the picture in tooltip)<br>" +
                           "</html>",
                           JLabel.CENTER));
    appCont.add(BorderLayout.CENTER, wondersPanel);
  }

  /**
   * Get all the images
   *
   */
  private void getAllImages() {
    m_wonderIcons = new Image[m_wonders.length];
    m_wonderImages = new Image[m_wonders.length];

    MediaTracker mt = new MediaTracker(this);

    for (int won = 0; won < m_wonders.length; won++) {
      //Icons Images are named as wi1.gif ... wi7.gif
      m_wonderIcons[won] = new ImageIcon(
        "D:/Projects/exp/images/wi"+won+".jpg").getImage();

      //Images are named as w1.gif ... w7.gif
      m_wonderImages[won] = new ImageIcon(
        "D:/Projects/exp/images/w"+won+".jpg").getImage();

      mt.addImage(m_wonderIcons[won], 100 + won); //100 + is just the id
      mt.addImage(m_wonderImages[won], 200 + won); //200 + is just the id
    }

    try {
      mt.waitForAll();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * This class extends JToolTip and set the UI
   * to ImageToolTipUI.
   *
   * @author Rahul Sapkal(rahul@javareference.com)
   */
  public class ImageToolTip
      extends JToolTip {
    public ImageToolTip(Image image) {
      setUI(new ImageToolTipUI(image));
    }
  }


  /**
   * This class extends MetalToolTipUI
   * and provides customizes it to draw
   * a given image on it.
   *
   * @author Rahul Sapkal(rahul@javareference.com)
   */
  public class ImageToolTipUI
      extends MetalToolTipUI {
    private Image m_image;

    public ImageToolTipUI(Image image) {
      m_image = image;
    }

    /**
     * This method is overriden from the MetalToolTipUI
     * to draw the given image and text
     */
    public void paint(Graphics g, JComponent c) {
      FontMetrics metrics = c.getFontMetrics(g.getFont());
      //Dimension size = c.getSize();
      //g.setColor(c.getBackground());
      //g.fillRect(0, 0, size.width, size.height);
      g.setColor(c.getForeground());

      g.drawString(((JToolTip) c).getTipText(), 3, 15);

      g.drawImage(m_image, 3, metrics.getHeight() + 3, c);
    }

    /**
     * This method is overriden from the MetalToolTipUI
     * to return the appropiate preferred size to size the
     * ToolTip to show both the text and image.
     */
    public Dimension getPreferredSize(JComponent c) {
      FontMetrics metrics = c.getFontMetrics(c.getFont());
      String tipText = ((JToolTip) c).getTipText();
      if (tipText == null) {
        tipText = "";
      }

      int width = SwingUtilities.computeStringWidth(metrics, tipText);
      int height = metrics.getHeight() + m_image.getHeight(c) + 6;

      if (width < m_image.getWidth(c)) {
        width = m_image.getWidth(c);
      }

      return new Dimension(width, height);
    }
  }

  public static void main(String[] arg) {
     ImageTooltipDemo m = new ImageTooltipDemo();

     m.setVisible(true);
     m.setSize(new Dimension(300, 150));
     m.validate();
   }

}
