/**
 * <p>
 * Classname:  jdk8examples.i18n.arabic.NumberOfChars
 * </p>
 *
 * <p>Copyright: Copyright (c) 2018 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Eng.º Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.com
 * <br>
 * Email: mktransportes@efacec.com
 * </p>
 */

package jdk8examples.i18n.arabic;


import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot pt}
 * @version $Revision: 1.1 $
 */
public class NumberOfChars {
  
  
 

///**
//* C++ UTF-8 strlen function
//* When unicode first came into being, it was generally implemented as UCS2 (now
//* called UTF-16BE). For instance Java, and Microsoft Windows store unicode 
// * strings in this way. UTF-16BE uses 2 bytes to store each character (wide 
// * chars), and so the character é has a code point of U+00e9, which is encoded 
// * as \x00 \xe9. This works okay, except you are limited to the range U+0000 to 
// * U+FFFF (the basic multilingual plane - BMP). There is a way to go beyond this
//* range, but its kind of a hack in my opinion.
//* UTF-8 is a superior because it is backwards compatible with ASCII (1 byte per 
// * character for the first 128 code points), and uses a few bits to indicate if 
// * multi-byte, how many bytes each character takes. This means a UTF-8 character
//* could be 1 byte, 2 bytes, 3 bytes and sometimes even 4 bytes long. Chinese 
// * for instance usually takes up 3 bytes per character.
//* The C standard strlen function returns the number of non-null bytes used to 
// * store a string. If the string is encoded in UTF-8, it is nice to have a way 
// * to see how many UTF-8 characters there are. For instance, ??(ni hao in 
// * chinese) in UTF-8 is encoded as 6 bytes, "\xe4\xbd\xa0\xe5\xa5\xbd".
//* @param str
//* http://www.zedwood.com/article/cpp-utf8-strlen-function
//* @return 
// */
//int utf8_strlen(const string& str)
//{
//    int c,i,ix,q;
//    for (q=0, i=0, ix=str.length(); i < ix; i++, q++)
//    {
//       c = (unsigned char) str[i];
//        if      (c>=0   && c<=127) i+=0;
//        else if ((c & 0xE0) == 0xC0) i+=1;
//        else if ((c & 0xF0) == 0xE0) i+=2;
//        else if ((c & 0xF8) == 0xF0) i+=3;
//        //else if (($c & 0xFC) == 0xF8) i+=4; // 111110bb //byte 5, unnecessary in 4 byte UTF-8
//        //else if (($c & 0xFE) == 0xFC) i+=5; // 1111110b //byte 6, unnecessary in 4 byte UTF-8
//        else return 0;//invalid utf8
//    }
//    return q;
//}
    
    /**
     * From a given string, returns the number of chars its characters (Unicode code point) need to be represented. 
     * 
     * The returning number of chars is always equal or greater than the length of the 
     * given string because some characters need more than 1 (one) char to be represented. 
     * Characters equal to or greater than 0x10000 (65536) need 2 (two) chars. Others need 1 (one).
     * 
     * @param str
     * @return
     * @throws NullPointerException
     * @throws IndexOutOfBoundsException
     * @throws IllegalArgumentException 
     */
    public static int getNumberOfChars(final String str) 
        throws NullPointerException, IndexOutOfBoundsException, IllegalArgumentException{
      int size = 0;

      for (int i = 0; i < str.length(); i++) {
        final int iCharCode = str.codePointAt(i);        
        final boolean isValidCodePoint = Character.isValidCodePoint(iCharCode);
        
        if(!isValidCodePoint){
          throw new IllegalArgumentException("The given string contains a non valid code point at position "+i+".");
        }
        
        final int nChars = Character.charCount(iCharCode);
        //i += nChars;
        size += nChars;
      }    
      
      return size;      
    }
  
}
