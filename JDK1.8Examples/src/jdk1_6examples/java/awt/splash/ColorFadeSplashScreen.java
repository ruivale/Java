package jdk1_6examples.java.awt.splash;

import java.awt.SplashScreen;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;


/**
 * <p>Classname: </p>
 *
 * <p>Description: Java 6, aka JDK1.6, examples ...</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ENT, S.A.</p>
 *
 * @author rUI vALE - rui dot vale at ent dot efacec dot pt
 * @version 1.0
 */
public class ColorFadeSplashScreen {
  public ColorFadeSplashScreen() {
    /***
    SplashScreen splash = SplashScreen.getSplashScreen();

    if (splash != null) {
      Graphics2D g2 = splash.createGraphics();
      // clear the splash screen
      g2.setComposite(AlphaComposite.Clear);
      g2.fillRect(0, 0, splash.getSize().width, splash.getSize().height);
      g2.setPaintMode();
      // draw colors on top of sepia
      g2.drawImage(sepiaSplashImage, 0, 0, null);
      g2.setComposite(AlphaComposite.SrcOver.derive(progress));
      g2.drawImage(colorSplashImage, 0, 0, null);
      g2.dispose();
      splash.update();
    }
    /**/
  }

  public static void main(String[] args) {
    ColorFadeSplashScreen colorfadesplashscreen = new ColorFadeSplashScreen();
  }
}
