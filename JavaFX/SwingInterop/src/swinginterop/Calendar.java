/**
 * <p>
 * Classname: swinginterop.Calendar
 * </p>
 *
 * <p>Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich – Ap. 3078
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

package swinginterop;


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
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jun 13, 2017, 3:41:27 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class Calendar extends JPanel {

  private static final String STR_JAVAFX_DATEPICKER_CSS = "css";

  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(Calendar.class.getName());
  private static String STR_DATE_FORMAT_PATTERN = "yyyy-MM-dd";

  private static final int W = 700;
  private static final int H = 700;


  private java.util.Calendar calendar;
  private Locale locale;

  private Node calendarNode;
  //private DatePickerContent calendarNode;
  private DatePicker ghostDatePicker;
  private JFXPanel fxPanel;
  private Scene scene;



  public Calendar(){

    locale = Locale.getDefault();
    calendar = java.util.Calendar.getInstance();

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

  private Scene createScene() throws Exception {
    Group root = new Group();
    scene = new Scene(root, /*W, H,*/ javafx.scene.paint.Color.TRANSPARENT);

    ghostDatePicker = new DatePicker();
    ghostDatePicker.setShowWeekNumbers(false);

    ghostDatePicker.setValue(LocalDate.now());

    ghostDatePicker.setVisible(false);

    DatePickerSkin skin = new DatePickerSkin(ghostDatePicker);
    calendarNode = (Node)skin.getPopupContent();
    //calendarNode = (DatePickerContent)skin.getPopupContent();

    root.getChildren().add(calendarNode);



    //scene.getStylesheets().clear();

    System.setProperty("rcss", "D:/Projects/JavaFX/SwingInterop/css/date-picker-readable.css");

    final String strCSS =
      System.getProperty(STR_JAVAFX_DATEPICKER_CSS) != null &&
      !System.getProperty(STR_JAVAFX_DATEPICKER_CSS).isEmpty()?
          new File(System.getProperty(STR_JAVAFX_DATEPICKER_CSS)).toURL().toExternalForm():
          this.getClass().getResource("date-picker-readable.css").toExternalForm();

    System.out.println("\n\nCSS: "+strCSS+"\n\n");

    scene.getStylesheets().add(strCSS);


    return (scene);
  }




  public static void main(final String[] args){

    //Locale.setDefault(new Locale("zh", "CN"));
    Locale.setDefault(Locale.PRC);

//    java.util.Calendar cal = java.util.Calendar.getInstance();
//    final String s = cal.getDisplayName(java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.SHORT, Locale.US);
//

////    //DateFormatSymbols.getShortWeekdays();
//////    System.out.println("LocaleData.getBundle(\"DayAbbreviations\", Locale.PRC): "+
//////                        sun.util.resources.LocaleData.getBundle("DayAbbreviations", Locale.PRC));
//////
////
////    for (DayOfWeek c : DayOfWeek.values()){
////      System.out.println(c);
////    }
//
//    Locale locale = Locale.getDefault();
//
//    System.out.println("\n\n");
//
//    for(DayOfWeek dow: DayOfWeek.values()){
//      System.out.println("Full:   "+dow.getDisplayName(TextStyle.FULL, locale));
//      System.out.println("Narrow: "+dow.getDisplayName(TextStyle.NARROW, locale));
//      System.out.println("Short:  "+dow.getDisplayName(TextStyle.SHORT, locale));
//      System.out.println("---------------------------------------------");
//    }


    JFrame frame = new JFrame("JCalendar");
    frame.getContentPane().add(new Calendar());
    //frame.pack();
    frame.setBounds(100,100,650,550);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}


