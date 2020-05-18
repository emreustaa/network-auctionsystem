/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsm.frontend.Ekranlar.GirisEkrani;

import com.fsm.frontend.Objects.Message.Message;
import com.fsm.frontend.Objects.User.Credentials.SignUpCredentials;
import com.fsm.frontend.Objects.User.User;
import com.fsm.frontend.Objects.User.UserRepo;
import com.fsm.frontend.Utils.RestUtils;

import javax.swing.*;

/**
 * @author salih
 */
@SuppressWarnings({"FieldCanBeLocal", "SpellCheckingInspection"})
public class KayitEkrani extends javax.swing.JFrame {

    public KayitEkrani() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelKullaniciEkle = new javax.swing.JPanel();
        lblBilgiGiriniz = new javax.swing.JLabel();
        lblKullaniciAdiEkle = new javax.swing.JLabel();
        lblSifreEkle = new javax.swing.JLabel();
        lblEmailEkle = new javax.swing.JLabel();
        lblButceEkle = new javax.swing.JLabel();
        txtKullaniciAdiKayit = new javax.swing.JTextField();
        txtSifreKayit = new javax.swing.JTextField();
        txtEmailKayit = new javax.swing.JTextField();
        txtButceKayit = new javax.swing.JTextField();
        btnKaydet = new javax.swing.JButton();
        btnGeri = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelKullaniciEkle.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblBilgiGiriniz.setText("Lütfen Bilgileri Uygun Şekilde Giriniz");

        lblKullaniciAdiEkle.setText("Kullanıcı Adı :");

        lblSifreEkle.setText("Şifre:");

        lblEmailEkle.setText("E-Mail :");

        lblButceEkle.setText("Bütçe :");

        btnKaydet.setText("Kaydet");
        btnKaydet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKaydetActionPerformed(evt);
            }
        });

        btnGeri.setText("Geri");
        btnGeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeriActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelKullaniciEkleLayout = new javax.swing.GroupLayout(PanelKullaniciEkle);
        PanelKullaniciEkle.setLayout(PanelKullaniciEkleLayout);
        PanelKullaniciEkleLayout.setHorizontalGroup(
                PanelKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelKullaniciEkleLayout.createSequentialGroup()
                                .addGroup(PanelKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(PanelKullaniciEkleLayout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addGroup(PanelKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblKullaniciAdiEkle)
                                                        .addComponent(lblEmailEkle))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(PanelKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(txtEmailKayit, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                        .addComponent(txtKullaniciAdiKayit, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                                .addGap(0, 0, 0)
                                                .addGroup(PanelKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblSifreEkle)
                                                        .addComponent(lblButceEkle))
                                                .addGap(20, 20, 20)
                                                .addGroup(PanelKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(PanelKullaniciEkleLayout.createSequentialGroup()
                                                                .addComponent(txtButceKayit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnGeri, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(PanelKullaniciEkleLayout.createSequentialGroup()
                                                                .addComponent(txtSifreKayit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnKaydet, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(PanelKullaniciEkleLayout.createSequentialGroup()
                                                .addGap(134, 134, 134)
                                                .addComponent(lblBilgiGiriniz)))
                                .addGap(58, 58, 58))
        );
        PanelKullaniciEkleLayout.setVerticalGroup(
                PanelKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelKullaniciEkleLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(lblBilgiGiriniz)
                                .addGap(64, 64, 64)
                                .addGroup(PanelKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblKullaniciAdiEkle)
                                        .addComponent(txtKullaniciAdiKayit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSifreEkle)
                                        .addComponent(txtSifreKayit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnKaydet))
                                .addGap(18, 18, 18)
                                .addGroup(PanelKullaniciEkleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtEmailKayit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblEmailEkle)
                                        .addComponent(txtButceKayit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblButceEkle)
                                        .addComponent(btnGeri))
                                .addContainerGap(134, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 546, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(PanelKullaniciEkle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 312, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(PanelKullaniciEkle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKaydetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKaydetActionPerformed

        if (checkFields()) {
            JOptionPane.showMessageDialog(this, "All fields are required");
            return;
        }

        SignUpCredentials credentials = new SignUpCredentials(txtKullaniciAdiKayit.getText(),
                txtSifreKayit.getText(),
                txtEmailKayit.getText(),
                Integer.parseInt(txtButceKayit.getText()));

        Message result = RestUtils.createUser(credentials);

        if (result.isError()) {
            JOptionPane.showMessageDialog(this, result.getError().getErrorMessage());
            return;
        }

        SignUpCredentials userInfo = result.getResponse().getCredentials();
        UserRepo.setUser(new User(userInfo));

        new GirisEkrani().setVisible(true);
        JOptionPane.showMessageDialog(this, "SignUp Successful");
        this.dispose();
    }//GEN-LAST:event_btnKaydetActionPerformed

    private boolean checkFields() {
        return txtKullaniciAdiKayit.getText().isBlank() ||
                txtSifreKayit.getText().isBlank() ||
                txtButceKayit.getText().isBlank() ||
                txtEmailKayit.getText().isBlank();
    }

    private void btnGeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeriActionPerformed
        new GirisEkrani().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnGeriActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel PanelKullaniciEkle;
    private javax.swing.JButton btnGeri;
    private javax.swing.JButton btnKaydet;
    private javax.swing.JLabel lblBilgiGiriniz;
    private javax.swing.JLabel lblButceEkle;
    private javax.swing.JLabel lblEmailEkle;
    private javax.swing.JLabel lblKullaniciAdiEkle;
    private javax.swing.JLabel lblSifreEkle;
    private javax.swing.JTextField txtButceKayit;
    private javax.swing.JTextField txtEmailKayit;
    private javax.swing.JTextField txtKullaniciAdiKayit;
    private javax.swing.JTextField txtSifreKayit;
    // End of variables declaration//GEN-END:variables
}
