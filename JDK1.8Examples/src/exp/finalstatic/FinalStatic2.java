package exp.finalstatic;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

public class FinalStatic2 {
    private static String myString;
    public FinalStatic2() {}
    public FinalStatic2(String string) {
        setString(string);
    }
    public static String getString() {
        return myString;
    }
    public static void setString(String newValue) {
        if (myString == null) {
            myString = newValue;
        }
    }

    public static void main(String args[]){
        new FinalStatic2("first");
        System.out.println(FinalStatic2.getString());
        new FinalStatic2("second");
        System.out.println(FinalStatic2.getString());
    }

}
