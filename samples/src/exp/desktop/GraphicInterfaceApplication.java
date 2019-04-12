
package exp.desktop;




import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;



/**
 *
 *
 */
public class GraphicInterfaceApplication extends JInternalFrame {


  private static final int OK = 1;
  private static final int ERROR = 1;

  // Panel which holds the modal layer.
  private JPanel modalLayerPanel = new JPanel();

  private JLayeredPane layeredPane = new JLayeredPane();
  // The two layers of this class.
  private Integer[] layers = {
    JLayeredPane.DEFAULT_LAYER,
    JLayeredPane.MODAL_LAYER,
  };


  /**
   * This class is intended to be used by all graphic interfaces.
   */
  public GraphicInterfaceApplication (){

    this.setMaximizable(false);
    this.setIconifiable(false);
    this.setResizable(false);


    this.layeredPane.setLayout(new BorderLayout());

    modalLayerPanel.addMouseListener(
      new MouseAdapter() {
        public void mousePressed(MouseEvent evt) {
          // Do nothing.
        }
      }
    );
    modalLayerPanel.setVisible(true);
    modalLayerPanel.setOpaque(false);
    modalLayerPanel.setLayout(new BorderLayout());


    // Setting the JLayeredPane to the content pane of this JInternalFrame.
    this.setContentPane(this.layeredPane);

    this.setVisible(true);

  }



  /**
   * This method sets the default layer visible. In other words, if you want to
   * display anything in this GI you must at least call this method with a panel
   * as a parameter.
   */
  public void setTheDefaultLayer(JPanel panel) {

    layeredPane.setLayer(panel, layers[0].intValue());
    layeredPane.add(panel, BorderLayout.CENTER);

  }



  /**
   * Method which sets the display of a modal internal frame. This internal
   * frame is modal to the GI internal frame and not to the entire PInt. To that
   * propose we have the FaultApplication window.
   */
  public int showGIModalWindow(JInternalFrame jif ) {
    try {

      // Adding and displaying the JInternalFrame
      jif.pack();
      // Calculating the size of this jif and displaying it in the respective
      // layer
      Dimension mainWinDim = this.getSize();
      jif.setLocation((mainWinDim.width - jif.getWidth()) / 2,
                      (mainWinDim.height - jif.getHeight()) / 2);
      jif.setVisible(true);

      TransparentDesktopPane tdp = new TransparentDesktopPane(false);
      tdp.add(jif );
      modalLayerPanel.add(tdp, BorderLayout.CENTER);
      modalLayerPanel.setVisible(true);
      layeredPane.setLayer(modalLayerPanel, layers[1].intValue());
      layeredPane.add(modalLayerPanel, BorderLayout.CENTER);
      jif.setSelected(true);

    } catch (Exception e) {

      return ERROR;
    }
    return OK;
  }




  /**
   * Method which sets the display of a modal internal frame. This internal
   * frame is modal to the GI internal frame and not to the entire PInt. To that
   * propose we have the FaultApplication window.
   */
  public int showGIModalWindows(JInternalFrame[] jifs) {
    try {
      Dimension mainWinDim = this.getSize();
      TransparentDesktopPane tdp = new TransparentDesktopPane(false);
      int numberOfJInternalFrames = jifs.length;
      int x=0, y=0;
      int xoffset=20, yoffset=20;


      for (int i = 0; i < numberOfJInternalFrames; i++ ) {
        // Adding and displaying the JInternalFrame
        jifs[i].pack();
        // Calculating the size of this jif and displaying it in the respective
        // layer

        jifs[i].setLocation(x,y);

        x+=xoffset;
        y+=yoffset;

        jifs[i].setVisible(true);

        tdp.add(jifs[i]);

      }


      if(numberOfJInternalFrames == 1){
        jifs[0].setLocation((mainWinDim.width - jifs[0].getWidth()) / 2,
                        (mainWinDim.height - jifs[0].getHeight()) / 2);
      }

      modalLayerPanel.add(tdp, BorderLayout.CENTER);
      modalLayerPanel.setVisible(true);
      layeredPane.setLayer(modalLayerPanel, layers[1].intValue());
      layeredPane.add(modalLayerPanel, BorderLayout.CENTER);
      jifs[0].setSelected(true);

    } catch (Exception e) {

      return ERROR;
    }
    return OK;
  }




  /**
   * Method which dismiss the modal panel in the layered pane. It's useful to
   * dispose the modal internal frame which must be visible otherwise nothing is
   * done.
   */
  public int dismissGIModalWindows() {
    try {
      // Ensuring that they exist.
      if (modalLayerPanel != null && layeredPane != null) {
        modalLayerPanel.setVisible(false);
        modalLayerPanel.removeAll();
        layeredPane.remove(modalLayerPanel);
      }
    } catch (Exception e) {
      return ERROR;
    }
    return OK;
  }




  //
  //
  //
  public static void main (String[] args) {

    new GraphicInterfaceApplication();

  }



}




