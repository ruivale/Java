package exp.jdk14;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

 import java.awt.*;
 import javax.swing.*;

/**
 *
 *
 */
public class ScreenModes {

/**
 *
 */
  public ScreenModes() {


    for (int i = 0; i < 3; i++) {
      System.out.println("i="+i+".");
      for (int j = 0; j < 10; j++) {
        System.out.println("  j="+j+".");
        if(j==2){
          break;
        }
      }
      if(i==1){
       break;
      }
    }



/*******************************************************************************

   GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
   GraphicsDevice[] gs = ge.getScreenDevices();
   for (int j = 0; j < gs.length; j++) {

     System.out.println("graphicsDevice["+j+"]: "+gs[j]+".");

      GraphicsDevice gd = gs[j];
      GraphicsConfiguration[] gc = gd.getConfigurations();
      for (int i=0; i < gc.length; i++) {

System.out.println("GraphicsConfiguration["+i+"]: "+gc[i]+".");

      }
   }


JWindow jw = new JWindow();
JButton b = new JButton("AAAAAAAAAAAAAAAAAAAA");
jw.getContentPane().setLayout(new BorderLayout());
jw.getContentPane().add(b, BorderLayout.CENTER);

try {
    gs[0].setFullScreenWindow(jw);

System.out.println("is FullScreenWindow");

} finally {

System.out.println("FINALLY");

    gs[0].setFullScreenWindow(null);
}

jw.setVisible(true);



*******************************************************************************/


  }

  public static void main(String[] args) {
    new ScreenModes();
  }
}