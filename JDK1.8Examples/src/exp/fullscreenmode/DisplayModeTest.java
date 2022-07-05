package exp.fullscreenmode;


/**
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c)
 * </p>
 *
 * <p>
 * Company:
 * </p>
 */

/*
   test @(#)DisplayModeTest.java  1.4 01/07/17
   @bug 4189326
   @summary Tests changing display mode
   @author martak@eng: area=FullScreen
   @ignore This test enters full-screen mode, if available, and should not
   be run as an applet or as part of the test harness.
 */

/**
 * This test generates a table of all available display modes, enters
 * full-screen mode, if available, and allows you to change the display mode.
 * The application should look fine under each enumerationerated display mode. On
 * UNIX, only a single display mode should be available, and on Microsoft
 * Windows, display modes should depend on direct draw availability and the
 * type of graphics card.
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;


class DisplayModeModel extends DefaultTableModel {
  //~ Instance fields ----------------------------------------------------------

  private DisplayMode[] modes;

  //~ Constructors -------------------------------------------------------------

  /**
   * Creates a new DisplayModeModel object.
   *
   * @param modes DOCUMENT ME!
   */
  public DisplayModeModel (DisplayMode[] modes) {
    this.modes = modes;
  }

  //~ Methods ------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param r DOCUMENT ME!
   * @param c DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public boolean isCellEditable (
    int r,
    int c) {
    return false;
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public int getColumnCount () {
    return DisplayModeTest.COLUMN_WIDTHS.length;
  }

  /**
   * DOCUMENT ME!
   *
   * @param c DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public String getColumnName (int c) {
    return DisplayModeTest.COLUMN_NAMES[c];
  }

  /**
   * DOCUMENT ME!
   *
   * @param r DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public DisplayMode getDisplayMode (int r) {
    return modes[r];
  }

  /**
   * DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public int getRowCount () {
    if(modes == null) {
      return 0;
    }

    return modes.length;
  }

  /**
   * DOCUMENT ME!
   *
   * @param rowIndex DOCUMENT ME!
   * @param colIndex DOCUMENT ME!
   *
   * @return DOCUMENT ME!
   */
  public Object getValueAt (
    int rowIndex,
    int colIndex) {
    DisplayMode dm = modes[rowIndex];

    switch(colIndex) {
      case DisplayModeTest.INDEX_WIDTH:
        return Integer.toString(dm.getWidth());

      case DisplayModeTest.INDEX_HEIGHT:
        return Integer.toString(dm.getHeight());

      case DisplayModeTest.INDEX_BITDEPTH: {
        int    bitDepth = dm.getBitDepth();
        String ret;

        if(bitDepth == DisplayMode.BIT_DEPTH_MULTI) {
          ret = "Multi";
        } else {
          ret = Integer.toString(bitDepth);
        }

        return ret;
      }

      case DisplayModeTest.INDEX_REFRESHRATE: {
        int    refreshRate = dm.getRefreshRate();
        String ret;

        if(refreshRate == DisplayMode.REFRESH_RATE_UNKNOWN) {
          ret = "Unknown";
        } else {
          ret = Integer.toString(refreshRate);
        }

        return ret;
      }
    }

    throw new ArrayIndexOutOfBoundsException("Invalid column value");
  }
}


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision$
 */
