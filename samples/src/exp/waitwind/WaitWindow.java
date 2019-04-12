package exp.waitwind;


import javax.swing.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;



/**
 * Class used to inform the user that something is ocurring.
 */
public class WaitWindow extends JDialog/* implements Runnable*/{

  private static JLabel label = new JLabel("Sair");
  private static JLabel label2 = new JLabel();
  private static JLabel label3 = new JLabel();
  private static JLabel l_image = new JLabel();
  private static ImageIcon i1 = new ImageIcon("/JBProjects/PIntV1_0/images/PInt/wait_image1.gif");
  private static ImageIcon i2 = new ImageIcon("/JBProjects/PIntV1_0/images/PInt/wait_image2.gif");
  private static ImageIcon i3 = new ImageIcon("/JBProjects/PIntV1_0/images/PInt/wait_image3.gif");
  private static ImageIcon i4 = new ImageIcon("/JBProjects/PIntV1_0/images/PInt/wait_image4.gif");
  private static ImageIcon i5 = new ImageIcon("/JBProjects/PIntV1_0/images/PInt/wait_image5.gif");
  private static ImageIcon i6 = new ImageIcon("/JBProjects/PIntV1_0/images/PInt/wait_image6.gif");
  private static ImageIcon i7 = new ImageIcon("/JBProjects/PIntV1_0/images/PInt/wait_image7.gif");


  private static int currentImg = 1;

  private static int timerInterval = 500;

  private static final String defaultMessage = "Processing ...";

  private static javax.swing.Timer timer = null;
  private static boolean forward = true;
  private static boolean stoped = false;

  private static Container container = null;

  // This window's owner.
  private static JFrame parent = null;

  public static WaitWindow _this = null;



  /**
   * This constructor receives as an argument the frame that will act as parent.
   */
  protected WaitWindow(JFrame parent) {

    super(parent, true);

    initWindow();

    setParent(parent);

    setSize(350, 185);

//    _this.setModal(true);
    _this.setResizable(false);

    container = getContentPane();
    container.setLayout(new BorderLayout());

    label.setForeground(Color.black);
    label2.setForeground(Color.black);
    label3.setForeground(Color.black);
    GridLayout gridLayout = new GridLayout();
    gridLayout.setColumns(1);
    gridLayout.setRows(3);
    JPanel labelsPanel = new JPanel();
    labelsPanel.setLayout(gridLayout);
    Border border = BorderFactory.createEmptyBorder(20, 20, 10, 20);
    labelsPanel.setBorder(border);

    labelsPanel.add(label);
    labelsPanel.add(label2);
    labelsPanel.add(label3);

    label2.setVisible(false);
    label3.setVisible(false);

    border = BorderFactory.createEmptyBorder(2, 10, 20, 10);
    l_image.setBorder(border);
    l_image.setPreferredSize(new Dimension(300,75));
    l_image.setHorizontalAlignment(JLabel.CENTER);


    container.add(labelsPanel, BorderLayout.CENTER);
    container.add(l_image, BorderLayout.SOUTH);

    setWinOnScreen();

    timer = new javax.swing.Timer(timerInterval, new ImageSlideShow());

    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//    addWindowListener(new WindowHandler());

  }
  /**
   * Method that creates a "var link" to this classe, WaitWindow.
   */
  private void initWindow(){
    if(_this == null){
      _this = this;
    }
  }
  /**
   * Starts the visualization. The direction is used to show the sequence of
   * images. To set the direction see the setDireccion(boolean forward) method.
   *
   * This method uses the default
   */
  public static void startWindow() {
    setDireccion(true);
    label.setText(defaultMessage);
    timer.setDelay(timerInterval);
    timer.start();
    _this.pack();
    _this.setVisible(true);
  }
  /**
   * Starts the visualization. The direction is used to show the sequence of
   * images. To set the direction see the setDireccion(boolean forward) method.
   */
  public static void startWindow(String messageToShow) {
    label.setText(messageToShow);
    timer.start();
    _this.pack();
    _this.setVisible(true);
  }
  /**
   * Starts the visualization. The direction is used to show the sequence of
   * images. To set the direction see the setDireccion(boolean forward) method.
   */
  public static void startWindow(String messageToShow1, String messageToShow2) {
    label.setText(messageToShow1);
    label2.setText(messageToShow2);
    label2.setVisible(true);
    timer.start();
    _this.pack();;
    _this.setVisible(true);
  }
  /**
   * Starts the visualization. The direction is used to show the sequence of
   * images. To set the direction see the setDireccion(boolean forward) method.
   */
  public static void startWindow(String messageToShow1, String messageToShow2, String messageToShow3) {
    label.setText(messageToShow1);
    label2.setText(messageToShow2);
    label3.setText(messageToShow3);
    label2.setVisible(true);
    label3.setVisible(true);
    timer.start();
    _this.pack();
    _this.setVisible(true);
  }

