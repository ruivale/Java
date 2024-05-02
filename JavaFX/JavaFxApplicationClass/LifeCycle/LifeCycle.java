import javafx.application.Application;
import javafx.application.Platform;

import javafx.stage.Stage;

public class LifeCycle extends Application
{
   @Override
   public void init()
   {
      System.out.printf("init() called on thread %s%n", 
                        Thread.currentThread());
   }

   @Override
   public void start(Stage primaryStage)
   {
      System.out.printf("start() called on thread %s%n", 
                        Thread.currentThread());
      Platform.exit();
   }

   @Override
   public void stop()
   {
      System.out.printf("stop() called on thread %s%n", 
                        Thread.currentThread());
   }
}