package exp.debug;

import java.util.List;

/**
 *
 * @author 2334
 */
public class DetectingDebugRun {

  public static void main(final String[] args) {

    // ...which is very vendor specific. Which is fine, as long as you know.
    boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().
        toString().indexOf("jdwp") > 0;
    System.out.println("\nIs in DEBUG (searched for \"jdwp\")? " + isDebug);


    List inputArgs = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments();
    isDebug = inputArgs.contains("-Xdebug");
    System.out.println("Is in DEBUG (searched for \"-Xdebug\")? " + isDebug + "\n");
  }
}
