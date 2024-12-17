package pt.intro.java.ininth;

/**
 * <p>
 * Description: 
 * The notoriety of String and its wide range of applications means we should know it
 * in detail. Besides knowing how to declare and manipulate strings (for example, reverse, and
 * capitalize) developers should understand why this class was designed in a special or different
 * way. More precisely, why is String immutable? Or maybe this question has a better resonance
 * formulated like this—what are the pros and cons of String being immutable?
 *
 * String constant pool or cached pool 
 * One of the reasons in favor of string immutability is
 * represented by the string constant pool (SCP) or cached pool. In order to understand this
 * statement, let's dive a little bit into how the String class works internally. The SCP is a
 * special area in memory (not the normal heap memory) used for the storage of string literals.
 * Let's assume the following three String variables: 
 *    String x = "book"; 
 *    String y = "book"; 
 *    String z = "book"; 
 * 
 * How many String objects have been created? It is tempting to say three, but actually
 * Java creates only one String object with the "book" value. The idea is that everything between
 * quotes is considered as a string literal, and Java stores string literals in this special area of
 * memory called the SCP, by following an algorithm like this (this algorithm is known as string
 * interning): 
 *    When a string literal is created (for example, String x = "book"), Java inspects the
 *        SCP to see whether this string literal exists. 
 *    If the string literal is not found in the SCP,
 *        then a new string object for the string literal is created in the SCP and the corresponding
 *        variable, x, will point to it.
 *    If the string literal is found in the SCP (for example, String y =
 *        "book", String z = "book"), then the new variable will point to the String object (basically, 
 *        all variables that have the same value will point to the same String object).
 * 
 * Pros:
 *    Security:
 *      By having this information immutable, the code becomes secure to a wide range of
 *      security threats (for example, modifying the references accidentally or deliberately).
 *    Thread safety:
 *      Any immutable object is thread-safe by its nature. This means that strings can be shared 
 *      and manipulated by multiple threads, with no risk of corruption and inconsistency.
 *    Hash code caching:
 *      Hash codes should be calculated every time they are involved in hashing specific activities (for
 *      example, searching an element in a collection). Since String is immutable, every string has an 
 *      immutable hash code that can be cached and reused as it cannot be changed after string creation.
 *      
 * Cons:
 *    String cannot be extended
 *    Sensitive data in memory for a long time
 *      Sensitive data in strings (for example, passwords) may reside in memory (in SCP) for a long time. 
 *      Being a cache, the SCP takes advantage of special treatment from the garbage collector.
 *    OutOfMemoryError
 *      The SCP is a small memory zone in comparison with others and can be filled pretty quickly. 
 *      Storing too many string literals in the SCP will lead to OutOfMemoryError.
 * 
 * </p>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 *
 * @since 241213 (Friday ;-) )
 */
public class StringUtils {

  public static void main(final String[] args) {
    final StringUtils clazz = new StringUtils();
  }
}
