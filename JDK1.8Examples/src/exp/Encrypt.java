
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
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;
import java.util.*;
import java.io.*;
import javax.swing.border.*;


public class Encrypt extends JFrame {
  JFileChooser jfc = new JFileChooser();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButtonShow = new JButton();
  EncryptAlg encrypt = null;
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JTextField jTextField4 = new JTextField();
  JTextField jTextField5 = new JTextField();
  JTextField jTextField6 = new JTextField();
  JTextField jTextField7 = new JTextField();
  JTextField jTextField8 = new JTextField();
  JTextField jTextField9 = new JTextField();
  JTextField jTextField10 = new JTextField();
  JTextField jTextField11 = new JTextField();
  JTextField jTextField12 = new JTextField();
  JTextField jTextField13 = new JTextField();
  JTextField jTextField14 = new JTextField();
  JTextField jTextField15 = new JTextField();
  JTextField jTextField16 = new JTextField();

  JTextField jTextFields[] = {jTextField1, jTextField2, jTextField3, jTextField4, jTextField5,
                              jTextField6, jTextField7, jTextField8, jTextField9, jTextField10,
                              jTextField11, jTextField12, jTextField13, jTextField14, jTextField15,
                              jTextField16 };

  BorderLayout borderLayout1 = new BorderLayout();
  Border border1;
  JLabel jLabel1 = new JLabel();
  JButton jButton3 = new JButton();
  JPanel jPanel3 = new JPanel();
  GridLayout gridLayout2 = new GridLayout();
  JComboBox jComboBox1 = new JComboBox();
  JComboBox jComboBox2 = new JComboBox();
  JComboBox jComboBox3 = new JComboBox();
  JComboBox jComboBox4 = new JComboBox();
  JComboBox jComboBox5 = new JComboBox();
  JComboBox jComboBox6 = new JComboBox();
  JComboBox jComboBox7 = new JComboBox();
  JComboBox jComboBox8 = new JComboBox();
  JComboBox jComboBox9 = new JComboBox();
  JComboBox jComboBox10 = new JComboBox();
  JComboBox jComboBox11 = new JComboBox();
  JComboBox jComboBox12 = new JComboBox();
  JComboBox jComboBox13 = new JComboBox();
  JComboBox jComboBox14 = new JComboBox();
  JComboBox jComboBox15 = new JComboBox();
  JComboBox jComboBox16 = new JComboBox();
  JPanel jPanel4 = new JPanel();
  JTextArea jTextArea1 = new JTextArea();
  GridLayout gridLayout3 = new GridLayout();
  JTextArea jTextArea2 = new JTextArea();
  JTextArea jTextArea3 = new JTextArea();
  JTextArea jTextArea4 = new JTextArea();
  Border border2;

