/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsm.frontend.Ekranlar.Muzayede;

import com.fsm.frontend.Ekranlar.GirisEkrani.GirisEkrani;
import com.fsm.frontend.Ekranlar.MevcutUrunler.MevcutUrunlerEkrani;
import com.fsm.frontend.Ekranlar.SatilanUrunler.SatilanUrunlerEkrani;
import com.fsm.frontend.Objects.Auction.Auction;
import com.fsm.frontend.Objects.Auction.Bid;
import com.fsm.frontend.Objects.DAO.AuctionDAO;
import com.fsm.frontend.Objects.Message.Event;
import com.fsm.frontend.Objects.Message.Message;
import com.fsm.frontend.Objects.Message.MessageRepo;
import com.fsm.frontend.Objects.User.Credentials.SignUpCredentials;
import com.fsm.frontend.Objects.User.User;
import com.fsm.frontend.Objects.User.UserRepo;
import com.fsm.frontend.Objects.Valuable.Valuable;
import com.fsm.frontend.Utils.ClientUtils;
import com.fsm.frontend.Utils.RestUtils;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.swing.*;

/**
 * @author Emre
 */

@SuppressWarnings({"FieldCanBeLocal", "SpellCheckingInspection", "unused"})
public class MuzayedeEkrani extends javax.swing.JFrame {

    private static final String HOST = "localhost";
    private static final int HTTP_SERVER_PORT = 8080;
    private static final int UDP_SERVER_PORT = 8081;
    private static int myPort;
    private static DatagramSocket socket;
    private User currentUser;
    private Auction currentAuction;
    Valuable currentItem;
    private boolean autoOffer = false;
    public static final String imagesFolder = System.getProperty("user.dir") +
            "\\src\\main\\java\\com\\fsm\\frontend\\urunresimleri\\";

    enum STATE {
        ACTIVE, STOPPED
    }

    STATE currentSTATE;

    public MuzayedeEkrani() {
        initComponents();
        configureGUI();
        currentUser = UserRepo.getUser();
        myPort = currentUser.getPortNum();
        System.out.println("port set to: " + myPort);
        initializeConnection();
        this.currentAuction = ClientUtils.getCurrentAuction();
        currentItem = currentAuction.getItem();
        setLabels();
    }

    private void setLabels() {
        lblKullaniciAdi.setText(currentUser.getUserName());
        lblUrunAdi.setText(currentItem.getName());
        lblBaslangicFiyat.setText(currentItem.getInitialPrice() + "");
        lblKazanan.setText(currentAuction.getWinner());
        lblMevcutFiyat.setText(currentAuction.getCurrentPrice()+"");
        lblUrunId.setText(getItemId());
    }

    private String getItemId() {
        return currentItem.getId().toString().subSequence(0, 5).toString();
    }

    private void configureGUI() {
        setTitle("Online Müzayede Sistemi");
        setResizable(false);
        setImages();
    }

    private void setImages() {
        Icon urun = new ImageIcon(imagesFolder + "images.jpg");
        lblResim.setIcon(urun);
        Icon tokmak = new ImageIcon(imagesFolder + "müzayede.png");
        tokmak_resim.setIcon(tokmak);
    }

