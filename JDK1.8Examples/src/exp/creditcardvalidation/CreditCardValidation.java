package exp.creditcardvalidation;


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
public class CreditCardValidation {
  public CreditCardValidation() {
  }


  /**
   *
   * @param type String
   * @param ccnum String
   * @return boolean
   *
   * @todo OPTIMIZE IT ...
   */
  public static boolean isValidCreditCard(String type, String ccnum) {

    String re = null;

    if (type.equals("Visa")) {
      // Visa: length 16, prefix 4, dashes optional.
      re = "/^4\\d{3}-?\\d{4}-?\\d{4}-?\\d{4}$/";

    } else {
      if (type.equals("MC")) {
        // Mastercard: length 16, prefix 51-55, dashes optional.
        re = "/^5[1-5]\\d{2}-?\\d{4}-?\\d{4}-?\\d{4}$/";

      } else {
        if (type.equals("Disc")) {
          // Discover: length 16, prefix 6011, dashes optional.
          re = "/^6011-?\\d{4}-?\\d{4}-?\\d{4}$/";

        } else {
          if (type.equals("AmEx")) {
            // American Express: length 15, prefix 34 or 37.
            re = "/^3[4,7]\\d{13}$/";

          } else {
            if (type.equals("Diners")) {
              // Diners: length 14, prefix 30, 36, or 38.
              re = "/^3[0,6,8]\\d{12}$/";
            }
          }
        }
      }
    }

    if (re == null) {
      return false;
    }

    // Remove all dashes for the checksum checks to eliminate negative numbers
    ccnum = ccnum.replaceAll("-", "");

    // Checksum ("Mod 10")
    // Add even digits in even length strings or odd digits in odd length strings.
    int checksum = 0;
    for (int i = (2 - (ccnum.length() % 2)); i <= ccnum.length(); i += 2) {
      checksum += Integer.parseInt("" + ccnum.charAt(i - 1));
    }

    // Analyze odd digits in even length strings or even digits in odd length strings.
    for (int i = (ccnum.length() % 2) + 1; i < ccnum.length(); i += 2) {
      int digit = Integer.parseInt("" + ccnum.charAt(i - 1)) * 2;

      if (digit < 10) {
        checksum += digit;
      } else {
        checksum += (digit - 9);
      }
    }

    if ((checksum % 10) == 0) {
      return true;
    } else {
      return false;
    }
  }
}
