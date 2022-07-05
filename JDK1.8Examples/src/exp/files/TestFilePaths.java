package exp.files;

import java.io.File;


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
public class TestFilePaths {
  private static String strDir = "bak";

  public static void main(final String[] args) {
    try {
      File file = new File("view.gif");

      System.out.println("file.getName()=" + file.getName());
      System.out.println("file.getPath()=" + file.getPath());
      System.out.println("file.getAbsolutePath()=" + file.getAbsolutePath());
      System.out.println("file.getCanonicalPath()=" + file.getCanonicalPath());

      System.out.println("----------------------------------------------");

      if (file.exists()) {
        File fileNew = new File(strDir + "/" + file.getName());
        System.out.println("NewFile:" + fileNew.getAbsolutePath() + ".");

        if (fileNew.exists()) {
          System.out.println("File:" + fileNew.getAbsolutePath() +" exists. Deleting ...");

          if (!fileNew.delete()) {
            System.out.println("cannot delete file:" + fileNew.getAbsolutePath() +".");
          } else {
            System.out.println("file:" + fileNew.getAbsolutePath() +" DELETED.");
          }
        } else {
          System.out.println("File:" + fileNew.getAbsolutePath() +" DOES NOT exists.");
        }


        final File fileTemp = file.createTempFile(
            "temp_",
            ".temp",
            new File(file.getAbsolutePath()).getParentFile());

        if(fileTemp.exists()){
          System.out.println("temp file:"+fileTemp.getAbsolutePath()+" CREATED.");
        }else{
          System.out.println("temp file:"+fileTemp.getAbsolutePath()+" NOT CREATED.");
        }

        System.out.println("Press any key to continue ...");
        System.in.read();

        final String strFileBak = file.getAbsolutePath();

        if (!file.renameTo(fileNew)) {
          System.out.println("cannot RENAME file:" + file.getAbsolutePath() +
                             " to file:" + fileNew.getAbsolutePath() + ".");
        } else {
          fileTemp.renameTo(new File(strFileBak));

          System.out.println("file:" + file.getAbsolutePath() +
                             " RENAMED to file:" + fileNew.getAbsolutePath() +".");
        }
      } else {
        System.out.println("Cannot FIND the original file. Finishing ... ");
      }

    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

}
