package exp.encrypt;


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
public class SimpleEncrypt {
  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param arg  Insert doc ...
   */
  public static void main(String[] arg) {
    new SimpleEncrypt().doIt();
  }

  /**
   * Insert doc ...
   */
  public void doIt() {
    try {
      // quick way to do input from the keyboard, now deprecated...
      java.io.StreamTokenizer Input = new java.io.StreamTokenizer(System.in);

      //
      System.out.print("Input your secret password : ");
      Input.nextToken();

      String secret = new String(encrypt(Input.sval));
      System.out.println("the encrypted result : " + secret);

      boolean ok = true;
      String  s = "";

      while(ok) {
        System.out.print("Now try to enter a password : ");
        Input.nextToken();
        s = new String(encrypt(Input.sval));

        if(secret.equals(s)) {
          System.out.println("You got it!");
          ok = false;
        } else {
          System.out.println("Wrong, try again...!");
        }
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Insert doc ...
   *
   * @param x  Insert doc ...
   *
   * @return  Insert doc ...
   *
   * @throws Exception  Insert doc ...
   */
  static byte[] encrypt(String x)
      throws Exception {
    java.security.MessageDigest d = null;
    d = java.security.MessageDigest.getInstance("SHA-1");
    d.reset();
    d.update(x.getBytes());

    return d.digest();
  }
}
