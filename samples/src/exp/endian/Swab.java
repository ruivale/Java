package exp.endian;


public class Swab {

  public static final int swabInt(int v) {
    return (v >>> 24) | (v << 24) | ((v << 8) & 0x00FF0000) |
    ((v >> 8) & 0x0000FF00);
  }

  public static void main(String[] argv) {
    // before 0x01020304
    // after  0x04030201
    int v = 0x01020304;
    System.out.println("before : 0x" + Integer.toString(v, 16));
    System.out.println("after  : 0x" + Integer.toString(
        swabInt(v),
        16));
  }
}
