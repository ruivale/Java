package exp.layouts;


import java.awt.*;

import javax.swing.*;


/**
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author unascribed
 * @version 1.0
 */
public class BillingPlansGeneralInfo extends JPanel {
  JPanel        jPanelIntroMsg              = new JPanel();
  BorderLayout  borderLayoutIntroMsg        = new BorderLayout();
  JLabel        jLabelIntroMsg              = new JLabel();
  JScrollPane   jScrollPaneIntroMsg         = new JScrollPane();
  JTextArea     jTextAreaIntroMsg           = new JTextArea();
  JScrollPane   jScrollPaneOfferMsg         = new JScrollPane();
  JPanel        jPanelOfferMsg              = new JPanel();
  JLabel        jLabelOfferMsg              = new JLabel();
  JTextArea     jTextAreaOfferMsg           = new JTextArea();
  BorderLayout  borderLayoutOfferMsg        = new BorderLayout();
  JScrollPane   jScrollPanePolicy           = new JScrollPane();
  JPanel        jPanelPolicy                = new JPanel();
  JLabel        jLabelPolicy                = new JLabel();
  JTextArea     jTextAreaPolicy             = new JTextArea();
  BorderLayout  borderLayoutPolicy          = new BorderLayout();
  JPanel        jPanelTimeUnits             = new JPanel();
  BorderLayout  borderLayout1               = new BorderLayout();
  JLabel        jLabelTimeUnits             = new JLabel();
  JComboBox     jComboBoxTimeUnits          = new JComboBox();
  JPanel        jPanelMinTimeUnit           = new JPanel();
  JLabel        jLabelMinTimeUnit           = new JLabel();
  BorderLayout  borderLayout2               = new BorderLayout();
  JTextField    jTextFieldMinTimeUnit       = new JTextField();
  JPanel        jPanelFreeAccessTime        = new JPanel();
  BorderLayout  borderLayout3               = new BorderLayout();
  JLabel        jLabelFreeAccessTime        = new JLabel();
  JTextField    jTextFieldFreeAccessTime    = new JTextField();
  JPanel        jPanelMaxFreeAccessTime     = new JPanel();
  BorderLayout  borderLayout4               = new BorderLayout();
  JLabel        jLabelMaxFreeAccessTime     = new JLabel();
  JTextField    jTextFieldMaxFreeAccessTime = new JTextField();
  GridBagLayout gridBagLayout1              = new GridBagLayout();

