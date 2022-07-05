
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
 * $Id: DnDExample.java,v 1.7 2001/02/26 12:45:30 jolif Exp $
 */

import ilog.views.*;
import ilog.views.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import javax.swing.*;
import java.util.ArrayList;
// ILOG imports
import ilog.views.*;
import ilog.views.event.*;
import ilog.views.sdm.*;
import ilog.views.swing.*;
//import ilog.views.sdm.gui.*;
import ilog.views.interactor.*;
import ilog.views.sdm.event.*;
import java.io.*;




/*
 * This example launches a JFrame containing two JInternalFrame.
 * You can drag and drop IlvGraphic from one frame to another.
 */
public class DnDExample extends JFrame
{
  private boolean main = false;
  private static ArrayList desktops = new ArrayList();

  public static void main(String[] arg)
  {
    new DnDExample();
  }

  public DnDExample()
  {
    super("Drag'n'Drop Sample");
    setSize(800,685);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        DnDExample.this.dispose();
        desktops.remove(DnDExample.this);
        if (desktops.size() == 0 || main)
          System.exit(0);
      }
    });

    // BorderLayout
    getContentPane().setLayout(new BorderLayout());

    // ToolBar
    if (desktops.size() == 0) {
      main = true;
      JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout(FlowLayout.LEFT));
      JButton b1 = new JButton("Open another Drag'n'Drop Desktop");
      b1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          desktops.add(new DnDExample());
        }
      });
      panel.add(b1);
      JButton b2 = new JButton("Reset all Drag'n'Drop Desktops");
      panel.add(b2);
      b2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          int size = desktops.size();
          for (int i = 0; i < size; i++)
            ((DnDExample)desktops.get(i)).reset();
        }
      });
      getContentPane().add(panel, BorderLayout.NORTH);
    }
    desktops.add(this);

    // Creates the MDI manager
    JDesktopPane desktop = new JDesktopPane();
    getContentPane().add(desktop, BorderLayout.CENTER);

    // Creates the JInternalFrame in which we will put the drag source IlvManagerView
    EntIlogJInternalFrame f1 =
      new EntIlogJInternalFrame("Drag an IlvGraphic from this IlvManagerView", true);
    //f1.setBackground(Color.white);

    // Create s the JInternalFrame in which we will put the drop target IlvMAnagerView
    EntIlogJInternalFrame f2 =
      new EntIlogJInternalFrame("Drop an IlvGraphic on this IlvManagerView",
                         true);
    //f2.setBackground(Color.white);

    IlvSDMView ilvSDMView = new IlvSDMView();
    IlvSDMEngine ilvSDMEngine = ilvSDMView.getSDMEngine();
    IlvSDMView ilvSDMView2 = new IlvSDMView();
    IlvSDMEngine ilvSDMEngine2 = ilvSDMView2.getSDMEngine();
    IlvJScrollManagerView p1 = new IlvJScrollManagerView(ilvSDMView);
    IlvJScrollManagerView p2 = new IlvJScrollManagerView(ilvSDMView2);


    f1.setBounds(0,0, 488, 360);
    f2.setBounds(305,260,488, 360);

    f1.getContentPane().add(p1);
    f2.getContentPane().add(p2);

    f1.setVisible(true);
    f2.setVisible(true);

    // Loads data to transfer
    try {

      //ilvSDMView.setXMLFile("file:/JBProjects/lixo.xml");
      //ilvSDMView2.setXMLFile("file:/JBProjects/lixo2.xml");

      //ilvSDMEngine.setXMLFile("file:/JBProjects/lixo.xml");
      //ilvSDMEngine2.setXMLFile("file:/JBProjects/lixo2.xml");

      File file = new File("/JBProjects/PIntV1_0/lixo.xml");
      //FileReader fr = new FileReader(file);
      RandomAccessFile raf = new RandomAccessFile(file, "r");
      byte[] bytes = new byte[new Long(raf.length()).intValue()];
      raf.read(bytes);
      ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

      ilvSDMEngine.readXML(bais);

      file = new File("/JBProjects/PIntV1_0/lixo2.xml");
      raf = new RandomAccessFile(file, "r");
      bytes = new byte[new Long(raf.length()).intValue()];
      raf.read(bytes);
      bais = new ByteArrayInputStream(bytes);

      ilvSDMEngine2.readXML(bais);


    } catch (Exception ex) {
      System.err.println("Cannot read data file");
    }

    try {
      DragGestureRecognizer dgr
        = DragSource.getDefaultDragSource().
        createDefaultDragGestureRecognizer(ilvSDMView,DnDConstants.ACTION_COPY_OR_MOVE,
                                           new DragAdapter());
      DragGestureRecognizer dgr2
        = DragSource.getDefaultDragSource().
        createDefaultDragGestureRecognizer(ilvSDMView2,DnDConstants.ACTION_COPY_OR_MOVE,
                                           new DragAdapter());
    } catch (Exception ex) {
      System.err.println("Cannot initialize DnD on IlvManagerView ");
    }

    DropTarget dt =
      new DropTarget(ilvSDMView, DnDConstants.ACTION_COPY_OR_MOVE, new DropAdapter());
    dt.setActive(true);

    DropTarget dt2 =
      new DropTarget(ilvSDMView2, DnDConstants.ACTION_COPY_OR_MOVE, new DropAdapter());
    dt2.setActive(true);

    desktop.add(f1);
    desktop.add(f2);
    setVisible(true);
  }

  /**
   *
   */
  public DnDExample(InputStream is)
  {
    super("Drag'n'Drop Sample");
    setSize(800,685);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        DnDExample.this.dispose();
        desktops.remove(DnDExample.this);
        if (desktops.size() == 0 || main)
          System.exit(0);
      }
    });

    // BorderLayout
    getContentPane().setLayout(new BorderLayout());

    // ToolBar
    if (desktops.size() == 0) {
      main = true;
      JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout(FlowLayout.LEFT));
      JButton b1 = new JButton("Open another Drag'n'Drop Desktop");
      b1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          desktops.add(new DnDExample());
        }
      });
      panel.add(b1);
      JButton b2 = new JButton("Reset all Drag'n'Drop Desktops");
      panel.add(b2);
      b2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          int size = desktops.size();
          for (int i = 0; i < size; i++)
            ((DnDExample)desktops.get(i)).reset();
        }
      });
      getContentPane().add(panel, BorderLayout.NORTH);
    }
    desktops.add(this);

    // Creates the MDI manager
    JDesktopPane desktop = new JDesktopPane();
    getContentPane().add(desktop, BorderLayout.CENTER);

    // Creates the JInternalFrame in which we will put the drag source IlvManagerView
    EntIlogJInternalFrame f1 =
      new EntIlogJInternalFrame("Drag an IlvGraphic from this IlvManagerView", true);
    //f1.setBackground(Color.white);

    // Create s the JInternalFrame in which we will put the drop target IlvMAnagerView
    EntIlogJInternalFrame f2 =
      new EntIlogJInternalFrame("Drop an IlvGraphic on this IlvManagerView",
                         true);
    //f2.setBackground(Color.white);

