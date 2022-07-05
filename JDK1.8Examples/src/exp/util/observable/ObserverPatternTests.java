/**
 * <p>
 * Classname: exp.util.observable.ObserverPatternTests
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
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
package exp.util.observable;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jun 17, 2015, 12:36:28 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class ObserverPatternTests {

  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(ObserverPatternTests.class.getName());

  public static void main(final String[] args) {
    NoticeBoard noticeBoard = new NoticeBoard();
    Employee emp1 = new Employee("emp1");
    Employee emp2 = new Employee("emp2");
    Employee emp3 = new Employee("emp3");
    noticeBoard.addObserver(emp1);
    noticeBoard.addObserver(emp2);
    noticeBoard.addObserver(emp3);
    noticeBoard.updateNoticeBoard(
        "Farewell party for Mr. ABC on 30th April 2012 in the Canteen. All of you are requested to attend the Party");
  }
}

class NoticeBoard extends Observable {

  private String notice;

  public String getNotice() {
    return notice;
  }

  public void updateNoticeBoard(String notice) {
    this.notice = notice;
    setChanged();
    notifyObservers(notice);
  }
}


class Employee implements Observer {
  final String strName;

  public Employee(final String strName) {
    this.strName = strName;
  }

  public void update(Observable o,Object arg) {
    System.out.println("Employee("+this.strName+") - Notice Board Updated: " + arg);
  }
}