  /**
   * Creates a new BillingPlansGeneralInfo object.
   */
  public BillingPlansGeneralInfo() {
    try {
      jbInit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * DOCUMENT ME!
   *
   * @param args DOCUMENT ME!
   */
  public static void main(String[] args) {
    BillingPlansGeneralInfo billingPlansGeneralInfo1 =
      new BillingPlansGeneralInfo();
    JFrame                  f                       =
      new JFrame("GridBag Layout Example");
    BillingPlansGeneralInfo billingPlansGeneralInfo =
      new BillingPlansGeneralInfo();

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    f.getContentPane()
     .add("Center", billingPlansGeneralInfo);
    f.pack();
    f.setSize(f.getPreferredSize());
    f.show();
  }

  private void jbInit()
      throws Exception {
    this.setLayout(gridBagLayout1);
    jPanelIntroMsg.setLayout(borderLayoutIntroMsg);
    jLabelIntroMsg.setText("Introduction message:");
    jLabelIntroMsg.setVerticalAlignment(SwingConstants.TOP);
    jLabelIntroMsg.setVerticalTextPosition(SwingConstants.TOP);
    jPanelOfferMsg.setLayout(borderLayoutOfferMsg);
    jLabelOfferMsg.setText("Offer message:");
    jLabelOfferMsg.setVerticalAlignment(SwingConstants.TOP);
    jLabelOfferMsg.setVerticalTextPosition(SwingConstants.TOP);
    jPanelPolicy.setLayout(borderLayoutPolicy);
    jLabelPolicy.setText("Introduction message:");
    jLabelPolicy.setVerticalAlignment(SwingConstants.TOP);
    jLabelPolicy.setVerticalTextPosition(SwingConstants.TOP);
    jPanelTimeUnits.setLayout(borderLayout1);
    jLabelTimeUnits.setText("Time units:");
    jPanelMinTimeUnit.setLayout(borderLayout2);
    jLabelMinTimeUnit.setText("Min. time unit:");
    jPanelFreeAccessTime.setLayout(borderLayout3);
    jLabelFreeAccessTime.setText("Free access time:");
    jPanelMaxFreeAccessTime.setLayout(borderLayout4);
    jLabelMaxFreeAccessTime.setText("Max. free access time:");
    jPanelIntroMsg.add(jLabelIntroMsg, BorderLayout.WEST);
    jPanelIntroMsg.add(jScrollPaneIntroMsg, BorderLayout.CENTER);
    jScrollPaneIntroMsg.getViewport()
                       .add(jTextAreaIntroMsg, null);

    this.add(
      jPanelIntroMsg,
      new GridBagConstraints(
        0,
        0,
        1,
        1,
        1.0,
        1.0,
        GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL,
        new Insets(0, -1, 0, 3),
//        new Insets(3, 1, 0, 4),
        356,
        29));

    this.add(
      jPanelOfferMsg,
      new GridBagConstraints(
        0,
        1,
        1,
        1,
        1.0,
        1.0,
        GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL,
        new Insets(0, -1, 0, 3),
        356,
        29));
    jPanelOfferMsg.add(jLabelOfferMsg, BorderLayout.WEST);
    jPanelOfferMsg.add(jScrollPaneOfferMsg, BorderLayout.CENTER);
    this.add(
      jPanelPolicy,
      new GridBagConstraints(
        0,
        2,
        1,
        1,
        1.0,
        1.0,
        GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL,
        new Insets(0, -1, 0, 3),
        356,
        29));
    jScrollPaneOfferMsg.add(jTextAreaOfferMsg, null);
    jPanelPolicy.add(jLabelPolicy, BorderLayout.WEST);
    jPanelPolicy.add(jScrollPanePolicy, BorderLayout.CENTER);
    jScrollPanePolicy.add(jTextAreaPolicy, null);
    this.add(
      jPanelTimeUnits,
      new GridBagConstraints(
        0,
        3,
        1,
        1,
        1.0,
        1.0,
        GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL,
        new Insets(0, -1, 0, 3),
        306,
        -3));
    jPanelTimeUnits.add(jLabelTimeUnits, BorderLayout.WEST);
    jPanelTimeUnits.add(jComboBoxTimeUnits, BorderLayout.CENTER);
    this.add(
      jPanelMinTimeUnit,
      new GridBagConstraints(
        0,
        4,
        1,
        1,
        1.0,
        1.0,
        GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL,
        new Insets(0, -1, 0, 3),
        410,
        3));
    jPanelMinTimeUnit.add(jLabelMinTimeUnit, BorderLayout.WEST);
    jPanelMinTimeUnit.add(jTextFieldMinTimeUnit, BorderLayout.CENTER);
    this.add(
      jPanelFreeAccessTime,
      new GridBagConstraints(
        0,
        5,
        1,
        1,
        1.0,
        1.0,
        GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL,
        new Insets(0, -1, 0, 3),
        410,
        3));
    jPanelFreeAccessTime.add(jLabelFreeAccessTime, BorderLayout.WEST);
    jPanelFreeAccessTime.add(jTextFieldFreeAccessTime, BorderLayout.CENTER);
    this.add(
      jPanelMaxFreeAccessTime,
      new GridBagConstraints(
        0,
        6,
        1,
        1,
        1.0,
        1.0,
        GridBagConstraints.CENTER,
        GridBagConstraints.HORIZONTAL,
        new Insets(0, -1, 0, 3),
        410,
        3));
    jPanelMaxFreeAccessTime.add(jLabelMaxFreeAccessTime, BorderLayout.WEST);
    jPanelMaxFreeAccessTime.add(
      jTextFieldMaxFreeAccessTime,
      BorderLayout.CENTER);


    jScrollPaneOfferMsg.getViewport()
                       .add(jTextAreaOfferMsg, null);
    jScrollPanePolicy.getViewport()
                     .add(jTextAreaPolicy, null);

    // Labels prefered size
    jLabelIntroMsg.setPreferredSize(new Dimension(150, 60));
    jLabelOfferMsg.setPreferredSize(new Dimension(150, 60));
    jLabelPolicy.setPreferredSize(new Dimension(150, 60));
    jLabelTimeUnits.setPreferredSize(new Dimension(150, 20));
    jLabelMinTimeUnit.setPreferredSize(new Dimension(150, 20));
    jLabelFreeAccessTime.setPreferredSize(new Dimension(150, 20));
    jLabelMaxFreeAccessTime.setPreferredSize(new Dimension(150, 20));

    jScrollPaneIntroMsg.getVerticalScrollBar().setVisible(true);
    jScrollPaneOfferMsg.getVerticalScrollBar().setVisible(true);
    jScrollPanePolicy.getVerticalScrollBar().setVisible(true);

    jPanelIntroMsg.setBorder(BorderFactory.createEmptyBorder(5,5,0,5));
    jPanelOfferMsg.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
    jPanelPolicy.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
    jPanelTimeUnits.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
    jPanelMinTimeUnit.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
    jPanelFreeAccessTime.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
    jPanelMaxFreeAccessTime.setBorder(BorderFactory.createEmptyBorder(0,5,5,5));

  }
}
