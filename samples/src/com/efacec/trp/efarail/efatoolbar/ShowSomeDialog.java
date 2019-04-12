package com.efacec.trp.efarail.efatoolbar;

import java.awt.Font;
import java.util.Arrays;


public class ShowSomeDialog {

  public static void main(final String[] args) {

    javax.swing.SwingUtilities.invokeLater(() -> {
      try {
        javax.swing.JFrame f = new javax.swing.JFrame("ShowSomeDialog");
        javax.swing.JLabel l = new javax.swing.JLabel();
        l.setFont(new java.awt.Font("SimSun", Font.PLAIN, 10));
        l.setText("ShowSomeDialog args:" + Arrays.toString(args));
        f.setLayout(new java.awt.BorderLayout());
        f.add(l, java.awt.BorderLayout.NORTH);
        javax.swing.JTextArea l2 = new javax.swing.JTextArea();
        l2.setFont(new java.awt.Font("SimSun", java.awt.Font.PLAIN, 10));

        l2.append("-------------------------------------------------------------\n");
        for (String s : args) {

          l2.append("original:" + s);
          l2.append("\n\tDecoded(UTF-8):" + java.net.URLDecoder.decode(s, "UTF-8"));
          l2.append("\n\tUTF-8:" + new String(s.getBytes(), "UTF-8"));

          l2.append("\n");

        }

        f.add(l2, java.awt.BorderLayout.CENTER);
        f.setBounds(700, 550, 550, 325);
        f.setVisible(true);

      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    System.out.println("\n\n-----------------------------------------------------------");
    System.out.println("ShowSomeDialog args: ");
    for (String s : args) {
      System.out.print(s + ", ");
    }

  }

}
