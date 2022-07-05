package exp.swing.andthreads.bigeg;

import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

/**
 * Loads the begin,end bracketed text from a file and creates
 * a tab pane for each one:
 *
 *   @begin ExampleClassName ExampleTitle
 *   Example HTML documentation
 *   @end
 *
 * The Example1, Example2 classes are set up this way.
 */
class ExampleContainer
    extends JPanel {
  Example1 firstExample;

  class ExampleInfo {
    String className;
    String title;
    String htmlText;
  }

  static void messageAndExit(String msg) {
    System.err.println("\n" + msg);
    System.exit(1);
  }

  Reader makeHTMLDoc(String text) {
    String header =
        "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML//EN\"><html><body>\n";

    text += "<a href=\"http://www.sapo.pt\">sapo</a>";
    String footer = "</body>\n</html>\n";
    return new CharArrayReader( (header + text + footer).toCharArray());
  }

  Vector parseExampleFile(File file) {

    /* Load the file into a string.  We expand tabs here to work around
     * a bug in the HTML viewer.
     */
    String fileContents = "";
    try {
      DataInputStream stream = new DataInputStream(new
          FileInputStream(file));
      BufferedReader reader = new BufferedReader(new
                                                 InputStreamReader(stream));
      StringBuffer buffer = new StringBuffer();
      int c;
      while ( (c = reader.read()) != -1) {
        if (c == '\t') {
          buffer.append("    ");
        } else {
          buffer.append( (char) c);
        }
      }
      fileContents = buffer.toString();
    } catch (IOException e) {
      messageAndExit("Couldn't load \"" + file + "\" " + e);
    }

//System.out.println("File contents= "+fileContents+".</File contents>");


    /* Extract the @begin,@end bracked text, create a ExampleInfo
     * object for each one.
     */
    Vector rv = new Vector();
    int i0 = 0;
    while ( (i0 = fileContents.indexOf("@begin", i0)) != -1) {
      int i1 = fileContents.indexOf("@end", i0);
      if (i1 == -1) {
        messageAndExit("Can't find matching @end for " +
                       fileContents.substring(i0));
      }
      String s = fileContents.substring(i0 + "@begin".length() + 1, i1);


      StringTokenizer st = new StringTokenizer(s);
      ExampleInfo info = new ExampleInfo();
      info.className = st.nextToken(" ");
      info.title = st.nextToken("\n").trim();
      info.htmlText = st.nextToken("");
/*
      ExampleInfo info = new ExampleInfo();
      info.className = "Example1";
      info.title = ""+i1;
      info.htmlText = ""+s;
*/


/*
      StringTokenizer st = new StringTokenizer(s);
      ExampleInfo info = new ExampleInfo();
      info.className = st.nextToken(" ");
      info.title = st.nextToken("\n").trim();
      info.htmlText = st.nextToken("");
*/

System.out.println("info= "+info.className+".</info>");

      rv.addElement(info);
      i0 = i1;
    }
    return rv;
  }

  ExampleContainer(File file) {
    try{

      Vector examples = parseExampleFile(file);
      boolean defaultButtonIsSet = false;

      for (int i = 0; i < examples.size(); i++) {
        ExampleInfo exampleInfo = (ExampleInfo) (examples.elementAt(i));


//System.out.println("exampleInfo= "+exampleInfo.htmlText+".");

        /* Create an instance of the example class
         */
        Class exampleClass = null;
        Component exampleComponent;
        try {
          exampleClass = Class.forName(exampleInfo.className);
          exampleComponent = (Component) (exampleClass.newInstance());
        } catch (Exception e) {
          exampleClass = Class.forName("exp.swing.andthreads.bigeg.Example1");
          exampleComponent = new JLabel("Can't build example: " + e);
        }

        /* Make a JEditorPane that Displays the HTML documentation
         * for the example.
         */
        String header =
            "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML//EN\"><html><body>\n";
        String title = "<h1>" + exampleInfo.title + "</h1>\n";
        String text = exampleInfo.htmlText + "\n";
        String footer = "\n</body>\n</html>\n";

        JEditorPane docView = new JEditorPane();
        docView.setEditable(false);
        docView.setContentType("text/html");
        Document doc = docView.getEditorKit().createDefaultDocument();
        Reader inr = new CharArrayReader( (header + title + text +
                                           footer).toCharArray());
        try {
          docView.getEditorKit().read(inr, doc, 0);
          docView.setDocument(doc);
        } catch (Exception e) {
          messageAndExit("Couldn't parse HTML doc: " + e);
        }

        /* Add a panel that contains the example and its documentation
         */
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5),
            exampleClass.toString(),
            TitledBorder.CENTER,
            TitledBorder.TOP));
        panel.setLayout(new BorderLayout());
        panel.add(exampleComponent, BorderLayout.NORTH);
        panel.add(new JScrollPane(docView), BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(320, 480));
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        //XXX:
        if (!defaultButtonIsSet &&
            exampleComponent instanceof Example1) {
          firstExample = (Example1) exampleComponent;
          defaultButtonIsSet = true;
        }

      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  void setFocus() {
    if(firstExample == null){
      System.out.println("firstExample == null");
      return;
    }
    if(firstExample.getStartButton() == null){
      System.out.println("firstExample.getStartButton() == null");
      return;
    }
    firstExample.getStartButton().requestFocus();
  }
}
