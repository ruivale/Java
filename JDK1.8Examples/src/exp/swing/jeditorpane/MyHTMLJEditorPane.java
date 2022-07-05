package exp.swing.jeditorpane;

import javax.swing.*;
import java.awt.BorderLayout;
import java.net.URL;


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
public class MyHTMLJEditorPane extends JPanel{

  final JEditorPane jEditorPane;// = new JEditorPane();

  public MyHTMLJEditorPane() throws Exception{

    final String strUrl =
      //"file:///D:/JBProjects/PIntV1_0/bak/PIntAboutInfo.html";
      //"file://D:\\JBProjects\\PIntV1_0\\bak\\PIntAboutInfo.html";
      //"file:///d:/JBProjects/PIntV1_0/bak/PIntAboutInfo.html";
      //"file:///./PIntAboutInfo.html";
      "file:///D:/temp/env_vars.txt";
    
    jEditorPane = new JEditorPane(strUrl);
    jEditorPane.setEditable(false);

/***
    try {
      //final URL url = new URL(
        //  "file:/D:/JBProjects/PIntV1_0/bak/PIntAboutInfo.html");
      //jEditorPane.setPage(url);
      //jEditorPane.setText(
        //"<html><body>AAAAAAAAAAA <a href=\"http://www.efacec.pt\">lklk</a></body></html>");

        String msg = "<html><body><center><a href=\"http://www.sapo.pt\">sapo</a><h2>Titlet</h2></center><b><p>Text to</p><p>display</p></b></body></html>";
        String strMimeType = "text/html";
        jEditorPane.setContentType(strMimeType);
        jEditorPane.setText(msg);
        //jEditorPane.setOpaque(false);



    } catch (Exception ex) {
      ex.printStackTrace();
    }
/**/
    
    this.setLayout(new BorderLayout());



    //this.add(jEditorPane, BorderLayout.CENTER);
    final JScrollPane jScrollPane = new JScrollPane(jEditorPane);
    this.add(jScrollPane, BorderLayout.CENTER);

  }

  public static void main(String[] args) throws Exception {
    MyHTMLJEditorPane m = new MyHTMLJEditorPane();

    JFrame f = new JFrame("Worker Thread Examples");
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(m, BorderLayout.CENTER);
    f.pack();
    f.setSize(400,250);
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
