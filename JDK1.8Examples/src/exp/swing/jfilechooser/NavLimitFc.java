package exp.swing.jfilechooser;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class NavLimitFc{
  File limitedRoot;
  JFrame frame;
  Container con;
  JFileChooser jfc;
  NavLimitedFsv nlf;
  int count = 0;
  JComboBox jcb;
  ListCellRenderer oldRenderer;

  public NavLimitFc(File top){
    limitedRoot = top;

    frame = new JFrame("JFileChooser with limited navigation test");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    con = frame.getContentPane();

    jfc = new JFileChooser(limitedRoot);
    jfc.setFileSystemView(nlf = new NavLimitedFsv(limitedRoot));
    jfc.setFileView(new FileView(){
      public Boolean isTraversable(File f){
        return nlf.isTraversable(f);
      }
    });

    hideNavigation(jfc.getComponents());

    con.add(jfc, BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
  }


  // adapted from Michael's method of the same name
  void hideNavigation(Component[] comp){
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
  } // hideNavigation()

  public static void main(String[] args){
    Locale.setDefault(Locale.US);
    //Thread.setDefaultUncaughtExceptionHandler(new SilentExceptionHandler());
    new NavLimitFc(new File("."));
  }
}

class NavLimitedFsv extends FileSystemView{
  File root, parent;
  File[] roots = new File[1];

  public NavLimitedFsv(File r){
    root = r;
    roots[0] = root;
    try{
      File f = r.getCanonicalFile();
      parent = f.getParentFile();
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }

  public File createNewFolder(File containingDir) throws IOException{
    return getFileSystemView().createNewFolder(containingDir);
  }

  public File getParentDirectory(File dir){
    File d = null;

    try{
      if (dir.getCanonicalFile().equals(root.getCanonicalFile())){
        d = dir;
      }
      else{
        d = super.getParentDirectory(dir);
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return d;
  }

  public Boolean isTraversable(File f){
    if (f.equals(parent)){
      throw new UnsupportedOperationException();
      // return false;  // get NPE!
    }
    else{
      return super.isTraversable(f);
    }
  }
} // class NavLimitedFsv

/**
class SilentExceptionHandler implements Thread.UncaughtExceptionHandler {

  public void uncaughtException(Thread t, Throwable e) {
    if (! (e instanceof UnsupportedOperationException)){
      throw (RuntimeException)e;
    }
  }
}
/**/
