package exp.dnd;
/*
 * @(#)Test.java	1.0 98/09/21
 *
 * Copyright 1998 by Rockhopper Technologies, Inc.,
 * 75 Trueman Ave., Haddonfield, New Jersey, 08033-2529, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Rockhopper Technologies, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with RTI.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.datatransfer.*;
import java.io.*;
import java.awt.dnd.*;

/**
  * File: Test.java<br>
  * Test
  * @author <A HREF="mailto:gene@rockhoppertech.com">Gene De Lisa</A>
  * @version 1.0 Sat Dec 12, 1998
  * @see java.lang.Object
  */

public class Test extends JPanel {

  public Test() {
    this.setLayout( new GridLayout(6, 1, 5, 5));

    DragLabel2 dragLabel = new DragLabel2("C Drag from here", DnDConstants.ACTION_COPY);
    dragLabel.setBackground(Color.white);
    dragLabel.setOpaque(true);
    add(dragLabel);

    dragLabel = new DragLabel2("M Drag from here too", DnDConstants.ACTION_MOVE);
    dragLabel.setBackground(Color.white);
    dragLabel.setOpaque(true);
    add(dragLabel);

    dragLabel = new DragLabel2("CM Drag from here too", DnDConstants.ACTION_COPY_OR_MOVE);
    dragLabel.setBackground(Color.white);
    dragLabel.setOpaque(true);
    add(dragLabel);

    DropLabel dropLabel = new DropLabel("C Drop here", DnDConstants.ACTION_COPY);
    dropLabel.setBackground(Color.yellow);
    dropLabel.setOpaque(true);
    add(dropLabel);

    dropLabel = new DropLabel("M Drop here also", DnDConstants.ACTION_MOVE);
    dropLabel.setBackground(Color.yellow);
    dropLabel.setOpaque(true);
    add(dropLabel);

    dropLabel = new DropLabel("CM Drop here also", DnDConstants.ACTION_COPY_OR_MOVE);
    dropLabel.setBackground(Color.yellow);
    dropLabel.setOpaque(true);
    add(dropLabel);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setTitle("Drag and Drop test");
    Container cont = frame.getContentPane();
    cont.add( new Test() );
    frame.addWindowListener( new WindowAdapter() {
                               public void windowClosing(WindowEvent e) {
                                 System.exit(0);
                               }
                             }
                           );
    frame.setSize(300, 300);
    frame.setVisible(true);
  }

}
