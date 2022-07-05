package exp.corba;


import com.ent.corba.NameService.NamingContext.NamingContext;
import com.ent.corba.EventService.EventService;
import com.ent.corba.orb.ORB;
import com.ent.corba.NameService.NamingContext.NamingContextHelper;
import com.ent.corba.EventService.EventServiceHelper;
import com.ent.corba.EventService.Property;
import com.ent.corba.EventService.EventConstants;


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
public class SIPWatchDog {


    /** The object request broker. */
    private static ORB orb;

    /** The name server reference. */
    private static NamingContext nameServerRef = null;

    /** The event server reference. */
    private static EventService eventServerRef = null;



    /**
     * @param args
     */
    public static void main(String[] args) {

       // ORB
       orb = ORB.init();

        // NAME SERVER
        final com.ent.corba.orb.Object objRef = orb.resolve_initial_services("NameServer");

        if(objRef==null)
            System.out.println("objRef is null");

        // Using the com.ent.CORBA.nameServer package.
        nameServerRef = NamingContextHelper._narrow(objRef);


        if(nameServerRef==null)
            System.out.println("nameServerRef is null");

        //EVENT SERVER;
        final String[][] nameComps = {
                {"Event", "Server"},
                {"1", null}};

        /***
        // Building the NameComponent that will be invoked.
        final NameComponent[] nameComp = new NameComponent[nameComps.length];

        // For all nameComps components, creates a NameComponent.
        for (int i = 0; i < nameComps.length; i++) {
            nameComp[i] = new NameComponent();
            nameComp[i].id = nameComps[i][0];
            nameComp[i].kind = nameComps[i][1];
        }
        /**/

     // Building the NameComponent that will be invoked.
      final com.ent.corba.NameService.NamingContext.NameComponent[] nameComp =
          new com.ent.corba.NameService.NamingContext.NameComponent[
          nameComps.length];

      // For all nameComps components, creates a NameComponent.
      for (int i = 0; i < nameComps.length; i++) {
        nameComp[i] =
            new com.ent.corba.NameService.NamingContext.NameComponent();
        nameComp[i].id = nameComps[i][0];
        nameComp[i].kind = nameComps[i][1];

      }


        // Resolving the object
        com.ent.corba.orb.Object object=null;
        try {
            object = nameServerRef.resolve(nameComp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Ref narrow
        eventServerRef = EventServiceHelper._narrow(object);

/***
        Event_Info  evtInfo = new Event_Info();

        //evtInfo.nEqKind = ;
        int nIDEvent=0, nIDOrig=0;
        short nOrig=0;

        //COLOCAR OS VALORES CORRECTOS
        evtInfo.nIDEvent = nIDEvent;
        evtInfo.nOrig = nOrig;
        evtInfo.nIDOrig = nIDOrig;



        final Property properties[] = new Property[4];
        for (int i = 0; i < properties.length; i++) {
            properties[i] = new Property();
        }

        //COLOCAR AS STRINGS CORRECTAS
        String eventDescr="1", identifier="2", severity="3";

        properties[0].key = EventConstants.KEY_EVENTDESCRIPTION;
        properties[0].value = eventDescr;

        properties[1].key = EventConstants.KEY_EVENTSEVERITY;
        properties[1].value = new Integer(severity).toString();

        properties[2].key = EventConstants.KEY_EVENTAPP;
        properties[2].value = identifier;

        properties[3].key = EventConstants.KEY_EVENTSTATUS;
        properties[3].value = EventConstants.VALUE_STATUS_INFO;
*/
    }

}
