/**
 * <p>
 * Classname: exp.swing.layout.cardlayout.CardLayoutSample
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2019 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
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
package exp.swing.layout.cardlayout;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CardLayoutSample implements ItemListener {

  JPanel cards; //a panel that uses CardLayout
  final static String BUTTONPANEL = "Card with JButtons";
  final static String TEXTPANEL = "Card with JTextField";

  public void addComponentToPane(Container pane) {
    //Put the JComboBox in a JPanel to get a nicer look.
    JPanel comboBoxPane = new JPanel(); //use FlowLayout
    String comboBoxItems[] = {BUTTONPANEL, TEXTPANEL};
    JComboBox cb = new JComboBox(comboBoxItems);
    cb.setEditable(false);
    cb.addItemListener(this);
    comboBoxPane.add(cb);

    //Create the "cards".
    JPanel card1 = new JPanel();
    card1.add(new JButton("Button 1"));
    card1.add(new JButton("Button 2"));
    card1.add(new JButton("Button 3"));

    JPanel card2 = new JPanel();
    card2.add(new JTextField("TextField", 20));

    //Create the panel that contains the "cards".
    cards = new JPanel(new CardLayout());
    cards.add(card1, BUTTONPANEL);
    cards.add(card2, TEXTPANEL);

    pane.add(comboBoxPane, BorderLayout.PAGE_START);
    pane.add(cards, BorderLayout.CENTER);
  }

  public void itemStateChanged(ItemEvent evt) {
    CardLayout cl = (CardLayout) (cards.getLayout());
    cl.show(cards, (String) evt.getItem());
  }

  /**
   * Create the GUI and show it. For thread safety, this method should be invoked from the event
   * dispatch thread.
   */
  private static void createAndShowGUI() {
    //Create and set up the window.
    JFrame frame = new JFrame("CardLayoutDemo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Create and set up the content pane.
    CardLayoutSample demo = new CardLayoutSample();
    demo.addComponentToPane(frame.getContentPane());

    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    /* Use an appropriate Look and Feel */
    try {
      //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
    } catch (UnsupportedLookAndFeelException ex) {
      ex.printStackTrace();
    } catch (IllegalAccessException ex) {
      ex.printStackTrace();
    } catch (InstantiationException ex) {
      ex.printStackTrace();
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
    }
    /* Turn off metal's use of bold fonts */
    UIManager.put("swing.boldMetal", Boolean.FALSE);

    //Schedule a job for the event dispatch thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
}
