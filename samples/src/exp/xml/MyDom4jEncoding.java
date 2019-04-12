package exp.xml;


import org.dom4j.io.*;
import java.awt.*;
import java.io.*;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c)
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 */
import java.sql.*;

import javax.swing.*;


/**
 *
 *
 */
public class MyDom4jEncoding {
  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new MyDom4jEncoding object.
   */
  public MyDom4jEncoding() {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
    } catch(java.lang.ClassNotFoundException e) {
      System.err.print("ClassNotFoundException: ");
      System.err.println(e.getMessage());
    }
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   */
  public static void main(String[] args) {
    try {
      MyDom4jEncoding myDom4jEncoding = new MyDom4jEncoding();

//System.out.println("Targets:"+InputStreamHelper.stringFromInputStream(myDom4jEncoding.getTargets()));
      SAXReader          reader = new SAXReader();
      org.dom4j.Document doc = reader.read(myDom4jEncoding.getTargets());

//      org.dom4j.Document doc = DocumentHelper.parseText(
      //      InputStreamHelper.stringFromInputStream(myDom4jEncoding.getTargets()));
      System.out.println("doc:\n" + doc.asXML() + "\n");

      JFrame f = new JFrame();
      f.getContentPane()
       .setLayout(new BorderLayout());

      JDesktopPane jdp = new JDesktopPane();

      ByteArrayOutputStream XML = new ByteArrayOutputStream();
      OutputFormat format       = new OutputFormat();
      format.setEncoding("ISO-8859-1");

      XMLWriter writer = new XMLWriter(XML, format);
      writer.write(doc);
      writer.flush();

/*
   EntSDMPanelView esp = new EntSDMPanelView(
     null, new ByteArrayInputStream(XML.toByteArray()));
   esp.setSize(400, 300);
   jdp.add(esp);
 */
      f.getContentPane()
       .add(jdp, BorderLayout.CENTER);
      f.setBounds(100, 50, 500, 400);
      f.setVisible(true);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Insert doc ...
   */
  private void setEncoding() {
/*
   //      org.dom4j.Document doclk = DocumentHelper.parseText(
     //      InputStreamHelper.stringFromInputStream(giXML));
   System.out.println("\n\n\n\n\n\n\n\n"+InputStreamHelper.stringFromInputStream(giXML)+"\n\n\n");
   //System.out.println("\n\n\ndoclk:\n"+doclk.asXML()+"\n\n");
   PInt.waitWindow.close();
   if(true)return;
 */
/*
   SAXReader reader = new SAXReader();
   org.dom4j.Document doc = reader.read();
   ByteArrayOutputStream XML = new ByteArrayOutputStream();
   OutputFormat format = new OutputFormat();
   format.setEncoding("ISO-8859-1");
   XMLWriter writer = new XMLWriter(XML, format);
   writer.write(doc);
   writer.flush();
 */
  }

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   */
  private InputStream getGIXML() {
    try {
      Connection conn =
        DriverManager.getConnection("jdbc:oracle:thin:@172.18.56.11:1521:STV",
          "inoss_stv", "inoss");

      CallableStatement callableStatement =
        conn.prepareCall("{? = call get_gi_xml(?, ?)}");

      callableStatement.registerOutParameter(1, Types.CLOB);

      // Setting the paramenter to the stored procedure.
      //      callableStatement.setInt(2, 199000);
      // A REDE TODA
      callableStatement.setInt(2, -1);

      callableStatement.setInt(3, 9); //rvale

      //     callableStatement.setBoolean(4, true);
      // Executing
      callableStatement.executeUpdate();

      // Getting the result
      Clob   clob = callableStatement.getClob(1);

      String out = clob.getSubString(
          1,
          (new Long(clob.length())).intValue());

      conn.close();

      //System.out.println("o clob: \n" + out + "\n");
      return (new ByteArrayInputStream(out.getBytes()));
    } catch(Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   */
  private InputStream getTargets() {
    try {
      Connection conn =
        DriverManager.getConnection("jdbc:oracle:thin:@172.18.56.11:1521:STV",
          "inoss_stv", "inoss");

      CallableStatement cs =
        conn.prepareCall("{? = call getstationtargetequipsxml(?, ?)}");

      cs.registerOutParameter(1, Types.CLOB);

      // Station ID
      cs.setInt(2, 199000);

      //User ID stv(5)/ rvale(9)
      cs.setInt(3, 9);

      cs.executeUpdate();

      Clob   clob = cs.getClob(1);
      String out = clob.getSubString(
          1,
          (new Long(clob.length())).intValue());

      //System.out.println("o clob é: \n"+out);
      return (new ByteArrayInputStream(out.getBytes()));
    } catch(Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * Insert doc ...
   *
   * @return  Insert doc ...
   */
  private InputStream getUserXML() {
    try {
      Connection conn =
        DriverManager.getConnection("jdbc:oracle:thin:@172.18.56.11:1521:INOSS",
          "inoss_system", "inoss");

      // SQL92 syntax!
      CallableStatement cs =
        conn.prepareCall("{? = call Get_XML_UserMenus(?)}");

      cs.registerOutParameter(1, Types.CLOB);

      // User Manel
      //cs.setInt(2, 2);
      cs.setInt(2, 9);

      cs.executeUpdate();

      conn.close();

      Clob   clob = cs.getClob(1);
      String out = clob.getSubString(
          1,
          (new Long(clob.length())).intValue());

      //System.out.println("o clob é: " + out);
      return (new ByteArrayInputStream(out.getBytes()));
    } catch(Exception e) {
      e.printStackTrace();
    }

    return null;
  }
}
