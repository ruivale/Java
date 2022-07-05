package exp.forcicle;

import javax.swing.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class CreatingVarsInsideFor {
  private static final int MAX_ITERACTIONS = 20000;
  long before;
  long after;

  public CreatingVarsInsideFor(final boolean isInside) {
    if(isInside){
      before = System.currentTimeMillis();
      for (int i = 0; i < MAX_ITERACTIONS; i++) {
        JPanel p = new JPanel();
        JButton b = new JButton();
      }
      after = (System.currentTimeMillis() - before);
      System.out.println("Inside the loop, it took " + after + " milliseconds.");

    }else{
      JPanel p1;
      JButton b1;
      before = System.currentTimeMillis();
      for (int i = 0; i < MAX_ITERACTIONS; i++) {
        p1 = new JPanel();
        b1 = new JButton();
      }
      after = (System.currentTimeMillis() - before);
      System.out.println("Outside the loop, it took " + after +
                         " milliseconds.");
    }

  }
  public static void main(String[] args) {
    CreatingVarsInsideFor creatingVarsInsideFor1 = new CreatingVarsInsideFor(false);
  }

}
