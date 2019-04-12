
package  exp.dnd;
/*
 * put your module comment here
 * formatted with JxBeauty (c) johann.langhofer@nextra.at
 */


import  java.awt.*;
import  java.awt.dnd.*;
import  java.awt.datatransfer.*;
import  javax.swing.*;
import  java.io.*;
import  java.util.*;
import  java.util.List;


public class DroppableList extends JList
        implements DropTargetListener {
    DropTarget dropTarget;

    public DroppableList () {
        dropTarget = new DropTarget(this, this);
        setModel(new DefaultListModel());
    }

    public void dragEnter (DropTargetDragEvent dropTargetDragEvent) {
        dropTargetDragEvent.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
    }

    public void dragExit (DropTargetEvent dropTargetEvent) {}

    public void dragOver (DropTargetDragEvent dropTargetDragEvent) {}

    public void dropActionChanged (DropTargetDragEvent dropTargetDragEvent) {}


    public synchronized void drop (DropTargetDropEvent dropTargetDropEvent) {
        try {
            Transferable tr = dropTargetDropEvent.getTransferable();
            if (tr.isDataFlavorSupported(TransferableTreeNode.DEFAULT_MUTABLE_TREENODE_FLAVOR)) {
                dropTargetDropEvent.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                Object userObject = tr.getTransferData(TransferableTreeNode.DEFAULT_MUTABLE_TREENODE_FLAVOR);
                ((DefaultListModel)getModel()).addElement(userObject);
                dropTargetDropEvent.getDropTargetContext().dropComplete(true);
            }
            else if (tr.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                dropTargetDropEvent.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                String string = (String)tr.getTransferData(DataFlavor.stringFlavor);
                ((DefaultListModel)getModel()).addElement(string);
                dropTargetDropEvent.getDropTargetContext().dropComplete(true);
            }
            else if (tr.isDataFlavorSupported(DataFlavor.plainTextFlavor)) {
                dropTargetDropEvent.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                Object stream = tr.getTransferData(DataFlavor.plainTextFlavor);
                if (stream instanceof InputStream) {
                    InputStreamReader isr = new InputStreamReader((InputStream)stream);
                    BufferedReader reader = new BufferedReader(isr);
                    String line;
                    while ((line = reader.readLine()) != null) {
                        ((DefaultListModel)getModel()).addElement(line);
                    }
                    dropTargetDropEvent.getDropTargetContext().dropComplete(true);
                }
                else if (stream instanceof Reader) {
                    BufferedReader reader = new BufferedReader((Reader)stream);
                    String line;
                    while ((line = reader.readLine()) != null) {
                        ((DefaultListModel)getModel()).addElement(line);
                    }
                    dropTargetDropEvent.getDropTargetContext().dropComplete(true);
                }
                else {
                    System.err.println("Unknown type: " + stream.getClass());
                    dropTargetDropEvent.rejectDrop();
                }
            }
            else if (tr.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                dropTargetDropEvent.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                List fileList = (List)tr.getTransferData(DataFlavor.javaFileListFlavor);
                Iterator iterator = fileList.iterator();
                while (iterator.hasNext()) {
                    File file = (File)iterator.next();
                    Hashtable hashtable = new Hashtable();
                    hashtable.put("name", file.getName());
                    hashtable.put("url", file.toURL().toString());
                    ((DefaultListModel)getModel()).addElement(hashtable);
                }
                dropTargetDropEvent.getDropTargetContext().dropComplete(true);
            }
            else {
                System.err.println("Rejected");
                dropTargetDropEvent.rejectDrop();
            }
        } catch (IOException io) {
            io.printStackTrace();
            dropTargetDropEvent.rejectDrop();
        } catch (UnsupportedFlavorException ufe) {
            ufe.printStackTrace();
            dropTargetDropEvent.rejectDrop();
        }
    }
}



