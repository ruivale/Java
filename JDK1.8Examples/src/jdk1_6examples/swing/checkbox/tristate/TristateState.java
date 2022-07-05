package jdk1_6examples.swing.checkbox.tristate;


/**
 * <p>Classname: </p>
 *
 * <p>Description: Java 6, aka JDK1.6, examples ...</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ENT, S.A.</p>
 *
 * @author rUI vALE - rui dot vale at ent dot efacec dot pt
 * @version 1.0
 */
public enum TristateState {
  SELECTED {
    public TristateState next() {
      return INDETERMINATE;
    }
  },
  INDETERMINATE {
    public TristateState next() {
      return DESELECTED;
    }
  },
  DESELECTED {
    public TristateState next() {
      return SELECTED;
    }
  };

  public abstract TristateState next();
}
