package pt.intro.java.afirst;

/**
 * <p>
 * Description: a simple POJO.
 *
 * This holds information associated with planes.
 *
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 */
public class Plane {

  /* the plane's maker */
  String strMaker;
  /* the plane's model */
  String strModel;
  /* the plane's number of seats */
  int nSeats;

  /**
   * Default constructor...
   */
  Plane() {    
  }
  
  /**
   * Constructor with all needed information...
   * 
   * @param strMaker the plane's maker.
   * @param strModel the plane's model.
   * @param nSeats the plane's number of seats.
   */
  Plane(String strMaker, String strModel, int nSeats) {
    this.strMaker = strMaker;
    this.strModel = strModel;
    this.nSeats = nSeats;
  }
 
  /**
   * Will print a message signaling the plane's taking off...
   */
  void takeOff() {
    System.out.println("The plane " + this.toString() + " is taking off...");
  }

  /**
   * Returns this class description in a friendly way.
   *
   * @return String description
   */
  public String toString() {
    return 
        new StringBuffer(32)
          .append(this.strMaker)
          .append("/")
          .append(strModel).append(" with ")
          .append(this.nSeats).append(" seats").toString();
  }
}
