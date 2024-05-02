import javafx.application.Application;
import javafx.application.Platform;

import javafx.stage.Stage;

public class Launch extends Application
{
   @Override
   public void start(Stage primaryStage)
   {
      System.out.printf("start() called on %s%n", Thread.currentThread());
      Platform.exit();
   }

   public static void main(String[] args)
   {
      System.out.printf("main() called on %s%n", Thread.currentThread());
      Application.launch(args);
      System.out.printf("terminating");
   }
}