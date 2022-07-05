/**
 * <p>
 * Classname: jdk8examples.i18n.arabic.RightToLeftTextCompsSample
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2018 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC SE
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
package jdk8examples.i18n.arabic;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;



/**
 *
 */
public class RightToLeftTextCompsSample extends JFrame {

  /**
   *
   */
  public RightToLeftTextCompsSample() {
    super("Test");

    String str1 = "ARA \u062a\u0639\u0637\u064a \u064a\u0648\u0646\u064a\u0643\u0648\u062f " +
         "\u0631\u0642\u0645\u0627 \u0641\u0631\u064a\u062f\u0627 \u0644\u0643\u0644 \u062d\u0631\u0641";

    JLabel testLabel = new JLabel();
    testLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

    //testLabel.setText( str1 );
    testLabel.setText("ca 12 ?????");

    testLabel.setBorder(new LineBorder(Color.BLACK));

    JTextField testTextField = new JTextField();
    testTextField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

    //testTextField.setText( str1 );
    testTextField.setText("ca 12 ?????");

    testTextField.setBorder(new LineBorder(Color.BLACK));

    JPanel panel = new JPanel(new BorderLayout(5, 5));
    panel.setBorder(new EmptyBorder(10, 10, 10, 10));
    panel.add(testLabel, BorderLayout.NORTH);
    panel.add(testTextField, BorderLayout.SOUTH);

    setContentPane(panel);
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        new RightToLeftTextCompsSample();
      }
    });
  }

}