  /**
   * Stops the timer, but don't set the visibility to false.
   */
  public static void stopTimer() {
    timer.stop();
  }
  /**
   * Stops the timer, but don't set the visibility to false.
   */
  public static void startTimer() {
    timer.start();
  }
  /**
   * This method is used when the intention is to change the label's text during
   * the WaitWindow visualization. To change the text before the visualization,
   * the start(..) method should be used.
   */
  public void changeText(String label1Text) {
    label.setText(label1Text);
  }
  /**
   * This method is used when the intention is to change the label's text during
   * the WaitWindow visualization. To change the text before the visualization,
   * the start(..) method should be used.
   */
  public void changeText(String label1Text, String label2Text) {
    label.setText(label1Text);
    label2.setText(label2Text);
  }
  /**
   * This method is used when the intention is to change the label's text during
   * the WaitWindow visualization. To change the text before the visualization,
   * the start(..) method should be used.
   */
  public void changeText(String label1Text, String label2Text, String label3Text) {
    label.setText(label1Text);
    label2.setText(label2Text);
    label3.setText(label3Text);
  }
  /**
   * Sets the direction in which the images will be displayed.
   *    TRUE  - means the direction is from our point to somewhere;
   *    FALSE - means the direction is from somewhere to us.
   */
  public static void setDireccion(boolean direction) {
    forward = direction;
    if(forward){
      currentImg = 1;
    }else{
      currentImg = 7;
    }
  }
  /**
   * Sets the interval between images in the images slide show.
   */
  public static void setDelay(int interval){
    timer.setDelay(interval);
  }
  /**
   * Set the visibility to false, and set the two extra label visibility to
   * false because the default behaviour is to show just one label.
   */
  public static void finishWindow() {


System.out.println("finish()finish()finish()finish()");


//    if(_this.isVisible()){
      _this.setVisible(false);
      label2.setVisible(false);
      label3.setVisible(false);
      timer.stop();

System.out.println("timer.stop();timer.stop();timer.stop();timer.stop();");

    //}
  }
  /**
   * Sets the parent window of this JDialog.
   */
  private static void setParent(JFrame _pint) {
    if (parent == null) {
      parent = _pint;
    }
  }
  /**
   * Return this window owner.
   */
  public static JFrame getParentWindow() {
    return parent;
  }
  /**
   * Sets this window in the middle of the screen.
   */
  private static void setWinOnScreen(){
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    screenSize.height = screenSize.height / 2;
    screenSize.width = screenSize.width / 2;
    Dimension size = _this.getSize();
    size.height = size.height / 2;
    size.width = size.width / 2;
    int y = screenSize.height - size.height;
    int x = screenSize.width - size.width;
    _this.setLocation(x, y);
  }



  /**
   *
   *
  public void run(){
    if (Thread.currentThread().getName().equals("this_to_front")) {
      while(!_this.isVisible()){
        try{Thread.sleep(250);}catch(Exception e){}

        _this.toFront();
        _this.requestFocus();
        _this.transferFocus();


      }
    }
  }


  /**
   * Class that handles the images movimentation.
   */
  class ImageSlideShow implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      if (forward) {
        switch (currentImg) {
          case 1:
            l_image.setIcon(i1);
            currentImg++;
            break;
          case 2:
            l_image.setIcon(i2);
            currentImg++;
            break;
          case 3:
            l_image.setIcon(i3);
            currentImg++;
            break;
          case 4:
            l_image.setIcon(i4);
            currentImg++;
            break;
          case 5:
            l_image.setIcon(i5);
            currentImg++;
            break;
          case 6:
            l_image.setIcon(i6);
            currentImg++;
            break;
          case 7:
            l_image.setIcon(i7);
            currentImg = 1;
            break;
        }
      } else {
        switch (currentImg) {
          case 1:
            l_image.setIcon(i1);
            currentImg = 7;
            break;
          case 2:
            l_image.setIcon(i2);
            currentImg--;
            break;
          case 3:
            l_image.setIcon(i3);
            currentImg--;
            break;
          case 4:
            l_image.setIcon(i4);
            currentImg--;
            break;
          case 5:
            l_image.setIcon(i5);
            currentImg--;
            break;
          case 6:
            l_image.setIcon(i6);
            currentImg--;
            break;
          case 7:
            l_image.setIcon(i7);
            currentImg--;
            break;
        }
      }
    }
  }
/*
  public class WindowHandler extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
      JOptionPane.showMessageDialog(null, "A configuração ainda não terminou!");
    }
  }
*/



  //////////////////////////////////////////////////////////////
  public static void main(String[] args) {
//    WaitWindow box = new WaitWindow();
  }
  ////////////////////////////////////////////////////////////////


}



