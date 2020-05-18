package com.fsm.backend.Utils;

import com.fsm.backend.Objects.Message.Message;
import com.fsm.backend.Objects.Message.MessageRepo;
import com.fsm.backend.Objects.User.UserRepo;

import javax.swing.*;
import java.io.IOException;
import java.net.*;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Salih Karakaya
 */

@SuppressWarnings("FieldCanBeLocal")
public class Broadcast extends javax.swing.JFrame {

    public Broadcast() {
        initComponents();
    }

    private enum STATE {
        ACTIVE, STOPPED
    }

    @SuppressWarnings({"MagicConstant"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonStart = new javax.swing.JButton();
        jButtonStop = new javax.swing.JButton();
        tfPort = new javax.swing.JTextField();
        jLabelPort = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblActiveCount = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonStart.setText("Start");
        jButtonStart.addActionListener(this::jButtonStartActionPerformed);

        jButtonStop.setText("Stop");
        jButtonStop.setEnabled(false);
        jButtonStop.addActionListener(this::jButtonStopActionPerformed);

        tfPort.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfPort.setText("8081");
        tfPort.setToolTipText("Port Number");

        jLabelPort.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelPort.setText("Port:");

        jLabel2.setText("ActiveUsers:");

        lblActiveCount.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabelPort, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblActiveCount)
                                                .addGap(31, 31, 31))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jButtonStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButtonStart, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(lblActiveCount))
                                                .addGap(48, 48, 48))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(tfPort, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                                                        .addComponent(jLabelPort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addComponent(jButtonStart, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonStop, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed
        setPort();
        this.setTitle("Server");
         start();
        toggleStartStopButtons();
    }//GEN-LAST:event_jButtonStartActionPerformed

    private void jButtonStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStopActionPerformed
         stop();
        afterServerStop();
    }//GEN-LAST:event_jButtonStopActionPerformed

    private void toggleStartStopButtons() {
        jButtonStart.setEnabled(!jButtonStart.isEnabled());
        jButtonStop.setEnabled(!jButtonStop.isEnabled());
    }

    private void afterServerStop() {
         socket.close();
        toggleStartStopButtons();
    }

    private void setPort() {
        try {
             UDP_SERVER_PORT = Integer.parseInt(tfPort.getText());
        } catch (NumberFormatException e) {
             UDP_SERVER_PORT = 8081;
        }
        System.out.println("UDP_SERVER_PORT set to: " +  UDP_SERVER_PORT);
    }

    public static void broadCastMessage(Message message) {
         BroadCastMessage(ControllerUtils.getJsonString(message));
    }

    public static void broadCastMessage(Message message, Predicate<UUID> filter) {
         BroadCastMessage(ControllerUtils.getJsonString(message), filter);
    }



        private static final String HOST = "localhost";
        private static int UDP_SERVER_PORT;

        private static DatagramSocket socket;

        private static STATE currentState;

        private static ExecutorService executor
                = Executors.newCachedThreadPool(
                Executors.defaultThreadFactory());

        private static Void listen() {
            while (currentState == STATE.ACTIVE) {
                DatagramPacket packet = getPacketForReceive();
                waitFor(packet);
                String messageJson =
                        getMessageFrom(Objects.requireNonNull(packet));
                System.out.println("message: " + messageJson);
                Message message = (Message) ControllerUtils.getObjectFrom(messageJson);
                processMessage(message);
            }
            return null;
        }

        private static void BroadCastMessage(String messageJson) {
            byte[] data = messageJson.getBytes();
            UserRepo.activeUsers.forEach((portNum, auctionId) -> {
                DatagramPacket packet = getPacketForSend(data, portNum);
                send(packet);
                System.out.println("message sent to: " + portNum + " for auction: " + auctionId);
            });
        }

        private static void BroadCastMessage(String messageJson, Predicate<UUID> filter) {
            UserRepo.activeUsers.forEach((portNum, auctionId) -> {
                byte[] data = messageJson.getBytes();
                if (filter.test(auctionId)) {
                    DatagramPacket packet = getPacketForSend(data, portNum);
                    send(packet);
                    System.out.println("message sent to: " + portNum + " for auction: " + auctionId);
                }
            });
        }

        private static void sendStopServerMessage() {
            Message stopMessage = ControllerUtils.getServerStoppedCommand();
            String stopCommand = ControllerUtils.
                    getJsonString(stopMessage);
            byte[] stopBytes = stopCommand.getBytes();
            DatagramPacket packet = getPacketForSend(stopBytes, UDP_SERVER_PORT);
            send(packet);
        }

        private static void start() {
            socket = createSocket(UDP_SERVER_PORT);
            currentState = STATE.ACTIVE;
            submitTask(Broadcast::listen);
        }

        public static void processMessage(Message message) {
            if (message.isCommand()) {
                if (isServerStopped(message)) {
                    System.out.println("server at " +  UDP_SERVER_PORT + " stopped");
                    //return;
                }
            }
        }

        private static boolean isServerStopped(Message message) {
            return message.getCommand().getCommandType().equals(MessageRepo.STOP_SERVER_CMD);
        }

        private static void stop() {
            currentState = STATE.STOPPED;
            sendStopServerMessage();
        }

        private static void waitFor(DatagramPacket packet) {
            try {
                socket.receive(packet);
            } catch (IOException ex) {
                Logger.getLogger(Broadcast.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private static void send(DatagramPacket packet) {
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private static void submitTask(Callable<Void> func) {
            executor.submit(func);
        }

        private static DatagramPacket getPacketForSend(byte[] data, int userPort) {
            try {
                return new DatagramPacket(
                        data,
                        data.length,
                        InetAddress.getByName(HOST),
                        userPort);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            return null;
        }

        private static DatagramPacket getPacketForReceive() {
            byte[] data = new byte[1024];
            try {
                return new DatagramPacket(data,
                        data.length,
                        InetAddress.getByName(HOST),
                        UDP_SERVER_PORT);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Broadcast.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        private static String getMessageFrom(DatagramPacket packet) {
            return new String(packet.getData(), 0, packet.getLength());
        }

        private static DatagramSocket createSocket(int port) {
            DatagramSocket datagramSocket = null;
            try {
                datagramSocket = new DatagramSocket(port);
            } catch (SocketException se) {
                se.printStackTrace();
            }
            return datagramSocket;
        }


    public static void begin() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Broadcast.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new Broadcast().setVisible(true));
        MainUtils.startHTTPServer();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonStart;
    private javax.swing.JButton jButtonStop;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelPort;
    private javax.swing.JLabel lblActiveCount;
    private javax.swing.JTextField tfPort;
    // End of variables declaration//GEN-END:variables
}
