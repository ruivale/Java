/**
 * <p>
 * Classname: package jdk1_6examples.outofmemorytests.MemoryRecoveryTest
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
package jdk1_6examples.outofmemorytests;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;


/** A demo. showing recovery from an OutOfMemoryError.
Our options once an OOME is encountered are relatively
few, but we can still warn the end user and provide
advice on how to correct the problem.
@author Andrew Thompson */
public class MemoryRecoveryTest {

  public static void main(String[] args) {
    ArrayList<Object> list = new ArrayList<Object>();
    byte[] buffer = new byte[32000];

    final JProgressBar memory = new JProgressBar(
        0,
        (int) Runtime.getRuntime().totalMemory());
    ActionListener listener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        memory.setValue(
            (int) Runtime.getRuntime().freeMemory());
      }
    };
    Timer timer = new Timer(500, listener);
    timer.start();

    JDialog dialog = new JDialog();
    dialog.setTitle("Available Memory");
    JPanel memoryPanel = new JPanel();
    memoryPanel.add(memory);
    memoryPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
    dialog.add(memoryPanel);
    dialog.pack();
    dialog.setLocationRelativeTo(null);
    dialog.setVisible(true);
    dialog.addWindowListener(new WindowAdapter() {

      @Override
      public void windowClosing(WindowEvent we) {
        System.exit(0);
      }
    });

    // prepare a memory warning panel in advance
    JPanel memoryWarning = new JPanel();
    memoryWarning.add(new JLabel(
        "<HTML><BODY>There is not enough memory to" +
        " complete the task!<BR> Use a variant " +
        " of the application that assigns more memory."));

    try {
      // do our 'memory intensive' task
      while(true) {
        list.add(new Object());
      }
    } catch(OutOfMemoryError oome) {
      // provide the VM with some memory 'breathing space'
      // by clearing the buffer
      buffer = null;
      // tell the user what went wrong, and how to fix it
      JOptionPane.showMessageDialog(
          dialog,
          memoryWarning,
          "Out of Memory!",
          JOptionPane.ERROR_MESSAGE);
    }
  }
}