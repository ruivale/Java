/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk1_6examples.regexp.patterns;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import javax.swing.*;


public class TestPatterns
    extends JFrame
    implements ActionListener {

  JTextArea text = new JTextArea(5, 29);
  JTextField pattern = new JTextField(35);
  JButton search = new JButton("Search");
  JButton newSearch = new JButton("New Search");
  JTextArea result = new JTextArea(5, 29);

  public TestPatterns() {
    super("Test Patterns");
    setSize(430, 320);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container pane = getContentPane();
    GridLayout grid = new GridLayout(3, 1);
    pane.setLayout(grid);
    // set up the top row
    JLabel textLabel = new JLabel("Text: ");
    JPanel row1 = new JPanel();
    row1.add(textLabel);
    JScrollPane scroll = new JScrollPane(text,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    row1.add(scroll);
    // set up the middle row
    JPanel row2 = new JPanel();
    JLabel patternLabel = new JLabel("Pattern: ");
    row2.add(patternLabel);
    row2.add(pattern);
    search.addActionListener(this);
    newSearch.addActionListener(this);
    row2.add(search);
    row2.add(newSearch);
    // set up the bottom row
    JPanel row3 = new JPanel();
    JLabel resultLabel = new JLabel("Result: ");
    row3.add(resultLabel);
    result.setEditable(false);
    JScrollPane scroll2 = new JScrollPane(result,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    row3.add(scroll2);
    // set up the content pane
    pane.add(row1);
    pane.add(row2);
    pane.add(row3);
    setContentPane(pane);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent evt) {
    Object source = evt.getSource();
    if(source ==
        search) {
      checkPattern();
    } else {
      pattern.setText("");
      result.setText("");
    }
  }

  private void checkPattern() {
    try {
      if(Pattern.matches(pattern.getText(), text.getText())) {
        result.setText("That pattern was found");
      } else {
        result.setText("That pattern was not found");
      }
    } catch(PatternSyntaxException pse) {
      result.setText("Regex error: " +
          pse.getMessage());
    }
  }

  public static void main(String[] arguments) {
    TestPatterns app = new TestPatterns();
  }
}