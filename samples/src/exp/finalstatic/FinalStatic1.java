package exp.finalstatic;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

public class FinalStatic1 {
    private static String myString;
    private static boolean myStringIsSet = false;
    public FinalStatic1(String s) {
        if (!myStringIsSet) {
            myString = s;
            myStringIsSet = true;
        }
    }
    public static String getMyString() {
        return myString;
    }
    public static void main(String[] s) {
        new FinalStatic1("first");
        System.out.println(FinalStatic1.getMyString());
        new FinalStatic1("second");
        System.out.println(FinalStatic1.getMyString());
    }
}
