package exp.strings;


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
public class StringRefs {
  String s = "AHAH";

  public StringRefs() {
    System.out.println("s="+s+". before change(..)");
    change(s);
    System.out.println("s="+s+". after change(..)");
    new S(this);
  }

  private void change(String s){
    s += "BHBH";
    System.out.println("s="+s+". change(..)");
  }

  public String getS(){
    return s;
  }

  public static void main(String s[]){
    new StringRefs();
  }
}

class S {
  S(StringRefs sr){
    String s = sr.getS();
    s += "CHCH";
    System.out.println("s="+s+". S");
  }
}
