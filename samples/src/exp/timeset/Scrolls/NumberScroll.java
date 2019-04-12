package exp.timeset.Scrolls;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:
 * @author
 * @version 1.0
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class NumberScroll extends JPanel {

  public JTextField tf = new JTextField(4);
  JScrollBar scroll = new JScrollBar();
  public int scrollValue=0;
  public int value;
  public int oldValue=0;
  public String text="0";
  public static int maxValue;


  public NumberScroll(int minValue, int maxValue) {


    final int maxVal = maxValue;
    this.maxValue = maxValue;

    value = minValue;
    text="" + minValue;
    Container contentPane = this;
    tf.setCaretColor(Color.white);
    scroll.setMaximum(60000);
    scroll.setMinimum(-60000);
    tf.setText(text);


    final int minVal= minValue;
    tf.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

       int newValue = (new Integer(tf.getText())).intValue();

       if(e.getActionCommand().equals("")) {
          tf.setText("1");
        } else {
          if(newValue>maxVal) {
              tf.setText("" + maxVal);
            } else {
              if(newValue<minVal){
                  tf.setText("" + minVal);
                }
              }
            value = (new Integer(tf.getText())).intValue();
          }
      }
     });


    tf.addMouseMotionListener(new MouseMotionListener(){
    public void mouseMoved(MouseEvent e){}
    public void mouseDragged(MouseEvent e){
      tf.select(0,tf.getText().length());
      }
    });


    tf.addMouseListener(new MouseListener(){
      public void mouseExited(MouseEvent e){
      }
      public void mouseEntered(MouseEvent e){}
      public void mouseReleased(MouseEvent e){
        tf.select(0,tf.getText().length());
      }
      public void mousePressed(MouseEvent e){
        tf.select(0,tf.getText().length());
      }
      public void mouseClicked(MouseEvent e){
        tf.select(0,tf.getText().length());
      }
    });


    scroll.addAdjustmentListener(new AdjustmentListener(){
      public void adjustmentValueChanged(AdjustmentEvent e){


        oldValue = (new Integer(tf.getText())).intValue();

       if((e.getValue()-scrollValue)>0) {

         if(oldValue <= minVal )  {
           return;
          }
          //scrollDown
          //if (value > 0) {
            value--;
            tf.setText("" + value);
          //}
          scrollValue=e.getValue();

        } else {
          if((e.getValue()-scrollValue)< 0) {

           if(oldValue >= maxVal)  {
             return;
             }
             //scrollUp
             value++;
             tf.setText("" + value);
             scrollValue=e.getValue();
           }
        }


        //scrollValue=e.getValue();
      }});

    // Add components
    GridBagLayout layout = new GridBagLayout();
    contentPane.setLayout(layout);

    //contentPane.setLayout(new FlowLayout());
    contentPane.add(tf, new GridBagConstraints(0, 0, 0, 0, 0.0, 0.0,
    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
      new Insets(0, 0, 0, 0), 0, 0));

    scroll.setPreferredSize(new Dimension(18,tf.getPreferredSize().height));
    contentPane.add(scroll, new GridBagConstraints(1, 0, 0, 0, 0.0, 0.0,
    GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
      new Insets(0, tf.getPreferredSize().width, 0, 0), 0, 0));
  }


}
