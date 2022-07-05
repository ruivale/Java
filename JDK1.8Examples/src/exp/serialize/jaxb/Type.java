/**
 * <p>
 * Classname: exp.serialize.jaxb.Type
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


import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;



/**
 *
 * @author 6643
 */
public class Type {
  // <editor-fold desc="Declarations">

  private String _sName = null;
  private int _nCode = -1;
  private String _sLayoutFile = null;
  private String _sBatchFile = null;
  // </editor-fold>

  // <editor-fold desc="Properties">
  /**
   * @return the _sName
   */
  @XmlAttribute(required = true, name = "Name")
  public String getName() {
    return _sName;
  }

  /**
   * @param sName the _sName to set
   */
  public void setName(String sName) {
    this._sName = sName;
  }

  /**
   * @return the _nCode
   */
  @XmlAttribute(required = true, name = "Code")
  public int getCode() {
    return _nCode;
  }

  /**
   * @param nCode the _nCode to set
   */
  public void setCode(int nCode) {
    this._nCode = nCode;
  }

  /**
   * @return the _sLayoutFile
   */
  @XmlElement(required = true, name = "LayoutFile")
  public String getLayoutFile() {
    return _sLayoutFile;
  }

  /**
   * @param sLayoutFile the _sLayoutFile to set
   */
  public void setLayoutFile(String sLayoutFile) {
    this._sLayoutFile = sLayoutFile;
  }

  /**
   * @return the _sBatchFile
   */
  @XmlElement(required = true, name = "BatchFile")
  public String getBatchFile() {
    return _sBatchFile;
  }

  /**
   * @param sBatchFile the _sBatchFile to set
   */
  public void setBatchFile(String sBatchFile) {
    this._sBatchFile = sBatchFile;
  }
  // </editor-fold>

  // <editor-fold desc="Constructors">
  public Type() {
  }

  public Type(String sName,
              int nCode,
              String sLayoutFile,
              String sBatchFile) {
    _sName = sName;
    _nCode = nCode;
    _sLayoutFile = sLayoutFile;
    _sBatchFile = sBatchFile;
  }
  // </editor-fold>

  
  
  
  
  public static void main(String[] args) {
    try {
      final Type type = new Type("sName", 0, "sLayoutFile", "sBatchFile");

      final XmlSerialization xmlSerialization = new XmlSerialization(type.getClass());
      xmlSerialization.serialize(type,   "c:\\temp\\type.dat");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

} //end class
