/**
 * <p>
 * Classname: jdk8examples.optional.NewClass
 * </p>
 *
 * <p>Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel: +351 22 940 2000
 * <br>
 * Fax: +351 22 948 5428
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package jdk8examples.optional;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;

//import javax.validation.constraints.NotNull;



/**
 * <p>
 * Description:
 *
 * From: http://java.dzone.com/articles/optional-java-8-cheat-sheet
 *
 * </p>
 *
 * Created on Jun 18, 2014, 12:53:11 PM
 */
public class OptionalTests {

  final Optional<String> opt;
  String x =
      //null;
      "XXXXXXXXXxxxxxxxxxXXXXXXXXX";

  public OptionalTests(){

    // In the first case Optional must contain not null value and will throw an exception if null
    // is passed. ofNullable() will either return empty or present (set) Optional. empty() always
    // return empty Optional, corresponding to null. It?s a singleton because Optional<T> is immutable.
    //opt = Optional.of(x);
    opt = Optional.ofNullable(x);
    //opt = Optional.empty();


    System.out.println("ifPresent");
    // ifPresent() - do something when Optional is set
    opt.ifPresent(s -> print(s));
    //opt.ifPresent(this::print);
    // The latter syntax (method reference) can be used when lambda argument (String x) matches
    // function formal parameters.


    System.out.println("filter");
    // filter() - reject (filter out) certain Optional values. Sometimes you want to perform some
    // action not only when a reference is set but also when it meets certain condition.
    opt.filter(x -> x.contains("Xx")).ifPresent(this::print);
    // Optional.filter() that turns present (set) Optional to empty Optional if underlying value
    // does not meet given predicate.
    // This is equivalent to more imperative:
    if (opt.isPresent() && opt.get().contains("Xx")) {
      print(opt.get());
    }


    System.out.println("map");
    // map() - transform value if present. Very often you need to apply some transformation on a
    // value, but only if it?s not null (avoiding NullPointerException):
    opt.map(String::trim).filter(t -> t.length() > 1).ifPresent(this::print);
    // This becomes tricky. Optional.map() applies given function on a value inside Optional -
    // but only if Optional is present. Otherwise nothing happens and empty() is returned.
    // Remember that the transformation is type-safe - look at generics here:
    // Optional<String>  opt = //...
    // Optional<Integer> len = opt.map(String::length);
    // If Optional<String> is present Optional<Integer> len is present as well, wrapping length of
    // a String. But if optwas empty, map() over it does nothing except changing generic type.


    System.out.println("orElse/orElseGet");
    // At some point you may wish to unwrap Optional and get a hold of real value inside. But you
    // can?t do this if Optional is empty. With Optional we can say:
    int len = opt.map(String::length).orElse(-1);
    System.out.println("\tlen: "+len);
    // There is also a version that accepts Supplier<T> if computing default value is slow,
    // expensive or has side-effects:
    len = opt.map(String::length).orElseGet(() -> slowDefault());  //orElseGet(this::slowDefault)
    System.out.println("\tlen: "+len);


    System.out.println("flatMap");
    // Imagine you have a function that does not accept null but may produce one.
    // Using it is a bit cumbersome:
    String similarOrNull = x != null? findSimilar(x) : null;
    // With Optional it is a bit more straighforward:
    Optional<String> similar = opt.map(this::findSimilar);
    // If the function we map() over returns null, the result of map() is an empty Optional.
    // Otherwise it?s the result of said function wrapped with (present) Optional. So far so good
    // but why do we return null-able value if we have Optional?
    // public Optional<String> tryFindSimilar(String s)
    // Our intentions are clear but using map() fails to produce correct type. Instead we use flatMap():
    Optional<Optional<String>> bad = opt.map(this::tryFindSimilar);
    similar =  opt.flatMap(this::tryFindSimilar);
    // Do you see double Optional<Optional<...>>? Definitely not what we want. If you are mapping
    // over a function that returns Optional, use flatMap instead. Here is a simplified implementation
    // of this function:
    // public <U> Optional<U> flatMap(Function<T, Optional<U>> mapper) ...


    System.out.println("orElseThrow");
    // lazily throw exceptions on empty Optional. Often we would like to throw an exception if
    // value is not available:
    // public char firstChar(String s) ...
    // This whole method can be replaced with the following idiom:
    opt.filter(s -> !s.isEmpty()).map(s -> s.charAt(0)).orElseThrow(IllegalArgumentException::new);
    // We don?t want to create an instance of exception in advance because creating an exception
    // has significant cost.


    //
    //
    // Bigger example
    //
    System.out.println("Bigger example");
    // Imagine we have a Person with an Address that has a validFrom date. All of these can be null.
    // We would like to know whether validFrom is set and in the past:
    // private boolean validAddress(/*Null*/ Person person) ... see method and the commented code
    // Quite ugly and defensive. Alternatively but still ugly:
    // returnperson != null &&
    // person.getAddress() != null &&
    // person.getAddress().getValidFrom() != null &&
    // person.getAddress().getValidFrom().isBefore(now());
    // Now imagine all of these (person, getAddress(), getValidFrom()) are Optionals of appropriate
    // types, clearly indicating they may not be set:
    // class Person ..., class Address ..., class Instant ...
    // Suddenly the computation is much more streamlined:
    // Is it more readable? Hard to tell. But at least it?s impossible to produce NullPointerException
    // when Optional is used consistently.
    // return person.flatMap(
    //            Person::getAddress).flatMap(
    //                Address::getValidFrom).filter(x -> x.before(now())).isPresent();


    //
    // Converting Optional<T> to List<T>
    //  sometimes like to think about Optional as a collection having either 0 or 1 elements.
    // This may make understanding ofmap() and flatMap() easier. Unfortunately Optional doesn?t
    // have toList() method, but it?s easy to implement one:
    // public static<T> List<T> toList(Optional<T> option) ...
    // Or less idiomatically:
    // public static<T> List<T> toListSimple(Optional<T> option) ...
    // But why limit ourselves to List<T>? What about Set<T> and other collections? Java 8 already
    // abstracts creating arbitrary collection via Collectors API, introduced for Streams. The API
    // is hideous but comprehensible:
    // public static<R, A, T> R collect(Optional<T> option, Collector<? superT, A, R> collector) ...
    // We can now say:
    // import static java.util.stream.Collectors.*;
    final List<String> list = collect(opt, java.util.stream.Collectors.toList());
    final Set<String>  set  = collect(opt, java.util.stream.Collectors.toSet());

    System.out.println("List<String> list = collect(opt, java.util.stream.Collectors.toList()):");
    for (String string : list) {
      System.out.println("\t"+string);
    }
    System.out.println("Set<String>  set  = collect(opt, java.util.stream.Collectors.toSet()):");
    for (String string : set) {
      System.out.println("\t"+string);
    }


    // Summary
    // Optional<T> is not nearly as powerful as Option[T] in Scala (but at least it doesn?t allow
    // wrapping null). The API is not as straightforward as null-handling and probably much slower.
    // But the benefit of compile-time checking plus readability and documentation value of Optional
    // used consistently greatly outperforms disadvantages. Also it will probably replace nearly
    // identical com.google.common.base.Optional<T> from Guava
    //
    // 1 - from theoretical point of view both maybe and sequence abstractions are monads, that?s
    // why they share some functionality

    System.out.println("-------------------------------------------------------------------------\n\n");
  }

