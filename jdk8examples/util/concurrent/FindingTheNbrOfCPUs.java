package jdk8examples.util.concurrent;

import java.util.concurrent.ForkJoinPool;

/**

@author 2334
*/
public class FindingTheNbrOfCPUs {

  public static void main(final String[] args){
    System.out.println(ForkJoinPool.commonPool());
  }
}
