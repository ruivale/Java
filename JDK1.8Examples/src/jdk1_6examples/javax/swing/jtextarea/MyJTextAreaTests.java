/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MyJTextAreaTests.java
 *
 * Created on 21/Set/2010, 17:47:18
 */

package jdk1_6examples.javax.swing.jtextarea;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import javax.swing.JFrame;

/**
 *
 * @author 2334
 */
public class MyJTextAreaTests extends javax.swing.JPanel {

    /** Creates new form MyJTextAreaTests */
    public MyJTextAreaTests() {
        initComponents();

        this.jTextArea1.setEditable(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    jTextArea1 = new javax.swing.JTextArea();
    jButton1 = new javax.swing.JButton();
    jTextField1 = new javax.swing.JTextField();

    jTextArea1.setColumns(20);
    jTextArea1.setRows(5);
    jScrollPane1.setViewportView(jTextArea1);

    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("jdk1_6examples/javax/swing/jtextarea/Bundle"); // NOI18N
    jButton1.setText(bundle.getString("MyJTextAreaTests.jButton1.text")); // NOI18N
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    jTextField1.setText(bundle.getString("MyJTextAreaTests.jTextField1.text"));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(47, 47, 47)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1))
          .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(34, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(26, 26, 26)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(27, 27, 27)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jButton1))
        .addContainerGap(200, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      // TODO add your handling code here:

      this.jTextArea1.insert(this.jTextField1.getText()+"\n", 0);
      this.jTextArea1.setCaretPosition(0);

    }//GEN-LAST:event_jButton1ActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextArea jTextArea1;
  private javax.swing.JTextField jTextField1;
  // End of variables declaration//GEN-END:variables

  public static void main(String[] args) {
    MyJTextAreaTests d = new MyJTextAreaTests();
    JFrame                        frame       = new JFrame();
    frame.setLayout(new BorderLayout());
    frame.add(d, BorderLayout.CENTER);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }
}
