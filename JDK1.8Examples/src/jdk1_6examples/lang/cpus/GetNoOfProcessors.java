/**
 * <p>
 * Classname:  jdk1_6examples.lang.cpus.GetNoOfProcessors
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Engº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */
package jdk1_6examples.lang.cpus;

public class GetNoOfProcessors {

  public static void main(String[] args) {
    System.out.println("No. of processors found in the system: " + getNoOfProcessors());
  }

  /**
   * Gets the no. of processors.
   * @return The no. of processors
   */
  public static int getNoOfProcessors() {
    int noOfProcessors = 0;
    try {
      Runtime runtime = Runtime.getRuntime();
      Class runtimeClass = runtime.getClass();
      java.lang.reflect.Method availProcessorsMethod =
          runtimeClass.getMethod("availableProcessors", null);
      noOfProcessors =
          ((Integer) availProcessorsMethod.invoke(runtime, null)).intValue();

    } catch (Throwable e) {
      // Ignore this exception as this method is only supported in JDK > 1.4
    }
    return noOfProcessors;
  }
}