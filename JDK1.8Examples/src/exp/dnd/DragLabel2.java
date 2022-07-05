package exp.dnd;
/*
 * @(#)DragLabel.java	1.0 98/09/21
 *
 * Copyright 1998 by Rockhopper Technologies, Inc.,
 * 75 Trueman Ave., Haddonfield, New Jersey, 08033-2529, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Rockhopper Technologies, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with RTI.
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import javax.swing.*;

/**
  * File: DragLabel.java<br>
  * a JLabel subclass that allows its text to be
  * transferred via Drag and Drop
  * @author <A HREF="mailto:gene@rockhoppertech.com">Gene De Lisa</A>
  * @version 1.0 Wed Dec 09, 1998
  * @see javax.swing.JLabel
  */

public class DragLabel2 extends JLabel {

  public DragLabel2(String s) {
    this.setText(s);
    this.setOpaque(true);
    this.dragSource = DragSource.getDefaultDragSource();
    this.dgListener = new DGListener();
    this.dsListener = new DSListener();

    // component, action, listener
    this.dragSource.createDefaultDragGestureRecognizer(
      this,
      this.dragAction,
      this.dgListener);
  }

  public DragLabel2(String s, int a) {
    if (a != DnDConstants.ACTION_NONE &&
      a != DnDConstants.ACTION_COPY &&
      a != DnDConstants.ACTION_MOVE &&
      a != DnDConstants.ACTION_COPY_OR_MOVE &&
      a != DnDConstants.ACTION_LINK
    ) throw new IllegalArgumentException("action" + a);

    this.dragAction = a;
    this.setText(s);
    this.setOpaque(true);
    this.dragSource = DragSource.getDefaultDragSource();
    this.dgListener = new DGListener();
    this.dsListener = new DSListener();

    // component, action, listener
    this.dragSource.createDefaultDragGestureRecognizer(
      this,
      this.dragAction,
      this.dgListener);
  }


  /**
   * DGListener
   * a listener that will start the drag.
   * has access to top level's dsListener and dragSource
   * @see java.awt.dnd.DragGestureListener
   * @see java.awt.dnd.DragSource
   * @see java.awt.datatransfer.StringSelection
   */
  class DGListener implements DragGestureListener {
    /**
     * Start the drag if the operation is ok.
     * uses java.awt.datatransfer.StringSelection to transfer
     * the label's data
     * @param e the event object
     */
    public void dragGestureRecognized(DragGestureEvent e) {

      // if the action is ok we go ahead
      // otherwise we punt
      //System.out.println(e.getDragAction());
      if((e.getDragAction() & DragLabel2.this.dragAction) == 0)
        return;
      //System.out.println( "kicking off drag");

      // get the label's text and put it inside a Transferable
      // Transferable transferable = new StringSelection( DragLabel.this.getText() );
      Transferable transferable = new StringTransferable( DragLabel2.this.getText() );

      // now kick off the drag
      try {
        // initial cursor, transferrable, dsource listener
        e.startDrag(DragSource.DefaultCopyNoDrop,
          transferable,
          DragLabel2.this.dsListener);

        // or if dragSource is a variable
        // dragSource.startDrag(e, DragSource.DefaultCopyDrop, transferable, dsListener);


        // or if you'd like to use a drag image if supported

        /*
          if(DragSource.isDragImageSupported() )
          // cursor, image, point, transferrable, dsource listener
          e.startDrag(DragSource.DefaultCopyDrop, image, point, transferable, dsListener);
        */

      }catch( InvalidDnDOperationException idoe ) {
        System.err.println( idoe );
      }
    }
  }

  /**
   * DSListener
   * a listener that will track the state of the DnD operation
   *
   * @see java.awt.dnd.DragSourceListener
   * @see java.awt.dnd.DragSource
   * @see java.awt.datatransfer.StringSelection
   */
  class DSListener implements DragSourceListener {

    /**
     * @param e the event
     */
    public void dragDropEnd(DragSourceDropEvent e) {

      System.out.println("    dragDropEnd(DragSourceDropEvent e)");

      if( e.getDropSuccess() == false ) {
        //System.out.println( "not successful");
        return;
      }

      /*
       * the dropAction should be what the drop target specified
       * in acceptDrop
       */
      //System.out.println( "dragdropend action " + e.getDropAction() );

      // this is the action selected by the drop target
      if(e.getDropAction() == DnDConstants.ACTION_MOVE)
        DragLabel2.this.setText("");
    }

    /**
     * @param e the event
     */
    public void dragEnter(DragSourceDragEvent e) {

      System.out.println( "    dragEnter(DragSourceDragEvent e) " + e);

      DragSourceContext context = e.getDragSourceContext();
      //intersection of the users selected action, and the source and target actions
      int myaction = e.getDropAction();
      if( (myaction & DragLabel2.this.dragAction) != 0) {
        context.setCursor(DragSource.DefaultCopyDrop);
      } else {
        context.setCursor(DragSource.DefaultCopyNoDrop);
      }
    }
    /**
     * @param e the event
     */
    public void dragOver(DragSourceDragEvent e) {

      System.out.println("    dragOver(DragSourceDragEvent e");

      DragSourceContext context = e.getDragSourceContext();
      int sa = context.getSourceActions();
      int ua = e.getUserAction();
      int da = e.getDropAction();
      int ta = e.getTargetActions();
      /*
      System.out.println("dl dragOver source actions" + sa);
      System.out.println("user action" + ua);
      System.out.println("drop actions" + da);
      System.out.println("target actions" + ta);
      */
    }
    /**
     * @param e the event
     */
    public void dragExit(DragSourceEvent e) {

      System.out.println( "    dragExit(DragSourceEvent e)" + e);

      DragSourceContext context = e.getDragSourceContext();
    }

    /**
     * for example, press shift during drag to change to
     * a link action
     * @param e the event
     */
    public void dropActionChanged (DragSourceDragEvent e) {
      DragSourceContext context = e.getDragSourceContext();
      context.setCursor(DragSource.DefaultCopyNoDrop);
    }
  }


  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setTitle("DragLabel test");
    Container cont = frame.getContentPane();
    DragLabel2 l = new DragLabel2("Here is some text");
    l.setBackground(Color.black);
    l.setForeground(Color.yellow);
    cont.add( l );
    frame.addWindowListener( new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    frame.setSize(300,300);
    frame.setVisible(true);
  }

  private DragSource dragSource;
  private DragGestureListener dgListener;
  private DragSourceListener dsListener;
  private int dragAction = DnDConstants.ACTION_COPY;
}
