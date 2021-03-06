package exp.dnd;

/*
 * @(#)StringTransferable.java	1.0 98/09/21
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

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
  * File: StringTransferable.java<br>
  * StringTransferable
  *
  * @author <A HREF="mailto:gene@rockhoppertech.com">Gene De Lisa</A>
  * @version 1.0 Mon Oct 26, 1998
  * @see java.lang.Object
  */

public class StringTransferable implements Transferable, ClipboardOwner {

  // We don't really need these class variables since the array will contain them.
  // In a future article we will actually make our own flavors here.
  public static final DataFlavor plainTextFlavor = DataFlavor.plainTextFlavor;
  public static final DataFlavor localStringFlavor = DataFlavor.stringFlavor;

  public static final DataFlavor[] flavors = {
    StringTransferable.plainTextFlavor,
    StringTransferable.localStringFlavor
  };

  private static final List flavorList = Arrays.asList( flavors );
  private String string;

  /**
   * Constructor.
   * simply initializes instance variable
   */
  public StringTransferable(String string) {
    this.string = string;
  }

  private void dumpFlavor(DataFlavor flavor) {
    System.out.println( "getMimeType " +
                        flavor.getMimeType() );
    System.out.println( "getHumanPresentableName " +
                        flavor.getHumanPresentableName() );
    System.out.println( "getRepresentationClass " +
                        flavor.getRepresentationClass().getName() );
    System.out.println( "isMimeTypeSerializedObject " +
                        flavor.isMimeTypeSerializedObject() );
    System.out.println( "isRepresentationClassInputStream " +
                        flavor.isRepresentationClassInputStream() );
    System.out.println( "isRepresentationClassSerializable " +
                        flavor.isRepresentationClassSerializable() );
    System.out.println( "isRepresentationClassRemote " +
                        flavor.isRepresentationClassRemote() );
    System.out.println( "isFlavorSerializedObjectType " +
                        flavor.isFlavorSerializedObjectType() );
    System.out.println( "isFlavorRemoteObjectType " +
                        flavor.isFlavorRemoteObjectType() );
    System.out.println( "isFlavorJavaFileListType " +
                        flavor.isFlavorJavaFileListType() );
  }

  public synchronized DataFlavor[] getTransferDataFlavors() {
    //    return (DataFlavor[]) flavorList.toArray();
    return flavors;
  }

  public boolean isDataFlavorSupported( DataFlavor flavor ) {
    return ( flavorList.contains( flavor ) );
  }

  public synchronized Object getTransferData(DataFlavor flavor)
  throws UnsupportedFlavorException, IOException {
    System.err.println( "getTransferData(): ");
    dumpFlavor(flavor);

    if (flavor.equals(StringTransferable.plainTextFlavor)) {
      return new ByteArrayInputStream(this.string.getBytes("Unicode"));
    } else if (StringTransferable.localStringFlavor.equals(flavor)) {
      return this.string;
    } else {
      throw new UnsupportedFlavorException (flavor);
    }
  }
  public String toString() {
    return "StringTransferable";
  }

  public void lostOwnership(Clipboard clipboard, Transferable contents) {
    System.out.println ("StringTransferable lost ownership of " +
                        clipboard.getName());
    System.out.println ("data: " + contents);
  }
}
