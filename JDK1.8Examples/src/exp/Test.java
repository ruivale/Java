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

public class Test
    extends JFrame {

  public Component glassPane; // = new CustomGlassPane();
  JRadioButton rb = new JRadioButton();
  JButton button = new JButton("show glass pane");
  JDesktopPane jdp = new JDesktopPane();

  public int count = 0;

  /*
    resize, closable,max, icon
   */
  JInternalFrame jif = new JInternalFrame("jif", false, true, false, false);
  JInternalFrame jif2 = new JInternalFrame("jif2");

  public Test myself;

  public Test() {

    this.myself = this;

//	jdp.putClientProperty("JDesktopPane.dragMode", "outline");

    glassPane = new CustomGlassPane(myself);

    Container contentPane = getContentPane();

    contentPane.setLayout(new BorderLayout());
    //contentPane.setLayout(null);


    //jdp.setLayout(new BorderLayout());
    jdp.setLayout(null);

//        jif.setGlassPane(glassPane);
    JButton b = new JButton("BOTAO DENTRO DA JINTERNAL FRAME");
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        count++;
        jif.setGlassPane(glassPane);
//                                    jif.setGlassPane(new CustomGlassPane(myself));
        jif.getGlassPane().setVisible(true);

        System.err.println(
            "1º BOTAO NA JIF1º BOTAO NA JIF1º BOTAO NA JIF1º BOTAO NA JIF1º BOTAO NA JIF");
      }
    });
    b.setBounds(10, 10, 180, 20);
    /*
            JButton b2 = new JButton("BOTAO DENTRO DA JINTERNAL FRAME");
            b2.addActionListener(new ActionListener() {
     public void actionPerformed(ActionEvent e) {

                                        count++;
     jif.setGlassPane(new CustomGlassPaneJIF(myself));
                                        jif.getGlassPane().setVisible(true);


                                        System.err.println("1º BOTAO NA JIF1º BOTAO NA JIF1º BOTAO NA JIF1º BOTAO NA JIF1º BOTAO NA JIF");
                                    }
                                });
            b2.setBounds(10,40,180,20);
     */


    /*
            jif.addInternalFrameListener(new InternalFrameListener(){
              public void internalFrameActivated(InternalFrameEvent e){

//            if(jif.getGlassPane().isVisible())
//              jif.getGlassPane().setVisible(true);

//            JOptionPane.showMessageDialog(null,"activated");
              }
              public void internalFrameOpened(InternalFrameEvent e){
//            JOptionPane.showMessageDialog(null,"opened");
              }
              public void internalFrameClosed(InternalFrameEvent e){
//            JOptionPane.showMessageDialog(null,"internalFrameClosed");
              }
              public void internalFrameClosing(InternalFrameEvent e){
//            JOptionPane.showMessageDialog(null,"internalFrameClosing");
              }
              public void internalFrameIconified(InternalFrameEvent e){
//            JOptionPane.showMessageDialog(null,"internalFrameIconified");
              }
              public void internalFrameDeiconified(InternalFrameEvent e){
//            JOptionPane.showMessageDialog(null,"internalFrameDeiconified");
              }
              public void internalFrameDeactivated(InternalFrameEvent e){

//            if(jif.getGlassPane().isVisible())
      //            jif.getGlassPane().setVisible(true);

              }
            });
     */

    jif.getContentPane().setLayout(null);
    //        jif.getContentPane().setLayout(new BorderLayout());
    jif.getContentPane().add(b);
