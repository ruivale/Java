package exp.ilog;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

import exp.ilog.tree.*;




import ilog.views.sdm.gui.action.*;
import ilog.views.sdm.gui.util.*;
import ilog.views.sdm.gui.event.*;
import ilog.views.sdm.gui.interactor.*;

import ilog.views.sdm.*;
import ilog.views.sdm.renderer.*;

import ilog.views.*;
import ilog.views.swing.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;



/**
 *
 *
 */
public class ShowXMLInTreeAndPanel extends JFrame implements IlvSDMUtils.ArgList,
                                              IlvSDMApplicationInterface{

  IlvSDMGrapherView grapherView;
  IlvSDMDocumentController controller;
  IlvSDMDocument document;
  IlvSDMTreeView tree;
  IlvSDMFrame treeDialog;

  IlvSDMEngine engine;
  IlvSDMView view;

  Container contentPane;
  Container rootAncestor;

  // The frame object.
  IlvSDMFrameInterface frame;

  String[] args;




/**
 *
 */
  public ShowXMLInTreeAndPanel() {

    this.frame = new IlvSDMFrame();
    ((JFrame)this.frame).addWindowListener(new WindowAdapter(){
        public void windowClosed(WindowEvent e){
            System.exit(0);
        }
      });
    init(this.frame.getContentPane(), null);

/*
    this.openXML("d:\\jbprojects\\gistv\\data\\GISTVTargets.xml");
    this.openCSS("d:\\jbprojects\\gistv\\data\\GISTVTarget.css");
*/
    this.openXML("/JBProjects/IG/GISTV/data/GISTV_demo2.xml");
    this.openCSS("/JBProjects/IG/GISTV/data/MapaGISTV.css");

    showTreeView();

  }


  /**
   * Creates the SDM engine and the GUI of the viewer.
   * <p>
   * When the viewer is used as an applet,
   * this method is called automatically by the JApplet {@link #init()}
   * method.
   * <p>
   * When the viewer is used as an application,
   * this method must be called explicitly, for example:
   * <pre>
   *   JFrame frame = new JFrame();
   *   SDMViewer viewer = new SDMViewer(args);
   *   viewer.init(frame.getContentPane());
   * </pre>
   *
   * @param container The container in which the viewer GUI will
   * be created.
   * @param args The command-line arguments passed to the
   * <code>main</code> method of the application.
   */
  public void init(Container container, String[] args)
  {
    Container c = container;

    this.contentPane = container;
    while(container != null){
      // This allows to create the IlvSDMFrame manually:
      // it is detected and set here.
      //
      if(this.frame == null &&
         container instanceof IlvSDMFrameInterface)
        this.frame = (IlvSDMFrameInterface)container;
      if(container instanceof JApplet ||
         container instanceof JFrame){
        this.rootAncestor = container;
        break;
      }
      container = container.getParent();
    }

    // This used to be before the loop.
    //
    if(this.frame == null) {
      this.frame = new IlvSDMContainerFrame(c);
    }

    this.args = args;

//    boolean editable = getBooleanArg(this.editorArg, isEditionAllowed());

    // Create the SDM engine/view.
    //
    this.controller = new IlvSDMDocumentController(this);
//    addExtendedActions();
    this.controller.setUsingDefaultInteractor(false,null);
    this.document = new IlvSDMDocument(this.controller, null)
      {
        public Object createGroupParent()
        {
          Object parent = null;
          if(parent != null)
            return parent;
          else
            return super.createGroupParent();
        }
      };


    this.document.setEditable(true);
//    this.document.setEditable(false);


    this.grapherView = new IlvSDMGrapherView(this.document, frame);

    this.engine = this.document.getEngine();
    this.view = this.grapherView.getView();

//    this.view.setInteractor(new com.ent.PInt.sdm.SelectInteractor());

    getDocumentController().setActiveDocument(this.document);

    // Initialize viewer according to command-line arguments:

    // Initialize drag and drop.
    //
//    setDragAllowed(getBooleanArg(this.dragArg, false));
  //  setDropAllowed(getBooleanArg(this.dropArg, editable));

    // Create/add the status bar if requested.
    //
//    setStatusBarVisible(getBooleanArg(this.statusbarArg, true));

    // Create/add the toolbar if requested.
    //
//    setToolBarPosition(getStringArg(this.toolbarPositionArg, BorderLayout.NORTH));
  //  setToolBarVisible(getBooleanArg(this.toolbarArg, true));

    // Create/add the menu if requested.
    //
//    setMenuVisible(getBooleanArg(this.menuArg, true));

    // Title:
    //
//    String title = getStringArg(this.titleArg, this.title);
  //  this.frame.setTitle(title);

    // Pack frame:
    //
    this.frame.pack();

      this.frame.setSize(600, 500);
      if(this.frame instanceof JFrame){
        try {
          ((JFrame)this.frame).setLocation(0, 0);
        } catch(Exception ex2){}
      }

    this.frame.setVisible(true);


  }

  /**
   *
   */
  public void showTreeView(){

    this.treeDialog = new IlvSDMFrame();
    this.tree = new IlvSDMTreeView(this.document, this.treeDialog);

    this.treeDialog.setVisible(true);

  }



  /**
   * Opens an XML data file.
   * @param filename The name of the XML file.
   * This can be a full URL, a relative URL, or a
   * file name.
   */
  public void openXML(String filename)
  {
    this.document.openXML(filename);
  }

  /**
   * Opens a style sheet file.
   * @param filename The name of the CSS file.
   * This can be a full URL, a relative URL, or a
   * file name.
   */
  public void openCSS(String filename)
  {
    this.document.openCSS(filename);

  }

  /**
   * The "Open XML" action.
   */
  public void openXML()
  {
/*
    String filename = chooseFile(lastOpenXMLDirectory, this.XMLSuffixes, this.XMLDescrs, false);
    lastOpenXMLDirectory = IlvSDMUtils.lastChooseFileDirectory;
    if(filename != null){
      openXML(filename);
    }
*/
  }

  /**
   * The "Open CSS" action.
   */
  public void openCSS()
  {
/*
    String filename = chooseFile(lastOpenCSSDirectory, this.CSSSuffixes, this.CSSDescrs, false);
    lastOpenCSSDirectory = IlvSDMUtils.lastChooseFileDirectory;
    if(filename != null)
      openCSS(filename);
*/
  }




  /**
   * Returns the <code>IlvSDMDocument</code> associated
   * with this viewer.
   */
  public IlvSDMDocument getDocument()
  {
    return this.document;
  }
  /**
   * Does nothing.
   */
  public void newDocument()
  {
  }



  /**
   * Sets the command line arguments.
   * @param args The <code>String</code> array of the new command line arguments.
   */
  public void setArgs(String[] args) { this.args = args; }


  /**
   * Returns the command line arguments in a <code>String</code> array.
   */
  public String[] getArgs() { return this.args; }

  /**
   *
   */
  private IlvSDMFrameInterface getSDMFrame()
  {
    return this.frame;
  }

  /**
   * Returns the application controller.
   */
  public IlvSDMDocumentController getDocumentController()
  {
    return this.controller;
  }


  /**
   * Returns the top frame of the application.
   */
  public JFrame getMainApplicationFrame()
  {
    if (this.frame != null && this.frame instanceof JFrame)
      return (JFrame) this.frame;
    else
      return null;
  }


  /**
   * Returns always <code>null</code>.
   */
  public String getDefaultStylesheet() { return null; }

  /**
   *
   */
  private void fireActionStateChangedEvent()
  {
    getDocumentController().fireActionStateChangedEvent();
  }

  /**
   * This method is performed by the <code>IlvSDMDocumentController</code> when the active document view changes.
   * @param event The event.
   */
  public void activeDocumentViewChanged(AWTEvent event)
  {
    fireActionStateChangedEvent();
  }














  public static void main(String[] args) {
    ShowXMLInTreeAndPanel t = new ShowXMLInTreeAndPanel();
/*
    t.setBounds(10,10,700,500);
    t.setVisible(true);
*/


  }
}