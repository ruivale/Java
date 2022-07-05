/**
 * <p>
 * Classname:  jdk1_6examples.xml.parsing.ParsingSTVSeqsXML
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 EFACEC SE
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

package jdk1_6examples.xml.parsing;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Logger;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class ParsingSTVSeqsXML {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(ParsingSTVSeqsXML.class.getName());


 /**
  * The ParsingSTVSeqsXML default constuctor.
  */
  public ParsingSTVSeqsXML() throws Exception{
    // 
     final String strSeqXML = "<?xml version = \"1.0\" encoding = \"ISO-8859-1\"?>" +
          "<SEQS>      <seq id=\"1129\" name=\"Seq. 6\" mode=\"1\"/>" +
          "      <seq id=\"1108\" name=\"Seq. teste\" mode=\"1\"/>" +
          "      <seq id=\"1128\" name=\"Seq. 6\" mode=\"1\"/>" +
          "      <seq id=\"1130\" name=\"Seq. 6\" mode=\"1\"/>" +
          "      <seq id=\"1008\" name=\"Black/Burton\" mode=\"1\"/>" +
          "      <seq id=\"969\" name=\"NewJSTeste\" mode=\"1\"/>" +
          "      <seq id=\"1068\" name=\"STR\" mode=\"1\"/>" +
          "      <seq id=\"1168\" name=\"P&amp;R seq\" mode=\"1\"/>" +
          "      <seq id=\"1048\" name=\"Sandyford Depot\" mode=\"1\"/>" +
          "</SEQS>";



    final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    //factory.setNamespaceAware(true); // never forget this!
    final DocumentBuilder builder = factory.newDocumentBuilder();
    final InputStream inputStream = new ByteArrayInputStream(strSeqXML.getBytes());
    final Document doc = builder.parse(inputStream);

    XPathFactory xPathFactory = XPathFactory.newInstance();
    XPath xpath = xPathFactory.newXPath();
    XPathExpression expr =
        xpath.compile("/*/seq");
        //xpath.compile("//node[@id='17' and @type='group']/property[@name='symbolSTV']/text()");
        //xpath.compile("//book[author='Neal Stephenson']/title/text()");

    Object result = expr.evaluate(doc, XPathConstants.NODESET);
    NodeList nodes = (NodeList) result;

    for(int i = 0; i < nodes.getLength(); i++) {
      //System.out.println(nodes.item(i).getNodeValue());
      final NamedNodeMap nnm = nodes.item(i).getAttributes();

      for(int j = 0; j < nnm.getLength(); j++) {
        System.out.println(nnm.item(j).getNodeName()+"->"+ nnm.item(j).getNodeValue());
      }

      /***/
      final NodeList nl = nodes.item(i).getChildNodes();
      for(int j = 0; j < nl.getLength(); j++) {
        if(nl.item(j).getChildNodes().getLength() < 2){
          System.out.println(" "+j+"-"+
              nl.item(j).getNodeName()+"->"+ nl.item(j).getTextContent());
        }
      }
      /**/
    }

    if(nodes.getLength() < 1){
      System.out.println("No nodes found ...");
    }


  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("ParsingSTVSeqsXML").append("").toString();
  }

  public static void main(final String[] args){
    try {
      final ParsingSTVSeqsXML clazz = new ParsingSTVSeqsXML();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
}
