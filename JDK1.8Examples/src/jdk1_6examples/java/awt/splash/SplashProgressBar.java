package jdk1_6examples.java.awt.splash;

import java.awt.*;
//import org.jdesktop.animation.timing.Animator;
//import org.jdesktop.animation.timing.interpolation.PropertySetter;
//import org.jdesktop.animation.timing.TimingTargetAdapter;


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
public class SplashProgressBar {

  /***
  public SplashProgressBar() {
    SplashScreen splash = SplashScreen.getSplashScreen();
    if (splash != null) {
      // Load the sepia and colored images
      colorSplashImage = null;
      try {
        colorSplashImage = GraphicsUtilities.loadCompatibleImage(
            getClass().getResource("/reality/resources/splash-color.png"));
        sepiaSplashImage = GraphicsUtilities.loadCompatibleImage(
            new File("./lib/splash-sepia.png").toURI().toURL());
      } catch (IOException e) {
      }

      // Emulates the progress
      Animator animator = PropertySetter.createAnimator(
          6000, this, "colorSplash", 1.0f);
      animator.addTarget(new TimingTargetAdapter() {
        public void end() {
          show(mainPanel);
        }
      });
      animator.start();
    }
  }

  public float getColorSplash() {
      return colorSplash;
  }

  public void setColorSplash(float colorSplash) {
      this.colorSplash = colorSplash;

      SplashScreen splash = SplashScreen.getSplashScreen();
      Graphics2D g2 = splash.createGraphics();

      g2.setComposite(AlphaComposite.Clear);
      g2.fillRect(0, 0, splash.getSize().width, splash.getSize().height);

      g2.setPaintMode();
      g2.drawImage(sepiaSplashImage, 0, 0, null);

      g2.setComposite(AlphaComposite.SrcOver.derive(colorSplash));
      g2.drawImage(colorSplashImage, 0, 0, null);

      g2.dispose();
      splash.update();
  }
  /**/


  public static void main(String[] args) {
    SplashProgressBar splashprogressbar = new SplashProgressBar();
  }
}
