package exp.swing.frames;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.plaf.ColorUIResource;

class MouseEvtsTitleBar extends JDialog{

  private static Map<Integer, MouseEvtsTitleBar> map = new HashMap<Integer, MouseEvtsTitleBar>(2);

  final int id;
  final String strTitle;


  static{
    UIDefaults table = UIManager.getDefaults();
    ColorUIResource pr4 = new ColorUIResource(new Color(127,127,255));
    ColorUIResource pr3 = new ColorUIResource(new Color(0,0,127));
    ColorUIResource pr2 = new ColorUIResource(new Color(63,63,191));
    ColorUIResource pr1 = new ColorUIResource(new Color(0,0,255));
    ColorUIResource blk = new ColorUIResource(Color.RED); // BLACK
    ColorUIResource wht = new ColorUIResource(Color.YELLOW); // white
    ColorUIResource gry = new ColorUIResource(Color.GREEN); // gray
    Object[] colors = {
      "desktop"              , pr1, /* Color of the desktop background */
      "activeCaption"        , pr3, /* Color for captions (title bars) when they are active. */
      "activeCaptionText"    , wht, /* Text color for text in captions (title bars). */
      "activeCaptionBorder"  , blk, /* Border color for caption (title bar) window borders. */
      "inactiveCaption"      , pr1, /* Color for captions (title bars) when not active. */
      "inactiveCaptionText"  , gry, /* Text color for text in inactive captions (title bars). */
      "inactiveCaptionBorder", gry, /* Border color for inactive caption (title bar) window borders. */
      "window"               , wht, /* Default color for the interior of windows */
      "windowBorder"         , blk, /* Color of the window border */
      "windowText"           , blk, /* Color of the window text */
      "menu"                 , pr1, /* Background color for menus */
      "menuText"             , blk, /* Text color for menus  */
      "text"                 , pr1, /* Text background color */
      "textText"             , blk, /* Text foreground color */
      "textHighlight"        , pr4, /* Text background color when selected */
      "textHighlightText"    , wht, /* Text color when selected */
      "textInactiveText"     , gry, /* Text color when disabled */
      "control"              , pr1, /* Default color for controls (buttons, sliders, etc) */
      "controlText"          , blk, /* Default color for text in controls (buttons, sliders, etc) */
      "controlHighlight"     , pr4, /* Highlight color for controls */
      "controlLtHighlight"   , wht, /* Lighter highlight color for controls */
      "controlShadow"        , pr2, /* Shadow color for controls */
      "controlDkShadow"      , blk, /* Dark shadow color for controls */
      "scrollbar"            , pr3, /* Scrollbar background (usually the "track") */
      "info"                 , wht, /* Background color for informational text */
      "infoText"             , blk  /* Color for informational text */
    };
    table.putDefaults(colors);

  }

  public MouseEvtsTitleBar(final int id, final String strTitle, final int x, final int y) {
    this.id = id;
    this.strTitle = strTitle;

    this.setResizable(true);
    this.setSize(300, 200);
    this.setLocation(x, y);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setTitle(strTitle);
    this.setUndecorated(true);


    final JRootPane jRootPane = new JRootPane(){
      private static final long serialVersionUID = 1L;
      @Override
      public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.BLACK);
        g.drawRect(0,0, getWidth()-1, getHeight()-1);
        g.drawRect(1,1, getWidth()-3, getHeight()-3);
        g.drawRect(2,2, getWidth()-5, getHeight()-5);
        g.drawRect(3,3, getWidth()-7, getHeight()-7);

        System.out.println(strTitle+" - jRootPane paint() w:"+getWidth()+" y:"+getHeight());
      }
    };
    //this.setRootPane(jRootPane);
    this.getRootPane().setWindowDecorationStyle(JRootPane.ERROR_DIALOG);
    this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));





    this.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent me) {
        System.out.println(strTitle+" - mouse pressed");
      }
      public void mouseReleased(MouseEvent me) {
        System.out.println(strTitle+" - Mouse released");
      }
      public void mouseClicked(MouseEvent me) {
        System.out.println(strTitle+" - Mouse clicked");
        if (me.getY() < getContentPane().getY() && me.getClickCount() > 1) {
          JOptionPane.showMessageDialog(getContentPane(), "In Title Bar");
        }
      }
    });
    this.addWindowListener(new WindowAdapter() {
      public void windowActivated(WindowEvent e) {
        System.out.println(strTitle+" - windowActivated");

        /** calling other windows to repaint themselves  */
        repaintOtherSWs(id);
      }

      public void windowClosed(WindowEvent e) {
        System.out.println(strTitle+" - internalFrameClosed");
      }

      public void windowClosing(WindowEvent e) {
        System.out.println(strTitle+" - windowClosing");
      }

      public void windowDeactivated(WindowEvent e) {
        System.out.println(strTitle+" - windowDeactivated");
      }

      public void windowDeiconified(WindowEvent e) {
        System.out.println(strTitle+" - windowDeiconified");
      }

      public void windowGainedFocus(WindowEvent e) {
        System.out.println(strTitle+" - windowGainedFocus");
      }

      public void windowIconified(WindowEvent e) {
        System.out.println(strTitle+" - windowIconified");
      }

      public void windowLostFocus(WindowEvent e) {
        System.out.println(strTitle+" - windowLostFocus");
      }

      public void windowOpened(WindowEvent e) {
        System.out.println(strTitle+" - windowOpened");
      }

      public void windowStateChanged(WindowEvent e) {
        System.out.println(strTitle+" - windowStateChanged windowStateChanged");
      }

    });


    this.setLayout(new BorderLayout());
    this.getContentPane().add(new JButton(strTitle));


    map.put(id, this);
  }

  /**
   *
   * @param id
   */
  private void repaintOtherSWs(final int id) {
    for (Map.Entry<Integer, MouseEvtsTitleBar> entry : map.entrySet()) {
      final int iId = entry.getKey();
      final MouseEvtsTitleBar mouseEvtsTitleBar = entry.getValue();

      if(iId != id){
        mouseEvtsTitleBar.repaint();
      }
    }
  }


  public static void main(String[] args) {
    new MouseEvtsTitleBar(1, "1111", 200, 200).setVisible(true);
    new MouseEvtsTitleBar(2, "2222", 600, 200).setVisible(true);
  }
}
