package exp.swing.trees;


import javax.swing.JFrame;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;

import java.awt.event.*;
import java.awt.*;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.util.ArrayList;


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
public class TestTreeSelectionListener
    extends JPanel {
  String strType = null;
  static JTree jTreeVersions;
  SimpleMutableTreeNode nodeTreeRoot =
      new SimpleMutableTreeNode("Root","R");


  public TestTreeSelectionListener() {
    jTreeVersions = new JTree(nodeTreeRoot);
    //jTreeVersions.setEditable(false);

    SimpleMutableTreeNode treeNodePint0 = new SimpleMutableTreeNode("G1", "G");
    nodeTreeRoot.add(treeNodePint0);
    SimpleMutableTreeNode treeNodePint00 = new SimpleMutableTreeNode(
        "S1 1","S");
    treeNodePint0.add(treeNodePint00);
    SimpleMutableTreeNode treeNodePint01 = new SimpleMutableTreeNode(
        "S1 2", "S");
    treeNodePint0.add(treeNodePint01);

    treeNodePint00.add( new SimpleMutableTreeNode("Z1 1", "Z"));
    treeNodePint00.add( new SimpleMutableTreeNode("Z1 2", "Z"));


    SimpleMutableTreeNode treeNodePint1 = new SimpleMutableTreeNode("G2", "G");
    nodeTreeRoot.add(treeNodePint1);
    SimpleMutableTreeNode treeNodePint10 = new SimpleMutableTreeNode(
        "S2 1", "S");
    treeNodePint1.add(treeNodePint10);
    SimpleMutableTreeNode treeNodePint11 = new SimpleMutableTreeNode(
        "S2 2", "S");
    treeNodePint1.add(treeNodePint11);

    treeNodePint10.add( new SimpleMutableTreeNode("Z2 1", "Z"));
    treeNodePint10.add( new SimpleMutableTreeNode("Z2 2", "Z"));





    SimpleMutableTreeNode treeNodePint2 = new SimpleMutableTreeNode("G3", "G");
    nodeTreeRoot.add(treeNodePint2);

    JScrollPane jScrollPane = new JScrollPane();
    jScrollPane.getViewport().add(jTreeVersions, null);

    setLayout(new java.awt.BorderLayout());
    add(jScrollPane, java.awt.BorderLayout.CENTER);

    jTreeVersions.addTreeSelectionListener(new TreeSelectionListener(){
      public void valueChanged(final TreeSelectionEvent e){

        System.out.println("--------------------------------------------");

        TreePath[] tps = jTreeVersions.getSelectionPaths();

        if(tps!=null && tps.length > 0){

          if (strType == null) {
            strType = ((SimpleMutableTreeNode) tps[0].getLastPathComponent()).type;

          } else if(tps.length > 1){
            java.util.List listOfPaths = new ArrayList(tps.length);

            // test for the selection leafs type ... must be all from the same ..
            for (int i = 0; i < tps.length; i++) {
              if (strType.equals(
                  ((SimpleMutableTreeNode) tps[i].getLastPathComponent()).type)) {

System.out.println("Adding to list -> "+tps[i].toString()+".");

                listOfPaths.add(tps[i]);
              }
            }
            final int intNbrOfPaths = listOfPaths.size();
            final TreePath[] tpsNew = new TreePath[intNbrOfPaths];

            for (int i = 0; i < intNbrOfPaths; i++) {
              tpsNew[i] = (TreePath) listOfPaths.get(i);

System.out.println("Adding to the TreePath[]s -> "+tpsNew[i].toString());

            }

            jTreeVersions.setSelectionPaths(tpsNew);
            jTreeVersions.treeDidChange();

          }else if(tps.length == 1){
            strType = ((SimpleMutableTreeNode) tps[0].getLastPathComponent()).type;

          }
        }
      }
    });


  }

  public static void main(String[] args) {
    TestTreeSelectionListener t = new TestTreeSelectionListener();
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new java.awt.BorderLayout());
    f.getContentPane().add(t, java.awt.BorderLayout.CENTER);
    f.setBounds(100,100,300,450);
    f.setVisible(true);
  }
}


