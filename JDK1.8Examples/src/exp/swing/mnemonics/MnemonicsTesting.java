package exp.swing.mnemonics;

import javax.swing.*;

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
public class MnemonicsTesting {
  public MnemonicsTesting() {
    JButton b = new JButton("ABCD");
    b.setMnemonic('B');
    System.out.println("mne="+b.getMnemonic());
  }

  public static void main(String[] args) {
    MnemonicsTesting mnemonicstesting = new MnemonicsTesting();
  }
}
