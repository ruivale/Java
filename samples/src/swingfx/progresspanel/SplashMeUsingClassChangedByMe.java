/**
 * <p>
 * Classname:  swingfx.progresspanel.SplashMeUsingClassChangedByMe
 * </p>
 *
 * <p>Copyright: Copyright (c) 2010 EFACEC SE
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

package swingfx.progresspanel;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class SplashMeUsingClassChangedByMe {
  /**
   *
   *
   * @param args
   */
  public static void main(final String[] args) {
    JFrame frame = new JFrame();

    JPanel panel = new JPanel();

    panel.add(new JLabel("Example Text:"));
    panel.add(new JButton("Example Button"));
    frame.add(panel);

    final PerformanceInfiniteProgressPanelEFACEC pane =
      new PerformanceInfiniteProgressPanelEFACEC(true, 15);

    Thread thread =
      new Thread(
          new Runnable() {
            public void run() {
              try {
                Thread.sleep(10000);
              } catch(final InterruptedException e) {
              }

              pane.setVisible(false);
            }
          });

    thread.start();

    //pane.setBounds(0,0, 50,50);

    frame.setSize(
      350,
      300);
    frame.setGlassPane(pane);
    pane.setTextToDraw("4509");
    pane.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}