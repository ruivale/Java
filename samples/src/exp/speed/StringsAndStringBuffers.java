package exp.speed;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class StringsAndStringBuffers {

  public StringsAndStringBuffers() {
    String s = "StringStringStringStringStringStringString";
    StringBuffer sb = new StringBuffer("StringStringStringStringStringStringString");

    long t0 = System.currentTimeMillis();

    String _s = s+s;

    long t1 = System.currentTimeMillis();

    String _sb = sb.append(sb).toString();

    long t2 = System.currentTimeMillis();

    System.out.println("new StrStr(): "+(t1-t0)+".");
    System.out.println("new Short(): "+(t2-t1)+".");


    teste();

  }
  public void teste(){
    for (int i = 0; i < 20; i++) {
      System.out.println("i -> "+i+";");
      if(i==15)return;

    }

  }

  public static void main(String[] args) {
    StringsAndStringBuffers stringsAndStringBuffers1 = new StringsAndStringBuffers();
  }
}