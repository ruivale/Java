
package exp.dnd.ilog;

/*
 * Copyright (C) 1996-2001 by ILOG.
 * All rights reserved.
 *
 * This source code is an addition to the ILOG JViews Reference Manual
 * delivered  with the JViews library.
 * It is supplied as an example to show you how to code with JViews.
 * Feel free to use, copy or modify it within the framework of your
 * JViews license agreement.
 */
/*
 * $Id: DragAdapter.java,v 1.9 2001/02/26 12:45:31 jolif Exp $
 */

import ilog.views.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.lang.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.image.*;
import java.io.*;


/**
 * <code>DragAdapter</code> is a class which allows an <code>IlvManagerView</code>
 * to be a DragSource for Java DnD mechanism by implementing Java DragSource and
 * DragGesture Listners.
 * @see DropAdapter
 */
public class DragAdapter
  implements DragSourceListener, DragGestureListener
{
  private static DragSource ds = DragSource.getDefaultDragSource();
  private IlvGraphic currentDrag = null;

  /**
   * It implements the DragGestureListener method to begin a DnD operation
   * @param ev the triggered event
   */
  public void dragGestureRecognized(DragGestureEvent ev)
  {
    // Gets the view from the event
    IlvManagerView view = (IlvManagerView)((DragGestureRecognizer)ev.getSource()).getComponent();

    // Tests that if the action is allowed
    if ((ev.getDragAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {

      // Tries to get the object under the mouse (we only deal with
      // IlvGraphic objects).
      IlvPoint p = new IlvPoint(ev.getDragOrigin().x, ev.getDragOrigin().y);
      currentDrag = view.getManager().getObject(p, view);

      if (currentDrag != null && view.getManager().isSelectable(currentDrag)) {
        // Here we can improve behavior on platform allowing DragImage
        if (ds.isDragImageSupported()) {
          // creation of an image
        }

        // Computation of the position of the mouse pointer in the object
        view.getTransformer().inverse(p);

        IlvRect rect = currentDrag.boundingBox(null);
        IlvPoint delta = new IlvPoint(p.x - rect.x, p.y - rect.y);

        // To renember this position in drop action we set a property on the object
        //currentDrag.setNamedProperty(new DeltaProperty(delta));

        // We change the cursor depending on the action type
        Cursor cursor = (ev.getDragAction() == DnDConstants.ACTION_COPY)?
          ds.DefaultCopyDrop:ds.DefaultMoveDrop;
        try {

System.out.println("\ncurrentDrag: "+currentDrag.toString()+".\n");


          // Send the IlvGraphic to the DnD mechanism starting to drag
/*
          ev.startDrag(//ev,
                       cursor,
                       // IlvGraphic implements Transferable and can be directly passed
                       // to drag n drop mechanism.
                       currentDrag,
                       // this class will be the DragSourceListener
                       this);
*/
          ds.startDrag(ev,
                       cursor,
                       // IlvGraphic implements Transferable and can be directly passed
                       // to drag n drop mechanism.
                       currentDrag,
                       // this class will be the DragSourceListener
                       this);

        }  catch (Exception ex) {
          ex.printStackTrace();
          System.err.println("Drag cannot be performed");
          // Removes NamedProperty to prevent from polluting memory space
          currentDrag.removeNamedProperty(DeltaProperty.NAME);
        }
      }
    }
  }

  /**
   * It implements the DragSourceListener method to end a DnD operation
   * @param dsde the triggered event
   */
  public void dragDropEnd(DragSourceDropEvent dsde)
  {
    // Gets the view
    IlvManagerView view = (IlvManagerView)(dsde.getDragSourceContext().getComponent());
    // Removes NamedProperty to prevent from polluting memory space
    currentDrag.removeNamedProperty(DeltaProperty.NAME);
    // In move action we delete the initial object in case of success
    if (dsde.getDropSuccess() && ((dsde.getDropAction() & DnDConstants.ACTION_MOVE) != 0))
      view.getManager().removeObject(currentDrag, true);
    currentDrag = null;
  }

  // In this simple example we don't redefine following methods
  public void dropActionChanged(DragSourceDragEvent dsde)
  {
    System.out.println("    dropActionChanged(Drag");

  }
  public void dragGestureChanged(DragSourceDragEvent dsde)
  {
    System.out.println("    dragGestureChanged(DragSou");
  }
  public void dragEnter(DragSourceDragEvent dsde)
  {
    System.out.println("    dragEnter(DragSource");
  }
  public void dragOver(DragSourceDragEvent dsde)
  {
    System.out.println("    dragOver(DragSource");

        int action = dsde.getDropAction();
        if (action == DnDConstants.ACTION_COPY) {
          dsde.getDragSourceContext().setCursor(null);
          dsde.getDragSourceContext().setCursor(DragSource.DefaultCopyDrop);

        } else {

          if (action == DnDConstants.ACTION_MOVE) {
            dsde.getDragSourceContext().setCursor(null);
            dsde.getDragSourceContext().setCursor(DragSource.DefaultMoveDrop);
          } else {
            dsde.getDragSourceContext().setCursor(DragSource.DefaultMoveNoDrop);
            dsde.getDragSourceContext().setCursor(null);
          }
        }


  }
  public void dragExit(DragSourceEvent dsde)
  {
    System.out.println("    dragExit(DragSource");

          dsde.getDragSourceContext().setCursor(null);


  }
}
