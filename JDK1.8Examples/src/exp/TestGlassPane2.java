

/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package exp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;

public class TestGlassPane2 extends JFrame {

    public Component glassPane;// = new CustomGlassPane();
    JRadioButton rb = new JRadioButton();
    JButton button = new JButton("show glass pane");
    JDesktopPane jdp = new JDesktopPane();

    JInternalFrame jInternalFrame;// = new JInternalFrame();

    JButton b = new JButton("JIF  JIF  JIF  JIF  JIF  JIF  JIF  JIF  JIF  ");

    public boolean isGlassPaneVisible = false;

    JPanel p = new JPanel();

    public int count = 0;

    /*
      resize, closable,max, icon
    */
    JInternalFrame jif = new JInternalFrame("jif", false, true, false, false);
    JInternalFrame jif2 = new JInternalFrame("jif2");


    public TestGlassPane2 myself;


    public TestGlassPane2() {

        this.myself = this;

        jdp.putClientProperty("JDesktopPane.dragMode",
                              "outline");

        //        glassPane = new CustomGlassPane2(myself);

        Container contentPane = getContentPane();

        contentPane.setLayout(new BorderLayout());


        jdp.setLayout(null);



        button.addActionListener(new ActionListener() {
                                     public void actionPerformed(ActionEvent e) {

                                         System.err.println("buttonbuttonbuttonbuttonbuttonbuttonbuttonbuttonbuttonbutton");

                                         jInternalFrame = new JInternalFrame("aakjskasj",true,true,true,true);
                                         //         		jInternalFrame = (new JOptionPane()).createInternalFrame(b,"ruiruirui");
                                         jInternalFrame.getContentPane().setLayout(new BorderLayout());
                                         jInternalFrame.getContentPane().add(new JButton("eeeeeeeee"), BorderLayout.CENTER);
                                         jInternalFrame.setVisible(true);
                                         jInternalFrame.setBounds(5,5,100,200);
                                         jif.getContentPane().add(jInternalFrame);

                                         //           	jdp.repaint();

                                         //          jdp.validate();


                                     }
                                 });


        b.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    /*
                    System.err.println("CLAIASILASIASKAKSALASIASLAISALSAISALSIASLAI");

                                jInternalFrame = new JInternalFrame("aakjskasj",true,true,true,true);
                    //         		jInternalFrame = (new JOptionPane()).createInternalFrame(b,"ruiruirui");
                                                                jInternalFrame.getContentPane().setLayout(new BorderLayout());
                                                jInternalFrame.getContentPane().add(new JButton("eeeeeeeee"), BorderLayout.CENTER);
                                jInternalFrame.setVisible(true);
                                        jdp.add(jInternalFrame);
                    */


                    /*						JOptionPane.showInternalMessageDialog(
                                                                  myself, // parentComponent
                                                                  "Break Time ...", // message
                                                                "Reminder!", // title
                                                                JOptionPane.INFORMATION_MESSAGE); // messageType
                    */



                    glassPane = new CustomGlassPane2(myself);
                    //						isGlassPaneVisible = true;
                    //           	jif.setGlassPane(glassPane);
                    //            jif.getGlassPane().setVisible(true);


                    glassPane.setBounds(0,0,jif.getWidth(),jif.getHeight());
                    glassPane.setVisible(true);
                    jif.getContentPane().add(glassPane,0);
                    //						jif.setContentPane(new CustomGlassPane2(myself));




                    System.err.println("1º BOTAO NA JIF1º BOTAO NA JIF1º BOTAO NA JIF1º BOTAO NA JIF1º BOTAO NA JIF");

                }
            });


        /************************************
        *						VER MELHOR							*
        ************************************/
        //  jif.getAccessibleContext().*Modal();


        b.setBounds(10,10,180,20);


        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBounds(0,0,300,250);
        p.add(b);


        jif.getContentPane().setLayout(null);
        jif.getContentPane().add(new JPanel(),0);
        jif.getContentPane().add(p,1);
        jif.setBounds(10,10,300,250);
        jif.setVisible(true);

        jif2.getContentPane().setLayout(new BorderLayout());
        jif2.setBounds(300,300,200,200);
        jif2.setVisible(true);


        jdp.add(jif);

        jdp.add(jif2);


        button.setBounds(5,5,180,20);
        jdp.setBounds(40,40,600,500);


        contentPane.add(new MultiLineLabel("  rui\n  aaaaa\n vale"), BorderLayout.NORTH);
