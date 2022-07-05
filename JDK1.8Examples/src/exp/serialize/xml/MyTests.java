package exp.serialize.xml;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
//import com.ent.stv.GISTVInt;
import java.io.BufferedInputStream;
import java.io.File;
import java.util.Vector;
import java.io.FileOutputStream;


/**
 * <p>
 * Title: </p>
 * <p>
 * <p>
 * Description: </p>
 * <p>
 * <p>
 * Copyright: Copyright (c) </p>
 * <p>
 * <p>
 * Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class MyTests {

  String strFile = "c:/temp/MyTests.xml";

  public MyTests() {
    deserializeUserSelection();
    serializeUserSelection();
  }

  /**
   * deserializeUserSelection
   *
   * @param generalOptions GeneralOptions
   * @param i              int
   */
  private void deserializeUserSelection() {

    XMLDecoder xmlDecoder = null;

    final String strFileName = strFile;

    if (strFileName != null && !strFileName.equals("")) {

      final File file = new File(strFileName);
      java.util.List listOfValues = new Vector(4, 2);
      boolean running = true;

      if (file.exists()) {
        try {
          xmlDecoder = new XMLDecoder(
              new BufferedInputStream(
                  new FileInputStream(file)));

          while (running) {
            listOfValues.add(xmlDecoder.readObject());
          }

        } catch (Exception ex) {
          // Normally, a exception to forget cause the readObject(), which
          // will try to read indefinitively
          running = false;

        } finally {
          if (xmlDecoder != null) {
            xmlDecoder.close();
          }
        }

        GeneralOptionsSerializableObjects objFromFile;
        final int intNbrOfValues = listOfValues.size();
        for (int i = 0; i < intNbrOfValues; i++) {
          objFromFile = (GeneralOptionsSerializableObjects) listOfValues.get(i);

          switch ( objFromFile.intId ) {
            case 0:
              String s = (String) objFromFile.obj;
              System.out.println("s=" + s + ".");
              break;

            case 1:
              boolean b1 = ((Boolean) objFromFile.obj).booleanValue();
              System.out.println("b1=" + b1 + ".");
              break;

            case 2:
              boolean b2 = ((Boolean) objFromFile.obj).booleanValue();
              System.out.println("b2=" + b2 + ".");
              break;

            case 3:
              boolean b3 = ((Boolean) objFromFile.obj).booleanValue();
              System.out.println("b3=" + b3 + ".");
              break;
          }
        }
      } else {
        System.out.println("DeSerialiazing ... file does not exist.");
      }
    }
  }

  /**
   *
   * @param intUserId int
   */
  protected void serializeUserSelection() {

    XMLEncoder xmlEncoder = null;

    // Serializing the resizing ...
    try {
      xmlEncoder = new XMLEncoder(
          new BufferedOutputStream(
              new FileOutputStream(strFile)));

      Object[] objs = new Object[4];

      // Saving the user choices ...
      final GeneralOptionsSerializableObjects objLastSelDir = new GeneralOptionsSerializableObjects();
      objLastSelDir.setId(0);
      objLastSelDir.setObj("last selected dir");
      xmlEncoder.writeObject(objLastSelDir);

      final GeneralOptionsSerializableObjects objSnapWinAllow = new GeneralOptionsSerializableObjects();
      objSnapWinAllow.setId(1);
      objSnapWinAllow.setObj(new Boolean(true));
      xmlEncoder.writeObject(objSnapWinAllow);

      final GeneralOptionsSerializableObjects objAutoLockCam = new GeneralOptionsSerializableObjects();
      objAutoLockCam.setId(2);
      objAutoLockCam.setObj(new Boolean(false));
      xmlEncoder.writeObject(objAutoLockCam);

      final GeneralOptionsSerializableObjects objCamOperWinAllow
          = new GeneralOptionsSerializableObjects();
      objCamOperWinAllow.setId(3);
      objCamOperWinAllow.setObj(new Boolean(true));
      xmlEncoder.writeObject(objCamOperWinAllow);

    } catch (Exception ex) {
      ex.printStackTrace();

    } finally {
      if (xmlEncoder != null) {
        xmlEncoder.close();
      }
    }
  }

  public static void main(String[] args) {
    MyTests mytests = new MyTests();
  }
}
