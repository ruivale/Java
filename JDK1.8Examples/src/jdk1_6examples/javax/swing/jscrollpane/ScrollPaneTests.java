/**
 * <p>
 * Classname:  jdk1_6examples.javax.swing.jscrollpane.ScrollPaneTests
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

package jdk1_6examples.javax.swing.jscrollpane;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class ScrollPaneTests extends JPanel {

  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(
      ScrollPaneTests.class.getName());

  // Variables declaration - do not modify
  private javax.swing.JButton jButtonAdd;
  private javax.swing.JButton jButtonExit;
  private javax.swing.JButton jButtonOk;
  private javax.swing.JButton jButtonRemove;
  private javax.swing.JLabel jLabelAvailableButtons;
  private javax.swing.JLabel jLabelConfigButtons;
  private javax.swing.JList jListAvailableButtons;
  private javax.swing.JList jListConfiguredButtons;
  private javax.swing.JPanel jPanelAddRemButtons;
  private javax.swing.JPanel jPanelAvailableButtons;
  private javax.swing.JPanel jPanelButtons;
  private javax.swing.JPanel jPanelConfiguredButtons;
  private javax.swing.JPanel jPanelMain;
  private javax.swing.JScrollPane jScrollPaneAvailableButtons;
  private javax.swing.JScrollPane jScrollPaneConfiguredButtons;
  // End of variables declaration

  // Properties
  private static String strJLabel1Text;
  private static String strJLabel2Text;
  private static String strOkButtonText;
  private static String strExitButtonText;
  private DefaultListModel availableButtonsListModel = new DefaultListModel();
  private DefaultListModel configuredButtonsListModel = new DefaultListModel();

  /**
   *
   * @param owner
   */
  public ScrollPaneTests() {

    try {
      this.addButtons();
      this.getProperties();
      this.initComponents();
      this.addListeners();



    } catch (Exception e) {
      LOGGER.log(
          Level.SEVERE,
          "Cannot init the class.",
          e);
    }

  }

  private void getProperties() {

      strJLabel1Text = "Available buttons";
      strJLabel2Text = "Configured buttons";
      strOkButtonText = "Ok";
      strExitButtonText = "Close";
  }

  /**
   * Invoked when the user send a button from left to right,
   * from available to configured.
   */
  private void addButton() {
    int index;
    JButton toolbarComp;
    int[] selIndexes = jListAvailableButtons.getSelectedIndices();

    for (int i = selIndexes.length - 1; i >= 0; i--) {
      index = selIndexes[i];
      toolbarComp =
          (JButton) availableButtonsListModel.remove(index);
      configuredButtonsListModel.addElement(toolbarComp);
    }

    revalidate();
  }

  /**
   * Invoked when the user send a button from right to left,
   * from configured to available.
   */
  private void removeButton() {
    int index;
    JButton toolbarComp;
    int[] selIndexes =
          jListConfiguredButtons.getSelectedIndices();

    for (int i = selIndexes.length - 1; i >= 0; i--) {
      index = selIndexes[i];
      toolbarComp =
          (JButton) configuredButtonsListModel.remove(index);
      availableButtonsListModel.addElement(toolbarComp);
    }

    revalidate();

  }

  private void addListeners() {
    jButtonAdd.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addButton();
      }
    });

    jButtonRemove.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        removeButton();
      }
    });

  }


  
  private void initComponents() {

    jPanelMain = new javax.swing.JPanel();
    jPanelAvailableButtons = new javax.swing.JPanel();
    jLabelAvailableButtons = new javax.swing.JLabel();
    jScrollPaneAvailableButtons = new javax.swing.JScrollPane();
    jListAvailableButtons = new javax.swing.JList(availableButtonsListModel);
    jPanelAddRemButtons = new javax.swing.JPanel();
    jButtonAdd = new javax.swing.JButton();
    jButtonRemove = new javax.swing.JButton();
    jPanelConfiguredButtons = new javax.swing.JPanel();
    jLabelConfigButtons = new javax.swing.JLabel();
    jScrollPaneConfiguredButtons = new javax.swing.JScrollPane();
    jListConfiguredButtons = new javax.swing.JList(configuredButtonsListModel);
    jPanelButtons = new javax.swing.JPanel();
    jButtonOk = new javax.swing.JButton();
    jButtonExit = new javax.swing.JButton();


    jPanelMain.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jPanelAvailableButtons.setLayout(new java.awt.BorderLayout());

    jLabelAvailableButtons.setText(strJLabel1Text);
    jPanelAvailableButtons.add(jLabelAvailableButtons, java.awt.BorderLayout.NORTH);

    //jScrollPaneAvailableButtons.setPreferredSize(new java.awt.Dimension(200, 200));

    //jListAvailableButtons.setPreferredSize(new java.awt.Dimension(33, 80));
    TLCComponentListRenderer renderer= new TLCComponentListRenderer();
    jListAvailableButtons.setCellRenderer(renderer);
    jScrollPaneAvailableButtons.setViewportView(jListAvailableButtons);

    jPanelAvailableButtons.add(jScrollPaneAvailableButtons, java.awt.BorderLayout.CENTER);

    jPanelMain.add(jPanelAvailableButtons);

    jPanelAddRemButtons.setMinimumSize(new java.awt.Dimension(100, 33));
    jPanelAddRemButtons.setPreferredSize(new java.awt.Dimension(110, 60));

    jButtonAdd.setText(">>");
    jPanelAddRemButtons.add(jButtonAdd);

    jButtonRemove.setText("<<");
    jPanelAddRemButtons.add(jButtonRemove);

    jPanelMain.add(jPanelAddRemButtons);

    jPanelConfiguredButtons.setLayout(new java.awt.BorderLayout());

    jLabelConfigButtons.setText(strJLabel2Text);
    jPanelConfiguredButtons.add(jLabelConfigButtons, java.awt.BorderLayout.NORTH);

    //jScrollPaneConfiguredButtons.setPreferredSize(new java.awt.Dimension(200, 200));

    TLCComponentListRenderer renderer2= new TLCComponentListRenderer();
    jListConfiguredButtons.setCellRenderer(renderer2);
    jScrollPaneConfiguredButtons.setViewportView(jListConfiguredButtons);

    jPanelConfiguredButtons.add(jScrollPaneConfiguredButtons, java.awt.BorderLayout.CENTER);

    jPanelMain.add(jPanelConfiguredButtons);

    add(jPanelMain, java.awt.BorderLayout.CENTER);

    jPanelButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

    jButtonOk.setText(strOkButtonText);
    jPanelButtons.add(jButtonOk);
    jButtonExit.setText(strExitButtonText);
    jPanelButtons.add(jButtonExit);

    add(jPanelButtons, java.awt.BorderLayout.SOUTH);

  }

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {

      public void run() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        f.add(new ScrollPaneTests(), BorderLayout.CENTER);
        f.setBounds(100,100,600,350);
        f.setVisible(true);

        
      }
    });
  }

  public void revalidateItens() {
    this.jScrollPaneAvailableButtons.revalidate();
    this.jScrollPaneConfiguredButtons.revalidate();
    super.revalidate();
  }

  private void addButtons() {
    int n = 20;
    for (int i = 0; i < n; i++) {
      availableButtonsListModel.addElement(new JButton("->"+i+"---------------8777777777---------"));
    }
    n =18;
    for (int i = 0; i < n; i++) {
      configuredButtonsListModel.addElement(new JButton("->"+i+"------------78888888888------------"));
    }
  }
}
class TLCComponentListRenderer extends JLabel
    implements ListCellRenderer {

  public TLCComponentListRenderer() {

    setOpaque(true);
    this.setHorizontalAlignment(JLabel.LEFT);
    setVerticalAlignment(CENTER);

  }

  public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus) {


    if (isSelected) {
      setBackground(list.getSelectionBackground());
      setForeground(list.getSelectionForeground());
    } else {
      setBackground(list.getBackground());
      setForeground(list.getForeground());
    }

    //Set the icon and text.
    final JButton tlcComponent = (JButton) value;
    setText(tlcComponent.getText());

    final Icon icon = tlcComponent.getIcon();
    this.setIcon(icon);

    return this;

  }
}

