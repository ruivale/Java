/**
 * <p>
 * Classname:  jdk1_6examples.javax.swing.jdialog.MyOptionPane
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

package jdk1_6examples.javax.swing.jdialog;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author rvale
 */
public class MyOptionPane {

  private static String STR_OK = "OK";
  private static String STR_NO = "No";
  private static String STR_CANCEL = "Cancelar";
  private static String STR_CLOSE = "Close";

  static{
    try {

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static volatile int iOptionPaneResult = JOptionPane.CLOSED_OPTION;

  /**
   *
   * @param window
   * @param strTitle
   * @param strMessage
   * @param iButtons
   * @return
   */
  public static int showOptionPane(
      final Window window,
      final String strTitle,
      final String strMessage,
      final int[] iButtons) {

    iOptionPaneResult = JOptionPane.CANCEL_OPTION;

    final JDialog jDig = new JDialog(
        window,
        strTitle,
        ModalityType.APPLICATION_MODAL);

    final JButton jBOk = new JButton(STR_OK);
    final JButton jBNo = new JButton(STR_NO);
    final JButton jBCancel = new JButton(STR_CANCEL);
    final JButton jBClose = new JButton(STR_CLOSE);

    jBOk.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        iOptionPaneResult = JOptionPane.OK_OPTION;
        jDig.setVisible(false);
      }
    });
    jBNo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        iOptionPaneResult = JOptionPane.NO_OPTION;
        jDig.setVisible(false);
      }
    });
    jBCancel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        iOptionPaneResult = JOptionPane.CANCEL_OPTION;
        jDig.setVisible(false);
      }
    });
    jBClose.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        iOptionPaneResult = JOptionPane.CLOSED_OPTION;
        jDig.setVisible(false);
      }
    });

    final JLabel jLMessage = new JLabel(strMessage);
    jLMessage.setBorder(BorderFactory.createEmptyBorder(10, 5, 20, 10));

    final JPanel jPBtts = new JPanel(new GridLayout(1,iButtons.length,3,3));

    for(int iBtt: iButtons){
      if(iBtt == JOptionPane.OK_OPTION){
        jPBtts.add(jBOk);
      }else if(iBtt == JOptionPane.NO_OPTION){
        jPBtts.add(jBNo);
      }else if(iBtt == JOptionPane.CANCEL_OPTION){
        jPBtts.add(jBCancel);
      }else if(iBtt == JOptionPane.CLOSED_OPTION){
        jPBtts.add(jBClose);
      }
    }

    final JPanel jPSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
    jPSouth.add(jPBtts);

    jDig.setLayout(new BorderLayout());
    jDig.add(jLMessage, BorderLayout.CENTER);
    jDig.add(jPSouth, BorderLayout.SOUTH);

    jDig.pack();
    jDig.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    jDig.setVisible(true);


    return iOptionPaneResult;
  }



  
  public static void main(String[] args) {
    int iResult = -1;

    final int[] iButtons = {
      JOptionPane.OK_OPTION,
      JOptionPane.CANCEL_OPTION,
      JOptionPane.CLOSED_OPTION
    };
    iResult = showOptionPane(
        new JFrame(),
        "title",
        "message bla  bla  bla  bla  bla  bla  bla  bla  bla  bla  bla  bla  bla  bla  bla ",
        iButtons);

    System.out.println("Result: "+iResult);

    final int[] iButtons2 = {
      JOptionPane.OK_OPTION,
      JOptionPane.NO_OPTION
    };
    iResult = showOptionPane(
        new JFrame(),
        "title",
        "message bla  bla  bla  bla  bla  bla  bla  bla  bla  bla  bla  bla  bla  bla  bla ",
        iButtons2);
    System.out.println("Result: "+iResult);


    System.exit(0);
  }
}
