/*
 * MySWsDecoderSizesAutoLayoutTests.java
 *
 * Created on 8 de Maio de 2008, 15:39
 */

package exp.layouts.sws.autolayouts;


import javax.swing.GroupLayout.Alignment;



/**
 *
 * @author  c2334
 */
public class MySWsDecoderSizesAutoLayoutTests extends javax.swing.JFrame {

    /** Creates new form MySWsDecoderSizesAutoLayoutTests */
    public MySWsDecoderSizesAutoLayoutTests() {
        initComponents();
        
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    jPanel2 = new javax.swing.JPanel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    getContentPane().setLayout(new java.awt.GridBagLayout());

    jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jPanel1.setMinimumSize(new java.awt.Dimension(200, 200));
    jPanel1.setPreferredSize(new java.awt.Dimension(200, 200));

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(Alignment.LEADING)
      .addGap(0, 198, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(Alignment.LEADING)
      .addGap(0, 198, Short.MAX_VALUE)
    );

    getContentPane().add(jPanel1, new java.awt.GridBagConstraints());

    jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    jPanel2.setMinimumSize(new java.awt.Dimension(100, 100));

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(Alignment.LEADING)
      .addGap(0, 198, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(Alignment.LEADING)
      .addGap(0, 198, Short.MAX_VALUE)
    );

    getContentPane().add(jPanel2, new java.awt.GridBagConstraints());

    pack();
  }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MySWsDecoderSizesAutoLayoutTests().setVisible(true);
            }
        });
    }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  // End of variables declaration//GEN-END:variables

}
