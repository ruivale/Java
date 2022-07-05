
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package exp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;


public class ImagesBenchMark extends JFrame {

  int WINDOW_WIDTH = 0;
  int WINDOW_HEIGHT = 0;

  JFileChooser jFileChooser = new JFileChooser("c:/JBProjects/images/");

  JRadioButton jRadioButton_Image = new JRadioButton("Image");
  JRadioButton jRadioButton_ImageIcon = new JRadioButton("ImageIcon");
  JRadioButton jRadioButton_Panel = new JRadioButton("Panel");
  ButtonGroup buttonGroup = new ButtonGroup();

  JButton jButton = new JButton("Escolher ficheiro");

  JPanel jPanel1 = new JPanel();
  Border border1;
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  FlowLayout flowLayout2 = new FlowLayout();

  JPanel contentPane = null;
  Border border2;


  public ImagesBenchMark() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
      pack();
      setLocationOnScreen();
      setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // MAIN
  public static void main(String[] args) {
    ImagesBenchMark imagesBenchMark = new ImagesBenchMark();
  }


  // Component initialization
  private void jbInit() throws Exception {

    contentPane = (JPanel) this.getContentPane();
    border2 = BorderFactory.createEmptyBorder(5, 10, 5, 10);
    contentPane.setLayout(borderLayout1);

    border1 = BorderFactory.createCompoundBorder(
                BorderFactory.createBevelBorder(
                  BevelBorder.RAISED,
                  Color.white,
                  Color.white,
                  new Color(142, 142, 142),
                  new Color(99, 99, 99)),
                BorderFactory.createEmptyBorder(10, 50, 10, 50));

    jButton.addActionListener(new ImagesBenchMark_jButton_actionAdapter(this));
    jPanel1.setBorder(border1);
    jPanel1.setLayout(flowLayout2);
    jButton.setHorizontalTextPosition(SwingConstants.RIGHT);
    jPanel2.setLayout(flowLayout1);
    flowLayout1.setAlignment(FlowLayout.RIGHT);
    flowLayout2.setAlignment(FlowLayout.LEFT);
    contentPane.setBorder(border2);
    buttonGroup.add(jRadioButton_Image);
    buttonGroup.add(jRadioButton_ImageIcon);
    buttonGroup.add(jRadioButton_Panel);
    jRadioButton_Image.setSelected(true);

    contentPane.add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jButton, null);
    contentPane.add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jRadioButton_ImageIcon, null);
    jPanel1.add(jRadioButton_Image, null);
    jPanel1.add(jRadioButton_Panel, null);


  }

  protected void setLocationOnScreen() {
    // Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    WINDOW_WIDTH = getWidth();
    WINDOW_HEIGHT = getHeight();

    setLocation((screenSize.width - WINDOW_WIDTH) / 2, (screenSize.height - WINDOW_HEIGHT) / 2);
  }



  // Gets a file name and creates an ImageIcon class
  protected void loadImageIcon(String filename) {
    long now, after;
    now = System.currentTimeMillis();

    ImageIcon image = new ImageIcon(filename);

    after = System.currentTimeMillis();
    System.out.println("After loadImageIcon: " + (after - now));
  }

  // Gets a file name and creates an Image class
  protected void loadImage(String filename) {
    long now, after;
    now = System.currentTimeMillis();

    Image image = Toolkit.getDefaultToolkit().getImage(filename);
    //		Image image = new ImageIcon("images/Caixa_back2.gif")).getImage();

    after = System.currentTimeMillis();
    System.out.println("After loadImage: " + (after - now));
  }

  // Gets a file name and creates an ImageIcon class
  protected void loadPanel(String filename) {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    long now, after;
    now = System.currentTimeMillis();

    MyJPanel myJPanel = new MyJPanel(filename, WINDOW_WIDTH, WINDOW_HEIGHT);

    after = System.currentTimeMillis();
    System.out.println("After panel: " + (after - now));

    JFrame jFrame = new JFrame();
    jFrame.setContentPane(myJPanel);
    jFrame.setSize(screenSize.width, screenSize.height);
    jFrame.setVisible(true);
  }



  // Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

  void jButton_actionPerformed(ActionEvent e) {

    jFileChooser.showOpenDialog(this);

    StringBuffer filename = new StringBuffer("" + jFileChooser.getSelectedFile());

    for (int i = 0; i < filename.length(); i++)
      if (filename.charAt(i) == '\\')
        filename.setCharAt(i, '/');

    if (!filename.equals("")) {
      if (jRadioButton_Image.isSelected())
        loadImage(filename.toString());
      if (jRadioButton_ImageIcon.isSelected())
        loadImageIcon(filename.toString());
      if (jRadioButton_Panel.isSelected())
        loadPanel(filename.toString());
    }
  }


}

class ImagesBenchMark_jButton_actionAdapter implements java.awt.event.ActionListener {
  ImagesBenchMark adaptee;

  ImagesBenchMark_jButton_actionAdapter(ImagesBenchMark adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton_actionPerformed(e);
  }
}



/**
 * My own JPanel
 */
class MyJPanel extends JPanel {

  ImageIcon image = null;

  int WINDOW_WIDTH = 0;
  int WINDOW_HEIGHT = 0;

  // Constructor
  public MyJPanel(String filename, int WINDOW_WIDTH, int WINDOW_HEIGHT) {

    this.WINDOW_WIDTH = WINDOW_WIDTH;
    this.WINDOW_HEIGHT = WINDOW_HEIGHT;

    image = new ImageIcon(filename);

    setLayout(new BorderLayout());

  }

  public void paintComponent(Graphics g) {
    Dimension size = getSize();
    for (int row = 0; row < WINDOW_WIDTH; row += WINDOW_WIDTH)
      for (int col = 0; col < WINDOW_HEIGHT; col += WINDOW_HEIGHT)
        image.paintIcon(this, g, col, row);
  }
}
