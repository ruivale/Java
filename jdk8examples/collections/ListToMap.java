package jdk8examples.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class ListToMap {

  public static void main(String[] args) {

    List<Person> people = new ArrayList<>(5);
    people.add(new Person(1, "Mario", 27));
    people.add(new Person(2, "Luigi", 30));
    people.add(new Person(3, "Steve", 20));

    // Converting List to Map in Java8 Using Lambda Expression
    Map<Integer, Person> mapLambdaExpr =
      people.stream().collect(Collectors.toMap(p -> p.getPersonId(), p -> p));
    System.out.println("<------------Iterating Map By Lambda Expression-------------->");
    System.out.println(mapLambdaExpr);

    System.out.println();

    // Converting List to Map in Java8 Using Method Reference
    Map<Integer, Person> mapMethodRef =
      people.stream().collect(Collectors.toMap(Person::getPersonId, Function.identity()));
    System.out.println("<------------Iterating Map By Method Reference--------------->");
    System.out.println(mapMethodRef);

    System.out.println();

    // Duplicate Key Exception
    people.add(new Person(3, "Dave", 25));
    Map<Integer, Person> dupMap = people.stream().collect(Collectors.toMap(p -> p.getPersonId(), p -> p));
    System.out.println("<------------Duplicate Key Exception--------------->");
    System.out.println(dupMap);
  }
}
