/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdk1_6examples.xml.parsing;

import java.io.IOException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;


/**
 * Expressive as it is, XPath is not the Java language -- in fact, 
 * XPath is not a complete programming language. There are many things you can't
 * say in XPath, even queries you can't make. For example, XPath can't find all 
 * the books whose International Standard Book Number (ISBN) check digit doesn't 
 * match or all the authors for whom the external accounts database shows a 
 * royalty payment is due. Fortunately, it is possible to integrate XPath into 
 * Java programs so that you get the best of both worlds: Java for what Java is 
 * good for and XPath for what XPath is good for. 
 * 
 * Until recently, the exact application program interface (API) by which Java 
 * programs made XPath queries varied with the XPath engine. Xalan had one API, 
 * Saxon had another, and other engines had other APIs. This meant your code 
 * tended to lock you into one product. Ideally, you'd like to able to experiment 
 * with different engines that have different performance characteristics without 
 * undue hassle or rewriting of code. 
 * 
 * For this reason, Java 5 introduced the javax.xml.xpath package to provide an 
 * engine and object-model independent XPath library. This package is also 
 * available in Java 1.3 and later if you install Java API for XML Processing 
 * (JAXP) 1.3 separately. Among other products, Xalan 2.7 and Saxon 8 include an 
 * implementation of this library.
 * 
 * 
 * Used to test the Java6 XML/XPath capabilities, mainly performance. Since we, 
 * till 20080619, use the Xerces/dom4j/Jaxen trio 4 XML parsing/processing, we
 * need to do some performance tests.
 * Here we go:
 *  - using only Java6 classes, reading the XML file of about 1000k bytes, search
 *    for a node, in the middle of the file, printing it's attributes and its non 
 *    child node values, it takes, on average, 440 millis. 
 *    The "old trio" takes, on average, 600 millis. About 36% more time. 
 *    But, if we remove Xerces, the "old trio" become a duo (dom4j & Jaxen) and 
 *    the results lead to a better performance. It takes, on average, 485 millis.
 *    (Pentium Core 2 Duo, 2.33 GHz, 2 GB RAM)
 * 
 */
public class ParsingXMLTests {
  final String strFilePath = "D:/Projects/JDK1.6Examples/xml/stv_main_xml.xml";

  /**
   * ...
   * - <SDM backimage="stv/images/ReferTelecom.gif">
   *     <node type="group" id="17">
   *       <property name="id">17</property> 
   *       <property name="textid">17</property> 
   *       <property name="symbolSTV">stv/images/MapaPortugal.jpg</property> 
   *       <property name="symbolSTVGroupEnable">stv/images/treenode_cyan.png</property>
   * ...
   * ...
   *     <node type="NVR VisioWave" id="309012">
   *         <property name="id">309012</property> 
   *         <property name="type">NVR VisioWave</property> 
   *      ....
   * 
   * 
   * @throws java.lang.Exception
   */
  public ParsingXMLTests() throws Exception{
        
    long before = System.currentTimeMillis();
    
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true); // never forget this!
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(strFilePath);
    
    XPathFactory xPathFactory = XPathFactory.newInstance();    
    XPath xpath = xPathFactory.newXPath();
    XPathExpression expr = 
        xpath.compile("//node[@id='1' and @type='group']");
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
    
    
    long after = System.currentTimeMillis();
    
    System.out.println("I toke "+(after - before)+" millis. ParsingXMLTests"); 
    System.out.println("org.xml.sax.driver:"+System.getProperty("org.xml.sax.driver")); 
    
  }
  
  public static void main(String[] args) {
    try {
      new ParsingXMLTests();      
    } catch(Exception e) {
    }
  }
}
