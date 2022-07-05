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
public class StringCompareTo {
  public static void main(String[] args) {
    String sTODAY = "TODAY";
    String stoday = "today";

    int iTODAYtoday = sTODAY.compareTo(stoday);
    int itodayTODAY = stoday.compareTo(sTODAY);

    System.out.println(sTODAY+".compareTo("+stoday+")="+iTODAYtoday+".");
    System.out.println(stoday+".compareTo("+sTODAY+")="+itodayTODAY+".");

  }
}
