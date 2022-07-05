package jdk1_6examples.javax.swing.tabpane;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class JDK6TabSample {
  static void addIt(JTabbedPane tabbedPane, String text) {
    JLabel label = new JLabel(text);
    JButton button = new JButton(text);
    JPanel panel = new JPanel();
    panel.add(label);
    panel.add(button);
    tabbedPane.addTab(text, panel);
    tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, new JTextField(text));
  }

  public static void main(String args[]) {
    JFrame f = new JFrame("Got JTabbedPane?");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JTabbedPane tabbedPane = new JTabbedPane();
    addIt(tabbedPane, "Tab One");
    addIt(tabbedPane, "Tab Two");
    addIt(tabbedPane, "Tab Three");
    addIt(tabbedPane, "Tab Four");
    addIt(tabbedPane, "Tab Five");
    f.add(tabbedPane, BorderLayout.CENTER);
    f.setSize(300, 200);
    f.setVisible(true);

  }
}
