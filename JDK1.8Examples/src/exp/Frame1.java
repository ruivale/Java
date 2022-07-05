package exp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame1 extends JFrame {
  JPanel contentPane = null;

	int WINDOW_WIDTH = 0;
	int WINDOW_HEIGHT = 0;

	MyJPanel myJPAnel = null;

  //Construct the frame
  public Frame1() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      setLocationOnScreen();
      myJPAnel = new MyJPanel(WINDOW_WIDTH, WINDOW_HEIGHT);
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }

		setVisible(true);

System.out.println("A FREE MEM: "+Runtime.getRuntime().freeMemory()+".");
System.err.println("A USED MEM: "+Runtime.getRuntime().totalMemory()+".");

  }

  // Component initialization
  private void jbInit() throws Exception  {
//    contentPane = (JPanel) this.getContentPane();
  //  contentPane.setLayout(null);

  	setContentPane(myJPAnel);

		JButton b = new JButton("askhdjkasdhajks");
  	b.setForeground(Color.yellow);
  	b.setOpaque(false);

   	getContentPane().add(b);

//		getGlassPane().setVisible(true);

//    contentPane.add(myJPAnel);

  }

	protected void setLocationOnScreen(){
		// Center the window
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   	setSize(screenSize.width, screenSize.height);

    WINDOW_WIDTH = screenSize.width;
    WINDOW_HEIGHT = screenSize.height;

// 		setLocation(screenSize.width/2 - WINDOW_WIDTH, screenSize.height/2 - WINDOW_HEIGHT);
	}

/*  // Overridden to paint our image
	public void paintComponent(Graphics g) {
		Dimension size = getSize();

		for(int row=0; row < WINDOW_WIDTH; row += WINDOW_WIDTH)
			for(int col=0; col < WINDOW_HEIGHT; col += WINDOW_HEIGHT)
				image.paintIcon(this,g,col,row);
	}
*/
  // Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

  public static void main(String args[]){new Frame1();}





	/**
  * My own JPanel
  */
	class MyJPanel extends JPanel {

		ImageIcon image = new ImageIcon("C:/JBProjects/images/Porto.jpg");

		int WINDOW_WIDTH = 0;
  	int WINDOW_HEIGHT = 0;

 		// Constructor
		public MyJPanel(int WINDOW_WIDTH, int WINDOW_HEIGHT){
    	this.WINDOW_WIDTH = WINDOW_WIDTH;
     	this.WINDOW_HEIGHT = WINDOW_HEIGHT;
      setLayout(new BorderLayout());
		}

		public void paintComponent(Graphics g) {
			Dimension size = getSize();
			for(int row=0; row < WINDOW_WIDTH; row += WINDOW_WIDTH)
				for(int col=0; col < WINDOW_HEIGHT; col += WINDOW_HEIGHT)
					image.paintIcon(this,g,col,row);
		}
	}

}