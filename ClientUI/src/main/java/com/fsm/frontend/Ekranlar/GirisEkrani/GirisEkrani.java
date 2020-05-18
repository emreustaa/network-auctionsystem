/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsm.frontend.Ekranlar.GirisEkrani;

import com.fsm.frontend.Ekranlar.Muzayede.MuzayedeEkrani;
import com.fsm.frontend.Objects.Auction.Auction;
import com.fsm.frontend.Objects.DAO.AuctionDAO;
import com.fsm.frontend.Objects.Message.Message;
import com.fsm.frontend.Objects.Message.MessageRepo;
import com.fsm.frontend.Objects.User.Credentials.LoginCredentials;
import com.fsm.frontend.Objects.User.Credentials.SignUpCredentials;
import com.fsm.frontend.Objects.User.User;
import com.fsm.frontend.Objects.User.UserRepo;
import com.fsm.frontend.Utils.ClientUtils;
import com.fsm.frontend.Utils.RestUtils;

import javax.swing.*;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * @author Emre Usta
 */
@SuppressWarnings({"FieldCanBeLocal", "Convert2Lambda", "unused", "Anonymous2MethodRef"})
public class GirisEkrani extends javax.swing.JFrame {

    public GirisEkrani() {
        initComponents();
        setTitle("Kullanıcı Giriş Ekranı");
        setResizable(false);
        ClientUtils.auctionCache.clear();
        ClientUtils.auctionCache.addAll(getAuctions());
        fillAuctionsCB();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelGiris = new javax.swing.JPanel();
        lblSifre = new javax.swing.JLabel();
        lblKullaniciId = new javax.swing.JLabel();
        txtKullaniciAdi = new javax.swing.JTextField();
        txtSifre = new javax.swing.JTextField();
        lblSifreDurum = new javax.swing.JLabel();
        btnKullaniciEkle = new javax.swing.JButton();
        btnGiris = new javax.swing.JButton();
        lblidDurum = new javax.swing.JLabel();
        lblSifre1 = new javax.swing.JLabel();
        txtPortNum = new javax.swing.JTextField();
        lblSifre2 = new javax.swing.JLabel();
        cbMuzayedeler = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        PanelGiris.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblSifre.setText("Şifre");

        lblKullaniciId.setText("Kullanıcı Adı:");

        btnKullaniciEkle.setText("Kullanıcı Ekle");
        btnKullaniciEkle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKullaniciEkleActionPerformed(evt);
            }
        });

        btnGiris.setText("Giris");
        btnGiris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGirisActionPerformed(evt);
            }
        });

        lblSifre1.setText("Port Num.");

        lblSifre2.setText("Müzayedeler");

        cbMuzayedeler.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Lütfen Bir Müzayede Seçiniz"}));

        javax.swing.GroupLayout PanelGirisLayout = new javax.swing.GroupLayout(PanelGiris);
        PanelGiris.setLayout(PanelGirisLayout);
        PanelGirisLayout.setHorizontalGroup(
                PanelGirisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelGirisLayout.createSequentialGroup()
                                .addContainerGap(94, Short.MAX_VALUE)
                                .addGroup(PanelGirisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblKullaniciId, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSifre1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnGiris, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSifre2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(PanelGirisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(PanelGirisLayout.createSequentialGroup()
                                                .addGroup(PanelGirisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtKullaniciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(PanelGirisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(lblidDurum, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                                        .addComponent(lblSifreDurum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(PanelGirisLayout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(btnKullaniciEkle))
                                        .addGroup(PanelGirisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(cbMuzayedeler, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtPortNum, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(0, 33, Short.MAX_VALUE))
        );
        PanelGirisLayout.setVerticalGroup(
                PanelGirisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelGirisLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(PanelGirisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(PanelGirisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblKullaniciId, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtKullaniciAdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblidDurum, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PanelGirisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSifreDurum, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSifre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(PanelGirisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblSifre1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPortNum, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PanelGirisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblSifre2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbMuzayedeler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(PanelGirisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnGiris)
                                        .addComponent(btnKullaniciEkle))
                                .addContainerGap(64, Short.MAX_VALUE))
        );

        getContentPane().add(PanelGiris, "card10");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKullaniciEkleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKullaniciEkleActionPerformed
        new KayitEkrani().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKullaniciEkleActionPerformed

    private void btnGirisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGirisActionPerformed

        if(cbMuzayedeler.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please choose an auction from the list");
            return;
        }

        if(txtKullaniciAdi.getText().isBlank() || txtSifre.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Username and password cannot be empty");
            return;
        }

        int portNum = ClientUtils.getAsNumber(txtPortNum.getText());

            if (portNum == -1) {
                JOptionPane.showMessageDialog(this,
                        "port must be a number greater than 1024");
                return;
            }

        LoginCredentials credentials = getCredentials(portNum);

        if (credentials.getPortNum() == 8080 || credentials.getPortNum() == 8081 || credentials.getPortNum() <= 1024) {
            JOptionPane.showMessageDialog(this, MessageRepo.PORT_ALREADY_TAKEN_ERR);
            return;
        }

        HttpResponse<String> response = login(credentials);
        Message responseMessage =
                (Message) ClientUtils
                        .getMessageFrom(response.body());

        if (responseMessage.isError()) {
            JOptionPane.showMessageDialog(this,
                    responseMessage.getError().getErrorMessage());
            return;
        }

        User u = new User(getCredentials(responseMessage))
                .setPort(portNum);
        UserRepo.setUser(u);

        ClientUtils.setCurrentAuction(getSelectedAuction());
        new MuzayedeEkrani().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnGirisActionPerformed

    private Auction getSelectedAuction() {
        return ClientUtils.auctionCache
                .stream()
                .filter(au -> au.getName().equals(cbMuzayedeler.getSelectedItem()))
                .findFirst()
                .orElseThrow();
    }

    private SignUpCredentials getCredentials(Message responseMessage) {
        return responseMessage
                .getResponse()
                .getCredentials();
    }

    private HttpResponse<String> login(LoginCredentials credentials) {
        Message loginCmd = ClientUtils.getUserLoginCommand(credentials);
        HttpRequest request = RestUtils.
                preparePOST("/users/login", loginCmd);
        return RestUtils.sendMessageToHttpServer(request);
    }

    private LoginCredentials getCredentials(int portNum) {
        return new LoginCredentials(
                txtKullaniciAdi.getText(),
                txtSifre.getText(),
                portNum,
                ClientUtils.getAuctionId((String)
                        cbMuzayedeler.getSelectedItem()));
    }

    private void fillAuctionsCB() {
        for (Auction auction : ClientUtils.auctionCache) {
            cbMuzayedeler.addItem(auction.getName());
        }
    }

    private List<Auction> getAuctions() {
        Message getAuctionsCmd = ClientUtils.getAuctionsCommand();
        HttpRequest request = RestUtils.prepareGET("/auctions/getAllAuctions");
        HttpResponse<String> response = RestUtils.sendMessageToHttpServer(request);
        Message responseMessage = (Message) ClientUtils.getMessageFrom(response.body());
        List<AuctionDAO> auctionDAOS = responseMessage.getResponse().getAuctionDAOS();
        return ClientUtils.getAuctionsFrom(auctionDAOS);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException | ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GirisEkrani().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelGiris;
    private javax.swing.JButton btnGiris;
    private javax.swing.JButton btnKullaniciEkle;
    private javax.swing.JComboBox<String> cbMuzayedeler;
    private javax.swing.JLabel lblKullaniciId;
    private javax.swing.JLabel lblSifre;
    private javax.swing.JLabel lblSifre1;
    private javax.swing.JLabel lblSifre2;
    private javax.swing.JLabel lblSifreDurum;
    private javax.swing.JLabel lblidDurum;
    private javax.swing.JTextField txtKullaniciAdi;
    private javax.swing.JTextField txtPortNum;
    private javax.swing.JTextField txtSifre;
    // End of variables declaration//GEN-END:variables
}
