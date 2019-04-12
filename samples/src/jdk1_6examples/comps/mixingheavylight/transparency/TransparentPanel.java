/**
 * <p>
 * Classname:  jdk1_6examples.comps.mixingheavylight.transparency.TransparentPanel
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 EFACEC SE
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
package jdk1_6examples.comps.mixingheavylight.transparency;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import javax.swing.JPanel;


public class TransparentPanel extends JPanel {

  /**
   * Default constructor. Sets the layout manager to JPanel's
   * default layout manager: FlowLayout.
   */
  public TransparentPanel() {
    this(new FlowLayout());
  }

  /**
   * Construct panel with input layout manager.
   *
   * @param mgr The layout manager for the extended JPanel.
   */
  public TransparentPanel(LayoutManager mgr) {

    // set layout manager, if any
    super(mgr);

    // set opaque false so that an isOpaque() == false
    super.setOpaque(false);

    // set mixing cutout -- see AWTUtilitesClass below
    AWTUtilitiesClass.setMixingCutoutShape(this, new Rectangle());
  }

  /**
   * Override setOpaque so that the user cannot change the
   * opacity of this panel, since it is after all suppose to
   * be transparent.
   *
   * @param isOpaque Is this panels should be opaque?
   */
  @Override
  public void setOpaque(boolean isOpaque) {
    // do not allow this to become opaque because it is
    // transparent after all
    }

  /**
   * Overrides paint so that only the children and not this
   * are rendered.
   *
   * @param g
   */
  @Override
  public void paint(Graphics g) {
    paintChildren(g);
  }
}
