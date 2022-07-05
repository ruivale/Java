package exp.assertions;

import java.io.IOException;


public class AssertDemoPNG {

  private static final String str = "c:/temp/saved.png";

  public static void main(String[] args) throws IOException {
    PNG png = new PNG((args.length == 0) ? null : args[0]);
  }
}
