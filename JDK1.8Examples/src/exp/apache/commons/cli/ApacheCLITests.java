/**
 * <p>
 * Classname: exp.apache.commons.cli.ApacheCLITests
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2016 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC Eng. Sistemas
 * <br>
 * Rua Eng.º Frederico Ulrich ? Ap. 3078
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
package exp.apache.commons.cli;

import java.util.logging.Logger;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;


/**
 * <p>
 * Description:
 * </p>
 * <p>
 * Created on Nov 15, 2016, 6:02:13 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 * @version $Revision: 1.1 $
 */
public class ApacheCLITests {

  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(ApacheCLITests.class.getName());

  /**
   * The ApacheCLITests default constructor.
   */
  public ApacheCLITests(final String args[]) {
// create the command line parser
    CommandLineParser parser = new DefaultParser();

// create the Options
    Options options = new Options();
    options.addOption("f", "font", true, "the font");
    options.addOption("a", "all", false, "do not hide entries starting with .");
    options.addOption("A", "almost-all", false, "do not list implied . and ..");
    options.addOption("b", "escape", false, "print octal escapes for nongraphic "
                                                + "characters");
    options.addOption(OptionBuilder.withLongOpt("block-size")
        .withDescription("use SIZE-byte blocks")
        .hasArg()
        .withArgName("SIZE")
        .create());
    options.addOption("B", "ignore-backups", false, "do not list implied entried "
                                                        + "ending with ~");
    options.addOption("c", false, "with -lt: sort by, and show, ctime (time of last "
                                      + "modification of file status information) with "
                                      + "-l:show ctime and sort by name otherwise: sort "
                                      + "by ctime");
    options.addOption("C", false, "list entries by columns");

    try {
      // parse the command line arguments
      CommandLine line = parser.parse(options, args);

      // validate that block-size has been set
      if (line.hasOption("block-size")) {
        // print the value of block-size
        System.out.println(line.getOptionValue("block-size"));
      }


      System.out.println("------------------------------------------------------------------------");
      for (Option opt : line.getOptions()) {
        System.out.println(opt.getOpt() + ": "+opt.getValue()+" "+opt);
      }
      System.out.println("------------------------------------------------------------------------");

    } catch (ParseException exp) {
      System.out.println("Unexpected exception:" + exp.getMessage());
    }



  }

  public static void main(final String[] args) {
    String[] _args = new String[]{
      "--block-size=10",
      "-f=Tahoma F",
      "--font=Tahoma Font",
    };

    final ApacheCLITests clazz = new ApacheCLITests(_args);

  }
}
