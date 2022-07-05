// %1221738546122:%
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk1_6examples.javax.swing.tree.lazyloading;


import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;


/**
 * 
 *
 * @author $author$
 * @version $Revision$
  */
public abstract class LazyLoadingTreeNode
    extends DefaultMutableTreeNode
    implements TreeWillExpandListener {
  /**  */
  private static final String ESCAPE_ACTION_NAME = "escape";

  /**  */
  private static final KeyStroke ESCAPE_KEY =
    KeyStroke.getKeyStroke(
      KeyEvent.VK_ESCAPE,
      0);

  /** The JTree containing this Node */
  private JTree tree;

  /** Can the worker be Canceled ? */
  private boolean cancelable;

  /**
   * Default Constructor
   *
   * @param userObject an Object provided by the user that constitutes the
   *        node's data
   * @param tree the JTree containing this Node
   * @param cancelable
   */
  public LazyLoadingTreeNode(
    final Object userObject,
    final JTree tree,
    final boolean cancelable) {
    super(userObject);
    tree.addTreeWillExpandListener(this);
    this.tree         = tree;
    this.cancelable   = cancelable;
    setAllowsChildren(true);
  }

  /**
   * If the
   *
   * @return false, this node can't be a leaf
   *
   * @see #getAllowsChildren()
   */
  @Override
  public boolean isLeaf() {
    return !getAllowsChildren();
  }

  /**
   * This method will be executed in a background thread.  If you have to
   * do some GUI stuff use {@link SwingUtilities#invokeLater(Runnable)}
   *
   * @param tree the tree
   *
   * @return the Created nodes
   */
  public abstract MutableTreeNode[] loadChildren(final JTree tree);

  /**
   * Default empty implementation, do nothing on collapse event.
   *
   * @param event
   *
   * @throws ExpandVetoException
   */
  public void treeWillCollapse(final TreeExpansionEvent event)
      throws ExpandVetoException {
  }

  /**
   * Node will expand, it's time to retreive nodes
   *
   * @param event
   *
   * @throws ExpandVetoException
   */
  public void treeWillExpand(final TreeExpansionEvent event)
      throws ExpandVetoException {
    if(this.equals(event.getPath().getLastPathComponent())) {
      if(areChildrenLoaded()) {
        return;
      }

      setLoading();

      SwingWorker<MutableTreeNode[], ?> worker =
        createSwingWorker(
          tree,
          cancelable);

      worker.execute();
    }
  }

  /**
   * Define nodes children
   */
  protected void setChildren(MutableTreeNode... nodes) {
    if(nodes == null) {
    }

    TreeModel model = tree.getModel();

    if(model instanceof DefaultTreeModel) {
      DefaultTreeModel defaultModel = (DefaultTreeModel)model;
      int childCount                = getChildCount();

      if(childCount > 0) {
        for(int i = 0; i < childCount; i++) {
          defaultModel.removeNodeFromParent((MutableTreeNode)getChildAt(0));
        }
      }

      for(int i = 0; i < nodes.length; i++) {
        defaultModel.insertNodeInto(
          nodes[i],
          this,
          i);
      }
    }
  }

  /**
   * 
  DOCUMENT ME!
   *
   * @return <code>true</code> if there are some childrens
   */
  protected boolean areChildrenLoaded() {
    return (getChildCount() > 0) && getAllowsChildren();
  }

  /**
   * 
  DOCUMENT ME!
   *
   * @return a new Loading please wait node
   */
  protected MutableTreeNode createLoadingNode() {
    return new DefaultMutableTreeNode(
      "Loading Please Wait ...",
      false);
  }

  /**
   * Create worker that will load the nodes
   *
   * @param tree the tree
   * @param cancelable if the worker should be cancelable
   *
   * @return the newly created SwingWorker
   */
  protected SwingWorker<MutableTreeNode[], ?> createSwingWorker(
    final JTree tree,
    final boolean cancelable) {
    SwingWorker<MutableTreeNode[], ?> worker =
      new SwingWorker<MutableTreeNode[], Object>() {
        @Override
        protected void done() {
          try {
            if(!isCancelled()) {
              MutableTreeNode[] nodes = get();

              setAllowsChildren(nodes.length > 0);
              setChildren(nodes);
              unRegisterSwingWorkerForCancel(
                tree,
                this);
            } else {
              reset();
            }
          } catch(final InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          } catch(final ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }

        @Override
        protected MutableTreeNode[] doInBackground()
            throws Exception {
          return loadChildren(tree);
        }
      };

    registerSwingWorkerForCancel(
      tree,
      worker);

    return worker;
  }

  /**
   * If the node is cancelable an escape Action is registered in the
   * tree's  InputMap and ActionMap that will cancel the execution
   *
   * @param tree the tree
   * @param worker the worker to cancel
   */
  protected void registerSwingWorkerForCancel(
    final JTree                       tree,
    final SwingWorker<MutableTreeNode[], ?> worker) {
    if(!cancelable) {
      return;
    }

    tree.getInputMap()
        .put(
      ESCAPE_KEY,
      ESCAPE_ACTION_NAME);

    Action action = tree.getActionMap()
                        .get(ESCAPE_ACTION_NAME);

    if(action == null) {
      CancelWorkersAction cancelWorkerAction = new CancelWorkersAction();

      cancelWorkerAction.addSwingWorker(worker);
      tree.getActionMap()
          .put(
        ESCAPE_ACTION_NAME,
        cancelWorkerAction);
    } else {
      if(action instanceof CancelWorkersAction) {
        CancelWorkersAction cancelAction = (CancelWorkersAction)action;

        cancelAction.addSwingWorker(worker);
      }
    }
  }

  /**
   * Need some improvement ... This method should restore the Node
   * initial state if the worker if canceled
   */
  protected void reset() {
    DefaultTreeModel defaultModel = (DefaultTreeModel)tree.getModel();
    int childCount                = getChildCount();

    if(childCount > 0) {
      for(int i = 0; i < childCount; i++) {
        defaultModel.removeNodeFromParent((MutableTreeNode)getChildAt(0));
      }
    }

    setAllowsChildren(true);
  }

  /**
   * Remove the swingWorker from the cancellable task of the tree
   *
   * @param tree
   * @param worker
   */
  protected void unRegisterSwingWorkerForCancel(
    final JTree                       tree,
    final SwingWorker<MutableTreeNode[], ?> worker) {
    if(!cancelable) {
      return;
    }

    Action action = tree.getActionMap()
                        .get(ESCAPE_ACTION_NAME);

    if((action != null) && action instanceof CancelWorkersAction) {
      CancelWorkersAction cancelWorkerAction = new CancelWorkersAction();

      cancelWorkerAction.removeSwingWorker(worker);
    }
  }

  /**
   * set the loading state
   */
  private void setLoading() {
    setChildren(createLoadingNode());

    TreeModel model = tree.getModel();

    if(model instanceof DefaultTreeModel) {
      DefaultTreeModel defaultModel = (DefaultTreeModel)model;
      int[] indices                 = new int[getChildCount()];

      for(int i = 0; i < indices.length; i++) {
        indices[i] = i;
      }

      defaultModel.nodesWereInserted(
        LazyLoadingTreeNode.this,
        indices);
    }
  }

  /**
   * ActionMap can only store one Action for the same key, This Action
   * Stores the list of SwingWorker to be canceled if the escape  key is
   * pressed.
   *
   * @author Thierry LEFORT 3 mars 08
   */
  protected static class CancelWorkersAction
      extends AbstractAction {
    /** the SwingWorkers */
    private Vector<SwingWorker<MutableTreeNode[], ?>> workers =
      new Vector<SwingWorker<MutableTreeNode[], ?>>();

    /**
     * Default constructor
     */
    private CancelWorkersAction() {
      super(ESCAPE_ACTION_NAME);
    }

    /**
     * Do the Cancel
     *
     * @param e
     */
    public void actionPerformed(final ActionEvent e) {
      Iterator<SwingWorker<MutableTreeNode[], ?>> it = workers.iterator();

      while(it.hasNext()) {
        SwingWorker<MutableTreeNode[], ?> worker =
          (SwingWorker<MutableTreeNode[], ?>)it.next();

        worker.cancel(true);
      }
    }

    /**
     * Add a Cancelable SwingWorker
     *
     * @param worker
     */
    public void addSwingWorker(final SwingWorker<MutableTreeNode[], ?> worker) {
      workers.add(worker);
    }

    /**
     * Remove a SwingWorker
     *
     * @param worker
     */
    public void removeSwingWorker(
      final SwingWorker<MutableTreeNode[], ?> worker) {
      workers.remove(worker);
    }
  }
}