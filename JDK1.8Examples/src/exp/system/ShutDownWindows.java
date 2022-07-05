package exp.system;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ShutDownWindows {
  public static void main(String[] args) {
    try {
      Runtime.getRuntime().exec("rundll32.exe user.exe,exitWindowsExec");
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
