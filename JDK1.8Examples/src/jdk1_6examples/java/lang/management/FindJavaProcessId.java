/**
 * <p>
 * Classname:  jdk1_6examples.java.lang.management.FindJavaProcessId
 * </p>
 *
 * <p>Copyright: Copyright (c) 2011 Efacec Engenharia e Sistemas, S.A.
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
package jdk1_6examples.java.lang.management;

import java.util.logging.Level;
import java.util.logging.Logger;


import sun.management.VMManagement;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author 2334
 */
public class FindJavaProcessId {

  public static void main(String[] args) {

    try {
      RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
      Field jvmField = runtimeMXBean.getClass().getDeclaredField("jvm");
      jvmField.setAccessible(true);
      VMManagement vmManagement = (VMManagement) jvmField.get(runtimeMXBean);
      Method getProcessIdMethod = vmManagement.getClass().getDeclaredMethod("getProcessId");
      getProcessIdMethod.setAccessible(true);
      Integer processId = (Integer) getProcessIdMethod.invoke(vmManagement);
      System.out.println("################    ProcessId = " + processId);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
