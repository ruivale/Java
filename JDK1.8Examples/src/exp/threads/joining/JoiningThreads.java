package exp.threads.joining;


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
public class JoiningThreads {


  public JoiningThreads() {

    final Thread t1 = new Thread(new Runnable() {
      public void run() {
        System.out.println("t1 t1 t1 t1 t1 t1 t1 t1 t1 t1 t1 ");
        javax.swing.JOptionPane.showMessageDialog(null, "Continuar?");
        System.out.println(
            "ENDING t1 ENDING t1 ENDING t1 ENDING t1 ENDING t1 ENDING t1 ");
      }
    });
    t1.start();

    final Thread t2 = new Thread(new Runnable() {
      public void run() {
        System.out.println("t2 t2 t2 t2 t2 t2 t2 t2 t2 t2 t2 ");

        try {
          t1.join(5000);
        } catch (Exception ex) {
          ex.printStackTrace();
        }

        System.out.println(
            "ENDING t2 ENDING t2 ENDING t2 ENDING t2 ENDING t2 ENDING t2 ");
      }
    });
    t2.start();

  }

  public static void main(String a[]) {
    new JoiningThreads();
  }
}
