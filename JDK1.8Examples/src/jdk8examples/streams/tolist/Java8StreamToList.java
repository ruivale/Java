package jdk8examples.streams.tolist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Java8StreamToList {

  private static void streamToList() {

    ArrayList<String> myList = null;
    List<String> listOfStream = null;
    Stream<String> streamOfString = null;

    /** *** Converting Stream to List Using The 'Collectors.toList()' Method **** */
    streamOfString = Stream.of("Code", "Logic", "Program", "Review", "Skill");
    listOfStream = streamOfString.collect(Collectors.toList());
    System.out.println("Example #1 - Java8 Stream to List?= " + listOfStream);

    /** *** Java8 Stream to ArrayList Using 'Collectors.toCollection' Method **** */
    streamOfString = Stream.of("One", "Two", "Three", "Four", "Five");
    listOfStream = streamOfString.collect(Collectors.toCollection(ArrayList::new));
    System.out.println("Example #2 - Java8 Stream to List?= " + listOfStream);

    /** *** IIIrd Way To Convert Stream To List In Java8 **** */
    streamOfString = Stream.of("Abc", "Cde", "Efg", "Jkd", "Res");
    myList = new ArrayList<String>();
    streamOfString.forEach(myList::add);
    System.out.println("Example #3 - Java8 Stream to List?= " + myList);

    /** *** IVth Way To Convert Parallel Stream to List **** */
    streamOfString = Stream.of("Java", "C++", "JavaScript", "Scala", "Python");
    myList = new ArrayList<String>();
    streamOfString.parallel().forEachOrdered(myList::add);
    System.out.println("Example #4 - Java8 Stream to List?= " + myList);

    /** *** Vth Way Of Creating List From Stream In Java. But Unfortunately This Creates Array Of Objects As Opposed To The Array Of String **** */
    streamOfString = Stream.of("James", "Jarry", "Jasmine", "Janeth");
    Object[] arrayOfString = streamOfString.toArray();
    List<Object> listOfNames = Arrays.asList(arrayOfString);
    System.out.println("Example #5 - Java8 Stream to List?= " + listOfNames);

    /** *** Can We Convert The Above Method To String[] Instead Of Object[], 'Yes' By Using Overloaded Version Of 'toArray()' As Shown Below **** */
    streamOfString = Stream.of("Rectangle", "Square", "Circle", "Oval");
    String[] arrayOfShapes = streamOfString.toArray(String[]::new);
    listOfStream = Arrays.asList(arrayOfShapes);
    System.out.println("Modified Version Of Example #5?= " + listOfStream);
  }

  public static void main(String[] args) {
    streamToList();
  }
}
