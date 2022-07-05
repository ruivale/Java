/**
 * <p>
 * Classname:  jdk1_6examples.javax.swing.jformattedtextfield.RegexFormatter
 * </p>
 *
 * <p>Copyright: Copyright (c) 2010 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Engº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package jdk1_6examples.javax.swing.jformattedtextfield;


import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.swing.text.DefaultFormatter;


/**
 *
 * <p>Classname: pt.efacec.inossv2.system.util.RegexFormatter </p>
 *
 * <p>Description: Modules to configure the INOSSv2 system mainly STV and
 * SIP.
 *
 * A regular expression based implementation of <code>AbstractFormatter</code>.
 *
 * </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: EFACEC</p>
 *
 * @author rUI vALE - rui dot vale at ent dot efacec dot pt
 * @version $Revision: 1.1 $
 */
public class RegexFormatter
    extends DefaultFormatter {
  private Pattern pattern;
  private Matcher matcher;

  public RegexFormatter() {
    super();
  }

  /**
   * Creates a regular expression based <code>AbstractFormatter</code>.
   * <code>pattern</code> specifies the regular expression that will
   * be used to determine if a value is legal.
   */
  public RegexFormatter(String pattern) throws PatternSyntaxException {
    this();
    setPattern(Pattern.compile(pattern));
  }

  /**
   * Creates a regular expression based <code>AbstractFormatter</code>.
   * <code>pattern</code> specifies the regular expression that will
   * be used to determine if a value is legal.
   */
  public RegexFormatter(Pattern pattern) {
    this();
    setPattern(pattern);
  }

  /**
   * Sets the pattern that will be used to determine if a value is
   * legal.
   */
  public void setPattern(Pattern pattern) {
    this.pattern = pattern;
  }

  /**
   * Returns the <code>Pattern</code> used to determine if a value is
   * legal.
   */
  public Pattern getPattern() {
    return pattern;
  }

  /**
   * Sets the <code>Matcher</code> used in the most recent test
   * if a value is legal.
   */
  protected void setMatcher(Matcher matcher) {
    this.matcher = matcher;
  }

  /**
   * Returns the <code>Matcher</code> from the most test.
   */
  protected Matcher getMatcher() {
    return matcher;
  }

  /**
   * Parses <code>text</code> returning an arbitrary Object. Some
   * formatters may return null.
   * <p>
   * If a <code>Pattern</code> has been specified and the text
   * completely matches the regular expression this will invoke
   * <code>setMatcher</code>.
   *
   * @throws ParseException if there is an error in the conversion
   * @param text String to convert
   * @return Object representation of text
   */
  public Object stringToValue(String text) throws ParseException {
    Pattern pattern = getPattern();

    if (pattern != null) {
      Matcher matcher = pattern.matcher(text);

      if (matcher.matches()) {
        setMatcher(matcher);
        return super.stringToValue(text);
      }
      throw new ParseException("Pattern did not match", 0);
    }
    return text;
  }
}
