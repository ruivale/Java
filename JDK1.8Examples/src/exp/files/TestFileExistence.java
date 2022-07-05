package exp.files;

import java.io.*;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class TestFileExistence {
  public TestFileExistence() {
  }

  /**
   *
   * @param strAbsFilePath String
   * @return File
   */
  private File buildImageFile(final String strAbsFilePath){
    File file = null;

    if(strAbsFilePath != null && !strAbsFilePath.equals("")){
      file = new File(strAbsFilePath);

      if(!file.exists()){
        final String strParent = file.getParent();

        if (strParent != null && !strParent.equals("")) {
          final File fileDirs = new File(strParent);

          if(!fileDirs.exists()){
            fileDirs.mkdirs();
          }
        }
      }
    }

    return file;
  }


  public static void main(String[] args) {
    String strFile = "d:/temp/ole/ole/ole.txt";

    TestFileExistence t = new TestFileExistence();
    t.buildImageFile(strFile);
    //File f = new File("d:/temp/ole");
  }
}


