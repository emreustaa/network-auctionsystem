/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsm.frontend;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author emreu
 */
public class SatilanUrunlerEkrani extends javax.swing.JFrame {

    /**
     * Creates new form SatilanUrunlerEkrani
     */
    DefaultTableModel dtm = new DefaultTableModel();
    public SatilanUrunlerEkrani() {
        initComponents();
        this.setTitle("Satılan Ürünler Ekranı");
        
        String basliklar[]={"Ürün Kodu","Ürün Adı","Başlangıç Fiyatı","Satılan Fiyat"};
        dtm.setColumnIdentifiers(basliklar);
        dtm.addRow(new Object[]{"1356741","Tablo","3500 ₺","8000 ₺"});
        dtm.addRow(new Object[]{"1665685","Kıyafet","2450 ₺","12500 ₺"});
        dtm.addRow(new Object[]{"1121542","Ayakkabı","3125 ₺","250045 ₺"});
        dtm.addRow(new Object[]{"1946591","Sakız","4250 ₺","365000 ₺"});
        dtm.addRow(new Object[]{"1335541","Krampon","3759 ₺","25451 ₺"});
        dtm.addRow(new Object[]{"12154451","Palto","8000 ₺","45454 ₺"});
        dtm.addRow(new Object[]{"13254561","Tablo","1250 ₺","488725 ₺"});
        
        tblSatilanUrunler.setModel(dtm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSatilanUrunler = new javax.swing.JTable();
        btnGeriSatilanUrunler = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Satılan Ürünler Ekranı");

        tblSatilanUrunler.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblSatilanUrunler);

        btnGeriSatilanUrunler.setText("Geri");
        btnGeriSatilanUrunler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeriSatilanUrunlerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGeriSatilanUrunler, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(199, 199, 199))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGeriSatilanUrunler)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGeriSatilanUrunlerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeriSatilanUrunlerActionPerformed
        // TODO add your handling code here:
        MuzayedeEkrani me = new MuzayedeEkrani();
        me.setVisible(true);
        this.dispose();
        
        
    }//GEN-LAST:event_btnGeriSatilanUrunlerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SatilanUrunlerEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SatilanUrunlerEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SatilanUrunlerEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SatilanUrunlerEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SatilanUrunlerEkrani().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGeriSatilanUrunler;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSatilanUrunler;
    // End of variables declaration//GEN-END:variables
}
