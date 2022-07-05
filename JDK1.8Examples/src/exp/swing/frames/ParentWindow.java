package exp.swing.frames;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ParentWindow extends JFrame implements ItemListener
{
  JCheckBox chk = new JCheckBox("Open new window");
  ChildWindow cw;
  public ParentWindow()
  {
    setLocation(300,200);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    JPanel jp = new JPanel();
    chk.addItemListener(this);
    jp.add(chk);
    getContentPane().add(jp);
    pack();
  }
  public void itemStateChanged(ItemEvent ie)
  {
    int state = ie.getStateChange();
    if(state == ItemEvent.SELECTED)
    {
      cw = new ChildWindow(this);
    }

    if(state == ItemEvent.DESELECTED)
    {
      //other cose associated with uncheck event
      cw.dispose();
    }
  }
  public static void main(String args[]){new ParentWindow().setVisible(true);}
}
class ChildWindow extends JFrame
{
  ParentWindow parent;
  public ChildWindow(ParentWindow pw)
  {
    parent = pw;
    setLocation(300,200);
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        parent.chk.setSelected(false);}}); //<-----------------
    JPanel jp = new JPanel();
    jp.add(new JLabel("Child Window"));
    getContentPane().add(jp);
    pack();
    setVisible(true);
  }
}
