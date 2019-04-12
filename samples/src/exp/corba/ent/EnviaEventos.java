package exp.corba.ent;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c)
 * Company:
 * @author
 * @version 1.0
 */

public class EnviaEventos {

  public EnviaEventos() {


/*
    // Building the NameComponent that will be invoked.
    com.ent.corba.nameserver.NamingContext.NameComponent[] nameComp =
      new com.ent.corba.nameserver.NamingContext.NameComponent[1];
    nameComp[0] = new com.ent.corba.nameserver.NamingContext.NameComponent();
    nameComp[0].id = "Regional";
    nameComp[0].kind = "PINT";

    try{
      // Resolving the Authentication object
      com.ent.corba.orb.Object object = CORBAConnections.nameServerRef.resolve(nameComp);

      // Ref narrow
      com.ent.corba.events.EventConsumer.EventConsumer eventConsumer =
        com.ent.corba.events.EventConsumer.EventConsumer._narrow(object);

//System.out.println("SecurityService SecurityContext = SecurityService._narrow(object);");

       com.ent.corba.events.EventConsumer.DateTime d =
        new  com.ent.corba.events.EventConsumer.DateTime();
        d.year = 1999;
        d.month = 1;
        d.day = 9;
        d.hour = 11;
        d.minute = 12;
        d.second = 12;

      com.ent.corba.events.EventConsumer.EventStruct s =
        new com.ent.corba.events.EventConsumer.EventStruct();
      s.eventDescr = "descricao";
      s.eventInfo = s.eventDescr.getBytes();

      eventConsumer.DeliverEvent(d,2,"AAAAAAAAAA",s);







    }catch(com.ent.corba.nameserver.NamingContext.NamingContext_NotFound nf){
      //TRATAR O ERRO
      System.out.println("["+(new Date(System.currentTimeMillis())).toString() +
                "] NamingContext_NotFound: [SecurityService] "+nf.getMessage());
      nf.printStackTrace(System.out);

    }catch(com.ent.corba.nameserver.NamingContext.NamingContext_CannotProceed cp){
      //TRATAR O ERRO
      System.out.println("["+(new Date(System.currentTimeMillis())).toString() +
          "] NamingContext_CannotProceed: [SecurityService] "+cp.getMessage());
      cp.printStackTrace(System.out);

    }catch(com.ent.corba.nameserver.NamingContext.NamingContext_InvalidName in){
      //TRATAR O ERRO
      System.out.println("["+(new Date(System.currentTimeMillis())).toString() +
            "] NamingContext_InvalidName: [SecurityService] "+in.getMessage());
      in.printStackTrace(System.out);

    }catch (Exception e) {
      System.out.println("["+(new Date(System.currentTimeMillis())).toString() +
        "] ERROR: " + e);


    }

        try{

        }catch(Exception e){

          System.out.println("ERRO na sending event");

        }



*/




  }
  public static void main(String[] args) {
    EnviaEventos enviaEventos1 = new EnviaEventos();
  }
}