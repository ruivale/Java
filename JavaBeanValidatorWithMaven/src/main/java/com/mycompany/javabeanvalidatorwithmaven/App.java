package com.mycompany.javabeanvalidatorwithmaven;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class App {

  public static void main(String[] args) {
    App app = new App();
    app.demo();
  }

  private void demo() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    System.out.println("");
    // No violations.
    {
      Car car = new Car("Honda", "ABC-789", 4);
      System.out.println("car = " + car);

      Set< ConstraintViolation< Car>> violations = validator.validate(car);
      System.out.format("INFO - Found %d violations.\n", violations.size());
    }

    System.out.println("");

    // 3 violations.
    {
      Car car = new Car(null, "X", 1);
      System.out.println("car = " + car);

      Set< ConstraintViolation< Car>> violations = validator.validate(car);
      System.out.format("INFO - Found %d violations.\n", violations.size());
      violations.forEach(carConstraintViolation -> System.out.println(carConstraintViolation.getMessage()));
    }

    System.out.println("");
  }
}
