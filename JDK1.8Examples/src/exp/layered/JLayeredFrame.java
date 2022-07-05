package exp.layered;

import javax.swing.JFrame;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JLayeredFrame extends JFrame {

  private JLayeredPane lp = new JLayeredPane();

  private Integer[] layers = {
    JLayeredPane.FRAME_CONTENT_LAYER,
    JLayeredPane.DEFAULT_LAYER,
    JLayeredPane.DEFAULT_LAYER,
    JLayeredPane.DEFAULT_LAYER,
    JLayeredPane.PALETTE_LAYER,
    JLayeredPane.MODAL_LAYER,
    JLayeredPane.POPUP_LAYER,
    JLayeredPane.DRAG_LAYER,
  };
  private final Component[] comps = {
    new JButton("Frame Content"), new JButton("Default"),
    new JButton("default 1"), new JButton("default 2"),
    new JButton("Palette"), new JButton("Modal"),
    new JButton("Popup"), new JButton("Drag"),
  };
  public JLayeredFrame() {
    setContentPane(lp);
    lp.setLayout(null);

    for (int i = 0; i < comps.length; ++i) {
      AbstractButton button = (AbstractButton)comps[i];
      String t = button.getText();

      lp.setLayer(button, layers[i].intValue());
      lp.add(button);
    }
    for (int i = 0; i < comps.length; ++i) {
      AbstractButton button = (AbstractButton)comps[i];
      String t = button.getText();
      String replacement = new String("Layer:  ");

      if (t.equals("Default"))
        lp.setPosition(button, 2);
      else if (t.equals("default 2"))
        lp.setPosition(button, 0);

      replacement += t + "(" + lp.getLayer(button) + "),";
      replacement += "  Index:  " + lp.getIndexOf(button);
      replacement += "  Position:  " +
                     lp.getPosition(button);

      button.setText(replacement);
      button.setOpaque(false);
      button.setBounds(i*50, i*50, 350, 75);
    }
  }

  public static void main(String[] args) {
    JLayeredFrame f = new JLayeredFrame();
    f.setBounds(100, 100, 500, 400);
    f.setVisible(true);
  }
}
