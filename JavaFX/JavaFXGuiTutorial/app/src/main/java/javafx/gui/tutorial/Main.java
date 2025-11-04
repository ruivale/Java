/**
 * <p>
 * Classname:  javafx.gui.tutorial.Main
 * </p>
 *
 * <p>Copyright: Copyright (c) 2024 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */

package javafx.gui.tutorial;


import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * <p>
 * Description:
 * https://www.youtube.com/watch?v=Ld14MG-aPAI
 * 
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since __DATE__
 */
public class Main extends Application{
  /* This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

  /* .. */
  private static final long serialVersionUID = 0L;


  private int memoizedHashCode = 0;


  
  @Override
  public void start(final Stage primaryStage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("bookranking.fxml"));
    final Parent root = loader.load();
    primaryStage.setTitle("Ranking of books");
    
    final BookRankingController controller = (BookRankingController)loader.getController();
    controller.bookslistview.getItems().add("Gone with the wind");
    controller.bookslistview.getItems().add("Sailing under water");
    controller.bookslistview.getItems().add("Gladiator");        
    controller.bookslistview.getItems().add("Jurassic park");
    controller.bookslistview.getItems().add("How to plan tomatoes");
    controller.bookslistview.getItems().add("How to plan cucumbers");

        
    primaryStage.setScene(new Scene(root, 800, 600));
    primaryStage.show();
  }

  
 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("Main").append("").toString();
  }

  
  
  
  public static void main(final String[] args){ 
    launch(args);    
  }

}
