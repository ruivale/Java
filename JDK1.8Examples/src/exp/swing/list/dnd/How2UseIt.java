package exp.swing.list.dnd;

import java.awt.dnd.*;
import javax.swing.*;
import java.awt.datatransfer.*;


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
public class How2UseIt {



  public How2UseIt() {
    final DefaultListModel listModel = new DefaultListModel();
    final JList list = new JList(listModel);

    new DropTarget(list, new ListDropListener(){
        public void drop(DropTargetDropEvent dtde){
            super.drop(dtde);
            JList list = (JList)dtde.getDropTargetContext().getComponent();
            try{
                String data = (String)dtde.getTransferable().getTransferData(DataFlavor.stringFlavor);
                if(before==null){
                    listModel.setElementAt(data, listIndex);
                    list.setSelectedIndex(listIndex);
                }else if(before.equals(Boolean.TRUE)){
                    listModel.insertElementAt(data, listIndex);
                    list.setSelectedIndex(listIndex);
                }else{
                    if(listIndex<listModel.size()){
                        listModel.insertElementAt(data, listIndex+1);
                        list.setSelectedIndex(listIndex+1);
                    }else{
                        listModel.addElement(data);
                        list.setSelectedIndex(listModel.getSize()-1);
                    }
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
});  }
}
