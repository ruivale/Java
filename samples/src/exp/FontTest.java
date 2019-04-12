
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package exp;


import java.awt.event.*;
import java.awt.*;

public class FontTest {

    public static void main(String args[]) { new FontTestFrame(); } }

class FontTestFrame extends Frame implements ActionListener {
    MenuBar mb = new MenuBar();
    Menu file = new Menu("File");
    Menu edit = new Menu("Edit");
    Menu view = new Menu("View");
    Menu help = new Menu("Help");
    MenuItem fileOpen = new MenuItem("Open...");
    MenuItem fileSaveAs = new MenuItem("Save As...");
    MenuItem editCut = new MenuItem("Cut");
    MenuItem editCopy = new MenuItem("Copy");
    MenuItem editPaste = new MenuItem("Paste");
    MenuItem helpAbout = new MenuItem("About...");
    Button ok = new Button("OK");
    Button cancel = new Button("Cancel");

    FontTestFrame() {

        super();

        /* Set the layout */
        setLayout(new FlowLayout());

        /* Add menu items to menus */
        file.add(fileOpen);
        file.addSeparator();
        file.add(fileSaveAs);
        edit.add(editCut);
        edit.add(editCopy);
        edit.add(editPaste);
        help.add(helpAbout);


        /* Add menus to menubar */
        mb.add(file); mb.add(edit);
        mb.add(view); mb.add(help);


        /* Set menubar */
        setMenuBar(mb);

        /* Add components */
        add(ok); add(cancel);

        /* Add the action listeners */
        fileOpen.addActionListener(this);
        fileSaveAs.addActionListener(this);
        editCut.addActionListener(this);
        editCopy.addActionListener(this);
        editPaste.addActionListener(this);
        helpAbout.addActionListener(this);

        /* Add the window listener */
        addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent evt) {
                                  dispose();
                                  System.exit(0);
                              }});

        /* Size the frame */
        setSize(200,200);

        /* Center the frame */
        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle frameDim = getBounds();

        setLocation((screenDim.width - frameDim.width) / 2,(screenDim.height - frameDim.height) / 2);

        /* Set the default font for all the components */
        setDefaultFont("Monospaced", Font.BOLD,20,mb);

        /* Show the frame */
        setVisible(true); }

    public void actionPerformed(ActionEvent evt) {
        java.lang.Object obj = evt.getSource();

        if (obj == fileOpen);
        else if (obj == fileSaveAs);
        else if (obj == editCut);
        else if (obj == editCopy);
        else if (obj == editPaste);

        else if (obj == helpAbout);
    }

    public void setDefaultFont(String fontName, int fontStyle, int fontSize, MenuBar mb) {
        Component[] cp = getComponents();
        Font f; int i, menuCount = 0;

        f = new Font(fontName,fontStyle,fontSize);

        if (mb != null) {
            menuCount = mb.getMenuCount();

            /* Cycle through the menu components and set the font */
            for(i = 0; i < menuCount; i++) {
                mb.getMenu(i).setFont(f);
                int menuItemCount = mb.getMenu(i).getItemCount();
                /* Cycle through the menu items and set the font */
                for (int j = 0; j < menuItemCount; j++)
                    mb.getMenu(i).getItem(j).setFont(f);
            }
        }

        /* Cycle through the components and set the font */
        for (i = 0; i < cp.length; i++)
            cp[i].setFont(f);
    }
}
