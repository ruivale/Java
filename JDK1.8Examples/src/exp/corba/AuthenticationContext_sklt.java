package exp.corba;

import com.ent.corba.orb.*;
import com.ent.corba.orb_comm.*;
import org.omg.CORBA.*;


class AuthenticationContext_sklt extends Skeleton
{
  private ImplementationDef impl;
  public AuthenticationContext_sklt(ImplementationDef impl)
  {
    this.impl=impl;
  }
  public void run()
  {
    AuthenticationContext_serv serv=new AuthenticationContext_serv();
    byte qi[];
    int offset=0;
    int size;
    byte msg[];
    byte reply[];

    String interfacename;
    int op=0;
    int i;
    while(true)
    {
      msg=impl.operational_chan.Recv();
      qi=impl.operational_chan.Recv();
      msg=impl.operational_chan.Recv();
      if (msg==null)
        break;
      offset=12;
      interfacename=Marshall.unmarshall_string(msg,offset+Marshall.sizeof_long(op));
      if (interfacename.compareTo("AuthenticationContext")!=0)
      {
        INV_OBJREF e=new INV_OBJREF();

        reply=new byte[4+Marshall.sizeof_exception(e)];
        for(i=0;i<4;i++)
          reply[i]=msg[i];
        Marshall.marshall_exception(e,reply,4);
        impl.operational_chan.Send(qi);
        msg=new byte[1];
        msg[0]=0;
        impl.operational_chan.Send(msg);
        impl.operational_chan.Send(reply);
      }
      else
      {
        op=Marshall.unmarshall_long(msg,offset);
        offset+=Marshall.sizeof_long(op);
        offset+=Marshall.sizeof_string(interfacename);
        switch(op)
        {
          case 0:
            {
              String __user__;
              String __password__;
              boolean __result__;
              __user__=Marshall.unmarshall_string(msg,offset);
              offset+=Marshall.sizeof_string(__user__);
              __password__=Marshall.unmarshall_string(msg,offset);
              offset+=Marshall.sizeof_string(__password__);
              try
              {
                __result__=serv.authenticate(__user__,__password__);
              }
              catch(Exception e)
              {
                reply=new byte[4+Marshall.sizeof_exception((SystemException)e)];
                for (i=0;i<4;i++)
                  reply[i]=msg[i];
                Marshall.marshall_exception((SystemException)e,reply,4);
                impl.operational_chan.Send(qi);
                msg=new byte[1];
                msg[0]=0;
                impl.operational_chan.Send(msg);
                impl.operational_chan.Send(reply);
                break;
              }
              size=4+Marshall.sizeof_noexception();
              size+=Marshall.sizeof_boolean(__result__);
              reply=new byte[size];
              for(i=0;i<4;i++)
                reply[i]=msg[i];
              offset=4;
              Marshall.marshall_noexception(reply,offset);
              offset+=Marshall.sizeof_noexception();
              Marshall.marshall_boolean(__result__,reply,offset);
              offset+=Marshall.sizeof_boolean(__result__);
              impl.operational_chan.Send(qi);
              msg=new byte[1];
              msg[0]=0;
              impl.operational_chan.Send(msg);
              impl.operational_chan.Send(reply);
              break;
            }
        }
      }
    }
  }
}