public class DisplayModeTest extends JFrame implements ActionListener,
  ListSelectionListener {
  //~ Static fields/initializers -----------------------------------------------

  /** DOCUMENT ME! */
  public static final int   INDEX_WIDTH       = 0;

  /** DOCUMENT ME! */
  public static final int   INDEX_HEIGHT      = 1;

  /** DOCUMENT ME! */
  public static final int   INDEX_BITDEPTH    = 2;

  /** DOCUMENT ME! */
  public static final int   INDEX_REFRESHRATE = 3;

  /** DOCUMENT ME! */
  public static final int[] COLUMN_WIDTHS = new int[] { 100, 100, 100, 100 };

  /** DOCUMENT ME! */
  public static final String[] COLUMN_NAMES = new String[] {
      "Width", "Height", "Bit Depth", "Refresh Rate"
    };

  //~ Instance fields ----------------------------------------------------------

  private DisplayMode    originalDM;
  private GraphicsDevice device;
  private JButton     changeDM = new JButton("Set Display");
  private JButton     exit = new JButton("Exit");
  private JLabel      currentDM    = new JLabel();
  private JTable      dmList       = new JTable();
  private JScrollPane dmPane       = new JScrollPane(dmList);
  private boolean     isFullScreen = false;
  private boolean     waiting      = false;

  //~ Constructors -------------------------------------------------------------

  /**
   * Creates a new DisplayModeTest object.
   *
   * @param device DOCUMENT ME!
   */
  public DisplayModeTest (GraphicsDevice device) {
    super(device.getDefaultConfiguration());
    this.device = device;
    setTitle("Display Mode Test");
    originalDM = device.getDisplayMode();
    setDMLabel(originalDM);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // Make sure a DM is always selected in the list
    exit.addActionListener(this);
    changeDM.addActionListener(this);
    changeDM.setEnabled(device.isDisplayChangeSupported());
  }

  //~ Methods ------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param newMode DOCUMENT ME!
   */
  public void setDMLabel (DisplayMode newMode) {
    int    bitDepth    = newMode.getBitDepth();
    int    refreshRate = newMode.getRefreshRate();
    String bd;
    String rr;

    if(bitDepth == DisplayMode.BIT_DEPTH_MULTI) {
      bd = "Multi";
    } else {
      bd = Integer.toString(bitDepth);
    }

    if(refreshRate == DisplayMode.REFRESH_RATE_UNKNOWN) {
      rr = "Unknown";
    } else {
      rr = Integer.toString(refreshRate);
    }

    currentDM.setText(
      COLUMN_NAMES[INDEX_WIDTH] + ": " + newMode.getWidth() + " " +
      COLUMN_NAMES[INDEX_HEIGHT] + ": " + newMode.getHeight() + " " +
      COLUMN_NAMES[INDEX_BITDEPTH] + ": " + bd + " " +
      COLUMN_NAMES[INDEX_REFRESHRATE] + ": " + rr);
  }

  /**
   * DOCUMENT ME!
   *
   * @param isVis DOCUMENT ME!
   */
  public void setVisible (boolean isVis) {
    super.setVisible(isVis);

    if(isVis) {
      dmList.setModel(new DisplayModeModel(device.getDisplayModes()));
    }
  }

  /**
   * DOCUMENT ME!
   *
   * @param ev DOCUMENT ME!
   */
  public void actionPerformed (ActionEvent ev) {
    Object source = ev.getSource();

    if(source == exit) {
      device.setDisplayMode(originalDM);
      System.exit(0);
    } else { // if (source == changeDM)

      int index = dmList.getSelectionModel()
                        .getAnchorSelectionIndex();

      if(index >= 0) {
        DisplayModeModel model = (DisplayModeModel)dmList.getModel();
        DisplayMode      dm = model.getDisplayMode(index);
        device.setDisplayMode(dm);
        setDMLabel(dm);
        setSize(new Dimension(
            dm.getWidth(),
            dm.getHeight()));
        validate();
      }
    }
  }

  /**
   * DOCUMENT ME!
   */
  public void begin () {
    isFullScreen = device.isFullScreenSupported();
    setUndecorated(isFullScreen);
    setResizable(!isFullScreen);

    if(isFullScreen) {
      // Full-screen mode
      device.setFullScreenWindow(this);
      validate();
    } else {
      // Windowed mode
      pack();
      setVisible(true);
    }
  }

  /**
   * DOCUMENT ME!
   *
   * @param args DOCUMENT ME!
   */
  public static void main (String[] args) {
    final GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    final GraphicsDevice gd = env.getDefaultScreenDevice();

    System.out.println("\nGraphicsEnvironment.DefaultScreenDevice id: "+gd.getIDstring());

    final GraphicsDevice[] gds = env.getScreenDevices();

    // REMIND : Multi-monitor full-screen mode not yet supported
    for(int i = 0; i < gds.length  ; i++) {
      System.out.println();
      System.out.println("devices["+i+"].id: "+gds[i].getIDstring());
      System.out.println("devices["+i+"].type: "+gds[i].getType());
      System.out.println("devices["+i+"].defaultConfig.device.id: "+gds[i].getDefaultConfiguration().getDevice().getIDstring());
      System.out.println("devices["+i+"].getAvailableAcceleratedMemory(): "+gds[i].getAvailableAcceleratedMemory());

      final DisplayMode dmSel = gds[i].getDisplayMode();
      System.out.println(
          "devices["+i+"].displayMode"+
          " w:"+dmSel.getWidth()+
          " h:"+dmSel.getHeight()+
          " bitdepth:"+dmSel.getBitDepth()+
          " refreshrate:"+dmSel.getRefreshRate());

//      System.out.println("devices["+i+"].: "+gds[i]);
//      System.out.println("devices["+i+"].: "+gds[i]);
//      System.out.println("devices["+i+"].: "+gds[i]);


      int j = 0;
      for(GraphicsConfiguration gc: gds[i].getConfigurations()){
        System.out.println(
            "\tGraphicsConfiguration "+(j++)+
            " size:"+gc.getBounds()+
            " deviceId:"+gc.getDevice().getIDstring()
        );
      }
//      j = 0;
//      for(DisplayMode dm: gds[i].getDisplayModes()){
//        System.out.println(
//            "\tDisplayMode "+(j++)+
//            " w:"+dm.getWidth()+
//            " h:"+dm.getHeight()+
//            " bitdepth:"+dm.getBitDepth()+
//            " refreshrate:"+dm.getRefreshRate());
//      }


//      DisplayModeTest test = new DisplayModeTest(devices[i]);
//      test.initComponents(test.getContentPane());
//      test.begin();

      System.out.println("---------------------------------------------------------------------------------------------");
    }
  }

  /**
   * DOCUMENT ME!
   *
   * @param ev DOCUMENT ME!
   */
  public void valueChanged (ListSelectionEvent ev) {
    changeDM.setEnabled(device.isDisplayChangeSupported());
  }

  private void initComponents (Container c) {
    setContentPane(c);
    c.setLayout(new BorderLayout());

    // Current DM
    JPanel currentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    c.add(
      currentPanel,
      BorderLayout.NORTH);

    JLabel current = new JLabel("Current Display Mode : ");
    currentPanel.add(current);
    currentPanel.add(currentDM);

    // Display Modes
    JPanel modesPanel = new JPanel(new GridLayout(
          1,
          2));
    c.add(
      modesPanel,
      BorderLayout.CENTER);

    // List of display modes
    for(int i = 0; i < COLUMN_WIDTHS.length; i++) {
      TableColumn col = new TableColumn(i, COLUMN_WIDTHS[i]);
      col.setIdentifier(COLUMN_NAMES[i]);
      col.setHeaderValue(COLUMN_NAMES[i]);
      dmList.addColumn(col);
    }

    dmList.getSelectionModel()
          .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    dmList.getSelectionModel()
          .addListSelectionListener(this);
    modesPanel.add(dmPane);

    // Controls
    JPanel controlsPanelA = new JPanel(new BorderLayout());
    modesPanel.add(controlsPanelA);

    JPanel controlsPanelB = new JPanel(new GridLayout(
          2,
          1));
    controlsPanelA.add(
      controlsPanelB,
      BorderLayout.NORTH);

    // Exit
    JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    controlsPanelB.add(exitPanel);
    exitPanel.add(exit);

    // Change DM
    JPanel changeDMPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    controlsPanelB.add(changeDMPanel);
    changeDMPanel.add(changeDM);
    controlsPanelA.add(
      new JPanel(),
      BorderLayout.CENTER);
  }
}
