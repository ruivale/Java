package exp.swing.panel;


import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;


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
public class MyTestingPanel
    extends JPanel {
  public MyTestingPanel() {
    try {
      jbInit();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    MyTestingPanel mytestingpanel = new MyTestingPanel();
  }

  private void jbInit() throws Exception {
    GridBagLayout gridBagLayoutSouth = new GridBagLayout();
    JPanel jPanelZoom = new JPanel();
    JPanel jPanelFocus = new JPanel();
    JButton jButtonZP = new JButton();
    JButton jButtonZM = new JButton();
    GridLayout gridLayoutZoom = new GridLayout();
    JButton jButtonFP = new JButton();
    JButton jButtonFM = new JButton();
    JPanel jPanelFocusPM = new JPanel();
    GridLayout gridLayoutFocusPM = new GridLayout();
    JPanel jPanelAutoFocus = new JPanel();
    JButton jButtonAF = new JButton();
    BorderLayout borderLayoutAutoFocus = new BorderLayout();
    BorderLayout borderLayoutFocus = new BorderLayout();
    Border border3 = BorderFactory.createEmptyBorder(0, 3, 0, 3);

    border3 = BorderFactory.createEmptyBorder(0, 4, 0, 5);
    this.setLayout(gridBagLayoutSouth);
    jButtonZP.setText("jButton1");
    jButtonZM.setText("jButton2");
    jPanelZoom.setLayout(gridLayoutZoom);
    gridLayoutZoom.setColumns(1);
    gridLayoutZoom.setRows(2);
    jPanelFocus.setLayout(borderLayoutFocus);
    jButtonFP.setText("jButton3");
    jButtonFM.setText("jButton4");
    jPanelFocusPM.setLayout(gridLayoutFocusPM);

    jPanelFocusPM.setBorder(new TitledBorder("Focus"));
    jPanelZoom.setBorder(new TitledBorder("Zoom"));

    gridLayoutFocusPM.setColumns(1);
    gridLayoutFocusPM.setRows(2);
    jButtonAF.setText("jButton5");
    jPanelFocus.setPreferredSize(new Dimension(83, 100));
    jPanelAutoFocus.setBorder(border3);
    jPanelAutoFocus.setLayout(borderLayoutAutoFocus);
    jPanelZoom.add(jButtonZP);
    jPanelZoom.add(jButtonZM);
    jPanelFocusPM.add(jButtonFP);
    jPanelFocusPM.add(jButtonFM);
    this.add(jPanelZoom, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                                , GridBagConstraints.NORTH,
                                                GridBagConstraints.NONE,
                                                new Insets(0, 0, 0, 0), 0, 0));
    this.add(jPanelFocus, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
                                                 , GridBagConstraints.CENTER,
                                                 GridBagConstraints.NONE,
                                                 new Insets(0, 0, 0, 0), 0, 0));
    jPanelAutoFocus.add(jButtonAF, java.awt.BorderLayout.NORTH);
    jPanelFocus.add(jPanelFocusPM, java.awt.BorderLayout.NORTH);
    jPanelFocus.add(jPanelAutoFocus, java.awt.BorderLayout.CENTER);
  }

}
