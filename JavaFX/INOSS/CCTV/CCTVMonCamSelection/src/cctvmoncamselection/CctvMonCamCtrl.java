
package cctvmoncamselection;


import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * 
 */
public class CctvMonCamCtrl extends AnchorPane implements Initializable {
  
    @FXML
    TextField txtFMon;
    @FXML
    TextField txtFCam;
    @FXML
    PasswordField password;
    @FXML
    Button bttSet;
    @FXML
    Label lblErrorMessage;  
    @FXML
    Label lblMon;  
    @FXML
    Label lblCam;  
    
    
    /**
     * 
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      bttSet.setText("Set");
      txtFMon.setText("");
      txtFCam.setText("");
      txtFMon.setPromptText("Mon");
      txtFCam.setPromptText("Cam");        
      
      lblMon.setText("Mon:");
      lblCam.setText("Cam:");
    }
    
    
    /**
     * 
     * @param event 
     */
    public void setMonCam(ActionEvent event) {
      System.out.println("CctvMonCamCtrl.setMonCam(..): " + this.txtFCam.getText() + " -> " + this.txtFMon.getText());
    }    
}


