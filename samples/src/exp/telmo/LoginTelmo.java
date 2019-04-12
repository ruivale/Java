package exp.telmo;


/**
 * Title:        Plataforma Integradora<p>
 * Description:  Codigo para a plataforma integradora.
 * Este codigo e ainda muito precoce em termos de
 * especificação.
 * <p>
 * Copyright:    Copyright (c) Telmo Sa<p>
 * Company:      ENT<p>
 * @author Telmo Sa
 * @version 1.0
 */

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.sql.*;
import java.util.*;
import java.io.*;

/*
import pint.login.Login.*;
import pint.comunicacoes.*;
import pint.ObjsDados.*;
import pint.ObjsDados.UserDataGenCode.*;
*/

/**
 *
 * 23-5-2000,tsa Criacao deste comentarios
 * Esta classe como o nome diz, trata do login do utilizadores na plataforma
 * integradora.
 * Esta classe implementa um interface (LoginIntf) que permite obter uma
 * abstracao da implementacao da classe base(neste caso chama-se login).
 * Desta forma poderemos alterar esta classe ou ate desenvolver uma outra
 * sem afectar o codigo geral.
 *
 * 2-8-2000,tsa Alteracao de fundo. Deixou de ser uma aplicacao std alone
 * para passar a ser distribuida. O processo de login e agora feito via CORBA
 * O pedido e primeiramente feito ao PCC e se este nao estiver disponivel
 * passa-se para um modo de operacao local em que o utilizador tem os seus
 * previlegios reduzidos.
 *
 */
public class LoginTelmo extends JDialog {
  /**    */
  Border border1;
  /**    */
  Border border2;
  /**    */
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  /**    */
  JLabel userName = new JLabel();
  /**    */
  JLabel passWord = new JLabel();
  /**    */
  JButton okButton = new JButton();
  /**    */
  JButton cleanButton = new JButton();
  /**    */
  /**    */
  JPanel contentpane;
  /**    */
  JPasswordField passWordTextField = new JPasswordField();
  /**    */
  Statement stmt = null;
  /**    */
  ResultSet result = null;
  /**    */
//  UserData userData = null;

  /**    */
  public LoginTelmo() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**    */
  private void jbInit() throws Exception {

    userName.setText("User Name");
    border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(142, 142, 142));
    border2 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(142, 142, 142));
    this.getContentPane().setLayout(gridBagLayout1);
    contentpane = (JPanel)this.getContentPane();
    passWord.setText("Password");
    okButton.setPreferredSize(new Dimension(79, 23));
    okButton.setText("Ok");
    okButton.addActionListener(new java.awt.event.ActionListener() {

      /**        */
      public void actionPerformed(ActionEvent e) {
        okButton_actionPerformed(e);
      }
    });
    cleanButton.setText("Clean");
    cleanButton.addMouseListener(new java.awt.event.MouseAdapter() {

      /**        */
      public void mouseClicked(MouseEvent e) {
        cleanButton_mouseClicked(e);
      }
    });
    this.setTitle("Login");
    this.setModal(true);
    this.setResizable(false);
    this.addFocusListener(new java.awt.event.FocusAdapter() {

      /**        */
      public void focusLost(FocusEvent e) {
        this_focusLost(e);
      }
    });

    passWordTextField.addKeyListener(new java.awt.event.KeyAdapter() {

      /**        */
      public void keyPressed(KeyEvent e) {
        passWordTextField_keyPressed(e);
      }
    });
    contentpane.add(passWord, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 10, 0, 10), 0, 0));
    contentpane.add(userName, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 10, 0, 10), 0, 0));
    contentpane.add(okButton, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    contentpane.add(cleanButton, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 0, 10, 0), 0, 0));
    contentpane.add(passWordTextField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 7, 10, 7), 200, 5));

    this.pack();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = contentpane.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

  }

  /**    */
  void this_focusLost(FocusEvent e) {
    this.toFront();
  }

  /**    */
  void cleanButton_mouseClicked(MouseEvent e) {

    // limpo os campos e ponho o cursor no campo user Name
    //userNameTextField.setText("");
    passWordTextField.setText("");
    //userNameTextField.grabFocus();
  }

  //Usando este metodo consegue-se vir buscar a informacao do user em questao.
  /**    */
