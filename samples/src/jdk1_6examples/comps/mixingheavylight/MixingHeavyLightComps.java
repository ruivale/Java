/**
 * <p>
 * Classname: package jdk1_6examples.comps.mixingheavylight.MixingHeavyLightComps
 * </p>
 *
 * <p>Copyright: Copyright (c) 2008 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Engº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package jdk1_6examples.comps.mixingheavylight;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Label;
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
