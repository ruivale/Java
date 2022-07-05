package exp.swing.borders.highligthtitledborder;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.applet.Applet;


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
public abstract class FocusOwnerTracker implements PropertyChangeListener{
    private static final String PERMANENT_FOCUS_OWNER = "permanentFocusOwner";

    private KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    private Component comp;
    private boolean inside;

    public FocusOwnerTracker(Component comp){
        this.comp = comp;
    }

    public boolean isFocusInside(){
        return isFocusInside(false);
    }

    private boolean isFocusInside(boolean find){
        if(!find)
            return inside;

        Component c = focusManager.getPermanentFocusOwner();
        while(c!=null){
            if(c==comp){
                return true;
            } else if((c instanceof Window) ||
                    (c instanceof Applet && c.getParent()==null)){
                if(c==SwingUtilities.getRoot(comp)){
                    return false;
                }
                break;
            }
            c = c.getParent();
        }
        return false;
    }

    public void start(){
        focusManager.addPropertyChangeListener(PERMANENT_FOCUS_OWNER, this);
        inside = isFocusInside(true);
    }

    public void stop(){
        focusManager.removePropertyChangeListener(PERMANENT_FOCUS_OWNER, this);
    }

    public void propertyChange(PropertyChangeEvent evt){
        boolean inside = isFocusInside(true);
        if(this.inside!=inside){
            if(inside)
                focusGained();
            else
                focusLost();
            this.inside = inside;
        }
    }

    public abstract void focusLost();
    public abstract void focusGained();
}
