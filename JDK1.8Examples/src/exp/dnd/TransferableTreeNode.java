
package exp.dnd;
/*
 * put your module comment here
 * formatted with JxBeauty (c) johann.langhofer@nextra.at
 */


import javax.swing.tree.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.io.*;


/**
 * put your documentation comment here
 */
public class TransferableTreeNode
    extends DefaultMutableTreeNode
    implements Transferable {
  
  final static int TREE = 0;
  final static int STRING = 1;
  final static int PLAIN_TEXT = 2;

  final public static DataFlavor DEFAULT_MUTABLE_TREENODE_FLAVOR = new DataFlavor(
        DefaultMutableTreeNode.class,
        "Default Mutable Tree Node");

  static DataFlavor flavors[] = {
    DEFAULT_MUTABLE_TREENODE_FLAVOR, DataFlavor.stringFlavor, DataFlavor.plainTextFlavor
  };
  /* The following works fine
   static DataFlavor flavors[] = {
   DEFAULT_MUTABLE_TREENODE_FLAVOR
   };
   */
  private DefaultMutableTreeNode data;

  /**
   * put your documentation comment here
   * @param     DefaultMutableTreeNode data
   */
  public TransferableTreeNode (DefaultMutableTreeNode data) {
    this.data = data;
  }

  /**
   * put your documentation comment here
   * @return
   */
  public DataFlavor[] getTransferDataFlavors () {
    return flavors;
  }

  /**
   * put your documentation comment here
   * @param flavor
   * @return
   * @exception UnsupportedFlavorException, IOException
   */
  public Object getTransferData (DataFlavor flavor) throws UnsupportedFlavorException,
    IOException {
    Object returnObject;
    if (flavor.equals(flavors[TREE])) {
      Object userObject = data.getUserObject();
      if (userObject == null) {
        returnObject = data;
      } else {
        returnObject = userObject;
      }
    } else if (flavor.equals(flavors[STRING])) {
      Object userObject = data.getUserObject();
      if (userObject == null) {
        returnObject = data.toString();
      } else {
        returnObject = userObject.toString();
      }
    } else if (flavor.equals(flavors[PLAIN_TEXT])) {
      Object userObject = data.getUserObject();
      String string;
      if (userObject == null) {
        string = data.toString();
      } else {
        string = userObject.toString();
      }
      returnObject = new ByteArrayInputStream(string.getBytes());
    } else {
      throw new UnsupportedFlavorException(flavor);
    }
    return returnObject;
  }

  /**
   * put your documentation comment here
   * @param flavor
   * @return
   */
  public boolean isDataFlavorSupported (DataFlavor flavor) {
    boolean returnValue = false;
    for (int i = 0, n = flavors.length; i < n; i++) {
      if (flavor.equals(flavors[i])) {
        returnValue = true;
        break;
      }
    }
    return returnValue;
  }
}



