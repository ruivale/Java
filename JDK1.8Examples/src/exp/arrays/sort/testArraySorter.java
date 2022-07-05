package exp.arrays.sort;


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
public class testArraySorter {
  //~ Static fields/initializers ///////////////////////////////////////////////

  /** .. */
  public static final ASCIIComparer asciiComparer = new ASCIIComparer();

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   */
  public static void main(String[] args) {
    if(args.length==0) {
      System.out.println("give me some args to sort");
    } else {
      ArraySorter.sort(args, asciiComparer);

      for(int i = 0; i<args.length; i++) {
        System.out.println(args[i]);
      }
    }
  }

  //~ Inner Classes ////////////////////////////////////////////////////////////

  public static class ASCIIComparer
      implements ArraySorter.Comparer {
    public int compare(
      Object a,
      Object b) {
      return ((String)a).compareTo((String)b);
    }
  }
}
