package exp.swing.trees;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
/**
 * Class that holds info about the stations nodes. It is used in the tree so
 * this class extends DefaultMutableTreeNode
 */
public class SimpleMutableTreeNode
    extends DefaultMutableTreeNode {

  String strTooltipText;
  String type;
  /**
   * Creates a new SimpleMutableTreeNode object.
   *
   * @param name
   */
  public SimpleMutableTreeNode(String name) {
    super(name);
    this.strTooltipText = name;
  }
  /**
   * Creates a new SimpleMutableTreeNode object.
   *
   * @param name
   */
  public SimpleMutableTreeNode(String name, String type) {
    super(name);
    this.strTooltipText = name;
    this.type = type;
  }

  /**
   * getToolTipText
   *
   * @return String
   */
  public String getToolTipText() {
    return this.strTooltipText;
  }
  /**
   *
   * @param strToolTip String
   */
  public void setToolTipText(final String strToolTip){
    this.strTooltipText = strToolTip;
  }
}
