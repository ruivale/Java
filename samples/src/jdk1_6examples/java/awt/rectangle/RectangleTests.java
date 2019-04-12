package jdk1_6examples.java.awt.rectangle;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class RectangleTests {
  private static Rectangle rectFrame = new Rectangle();
  private static Rectangle rectPanel = new Rectangle();
  private static Rectangle rectDialog = new Rectangle();


  public static void main(final String[] args){

    final JFrame f = new JFrame("tlc");
    f.setExtendedState(JFrame.MAXIMIZED_BOTH);
    //f.setBounds(0,0,1280,800);
    f.setLayout(null);

    final JPanel jP = new JPanel();
    jP.setBounds(20, 30, 1200, 170);
    jP.setBackground(Color.BLACK);

    f.add(jP);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);

    final JDialog jD = new JDialog(f, "", true);
    jD.setSize(1200, 170);
    jD.setLocationRelativeTo(f);
    jD.addComponentListener(new ComponentAdapter(){
      public void componentMoved(final ComponentEvent e){
        rectFrame = f.getBounds();
        rectPanel = jP.getBounds();
        rectDialog = jD.getBounds();

        if(!rectFrame.contains(rectDialog)){
          System.out.println("RectFrame does not contain 100% of the RectDialog.");
          return;
        }

        final Rectangle rectIntersect = new Rectangle();
        Rectangle.intersect(rectFrame, rectDialog, rectIntersect);
        System.out.println("Rectangle.intersect(rectFrame, rectDialog, rect): " +rectIntersect);

        int heightDiffTop = rectIntersect.y;
        int heightDiffBottom = rectFrame.height - (rectIntersect.y + rectIntersect.height);        
        int widthDiffLeft = rectIntersect.x;
        int widthDiffRight = rectFrame.width - (rectIntersect.x + rectIntersect.width);

        System.out.println(
            "widthDiffRight: "+widthDiffRight+
            ", widthDiffLeft: "+widthDiffLeft+
            ", heightDiffTop: "+heightDiffTop+
            ", heightDiffBottom: "+heightDiffBottom+
            ".");

        final int[] iValues = new int[]{
          heightDiffBottom,
          heightDiffTop,
          widthDiffRight,
          widthDiffLeft};
        int iMax = heightDiffBottom;

        for(int i : iValues) {
          if(i > iMax){
            iMax = i;
          }
        }
        System.out.println("Max value is: " +iMax);

        final Rectangle rectNew = new Rectangle();

        if(iMax == heightDiffBottom){
          System.out.println("The heightDiffBottom ...");
          //if(xDiff > )
          rectNew.setBounds(
              rectFrame.x,
              rectFrame.height - heightDiffBottom,
              rectFrame.width, 
              heightDiffBottom);

        }else if(iMax == heightDiffTop){
          System.out.println("The heightDiffTop ...");
          rectNew.setBounds(
              rectFrame.x,
              rectFrame.y,
              rectFrame.width,
              heightDiffTop);

        }else if(iMax == widthDiffRight){
          System.out.println("The widthDiffRight ...");
          rectNew.setBounds(
              rectFrame.width - widthDiffRight,
              rectFrame.y,
              widthDiffRight,
              rectFrame.height);

        }else{
          // iMax == widthDiffLeft
          System.out.println("The widthDiffLeft ...");
          rectNew.setBounds(
              rectFrame.x,
              rectFrame.y,
              widthDiffLeft,
              rectFrame.height);
        }

        jP.setBounds(rectNew);
        f.validate();
        f.repaint();

        System.out.println("---------------------------------------------------");

      }
    });

    new Thread(new Runnable() {
      public void run() {
        jD.setVisible(true);
      }
    }).start();

  }
}
