package exp.swing.window;

import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class JWindowTests extends JPanel {
  public JWindowTests() {
    final JFrame f = new JFrame("FRAME");
    final JWindow w = new JWindow(f);
    final JButton b = new JButton("Press me to show window");
    final JButton c = new JButton("Press to hide me");

    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(b, BorderLayout.CENTER);
    f.setBounds(100,100,700,500);

    w.getContentPane().setLayout(new BorderLayout());
    w.getContentPane().add(c, BorderLayout.CENTER);
    w.setSize(300,200);

    final Component gp = f.getGlassPane();
    gp.addMouseListener(new MouseAdapter() {});

    b.addActionListener(new ActionListener(){
      public void actionPerformed(final ActionEvent e){
        //BufferedImage img = new BufferedImage(16,16,BufferedImage.TYPE_4BYTE_ABGR);
        //Cursor blankCursor = getToolkit().createCustomCursor(img,new Point(0,0),"blankCursor");
        //w.setCursor(blankCursor);
        //c.setCursor(blankCursor);
        //gp.setVisible(true);
        w.setVisible(true);
      }
    });
    c.addActionListener(new ActionListener(){
      public void actionPerformed(final ActionEvent e){
        //gp.setVisible(false);
        w.setVisible(false);
      }
    });
    c.addMouseListener(new MouseAdapter(){
      public void mouseEntered(final MouseEvent e){
        System.out.println("W mouseEntered ("+e.getX()+","+e.getY()+")");
      }
      public void mouseExited(final MouseEvent e){
        System.out.println("W mouseExited ("+e.getX()+","+e.getY()+")");
        try{
          (new Robot()).mouseMove(e.getX() - 50, e.getY() - 50);
        }catch(AWTException awte){
          awte.printStackTrace();
        }catch(SecurityException se){
          se.printStackTrace();
        }
      }
    });
    w.addMouseMotionListener(new MouseMotionAdapter(){
      public void _mouseMoved(final MouseEvent e){
        System.out.println("W mouseMoved ("+e.getX()+","+e.getY()+")");
      }
    });

    javax.swing.Timer timer = new javax.swing.Timer(500, new ActionListener(){
      public void actionPerformed(final ActionEvent e){
        if(w.isVisible()){
          //w.contains();
        }
      }
    });
    timer.start();

    f.setVisible(true);
  }
  public static void main(String[] args) {
    JWindowTests jw = new JWindowTests();
  }
}
