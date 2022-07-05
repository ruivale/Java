
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package exp;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;


public class MinePint extends JFrame {
    JMenuBar jMenuBar1 = new JMenuBar();
    JToolBar jToolBar1 = new JToolBar();
    JSplitPane jSplitPane1 = new JSplitPane();
    JSplitPane jSplitPane3 = new JSplitPane();
    JPanel jPanel3 = new JPanel();
    JPanel jPanel4 = new JPanel();
    JPanel jPanel5 = new JPanel();
    JPanel jPanel1 = new JPanel();
    Border border1;
    JToolBar jToolBar2 = new JToolBar();
    Border border2;

    public MinePint() {    
      
        try {
            jbInit();
            this.setIconImage((new ImageIcon("C:/MUXManager/images/ENT.jpg")).getImage());
            this.setTitle("  Plataforma Integradora");
            setVisible(true);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MinePint minePint1 = new MinePint();
    }

    private void jbInit() throws Exception {
        border1 = BorderFactory.createBevelBorder(BevelBorder.RAISED,new Color(250, 250, 250),Color.white,new Color(122, 122, 122),new Color(85, 85, 85));
        border2 = BorderFactory.createEtchedBorder(Color.white,new Color(142, 142, 142));
        this.getContentPane().setLayout(null);
        jToolBar1.setBackground(new Color(175, 175, 175));
        jToolBar1.setBorder(border1);
        jToolBar1.setBounds(new Rectangle(1, 19, 728, 30));
        jSplitPane1.setBounds(new Rectangle(2, 50, 725, 360));
        jSplitPane3.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSplitPane3.setBounds(new Rectangle(2, 411, 724, 108));
        jPanel5.setBackground(SystemColor.textHighlight);
        jPanel1.setBackground(new Color(239, 239, 239));
        jPanel4.setBackground(SystemColor.controlLtHighlight);
        jPanel3.setBackground(SystemColor.controlLtHighlight);
        jToolBar2.setBorder(border2);
        jToolBar2.setBounds(new Rectangle(1, 1, 728, 19));
        this.getContentPane().add(jSplitPane1, null);
        this.getContentPane().add(jSplitPane3, null);
        jSplitPane3.add(jPanel3, JSplitPane.TOP);
        jSplitPane3.add(jPanel4, JSplitPane.BOTTOM);
        jSplitPane1.add(jPanel5, JSplitPane.RIGHT);
        jSplitPane1.add(jPanel1, JSplitPane.LEFT);
        jSplitPane1.setDividerLocation(60);
        this.getContentPane().add(jToolBar2, null);
        this.getContentPane().add(jToolBar1, null);

    }
}
