
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
 * $Id: DropAdapter.java,v 1.7 2001/02/26 12:45:31 jolif Exp $
 */


import ilog.views.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.awt.*;
import java.io.*;
import java.util.*;

/**
 * <code>DropAdapter</code> is a class which allows an <code>IlvManagerView</code>
 * to be a DropTarget for Java DnD mechanism by implementing Java DropTargetListener
 * @see DragAdapter
 */
public class DropAdapter
  implements DropTargetListener
{
  public  void dragEnter (DropTargetDragEvent dtde)
  {
    // we accept drag only of our type
    dtde.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
  }

  public  void dragOver (DropTargetDragEvent dtde)
  {
    // we accept drag only of our type
    dtde.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
  }


  /**
   * It implements the DropTargetListener method to end a DnD operation
   * @param dtde the triggered event
   */
  public  void drop (DropTargetDropEvent dtde)
  {

System.out.println("drop (DropTargetDropEvent dtde)");

    // Gets the targeted view
    IlvManagerView view = (IlvManagerView)(dtde.getDropTargetContext().getComponent());

    // Verifies the kind of action asked by the user
    if ((dtde.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {
      // Gets the location of mouse pointer
      IlvPoint p = new IlvPoint(dtde.getLocation().x, dtde.getLocation().y);
      view.getTransformer().inverse(p);

      // We parse DataFlavor supported by the Transferable object
      // to find one we can understand.
      Transferable trans = dtde.getTransferable();
      DataFlavor df[] = dtde.getCurrentDataFlavors();

      for (int i = 0; i < df.length; i++) {
        // IlvGraphic.GetGraphicObject recognize DataFlavor.stringFlavor
        if (DataFlavor.stringFlavor.equals(df[i])) {
          // we have found the right flavor, accepts the user action
          dtde.acceptDrop(dtde.getDropAction());
          IlvGraphic object = null;
          try {
            // asks for the corresponding IlvGraphic
            object = IlvGraphic.GetGraphicObject(trans);

            // Everything is OK
            if (object != null)
              dtde.getDropTargetContext().dropComplete(true);
            else {
              dtde.getDropTargetContext().dropComplete(false);
              return;
            }
          }
          catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Cannot perform drop action");
            dtde.getDropTargetContext().dropComplete(false);
            return;
          }

          // Getting the right property we will then move the
          // object to the right position and add it to manager
          IlvPoint delta = ((DeltaProperty)object.getNamedProperty(DeltaProperty.NAME)).getDelta();


          object.move(p.x - delta.x, p.y - delta.y);
          view.getManager().addObject(object, true);

          // Prevents from polluting memory space
          object.removeNamedProperty(DeltaProperty.NAME);

          return;
        }
      }
      // Found no compatible DataFlavor : reject the drop
      dtde.rejectDrop();
    }
    else dtde.rejectDrop();
  }

  // In this simple example we don't redefine following methods
  public  void dropActionChanged(DropTargetDragEvent dtde)
  {
  }
  public void dragScroll(DropTargetDragEvent dtde)
  {
  }
  public  void dragExit(DropTargetEvent dte)
  {
  }
}
