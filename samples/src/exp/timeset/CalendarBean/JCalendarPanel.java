/*
 * @(#)JCalendarDemo.java
 *
 * Copyright 1998 Kai Toedter
 */

package exp.timeset.CalendarBean;

import java.beans.*;
import java.util.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.applet.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.*;


public class JCalendarPanel extends JPanel implements ActionListener //PropertyChangeListener
{
  private JPanel         calendarPanel;
  private JPanel         demoPanel;
  private JCalendar      jcalendar;
  public JTextField     dateField;
  private JLocaleChooser localeChooser;
  public Calendar       calendar;
  public pmenu popup;
  protected BasicArrowButton calendarButton;

  //Font font = new Font(null, Font.PLAIN, 11);
  boolean selection=false;
  boolean doDisplay=false;
  JCalendarPanel me;
  boolean initPop = false;



  public JCalendarPanel() {

    // This panel layout
    this.setLayout( new GridBagLayout() );

    calendarPanel = new JPanel();
    calendarPanel.setLayout( new BorderLayout());

    // The panel holding the data textField
    JPanel controlPanel = new JPanel();
    controlPanel.setLayout( new BorderLayout() );
    JLocaleChooser localeChooser = new JLocaleChooser();

    dateField = new JTextField(11);
    dateField.setEditable( false );
    //dateField.setFont(font);
    // -----------------------------------------------------
    // Adding the dateField textField
    // -----------------------------------------------------
    controlPanel.add( dateField, BorderLayout.NORTH);

    jcalendar = new JCalendar();
    jcalendar.addPropertyChangeListener( new PropertyChangeListener(){
      public void propertyChange( PropertyChangeEvent evt ){
        if( calendarPanel != null ){
          if( evt.getPropertyName().equals( "locale" ) )
            {
              jcalendar.setLocale( (Locale) evt.getNewValue() );
              DateFormat df = DateFormat.getDateInstance( DateFormat.MEDIUM,
                                                          jcalendar.getLocale());
              dateField.setText( df.format( calendar.getTime() ) );
            }
            else if( evt.getPropertyName().equals( "calendar" ) ) {
              calendar = (Calendar) evt.getNewValue();
              DateFormat df = DateFormat.getDateInstance( DateFormat.MEDIUM);
              dateField.setText( df.format( calendar.getTime() ) );
            }
        }
      }
    });

    calendar = Calendar.getInstance();
    jcalendar.setCalendar( calendar );

    // -----------------------------------------------------
    // Adding controlPanel and calendarButton
    // -----------------------------------------------------
    add(controlPanel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
            new Insets(0, 0, 0, 0), 0, 0));
    calendarButton=new BasicArrowButton(BasicArrowButton.SOUTH);
    add(calendarButton , new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

    calendarButton.addActionListener(this);

    //initPopup();
    me=this;
  }


  public void setCalendar(Date date){

    Calendar cal=Calendar.getInstance();
    cal.setTime(date);
    jcalendar.setCalendar(cal);
  }


  public void setEnabled(boolean enable){
      //super.setEnabled(enable);
      calendarButton.setEnabled(enable);
      dateField.setEditable(enable);
      //calendarPanel.setEnabled(enable);
      if(demoPanel!=null)
        demoPanel.setEnabled(enable);
  }


  public void initPopup(){
    demoPanel = new JPanel();
    demoPanel.setBorder( new CompoundBorder( new TitledBorder( "Data" ),
    new EmptyBorder( 10, 10, 10, 10 ) ) );
    demoPanel.setLayout( new BorderLayout() );
    demoPanel.add( jcalendar, BorderLayout.CENTER );
    popup=new pmenu(this);
    popup.add(demoPanel);
    //popup.setInvoker(this);
    popup.setInvoker(calendarButton);
    popup.show(this, 0, getHeight());
  }


  public void closePopup(){
    if(popup==null)return;

    popup.close();
  }


  public void actionPerformed(ActionEvent e) {

    if(!this.isEnabled()) return;

    if(popup==null){
      //System.out.println("(1) mostrar");
      initPop=true;
      selection=true;
      initPopup();
      return;
    }

    if(popup.status){
      //System.out.println("(2) mostrar");
      popup.show(this, 0, getHeight());
      popup.status=false;
      doDisplay=false;
      return;
    }

    if(doDisplay){
      //System.out.println("(3) mostrar");
      popup.show(this, 0, getHeight());
    } else {
        popup.supermenuSelectionChanged(false);
      }

    doDisplay=!doDisplay;
  }


  public void closepopup() {
    popup.supermenuSelectionChanged(false);
  }

  public String getDate(){
    return dateField.getText();
  }



  public static void main(String args[]){

      JFrame frame = new JFrame( "JCalendar" );
      frame.getContentPane().add( new JCalendarPanel() );
      frame.pack();
      frame.setVisible( true );

  }



}


class pmenu extends JPopupMenu {

  JCalendarPanel calendar;
  boolean status = false;


  public pmenu(JCalendarPanel calendar){
    super();
    this.calendar=calendar;

  }

  public void close(){
    status=true;
    super.menuSelectionChanged(false);
  }

  public void open(){
    status=false;
    super.menuSelectionChanged(true);
  }

  public void menuSelectionChanged(boolean event){
  }

  public void supermenuSelectionChanged(boolean event){
    //System.out.println("closing");
    super.menuSelectionChanged(event);
  }

}


