
package cctvmoncamselection;


//import com.sun.javafx.scene.control.skin.DatePickerContent;
//import com.sun.javafx.scene.control.skin.DatePickerSkin;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.time.LocalDate;
import java.util.Locale;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * 
 * 
 */
public class CctvMonCamComp extends JPanel {

  private static final String STR_JAVAFX_DATEPICKER_CSS = "css";

  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(CctvMonCamComp.class.getName());

  private JFXPanel fxPanel;
  private Scene scene;



  public CctvMonCamComp(){

    setLayout(new BorderLayout());

    fxPanel = new JFXPanel();
    fxPanel.setBackground(Color.red);

    add(fxPanel, BorderLayout.CENTER);

    Platform.runLater(() -> {
      try {
        scene = createScene();
        fxPanel.setScene(scene);
        
      } catch (Exception e) {
        e.printStackTrace();
      }
    });   
  }
  
  private static Scene createScene() {
    Group root = new Group();
    Scene scene = new Scene(root, javafx.scene.paint.Color.ALICEBLUE);
    
    
    Text text = new Text();
    text.setX(40);
    text.setY(100);
    text.setFont(new Font(25));
    text.setText("Welcome JavaFX!");

    Label label = new Label("Label");
    
    root.getChildren().add(text);
    root.getChildren().add(label);

    return (scene);
  }  

  private Scene createScene_() throws Exception {
    final Group root = new Group();
    scene = new Scene(root, /*W, H,*/ javafx.scene.paint.Color.TRANSPARENT);
    
    
    final CctvMonCamCtrl cctvMonCamCtrl = new CctvMonCamCtrl();    
    root.getChildren().add(cctvMonCamCtrl);

        
    return (scene);
        
        
        

//    ghostDatePicker = new DatePicker();
//    ghostDatePicker.setShowWeekNumbers(false);
//
//    ghostDatePicker.setValue(LocalDate.now());
//
//    ghostDatePicker.setVisible(false);
//
//    DatePickerSkin skin = new DatePickerSkin(ghostDatePicker);
//    calendarNode = (Node)skin.getPopupContent();
//    //calendarNode = (DatePickerContent)skin.getPopupContent();
//
//    root.getChildren().add(calendarNode);
//

//    //scene.getStylesheets().clear();
//
//    System.setProperty("rcss", "D:/Projects/JavaFX/SwingInterop/css/date-picker-readable.css");
//
//    final String strCSS =
//      System.getProperty(STR_JAVAFX_DATEPICKER_CSS) != null &&
//      !System.getProperty(STR_JAVAFX_DATEPICKER_CSS).isEmpty()?
//          new File(System.getProperty(STR_JAVAFX_DATEPICKER_CSS)).toURL().toExternalForm():
//          this.getClass().getResource("date-picker-readable.css").toExternalForm();
//
//    System.out.println("\n\nCSS: "+strCSS+"\n\n");
//
//    scene.getStylesheets().add(strCSS);
//
//
//    return (scene);
  }




  public static void main(final String[] args){
    JFrame frame = new JFrame("CctvMonCamComp");
    frame.getContentPane().add(new CctvMonCamComp());
    //frame.pack();
    frame.setBounds(100,100,350,60);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}


