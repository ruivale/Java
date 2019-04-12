package exp.timearitmetic;

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
 * Copyright: Copyright (c) 2003
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author unascribed
 * @version 1.0
 */
public class RatePerHoursAndMinute {
  /**
   * DOCUMENT ME!
   *
   * @param args DOCUMENT ME!
   */
  public static void main(String[] args) {
    int    intAccessTimeUnits = 0;
    double doubleRate;
    double doublePaidMoney;
    int    intNbrOfTimeUnits = 0;
    int    intMin            = 0;
    int    intHrs            = 0;

    while (true)

      try {
        byte[] b = new byte[1024];
        System.out.println("Paid:");
        System.in.read(b);

        String paid = new String(b);
        doublePaidMoney = Double.parseDouble(paid);

        byte[] c = new byte[1024];
        System.out.println("0-Min 1-Hrs 2-Days 3-Weeks 4-Month:");
        System.in.read(c);

        String rate = new String(c);
        rate.trim();
        intAccessTimeUnits = new Double(rate).intValue();

        byte[] a = new byte[1024];
        System.out.println("Rate per unit:");
        System.in.read(a);
        doubleRate = Double.parseDouble(new String(a));

        double d, doubleAux;

        switch (intAccessTimeUnits) {
          case 0:

            //doubleRate = billingPlansHolder.aaaBillingPlanPricingMin;
            intNbrOfTimeUnits =
              new Double((doublePaidMoney / doubleRate)).intValue();

            // Example: 115 min = 45 min and 1 hrs
            intHrs = intNbrOfTimeUnits / 60;
            intMin = intNbrOfTimeUnits % 60;

            System.out.println(
              "0 - Hours:" + intHrs + ", Minutes:" + intMin + ".");

            break;

          case 1:
            d = doublePaidMoney / doubleRate;
            intHrs = new Double(d).intValue();
            intMin = (int) (60 * (d % intHrs));

System.out.println("1 - Hours:" + intHrs + ", Minutes:" + intMin + ".");

            break;

          case 2:
            d = doublePaidMoney / doubleRate;
            doubleAux = d * 24;
            intHrs = new Double(doubleAux).intValue();
            intMin = (int) (60 * (doubleAux % intHrs));

System.out.println("2 - Hours:" + intHrs + ", Minutes:" + intMin + ".");

            break;

          case 3:
            d = doublePaidMoney / doubleRate;
            doubleAux = d * 24 * 7;
            intHrs = new Double(doubleAux).intValue();
            intMin = (int) (60 * (doubleAux % intHrs));

System.out.println("3 - Hours:" + intHrs + ", Minutes:" + intMin + ".");

            break;

          case 4:
            d = doublePaidMoney / doubleRate;
            doubleAux = d * 24 * 7 * 30;
            intHrs = new Double(doubleAux).intValue();
            intMin = (int) (60 * (doubleAux % intHrs));


System.out.println("4 - Hours:" + intHrs + ", Minutes:" + intMin + ".");

            break;
        }

        System.out.println("##############################################");
      } catch (Exception e) {
        e.printStackTrace(System.out);
      }
  }
}
