package exp.desktop;

import javax.swing.*;
import java.awt.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */


 /**

Add the JDesktopPane containing the
JInternalFrames to a JScrollPane.
Make sure
that the preferredSize of the JDesktopPane
is at least equal to the rectangle enclosing all
the JInternalFrames (for that you will have to
listen to events (on JInternalFrames) using to
ComponentListener and recompute new size of
JDesktopPane in the componentResized and
componentMoved callbacks). After adjusting the
size of JDesktopPane call revalidate() on it.

  */


////////////////////////////////////////////////////////////////////////////////


  /*

  Answer
There are multiple ways to do it. You could
simply override the setBounds(....) method
and always ignore the changes in x and y coordinate. Other alternative is to
install a DesktopManager subclass in the JDesktopPane to which the
JInternalFrames are added and override the
public void setBoundsForFrame(JComponent f,
                              int newX,
                              int newY,
                              int newWidth,
                              int newHeight)

to ignore the newx and newy.
Comments and alternative answers


This is ok but ...
Cengiz Sahin, Sep 18, 2001
when overriding the setBounds Method the user is still able to move at least
 the outbound of the Frame.
After the movement the outbound remains and a repaint is necessary.

  */

////////////////////////////////////////////////////////////////////////////////

  /*

  Search
Stopping drag of InternalFrame...
URGENT!!!!!! PLEASE!!!!!!!!

Topic: Swing
Maheshwar Reddy, Aug 20, 2001  [replies:2]
Hello everyone,

    Can anyone help me out of how to stop the default behaviour of the
     JInternalFrame, from being dragged when placed in a JDesktopPane.
     Thanking You,

Cheers,
Mudi.

    


Re: Stopping drag of InternalFrame...
URGENT!!!!!! PLEASE!!!!!!!!
Topic: Swing
Christopher Brind, Aug 21, 2001  [replies:1]
Extend javax.swing.DefaultDesktopManager
 and override the dragging frame methods.
Then use an instance of your desktop manager with the desktop pane by
calling the setDesktopManager(DesktopManager d) method on your JDesktopPane
instance.

  */


public class DesktopWithScroll extends JFrame {

  public DesktopWithScroll() {
    Container contentPane = getContentPane();

//    JPanel comp = new JPanel();
  //  comp.setLayout(new BorderLayout());
    JDesktopPane jdp = new JDesktopPane();
    jdp.setPreferredSize(new Dimension(200,150));
    jdp.setLayout(new BorderLayout());
    JInternalFrame jif = new JInternalFrame("AAAAAAa",true,true,true,true);
    jif.setBounds(10,10,100,80);
    jif.setVisible(true);
    jdp.add(jif,BorderLayout.CENTER);
//    comp.add(jdp,BorderLayout.CENTER);

//  JLabel comp = new JLabel("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");



    JScrollPane sp = new JScrollPane(jdp);

    contentPane.add(jdp);

    setBounds(20,20,300,200);
    setVisible(true);

  }
  public static void main(String[] args) {
    DesktopWithScroll desktopWithScroll1 = new DesktopWithScroll();
  }
}