//        jif.getContentPane().add(b2);
    jif.setBounds(10, 10, 400, 350);
    jif.setVisible(true);

    jif2.getContentPane().setLayout(new BorderLayout());
    //  	jif2.getContentPane().add(b);
    jif2.setBounds(300, 300, 200, 200);
    jif2.setVisible(true);

    jdp.add(jif);
    jdp.add(jif2);

    //		jdp.add(jif, BorderLayout.CENTER);
    //	jdp.add(jif2, BorderLayout.SOUTH);

    button.setBounds(5, 5, 180, 20);
    jdp.setBounds(40, 40, 600, 500);
    //  	contentPane.add(button);
    //	contentPane.add(jdp);
    contentPane.add(button, BorderLayout.NORTH);
    contentPane.add(jdp, BorderLayout.CENTER);

    rb.setIcon(new ImageIcon("duke_standing.gif"));
    rb.setRolloverIcon(new ImageIcon("duke_waving.gif"));

    /*
      button.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {

         jif.setGlassPane(glassPane);

     System.err.println("1º BOTAO1º BOTAO1º BOTAO1º BOTAO1º BOTAO1º BOTAO1º BOTAO");

        glassPane.setVisible(true);
       }
      });
     */
    setSize(800, 600);
    setVisible(true);

  }

  public static void main(String[] args) {
    new Test();
  }

  public void hideGlassPane() {

    System.err.println("HIDEHIDEHIDEHIDEHIDEHIDEHIDE");

    glassPane.setVisible(false);
  }

}

//class CustomGlassPane extends JInternalFrame {
class CustomGlassPane
    extends JPanel {

  public JButton button = new JButton(
      "hfjsdhfjksjkdfhjkshdjkhjksdjkfhjksdkjfhjksd");
  private String displayString = "glass pane ...  ";

  //	JDesktopPane jdp = new JDesktopPane();

  /*
    resize, closable,max, icon
   */
  JInternalFrame jif = new JInternalFrame("jif", true, true, true, false);

  Test test;

  public CustomGlassPane(Test test) {

    this.test = test;

    this.setOpaque(false);

    // 		this.getContentPane().setLayout(new BorderLayout());
    setLayout(null);
    //        setLayout(new BorderLayout());

    //  	jdp.setLayout(new BorderLayout());

    jif.getContentPane().setLayout(new BorderLayout());
    JButton b1 = new JButton("" + test.count);
    b1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        System.err.println(
            "CLICKEI NO BOTAO DA 2ª INTERNAL FRAMECLICKEI NO BOTAO DA 2ª INTERNAL FRAME");

        turnVisibilityNull();
      }
    });
    jif.getContentPane().add(
        b1,
        BorderLayout.CENTER);
    jif.setBounds(10, 10, 300, 300);
    jif.setVisible(true);
    jif.requestFocus();

    //		jdp.add(jif, BorderLayout.CENTER);

    //  	add(jif);
    add(jif, BorderLayout.CENTER);

    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {

        System.err.println("CLICKEI NA GLASS PANEL CLICKEI NA GLASSPAnel CLICKEI NA GLASSPANEL CLICKEI NA GLASS");

        //				setVisible(false);
      }
    });
  }

  void turnVisibilityNull() {

    test.hideGlassPane();

//        setVisible(false);
    //      test.glassPane = null;
  }

}

/*
 class CustomGlassPaneJIF extends JInternalFrame {

    public JButton button = new JButton("hfjsdhfjksjkdfhjkshdjkhjksdjkfhjksdkjfhjksd");

    Test test;

    public CustomGlassPaneJIF(Test test) {


        super("jif",true,true,true,false);

        this.test = test;

        getContentPane().setLayout(null);

        JButton b1 = new JButton(""+test.count);
        b1.addActionListener(new ActionListener(){
                                 public void actionPerformed(ActionEvent e){

                                     System.err.println("CLICKEI NO BOTAO DA 2ª INTERNAL FRAMECLICKEI NO BOTAO DA 2ª INTERNAL FRAME");

                                     turnVisibilityNull();
                                 }
                             });
        b1.setBounds(10,10,100,30);
        getContentPane().add(b1);

        setBounds(10,10,300,300);
        setVisible(true);
        requestFocus();

//        addMouseListener(new MouseAdapter() {
  //                           public void mousePressed(MouseEvent e) {
//
  //                               System.err.println("CLICKEI NA GLASS PANEL CLICKEI NA GLASSPAnel CLICKEI NA GLASSPANEL CLICKEI NA GLASS");
//
  //                               //				setVisible(false);
    //                         }
      //                   });

    }

    void turnVisibilityNull(){
        setVisible(false);
        dispose();
    }

 }
 */
