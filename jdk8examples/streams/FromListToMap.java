/**
 * <p>
 * Classname: jdk8examples.streams.FromListToMap
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

package jdk8examples.streams;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jan 29, 2016, 12:11:37 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class FromListToMap {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(FromListToMap.class.getName());


 /**
  * The FromListToMap default constructor.
  */
  public FromListToMap(){
    List<Workshop> workshops =
        Arrays.asList(
            new Workshop("bootstrap", 21),
            new Workshop("effective", 42)
        );

    Map<String, Integer> workshopsMap = workshops.
        stream().
        collect(Collectors.toMap(Workshop::getName, Workshop::getAttendance));

    assertThat(workshopsMap.size(), is(2));
    assertThat(workshopsMap.get("bootstrap"), is(21));
    assertThat(workshopsMap.get("effective"), is(42));

  }


  public static void main(final String[] args){
    final FromListToMap clazz = new FromListToMap();
  }
}

class Workshop {

    private String name;
    private int attendance;

    public Workshop(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
    }

    public String getName() {
        return name;
    }

    public int getAttendance() {
        return attendance;
    }
}