/**
 * <p>
 * Classname: exp.design.pattern.composite.HtmlParentElement
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

import java.util.ArrayList;
import java.util.List;

public class HtmlParentElement extends HtmlTag {

  private String tagName;
  private String startTag;
  private String endTag;
  private List<HtmlTag> childrenTag;

  public HtmlParentElement(String tagName) {
    this.tagName = tagName;
    this.startTag = "";
    this.endTag = "";
    this.childrenTag = new ArrayList<>();
  }

  @Override
  public String getTagName() {
    return tagName;
  }

  @Override
  public void setStartTag(String tag) {
    this.startTag = tag;
  }

  @Override
  public void setEndTag(String tag) {
    this.endTag = tag;
  }

  @Override
  public void addChildTag(HtmlTag htmlTag) {
    childrenTag.add(htmlTag);
  }

  @Override
  public void removeChildTag(HtmlTag htmlTag) {
    childrenTag.remove(htmlTag);
  }

  @Override
  public List<HtmlTag> getChildren() {
    return childrenTag;
  }

  @Override
  public void generateHtml() {
    System.out.println(startTag);
    for (HtmlTag tag : childrenTag) {
      tag.generateHtml();
    }
    System.out.println(endTag);
  }
}
