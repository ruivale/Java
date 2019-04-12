
package  exp.dnd;


import  javax.swing.*;
import  javax.swing.tree.*;
import  java.awt.*;
import  java.awt.dnd.*;
import  java.io.IOException;



public class DraggableTree extends JTree
        implements DragGestureListener {

    DragSource dragSource = DragSource.getDefaultDragSource();
    final static DragSourceListener dragSourceListener = new MyDragSourceListener();

    static class MyDragSourceListener
            implements DragSourceListener {


        public void dragDropEnd (DragSourceDropEvent DragSourceDropEvent) {}

        public void dragEnter (DragSourceDragEvent DragSourceDragEvent) {}

        public void dragExit (DragSourceEvent DragSourceEvent) {}

        public void dragOver (DragSourceDragEvent DragSourceDragEvent) {}

        public void dropActionChanged (DragSourceDragEvent DragSourceDragEvent) {}
    }

    public DraggableTree () {
        dragSource.createDefaultDragGestureRecognizer(
            this,
            DnDConstants.ACTION_COPY_OR_MOVE,
            this);
    }

    public DraggableTree (TreeModel model) {
        super(model);
        dragSource.createDefaultDragGestureRecognizer(
            this,
            DnDConstants.ACTION_COPY_OR_MOVE,
            this);
    }

    // DragGestureListener
    public void dragGestureRecognized (DragGestureEvent dragGestureEvent) {
        TreePath path = getSelectionPath();
        if (path == null) {
            // Nothing selected, nothing to drag
            System.out.println("Nothing selected - beep");
            getToolkit().beep();
        }
        else {
            DefaultMutableTreeNode selection = (DefaultMutableTreeNode)path.getLastPathComponent();
            TransferableTreeNode node = new TransferableTreeNode(selection);
            dragSource.startDrag(
                dragGestureEvent,
                DragSource.DefaultCopyDrop,
                node,
                dragSourceListener);
        }
    }
}