  public Encrypt() {
    try {
      jbInit();
      enableEvents(AWTEvent.WINDOW_EVENT_MASK);
      setTitle(" File Encryption");
      this.setIconImage((new ImageIcon("icon.jpg")).getImage());
      pack();
      setVisible(true);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Encrypt encrypt1 = new Encrypt();
  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.white, Color.white, new Color(99, 99, 99), new Color(142, 142, 142)), BorderFactory.createEmptyBorder(5, 5, 5, 5));
    border2 = BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white, Color.white, new Color(99, 99, 99), new Color(142, 142, 142)), BorderFactory.createEmptyBorder(5, 5, 5, 5));
    jButton1.setText("Encriptar");
    jButton1.setVisible(false);
    jButton1.addActionListener(new Encrypt_jButton1_actionAdapter(this));
    this.getContentPane().setLayout(borderLayout1);
    jButton2.setText("Descriptar");
    jButton2.setVisible(false);
    jButton2.addActionListener(new Encrypt_jButton2_actionAdapter(this));
    jPanel2.setLayout(gridLayout1);
    gridLayout1.setColumns(18);
    gridLayout1.setHgap(2);
    gridLayout1.setVgap(2);
    jPanel2.setBorder(border1);
    borderLayout1.setHgap(5);
    jLabel1.setText("Chave ");
    jTextField1.setMaximumSize(new Dimension(21, 21));
    jButton3.setText("Select File");
    jButton3.addActionListener(new Encrypt_jButton3_actionAdapter(this));
    jButtonShow.setVisible(false);
    jButtonShow.setText("Show result");
    jButtonShow.addActionListener(new Encrypt_jButtonShow_actionAdapter(this));
    jPanel3.setLayout(gridLayout2);
    gridLayout2.setRows(2);
    gridLayout2.setColumns(8);
    jComboBox1.setEditable(true);
    jComboBox2.setEditable(true);
    jComboBox3.setEditable(true);
    jComboBox4.setEditable(true);
    jComboBox5.setEditable(true);
    jComboBox6.setEditable(true);
    jComboBox7.setEditable(true);
    jComboBox8.setEditable(true);
    jComboBox9.setEditable(true);
    jComboBox10.setEditable(true);
    jComboBox11.setEditable(true);
    jComboBox12.setEditable(true);
    jComboBox13.setEditable(true);
    jComboBox14.setEditable(true);
    jComboBox15.setEditable(true);
    jComboBox16.setEditable(true);
    jTextArea1.setLineWrap(true);
    jTextArea1.setCaretColor(Color.yellow);
    jTextArea1.setBackground(Color.blue);
    jTextArea1.setText("This program is protected by MY laws.");
    jTextArea1.setForeground(Color.white);
    jTextArea1.setEditable(false);
    jTextArea1.setFont(new java.awt.Font("Dialog", 1, 12));
    jPanel4.setLayout(gridLayout3);
    gridLayout3.setRows(4);
    jTextArea2.setLineWrap(true);
    jTextArea2.setCaretColor(Color.yellow);
    jTextArea2.setBackground(Color.blue);
    jTextArea2.setText("Any use or abuse will be severely punished!");
    jTextArea2.setForeground(Color.white);
    jTextArea2.setEditable(false);
    jTextArea2.setFont(new java.awt.Font("Dialog", 1, 12));
    jTextArea3.setLineWrap(true);
    jTextArea3.setCaretColor(Color.yellow);
    jTextArea3.setBackground(Color.blue);
    jTextArea3.setText("Copyright (c) 2000 GODOVA. All Rights Reserved.");
    jTextArea3.setForeground(Color.white);
    jTextArea3.setEditable(false);
    jTextArea3.setFont(new java.awt.Font("Dialog", 1, 12));
    jTextArea4.setPreferredSize(new Dimension(300, 17));
    jTextArea4.setCaretColor(Color.yellow);
    jTextArea4.setBackground(Color.blue);
    jTextArea4.setText("File Encryptor 1.0 ");
    jTextArea4.setForeground(Color.white);
    jTextArea4.setEditable(false);
    jTextArea4.setFont(new java.awt.Font("Dialog", 1, 12));
    jPanel4.setBorder(border2);
    this.getContentPane().add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(jButton3, null);
    jPanel1.add(jButtonShow, null);
    jPanel1.add(jButton1, null);
    jPanel1.add(jButton2, null);
    this.getContentPane().add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jLabel1, null);
    jPanel2.add(jTextField1, null);
    jPanel2.add(jTextField16, null);
    jPanel2.add(jTextField15, null);
    jPanel2.add(jTextField14, null);
    jPanel2.add(jTextField13, null);
    jPanel2.add(jTextField12, null);
    jPanel2.add(jTextField11, null);
    jPanel2.add(jTextField10, null);
    jPanel2.add(jTextField9, null);
    jPanel2.add(jTextField8, null);
    jPanel2.add(jTextField7, null);
    jPanel2.add(jTextField6, null);
    jPanel2.add(jTextField5, null);
    jPanel2.add(jTextField4, null);
    jPanel2.add(jTextField3, null);
    jPanel2.add(jTextField2, null);
    //		jPanel2.setVisible(false);

    this.getContentPane().add(jPanel3, BorderLayout.WEST);
    jPanel3.add(jComboBox16, null);
    jPanel3.add(jComboBox15, null);
    jPanel3.add(jComboBox14, null);
    jPanel3.add(jComboBox13, null);
    jPanel3.add(jComboBox12, null);
    jPanel3.add(jComboBox11, null);
    jPanel3.add(jComboBox10, null);
    jPanel3.add(jComboBox9, null);
    jPanel3.add(jComboBox8, null);
    jPanel3.add(jComboBox7, null);
    jPanel3.add(jComboBox6, null);
    jPanel3.add(jComboBox5, null);
    jPanel3.add(jComboBox4, null);
    jPanel3.add(jComboBox3, null);
    jPanel3.add(jComboBox2, null);
    jPanel3.add(jComboBox1, null);
    this.getContentPane().add(jPanel4, BorderLayout.CENTER);
    jPanel4.add(jTextArea4, null);
    jPanel4.add(jTextArea3, null);
    jPanel4.add(jTextArea1, null);
    jPanel4.add(jTextArea2, null);
    jPanel3.setVisible(false);
  }

  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

  void jButton1_actionPerformed(ActionEvent e) {

    byte key[] = new byte[16];

    for (int i = 0; i < 16; i++)
      key[i] = (byte)((new Integer(jTextFields[i].getText())).intValue());

    encrypt = new EncryptAlg(key);
    encrypt.encriptFile((jfc.getSelectedFile()).getAbsolutePath());
    encrypt = null;

  }

  void jButton2_actionPerformed(ActionEvent e) {

    byte key[] = new byte[16];

    for (int i = 0; i < 16; i++)
      key[i] = (byte)((new Integer(jTextFields[i].getText())).intValue());

    encrypt = new EncryptAlg(key);
    encrypt.decryptToFile((jfc.getSelectedFile()).getAbsolutePath());
    encrypt = null;


  }


  void jButton3_actionPerformed(ActionEvent e) {

    jfc.showDialog(this, "Select a file");

    if (jfc.getSelectedFile() != null && jfc.getSelectedFile().isFile()) {
      jButton1.setVisible(true);
      jButton2.setVisible(true);
      jButtonShow.setVisible(true);

      jPanel2.setVisible(true);
      pack();
    }
  }

  // show the resulting file in a window.
  void jButtonShow_actionPerformed(ActionEvent e) {
    byte key[] = new byte[16];

    for (int i = 0; i < 16; i++)
      key[i] = (byte)((new Integer(jTextFields[i].getText())).intValue());

    encrypt = new EncryptAlg(key);
    encrypt.decryptToFile((jfc.getSelectedFile()).getAbsolutePath());
    JDialog dia = new JDialog(this, "Decrypt", true);
    dia.getContentPane().setLayout(new BorderLayout());

    try {
      String resultingFileName = (jfc.getSelectedFile()).getAbsolutePath().substring(0,
                            (jfc.getSelectedFile()).getAbsolutePath().lastIndexOf(".")) + ".txt";
      File _file = new File(resultingFileName);
      RandomAccessFile file = new RandomAccessFile(_file, "r");
      String fileContents = "";
      String line = "";
      while ((line = file.readLine()) != null) {
        fileContents = fileContents + line + "\n";
      }
      JTextArea jtp = new JTextArea(fileContents);
      dia.getContentPane().add(jtp, BorderLayout.CENTER);
      dia.pack();
      dia.setVisible(true);

      if (!(new File(resultingFileName)).delete()) {
        System.out.println("Cannot delete file [" + resultingFileName + "]!");
      }
    } catch (Exception ex) {
      System.out.println("File not found! ERROR: " + ex.getMessage());
    }
    encrypt = null;

  }

}


