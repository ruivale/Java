package exp.methods.findmethods;

import com.ent.corba.EventService.Property;


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
public interface FindClassMethodsInt {
  boolean deliverEvent(Property[] p);
  boolean displayEvent(Property[] p, long[] l, byte[] b);
  boolean displayEvent(Property[] p, long l, byte b);
}
