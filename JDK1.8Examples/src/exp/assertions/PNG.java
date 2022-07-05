package exp.assertions;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;


class PNG {

  /**
   * Create a PNG instance, read specified PNG file, and decode it into suitable structures.
   *
   * @param filespec path and name of PNG file to read
   *
   * @throws NullPointerException when <code>filespec</code> is <code>null</code>
   */
  PNG(String filespec) throws IOException {
    // Enforce preconditions in non-private constructors and methods.

    if (filespec == null) {
      throw new NullPointerException("filespec is null");
    }
    try (FileInputStream fis = new FileInputStream(filespec)) {
      readHeader(fis);
    }
  }

  private void readHeader(InputStream is) throws IOException {
    // Confirm that precondition is satisfied in private helper methods.
    assert is != null : "null passed to is";
  }
}
