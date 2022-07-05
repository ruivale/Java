package exp.swing.jfilechooser;
/* third workable version that has solved the intrinsic listeners problem */
/* 22 Feb. 2006 by hiwa */
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class NavLimitFc3{
  File limitedRoot;
  File[] parents;
  JFrame frame;
  Container con;
  JFileChooser jfc;
  NavLimitedFsv3 nlf;
  FileView oldFv;
  int count = 0;
  int parentStrata = 1;
  int si;
  JComboBox jcb;
  ListCellRenderer oldRenderer;

  public NavLimitFc3(File top){
    limitedRoot = top;

    frame = new JFrame("JFileChooser with limited navigation test - 3");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    con = frame.getContentPane();

    parents = getParents(limitedRoot);
    parentStrata = parents.length;

    jfc = new JFileChooser(limitedRoot);
    jfc.setFileSystemView(nlf = new NavLimitedFsv3(limitedRoot, parents));
    oldFv = jfc.getFileView();
    jfc.setFileView(new LimitedFileView3(oldFv, limitedRoot, nlf));

    hideNavigation(jfc.getComponents(), parentStrata);

    con.add(jfc, BorderLayout.CENTER);
    frame.pack();
    frame.setVisible(true);
  }

  File[] getParents(File vroot){
    File parent;
    File[] pfs;
    int n = 0;
    String s = null;
    String rf = null;

    try{
      File f = vroot.getCanonicalFile();
      s = f.toString();
      n = countSepChar(s);
    }
    catch (Exception e){
      e.printStackTrace();
    }

    pfs = new File[n];
    int p = 0;
    for (int i = 0; i < pfs.length; ++i){
      int q = s.indexOf(File.separatorChar, p);
      if (q != -1){
        if (i == 0){
          ++q; // we shoud get [C:\] or [/] as system root
        }
        rf = s.substring(0, q);
        if (i != 0){
          ++q; // go beyond \ or /
        }
        p = q;
      }
      else{ // last iteration of this for loop
        rf = s;
      }
      parent = new File(rf);
      try{
        pfs[i] = parent.getCanonicalFile();
      }
      catch (Exception e){
        e.printStackTrace();
      }
    }
    return pfs;
  }

  int countSepChar(String fs){
    int c = 0;
    int p = 0;

    while((p = fs.indexOf(File.separatorChar, p)) != -1){
      ++c;
      ++p; // next search start
    }
    return c;
  }

  // adapted from Michael's method of the same name
  void hideNavigation(Component[] comp, final int ps){
    for (int x = 0; x < comp.length; x++){
      if (comp[x] instanceof JPanel){
        hideNavigation(((JPanel)comp[x]).getComponents(), ps);
      }
      else if(comp[x] instanceof JComboBox && count == 0){
        jcb = ((JComboBox)(comp[x]));

        ItemListener[] ila = jcb.getItemListeners();
        final ItemListener oldIL = ila[0];
        jcb.removeItemListener(oldIL);

        ActionListener[] ala = jcb.getActionListeners();
        final ActionListener oldAL = ala[0];
        jcb.removeActionListener(oldAL);

        jcb.addItemListener(new ItemListener(){
          public void itemStateChanged(ItemEvent ie){
            if (jcb.getSelectedIndex() >= ps){
              oldIL.itemStateChanged(ie);
            }
          }
        });

        jcb.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent ae){
            if (jcb.getSelectedIndex() >= ps){
              oldAL.actionPerformed(ae);
            }
          }
        });

        oldRenderer = jcb.getRenderer();
        final Object vtop = jcb.getItemAt(ps); // virtual root item
        jcb.setRenderer(new ListCellRenderer(){
          public Component getListCellRendererComponent(JList list,
                                                        Object value,
                                                        int index,
                                                        boolean isSelected,
                                                        boolean cellHasFocus){
            si = list.getSelectedIndex();
            if (index == -1 && si < ps){ // for parents ...
              jfc.setCurrentDirectory(limitedRoot); // goto virtual root
              // render virtual root list item
              return oldRenderer.getListCellRendererComponent
               (list, vtop, index, isSelected, cellHasFocus);
            }
            else{
              return oldRenderer.getListCellRendererComponent
               (list, value, index, isSelected, cellHasFocus);
            }
          }
        });
        ++count;
      }
    } // for( ... )
  } // hideNavigation()

  public static void main(String[] args){
    String vroot = ".";
    if (args.length > 0){
      vroot = args[0];
    }
    Locale.setDefault(Locale.US);
    new NavLimitFc3(new File(vroot));
  }
}

class LimitedFileView3 extends FileView{
  FileView old;
  File root;
  FileSystemView fsv;

  public LimitedFileView3(FileView ofv, File vr, FileSystemView fv){
    old = ofv;
    root = vr;
    fsv = fv;
  }

  public Boolean isTraversable(File f){
    return fsv.isTraversable(f);
  }
}

class NavLimitedFsv3 extends FileSystemView{
  File root, parent, canonRoot;
  File[] roots = new File[1];
  ArrayList parentsList;

  public NavLimitedFsv3(File r, File[] prnts){
    parentsList = new ArrayList(Arrays.asList(prnts));
    root = r;
    roots[0] = root;
    try{
      canonRoot = r.getCanonicalFile();
      parent = canonRoot.getParentFile();
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
      if (parentsList.contains(dir.getCanonicalFile())){
        d = canonRoot;
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
    Boolean b = null;

    try{
      if (parentsList.contains(f.getCanonicalFile())){
        b = Boolean.FALSE;
      }
      else{
        b = super.isTraversable(f);
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
      return b;
  }

  public File[] getRoots(){
    return roots;
  }

  public boolean isRoot(File f){
    boolean retval = false;

    try{
      if (f.getCanonicalFile().equals(canonRoot)){
        retval = true;
      }
      else{
        retval = false;
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return retval;
  }
} // class NavLimitedFsv3
