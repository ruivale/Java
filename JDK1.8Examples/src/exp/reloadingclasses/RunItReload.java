package exp.reloadingclasses;

import java.awt.*;
import java.awt.event.*;

import java.io.*;

import java.lang.reflect.*;

import java.net.*;

import javax.swing.*;


public class RunItReload extends JFrame {
  JPanel contentPane;
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea source = new JTextArea();
  JPanel jPanel1 = new JPanel();
  JLabel classNameLabel = new JLabel("Class Name");
  GridLayout gridLayout1 = new GridLayout(2, 1);
  JTextField className = new JTextField();
  JButton compile = new JButton("Go");
  Font boldFont = new java.awt.Font("SansSerif", 1, 11);
  String strJavaClassName = "Sample2";
  String strJavaCode = "public class Sample2 {\n  public static void main(String args[]) {\n    System.out.println(new java.util.Date());\n  }\n}";

  public RunItReload() {
    super("Editor");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    contentPane = (JPanel) this.getContentPane();
    this.setSize(400, 300);
    classNameLabel.setFont(boldFont);
    jPanel1.setLayout(gridLayout1);
    compile.setFont(boldFont);
    compile.setForeground(Color.black);
    compile.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            doCompile();
          } catch (Exception ex) {
            System.err.println("Error during save/compile: " + ex);
            ex.printStackTrace();
          }
        }
      });
    contentPane.add(jScrollPane1, BorderLayout.CENTER);
    contentPane.add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(classNameLabel);
    jPanel1.add(className);
    jScrollPane1.getViewport().add(source);
    contentPane.add(compile, BorderLayout.SOUTH);

    className.setText(strJavaClassName);
    source.setText(strJavaCode);
  }

  public static void main(String[] args) {
    Frame frame = new RunItReload();

    // Center screen
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();

    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }

    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }

    frame.setLocation((screenSize.width - frameSize.width) / 2,
      (screenSize.height - frameSize.height) / 2);
    frame.show();
  }

  private void doCompile() throws Exception {
    // write source to file
    String sourceFile = className.getText() + ".java";
    FileWriter fw = new FileWriter(sourceFile);
    fw.write(source.getText());
    fw.close();

    // compile it
    int compileReturnCode = -1;
        //com.sun.tools.javac.Main.compile(new String[] {sourceFile});

    if (compileReturnCode == 0) {
      // Create new class loader
      // with current dir as CLASSPATH
      File file = new File(".");
      ClassLoader loader = new URLClassLoader(new URL[] { file.toURL() });

      // load class through new loader
      Class aClass = loader.loadClass(className.getText());

      // run it
      Object[] objectParameters = { new String[] {  } };
      Class[] classParameters = { objectParameters[0].getClass() };
      Method theMethod = aClass.getDeclaredMethod("main", classParameters);

      // Static method, no instance needed
      theMethod.invoke(null, objectParameters);
    }
  }
}
