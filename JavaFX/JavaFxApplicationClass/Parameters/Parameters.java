import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.stage.Stage;

public class Parameters extends Application
{
   @Override
   public void start(Stage primaryStage)
   {
      Application.Parameters parm = getParameters();
      System.out.printf("Named parameters: %s%n", parm.getNamed());
      System.out.printf("Raw parameters: %s%n", parm.getRaw());
      System.out.printf("Unnamed parameters: %s%n", parm.getUnnamed());
      Platform.exit();
   }
}