/*
  public UserData getUserData() {
    return userData;
  }
*/
  /**    */
  public void setVisible(boolean tf) {
    super.setVisible(tf);
  }

  /**
   * Aqui faz-se o processamento dos dados.
   * De realcar que se tenta fazer o login ao PCC e caso isso nao seja possivel
   * tenta-se entao fazer o login no PC. O while que se ve na linha 202 corre no
   * maximo duas vezes e nao mais do que isso, sendo o resultado o login ou a
   * saida da aplicacao, ie, tenta-se o PCC se OK continua, se nao tenta-se o PC
   * se OK continua, se nao baza!!
   *
   * @param e
   */
  void okButton_actionPerformed(ActionEvent e) {
/*
    NameServerRef server = null;
    boolean remoto = true;
    boolean amIPcc = false;

    Properties props = System.getProperties();
    try {
      //FileInputStream in = new FileInputStream("pint/corba.properties");
      FileInputStream in = new FileInputStream("/root/jbproject/pint/classes/pint/corba.properties");
      props.load(in);
      in.close();
    } catch(FileNotFoundException ex1) {
      System.err.println(ex1.getMessage());
    } catch(IOException ex) {
      System.err.println(ex.getMessage());
    }

    try {
      server = new NameServerRef(props.getProperty("pcc.nameserver.url"));

    } catch(NameServerRefNsException e1) {
      JOptionPane.showMessageDialog(this,"Name Server do PCC indisponivel!" +
        " \nTrabalhando em modo local e limitado.", "A V I S O",
        JOptionPane.WARNING_MESSAGE);
      try {
        server = new NameServerRef();
        remoto = false;

      } catch(NameServerRefNsException e2) {
        JOptionPane.showMessageDialog(this,"Name Server do PC indisponivel!" +
          " \nImpossivel Continuar.", "A V I S O",
          JOptionPane.WARNING_MESSAGE);
        System.exit(1);
      }
    }

    while(true) {
      try {
        userData = ((pint.login.Login.Login)LoginHelper.narrow(server.get("Login"))).
          Login(userNameTextField.getText(),new String(passWordTextField.getPassword()) );
        break;

      } catch(UnknownUserException e3) {
        userNameTextField.setText("");
        passWordTextField.setText("");
        JOptionPane.showMessageDialog(this,"Utilizador desconhecido ou password errada",
          "A V I S O",JOptionPane.WARNING_MESSAGE);
        return;

      } catch(DBOperationException e1) {
        userNameTextField.setText("");
        passWordTextField.setText("");

        if(remoto) {
          JOptionPane.showMessageDialog(this,"Base de Dados do PCC indisponivel!" +
            " \nTrabalhando em modo local e limitado.", "A V I S O",
            JOptionPane.WARNING_MESSAGE);
          try {
            server = new NameServerRef();
            remoto = false;
          } catch(NameServerRefNsException e2) {
            JOptionPane.showMessageDialog(this,"Name Server do PC indisponivel!" +
              " \nImpossivel Continuar.", "A V I S O",
              JOptionPane.WARNING_MESSAGE);
            System.exit(1);
          }
        } else {
          JOptionPane.showMessageDialog(this,"Base de Dados do PC indisponivel!" +
            " \nImpossivel Continuar.", "A V I S O",
            JOptionPane.WARNING_MESSAGE);
          System.exit(1);
        }


      } catch(Exception e3) {
        //Se entrar aqui e porque ocorreu algum erro no acesso ao NS
        if(remoto) {
          JOptionPane.showMessageDialog(this,"Name Server do PCC indisponivel!" +
            " \nTrabalhando em modo local e limitado.", "A V I S O",
            JOptionPane.WARNING_MESSAGE);
          try {
            server = new NameServerRef();
            remoto = false;
          } catch(NameServerRefNsException e4) {
            JOptionPane.showMessageDialog(this,"Name Server do PC indisponivel!" +
              " \nImpossivel Continuar.", "A V I S O",
              JOptionPane.WARNING_MESSAGE);
            System.exit(1);
          }
        } else {
          JOptionPane.showMessageDialog(this,"Name Server do PC indisponivel!" +
            " \nImpossivel Continuar.", "A V I S O",
            JOptionPane.WARNING_MESSAGE);
          System.exit(1);
        }
      }
    }

    //User validado. Fecho a dialog de login e sigo para o ambiente.
    userData.permissao = remoto;
    this.dispose();

    //Faco o log deste evento.
    //Se nao sou o PCC tenho que fazer o registo do user no PCC...
    if(!props.getProperty("pcc.nameserver.url").
      equals(props.getProperty("pc.nameserver.url"))) {
        amIPcc = false;
        //lanco uma thread que vai tentando fazer o login no PCC.
        new userLogs(userData,"login",true,amIPcc).start();
    } else {
      amIPcc = true;
    }

    //... e no posto local.
    //Quando sou o posto central o conceito de local e remoto colapsa num so
    //- local.
    new userLogs(userData,"login",false,amIPcc).start();
*/
  }

  /**    */
  void passWordTextField_keyPressed(KeyEvent e) {
    if(e.getKeyCode() == 10)
      okButton_actionPerformed(null);
  }


public static void main(String[] args){

   LoginTelmo lt =  new LoginTelmo();
   lt.setVisible(true);
}


}
