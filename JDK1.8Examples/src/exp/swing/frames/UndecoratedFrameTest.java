package exp.swing.frames;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class UndecoratedFrameTest
    extends JFrame {
  private boolean unDecorated = false;
  private DecorateAction action;
  private JPanel mainPanel;

  public UndecoratedFrameTest() {
    action = new DecorateAction("Toggle");
    buildGUI();
  }

  private void buildGUI() {
    mainPanel = (JPanel) getContentPane();
    mainPanel.setLayout(new BorderLayout());
    JPanel centerPanel = new JPanel();
    centerPanel.add(new JLabel("Center"));
    mainPanel.add(centerPanel, BorderLayout.CENTER);
    setUndecorated(unDecorated);
    JButton decButton = new JButton(action);

    mainPanel.add(decButton, BorderLayout.SOUTH);
    mainPanel.getActionMap().put("toggle", action);
    mainPanel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),
                                "toggle");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
  }

  private void toggleDecoration() {
    dispose();
    setUndecorated(!unDecorated);
    mainPanel.getActionMap().put("toggle", action);
    mainPanel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),
                                "toggle");
    setVisible(true);
    unDecorated = !unDecorated;
  }

  public static void main(String[] args) {
    Runnable runnable = new Runnable() {
      public void run() {
        UndecoratedFrameTest undecoratedFrameTest = new UndecoratedFrameTest();
        undecoratedFrameTest.setVisible(true);
      }
    };
    SwingUtilities.invokeLater(runnable);

  }

  private class DecorateAction
      extends AbstractAction {

    public DecorateAction(String name) {
      super(name);
    }

    public void actionPerformed(ActionEvent e) {
      toggleDecoration();

    }
  }

}

