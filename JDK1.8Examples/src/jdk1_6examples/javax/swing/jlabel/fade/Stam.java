/**
 * <p>
 * Classname: package jdk1_6examples.javax.swing.jlabel.fade.Stam
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
package jdk1_6examples.javax.swing.jlabel.fade;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Stam
    extends JFrame
    implements ActionListener {

  private JLabel label;
  int count = 255;
  private Timer timer;
  private Color colorFore;

  public Stam() {
    super("Sample fade");

    this.setLayout(new FlowLayout());
    this.label = new JLabel("some text");
    //this.label.setForeground(new Color(0, 0, 0, 0));

    this.colorFore = this.label.getForeground();

    this.add(label);

    JButton button = new JButton("fade-in");
    button.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {
        timer = new Timer(25, Stam.this);
        timer.start();
      }
    });
    this.add(button);

    this.pack();
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void actionPerformed(ActionEvent e) {
    count -= 5;
    if(count < 0) {
      timer.stop();
      count = 255;
      return;
    }

    label.setForeground(
        new Color(
            this.colorFore.getRed(),
            this.colorFore.getGreen(),
            this.colorFore.getBlue(),
            count));

    System.out.println("count: "+count);    
  }

  public void actionPerformed_fromBlankToRed(ActionEvent e) {
    count += 5;
    if(count > 255) {
      timer.stop();
      return;
    }

    label.setForeground(new Color(255, 0, 0, count));
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {

      public void run() {
        new Stam().setVisible(true);
      }
    });
  }
}