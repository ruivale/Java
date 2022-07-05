package jdk1_6examples.jdbc.connpool;

import java.io.IOException;
import java.sql.Connection;
import javax.swing.SwingUtilities;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class TestConnectionPool {
  
  public static void main(final String[] args) throws Exception{
    new Thread(new Runnable() {
      public void run() {
        final Connection c1 = DBConnectionManager.getInstance().getConnection("oracle");
        System.out.println("c1"+(c1!=null));
        DBConnectionManager.getInstance().freeConnection("oracle", c1);
        holdOnAMinute();
      }
    }).start();

    new Thread(new Runnable() {
      public void run() {
        final Connection c2 = DBConnectionManager.getInstance().getConnection("oracle");
        System.out.println("c2"+(c2!=null));
      }
    }).start();

    new Thread(new Runnable() {
      public void run() {
        try {Thread.sleep(2340);} catch (InterruptedException ex) {}
        final Connection c3 = DBConnectionManager.getInstance().getConnection("oracle");
        System.out.println("c3?"+(c3!=null));
        DBConnectionManager.getInstance().freeConnection("oracle", c3);
        holdOnAMinute();
      }
    }).start();

    new Thread(new Runnable() {
      public void run() {
        try {Thread.sleep(540);} catch (InterruptedException ex) {}
        final Connection c4 = DBConnectionManager.getInstance().getConnection("oracle");
        System.out.println("c4"+(c4!=null));
      }
    }).start();

    new Thread(new Runnable() {
      public void run() {
        final Connection c5 = DBConnectionManager.getInstance().getConnection("oracle");
        System.out.println("c5"+(c5!=null));
      }
    }).start();

    new Thread(new Runnable() {
      public void run() {
        try {Thread.sleep(1340);} catch (InterruptedException ex) {}
        final Connection c6 = DBConnectionManager.getInstance().getConnection("oracle");
        System.out.println("c6"+(c6!=null));
        DBConnectionManager.getInstance().freeConnection("oracle", c6);
        holdOnAMinute();
      }
    }).start();

    new Thread(new Runnable() {
      public void run() {
        final Connection c7 = DBConnectionManager.getInstance().getConnection("oracle");
        System.out.println("c7"+(c7!=null));
      }
    }).start();

    new Thread(new Runnable() {
      public void run() {
        final Connection c8 = DBConnectionManager.getInstance().getConnection("oracle");
        System.out.println("c8"+(c8!=null));
      }
    }).start();

    new Thread(new Runnable() {
      public void run() {
        try {Thread.sleep(1340);} catch (InterruptedException ex) {}
        final Connection c9 = DBConnectionManager.getInstance().getConnection("oracle");
        System.out.println("c9"+(c9!=null));
        DBConnectionManager.getInstance().freeConnection("oracle", c9);
        holdOnAMinute();
      }
    }).start();

    new Thread(new Runnable() {
      public void run() {
        final Connection c10 = DBConnectionManager.getInstance().getConnection("oracle");
        System.out.println("c10"+(c10!=null));
      }
    }).start();

    

    
    SwingUtilities.invokeLater(new Runnable(){
      public void run() {
        new javax.swing.JFrame();
      }
    });
  }

  private static void holdOnAMinute(){
    final byte[] b = new byte[1024];
    try {
      System.in.read(b);
    } catch (IOException iOException) {
    }
    System.out.println("ENDING ...");

  }
}
