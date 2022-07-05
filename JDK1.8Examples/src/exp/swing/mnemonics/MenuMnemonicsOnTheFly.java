package exp.swing.mnemonics;

import javax.swing.*;
import java.util.*;
import java.awt.*;

/**
 * <p>Title: </p>
 *
 * <p>Description:
 * This is a utility class which can be used to set the mnemonics of all the
 * items in a menubar on the fly.
 * </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class MenuMnemonicsOnTheFly {

  /**
   * This method sets mnemonic of all the items in the menubar
   *
   * @param mb JMenuBar
   */
  public static void setMnemonics(JMenuBar mb) {
    MenuElement[] MenuElements = mb.getSubElements();
    int num = MenuElements.length;
    if (0 == num) {
      return;
    }

    setMnemonicForTopLevel(mb);
    for (int i = 0; i < num; ++i) {
      if (!(MenuElements[i] instanceof JMenu)) {
        continue;
      }
      setMnemonic((JMenu) MenuElements[i]);
    }
  }

  /**
   * This method sets mnemonic of all the top level menus in the menubar
   *
   * @param mb JMenuBar
   */
  private static void setMnemonicForTopLevel(JMenuBar mb) {
    MenuElement[] MenuElements = mb.getSubElements();
    int num = MenuElements.length;
    if (0 == num) {
      return;
    }

    char mnemonics[] = new char[num];

    for (int i = 0; i < num; ++i) {
      if (!(MenuElements[i] instanceof JMenu)) {
        continue;
      }
      JMenu menu = (JMenu) MenuElements[i];

      String text = menu.getText();
      char mnemonic = getMnemonic(text, mnemonics);
      if (' ' != mnemonic) {
        menu.setMnemonic(mnemonic);
        mnemonics[i] = mnemonic;
      }
    }
  }

  /**
   * This method sets mnemonic of the menuitems and submenus of a given menu.
   * To set different Mnemonic of each item it firsr checks first character of
   * the all the words then the second , third and so on till a unique
   * character is found.
   *
   * @param jm JMenu
   */
  private static void setMnemonic(JMenu jm) {
    Component mcomps[] = jm.getMenuComponents();
    int num = mcomps.length;

    if (0 == num) {
      return;
    }

    char mnemonics[] = new char[num];

    for (int i = 0; i < num; ++i) {
      if (!(mcomps[i] instanceof JMenuItem)) {
        continue;
      }
      JMenuItem menuitem = (JMenuItem) mcomps[i];

      String text = menuitem.getText();
      char mnemonic = getMnemonic(text, mnemonics);
      if (' ' != mnemonic) {
        menuitem.setMnemonic(mnemonic);
        mnemonics[i] = mnemonic;
      }
      if (menuitem instanceof JMenu) {
        setMnemonic((JMenu) menuitem);
      }
    }
  }

  /**
   *
   * @param text String
   * @param mnemonics char[]
   * @return char
   */
  private static char getMnemonic(String text, char[] mnemonics) {
    Vector words = new Vector();
    StringTokenizer t = new StringTokenizer(text);
    int maxsize = 0;

    while (t.hasMoreTokens()) {
      String word = (String) t.nextToken();
      if (word.length() > maxsize) {
        maxsize = word.length();
      }
      words.addElement(word);
    }
    words.trimToSize();

    for (int i = 0; i < maxsize; ++i) {
      char mnemonic = getMnemonic(words, mnemonics, i);
      if (' ' != mnemonic) {
        return mnemonic;
      }
    }

    return ' ';
  }

  /**
   *
   * @param words Vector
   * @param mnemonics char[]
   * @param index int
   * @return char
   */
  private static char getMnemonic(Vector words, char[] mnemonics, int index) {
    int numwords = words.size();

    for (int i = 0; i < numwords; ++i) {
      String word = (String) words.elementAt(i);
      if (index >= word.length()) {
        continue;
      }

      char c = word.charAt(index);
      if (!isMnemonicExists(c, mnemonics)) {
        return c;
      }
    }
    return ' ';
  }

  /**
   *
   * @param c char
   * @param mnemonics char[]
   * @return boolean
   */
  private static boolean isMnemonicExists(char c, char[] mnemonics) {
    int num = mnemonics.length;
    for (int i = 0; i < num; ++i) {
      if (mnemonics[i] == c) {
        return true;
      }
    }
    return false;
  }
}