/**
 *
 */
class Encrypt_jButton1_actionAdapter implements java.awt.event.ActionListener {
  Encrypt adaptee;

  Encrypt_jButton1_actionAdapter(Encrypt adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class Encrypt_jButton2_actionAdapter implements java.awt.event.ActionListener {
  Encrypt adaptee;

  Encrypt_jButton2_actionAdapter(Encrypt adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}


/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////


class EncryptAlg {

  public byte[] key;

  public EncryptAlg(byte[] key) {
    this.key = key;
  }





  /**
   *
   */
  public void decryptToFile(String file) {
    byte array[] = new byte[16];
    FileInputStream myfile;
    FileOutputStream myOutFile;
    Twofish_Algorithm alg = new Twofish_Algorithm();
    java.lang.Object _key;
    byte array_out[] = new byte[16];

    try {
      _key = alg.makeKey(key);
    } catch (java.security.InvalidKeyException e) {
      return ;
    }

    try {
      myfile = new FileInputStream(file);
      myOutFile = new FileOutputStream(file.substring(0, file.indexOf(".")) + ".txt");

      while (myfile.read(array) != -1) {
        array_out = new byte[16];
        //          array_out = alg.blockDecrypt(array, 0, _key);
        myOutFile.write(array_out);
      }
      myfile.close();
      myOutFile.close();
    } catch (IOException e) {}


  }


  /**
   *
   */
  public byte[] encrypt(byte array[]) {

    byte array_out[] = new byte[16];
    byte array_in[] = new byte[16];
    Twofish_Algorithm alg = new Twofish_Algorithm();

    try {
      java.lang.Object _key = alg.makeKey(key);
      array_in = array;
      //      array_out = alg.blockEncrypt(array_in, 0, _key);
    }
    catch (Exception exp) {}


    return array_out;
  }


  /**
   *
   */
  public byte[] encryptString(byte[] bytes) {

    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < bytes.length; i++)
      sb.append((char)bytes[i]);

    String str = sb.toString();

    int tamanho = str.length();
    int t_total = (int)(tamanho / 16) + 1;
    byte b_tmp[] = new byte[16];
    String tmp;
    byte total[] = new byte[t_total * 16];
    int a, b;
    byte t_2[];

    for (a = 0; a <= tamanho; a += 16) {

      if ((a + 16) > tamanho) {
        tmp = str.substring(a, tamanho);
        for (int t_1 = 0; t_1 < a + 16-tamanho; t_1++)
          tmp += ' ';
      } else
        tmp = str.substring(a, a + 16);

      b_tmp = new byte[16];
      t_2 = new byte[16];
      b_tmp = tmp.getBytes();
      t_2 = encrypt(b_tmp);

      for (int v2 = a; v2 < a + 16; v2++)
        total[v2] = t_2[v2 - a];

    }

    return total;

  }



  public void encriptFile(String inFile) {

    FileOutputStream myOutFile;
    FileInputStream myInFile;
    StringBuffer sb = new StringBuffer(inFile.substring(0, inFile.lastIndexOf(".")));
    String line;
    byte b[];

    try {
      myInFile = new FileInputStream(inFile);
      b = new byte[myInFile.available()];
      sb.append(".enc");
      myOutFile = new FileOutputStream(sb.toString(), true);

      int g, i = 0;
      while ((g = myInFile.read()) != -1) {
        b[i++] = (byte)g;
      }
      myOutFile.write(encryptString(b));

      myOutFile.close();
      myOutFile = null;
      myInFile.close();
      myInFile = null;

    } catch (IOException e) {}



  }
}


////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////


class Encrypt_jButton3_actionAdapter implements java.awt.event.ActionListener {
  Encrypt adaptee;

  Encrypt_jButton3_actionAdapter(Encrypt adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}


class Encrypt_jButtonShow_actionAdapter implements java.awt.event.ActionListener {
  Encrypt adaptee;

  Encrypt_jButtonShow_actionAdapter(Encrypt adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButtonShow_actionPerformed(e);
  }
}



