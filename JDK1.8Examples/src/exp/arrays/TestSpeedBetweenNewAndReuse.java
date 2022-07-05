package exp.arrays;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class TestSpeedBetweenNewAndReuse {
  final int CICLES = 10000000;
  final int I_CMD_LEN = 8;

  public TestSpeedBetweenNewAndReuse() {
    int[] cmd = new int[I_CMD_LEN];
    int k = 0;

    long before = System.currentTimeMillis();

    for (int j = 0; j < CICLES; j++) {
      cmd[k++] = 1;

      if (k == I_CMD_LEN) {
        /***/
        for (int i = 0; i < I_CMD_LEN; i++) {
          cmd[i] = -1;
        }
        /**/

        /***
        cmd = new int[I_CMD_LEN];
        /**/

        k = 0;
      }
    }

    long after = System.currentTimeMillis();

    System.out.println("It took "+(after-before)+".");

  }

  public static void main(String[] args) {
    TestSpeedBetweenNewAndReuse testspeedbetweennewandreuse = new
        TestSpeedBetweenNewAndReuse();
  }
}
