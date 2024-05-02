import javafx.application.Application;
import javafx.application.Platform;

import javafx.stage.Stage;

public class GlobalSS extends Application
{
    @Override
   public void start(Stage primaryStage)
   {
      System.out.printf("Default global style sheet: %s%n",
                        getUserAgentStylesheet());
      setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
      System.out.printf("New global style sheet: %s%n",
                        getUserAgentStylesheet());
      setUserAgentStylesheet(Application.STYLESHEET_MODENA);
      System.out.printf("New global style sheet: %s%n",
                        getUserAgentStylesheet());
      Platform.exit();
   }
}