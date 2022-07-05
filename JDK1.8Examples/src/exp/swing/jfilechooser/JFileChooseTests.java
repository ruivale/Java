package exp.swing.jfilechooser;


import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.plaf.basic.BasicFileChooserUI;


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
public class JFileChooseTests {
  public JFileChooseTests() {

    final JFrame f = new JFrame(" J F C ");
    f.setBounds(100, 100, 400, 500);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton b = new JButton("clica-me");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent e) {
        //File file = new File(".");
        JFileChooser jfc = new JFileChooser(".");

        final BasicFileChooserUI ui =
          (BasicFileChooserUI)jfc.getUI();

        //ui.getChangeToParentDirectoryAction().setEnabled(false);
        //ui.getGoHomeAction().setEnabled(false);
        //ui.getNewFolderAction().setEnabled(false);

        //jfc.remove(ui.getAccessoryPanel());

        //hideButtons(jfc.getComponents());
        hideNavigation(jfc.getComponents());
        //hideNavigationAll(jfc.getComponents());


        File fileInit = new File("AHAHAHAHAAHA.txt");
        jfc.setSelectedFile(fileInit);
        jfc.setDefaultLocale(new java.util.Locale("fr"));
        jfc.setLocale(new java.util.Locale("fr"));
        int intSaveDialog = jfc.showSaveDialog(f);

        System.out.println("intSaveDialog=" + intSaveDialog);

        if (intSaveDialog == 0) {
          File file = jfc.getSelectedFile();

          System.out.println("file.getAbsolutePath()=" + file.getAbsolutePath());
        }

        int intOpenDialog = jfc.showOpenDialog(f);

        System.out.println("intOpenDialog=" + intOpenDialog);

      }
    });
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(b);

    f.setVisible(true);
  }



  int count = 0;
  JComboBox jcb;
  ListCellRenderer oldRenderer;
  void hideNavigationAll(Component[] comp){

    for (int x = 0; x < comp.length; x++){
      if (comp[x] instanceof JPanel){
        hideNavigation(((JPanel)comp[x]).getComponents());
      }
      else if(comp[x] instanceof JComboBox && count == 0){
        jcb = ((JComboBox)(comp[x]));
        oldRenderer = jcb.getRenderer();
        jcb.setRenderer(new ListCellRenderer(){
          public Component getListCellRendererComponent(JList list,
                                                        Object value,
                                                        int index,
                                                        boolean isSelected,
                                                        boolean cellHasFocus){
            if (index != 0){
              return oldRenderer.getListCellRendererComponent
               (list, value, index, isSelected, cellHasFocus);
            }
            else{
              return new JLabel(" "); // erase the parent item
            }
          }
        });
        ++count;
      }
    } // for( ... )
  } // hideNavigation


  /**
   *
   * @param comp Component[]
   */
  public void hideNavigation(Component[] comp) {
    int count = 0;
    for (int x = 0; x < comp.length; x++) {
      if (comp[x] instanceof JPanel){
        hideNavigation(((JPanel) comp[x]).getComponents());

      }else{
        if (comp[x] instanceof JComboBox && count == 0) {
          ((JComboBox) comp[x]).setEnabled(false);
          //((JComboBox) comp[x]).setVisible(false);
          count++;
        } else {
          if (comp[x] instanceof JLabel &&
              ((JLabel) comp[x]).getText().equals("Look In:")) {
            ((JLabel) comp[x]).setEnabled(false);
            //((JLabel) comp[x]).setVisible(false);
          } else {
            if (comp[x] instanceof JButton && ((JButton) comp[x]).getIcon() != null) {
              ((JButton) comp[x]).setEnabled(false);
              //((JButton) comp[x]).setVisible(false);
            }
          }
        }
      }
    }
  }

  /**
   *
   * @param comp Component[]
   */
  public void hideButtons(Component[] comp) {
    for (int x = 0; x < comp.length; x++) {
      if (comp[x] instanceof JPanel)
        hideButtons(((JPanel) comp[x]).getComponents());
      else
        if (comp[x] instanceof JButton) {
          if (((JButton) comp[x]).getIcon() != null)
            ((JButton) comp[x]).setVisible(false);
        } else
          if (comp[x] instanceof JToggleButton) {
            ((JToggleButton) comp[x]).setVisible(false);
          }
    }
  }

  public static void main(String[] args) {
    JFileChooseTests f = new JFileChooseTests();
  }
}