  public void print(String s) {
    System.out.println(s);
  }

  public int slowDefault() {
    return 0;
  }

  public String findSimilar(/*@NotNull*/ String s){
    return "findSimilar";
  }

  public Optional<String> tryFindSimilar(String s)  {
    return Optional.ofNullable("tryFindSimilar");
  }

  public char firstChar(String s) {
    if (s != null && !s.isEmpty()) {
      return s.charAt(0);
    } else {
      throw new IllegalArgumentException();
    }
  }

  private boolean validAddress(/*Null*/ Optional<Person> person) {
    return person.flatMap(Person::getAddress).flatMap(Address::getValidFrom).filter(i -> i.isBefore(now())).isPresent();

//    if (person != null) {
//      if (person.getAddress() != null) {
//        final Instant validFrom = person.getAddress().getValidFrom();
//        return validFrom != null && validFrom.isBefore(now());
//      } else {
//        return false;
//      }
//    } else {
//      return false;
//    }

    //return true; // just for this to compile
  }

  public static long now(){
    return System.currentTimeMillis();
  }

  public static <T> List<T> toList(Optional<T> option) {
    return option.map(Collections::singletonList).orElse(Collections.emptyList());
  }

  public static <T> List<T> toListSimple(Optional<T> option) {
    if (option.isPresent()) {
      return Collections.singletonList(option.get());
    } else {
      return Collections.emptyList();
    }
  }

  public static <R, A, T> R collect(Optional<T> option, Collector<? super T, A, R> collector) {
    final A container = collector.supplier().get();
    option.ifPresent(v -> collector.accumulator().accept(container, v));
    return collector.finisher().apply(container);
  }


  public static void main(final String[] args){
    final OptionalTests clazz = new OptionalTests();
  }
}


class Person {
  private final Optional<Address> address = Optional.ofNullable(new Address());
  public Optional<Address> getAddress() {
    return address;
  }
}
class Address {
  private final Optional<Instant> validFrom = Optional.ofNullable(new Instant());
  public Optional<Instant> getValidFrom() {
    return validFrom;
  }
}
class Instant {
  public boolean isBefore(long lT) {
    return lT < System.currentTimeMillis();
  }
}
