/**
 * <p>
 * Classname: package jdk1_6examples.javax.swing.windows.snapper.WindowSnapper
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
package jdk1_6examples.javax.swing.windows.snapper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.InputEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;


public class WindowSnapper
    extends ComponentAdapter {

  private final Component compNotToOverlap;
  private final Rectangle rectIntersect = new Rectangle();
  private final Rectangle rectNotToOverlap = new Rectangle();
  private final Rectangle rectMovableComp = new Rectangle();

  private Component compMovable;
  private Robot robot;
  private PointerInfo pointerInfo;
  private boolean locked = false;
  private int iCompNotToOverlapBottomY  = 0;
  private int nx = 0;
  //private int ny = 0;


  /**
   *
   * @param compNotToOverlap
   */
  public WindowSnapper(
      final Component compNotToOverlap) {

    this.compNotToOverlap = compNotToOverlap;

    try {
      this.robot = new Robot();
      this.pointerInfo = MouseInfo.getPointerInfo();

    } catch(Exception e) {
      this.robot = null;
      this.pointerInfo = null;
    }
  }
  
  @Override
  public void componentShown(ComponentEvent evt) {
    this.componentMoved(evt);
  }

  @Override
  public void componentMoved(ComponentEvent evt) {
    if(locked) {
      return;
    }

    this.compMovable = evt.getComponent();

    if(this.compMovable.isShowing() && this.compNotToOverlap.isShowing()){
      this.rectMovableComp.setLocation(this.compMovable.getLocationOnScreen());
      this.rectMovableComp.setSize(this.compMovable.getSize());

      this.rectNotToOverlap.setLocation(this.compNotToOverlap.getLocationOnScreen());
      this.rectNotToOverlap.setSize(this.compNotToOverlap.getSize());
      //this.rectNotToOverlap.height += this.iHeightGap;

      Rectangle.intersect(
          this.rectMovableComp,
          this.rectNotToOverlap,
          this.rectIntersect);

      if(!this.rectIntersect.isEmpty()){
        this.nx = this.compMovable.getLocationOnScreen().x;
  //      this.ny = MouseInfo.getPointerInfo().getLocation().y;
        //int ny = this.compMovable.getLocationOnScreen().y;

        this.iCompNotToOverlapBottomY =
            this.compNotToOverlap.getLocationOnScreen().y +
            this.compNotToOverlap.getHeight();

      // top
      //if(this.ny < 0 + this.iCompNotToOverlapBottomY ||
        // this.compMovable.getLocationOnScreen().y < 0 + this.iCompNotToOverlapBottomY) {
        this.locked = true;

//        this.ny = this.iCompNotToOverlapBottomY < this.ny?
//          this.ny:
//          this.iCompNotToOverlapBottomY;

        // make sure we don't get into a recursive loop when the
        // set location generates more events
        this.compMovable.setLocation(this.nx, this.iCompNotToOverlapBottomY);
        //this.compMovable.setLocation(this.nx, this.ny);
        this.compMovable.validate();
        this.compMovable.repaint();

        if(this.robot != null /*&& this.pointerInfo != null*/) {
          this.robot.mouseRelease(InputEvent.BUTTON1_MASK);
          //robot.mouseMove(MouseInfo.getPointerInfo().getLocation().x, ny);
          //System.out.println("\trobot.mouseMove(" +
            //  MouseInfo.getPointerInfo().getLocation().x + "," + ny + ");");
        }

        this.locked = false;

      }
    }
  }

  public static void main(String[] args) {
    final JFrame frame = new JFrame("testes ...");
    frame.setLayout(new BorderLayout());

    final JPanel jPanel = new JPanel();
    jPanel.setBackground(Color.BLACK);
    jPanel.setPreferredSize(new Dimension(1600, 170));
    frame.add(jPanel, BorderLayout.NORTH);
    //frame.setBounds(1281, 0, 1600, 1400);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);


    final JDialog diag = new JDialog(frame,"Move me ...", true);
    JLabel label = new JLabel(
        "Tentar mover a janela para cima da área do RADIO ...");
    diag.add(label);
    diag.setBounds(500, 500, 750, 450);
    diag.addComponentListener(new WindowSnapper( jPanel));
    diag.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);



//    new Thread(new Runnable() {
//      int i = 0;
//      public void run() {
//        try {
//          while(true) {
//            i++;
//            Thread.sleep(1000);
//            Rectangle r = diag.getBounds();
//            System.out.println("JDialog bounds: " + r.x + "," + r.y + "," + r.width +
//                "," + r.height + ".");
//
//            if(i % 5 == 0) {
////              System.out.println(
////                  "Try to set the dialog location above the not to overlap panel.");
////              i = 0;
////              diag.setLocation(1400, 50);
////
////              Thread.sleep(750);
////              diag.setVisible(false);
////              diag.setSize(750, 1150);
////              diag.setLocationRelativeTo(frame);
////              Thread.sleep(500);
////              diag.setVisible(true);
//
//              final JDialog d = new JDialog(diag,"Move me ...", true);
//              d.setSize(500, 500);
//              d.setLocationRelativeTo(diag);
//
////              d.addHierarchyListener(new HierarchyListener(){
////                public void hierarchyChanged(final HierarchyEvent e){
////System.out.println("hierarchyChanged("+e.paramString()+").");
////                }
////              });
//
//              new Thread(new Runnable() {
//                public void run() {
//                  try {
//                    Thread.sleep(2500);
//                  } catch(InterruptedException interruptedException) {
//                  }
//
//                  diag.setVisible(false);
//                }
//              }).start();
//
//              d.setVisible(true);
//            }
//
//          }
//        } catch(Exception e) {
//          e.printStackTrace();
//        }
//      }
//    }).start();



    diag.setVisible(true);

  }
}
