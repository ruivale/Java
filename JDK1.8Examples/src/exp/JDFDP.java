package exp;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

//  JDFDP.java - Display and Parse java.sql.Date

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

public class JDFDP extends JFrame
      implements ActionListener,
      WindowListener
{
  // create a java.sql.Date
  java.sql.Date jsqlDate = new java.sql.Date(
                             System.currentTimeMillis() );

  DateFormat dfLocal = DateFormat.getDateInstance(
                         DateFormat.SHORT );
  DateFormat dfGermany = DateFormat.getDateInstance(
                           DateFormat.SHORT, Locale.GERMANY );

  JButton    jb = new JButton( "Go" );
  JLabel     jlI = new JLabel("Input a Date:"),
                   jlD = new JLabel("Display German:"),
                         jlP = new JLabel("Parsed:");

  JPanel     jp = new JPanel();

  JTextField jtI = new JTextField( 10 ),
                   jtD = new JTextField( 10 ),
                         jtP = new JTextField( 10 );


  public JDFDP()
  {
    super( "JDFDP" );
    addWindowListener( this );

    jb.addActionListener( this );

    jp.add(jlI);
    jp.add(jtI);
    jp.add(jb);
    jp.add(jlD);
    jp.add(jtD);
    jp.add(jlP);
    jp.add(jtP);

    getContentPane().add( jp, BorderLayout.CENTER );
    pack();

    // set text by sending dummy event
    jtI.setText( dfLocal.format( jsqlDate ) );
    actionPerformed(
      new ActionEvent( this, 12, "12" ) );

    show();

  }  // end constructor


  // ActionListener Implementation
  public void actionPerformed(ActionEvent e)
  {
    jtD.setText( "" );
    jtP.setText( "" );
    try
    {
      java.util.Date d = dfLocal.parse(
                           jtI.getText() );
      jtI.setText( dfLocal.format( d ) );
      jtD.setText( dfGermany.format( d ) );
      d = dfGermany.parse( jtD.getText() );
      // get new java.sql.Date
      jsqlDate = new java.sql.Date( d.getTime() );

      jtP.setText( jsqlDate.toString() );
    }
    catch( ParseException pe ) { jtI.setText( "" ); }

  }  // End actionPerformed


  // Window Listener Implementation
  public void windowOpened(WindowEvent e) {}
  public void windowClosing(WindowEvent e)
  {
    dispose();
    System.exit(0);
  }
  public void windowClosed(WindowEvent e) {}
  public void windowIconified(WindowEvent e) {}
  public void windowDeiconified(WindowEvent e) {}
  public void windowActivated(WindowEvent e) {}
  public void windowDeactivated(WindowEvent e) {}
  // End Window Listener Implementation


  public static void main(String[] args)
  {
    new JDFDP();
  }

}
