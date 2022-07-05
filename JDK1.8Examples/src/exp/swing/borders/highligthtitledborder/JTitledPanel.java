package exp.swing.borders.highligthtitledborder;

import javax.swing.*;
import java.awt.*;
/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class JTitledPanel
    extends JComponent {
  JCheckBox titleLabel = new JCheckBox();
//  JLabel titleLabel = new JLabel();

  /***/
  FocusOwnerTracker tracker = new FocusOwnerTracker(this) {
    public void focusGained() {
      titleLabel.setForeground(UIManager.getColor("textHighlightText"));
      titleLabel.setBackground(UIManager.getColor("textHighlight"));
    }

    public void focusLost() {
      titleLabel.setForeground(UIManager.getColor("textText"));
      titleLabel.setBackground(UIManager.getColor("control").darker());
    }
  };
  /**/

  JPanel contents = new JPanel(new BorderLayout());

  public JTitledPanel(String title) {
    setLayout(new BorderLayout());
    titleLabel.setText(title);
    titleLabel.setOpaque(true);
    titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
    titleLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
    //titleLabel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    //tracker.focusLost();

    contents.setBorder(BorderFactory.createLineBorder(Color.RED, 5));

    add(titleLabel, BorderLayout.NORTH);
    add(contents, BorderLayout.CENTER);

    // Memory-Leak occurs here. Why ?
    // How to avoid this ?
    //tracker.start();
  }

  public JPanel getContents() {
    return contents;
  }

  public static void main(String[] args) {
    JTitledPanel j = new JTitledPanel("TITLE");
    javax.swing.JFrame f = new javax.swing.JFrame();
    f.getContentPane().setLayout(new java.awt.BorderLayout());
    f.getContentPane().add(j, java.awt.BorderLayout.CENTER);
    f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    //f.pack();
    f.setSize(new Dimension(750, 500));
    f.setVisible(true);

  }
}
