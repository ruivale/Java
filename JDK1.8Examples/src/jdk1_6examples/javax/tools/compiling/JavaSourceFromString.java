package jdk1_6examples.javax.tools.compiling;

import javax.tools.*;
import java.net.*;


class JavaSourceFromString extends SimpleJavaFileObject {
  final String code;

  JavaSourceFromString(String name, String code) {
    super(URI.create("string:///" + name.replace('.','/') + Kind.SOURCE.extension),Kind.SOURCE);
    this.code = code;
  }

  //@Override
  public CharSequence getCharContent(boolean ignoreEncodingErrors) {
    return code;
  }
}
