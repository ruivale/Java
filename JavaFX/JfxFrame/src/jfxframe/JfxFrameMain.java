/**
 * <p>
 * Classname: jfxframe.JfxFrameMain
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2014 Efacec Engenharia e Sistemas, S.A.
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
package jfxframe;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javax.swing.SwingUtilities;

import org.capcaval.ermine.jfx.JfxFrame;
import org.capcaval.ermine.jfx.JfxFrameCallBack;
import org.capcaval.ermine.jfx.JfxTools;

public class JfxFrameMain {

  public static void main(String[] args) {
    System.out.println("Main thread name : " + Thread.currentThread().getName());

    //JfxTools.invokeAndWait(null);
    
    // create a instance of frame
    JfxFrame frame = JfxFrame.factory.newInstance(
        "JfxFrameMain",
        10, 10, 500, 300,
        new JfxFrameCallBack() {
          @Override
          public void notifyFrameCreated(JfxFrame frame) {
				// check out that it is running
            // inside the correct thread
            System.out.println("Jfx thread name : " + Thread.currentThread().getName()+
                               " Is EDT?"+SwingUtilities.isEventDispatchThread());
            frame.setbackgroundColor(Color.DARKBLUE);

            // create and add a label
            Label label = new Label("Hello!");
            label.setTextFill(Color.WHITE);
            frame.setView(label);
          }
        });
    // display it
    frame.display();
  }
}
