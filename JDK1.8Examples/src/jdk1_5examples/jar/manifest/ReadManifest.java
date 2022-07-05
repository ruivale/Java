package jdk1_5examples.jar.manifest;


/*
 * SCCS: %W% %I%
 *
 * Copyright 2007 Sun Microsystems, Inc.  All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


import java.io.File;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Logger;


/**
 * A small program that reads the manifest of a jar file.
 * @author Daniel Fuchs
 */
public class ReadManifest {

  /**
   * A logger for this class.
   **/
  private static final Logger LOG = Logger.getLogger(ReadManifest.class.getName());

  /**
   * Displays the attributes contained in the manifest of one (or
   * several jar files...
   *
   * @param args a list of jar file.
   */
  public static void main(String[] args) {

    for (String arg : args) {
      final File f = new File(arg);
      if (!f.canRead()) {
        System.err.println(f.getAbsolutePath() + ": no such file");
        continue;
      }
      try {
        JarFile jar = new JarFile(f);
        final Manifest manifest = jar.getManifest();
        final Attributes mattr = manifest.getMainAttributes();
        System.out.println(f.getAbsolutePath());
        System.out.println("Main attrs: ");
        for (Object a : mattr.keySet()) {
          System.out.println("\t " + a + ": " + mattr.getValue( (Name) a));
        }
        System.out.println("\nReading other attrs:\n");

        final Map<String, Attributes> attrs = manifest.getEntries();
        for (String name : attrs.keySet()) {
          final Attributes attr = attrs.get(name);
          System.out.println(name + ": \n");
          for (Object a : attr.keySet()) {
            System.out.println("\t " + a + ": " + attr.getValue( (Name) a));
          }
        }
      } catch (Exception x) {
        System.err.println("Failed to read manifest for " + f.getAbsolutePath() +
                           ": " + x);
      }
    }
  }

}
