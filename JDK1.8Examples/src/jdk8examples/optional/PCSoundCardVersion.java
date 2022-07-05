/**
 * <p>
 * Classname: jdk8examples.optional.PCSoundCardVersion
 * </p>
 *
 * <p>Copyright: Copyright (c) 2012 Efacec Engenharia e Sistemas, S.A.
 * <br>
 * This software is the confidential and proprietary information of EFACEC Eng. Sistemas.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC Eng. Sistemas
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

package jdk8examples.optional;


import java.util.logging.Logger;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;


/**
 * <p>
 * Description:
 * </p>
 *
 * Created on Jun 18, 2014, 4:19:40 PM
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class PCSoundCardVersion {
  /** This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(PCSoundCardVersion.class.getName());


  public PCSoundCardVersion(){
    this(Optional.empty());
  }

  public PCSoundCardVersion(final Optional<Soundcard> optSoundcard){
    final Optional<Computer> optComputer = Optional.of(new Computer());
    String strPCSCUsbVersion =
        optComputer.flatMap(Computer::getSoundcard)
            .flatMap(Soundcard::getUSB)
            .map(USB::getVersion)
            .orElse("UNKNOWN");

    System.out.println("PCSoundCardVersion Computer SoundCard usb version: "+strPCSCUsbVersion);

    optComputer.ifPresent(System.out::println);

    //
    //

    Soundcard soundcard = optSoundcard.orElse(new Soundcard("new"));
    System.out.println(soundcard);

    try {
      Soundcard soundcardWithException = optSoundcard.orElseThrow(IllegalStateException::new);
    } catch (Exception e) {
      e.printStackTrace();
    }

    //
    //
    //USB usb = new USB("2.5");
//    final Optional<USB> maybeUSB = Optional.of(new USB("3.0"));
//    maybeUSB.filter(usb -> "3.0".equals(usb.getVersion())).ifPresent(System.out.println("ok"));


  }

  public static void main(final String[] args){
    final PCSoundCardVersion clazz = new PCSoundCardVersion();
  }
}

class Computer {
  private final Optional<Soundcard> soundcard = Optional.of(new Soundcard("DEFAULT"));
  public Optional<Soundcard> getSoundcard() {
    return soundcard;
  }
  @Override
  public String toString(){
    return "The Computer class";
  }
}
class Soundcard {
  private final String strDesc;
  private final Optional<USB> usb = Optional.of(new USB());
  public Soundcard(final String strDesc) {
    this.strDesc = strDesc;
  }
  public Optional<USB> getUSB() {
    return usb;
  }
  @Override
  public String toString(){
    return "Soundcard: "+strDesc;
  }
}
class USB {
  private final String strVersion;
  USB(){
    this("2.0");
  }
  USB(final String strVersion){
    this.strVersion = strVersion;
  }
  public String getVersion() {
    return strVersion;
  }
}

