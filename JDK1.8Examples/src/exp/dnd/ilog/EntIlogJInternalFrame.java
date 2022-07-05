package exp.dnd.ilog;

import javax.swing.JInternalFrame;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */


import ilog.views.*;
import ilog.views.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import javax.swing.*;
import java.util.ArrayList;
// ILOG imports
import ilog.views.*;
import ilog.views.event.*;
import ilog.views.sdm.*;
import ilog.views.swing.*;
//import ilog.views.sdm.gui.*;
import ilog.views.interactor.*;
import ilog.views.sdm.event.*;
import java.io.*;



/**
 *
 *
 */
public class EntIlogJInternalFrame extends JInternalFrame {

   public IlvSDMView ilvSDMView = new IlvSDMView();
   public IlvSDMEngine ilvSDMEngine = ilvSDMView.getSDMEngine();
   public IlvJScrollManagerView ilvjScrollManagerView = new IlvJScrollManagerView(ilvSDMView);


  public EntIlogJInternalFrame(){

  }

  public EntIlogJInternalFrame(String title, boolean resizable){

    super(title, resizable);

    setBackground(Color.white);

    // Removes the title bar from this JInternalFrame
    ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);

    getContentPane().add(ilvjScrollManagerView);

    try {
      DragGestureRecognizer dgr
        = DragSource.getDefaultDragSource().
        createDefaultDragGestureRecognizer(
          ilvSDMView,
          DnDConstants.ACTION_COPY_OR_MOVE,
          new DragAdapter());

    } catch (Exception ex) {
      System.err.println("Cannot initialize DnD on IlvManagerView ");
    }

    DropTarget dt =
      new DropTarget(ilvSDMView, DnDConstants.ACTION_COPY_OR_MOVE, new DropAdapter());
    dt.setActive(true);


    setVisible(true);




  }


  public void setXML(InputStream is){
    try {
      this.ilvSDMEngine.readXML(is);

    }catch(Exception e){
      e.printStackTrace();
    }
  }


  public static void main(String[] args) {
  }
}