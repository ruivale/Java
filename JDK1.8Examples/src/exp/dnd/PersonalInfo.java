/*
 * put your module comment here
 * formatted with JxBeauty (c) johann.langhofer@nextra.at
 */


package  exp.dnd;

import  java.io.*;
import  java.awt.datatransfer.*;
import  java.util.*;


/** This class stores all family data that we use in the example. You should
 note that no error checking is done, but you probably will want to set that up
 in your scheme especially if you are using beans! */
public class PersonalInfo
        implements Transferable, Serializable {
    final public static DataFlavor INFO_FLAVOR = new DataFlavor(PersonalInfo.class,
            "Personal Information");
    static DataFlavor flavors[] =  {
        INFO_FLAVOR
    };
    public static final boolean MALE = true;
    public static final boolean FEMALE = !MALE;
    private String Name = null;
    private boolean Gender = MALE;
    private PersonalInfo Parent = null;
    private Vector Children = null;

    /**
     * put your documentation comment here
     * @param     String name
     * @param     boolean gender
     */
    public PersonalInfo (String name, boolean gender) {
        Children = new Vector();
        Name = name;
        Gender = gender;
    }

    /**
     * put your documentation comment here
     * @return
     */
    public String getName () {
        return  Name;
    }

    /**
     * put your documentation comment here
     * @param name
     */
    public void setName (String name) {
        Name = name;
    }

    /**
     * put your documentation comment here
     * @return
     */
    public boolean getGender () {
        return  Gender;
    }

    /**
     * put your documentation comment here
     * @param gender
     */
    public void setGender (boolean gender) {
        Gender = gender;
    }

    /**
     * put your documentation comment here
     * @return
     */
    public boolean isMale () {
        return  getGender() == MALE;
    }

    /**
     * put your documentation comment here
     * @param info
     */
    public void add (PersonalInfo info) {
        info.setParent(this);
        Children.add(info);
    }

    /**
     * put your documentation comment here
     * @param info
     */
    public void remove (PersonalInfo info) {
        info.setParent(null);
        Children.remove(info);
    }

    /**
     * put your documentation comment here
     * @return
     */
    public PersonalInfo getParent () {
        return  Parent;
    }

    /**
     * put your documentation comment here
     * @param parent
     */
    public void setParent (PersonalInfo parent) {
        Parent = parent;
    }

    /**
     * put your documentation comment here
     * @return
     */
    public Vector getChildren () {
        return  Children;
    }

    /**
     * put your documentation comment here
     * @return
     */
    public Object clone () {
        return  new PersonalInfo(Name, Gender);
    }

    /**
     * put your documentation comment here
     * @return
     */
    public String toString () {
        return  Name;
    }

    // --------- Transferable --------------
    public boolean isDataFlavorSupported (DataFlavor df) {
        return  df.equals(INFO_FLAVOR);
    }

    /** implements Transferable interface */
    public Object getTransferData (DataFlavor df) throws UnsupportedFlavorException,
            IOException {
        if (df.equals(INFO_FLAVOR)) {
            return  this;
        }
        else
            throw  new UnsupportedFlavorException(df);
    }

    /** implements Transferable interface */
    public DataFlavor[] getTransferDataFlavors () {
        return  flavors;
    }

    // --------- Serializable --------------
    private void writeObject (java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    /**
     * put your documentation comment here
     * @param in
     * @exception IOException, ClassNotFoundException
     */
    private void readObject (java.io.ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        in.defaultReadObject();
    }
}



