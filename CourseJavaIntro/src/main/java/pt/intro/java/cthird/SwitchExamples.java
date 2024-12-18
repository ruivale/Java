/**
 * <p>
 * Classname:  pt.intro.java.cthird.SwitchExamples
 * </p>
 *
 * <p>Copyright: Copyright (c) 2024 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */

package pt.intro.java.cthird;

import java.util.Dictionary;


/**
 * <p>
 * Description:
 * The switch in Java.
 * 
 * </p>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 *
 * @since 241217
 */
public class SwitchExamples {
  
  //<editor-fold defaultstate="collapsed" desc="Enums & classes"> 
  enum PlayerTypes {TENNIS, FOOTBALL, SNOOKER, UNKNOWN;}
  enum Day {SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;}
  
  sealed interface CarClassification permits FourWheels, SixWheels, MuchMoreWheels {}
  enum FourWheels implements CarClassification {CAR, VAN;}
  enum SixWheels implements CarClassification {BIG_VAN, SMALL_TRUCK;}
  enum MuchMoreWheels implements CarClassification {TRUCK, BIG_TRUCK;}
  
  
  class Player {
    private final String strType;
    Player(String strType){this.strType = strType;}
  }
  class TennisPlayer extends Player {
    TennisPlayer(){super("Tennis player");}
  }
  class FootballPlayer extends Player {
    FootballPlayer(){super("Football player");}
  }
  class SnookerPlayer extends Player {
    SnookerPlayer(){super("Snooker player");}
  }
  class UnknownPlayerException extends Exception {
    UnknownPlayerException(String strMessage){
      super(strMessage);
    }
  }
  
  class SportType {}
  class Individual extends SportType{}
  class Team extends SportType{}
  //</editor-fold>
  
  
  
    
  /**
   * Typical old-school example wrapped in a method.
   * If we forget about default, then the code will not compile.
   * 
   * @param playerType
   * @return
   * @throws pt.intro.java.cthird.SwitchExamples.UnknownPlayerException 
   */
  private Player createPlayer(PlayerTypes playerType) throws UnknownPlayerException {
    switch (playerType) {
      case TENNIS:
        return new TennisPlayer();
      case FOOTBALL:
        return new FootballPlayer();
      case SNOOKER:
        return new SnookerPlayer();
      case UNKNOWN:
        throw new UnknownPlayerException("Player type is unknown");
      default:
        throw new IllegalArgumentException("Invalid player type: " + playerType);
    }
  }


  /**
   * Old-school, extremely ugly switch.
   * If we forget about default, then there will be no complaints from the compiler side.
   * In this case, a missing default case may result in a null player.
   * 
   * @param playerType
   * @return
   * @throws pt.intro.java.cthird.SwitchExamples.UnknownPlayerException 
   */
  private Player createPlayerSwitch(PlayerTypes playerType) throws UnknownPlayerException {
    Player player = null;
    
    switch (playerType) {
      case TENNIS:
        player = new TennisPlayer();
        break;
      case FOOTBALL:
        player = new FootballPlayer();
        break;
      case SNOOKER:
        player = new SnookerPlayer();
        break;
      case UNKNOWN:
        throw new UnknownPlayerException("Player type is unknown");
      default:
        throw new IllegalArgumentException("Invalid player type: " + playerType);
    }
    return player;
  }

  
  /**
   * Switch expression written in a new  style (preview in JDK 12).
   * This time, default is not mandatory. We can skip it.
   * The JDK 12 switch is smart enough to signal if switch doesn't cover all possible input values. 
   * This is very useful in the case of Java enum values.
   * 
   * @param playerType
   * @return
   * @throws pt.intro.java.cthird.SwitchExamples.UnknownPlayerException 
   */
  private Player createPlayerNewStyle(PlayerTypes playerType) throws UnknownPlayerException {
    return switch (playerType) {
      case TENNIS ->
        new TennisPlayer();
      case FOOTBALL ->
        new FootballPlayer();
      case SNOOKER ->
        new SnookerPlayer();
      case UNKNOWN ->
        throw new UnknownPlayerException("Player type is unknown");
      // default is not mandatory
      default ->
        throw new IllegalArgumentException("Invalid player type: " + playerType);
    };
  }  
  
  
  /**
   * Switch expressions can have multiple labels separated by a comma.
   * 
   * @param playerType
   * @return
   * @throws pt.intro.java.cthird.SwitchExamples.UnknownPlayerException 
   */
  private SportType fetchSportTypeByPlayerType(PlayerTypes playerType) throws UnknownPlayerException {
    return switch (playerType) {
      case TENNIS, SNOOKER ->
        new Individual();
      case FOOTBALL ->
        new Team();
      default -> 
        throw new UnknownPlayerException("Player type is unknown");
    };
  }
  

  /**
   * An arrow case along with its code to its right is called a switch-labeled rule.
   * When the Java runtime matches any of the labels to the left of the arrow, it runs the code to 
   * the right of the arrow and does not fall through; it does not run any other code in the switch 
   * expression (or statement). If the code to the right of the arrow is an expression, then the 
   * value of that expression is the value of the switch expression.
   * 
   * @return 
   */
  private int getLengthWeekDaysName(Day day) {
    int numLetters = 0;
    
    switch (day) {
      case MONDAY, FRIDAY, SUNDAY ->
        numLetters = 6;
      case TUESDAY ->
        numLetters = 7;
      case THURSDAY, SATURDAY ->
        numLetters = 8;
      case WEDNESDAY ->
        numLetters = 9;
    };

    return numLetters;
  }
  
  
  /**
   * Selector expression is an interface that's been implemented by two enum types.
   * 
   * @param carClassification 
   */
  private void printCarClassification(CarClassification carClassification) {
    switch(carClassification) {      
      case FourWheels.CAR, FourWheels.VAN -> 
        System.out.println("A four wheels vehicle: " + carClassification.toString());
        
      case SixWheels.BIG_VAN, SixWheels.SMALL_TRUCK -> 
        System.out.println("A six wheels vehicle: " + carClassification.toString());      
        
      case MuchMoreWheels.TRUCK, MuchMoreWheels.BIG_TRUCK -> 
        System.out.println("A REALY big vehicle: " + carClassification.toString());
    }
  }
  
  
//  /**
//   * If a selector expression is of type long, float, double, and boolean, then its case labels must 
//   * have the same type as the selector expression or its corresponding boxed type.
//   * 
//   * If you change the case label 0f to 0, you would get the following compile-time error:
//   *    error: constant label of type int is not compatible with switch selector type float
//   * 
//   * @param v 
//   * 
//   * Warning: primitive patterns are a preview feature and are disabled by default. 
//   *          (use --enable-preview to enable primitive patterns)
//   * 
//   *          [preview] primitive patterns are a preview feature and may be removed in a future release.
//   */
//  private void whichFloat(float v) {
//    switch (v) {
//      case 0f ->
//        System.out.println("Zero");
//      case float x when x > 0f && x <= 10f ->
//        System.out.println(x + " is between 1 and 10");
//      case float x ->
//        System.out.println(x + " is larger than 10");
//    }
//  }  
  
  
  
  
  
  
  public static void main(final String[] args){
    final SwitchExamples clazz = new SwitchExamples();
    
    System.out.println("\n\n\nclazz.getLengthWeekDaysName(): "+ clazz.getLengthWeekDaysName(Day.WEDNESDAY)+"\n\n");
  }
}
