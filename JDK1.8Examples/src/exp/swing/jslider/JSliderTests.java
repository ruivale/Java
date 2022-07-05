package exp.swing.jslider;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.*;
import javax.swing.plaf.*;

/**
 * <p>
 * Title: </p>
 *
 * <p>
 * Description: </p>
 *
 * <p>
 * Copyright: Copyright (c) </p>
 *
 * <p>
 * Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class JSliderTests extends JPanel {

  private int iOldValue = 0;
  private boolean wasMouseClick = false;

  final JSlider sl = new JSlider(0, 100, 50);

  static {
    try {
      //-Dswing.defaultlaf=javax.swing.plaf.nimbus.NimbusLookAndFeel
      System.setProperty("swing.defaultlaf", "javax.swing.plaf.nimbus.NimbusLookAndFeel");
      //System.setProperty("swing.defaultlaf","com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }



  public JSliderTests() {

//    try {
//      sl.setUI(new MetalSliderUI() {
//
//        protected void scrollDueToClickInTrack(int direction) {
//          // this is the default behaviour, let's comment that out
//          scrollByBlock(direction);
//
//          int value = sl.getValue();
//
//          if (sl.getOrientation() == JSlider.HORIZONTAL) {
//            value = this.valueForXPosition(sl.getMousePosition().x);
//          } else if (sl.getOrientation() == JSlider.VERTICAL) {
//            value = this.valueForYPosition(sl.getMousePosition().y);
//          }
//          sl.setValue(value);
//        }
//      });
//
//    } catch (Exception e) {
//      e.printStackTrace();
//
//      System.out.println("sl.getUIClassID(): "+sl.getUIClassID());
//
//      try {
//        sl.setUI(new MySliderUI());
//
//      } catch (Exception exc) {
//        exc.printStackTrace();
//      }
//
//    }
    sl.addChangeListener(new ChangeListener() {

      public void stateChanged(ChangeEvent e) {
//
//        if (!sl.getValueIsAdjusting() && wasMouseClick) {
//          // probably a mouse clicked event
//          System.out.println("stateChanged value=" + sl.getValue() + " old=" + iOldValue);
//          System.out.println(e.toString());
//
//          if (iOldValue != sl.getValue()) {
//
//            final ChangeListener[] changeListeners = sl.getChangeListeners();
//            for (int i = 0; i < changeListeners.length; i++) {
//              sl.removeChangeListener(changeListeners[i]);
//            }
//
//            final int iInterval = (sl.getMaximum() - sl.getMinimum()) / 10;
//
//            if (iOldValue > sl.getValue()) {
//              // left
//              final int iNewValue = sl.getValue() - iInterval;
//              sl.setValue(iNewValue > sl.getMinimum() ? iNewValue : sl.getMinimum());
//              System.out.println("<-");
//
//            } else {
//              // right
//              final int iNewValue = sl.getValue() + iInterval;
//              sl.setValue(iNewValue < sl.getMaximum() ? iNewValue : sl.getMaximum());
//              System.out.println("->");
//            }
//
//            for (int i = 0; i < changeListeners.length; i++) {
//              sl.addChangeListener(changeListeners[i]);
//            }
//
//          }
//
//          wasMouseClick = false;
//        }
      }
    });

    sl.addMouseListener(new MouseAdapter() {
//      @Override
//      public void mousePressed(MouseEvent e) {
//        wasMouseClick = true;
//
//        System.out.println("mousePressed value=" + sl.getValue());
//
//        iOldValue = sl.getValue();
//      }
//

      @Override
      public void mouseReleased(MouseEvent e) {
        //System.out.println("released");
        process();
      }

      @Override
      public void mousePressed(MouseEvent e) {
        //System.out.println("pressed");
        process();
      }


      private void process() {
        System.out.println("mouseReleased value=" + sl.getValue());

        final BasicSliderUI sliderUI = (BasicSliderUI) sl.getUI();
        //sliderUI.scrollByBlock(1);

        int value = sl.getValue();

        if (sl.getOrientation() == JSlider.HORIZONTAL) {
          value = sliderUI.valueForXPosition(sl.getMousePosition().x);
        } else if (sl.getOrientation() == JSlider.VERTICAL) {
          value = sliderUI.valueForYPosition(sl.getMousePosition().y);
        }
        sl.setValue(value);
      }

    });

//    sl.addKeyListener(new KeyAdapter(){
//      public void keyPressed(KeyEvent e){
//        sl.setValueIsAdjusting(true);
//      }
//      public void keyReleased(KeyEvent e){
//        sl.setValueIsAdjusting(false);
//      }
//    });
    setLayout(new BorderLayout());
    add(sl);

    final Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          Thread.sleep(2500);
        } catch (InterruptedException interruptedException) {
        }

        while (true) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException interruptedException) {
          }

          int value = sl.getValue();

          if (value > sl.getMaximum() - 2) {
            value = 5;
          }

          sl.setValue(value + 1);
        }
      }
    }, "ThreadName");
    thread.setDaemon(true);
    thread.start();

  }

  public static void main(String[] args) {
    JSliderTests j = new JSliderTests();
    JFrame frame = new JFrame(" ");
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(j);
    frame.setBounds(100, 100, 600, 200);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /**
   *
   */
  class MySliderUI extends /*MetalSliderUI*/ BasicSliderUI {

    public MySliderUI() {
      super(null);
    }

    protected void scrollDueToClickInTrack(int direction) {

      System.out.println("scrollDueToClickInTrack(" + direction + ")");

      // this is the default behaviour, let's comment that out
      scrollByBlock(direction);

      int value = sl.getValue();

      if (sl.getOrientation() == JSlider.HORIZONTAL) {
        value = this.valueForXPosition(sl.getMousePosition().x);
      } else if (sl.getOrientation() == JSlider.VERTICAL) {
        value = this.valueForYPosition(sl.getMousePosition().y);
      }
      sl.setValue(value);
    }
  }

}