/*
    IlvSDMView ilvSDMView = new IlvSDMView();
    IlvSDMEngine ilvSDMEngine = ilvSDMView.getSDMEngine();
    IlvSDMView ilvSDMView2 = new IlvSDMView();
    IlvSDMEngine ilvSDMEngine2 = ilvSDMView2.getSDMEngine();
    IlvJScrollManagerView p1 = new IlvJScrollManagerView(ilvSDMView);
    IlvJScrollManagerView p2 = new IlvJScrollManagerView(ilvSDMView2);
*/

    f1.setBounds(0,0, 488, 360);
    f2.setBounds(305,260,488, 360);

    // Loads data to transfer
    try {
      f1.setXML(is);
      //ilvSDMEngine2.readXML(is);

    } catch (Exception ex) {
      System.err.println("Cannot read data file");
    }
/*
    try {
      DragGestureRecognizer dgr
        = DragSource.getDefaultDragSource().
        createDefaultDragGestureRecognizer(ilvSDMView,DnDConstants.ACTION_COPY_OR_MOVE,
                                           new DragAdapter());
      DragGestureRecognizer dgr2
        = DragSource.getDefaultDragSource().
        createDefaultDragGestureRecognizer(ilvSDMView2,DnDConstants.ACTION_COPY_OR_MOVE,
                                           new DragAdapter());
    } catch (Exception ex) {
      System.err.println("Cannot initialize DnD on IlvManagerView ");
    }

    DropTarget dt =
      new DropTarget(ilvSDMView, DnDConstants.ACTION_COPY_OR_MOVE, new DropAdapter());
    dt.setActive(true);

    DropTarget dt2 =
      new DropTarget(ilvSDMView2, DnDConstants.ACTION_COPY_OR_MOVE, new DropAdapter());
    dt2.setActive(true);
*/
    desktop.add(f1);
    desktop.add(f2);
    setVisible(true);
  }

  private void reset()
  {
  }
}

