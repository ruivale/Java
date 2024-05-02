import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;

import javafx.stage.Stage;

public class HostServ extends Application
{
   @Override
   public void start(Stage primaryStage)
   {
      HostServices hs = getHostServices();
      System.out.printf("Code base: %s%n", hs.getCodeBase());
      System.out.printf("Document base: %s%n", hs.getDocumentBase());
      System.out.printf("Web context: %s%n", hs.getWebContext());
      Platform.exit();
   }
}