//        contentPane.add(button, BorderLayout.NORTH);
        contentPane.add(jdp, BorderLayout.CENTER);
        contentPane.add(new JButton("WEST"), BorderLayout.WEST);


        rb.setIcon(new ImageIcon("duke_standing.gif"));
        rb.setRolloverIcon(new ImageIcon("duke_waving.gif"));

        setSize(800,600);
        setVisible(true);



        jif.addInternalFrameListener(new InternalFrameListener(){
                                         public void internalFrameActivated(InternalFrameEvent e){
                                             System.err.println("O GLASS PANE IS: "+isGlassPaneVisible);
                                             //            if(isGlassPaneVisible)
                                             //            glassPane.setVisible(true);
                                         }
                                         public void internalFrameOpened(InternalFrameEvent e){
                                         }
                                         public void internalFrameClosed(InternalFrameEvent e){
                                         }
                                         public void internalFrameClosing(InternalFrameEvent e){
                                         }
                                         public void internalFrameIconified(InternalFrameEvent e){
                                         }
                                         public void internalFrameDeiconified(InternalFrameEvent e){
                                         }
                                         public void internalFrameDeactivated(InternalFrameEvent e){
                                         }
                                     });

    }

    public void hideGlassPane(){

        System.err.println("HIDEHIDEHIDEHIDEHIDEHIDEHIDE");

        isGlassPaneVisible = false;
        glassPane.setVisible(false);
        glassPane = null;

    }


    public static void main(String[] args) {
        TestGlassPane2 testGlassPane21 = new TestGlassPane2();
    }




}
class CustomGlassPane2 extends JPanel {

    public JButton button = new JButton("hfjsdhfjksjkdfhjkshdjkhjksdjkfhjksdkjfhjksd");

    /*
      resize, closable,max, icon
    */
    JInternalFrame jif = new JInternalFrame("jif",true,false,true,false);


    TestGlassPane2 test;

    public CustomGlassPane2(TestGlassPane2 test) {

        this.test = test;

        this.setOpaque(false);

        setLayout(null);

        jif.getContentPane().setLayout(new BorderLayout());
        JButton b1 = new JButton(""+test.count);
        b1.addActionListener(new ActionListener(){
                                 public void actionPerformed(ActionEvent e){

                                     System.err.println("CLICKEI NO BOTAO DA 2ª INTERNAL FRAMECLICKEI NO BOTAO DA 2ª INTERNAL FRAME");

                                     turnVisibilityNull();
                                 }
                             });
        jif.getContentPane().add(
            b1,
            BorderLayout.CENTER);
        jif.setBounds(10,10,150,150);
        jif.setVisible(true);
        jif.requestFocus();
        add(jif,BorderLayout.CENTER);


        addMouseListener(new MouseAdapter() {
                             public void mousePressed(MouseEvent e) {

                                 System.err.println("CLICKEI NA GLASS PANEL CLICKEI NA GLASSPAnel CLICKEI NA GLASSPANEL CLICKEI NA GLASS");

                                 //				setVisible(false);
                             }
                         });
    }

    void turnVisibilityNull(){

        //    	test.hideGlassPane();

        //        test.b.setText("novonovonovo");
        //      repaint();

        //        try{Thread.sleep(1500);}catch(Exception e){}

        setVisible(false);

    }

}

