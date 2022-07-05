package jdk1_6examples.xml.unmarshal;


import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class UnmarshallingDemo {

  public static void main(String[] args) {
    try {
      JAXBContext jc = JAXBContext.newInstance("jdk1_6examples.xml.unmarshal");

      Unmarshaller u = jc.createUnmarshaller();

      File f = new File("item.xml");
      JAXBElement element = (JAXBElement) u.unmarshal(f);

      Item item = (Item) element.getValue();
      System.out.println(item.getCode());
      System.out.println(item.getName());
      System.out.println(item.getPrice());


    } catch (JAXBException e) {
      e.printStackTrace();
    }
  }
}
