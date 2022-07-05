package jdk1_6examples.xml.marshal;


import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;


public class JavaToXMLDemo {
  public static void main(String[] args)
      throws Exception {
    JAXBContext context = JAXBContext.newInstance(Employee.class);

    Marshaller m = context.createMarshaller();
    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    Employee object = new Employee();
    object.setCode("CA");
    object.setName("Cath");
    object.setSalary(300);

    m.marshal(object, System.out);

    m.marshal(object, new FileOutputStream("result.xml"));

  }
}


@XmlRootElement class Employee {
  private String code;

  private String name;

  private int salary;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int population) {
    this.salary = population;
  }
}
