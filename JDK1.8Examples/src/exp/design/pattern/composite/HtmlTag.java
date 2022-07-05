/**
 * <p>
 * Classname: exp.design.pattern.composite.HtmlTag
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2019 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE. You shall not
 * disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into EFACEC SE.
 * </p>
 * <p>
 * Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */
package exp.design.pattern.composite;

import java.util.List;


public abstract class HtmlTag {

  public abstract String getTagName();

  public abstract void setStartTag(String tag);

  public abstract void setEndTag(String tag);

  public void setTagBody(String tagBody) {
    throw new UnsupportedOperationException(
      "Current operation is not supportfor this object");
  }

  public void addChildTag(HtmlTag htmlTag) {
    throw new UnsupportedOperationException(
      "Current operation is not support for this object");    
  }

  public void removeChildTag(HtmlTag htmlTag) {
    throw new UnsupportedOperationException(
      "Current operation is not support for this object");
  }

  public List<HtmlTag> getChildren() {
    throw new UnsupportedOperationException(
      "Current operation is not support for this object");
  }

  public abstract void generateHtml();
}
