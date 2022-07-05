/**
 * <p>
 * Classname: exp.fonts.FontsCapableOfShowingACertainChar
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel: +351 22 940 2000
 * <br>
 * Fax: +351 22 948 5428
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */
package exp.fonts;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Hashtable;


/**
 * <p>
 * Description:
 * </p>
 * <p>
 * Created on Jul 21, 2016, 4:13:33 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class FontsCapableOfShowingACertainChar {

  static final Font defaultFont = new Font("Arial Unicode MS", Font.BOLD, 48);

  static private Font[] allFonts;

  static final char sampleChineseCharacter = '?';//'\u4F60';  // ni3 as in ni3 hao3


  /**
   *
   */
  public static void loadFonts() {

    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();

    allFonts = env.getAllFonts();
    int nFonts = allFonts != null ? allFonts.length : 0;
    String[] fontNames = new String[nFonts];
    Hashtable fontMap = new Hashtable();
    String currentFamily = "";
    int j = 0;

    for (int i = 0; i < nFonts; i++) {
      Font font = allFonts[i];

      if (font.canDisplay(sampleChineseCharacter)) {
        System.out.println(allFonts[i]);

        currentFamily = font.getFamily();
        Object key = fontMap.put(currentFamily, font);

        if (key == null) {
          // The currentFamily hasn't been seen yet.
          fontNames[j] = currentFamily;
          j++;
        }
      }
    }

    String tmp[] = fontNames;
    fontNames = new String[j];
    System.arraycopy(tmp, 0, fontNames, 0, j);

  }


  public static void main(String[] args) {
    FontsCapableOfShowingACertainChar.loadFonts();
  }
}
