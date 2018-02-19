package jdk8examples.covariant;


/**
 *
 * @author 2334
 */
public class Vehicle implements CustomCloneable {

  private final String model;

  public Vehicle(String model) {
    this.model = model;
  }

  @Override
  public Vehicle customClone() {
    return new Vehicle(this.model);
  }

  public String getModel() {
    return this.model;
  }



  
  public static void main(String[] args) {
    Vehicle originalVehicle = new Vehicle("Corvette");
    Vehicle clonedVehicle = originalVehicle.customClone();
    System.out.println(clonedVehicle.getModel());
  }
}
