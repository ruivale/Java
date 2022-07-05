package exp.jar;


import java.io.BufferedReader;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.util.Enumeration;

import java.util.jar.JarEntry;

import java.util.jar.JarFile;

import java.util.jar.JarOutputStream;


public class EditJarFile {

  public static void main(String args[]) {

    String jartoEdit = "D:\\Java\\Utils\\Jalopy\\lib\\jalopy-1.0b11.jar";

    String newFile = "D:\\temp\\env_vars.txt";

    File tempJar = null;

    try {

      tempJar = File.createTempFile(jartoEdit, null);

    } catch (IOException e) {

      System.err.println("Unable to create intermediate file.");

      System.exit( -2);

    }

    JarFile jar = null;

    try {

      jar = new JarFile(jartoEdit);

    } catch (IOException e) {

      System.err.println("Unable to access original file.");

      System.exit( -3);

    }

    boolean delFlag = false;

    try {

      JarOutputStream newJar =

          new JarOutputStream(

              new FileOutputStream(tempJar));

      byte buffer[] = new byte[1024];

      int bytesRead;

      try {

        FileInputStream fis = new FileInputStream(newFile);

        Enumeration entries = jar.entries();

        while (entries.hasMoreElements()) {

          JarEntry entry = (JarEntry) entries.nextElement();

          String name = entry.getName();

          System.out.println

              ("Copy " + name + " into new jar? (y or n)");

          BufferedReader reader =

              new BufferedReader

              (new InputStreamReader(System.in));

          String line = reader.readLine();

          if ("n".equals(line)) {

            System.out.println("Skipping: " + name);

            continue;

          }

          InputStream is = jar.getInputStream(entry);

          newJar.putNextEntry(entry);

          while ((bytesRead = is.read(buffer)) != -1) {

            newJar.write(buffer, 0, bytesRead);

          }

        }

        try {

          JarEntry entry = new JarEntry(newFile);

          newJar.putNextEntry(entry);

          while ((bytesRead = fis.read(buffer)) != -1) {

            newJar.write(buffer, 0, bytesRead);

          }

        } finally {

          fis.close();

        }

        delFlag = true;

      } catch (IOException ex) {

        System.err.println

            ("Operation aborted due to : " + ex);

      } finally {

        try {

          newJar.close();

        } catch (IOException ignored) {

        }

      }

    } catch (IOException ex) {

      System.err.println(

          "Can't access new file");

    } finally {

      try {

        jar.close();

      } catch (IOException ignored) {

      }

      if (!delFlag) {

        tempJar.delete();

      }

    }

    if (delFlag) {

      File origFile = new File(jartoEdit);

      origFile.delete();

      tempJar.renameTo(origFile);

    }

  }

}
