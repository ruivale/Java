/**
 * <p>
 * Classname:  exp.swing.jrootpane.JRootPaneTests
 * </p>
 *
 * <p>Copyright: Copyright (c) 2018 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */

package exp.swing.jrootpane;


import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.event.MouseInputAdapter;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class JRootPaneTests extends JRootPane {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(JRootPaneTests.class.getName());


 /**
  * The JRootPaneTests default constuctor.
  */
  public JRootPaneTests(){
    this.setPreferredSize(new Dimension(450, 350));
    
    this.setLayout(new BorderLayout());
    
    final JButton jb = new JButton("<html><body><br>aksdjksadj <br><br><br>dakjakdde <br>eiudiew<br><br></html></body>");
    jb.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("JButton action performed...");
        
        setGlass();
      }
    });
    
    this.add(jb, BorderLayout.CENTER);
    
    
  }

  private void setGlass(){
      // create opaque glass pane
      final JPanel jPanelGlass = new JPanel() {
        @Override
        public void paint(final Graphics g) {
          // try to do some transparency...
          Graphics2D g2d = (Graphics2D) g;

          g2d.setColor(Color.green);
          g2d.fillOval(20, 20, 100, 100);
          g2d.setColor(Color.GRAY);
          g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
          g2d.fillRect(0, 0, 460, 400);
        }
      };
      jPanelGlass.setOpaque(false);

      // Attach mouse listeners
      MouseInputAdapter adapter = new MouseInputAdapter() {
        public void mouseClicked(MouseEvent e) {
          System.out.println("MOUSE CLICKED...");
        }
      };     
      
      jPanelGlass.addMouseListener(adapter);
      jPanelGlass.addMouseMotionListener(adapter); 
      
      // Add modal internal frame to glass pane
      //jPanelGlass.add(this);

      this.setGlassPane(jPanelGlass);

      // Show glass pane, then modal dialog
      jPanelGlass.setVisible(true);      
      
      System.out.println("setGlass()...");
  }
  
 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("JRootPaneTests").append("").toString();
  }

  public static void main(final String[] args){
    JRootPaneTests jrpt = new JRootPaneTests();
    
    JFrame f = new JFrame("dkks");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setLayout(new BorderLayout());
    f.add(jrpt, BorderLayout.CENTER);
    f.setBounds(100, 100, 460, 400);
    f.setVisible(true);
  }
}
