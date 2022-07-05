package exp.corba;


import com.ent.corba.orb.*;

import org.omg.CORBA.*;

import java.lang.Exception;


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
public class AuthenticationContext
    extends com.ent.corba.orb.Object {
  //~ Instance fields //////////////////////////////////////////////////////////

  /** .. */
  private Operational op = new Operational();

  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new AuthenticationContext object.
   */
  public AuthenticationContext() {
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param obj  Insert doc ...
   *
   * @return  Insert doc ...
   */
  public static AuthenticationContext _narrow(com.ent.corba.orb.Object obj) {
    if(obj==null) {
      return null;
    }

    AuthenticationContext new_obj = new AuthenticationContext();
    new_obj.index        = obj.index;
    new_obj.nounce       = obj.nounce;
    new_obj.cinfo_list   = obj.cinfo_list;

    return new_obj;
  }

  /**
   * Insert doc ...
   *
   * @param user  Insert doc ...
   * @param password  Insert doc ...
   *
   * @return  Insert doc ...
   *
   * @throws Exception  Insert doc ...
   */
  public boolean authenticate(
    String user,
    String password)
      throws Exception {
    int     call_id     = 0;
    int     et;
    boolean result;
    byte[]  buffer;
    int     offset      = 0;
    int     buffer_size = Marshall.sizeof_long(call_id);

    buffer_size += (Marshall.sizeof_long(index) + Marshall.sizeof_long(nounce));

    buffer_size += Marshall.sizeof_long(0);
    buffer_size += Marshall.sizeof_string("AuthenticationContext");
    buffer_size += Marshall.sizeof_string(user);
    buffer_size += Marshall.sizeof_string(password);
    buffer    = new byte[buffer_size];
    call_id   = op.Prepare((byte)1, cinfo_list);
    Marshall.marshall_long(call_id, buffer, offset);
    Marshall.marshall_long(index, buffer,
      offset += Marshall.sizeof_long(call_id));
    Marshall.marshall_long(nounce, buffer, offset += Marshall.sizeof_long(index));
    Marshall.marshall_long(0, buffer, offset += Marshall.sizeof_long(nounce));
    Marshall.marshall_string("AuthenticationContext", buffer,
      offset += Marshall.sizeof_long(0));
    offset += Marshall.sizeof_string("AuthenticationContext");
    Marshall.marshall_string(user, buffer, offset);
    offset += Marshall.sizeof_string(user);
    Marshall.marshall_string(password, buffer, offset);
    offset += Marshall.sizeof_string(password);

    try {
      op.Invoke(call_id, false, buffer);
      buffer = op.Collect(call_id);
    } catch(SystemException e) {
      throw e;
    }

    offset = Marshall.sizeof_long(call_id);

    try {
      et = Marshall.unmarshall_exception(buffer, offset);
    } catch(SystemException e) {
      throw e;
    }

    offset += Marshall.sizeof_long(et);
    result = Marshall.unmarshall_boolean(buffer, offset);
    offset += Marshall.sizeof_boolean(result);

    return result;
  }
}
