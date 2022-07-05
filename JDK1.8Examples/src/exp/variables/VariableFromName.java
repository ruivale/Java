package exp.variables;


import java.lang.reflect.*;


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
public class VariableFromName {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  public String thirdValue  = "Hello world";

  /** .. */
  public double firstValue  = 3.1426;

  /** .. */
  public int secondValue = 42;

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   */
  public static void main(String[] args) {
    VariableFromName vbn = new VariableFromName();

    String           lookingForValue;

    vbn.showValue("firstValue");
    vbn.showValue("secondValue");
    vbn.showValue("thirdValue");
  }

  /**
   * Insert doc ...
   *
   * @param lookingForValue  Insert doc ...
   */
  public void showValue(String lookingForValue) {
    try {
      Field thisField     = VariableFromName.class.getField(lookingForValue);
      Class thisClassType = thisField.getType();
      System.out.print("The variable '" + lookingForValue + "' contains ");

      if(thisClassType.toString()
                          .equals("double")) {
        System.out.println(thisField.getDouble(this));
      } else if(thisClassType.toString()
                                 .equals("int")) {
        System.out.println(thisField.getInt(this));
      } else if(thisClassType.toString()
                                 .equals("class java.lang.String")) {
        System.out.println(thisField.get(this));
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
