package exp.robot;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;


public class RobotDemo
    extends JFrame implements ActionListener {
  //Store Keystrokes in an array
  static int keyInput[] = {KeyEvent.VK_R, KeyEvent.VK_O, KeyEvent.VK_B,
                          KeyEvent.VK_O, KeyEvent.VK_T};

  static JTextArea ta = new JTextArea();

  static JButton bold = new JButton("Bold");

  public RobotDemo() {
    getContentPane().add(ta, BorderLayout.CENTER);

    JPanel p = new JPanel();
    bold.addActionListener(this);
    p.add(bold);
    getContentPane().add(p, BorderLayout.SOUTH);
  }

  public static void main(String[] args) throws AWTException, IOException {
    RobotDemo rd = new RobotDemo();

    rd.setLocation(100, 100);
    rd.setTitle("Robot Demo");
    rd.setSize(200, 200);
    rd.show();

    Robot robot = new Robot();

    //This types the word 'robot' in the Textarea
    for (int i = 0; i < keyInput.length; i++) {
      if (i > 0) {
        robot.keyRelease(KeyEvent.VK_SHIFT);
      }

      robot.keyPress(keyInput[i]);

      robot.delay(500);

    }

    //The following clicks the button 'Bold' to get the text bolder
    robot.mouseMove(180, 280);

    robot.delay(2000);

    robot.mousePress(InputEvent.BUTTON1_MASK);

    //This delay keeps the button pressed for 2 seconds
    robot.delay(2000);

    robot.mouseRelease(InputEvent.BUTTON1_MASK);
    
    

    
      
//    final int nSleep = 5555;
//      
//    try {
//      final Dimension dimScreen = Toolkit. getDefaultToolkit(). getScreenSize();
//      Point pointOnScreen;
//      
//      while (true) {
//        pointOnScreen = MouseInfo.getPointerInfo().getLocation();
//        pointOnScreen.setLocation(
//            pointOnScreen.x > 0 ? pointOnScreen.x - 1 : 1, 
//            pointOnScreen.y > 0 ? pointOnScreen.y - 1 : 1);
//        robot.mouseMove(pointOnScreen.x, pointOnScreen.y);
//        System.out.println(pointOnScreen);        
//        Thread.sleep(nSleep);
//        
//        pointOnScreen = MouseInfo.getPointerInfo().getLocation();
//        pointOnScreen.setLocation(
//            pointOnScreen.x < dimScreen.width - 1 ? pointOnScreen.x + 1 : dimScreen.width - 2, 
//            pointOnScreen.y < dimScreen.height - 1 ? pointOnScreen.y + 1 : dimScreen.height - 2);
//        robot.mouseMove(pointOnScreen.x, pointOnScreen.y);       
//        System.out.println(pointOnScreen);
//        Thread.sleep(nSleep);
//        
//      }
//    } catch (HeadlessException headlessException) {
//    } catch (InterruptedException interruptedException) {
//    }
  }

  public void actionPerformed(ActionEvent ae) {
    Font f = new Font("Times New Roman", Font.BOLD, 20);
    ta.setFont(f);
  }
}
