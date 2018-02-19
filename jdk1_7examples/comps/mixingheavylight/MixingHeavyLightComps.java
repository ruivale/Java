/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdk1_7examples.comps.mixingheavylight;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - rui dot vale at efacec dot pt
 * @version $Revision: 1.1 $
 */
public class MixingHeavyLightComps{

  AsynchronousSocketChannel f;

  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(MixingHeavyLightComps.class.getName());

 /**
  * The MixingHeavyLightComps default constuctor.
  */
  public MixingHeavyLightComps(){


    final JFrame frame = new JFrame("Fade Tabs");
    frame.setLayout(null);
    //frame.setLayout(new BorderLayout());

    JLabel jL = new JLabel("1111 Light component ...");
    jL.setBounds(10,10, 100, 20);
    jL.setOpaque(true);
    jL.setForeground(Color.RED);

    JLabel jL2 = new JLabel("2222 Light component.");
    jL2.setBounds(18,18, 100, 20);
    jL2.setOpaque(true);
    jL2.setForeground(Color.BLUE);

    Label l = new Label("... Heavy component ...");
    l.setBounds(55,15,100,20);
    l.setForeground(Color.GREEN);

    //frame.add(jL2);
    frame.add(jL);
    frame.add(l);

    frame.setBounds(200,200,350,150);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);


  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("MixingHeavyLightComps").append("").toString();
  }

  public static void main(final String[] args){
    final MixingHeavyLightComps clazz = new MixingHeavyLightComps();
  }
}
