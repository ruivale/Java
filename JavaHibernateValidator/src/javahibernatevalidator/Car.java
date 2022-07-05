
package javahibernatevalidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;

public class Car {

    @NotNull
    private String manufacturer;

    @NotNull
    @Size(min = 2, max = 14)
    private String licensePlate;

    @Min(2)
    private int seatCount;

    public Car(String manufacturer, String licencePlate, int seatCount) {
        this.manufacturer = manufacturer;
        this.licensePlate = licencePlate;
        this.seatCount = seatCount;
    }

    //getters and setters ...
    
    
    public static void main(String[] args) {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      Validator validator = factory.getValidator();

      Car car = new Car(null, "DD-AB-123", 4);

      Set<ConstraintViolation<Car>> violations = validator.validate(car);
      
      for (ConstraintViolation<Car> v : violations) {
        System.out.println(
          v.getRootBean().getClass().getName() + " - " +
          v.getPropertyPath() + ": " + 
          v.getMessage()+"  [" + 
          v + "]");
      }
    }
}