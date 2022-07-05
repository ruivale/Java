/**
 * <p>
 * Classname: jdk8examples.predicate.PredicateSample
 * </p>
 *
 * <p>Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
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

package jdk8examples.predicate;


import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jan 7, 2016, 6:10:48 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class PredicateSample {

  private boolean voolean;

  private static boolean isTrue(final PredicateSample ps){
    return ps.voolean;
  }

  private static boolean isFalse(final PredicateSample ps){
    return !ps.voolean;
  }


  static List<PredicateSample> filter(List<PredicateSample> list, Predicate<PredicateSample> p){
    final List<PredicateSample> l = new ArrayList<>(16);

    for (PredicateSample pd: list) {
      if(p.test(pd)){
        l.add(pd);
      }
    }

    return l;
  }

  public interface Predicate<T>{
    public boolean test(T t);
  }




  public static void main(String[] args) {
    final List<PredicateSample> list = new ArrayList<>();
    list.add(new PredicateSample());
    list.add(new PredicateSample());
    list.add(new PredicateSample());
    list.add(new PredicateSample());

    //
    //
    PredicateSample.filter(list, PredicateSample::isTrue);
    //
    //
  }
}
