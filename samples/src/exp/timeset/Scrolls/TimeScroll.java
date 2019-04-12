package exp.timeset.Scrolls;

/**
 * Title:        DB Manager
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Apps
 * @author Rolando Martins
 * @version 1.0
 */


import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.*;
import java.util.StringTokenizer;
import javax.swing.text.Caret;
import javax.swing.text.Keymap;


public class TimeScroll extends JPanel {

  JTextField tf = new JTextField();
  public DateDocument dc;
  JScrollBar scroll = new JScrollBar();
  public int scrollValue=0;
  public String text="";
  public int value =0;
  public int hour =0, minute =0;

  public TimeScroll() {

    Container contentPane = this;
    dc = new DateDocument(tf);
    tf.setText(dc.initString);
    tf.setDocument(dc);
    tf.setCaretColor(Color.white);
    scroll.setMaximum(60000);
    scroll.setMinimum(-60000);

    tf.addMouseMotionListener(new MouseMotionListener(){
    public void mouseMoved(MouseEvent e){}
    public void mouseDragged(MouseEvent e){
      checkDoc();
      int pos=tf.getCaretPosition();
      if(pos>=1 && pos<=3)tf.select(0,2);
      if(pos>=4 && pos<=6)tf.select(3,5);
/*      if(pos>=7 && pos<=8)tf.select(6,8);*/
      dc.insertFlag=2;
      }
    });

    tf.addMouseListener(new MouseListener(){
      public void mouseExited(MouseEvent e){
        checkDoc();
      }
      public void mouseEntered(MouseEvent e){}
      public void mouseReleased(MouseEvent e){
        checkDoc();
        int pos=tf.getCaretPosition();
        if(pos>=1 && pos<=3)tf.select(0,2);
        if(pos>=4 && pos<=6)tf.select(3,5);
        /*if(pos>=7 && pos<=8)tf.select(6,8);*/
        dc.insertFlag=2;
      }
      public void mousePressed(MouseEvent e){
        checkDoc();
        int pos=tf.getCaretPosition();
        if(pos>=1 && pos<=3)tf.select(0,2);
        if(pos>=4 && pos<=6)tf.select(3,5);
        /*if(pos>=7 && pos<=8)tf.select(6,8);*/
        dc.insertFlag=2;
      }
      public void mouseClicked(MouseEvent e){
        checkDoc();
        int pos=tf.getCaretPosition();
        if(pos>=1 && pos<=3)tf.select(0,2);
        if(pos>=4 && pos<=6)tf.select(3,5);
        /*if(pos>=7 && pos<=8)tf.select(6,8);*/
        dc.insertFlag=2;
      }
    });

    scroll.addAdjustmentListener(new AdjustmentListener(){
      public void adjustmentValueChanged(AdjustmentEvent e){
        if(tf.getSelectedText()==null){
          tf.select(0,2);
        }
        if((e.getValue()-scrollValue)>0) {scrollDown(); value--;}
        else {
          scrollUp();
          value++;
        }
        scrollValue=e.getValue();
        dc.insertFlag=2;
        dc.flag=0;
      }});

      tf.addFocusListener(new FocusListener(){
      public void focusLost(FocusEvent e){
        if(tf.getText()=="" ||tf.getText()==null)
          return;
        checkDoc();
      }
      public void focusGained(FocusEvent e){}});

      // Add components
      GridBagLayout layout = new GridBagLayout();
      contentPane.setLayout(layout);

      //contentPane.setLayout(new FlowLayout());
      contentPane.add(tf, new GridBagConstraints(0, 0, 0, 0, 0.0, 0.0,
        GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
        new Insets(0, 0, 0, 0), 0, 0));

      scroll.setPreferredSize(new Dimension(20,tf.getPreferredSize().height));
      contentPane.add(scroll, new GridBagConstraints(1, 0, 0, 0, 0.0, 0.0,
        GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
        new Insets(0, tf.getPreferredSize().width, 0, 0), 0, 0));
  }


  void checkDoc() {

    String initString=tf.getText();
    int offset=tf.getCaretPosition();

    if(dc.checkTime(initString)==false){
       if(offset<2){
          initString=dc.S_hour+initString.substring(3,initString.length());
       }
       else
        if(offset<5) {
          initString = initString.substring(0,3)+ dc.S_minute;/* +
                        initString.substring(6,initString.length());*/
        }else
            initString=initString.substring(0,5);//+dc.S_second;*/
      dc.initString=initString;
      dc.insertString2(0,initString);

      }
  }

  public void setTime(String time){
    dc.setTime(time);
  }

  public String getTime(){
    return dc.getTime();
  }


