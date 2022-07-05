package exp.gistv.tests;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.util.logging.Level;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import java.awt.FlowLayout;
//import com.ent.stv.sdm.XMLNodesProperties;
import javax.swing.border.Border;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;


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
public class NavButtonConfigWin {
  public NavButtonConfigWin() {
    String strButtonName = "";
    int _x = 1;
    int _y = 1;
    final int min = -4000;
    final int max = 4000;
    final int x = _x;
    final int y = _y;

    final SpinnerModel spinnerModelX = new SpinnerNumberModel(x, min, max, 1);
    final JSpinner jSpinnerX = new JSpinner(spinnerModelX);
    final SpinnerModel spinnerModelY = new SpinnerNumberModel(y, min, max, 1);
    final JSpinner jSpinnerY = new JSpinner(spinnerModelY);


    jSpinnerX.setPreferredSize(new Dimension(60,20));
    jSpinnerY.setPreferredSize(new Dimension(60,20));


    final JLabel jLabName = new JLabel(
      new StringBuffer("Name").append(": ").append(strButtonName).toString());
    final JLabel jLabX = new JLabel("X:");
    final JLabel jLabY = new JLabel("Y:");


    final JButton jBInitPos = new JButton("initial pos");
    final JButton jBSave = new JButton("save");
    final JButton jBExit = new JButton("exit");


    final JPanel jPanelName = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,0));
    final JPanel jPanelX = new JPanel(new  FlowLayout(FlowLayout.LEFT, 10, 0));
    final JPanel jPanelY = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
    final JPanel jPanelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));

    //final Border border = BorderFactory.createEmptyBorder(5,10,5,10);

    //jPanelName.setBorder(border);
    jPanelName.add(jLabName);

    //jPanelX.setBorder(border);
    jPanelX.add(jLabX);
    jPanelX.add(jSpinnerX);
    jPanelX.add(new JPanel());

    //jPanelY.setBorder(border);
    jPanelY.add(jLabY);
    jPanelY.add(jSpinnerY);
    jPanelY.add(jBInitPos);

    //jPanelSouth.setBorder(border);
    jPanelSouth.add(jBSave);
    jPanelSouth.add(jBExit);

    JDialog diag = new JDialog();
    diag.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
    diag.getContentPane().setLayout(new BorderLayout(10,5));


    final JPanel jPanelCenter = new JPanel(new GridLayout(3,1,0,0));
    jPanelCenter.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    jPanelCenter.add(jPanelName);
    jPanelCenter.add(jPanelX);
    jPanelCenter.add(jPanelY);

    diag.getContentPane().add(jPanelCenter, BorderLayout.CENTER);
    diag.getContentPane().add(jPanelSouth, BorderLayout.SOUTH);




    diag.pack();
    diag.setVisible(true);


  }

  public static void main(final String[] args) {
    new NavButtonConfigWin();
  }

}






