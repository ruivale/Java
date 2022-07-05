package exp.timeset;

import javax.swing.*;
import java.awt.*;

import java.util.Vector;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class A extends JPanel {
  public A() {
  }
  public static void main(String[] args) {
    for (int i = 0; i < 5; i++) {
      if(i==3){
        break;
      }
      System.out.println("i="+i);
    }
  }
}