/**
 * <p>
 * Classname: exp.java.lang.ref.ReferencesExample
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC Eng. Sistemas
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
package exp.java.lang.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;


public class ReferenceExample {

  private String status = "Hi I am active";


  /**
   *
   * @return
   */
  public String getStatus() {
    return status;
  }

  /**
   *
   * @param status
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   *
   * @return
   */
  @Override
  public String toString() {
    return "ReferenceExample [status=" + status + "]";
  }

  /**
   *
   */
  public void strongReference() {
    ReferenceExample ex = new ReferenceExample();
    System.out.println(ex);
  }

  /**
   *
   */
  public void softReference() {
    SoftReference<ReferenceExample> ex = new SoftReference<>(getRefrence());
    System.out.println("Soft refrence :: " + ex.get());
  }

  /**
   *
   */
  public void weakReference() {
    int counter = 0;
    WeakReference<ReferenceExample> ex = new WeakReference<>(getRefrence());

    while (ex.get() != null) {
      counter++;
      System.gc();
      System.out.println("Weak reference deleted  after:: " + counter +" - "+ ex.get());
    }
  }

  /**
   *
   * @throws InterruptedException
   */
  public void phantomReference() throws InterruptedException {
    final ReferenceQueue queue = new ReferenceQueue();
    PhantomReference<ReferenceExample> ex = new PhantomReference<>(getRefrence(), queue);

    System.gc();
    queue.remove();
    System.out.println("Phantom reference deleted  after");
  }

  /**
   *
   * @return
   */
  private ReferenceExample getRefrence() {
    return new ReferenceExample();
  }




  public static void main(String[] args) {
    ReferenceExample ex = new ReferenceExample();
    ex.strongReference();
    ex.softReference();
    ex.weakReference();

    try {
      ex.phantomReference();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
