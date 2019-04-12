
package swingfx.progresspanel;


import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

import java.awt.image.BufferedImage;
import net.java.swingfx.waitwithstyle.CancelableAdaptee;
import net.java.swingfx.waitwithstyle.CancelableProgessAdapter;
import net.java.swingfx.waitwithstyle.InfiniteProgressAdapter;
import net.java.swingfx.waitwithstyle.InfiniteProgressPanel;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class PerformanceInfiniteProgressPanelEFACEC  
    extends JComponent
    implements ActionListener, CancelableAdaptee {

    private static final int DEFAULT_NUMBER_OF_BARS = 12;
    private static final Font font = new Font("Dialog", Font.PLAIN, 11);
    private int numBars;
    private String strText = "";
    protected InfiniteProgressAdapter infiniteProgressAdapter;
    private double dScale = 1.2d;

    private MouseAdapter mouseAdapter = new MouseAdapter() {
    };
    private MouseMotionAdapter mouseMotionAdapter = new MouseMotionAdapter() {
    };
    private KeyAdapter keyAdapter = new KeyAdapter() {
    };
    private ComponentAdapter componentAdapter = new ComponentAdapter() {
        public void componentResized(ComponentEvent e) {
            if (useBackBuffer == true) {
                setOpaque(false);
                imageBuf = null;
                iterate = 3;
            }
        }
    };

    private BufferedImage imageBuf = null;
    private Area[] bars;
    private Rectangle barsBounds = null;
    private Rectangle barsScreenBounds = null;
    private AffineTransform centerAndScaleTransform = null;
    private Timer timer = new Timer(1000 / 4, this);
    private Color[] colors = null;
    private int colorOffset = 0;
    private boolean useBackBuffer;
    private boolean tempHide = false;
    private String text;

    /**
     * TODO: finish documentation (see InfiniteProgressPanel)
     * @param i_bUseBackBuffer When true a screen capture of the underlying window
     * is taken. Therefore no update in the background can be visible through this
     * glass pane. Increases performances.
     */
    public PerformanceInfiniteProgressPanelEFACEC() {
        this(true);
    }

    public PerformanceInfiniteProgressPanelEFACEC(boolean i_bUseBackBuffer) {
        this(i_bUseBackBuffer, DEFAULT_NUMBER_OF_BARS);
    }

    public PerformanceInfiniteProgressPanelEFACEC(int numBars) {
        this(true, numBars, null);
    }

    public PerformanceInfiniteProgressPanelEFACEC(InfiniteProgressAdapter infiniteProgressAdapter) {
        this(true, DEFAULT_NUMBER_OF_BARS, infiniteProgressAdapter);
    }

    public PerformanceInfiniteProgressPanelEFACEC(boolean i_bUseBackBuffer, int numBars) {
        this(i_bUseBackBuffer, numBars, null);
    }

    public PerformanceInfiniteProgressPanelEFACEC(boolean i_bUseBackBuffer, InfiniteProgressAdapter infiniteProgressAdapter) {
        this(i_bUseBackBuffer, DEFAULT_NUMBER_OF_BARS, infiniteProgressAdapter);
    }

    public PerformanceInfiniteProgressPanelEFACEC(int numBars, InfiniteProgressAdapter infiniteProgressAdapter) {
        this(true, numBars, infiniteProgressAdapter);
    }

    public PerformanceInfiniteProgressPanelEFACEC(boolean i_bUseBackBuffer, int numBars, InfiniteProgressAdapter infiniteProgressAdapter) {
        useBackBuffer = i_bUseBackBuffer;
        this.numBars = numBars;
        setInfiniteProgressAdapter(infiniteProgressAdapter);

        colors = new Color[numBars * 2];
        // build bars
        bars = buildTicker(numBars);
        // calculate bars bounding rectangle
        barsBounds = new Rectangle();
        for (int i = 0; i < bars.length; i++) {
            barsBounds = barsBounds.union(bars[i].getBounds());
        }
        // create colors
        for (int i = 0; i < bars.length; i++) {
            int channel = 224 - 128 / (i + 1);
            colors[i] = new Color(channel, channel, channel);
            colors[numBars + i] = colors[i];
        }
        // set cursor
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        // set opaque
        setOpaque(useBackBuffer);
    }

    /**
     * 
     * @param strText
     */
    public void setTextToDraw(final String strText){
      this.strText = strText;
    }

    protected void setInfiniteProgressAdapter(InfiniteProgressAdapter infiniteProgressAdapter) {
        this.infiniteProgressAdapter = infiniteProgressAdapter;
    }

    int iterate;  //we draw use transparency to draw a number of iterations before making a snapshot

    /**
     * Called to animate the rotation of the bar's colors
     */
    public void actionPerformed(ActionEvent e) {
        // rotate colors
        if (colorOffset == (numBars - 1)) {
            colorOffset = 0;
        } else {
            colorOffset++;
        }
        // repaint
        if (barsScreenBounds != null) {
            repaint(barsScreenBounds);
        } else {
            repaint();
        }
        if (useBackBuffer && imageBuf == null) {
            if (iterate < 0) {
                try {
                    makeSnapshot();
                    setOpaque(true);
                } catch (AWTException e1) {
                    e1.printStackTrace();  //todo: decide what exception to throw
                }
            } else {
                iterate--;
            }
        }
    }

    /**
     * Show/Hide the pane, starting and stopping the animation as you go
     */
    public void setVisible(boolean i_bIsVisible) {
        setOpaque(false);
        // capture
        if (i_bIsVisible) {
            if (useBackBuffer) {
                // add window resize listener
                Window w = SwingUtilities.getWindowAncestor(this);
                if (w != null) {
                    w.addComponentListener(componentAdapter);
                } else {
                    addAncestorListener(new AncestorListener() {
                        public void ancestorAdded(AncestorEvent event) {
                            Window w = SwingUtilities.getWindowAncestor(PerformanceInfiniteProgressPanelEFACEC.this);
                            if (w != null) {
                                w.addComponentListener(componentAdapter);
                            }
                        }
                        public void ancestorRemoved(AncestorEvent event) {
                        }
                        public void ancestorMoved(AncestorEvent event) {
                        }
                    });
                }
                iterate = 3;
            }
            // capture events
            addMouseListener(mouseAdapter);
            addMouseMotionListener(mouseMotionAdapter);
            addKeyListener(keyAdapter);
            // start anim
            if (infiniteProgressAdapter != null) {
                infiniteProgressAdapter.animationStarting();
                infiniteProgressAdapter.rampUpEnded();
            }
            timer.start();
        } else {
            // stop anim
            timer.stop();
            if (infiniteProgressAdapter != null) {
                infiniteProgressAdapter.animationStopping();
            }
            /// free back buffer
            imageBuf = null;
            // stop capturing events
            removeMouseListener(mouseAdapter);
            removeMouseMotionListener(mouseMotionAdapter);
            removeKeyListener(keyAdapter);
            // remove window resize listener
            Window oWindow = SwingUtilities.getWindowAncestor(this);
            if (oWindow != null) {
                oWindow.removeComponentListener(componentAdapter);
            }
        }
        super.setVisible(i_bIsVisible);
    }

    private void makeSnapshot() throws AWTException {
        Window oWindow = SwingUtilities.getWindowAncestor(this);
        Insets oInsets = oWindow.getInsets();
        Rectangle oRectangle = new Rectangle(oWindow.getBounds());
        oRectangle.x += oInsets.left;
        oRectangle.y += oInsets.top;
        oRectangle.width -= oInsets.left + oInsets.right;
        oRectangle.height -= oInsets.top + oInsets.bottom;
        // capture window contents
        imageBuf = new Robot().createScreenCapture(oRectangle);
        //no need to fade because we are allready using an image that is showing through
    }

    /**
     * Recalc bars based on changes in size
     */
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        // update centering transform
        centerAndScaleTransform = new AffineTransform();
        centerAndScaleTransform.translate((double) getWidth() / 2d, (double) getHeight() / 2d);
        centerAndScaleTransform.scale(dScale, dScale);
        // calc new bars bounds
        if (barsBounds != null) {
            Area oBounds = new Area(barsBounds);
            oBounds.transform(centerAndScaleTransform);
            barsScreenBounds = oBounds.getBounds();
        }
    }

    /**
     * paint background dimed and bars over top
     */
    protected void paintComponent(Graphics g) {
      g.setFont(font);
      g.drawString(this.strText, this.getWidth()/2-(5*this.strText.length()/2), this.getHeight()/2+5);

        if (!tempHide) {
            Rectangle oClip = g.getClipBounds();
            if (imageBuf != null) {
                // draw background image
               // g.drawImage(imageBuf, 0, 0,
                 //           null);
            } else {
                g.setColor(new Color(255, 255, 255, 180));
                g.fillRect(oClip.x, oClip.y, oClip.width, oClip.height);
            }
            // move to center
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.transform(centerAndScaleTransform);
            // draw ticker
            for (int i = 0; i < bars.length; i++) {
                g2.setColor(colors[i + colorOffset]);
                g2.fill(bars[i]);
            }
            double maxY = InfiniteProgressPanel.drawTextAt(text, getFont(), g2, getWidth(), barsScreenBounds.getMaxY(), getForeground());
            if (infiniteProgressAdapter != null) {
                infiniteProgressAdapter.paintSubComponents(maxY);
            }
        }
    }

    /**
     * Builds the circular shape and returns the result as an array of <code>Area</code>. Each <code>Area</code> is one
     * of the bars composing the shape.
     */
    private static Area[] buildTicker(int i_iBarCount) {
        Area[] ticker = new Area[i_iBarCount];
        Point2D.Double center = new Point2D.Double(0, 0);
        double fixedAngle = 2.0 * Math.PI / ((double) i_iBarCount);

        for (double i = 0.0; i < (double) i_iBarCount; i++) {
            Area primitive = buildPrimitive();

            AffineTransform toCenter = AffineTransform.getTranslateInstance(center.getX(), center.getY());
            AffineTransform toBorder = AffineTransform.getTranslateInstance(12.0, -1.5);
            AffineTransform toCircle = AffineTransform.getRotateInstance(-i * fixedAngle, center.getX(), center.getY());

            AffineTransform toWheel = new AffineTransform();
            toWheel.concatenate(toCenter);
            toWheel.concatenate(toBorder);

            primitive.transform(toWheel);
            primitive.transform(toCircle);

            ticker[(int) i] = primitive;
        }

        return ticker;
    }

    /**
     * Builds a bar.
     */
    private static Area buildPrimitive() {
        Rectangle2D.Double body = new Rectangle2D.Double(2, 0, 10, 4);
        Ellipse2D.Double head = new Ellipse2D.Double(0, 0, 4, 4);
        Ellipse2D.Double tail = new Ellipse2D.Double(10, 0, 4, 4);

        Area tick = new Area(body);
        tick.add(new Area(head));
        tick.add(new Area(tail));

        return tick;
    }

    public void start() {
        setVisible(true);
    }

    public void stop() {
        setVisible(false);
    }

    public void setText(String text) {
        this.text = text;
        repaint();
    }

    public String getText() {
        return text;
    }

    public JComponent getComponent() {
        return this;
    }

    /**
     * Adds a listener to the cancel button in this progress panel.
     * @throws RuntimeException if the infiniteProgressAdapter is null or is not a CancelableProgessAdapter
     * @param listener
     */
    public void addCancelListener(ActionListener listener) {
        if (infiniteProgressAdapter instanceof CancelableProgessAdapter) {
            ((CancelableProgessAdapter)infiniteProgressAdapter).addCancelListener(listener);
        } else {
            throw new RuntimeException("Expected CancelableProgessAdapter for cancel listener.  Adapter is "+ infiniteProgressAdapter);
        }
    }

    /**
     * Removes a listener to the cancel button in this progress panel.
     * @throws RuntimeException if the infiniteProgressAdapter is null or is not a CancelableProgessAdapter
     * @param listener
     */
    public void removeCancelListener(ActionListener listener) {
        if (infiniteProgressAdapter instanceof CancelableProgessAdapter) {
            ((CancelableProgessAdapter)infiniteProgressAdapter).removeCancelListener(listener);
        } else {
            throw new RuntimeException("Expected CancelableProgessAdapter for cancel listener.  Adapter is " + infiniteProgressAdapter);
        }
    }
}

