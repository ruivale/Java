package exp.file.icon;


import java.io.File;
import javax.swing.filechooser.FileSystemView;
import javax.swing.Icon;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;


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
public class GetFileSystemIcons {

  private static Icon obtainBigFileSystemIcon() {
    File file = null;
    String extension = "doc";

    try {
      //Create a temporary file with the specified extension
      file = File.createTempFile("icon", "." + extension);

      sun.awt.shell.ShellFolder shellFolder = sun.awt.shell.ShellFolder.
                                              getShellFolder(file);
      Icon icon = new ImageIcon(shellFolder.getIcon(true));

      //Delete the temporary file
      file.delete();

      return icon;

    } catch (Exception e) {
      e.printStackTrace();

      return null;
    }
  }

  private static Icon obtainSmallFileSystemIcon() {
    File file = null;
    String extension = "doc";

    try {
      //Create a temporary file with the specified extension
      file = File.createTempFile("icon", "." + extension);

      FileSystemView view = FileSystemView.getFileSystemView();
      Icon icon = view.getSystemIcon(file);

      //Delete the temporary file
      file.delete();

      return icon;

    } catch (IOException ioe) {
      ioe.printStackTrace();

      return null;
    }
  }


  public static void main(final String[] args) {
    JFrame f = new JFrame("icons");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(new JLabel("icon",
                                      obtainBigFileSystemIcon(),
                                      JLabel.HORIZONTAL),
                           BorderLayout.CENTER);
    f.setBounds(100, 100, 600, 500);
    //f.pack();
    f.setVisible(true);

  }

}
