package pt.intro.java.afirst;

/**
 * <p>
 * Description: a test case for the Plane class.
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 */
public class PlaneTest {

  
  public static void main(String[] args) {
    // creating planes
    // creating "airbus/350"...
    Plane planeAirbus350 = new Plane();
    planeAirbus350.strMaker = "Airbus";
    planeAirbus350.strModel = "350";
    planeAirbus350.nSeats = 375;

    // creating "boeing/787"...
    Plane planeBoeing787 = new Plane("Boeing", "787", 290);

    // invoking "takeOff" method...
    planeAirbus350.takeOff();
    planeBoeing787.takeOff();
  }
}
