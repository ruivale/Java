package exp.corba;


import com.ent.corba.NameService.NamingContext.*;
import com.ent.corba.orb.*;

//import org.omg.CORBA.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;


/**
 * <p>
 * file :       AuthenticationContext_serv.java
 * </p>
 *
 * <p>
 * title:
 * </p>
 *
 * <p>
 * description:
 * </p>
 *
 * <p>
 * company:
 * </p>
 *
 * @author $author$
 * @version $Revision$
 */
public class AuthenticationContext_serv {
  //~ Methods ////////////////////////////////////////////////////////////////
  /**
   * DOC
   */
  public static void Startup() {
    try{
    com.ent.corba.orb.ORB               orb;
    com.ent.corba.orb.BOA               boa;
    com.ent.corba.orb.InterfaceDef      intf =
      new com.ent.corba.orb.InterfaceDef();
    com.ent.corba.orb.ImplementationDef impl =
      new com.ent.corba.orb.ImplementationDef();
    com.ent.corba.orb.Object            obj;
    String                              str;

    orb             = ORB.init();

    com.ent.corba.orb.Object objRef        =
      orb.resolve_initial_services("NameServer");
    NamingContext            nameServerRef =
      NamingContextHelper._narrow(objRef);


    boa             = orb.BOA_Init();
    impl.skeleton   = new AuthenticationContext_sklt(impl);
    obj             = boa.create("1st", intf, impl);
    boa.impl_is_ready(impl);
    str = orb.object_to_string(obj);




    // Building the NameComponent that will be invoked.
    NameComponent[] nameComp = new NameComponent[2];
    nameComp[0]   = new NameComponent();
    nameComp[1]   = new NameComponent();
    //nameComp[0].id     = "1st";
    //nameComp[0].kind   = "";
    nameComp[0].id     = "Authentication1";
    nameComp[0].kind   = "Authentication";
    nameComp[1].id     = "PInt";
    nameComp[1].kind   = "175";

    try {
      //unbind just to be sure
      nameServerRef.unbind(nameComp);
    } catch(Exception e) {
    }


      nameServerRef.bind(nameComp, obj);

    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * DOC
   *
   * @param user
   * @param password
   *
   * @return
   *
   * @throws SystemException
   */
  public boolean authenticate(
    String user,
    String password)
      throws Exception {
    if(user.equals("Rui") && password.equals("Rui")) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * DOC
   *
   * @param args
   */
  public static void main(String[] args) {
    Startup();
    new A();

  }
}


class A
    extends javax.swing.JFrame {
  //~ Instance fields ////////////////////////////////////////////////////////

  AuthenticationContext aa = null;

  //~ Constructors ///////////////////////////////////////////////////////////
  /**
   * Creates a new A object.
   */
  public A() {
    this.setBounds(10, 10, 300, 200);
    JButton a = new JButton("invoke");
    a.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {

System.out.println("(RUI, RUI):"+ aa.authenticate("Rui", "Rui")+".");

          } catch(Exception ex) {
            ex.printStackTrace();
          }
        }
      });

    JButton b = new JButton("init");
    b.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          try {
            com.ent.corba.orb.ORB    orb    = com.ent.corba.orb.ORB.init();

            com.ent.corba.orb.Object objRef =
              orb.resolve_initial_services("NameServer");

            // Using the com.ent.CORBA.nameServer package.
            com.ent.corba.NameService.NamingContext.NamingContext nameServerRef =
              com.ent.corba.NameService.NamingContext.NamingContextHelper._narrow(
                objRef);

            // Building the NameComponent that will be invoked.
            com.ent.corba.NameService.NamingContext.NameComponent[] nameComp =
              new com.ent.corba.NameService.NamingContext.NameComponent[2];
            nameComp[0] =
              new com.ent.corba.NameService.NamingContext.NameComponent();
            nameComp[1] =
              new com.ent.corba.NameService.NamingContext.NameComponent();

            nameComp[0].id     = "Authentication1";
            nameComp[0].kind   = "Authentication";
            nameComp[1].id     = "PInt";
            nameComp[1].kind   = "175";

            com.ent.corba.orb.Object object = nameServerRef.resolve(nameComp);

            // Ref narrow
            aa = AuthenticationContext._narrow(object);


          } catch(Exception ex) {
            ex.printStackTrace();
          }
        }
      });

    this.getContentPane()
        .setLayout(new BorderLayout());
    this.getContentPane()
        .add(a, BorderLayout.NORTH);
    this.getContentPane()
        .add(b, BorderLayout.SOUTH);

    this.setVisible(true);

  }
}
