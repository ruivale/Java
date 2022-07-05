package exp.win32;

import java.awt.event.*;

import java.util.*;

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
public class HibernateWindows {

  private static final javax.swing.Timer hwTimer = new javax.swing.Timer(
      1000, new HibernateWindowsActionListener());

  public HibernateWindows(
      final int intHour,
      final int intMin,
      final int inSecs) {

    // Determine the actual date and the difference between the parameters one
    // becomes the Timer delay ...
    Calendar calNow = Calendar.getInstance();
    calNow.setTimeInMillis(System.currentTimeMillis());



  }

  public static void main(String[] args) {
    HibernateWindows hw = new HibernateWindows(14,00,00);
  }
}

class HibernateWindowsActionListener implements ActionListener{

  public void actionPerformed(final ActionEvent e){

  }
}
