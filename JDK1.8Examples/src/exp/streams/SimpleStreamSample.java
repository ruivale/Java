/**
 * <p>
 * Classname:  exp.streams.SimpleStreamSample
 * </p>
 *
 * <p>Copyright: Copyright (c) 2019 EFACEC SE
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

package exp.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class SimpleStreamSample {

 /**
  * The SimpleStreamSample default constuctor.
  */
  public SimpleStreamSample(){
    
    final List<String> list = new ArrayList<>(4);
    list.add("David");
    list.add("António");
    list.add("João");
    list.add("Guilherme");
    
    List<String> result =
      list
        .stream()
        .map(SimpleStreamSample::concat)
        .filter(name -> name.startsWith("J"))
        .sorted()
        .collect(Collectors.toList());
    
    
    result.forEach(System.out::println);
  }

  private static String concat(final String s){
    return s + s;
  }
  
  public static void main(final String[] args){
    final SimpleStreamSample clazz = new SimpleStreamSample();
  }
}
