package exp.timeset.Scrolls;

import javax.swing.text.*;
import java.util.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:
 * @author
 * @version 1.0
 */


public class DateDocument extends PlainDocument {

 // public String initString = "XX:XX:XX"; // Y10K!
  public String initString = "XX:XX";// Y10K!
  private String temp;
  private static int sep1 = 2, sep2 = 5;
  private JTextComponent textComponent;
  private int newOffset;
  public int insertFlag=2;
  String S_hour;
  String S_minute;
  String S_second;
  public Integer hour;
  public Integer minute;
  public Integer second;
  int flag=0;

  public DateDocument(JTextComponent tc) {

    textComponent = tc;
    try {
      hour=new Integer((Calendar.getInstance()).get(Calendar.HOUR_OF_DAY));
      minute=new Integer((Calendar.getInstance()).get(Calendar.MINUTE));
      //second=new Integer((Calendar.getInstance()).get(Calendar.SECOND));
      S_hour="";
      S_minute="";
      S_second="";
      if(hour.intValue()<10)S_hour="0";
        S_hour=S_hour+hour.toString()+":";
        if(minute.intValue()<10)S_minute="0";
          S_minute=S_minute+minute.toString();//+":";
          /*if(second.intValue()<10)S_second="0";
            S_second=S_second+second.toString();*/
            initString=S_hour+S_minute;//+S_second;
            insertString(0, initString, null);

    } catch(Exception ex) {
        ex.printStackTrace();
      }
  }


  public void setTime(String time){
    try{
      initString=time;
      insertString2(0,time);
    }catch(Exception e){




    }
  }


  /*
   * This method is to initialize the timeField and for manual insertion
   * (not using the scrollbar)
   */
  public void insertString(int offset, String s, AttributeSet attributeSet)
  throws BadLocationException {

    if(flag==1) {
      flag=0;
      return;
    }
    if(insertFlag==0) return;
    else insertFlag--;
    if(s.equals(initString)) {
      super.insertString(offset, s, attributeSet);
    } else {
        try {
          Integer.parseInt(s);
        } catch(Exception ex) {
            return;  // only allow integer values
          }

        newOffset = offset;

        if(atSeparator(offset)) {
          newOffset-=1;
          textComponent.setCaretPosition(newOffset);
        }
        String backup;
        backup=initString;
        if(offset==0)
          backup=s+backup.substring(1,backup.length());
        else {
          if(offset==initString.length())
            backup=backup.substring(0,backup.length()-1)+s;
          else
            backup=backup.substring(0,offset)+s+backup.substring(offset+1,backup.length());
        }
        temp=initString;
        initString=backup;
        super.remove(newOffset, 1);
        super.insertString(newOffset, s, attributeSet);
        if(insertFlag==0){
          if(checkTime(initString)==false){
            if(offset<2){
              initString=S_hour+initString.substring(3,initString.length());

            } else
                if(offset<5){
                  initString=initString.substring(0,3)+S_minute;//+initString.substring(6,initString.length());

                }/*else {
                  initString=initString.substring(0,6)+S_second;
                  System.out.println("S_second" + S_second);
                }*/
            super.remove(0,initString.length());
            super.insertString(0,initString, attributeSet);
          }
        }

    }
  }

  public void insertString2(int offset, String s){
    try {
      super.remove(0,5);//8);
      super.insertString(offset,s,null);
    } catch(Exception e){}
      flag=1;
  }

  public void remove(int offset, int length)
  throws BadLocationException {
    if(atSeparator(offset))
      textComponent.setCaretPosition(offset+1);
    else
      textComponent.setCaretPosition(offset);
  }

  private boolean atSeparator(int offset) {
                return offset == sep1 || offset == sep2;
  }

  // Checks if the inserted time is valid
  boolean checkTime(String initString) {

    String text=initString;//+":";
    //System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO="+text);
    StringTokenizer st = new StringTokenizer(text,":");
    /*if(st.countTokens()!=3)
      return false;*/
    Integer hour = new Integer(st.nextToken());
    String tt = st.nextToken();
    //System.out.println("KKKKKKKKKKKKKKKK="+tt);
    Integer minute = new Integer(tt);//st.nextToken());
    //Integer second = new Integer(st.nextToken());
    if(hour.intValue()<0 || hour.intValue()>=24)      return false;
    if(minute.intValue()<0 || minute.intValue()>=60)  return false;
    //if(second.intValue()<0 || second.intValue()>=60)  return false;

    // Time values updated in this class!!!!!!!!
    this.hour = hour;
    this.minute = minute;
    //this.second = second;

    return true;

  }

  public String getTime(){

    try{
      return getText(0,6);//8);
    }catch(Exception e){}

    return "";
  }
}
