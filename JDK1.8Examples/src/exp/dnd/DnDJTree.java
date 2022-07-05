// %1130335836937:exp.dnd%
/*
 * put your module comment here
 * formatted with JxBeauty (c) johann.langhofer@nextra.at
 */
package exp.dnd;


import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.dnd.peer.*;
import java.awt.event.*;

import java.io.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.tree.*;


/**
 * put your documentation comment here
 */
public class DnDJTree
    extends JTree
    implements TreeSelectionListener,
      DragGestureListener,
      DropTargetListener,
      DragSourceListener {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  protected PersonNode SelectedNode = null;

  /** Stores the selected node info */
  protected TreePath SelectedTreePath = null;

  /** Variables needed for DnD */
  private DragSource        dragSource        = null;

  /** .. */
  private DragSourceContext dragSourceContext = null;

  /** Stores the parent Frame of the component */
  private Frame Parent = null;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Constructor
   *
   * @param root The root node of the tree
   * @param parent Parent JFrame of the JTree
   */
  public DnDJTree(
    PersonNode root,
    Frame      parent) {

    super(root);

    Parent = parent;
    addTreeSelectionListener(this);

    /* ********************** CHANGED ********************** */
    dragSource = DragSource.getDefaultDragSource();

    /* ****************** END OF CHANGE ******************** */
    DragGestureRecognizer dgr = dragSource.createDefaultDragGestureRecognizer(
        this, //DragSource
        DnDConstants.ACTION_COPY_OR_MOVE, //specifies valid actions
        this //DragGestureListener
      );

    /* Eliminates right mouse clicks as valid actions - useful especially
     * if you implement a JPopupMenu for the JTree
     */
    dgr.setSourceActions(dgr.getSourceActions() & ~InputEvent.BUTTON3_MASK);

    /* First argument:  Component to associate the target with
     * Second argument: DropTargetListener
     */
    DropTarget dropTarget = new DropTarget(this, this);

    //unnecessary, but gives FileManager look
    putClientProperty(
      "JTree.lineStyle",
      "Angled");

    MetalTreeUI ui = (MetalTreeUI)getUI();
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Returns The selected node
   *
   * @return  Insert doc ...
   */
  public PersonNode getSelectedNode() {
    return SelectedNode;
  }

  /**
   * DragSourceListener interface method
   *
   * @param dsde  Insert doc ...
   */
  public void dragDropEnd(DragSourceDropEvent dsde) {
    System.out.println(System.currentTimeMillis()+" - dragDropEnd(DragSourceDropEvent dsde) {");
  }

  /**
   * DragSourceListener interface method
   *
   * @param dsde  Insert doc ...
   */
  public void dragEnter(DragSourceDragEvent dsde) {
    /* ********************** CHANGED ********************** */
    /* ****************** END OF CHANGE ******************** */
    System.out.println(System.currentTimeMillis()+" - dragEnter(DragSourceDragEvent dsde) {");
  }

  /**
   * DropTaregetListener interface method
   *
   * @param e  Insert doc ...
   */
  public void dragEnter(DropTargetDragEvent e) {
    System.out.println(System.currentTimeMillis()+" - dragEnter(DropTargetDragEvent e) {");
  }

  /**
   * DragSourceListener interface method
   *
   * @param dsde  Insert doc ...
   */
  public void dragExit(DragSourceEvent dsde) {
    System.out.println(System.currentTimeMillis()+" - dragExit(DragSourceEvent dsde) {");
  }

  /**
   * DropTaregetListener interface method
   *
   * @param e  Insert doc ...
   */
  public void dragExit(DropTargetEvent e) {
    System.out.println(System.currentTimeMillis()+" - dragExit(DropTargetEvent e) {");
  }

  ///////////////////////// Interface stuff ////////////////////

  /**
   * DragGestureListener interface method
   *
   * @param e  Insert doc ...
   */
  public void dragGestureRecognized(DragGestureEvent e) {

    System.out.println(System.currentTimeMillis()+" - dragGestureRecognized(DragGestureEvent e) {");

    try{
      //Get the selected node
      PersonNode dragNode = getSelectedNode();

      if (dragNode != null) {
        //Get the Transferable Object
        Transferable transferable = (Transferable) dragNode.getUserObject();

System.out.println("\ttranferable="+transferable.toString()+".");

        /* ********************** CHANGED ********************** */

        //Select the appropriate cursor;
        Cursor cursor = DragSource.DefaultCopyNoDrop;
        int action = e.getDragAction();

        if (action == DnDConstants.ACTION_MOVE) {
          cursor = DragSource.DefaultLinkDrop;
        }

        //        cursor = DragSource.DefaultMoveNoDrop;
        //In fact the cursor is set to NoDrop because once an action is rejected
        // by a dropTarget, the dragSourceListener are no more invoked.
        // Setting the cursor to no drop by default is so more logical, because
        // when the drop is accepted by a component, then the cursor is changed by the
        // dropActionChanged of the default DragSource.
        /* ****************** END OF CHANGE ******************** */
        //begin the drag
        dragSource.startDrag(
            e,
            cursor,
            transferable,
            this);
      }
    }catch(Exception exc){
      exc.printStackTrace();
    }
  }

  /**
   * DragSourceListener interface method
   *
   * @param dsde  Insert doc ...
   */
  public void dragOver(DragSourceDragEvent dsde) {
    System.out.println(System.currentTimeMillis()+" - dragOver(DragSourceDragEvent dsde) {");
    /* ********************** CHANGED ********************** */
    /* ****************** END OF CHANGE ******************** */
  }

  /**
   * DropTaregetListener interface method
   *
   * @param e  Insert doc ...
   */
  public void dragOver(DropTargetDragEvent e) {
    System.out.println(System.currentTimeMillis()+" - dragOver(DropTargetDragEvent e) {");
    /* ********************** CHANGED ********************** */

    //set cursor location. Needed in setCursor method
    Point    cursorLocationBis = e.getLocation();
    TreePath destinationPath = getPathForLocation(
        cursorLocationBis.x,
        cursorLocationBis.y);

    // if destination path is okay accept drop...
    if(testDropTarget(
            destinationPath,
            SelectedTreePath)==null) {
      e.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
    }
    // ...otherwise reject drop
    else {
      e.rejectDrag();
    }

    /* ****************** END OF CHANGE ******************** */
  }

  /**
   * DropTargetListener interface method - What we do when drag is released
   *
   * @param e  Insert doc ...
   */
  public void drop(DropTargetDropEvent e) {
    System.out.println(System.currentTimeMillis()+" - drop(DropTargetDropEvent e) {");
    try {
      Transferable tr = e.getTransferable();

      //flavor not supported, reject drop
      if(!tr.isDataFlavorSupported(PersonalInfo.INFO_FLAVOR)) {
        e.rejectDrop();
      }

      //cast into appropriate data type
      PersonalInfo childInfo = (PersonalInfo)tr.getTransferData(
          PersonalInfo.INFO_FLAVOR);

      //get new parent node
      Point        loc             = e.getLocation();
      TreePath     destinationPath = getPathForLocation(
          loc.x,
          loc.y);
      final String msg             = testDropTarget(
          destinationPath,
          SelectedTreePath);

      if(msg!=null) {
        e.rejectDrop();
        SwingUtilities.invokeLater(
          new Runnable() {
            /**
             * put your documentation comment here
             */
            public void run() {
              JOptionPane.showMessageDialog(
                Parent,
                msg,
                "Error Dialog",
                JOptionPane.ERROR_MESSAGE);
            }
          });

        return;
      }

      PersonNode newParent = (PersonNode)destinationPath.getLastPathComponent();

      //get old parent node
      PersonNode oldParent  = (PersonNode)getSelectedNode()
                                            .getParent();
      int        action     = e.getDropAction();
      boolean    copyAction = (action==DnDConstants.ACTION_COPY);

      //make new child node
      PersonNode newChild = new PersonNode(childInfo);

      try {
        if(!copyAction) {
          oldParent.remove(getSelectedNode());
        }

        newParent.add(newChild);

        if(copyAction) {
          e.acceptDrop(DnDConstants.ACTION_COPY);
        } else {
          e.acceptDrop(DnDConstants.ACTION_MOVE);
        }
      } catch(java.lang.IllegalStateException ils) {
        e.rejectDrop();
      }

      e.getDropTargetContext()
       .dropComplete(true);

      //expand nodes appropriately - this probably isnt the best way...
      DefaultTreeModel model = (DefaultTreeModel)getModel();
      model.reload(oldParent);
      model.reload(newParent);

      TreePath parentPath = new TreePath(newParent.getPath());
      expandPath(parentPath);
    } catch(IOException io) {
      e.rejectDrop();
    } catch(UnsupportedFlavorException ufe) {
      e.rejectDrop();
    }
  } //end of method

  /**
   * DragSourceListener interface method
   *
   * @param dsde  Insert doc ...
   */
  public void dropActionChanged(DragSourceDragEvent dsde) {
    System.out.println(System.currentTimeMillis()+" - dropActionChanged(DragSourceDragEvent dsde) {");
  }

  /**
   * DropTaregetListener interface method
   *
   * @param e  Insert doc ...
   */
  public void dropActionChanged(DropTargetDragEvent e) {
    System.out.println(System.currentTimeMillis()+" - dropActionChanged(DropTargetDragEvent e) {");
  }

  //program entry point
  public static final void main(String[] args) {
    JFrame    f = new JFrame();
    Container c = f.getContentPane();

    //Generate your family "tree"
    PersonalInfo pi          = new PersonalInfo("GrandMother",
        PersonalInfo.FEMALE);
    PersonNode   grandmother = new PersonNode(pi);
    pi = new PersonalInfo(
        "Mother",
        PersonalInfo.FEMALE);

    PersonNode mother = new PersonNode(pi);
    pi = new PersonalInfo(
        "Daughter",
        PersonalInfo.FEMALE);

    PersonNode daughter = new PersonNode(pi);
    pi = new PersonalInfo(
        "Baby",
        PersonalInfo.FEMALE);

      PersonNode baby = new PersonNode(pi);
      pi = new PersonalInfo(
          "Son",
          PersonalInfo.MALE);

      PersonNode newbaby = new PersonNode(pi);
      pi = new PersonalInfo(
          "Son",
          PersonalInfo.MALE);

    PersonNode son = new PersonNode(pi);
    grandmother.add(mother);
    mother.add(daughter);
    mother.add(son);
    mother.add(newbaby);
    daughter.add(baby);

    //add the tree to the frame and display the frame.
    c.add(new DnDJTree(
        grandmother,
        f));
    f.pack();
    f.show();
  }

  /**
   * TreeSelectionListener - sets selected node
   *
   * @param evt  Insert doc ...
   */
  public void valueChanged(TreeSelectionEvent evt) {
    SelectedTreePath = evt.getNewLeadSelectionPath();

    if(SelectedTreePath==null) {
      SelectedNode = null;

      return;
    }

    SelectedNode = (PersonNode)SelectedTreePath.getLastPathComponent();
  }

  /**
   * Convenience method to test whether drop location is valid
   *
   * @param destination The destination path
   * @param dropper The path for the node to be dropped
   *
   * @return null if no problems, otherwise an explanation
   */
  private String testDropTarget(
    TreePath destination,
    TreePath dropper) {
    //Typical Tests for dropping
    //Test 1.
    boolean destinationPathIsNull = destination==null;

    if(destinationPathIsNull) {
      return "Invalid drop location.";
    }

    //Test 2.
    PersonNode node = (PersonNode)destination.getLastPathComponent();

    if(!node.getAllowsChildren()) {
      return "This node does not allow children";
    }

    if(destination.equals(dropper)) {
      return "Destination cannot be same as source";
    }

    //Test 3.
    if(dropper.isDescendant(destination)) {
      return "Destination node cannot be a descendant.";
    }

    //Test 4.
    if(dropper.getParentPath()
                  .equals(destination)) {
      return "Destination node cannot be a parent.";
    }

    return null;
  }
} //end of DnDJTree
