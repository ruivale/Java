package exp.swing.borders.highligthtitledborder;

import javax.swing.*;
import java.awt.GridLayout;

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
public class FocusOwnerDemo extends JFrame{
    public FocusOwnerDemo(){
        super("Focus Tracking - santhosh@in.fiorano.com");
        JTitledPanel tp1 = new JTitledPanel("First FileChooser");
        tp1.getContents().add(new JFileChooser());

        JTitledPanel tp2 = new JTitledPanel("Second FileChooser");
        tp2.getContents().add(new JFileChooser());

        JPanel contents = (JPanel)getContentPane();
        contents.setLayout(new GridLayout(2, 1));
        contents.add(tp1);
        contents.add(tp2);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args){
        new FocusOwnerDemo().setVisible(true);
    }
}