    public static int getMyPort() {
        return myPort;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblKullaniciAdi = new javax.swing.JLabel();
        lbladsf = new javax.swing.JLabel();
        cbOtomatikTeklif = new javax.swing.JCheckBox();
        txtTeklif = new javax.swing.JTextField();
        btnGonder = new javax.swing.JButton();
        lblResim = new javax.swing.JLabel();
        lbl11 = new javax.swing.JLabel();
        jlasdf = new javax.swing.JLabel();
        lblUrunId = new javax.swing.JLabel();
        jlbl22 = new javax.swing.JLabel();
        lblUrunAdi = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblBaslangicFiyat = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMevcutFiyat = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblKazanan = new javax.swing.JLabel();
        btnCikisYap = new javax.swing.JButton();
        tokmak_resim = new javax.swing.JLabel();
        menuBarMuzayede = new javax.swing.JMenuBar();
        menuAnaSayfa = new javax.swing.JMenu();
        menuKullaniciIslemleri = new javax.swing.JMenu();
        menuBilgiGuncelle = new javax.swing.JMenuItem();
        menuSifreGuncelle = new javax.swing.JMenuItem();
        menuUrunler = new javax.swing.JMenu();
        menuMevcutUrunler = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuKasaIslemleri = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblKullaniciAdi.setText("Ahmet AK");

        lbladsf.setFont(new java.awt.Font("Arial", Font.BOLD, 12)); // NOI18N
        lbladsf.setText("ÜRÜN RESMİ");

        cbOtomatikTeklif.setText("Otomatik Teklif");
        cbOtomatikTeklif.addItemListener(this::cbOtomatikTeklifItemStateChanged);

        txtTeklif.addActionListener(this::txtTeklifActionPerformed);

        btnGonder.setText("GÖNDER");
        btnGonder.addActionListener(this::btnGonderActionPerformed);

        lbl11.setText("Teklif Ver: ");

        jlasdf.setText("Ürün Kodu:");

        lblUrunId.setText("1356741");

        jlbl22.setText("Ürün Adı:");

        lblUrunAdi.setText("Tablo");

        jLabel1.setText("Başlangıç Fiyatı:");

        lblBaslangicFiyat.setText("3500 ₺");

        jLabel3.setText("Mevcut Fiyatı:");

        lblMevcutFiyat.setText("10.250 ₺");

        jLabel5.setText("Son Teklif:");

        lblKazanan.setText("Ahmet AK");

        btnCikisYap.setText("Çıkış Yap");
        btnCikisYap.addActionListener(this::btnCikisYapActionPerformed);

        menuAnaSayfa.setText("Ana Sayfa");
        menuBarMuzayede.add(menuAnaSayfa);

        menuKullaniciIslemleri.setText("Kullanıcı İşlemleri");

        menuBilgiGuncelle.setText("Bilgileri Görüntüle");
        menuBilgiGuncelle.addActionListener(this::menuBilgiGuncelleActionPerformed);
        menuKullaniciIslemleri.add(menuBilgiGuncelle);

        menuSifreGuncelle.setText("Şifre Güncelle");
        menuSifreGuncelle.addActionListener(this::menuSifreGuncelleActionPerformed);
        menuKullaniciIslemleri.add(menuSifreGuncelle);

        menuBarMuzayede.add(menuKullaniciIslemleri);

        menuUrunler.setText("Ürünler");

        menuMevcutUrunler.setText("Mevcut Ürünler");
        menuMevcutUrunler.addActionListener(this::menuMevcutUrunlerActionPerformed);
        menuUrunler.add(menuMevcutUrunler);

        jMenuItem1.setText("Satılan Ürünler");
        jMenuItem1.addActionListener(this::jMenuItem1ActionPerformed);
        menuUrunler.add(jMenuItem1);

        menuBarMuzayede.add(menuUrunler);

        menuKasaIslemleri.setText("Kasa İşlemleri");
        menuBarMuzayede.add(menuKasaIslemleri);

        setJMenuBar(menuBarMuzayede);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl11, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTeklif, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGonder))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbladsf, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(cbOtomatikTeklif, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblResim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBaslangicFiyat))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbl22, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlasdf, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUrunAdi, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUrunId, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblKullaniciAdi)
                        .addGap(18, 18, 18)
                        .addComponent(btnCikisYap, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(tokmak_resim, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblKazanan)
                                .addComponent(lblMevcutFiyat)))))
                .addGap(8, 8, 8))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKullaniciAdi)
                    .addComponent(btnCikisYap))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbladsf)
                    .addComponent(cbOtomatikTeklif))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblResim, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlasdf)
                            .addComponent(lblUrunId))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlbl22)
                            .addComponent(lblUrunAdi))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblBaslangicFiyat))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblMevcutFiyat))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblKazanan))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTeklif, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGonder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl11))
                        .addGap(66, 66, 66))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tokmak_resim, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuSifreGuncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSifreGuncelleActionPerformed

    }//GEN-LAST:event_menuSifreGuncelleActionPerformed

    private void txtTeklifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTeklifActionPerformed

    }//GEN-LAST:event_txtTeklifActionPerformed


    private void menuBilgiGuncelleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBilgiGuncelleActionPerformed

    }//GEN-LAST:event_menuBilgiGuncelleActionPerformed

    private void menuMevcutUrunlerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMevcutUrunlerActionPerformed
        MevcutUrunlerEkrani mue = new MevcutUrunlerEkrani();
        mue.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuMevcutUrunlerActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        SatilanUrunlerEkrani satilan = new SatilanUrunlerEkrani();
        satilan.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnCikisYapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCikisYapActionPerformed
        GirisEkrani ge = new GirisEkrani();
        ge.setVisible(true);
        prepareForLogout();
        this.dispose();
    }//GEN-LAST:event_btnCikisYapActionPerformed

    private void btnGonderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGonderActionPerformed
        sendPriceUpdatedEvent(txtTeklif.getText());

    }//GEN-LAST:event_btnGonderActionPerformed

    private void cbOtomatikTeklifItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbOtomatikTeklifItemStateChanged
        configureAutoOffer(evt);
    }//GEN-LAST:event_cbOtomatikTeklifItemStateChanged

    private void prepareForLogout() {
        Message logoutCommand = ClientUtils.
                getUserLogoutCommand(currentUser.getPortNum());
        ClientUtils.setCurrentAuction(null);
        HttpRequest logoutRequest = getUserLogoutRequest(logoutCommand);
        HttpResponse<String> Logoutresponse = RestUtils.sendMessageToHttpServer(logoutRequest);
        socket.close();
        System.out.println("user logout response: ");
        System.out.println(Logoutresponse.body());
    }

    private HttpRequest getUserLogoutRequest(Message logoutCommand) {
        return RestUtils.preparePOST("/users/logout", logoutCommand);
    }

    private void configureAutoOffer(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            enableAutoOffer();
        } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
            disableAutoOffer();
        }
    }

    private void enableAutoOffer() {
        autoOffer = true;
    }

    private void disableAutoOffer() {
        autoOffer = false;
    }

    private void sendPriceUpdatedEvent(String priceInput) {
        int newPrice = ClientUtils.getAsNumber(priceInput);
        if (notValid(newPrice)) return;
        Bid bid = getBid(newPrice);
        Message event = ClientUtils
                .getPriceUpdatedEvent(bid);
        HttpRequest request = getPriceUpdateRequest(event);
        HttpResponse<String> response = RestUtils.sendMessageToHttpServer(request);
        System.out.println("PRICE UPDATED EVENT SENT Response: ");
        System.out.println(response.body());

    }

    private HttpRequest getPriceUpdateRequest(Message event) {
        return RestUtils.preparePOST("/auctions/updatePrice", event);
    }

    private Bid getBid(int newPrice) {
        return new Bid(currentUser.getUserName(), currentAuction.getId(), newPrice);
    }

    private boolean notValid(int newPrice) {
        return newPrice == -1;
    }

    public static int getHttpServerPort() {
        return HTTP_SERVER_PORT;
    }

    public static int getUDPServerPort() {
        return UDP_SERVER_PORT;
    }

    public static String getHOST() {
        return HOST;
    }

    public static DatagramSocket getSocket() {
        return socket;
    }

    private void Stop() {
        System.out.println("Initialized stop sequence");
        currentSTATE = STATE.STOPPED;
        Message cmd = ClientUtils.
                getUserLogoutCommand(currentUser.getPortNum());
        ClientUtils.sendMessageToUDPServer(cmd);
    }

    private void initializeConnection() {
        socket = ClientUtils.createSocket(myPort);
        currentSTATE = STATE.ACTIVE;
//        ClientUtils.sendMessageToUDPServer(ClientUtils.getUserSignUpCommand(
//                new SignUpCredentials(currentUser.getUserName(), currentUser.getPassword(), currentUser.getMail(), currentUser.getCurrency())
//        ));
        System.out.println("current state set to: " + currentSTATE.toString());
        ClientUtils.submitTask(this::listen);
    }

    private Void listen() {
        while (currentSTATE == STATE.ACTIVE) {
            DatagramPacket packet = ClientUtils.getPacketForReceive();
            System.out.println("waiting for packet. MyPort: " + myPort);
            ClientUtils.waitFor(packet);
            String messageJson = ClientUtils.getMessageJsonFrom(packet);
            System.out.println("MESSAGE RECEIVED");
            System.out.println(messageJson);
            Message message = (Message) ClientUtils.getMessageFrom(messageJson);
            processMessage(message);
        }
        System.out.println("currentState: " + currentSTATE.toString());
        socket.close();
        return null;
    }

    private void processMessage(Message message) {
        if(message.isEvent()) {
            Event event = message.getEvent();
            if(isPriceUpdated(event)) {
                applyPriceUpdate(event.getBid());
            } else if(isAuctionCreated(event)) {
                addAuction(event.getAuctionDAO());
            }
        }
    }

    private void addAuction(AuctionDAO auctionDAO) {
        Auction auction = new Auction(auctionDAO);
        ClientUtils.auctionCache.add(auction);
    }

    private void applyPriceUpdate(Bid bid) {
        System.out.println("Price Updated");
        currentAuction.setCurrentPrice(bid.getNewPrice());
        currentAuction.setWinner(bid.getUserName());

        updateGUI(() -> {
            lblMevcutFiyat.setText(currentAuction.getCurrentPrice()+"");
            lblKazanan.setText(currentAuction.getWinner());
        });
    }

    private void updateGUI(Runnable update) {
        java.awt.EventQueue.invokeLater(update);
    }

    private boolean isAuctionCreated(Event e) {
        return e.getAuctionDAO() != null;
    }

    private boolean isPriceUpdated(Event event) {
        return event.getEventType()
                        .equals(MessageRepo.PRICE_UPDATED_EVT);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCikisYap;
    private javax.swing.JButton btnGonder;
    private javax.swing.JCheckBox cbOtomatikTeklif;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel jlasdf;
    private javax.swing.JLabel jlbl22;
    private javax.swing.JLabel lbl11;
    private javax.swing.JLabel lblBaslangicFiyat;
    private javax.swing.JLabel lblKazanan;
    private javax.swing.JLabel lblKullaniciAdi;
    private javax.swing.JLabel lblMevcutFiyat;
    private javax.swing.JLabel lblResim;
    private javax.swing.JLabel lblUrunAdi;
    private javax.swing.JLabel lblUrunId;
    private javax.swing.JLabel lbladsf;
    private javax.swing.JMenu menuAnaSayfa;
    private javax.swing.JMenuBar menuBarMuzayede;
    private javax.swing.JMenuItem menuBilgiGuncelle;
    private javax.swing.JMenu menuKasaIslemleri;
    private javax.swing.JMenu menuKullaniciIslemleri;
    private javax.swing.JMenuItem menuMevcutUrunler;
    private javax.swing.JMenuItem menuSifreGuncelle;
    private javax.swing.JMenu menuUrunler;
    private javax.swing.JLabel tokmak_resim;
    private javax.swing.JTextField txtTeklif;
    // End of variables declaration//GEN-END:variables
}
