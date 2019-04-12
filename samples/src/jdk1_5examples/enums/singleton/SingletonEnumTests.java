
package jdk1_5examples.enums.singleton;


public class SingletonEnumTests {

  public static void main(final String[] args){
    final SingletonEnum singletonEnum = SingletonEnum.INSTANCE;
    final SingletonEnum singletonEnum2 = SingletonEnum.getInstance();
  }
}
