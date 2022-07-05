package exp.swing.button.dropdown;


import java.awt.*;
import java.awt.event.*;

import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.*;
import java.beans.PropertyChangeEvent;


// @author  santhosh kumar - santhosh@in.fiorano.com
public abstract class DropDownButton
    extends JButton
    implements ChangeListener,
      PopupMenuListener,
      ActionListener,
      PropertyChangeListener {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  private final JButton arrowButton =
    new JButton(new ImageIcon("d:/jbprojects/exp/arrow.png"));

  /** .. */
  private final JButton mainButton = this;

  /** .. */
  private boolean popupVisible = false;

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new DropDownButton object.
   */
  public DropDownButton() {
    mainButton.getModel()
              .addChangeListener(this);
    arrowButton.getModel()
               .addChangeListener(this);
    arrowButton.addActionListener(this);
    arrowButton.setMargin(new Insets(3, 0, 3, 0));
    mainButton.addPropertyChangeListener("enabled", this); //NOI18N
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /*------------------------------[ ActionListener ]---------------------------------------------------*/
  public void actionPerformed(ActionEvent ae) {
    JPopupMenu popup = getPopupMenu();
    popup.addPopupMenuListener(this);
    popup.show(
      mainButton,
      0,
      mainButton.getHeight());
  }

  /**
   * Insert doc ...
   *
   * @param toolbar  Insert doc ...
   *
   * @return  Insert doc ...
   */
  public JButton addToToolBar(JToolBar toolbar){
      JToolBar tempBar = new JToolBar();
      tempBar.setAlignmentX(0.5f);
      tempBar.setRollover(true);
      tempBar.add(mainButton);
      tempBar.add(arrowButton);
      tempBar.setFloatable(false);
      toolbar.add(tempBar);
      return mainButton;
    }
  /**
   * Insert doc ...
   *
   * @param e  Insert doc ...
   */
  public void popupMenuCanceled(PopupMenuEvent e) {
    popupVisible = false;
  }

  /**
   * Insert doc ...
   *
   * @param e  Insert doc ...
   */
  public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
    popupVisible = false;

    mainButton.getModel()
              .setRollover(false);
    arrowButton.getModel()
               .setSelected(false);
    ((JPopupMenu)e.getSource()).removePopupMenuListener(this); // act as good programmer :)
  }

  /*------------------------------[ PopupMenuListener ]---------------------------------------------------*/
  public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
    popupVisible = true;
    mainButton.getModel()
              .setRollover(true);
    arrowButton.getModel()
               .setSelected(true);
  }

  /*------------------------------[ PropertyChangeListener ]---------------------------------------------------*/
  public void propertyChange(PropertyChangeEvent evt) {
    arrowButton.setEnabled(mainButton.isEnabled());
  }

  /*------------------------------[ ChangeListener ]---------------------------------------------------*/
  public void stateChanged(ChangeEvent e) {
    if(e.getSource()==mainButton.getModel()) {
      if(popupVisible && !mainButton.getModel()
                                        .isRollover()) {
        mainButton.getModel()
                  .setRollover(true);

        return;
      }

      arrowButton.getModel()
                 .setRollover(mainButton.getModel().isRollover());
      arrowButton.setSelected(mainButton.getModel().isArmed() &&
            mainButton.getModel().isPressed());
    } else {
      if(popupVisible && !arrowButton.getModel()
                                         .isSelected()) {
        arrowButton.getModel()
                   .setSelected(true);

        return;
      }

      mainButton.getModel()
                .setRollover(arrowButton.getModel().isRollover());
    }
  }

  /*------------------------------[ Other Methods ]---------------------------------------------------*/
  protected abstract JPopupMenu getPopupMenu();
}
