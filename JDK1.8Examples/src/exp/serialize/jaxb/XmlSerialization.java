/**
 * <p>
 * Classname: exp.serialize.jaxb.XmlSerialization
 * </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) 2015 Efacec Engenharia e Sistemas, S.A.
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
package exp.serialize.jaxb;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/**
 *
 * @author 6643
 */
public class XmlSerialization {

  private static final Logger LOGGER = Logger.getLogger(XmlSerialization.class.getName());
  private Class _oType = null;

  /**
   *
   * @param <T>
   * @param oClassType
   */
  public <T> XmlSerialization(final Class<T> oClassType) {
    _oType = oClassType;
  }

  /**
   *
   * @param <T>
   * @param sFileName
   * @return
   */
  public <T> T deserialize(final String sFileName) {
    try {
      T deserialization;
      JAXBContext jc = JAXBContext.newInstance(
          _oType
      );
      Unmarshaller unmarshaller = jc.createUnmarshaller();
      File xml = new File(sFileName);
      deserialization = (T) unmarshaller.unmarshal(xml);

      return deserialization;

    } catch (Exception ex) {
      if (LOGGER != null) {
        LOGGER.log(Level.SEVERE, "Reading from configuration file failed.", ex);
      }
    }
    return null;
  }

  /**
   *
   * @param oSerializableObject
   * @param sFileName
   * @return
   */
  public Boolean serialize(final Object oSerializableObject, final String sFileName) {
    try {
      File file = new File(sFileName);
      JAXBContext jc = JAXBContext.newInstance(oSerializableObject.getClass());
      Marshaller marshaller = jc.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      marshaller.marshal(oSerializableObject, file);

      return true;

    } catch (Exception ex) {
      if (LOGGER != null) {
        LOGGER.log(Level.SEVERE, "Writing to configuration file failed.", ex);
      }
    }
    return false;
  }
}
