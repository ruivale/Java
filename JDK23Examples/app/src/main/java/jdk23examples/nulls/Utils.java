package jdk23examples.nulls;


import java.util.function.Supplier;
import java.util.logging.Logger;


/**
 * <p>
 * Description:
 * Util class for objects null testing.
 * </p>
 *
 * @author rUI vALE - {ruivale at gmail dot com}
 *
 * @since 241213 (Friday ;-) )
 */
public class Utils {
  /* This class LOGGER */
  private static final Logger LOGGER = Logger.getLogger(Utils.class.getName());

  /* .. */
  private static final long serialVersionUID = 0L;


  private int memoizedHashCode = 0;


  /**
   * 
   * @param <T>
   * @param <X>
   * @param obj
   * @param exception
   * @return
   * @throws X 
   */
  public static <T, X extends Throwable> T requireNonNullElseThrow(T obj, X exception) throws X {
    if (obj == null) {
      throw exception;
    }
    
    return obj;
  }

  /**
   * 
   */
  public static <T, X extends Throwable> T requireNotNullElseThrow(T obj, Supplier<? extends X> exceptionSupplier) throws X {
    if (obj != null) {
      return obj;
    } else {
      throw exceptionSupplier.get();
    }
  }

 /**
  * Returns this class description in a friendly way.
  *
  * @return String description
  */
  public String toString(){
    return new StringBuffer("Utils").append("").toString();
  }

  public static void main(final String[] args){
    final Utils clazz = new Utils();
  }
}
