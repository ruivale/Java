/**
 * <p>
 * Classname: package jdk1_6examples.javax.swing.jdialog.HeadlessDialog
 * </p>
 *
 * <p>Copyright: Copyright (c) 2008 EFACEC SE
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
package jdk1_6examples.javax.swing.jdialog;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - rui dot vale at efacec dot pt
 * @version $Revision: 1.1 $
 */
public class HeadlessDialog {

  public static void main(final String[] args) {
    final javax.swing.JFrame f = new javax.swing.JFrame("tlc");
    //f.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
    f.setBounds(0,0,1280,700);
    f.setLayout(null);

    final javax.swing.JPanel jP = new javax.swing.JPanel();
    jP.setBounds(20, 30, 1200, 170);
    jP.setBackground(java.awt.Color.BLACK);

    f.add(jP);
    f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);

    final javax.swing.JDialog jD = new javax.swing.JDialog(f, "", true);
    jD.setUndecorated(true);
    jD.getRootPane().setWindowDecorationStyle(javax.swing.JRootPane.FRAME);
    jD.setResizable(false);

    jD.setBackground(java.awt.Color.BLACK);
    jD.getContentPane().setBackground(java.awt.Color.RED);

    jD.setSize(1200, 170);
    jD.setLocationRelativeTo(f);
    jD.addComponentListener(new java.awt.event.ComponentAdapter() {
      public void componentMoved(final java.awt.event.ComponentEvent e) {
        System.out.println("componentMoved");
      }
    });

    jD.addMouseListener(
        new java.awt.event.MouseAdapter() {
          public void mouseClicked(final java.awt.event.MouseEvent me) {
            System.out.println("mouseClicked");
            if(me.getModifiers() == java.awt.event.InputEvent.BUTTON3_MASK) {
              if(me.getY() < jD.getContentPane().getY()) {
                System.out.println("me.getY() < jD.getContentPane().getY())");
              } else {
                System.out.println(
                    "NOT NOT me.getY() < jD.getContentPane().getY())");
              }
            } else {
              System.out.println(
                  "NOT NOT me.getModifiers() == java.awt.event.InputEvent.BUTTON3_MASK");
            }
          }

          public void mousePressed(final java.awt.event.MouseEvent e) {
            System.out.println("mousePressed");
          }
        });
    jD.setVisible(true);
  }
}
