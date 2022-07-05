package exp.toolbar;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */


import java.lang.reflect.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;


public class ToolAction extends JButton implements ActionListener {

  private Class toolClass;
  private Method classMethod;//?VAR GLOBAL?

  private String toolClassText;//?VAR GLOBAL?
  private String classMethodText;//?VAR GLOBAL?
  private Object[] methodArgs;

  private static final String errorTitle = "jshdjshd";/*PropertieFiles.getValue(
        "tools/tools",
        "tools.component.error.title");*/
  private static final String errorMsg = "jsdhjsd";/*PropertieFiles.getValue(
        "tools/tools",
        "tools.component.error.msg");*/

  public int toolID;



  /**
   *
   */
  public ToolAction(String id,
                    String toolText,
                    String icon,
                    String _toolClass,
                    String _classMethod,
                    Object _methodArgs[]) {

    super((icon != null ? new ImageIcon(icon) : null));

    this.toolID = Integer.parseInt(id);
    this.toolClassText = _toolClass;
    this.classMethodText = _classMethod;
    this.methodArgs = _methodArgs;

    this.setToolTipText(toolText);
    this.addActionListener(this);

    if(buildClass() != 0){
        //WARNING MSG
    }

  }



  /**
   *
   */
  private int buildClass() {
    Class para[];
    try {
      toolClass = Class.forName(toolClassText);

      if (methodArgs != null) {
        para = new Class[methodArgs.length];
        for (int i=methodArgs.length; i>0; i--) {
          para[i-1] = Class.forName("java.lang.String");
        }
      } else {
        para = new Class[0];
        methodArgs = new Object[0];
      }

      classMethod = toolClass.getMethod(classMethodText, para);

    } catch (ClassNotFoundException cnfe) {
      System.out.println("ClassNotFoundException");
      cnfe.printStackTrace();
      return 0;
    }
    catch (NoSuchMethodException nsme) {
      System.out.println("NoSuchMethodException");
      nsme.printStackTrace();
      return 0;
    }

      return 1;

  }

  /**
   *
   */
  public void actionPerformed(ActionEvent actionEvent) {

    try {
      // Invoking the tool method with the desired arguments
      classMethod.invoke(toolClass.newInstance(), methodArgs);

    } catch (Exception e) {
      System.out.println("InvokationException");
      e.printStackTrace();
/*
      FaultApplication.showMessageDialog(
        errorTitle,
        errorMsg,
        FaultApplication.ERROR_MESSAGE);
*/
    }


  }





  public static void main(String[] args) {
        JFrame f = new JFrame();

        final JToolBar mb = new JToolBar();

        ToolAction toolAction1 = new ToolAction("12",
          "Auxiliar class",
          "/JBProjects/PInt/classes/images/icons/ENTIcon.gif",
          "exp.reflection.method.Auxiliar",
          "doA",
          new String[]{"AAA","aaa"}
        );
        mb.add(toolAction1);

        mb.addSeparator();


        final Vector v = new Vector();

        toolAction1 = new ToolAction("1323",
          "Auxiliar class 2",
          "/JBProjects/PInt/classes/images/icons/ENTIcon.gif",
          "exp.reflection.method.Auxiliar",
          "doA",
          new String[]{"BBBB","bbb"}
        );
        v.add(toolAction1);

        toolAction1 = new ToolAction("122",
          "Auxiliar class uuyuy",
          "/JBProjects/PInt/classes/images/icons/ENTIcon.gif",
          "exp.reflection.method.Auxiliar",
          "methodToInvoke",
          null
        );
        v.add(toolAction1);

        toolAction1 = new ToolAction("7236",
          "Auxiliar class 3",
          "/JBProjects/PInt/classes/images/icons/ENTIcon.gif",
          "exp.reflection.method.Auxiliar",
          "doA",
          new String[]{"CCCC","ccc"}
        );
        v.add(toolAction1);

        for(int i=0; i<v.size(); i++){
          mb.add((Component)v.get(i));
        }


        JButton button = new JButton("muda");
        button.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
           changeComp();
            for(int i=0; i<v.size(); i++){
              mb.remove((Component)v.get(i));
            }

         }
        });
        button.setBounds(100,50,50,20);

        f.getContentPane().setLayout(null);
        f.getContentPane().add(mb);
        f.getContentPane().add(button);
        f.setSize(400,200);
        f.show();

  }

  public static void changeComp(){

  }


}
