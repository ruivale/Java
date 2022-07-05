package exp.swing.jcolorchooser;

import exp.images.saving.SavingImgs;
import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.Window;
import java.awt.Frame;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.event.ChangeListener;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;

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
public class ChooseColorAndSaveImgs {

  /** Default icon size */
  private static final int INT_ICON_SIZE = 16;

  /** The color chooser */
  private final JColorChooser jcc = new JColorChooser();

  /** The icons panel. Must be visible to correctly save images ... */
  private SavingImgs savingImgs;


  /**
   * Displays the JColorChooser and allows the user to choose a color.
   * After choosing a color, a preview can be displayed
   * (Container->showColorChooser(..)).
   * When the preview container is shown, a button is provided to save the
   * images. If no preview is shown, then this implementation dont work. It relies
   * uppon the paint(..) method, so the component must be <b>VISIBLE</b>.
   *
   * @param comp Component the parent component of this JColorChooser
   * @param strSaveDir String the destination dir.
   * @param strName String the used to name the images. ("strName"_disable."strImageType")
   * @param strImageType String jpg or png
   * @param strColorChooserTitle String the color chooser window title.
   * @param strSaveButtonText String the saving button text
   *
   * @return Container a contains the images preview and the saving button.
   */
  public Container showColorChooser(
      final Window comp,
      final String strSaveDir,
      final String strName,
      final String strImageType,
      final String strColorChooserTitle,
      final String strSaveButtonText) {

    final Color colorChoosen = jcc.showDialog(comp,
                                              strColorChooserTitle,
                                              Color.WHITE);
    final SavingImgs savingImgs;
    final JPanel panel = new JPanel(new GridLayout(1,2,0,0));
    panel.setBackground(Color.WHITE);
    panel.setForeground(Color.WHITE);

    if(colorChoosen != null){
      savingImgs = new SavingImgs(strSaveDir,
                                  strName,
                                  strImageType,
                                  colorChoosen,
                                  INT_ICON_SIZE,
                                  INT_ICON_SIZE);
      panel.add(savingImgs);

      final JButton jButtonSave = new JButton(strSaveButtonText);
      jButtonSave.addActionListener(new ActionListener() {
        public void actionPerformed(final ActionEvent e) {
          savingImgs.saveImages();
        }
      });
      panel.add(jButtonSave);

    }
    return panel;
  }











  /**
   *
   * @return Container
   */
  public Container createColorChooser(
      final String strSaveDir,
      final String strName,
      final String strImageType) {

    final JPanel panel = new JPanel(new BorderLayout());
    final ColorSelectionModel colorSelectionModel = jcc.getSelectionModel();
    colorSelectionModel.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent e){
        System.out.println("stateChanged="+jcc.getColor()+".");

       // myPreviewPanel.changeColor(jcc.getColor());

      }
    });

    if(jcc != null){
      savingImgs = new SavingImgs(strSaveDir,
                                  strName,
                                  strImageType,
                                  null,
                                  INT_ICON_SIZE,
                                  INT_ICON_SIZE);
      panel.add(savingImgs);
    }

    return panel;
  }


  /**
   *
   * @return Color
   */
  public Color getColor(){
    Color color = null;
    if(jcc!=null){
      color = jcc.getColor();
    }
    return color;
  }

















  public static void main(String[] args) {
    final JFrame f_ = new JFrame();
    final ChooseColorAndSaveImgs c = new ChooseColorAndSaveImgs();
    f_.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container cont = c.showColorChooser(f_, "d:/temp", "XPTO", "jpg", "Cor", "Gravar imgs");

    f_.getContentPane().setLayout(new BorderLayout());
    f_.getContentPane().add(cont, BorderLayout.CENTER);
    f_.pack();
    f_.setLocation(100,100);
    f_.setVisible(true);

  }
}
