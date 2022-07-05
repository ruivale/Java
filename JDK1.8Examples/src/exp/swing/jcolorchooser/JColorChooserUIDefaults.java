package exp.swing.jcolorchooser;


import javax.swing.UIManager;
import javax.swing.UIDefaults;
import java.util.Enumeration;
import javax.swing.plaf.UIResource;
import java.awt.Toolkit;
import javax.swing.JColorChooser;
import java.awt.BorderLayout;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class JColorChooserUIDefaults {
  public JColorChooserUIDefaults() {
    UIManager.getCrossPlatformLookAndFeelClassName();
    actualizeUIValues();
    printUIValues();
  }


  private void printUIValues(){
    //Toolkit.getDefaultToolkit().

    UIDefaults uid = UIManager.getDefaults();
    Enumeration iter = uid.keys();
    try {
      Object key;

      while (iter.hasMoreElements()) {
        key = (Object) iter.nextElement();

        //if (key.toString().indexOf("ColorChooser") > -1) {
          System.out.println(key);
        //}
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  private void actualizeUIValues(){

    try {

      UIManager.put("ColorChooser.cancelText", "ColorChooser.cancelText");
      UIManager.put("ColorChooser.hsbBlueText", "ColorChooser.hsbBlueText");
      UIManager.put("ColorChooser.hsbBrightnessText","ColorChooser.hsbBrightnessText");
      UIManager.put("ColorChooser.hsbGreenText", "ColorChooser.hsbGreenText");
      UIManager.put("ColorChooser.hsbHueText", "ColorChooser.hsbHueText");
      UIManager.put("ColorChooser.hsbNameText", "ColorChooser.hsbNameText");
      UIManager.put("ColorChooser.hsbRedText", "ColorChooser.hsbRedText");
      UIManager.put("ColorChooser.hsbSaturationText","ColorChooser.hsbSaturationText");
      UIManager.put("ColorChooser.okText", "ColorChooser.okText");

      UIManager.put("ColorChooser.previewText", "ColorChooser.previewText");
      UIManager.put("ColorChooser.sampleText", "ColorChooser.sampleText");

      UIManager.put("ColorChooser.resetText", "ColorChooser.resetText");
      UIManager.put("ColorChooser.rgbBlueText", "ColorChooser.rgbBlueText");
      UIManager.put("ColorChooser.rgbGreenMnemonic","ColorChooser.rgbGreenMnemonic");
      UIManager.put("ColorChooser.rgbGreenText", "ColorChooser.rgbGreenText");
      UIManager.put("ColorChooser.rgbNameText", "ColorChooser.rgbNameText");
      UIManager.put("ColorChooser.rgbRedMnemonic","ColorChooser.rgbRedMnemonic");
      UIManager.put("ColorChooser.rgbRedText", "ColorChooser.rgbRedText");
      UIManager.put("ColorChooser.swatchesNameText","ColorChooser.swatchesNameText");
      UIManager.put("ColorChooser.swatchesRecentText","ColorChooser.swatchesRecentText");

    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  public static void main(String[] args) {
    JColorChooserUIDefaults jcolorchooseruidefaults = new
        JColorChooserUIDefaults();

    JColorChooser c = new JColorChooser();
    javax.swing.JFrame f = new javax.swing.JFrame("Kurid shes jasuiwenev");
    f.getContentPane().setLayout(new BorderLayout());
    f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    f.getContentPane().add(c);
    f.pack();
    f.setVisible(true);
   }
}
