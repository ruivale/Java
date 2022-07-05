package exp.encrypt;

import java.util.Arrays;

/** A fast Java class to encode and decode to and from BASE64 in full accordance with RFC 2045.
 * On Windows XP sp1 with 1.4.2_04 and later ;), this encoder and decoder is about 10 to 30 times faster on small
 * arrays (100 - 1000 bytes) and 2-3 times as fast on larger arrays (100000 - 1000000 bytes).

 * The encoder produces the same output as the Sun one except that the Sun's encoder appends
 * a trailing line separator if the last character isn't a pad. Unclear why, but it only adds to the
 * length and is probably a side effect. Both are in conformance with RFC 2045 though.


 * Code used for validation:

 *

 * 		Random rand = new Random();
 *		// All separators, including the system one.
 *		String sysSep = System.getProperty("line.separator");
 *		String[] seps = {null, "", "\r\n", "\n", "\r", sysSep};
 *		sun.misc.BASE64Encoder sunEnc = new sun.misc.BASE64Encoder();
 *
 *		for (int i = 0; i < 100000; i++) {
 *			byte[] ba1 = new byte[(int) rand.nextInt(100000)];
 *			rand.nextBytes(ba1);
 *
 *			String sep = seps[rand.nextInt(seps.length)]; // Pick a random separator
 *
 *			String s = Base64.encode(ba1, sep);
 *			byte[] ba2 = Base64.decode(s);
 *
 *			if (Arrays.equals(ba1, ba2) == false) {
 *				System.out.println("Integrity Failure!!");
 *				System.exit(0);
 *			}
 *
 *			String sunS = sunEnc.encode(ba1);
 *
 *			if (sep != null && sep.equals(sysSep)) {
 *				// trim since we aren't handling last separator exactly the same and number of white spaces doesn't matter anyway.
 *				if (sunS.trim().equals(s.trim()) == false) {
 *					System.out.println("Sun conformance Failure!!\n\"" + s + "\"\n\n    != \n\n\"" + sunS + "\"");
 *					System.exit(0);
 *				}
 *			}
 *		}
 *
 *		System.out.println("Success!!");
 *		System.exit(0);
 *


 *
 * Licence:
 * Free to use for any legal purpose, though sending an email to base64 @ miginfocom . com to tell me you're using it will ut a smile on my face! :)

 * Reprint/republish for public consumption is allowed as long as this licence is included.
 * @author Mikael Grev
 *         Date: 2004-aug-02
 *         Time: 11:31:11
 */

public class Base64 {
  private static final char[] CA =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".
      toCharArray();
  private static final int[] IA = new int[256];
  static {
    Arrays.fill(IA, -1);
    for (int i = 0, iS = CA.length; i < iS; i++) {
      IA[CA[i]] = i;
    }
  }

  /** Encodes a raw byte array into a BASE64 string representation i accordance with RFC 2045.
   * @param bArr The bytes to convert. If null or length 0, "" will be returned.
   * @param lineSep Optional line separator after 76 characters, unless end of file.
   * Max 2 chars length. If null or length 0 no line breaks will be inserted.

   * No line separator will be in breach of RFC 2045 which specifies max 76 per line but will be a
   * little faster.
   * @return A BASE64 encoded string. Never null.
   */
  public final static String encode(byte[] bArr, String lineSep) {
    // Check special case
    if (bArr == null || bArr.length == 0) {
      return "";
    }

    int last = bArr.length - 1; // Last byte
    int cCnt = (last / 3 + 1) << 2; // Character count
    int sepLen = lineSep != null ? lineSep.length() : 0;
    int sepCnt = ( (cCnt - 1) / 76) * sepLen; // line separator count
    int encArrLen = cCnt + sepCnt; // Length of returned string

    char[] encArr = new char[encArrLen];

    for (int rOff = 0, cOff = 0, sepAdd = 0; rOff <= last; rOff += 3) {
      int left = last - rOff;
      int bEnd = (left > 1 ? 2 : left);

      // Collect 1 to 3 bytes to encode
      int block = 0;
      for (int i = 0, r = 16; i <= bEnd; i++, r -= 8) {
        int n = bArr[rOff + i];
        block += (n < 0 ? n + 256 : n) << r;
      }

      // Encode into 2-4 chars appending '=' if not enough data left.
      encArr[cOff++] = CA[ (block >>> 18) & 0x3f]; // >>> is faster than >> !!
      encArr[cOff++] = CA[ (block >>> 12) & 0x3f];
      encArr[cOff++] = left > 0 ? CA[ (block >>> 6) & 0x3f] : '=';
      encArr[cOff++] = left > 1 ? CA[block & 0x3f] : '=';

      // Possibly insert line break after character 76.
      if (sepCnt > 0 && encArrLen > cOff) { // If we have a separator and not end if buf
        if ( (cOff - sepAdd) % 76 == 0) { // If after char 76
          encArr[cOff++] = lineSep.charAt(0);
          if (sepLen > 1) {
            encArr[cOff++] = lineSep.charAt(1);
          }
          sepAdd += sepLen;
        }
      }
    }

    return new String(encArr);
  }

  /** Decodes a BASE64 encoded string. All illegal characters will be igored and can handle both strings with
   * and without line separators.
   * @param s The string. null or "" will return an empty array.
   * @return The decoded array of bytes. Never null but may be of length 0.
   */

  public final static byte[] decode(String s) {
    // Check special case
    if (s == null || s.length() == 0) {
      return new byte[0];
    }

    // Note. Making an array of s is faster somtimes but slower sometimes.
    // Timing values are more uneven due to garbage creation.

    // Count '=' at end and disregard them
    int pad = 0;
    for (int i = s.length() - 1; s.charAt(i) == '='; i--) {
      pad++;

      // Count illegal characters to know what size the returned byte[] will have so we don't have to reallocate it later.
    }
    int sepCnt = 0; // Number of separator characters. (Actually illegal characters, but that's a bonus...)
    for (int i = 0, iS = s.length() - pad; i < iS; i++) {
      if (IA[s.charAt(i)] < 0) {
        sepCnt++;
      }
    }

    int len = ( ( (s.length() - sepCnt) * 6) >> 3) - pad;
    byte[] b = new byte[len]; // Preallocate byte[] of exact length

    for (int i = 0, iS = s.length() - pad, bIx = 0; i < iS; bIx += 3) {

      // Assemble three bytes into an int from four "valid" characters.
      int bits = 0;
      for (int j = 0; j < 4 && i < iS; ) { // j only increased if a valid char was found.
        int c = IA[s.charAt(i++)];
        if (c >= 0) {
          bits += c << (18 - 6 * j++);
        }
      }

      // Add the bytes
      for (int j = 0, r = 16; j < 3 && bIx + j < len; j++, r -= 8) {
        b[bIx + j] = (byte) ( (bits >> r) & 0xff);
      }
    }
    return b;
  }
}
