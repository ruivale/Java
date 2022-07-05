package exp.generator;


/**
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c)
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author unascribed
 * @version 1.0
 */
public class PWDGenerator {
  //~ Methods ////////////////////////////////////////////////////////////////
  /**
   * DOC
   *
   * @param minLength
   * @param maxLength
   *
   * @return
   */
  public String generatePWD(
    int minLength,
    int maxLength) {
    java.util.Random rand = new java.util.Random(System.currentTimeMillis());
    String           pass = "";
    String           pwd  = "abcdefghijklmnopqrstuvwxyz";
    pwd += pwd.toUpperCase();
    pwd += "1234567890";

//    pwd += "!@#$%";


    char[] pwdChars = new char[pwd.length()];

    for(int i = 0; i<pwd.length(); i++) {
      pwdChars[i] = pwd.charAt(i);

    }

    pwdChars = shuffleCharachters(pwdChars);

    int length = randomInt(minLength, maxLength);
    for(int i = 0; i<length; i++) {
      pass += pwdChars[rand.nextInt(pwdChars.length-1)];
    }

    return pass;
  }

  /**
   * DOC
   *
   * @param args
   */
  public static void main(String[] args) {
    PWDGenerator pwdgen = new PWDGenerator();
    int          i = 0;
    while(i<1) {
      System.out.println(pwdgen.generatePWD(6, 8));
      i++;
    }
  }

  /**
   * DOC
   *
   * @param min
   * @param max
   *
   * @return
   */
  public int randomInt(
    int min,
    int max) {
    java.util.Random rand = new java.util.Random(System.currentTimeMillis());
    int              diff = Math.abs(max-min);
    return min+rand.nextInt(diff);
  }

  /**
   * DOC
   *
   * @param characters
   *
   * @return
   */
  public char[] shuffleCharachters(char[] characters) {
    java.util.Random rand = new java.util.Random(System.currentTimeMillis());

    for(int j = 0; j<characters.length; j++) {
      int  from        = rand.nextInt(characters.length-1);
      char old = characters[j];
      characters[j]      = characters[from];
      characters[from]   = old;
    }

    return characters;
  }
}
