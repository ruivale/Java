
package jdk1_5examples.enums.singleton;


public enum SingletonEnum {
  INSTANCE;

  SingletonEnum(){
  }

  public static SingletonEnum getInstance(){
    return INSTANCE;
  }
}
