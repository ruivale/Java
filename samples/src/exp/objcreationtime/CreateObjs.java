package exp.objcreationtime;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

public class CreateObjs {

  long beforeShort, afterShort;
  String _string;
  short _short = 1;

  public CreateObjs() {

    beforeShort = System.currentTimeMillis();
    new Short(_short).toString();
    afterShort = System.currentTimeMillis();
    System.out.println("afterShort:"+afterShort+", beforeShort:"+beforeShort+".");
//    System.out.println("afterShort - beforeShort: "+(afterShort - beforeShort)+".");

    beforeShort = System.currentTimeMillis();
    _string = ""+_short;
    afterShort = System.currentTimeMillis();
    System.out.println("afterString:"+afterShort+", beforeString:"+beforeShort+".");
//    System.out.println("afterString - beforeString: "+(afterShort - beforeShort)+".");

  }
  public static void main(String[] args) {
    CreateObjs createObjs1 = new CreateObjs();
  }
}