package exp.xml.parsing;


import java.awt.*;

import javax.swing.*;


/**
 * Title: Description: Copyright:    Copyright (c) Company:
 *
 * @author
 * @version 1.0
 */
public class Untitled1
    extends JDialog {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  JButton jButton1 = new JButton();

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new Untitled1 object.
   */
  public Untitled1() {
    try {
      //jbInit();

/*
   // instantiate parser
   org.apache.xerces.parsers.DOMParser parser = new org.apache.xerces.parsers.DOMParser();
   //parser.parse();
   org.w3c.dom.Document document;
   document = parser.getDocument();
   org.apache.xerces.dom.DocumentImpl di = (org.apache.xerces.dom.DocumentImpl)document;
   di.setEncoding("ISO-8859-1");
   ilog.views.sdm.util.IlvXMLConnector a = new ilog.views.sdm.util.IlvXMLConnector();
 */
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   */
  public static void main(String[] args) {
    Untitled1 untitled11 = new Untitled1();
  }

  /**
   * Insert doc ...
   *
   * @throws Exception  Insert doc ...
   */
  private void jbInit()
      throws Exception {
    jButton1.setText("jButton1");
    jButton1.setBounds(new Rectangle(36, 38, 166, 55));
    this.getContentPane()
        .setLayout(null);
    this.getContentPane()
        .add(jButton1, null);
  }
}