  void scrollUp(){

    if(!this.isEnabled())
      return;

    int pos;

    text=dc.getTime();
    StringTokenizer st = new StringTokenizer(text,":");
    Integer value;
    String s_value;
    dc.insertFlag=2;

    if(tf.getSelectedText()!=null)
      pos=tf.getCaretPosition();
    else
      return;

    if(pos>=0 && pos<=3) {
      value=new Integer(st.nextToken());
      if(value.intValue()+1 < 24 && value.intValue()+1>=0) {
        value=new Integer(value.intValue()+1);
        s_value=value.toString();
        if(s_value.length()==1)
          s_value="0"+s_value;
        //text=s_value+text.substring(2,8);
        text=s_value+text.substring(2,5);
        dc.initString=text;
        try{
          dc.insertString2(0,text);
        }
        catch(Exception e) {


        }
        hour++;
      }
      tf.select(0,2);
    }
    if(pos>=4 && pos<=6){
      st.nextToken();
      String tt = st.nextToken();
      tt=tt.substring(0,tt.length()-1);
      value=new Integer(tt);
      if(value.intValue()+1<60 && value.intValue()+1>=0){
        value=new Integer(value.intValue()+1);
        s_value=value.toString();
        if(s_value.length()==1)s_value="0"+s_value;
        //text=text.substring(0,3) + s_value+text.substring(5,8);
        text=text.substring(0,3) + s_value;//+text.substring(5,6);
        dc.initString=text;
        try{dc.insertString2(0,text);} catch(Exception e){}
        minute++;
      }
      tf.select(3,5);

    }
/*    if(pos>=7 && pos<=8){
      st.nextToken();
      st.nextToken();
      value=new Integer(st.nextToken());
      if(value.intValue()+1<60 && value.intValue()+1>=0){
        value=new Integer(value.intValue()+1);
        s_value=value.toString();
        if(s_value.length()==1)s_value="0"+s_value;
        text=text.substring(0,6)+s_value;
        dc.initString=text;
        try{dc.insertString2(0,text);} catch(Exception e){System.out.println("Dor");}

      }
      tf.select(6,8);
    }*/

  }

  void scrollDown(){
    if(!this.isEnabled())return;
      int pos;
      text=dc.getTime();
      StringTokenizer st = new StringTokenizer(text,":");
      Integer value;
      String s_value;

      if(tf.getSelectedText()!=null)
        pos=tf.getCaretPosition();
      else
        return;

      if(pos>=0 && pos<=3) {
        value=new Integer(st.nextToken());
        if(value.intValue()<24 && value.intValue()>0) {
          value=new Integer(value.intValue()-1);
          s_value=value.toString();
          if(s_value.length()==1)s_value="0"+s_value;
            //text=s_value+text.substring(2,8);
            text=s_value+text.substring(2,5);
          dc.initString=text;
          try {
            dc.insertString2(0,text);
          } catch(Exception e) {




            }
          hour--;
        }

        tf.select(0,2);
      }

      if(pos>=4 && pos<=6){
        st.nextToken();
        String tt = st.nextToken();
        tt=tt.substring(0,tt.length()-1);
        value=new Integer(tt);
        if(value.intValue()<60 && value.intValue()>0){
          value=new Integer(value.intValue()-1);
          s_value=value.toString();
          if(s_value.length()==1)
            s_value="0"+s_value;
          //text=text.substring(0,3)+s_value+text.substring(5,8);
          text=text.substring(0,3)+s_value;//+text.substring(5,6);
          dc.initString=text;
          try {
            dc.insertString2(0,text);
          } catch(Exception e){




            }
          minute--;
        }
        tf.select(3,5);
      }
      /*if(pos>=7 && pos<=8) {
        st.nextToken();
        st.nextToken();
        value=new Integer(st.nextToken());

        if(value.intValue()<60 && value.intValue()>0){
          value=new Integer(value.intValue()-1);
          s_value=value.toString();
          if(s_value.length()==1)
            s_value="0"+s_value;
          text=text.substring(0,6)+s_value;
          dc.initString=text;
          try{
            dc.insertString2(0,text);
          } catch(Exception e){
              System.out.println("Dor");
            }
        }
        tf.select(6,8);
      }*/
  }

  public void setEnabled(boolean enable){
    super.setEnabled(enable);
    scroll.setEnabled(enable);
    tf.setEditable(enable);
  }

  static public void main(String[] args) {
    TimeScroll time =new TimeScroll();
    JFrame frame = new JFrame();
    Container contentPane=frame.getContentPane();
    contentPane.setLayout(new BorderLayout());
    contentPane.add(time);
//    time.setBounds(0,0,70,20);
//    frame.setBounds(100,100,70,20);
    frame.pack();
    frame.setVisible(true);
  }
}

