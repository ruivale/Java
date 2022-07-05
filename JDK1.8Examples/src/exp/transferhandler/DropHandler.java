package exp.transferhandler;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import java.io.File;
import java.io.IOException;

import java.net.URL;

import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.TransferHandler;
import javax.swing.text.JTextComponent;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class DropHandler extends TransferHandler {
  public boolean importData(JComponent component, Transferable transferable) {
    DataFlavor[] flavors = transferable.getTransferDataFlavors();
    JTextComponent source = (JTextComponent) component;

    try {
      if (canImportFiles(flavors)) {
        importFiles(transferable, source);
      } else if (canImportStrings(flavors)) {
        importStrings(transferable, source);
      } else {
        return false;
      }

      return true;
    } catch (UnsupportedFlavorException e) {
      source.setText(e.getMessage());
    } catch (IOException e) {
      source.setText(e.getMessage());
    }

    return false;
  }

  private void importFiles(Transferable transferable, JTextComponent source)
    throws UnsupportedFlavorException, IOException {
    List files = (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);

    for (int i = 0; i < files.size(); i++) {
      source.setText(source.getText() + "\n" + ((File) (files.get(i))).toURL());
    }
  }

  private void importStrings(Transferable transferable, JTextComponent source)
    throws UnsupportedFlavorException, IOException {
    String string = (String) transferable.getTransferData(DataFlavor.stringFlavor);
    String message = null;
    File file = new File(string);

    if (file.exists()) {
      message = file.toURL().toString();
    } else {
      try {
        URL url = new URL(string);
        url.getContent();
        message = url.toString();
      } catch (IOException e) {
        message = "Could not convert string to URL " + string;
      }
    }

    source.setText(source.getText() + "\n" + message);
  }

  public boolean canImport(JComponent c, DataFlavor[] flavors) {
    return canImportFiles(flavors) || canImportStrings(flavors);
  }

  private boolean canImportFiles(DataFlavor[] flavors) {
    return Arrays.asList(flavors).contains(DataFlavor.javaFileListFlavor);
  }

  private boolean canImportStrings(DataFlavor[] flavors) {
    return Arrays.asList(flavors).contains(DataFlavor.stringFlavor);
  }
}
