
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package exp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Classe que representa ...
 * 
 * @author Rui Vale
 */
public class expFrame extends JFrame {
  /**    */
  JPanel contentPane;
  /**    */
  JMenuBar menuBar1 = new JMenuBar();
  /**    */
  JMenu menuFile = new JMenu();
  /**    */
  JMenuItem menuFileExit = new JMenuItem();
  /**    */
  JMenu menuHelp = new JMenu();
  /**    */
  JMenuItem menuHelpAbout = new JMenuItem();
  /**    */
  JToolBar toolBar = new JToolBar();
  /**    */
  JButton jButton1 = new JButton();
  /**    */
  JButton jButton2 = new JButton();
  /**    */
  JButton jButton3 = new JButton();
  /**    */
  ImageIcon image1;
  /**    */
  ImageIcon image2;
  /**    */
  ImageIcon image3;
  /**    */
  JLabel statusBar = new JLabel();
  /**    */
  BorderLayout borderLayout1 = new BorderLayout();
  /**    */
  JScrollBar jScrollBar1 = new JScrollBar();

  //Construct the frame
  /**    */
  public expFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  /**    */
  private void jbInit() throws Exception  {
    image1 = new ImageIcon(expFrame.class.getResource("openFile.gif"));
    image2 = new ImageIcon(expFrame.class.getResource("closeFile.gif"));
    image3 = new ImageIcon(expFrame.class.getResource("help.gif"));
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    this.setSize(new Dimension(400, 300));
    this.setTitle("Frame Title");
    statusBar.setText(" ");
    menuFile.setText("File");
    menuFileExit.setText("Exit");
    menuFileExit.addActionListener(new ActionListener()  {

      /**        */
      public void actionPerformed(ActionEvent e) {
        fileExit_actionPerformed(e);
      }
    });
    menuHelp.setText("Help");
    menuHelpAbout.setText("About");
    menuHelpAbout.addActionListener(new ActionListener()  {

      /**        */
      public void actionPerformed(ActionEvent e) {
        helpAbout_actionPerformed(e);
      }
    });
    jButton1.setIcon(image1);
    jButton1.setToolTipText("Open File");
    jButton2.setIcon(image2);
    jButton2.setToolTipText("Close File");
    jButton3.setIcon(image3);
    jButton3.setToolTipText("Help");
    jScrollBar1.setVisibleAmount(99);
    jScrollBar1.setAutoscrolls(true);
    toolBar.add(jButton1);
    toolBar.add(jButton2);
    toolBar.add(jButton3);
    menuFile.add(menuFileExit);
    menuHelp.add(menuHelpAbout);
    menuBar1.add(menuFile);
    menuBar1.add(menuHelp);
    this.setJMenuBar(menuBar1);
    contentPane.add(toolBar, BorderLayout.NORTH);
    contentPane.add(statusBar, BorderLayout.SOUTH);
    contentPane.add(jScrollBar1, BorderLayout.CENTER);
  }

  //File | Exit action performed
  /**    */
  public void fileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }

  //Help | About action performed
  /**    */
  public void helpAbout_actionPerformed(ActionEvent e) {
    expFrame_AboutBox dlg = new expFrame_AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.show();
  }

  //Overridden so we can exit when window is closed
  /**    */
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      fileExit_actionPerformed(null);
    }
  }
}
