/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package personal.filesindirrenamer;

import java.io.Console;
import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;


/**
 * personal.filesindirrenamer.SearchForFilesAndRenameThem
 * @author c2334
 */
public class SearchForFilesAndRenameThem {

  public static void main(String[] args) throws Exception {
    final Console console = System.console();
    
    if (console == null) {
      System.err.println("sales: unable to obtain console");
      return;
    }

    String strRegexp = console.readLine("Enter regexp to search: [.*\\)] ");
    if(strRegexp.length() < 1){
      strRegexp = ".*\\)";
    }
    
    String strDir = console.readLine("Enter dir: [.] ");
    if(strDir.length() < 1){
      strDir = ".";
    }
    
    final String strFileNameConcat = console.readLine("Enter files name concat: [] ");
    if(strFileNameConcat.length() < 1){
      System.out.println("\nNothing to be done. Aborting ...");
      return;
    }

    System.out.println("\nSearching for "+
        strRegexp+" files in the dir "+
        strDir+" and concat its names with "+
        strFileNameConcat+".");
    
    final String strRegexpAux = strRegexp;
    final FileFilter ff = new FileFilter() {
      @Override
      public boolean accept(File file) {
        return Pattern.matches(strRegexpAux, file.getName());
        //return file.getName().endsWith(strRegexp);
      }
    };
    final File fileDir = new File(strDir);
    
    if(fileDir.exists() && fileDir.isDirectory()){
      final File[] files = fileDir.listFiles(ff);
      System.out.println("Found "+files.length+" files."); 
      
      for(int i = 0; i < files.length; i++) {
        System.out.println("Renaming file["+i+"]: "+files[i].getAbsolutePath()+"."); 
        final String strNewName = files[i].getAbsolutePath().concat(strFileNameConcat);
        
        if(files[i].renameTo(new File(strNewName))){
          System.out.println("Renamed to "+strNewName+"."); 
        }else{
          System.out.println("Didn't rename."); 
        }
      }
      
    }else{
      System.err.println("The given "+strDir+" doesnt exist or is not a directory!");
    }
  }
  
}
