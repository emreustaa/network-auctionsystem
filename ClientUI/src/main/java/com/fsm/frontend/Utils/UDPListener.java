package com.fsm.frontend.Utils;

import javax.swing.*;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Salih Karakaya
 */
public class UDPListener extends javax.swing.JFrame {

    private static final String HOST = "localhost";
    private static final int SERVER_PORT = 8080;
    private static int myPort;
    private static String CLIENT_STARTED;
    private static String CLIENT_STOPPED;
//    private static final String CLIENT_STARTED = myPort + " CLIENT_STARTED",
//            CLIENT_STOPPED = myPort + " CLIENT_STOPPED";

    private DatagramSocket socket;

    enum state {
        ACTIVE, STOPPED
    }

    state currentState;

    ExecutorService executor =
            Executors.newCachedThreadPool(
                    Executors.defaultThreadFactory());

    public UDPListener() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelHistory = new javax.swing.JLabel();
        jButtonStart = new javax.swing.JButton();
        jButtonStop = new javax.swing.JButton();
        jButtonSendMessage = new javax.swing.JButton();
        tfPort = new javax.swing.JTextField();
        jLabelPort = new javax.swing.JLabel();
        tfMessage = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        taHistory = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblActiveCount = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelHistory.setText("History:");

        jButtonStart.setText("Start");
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });

        jButtonStop.setText("Stop");
        jButtonStop.setEnabled(false);
        jButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStopActionPerformed(evt);
            }
        });

        jButtonSendMessage.setEnabled(false);
        jButtonSendMessage.setText("Send Message");
        jButtonSendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (jButtonStop.isEnabled()) {
                    jButtonSendMessageActionPerformed(evt);
                }
            }
        });

        tfPort.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tfPort.setText("8081");
        tfPort.setToolTipText("Port Number");

        jLabelPort.setText("Port:");

        tfMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMessageActionPerformed(evt);
            }
        });

        taHistory.setColumns(20);
        taHistory.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        taHistory.setRows(5);
        jScrollPane2.setViewportView(taHistory);

        jLabel1.setText("Message:");

        jLabel2.setText("ActiveUsers:");

        lblActiveCount.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jScrollPane2)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jLabelHistory)
                                                                .addComponent(tfMessage)
                                                                .addComponent(jButtonSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(21, 21, 21)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jButtonStop, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jButtonStart, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(jLabelPort)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                        .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addContainerGap(37, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblActiveCount)
                                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(lblActiveCount))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(tfMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4)
                                                .addComponent(jButtonSendMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(tfPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelPort))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonStart)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonStop)))
                                .addGap(4, 4, 4)
                                .addComponent(jLabelHistory)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed
        setPort();
        this.setTitle(myPort + "");
        socket = createSocket(myPort);
        init();
        toggleButtons();
    }//GEN-LAST:event_jButtonStartActionPerformed

    private void jButtonStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStopActionPerformed
        Stop();
    }//GEN-LAST:event_jButtonStopActionPerformed

    private void jButtonSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendMessageActionPerformed
        String message = myPort + " " + tfMessage.getText();
        SendMessage(message);
    }//GEN-LAST:event_jButtonSendMessageActionPerformed

    private void tfMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMessageActionPerformed
        String message = tfMessage.getText();
        SendMessage(message);
    }//GEN-LAST:event_tfMessageActionPerformed

    private void setPort() {
        try {
            myPort = Integer.parseInt(tfPort.getText());
        } catch (NumberFormatException e) {
            myPort = 8080;
        }
        System.out.println("port set to: " + myPort);
        prepareCommands();
    }

    private void prepareCommands() {
        CLIENT_STARTED = myPort + " CLIENT_STARTED";
        CLIENT_STOPPED = myPort + " CLIENT_STOPPED";
    }

    private void toggleButtons() {
        jButtonStart.setEnabled(!jButtonStart.isEnabled());
        jButtonStop.setEnabled(!jButtonStop.isEnabled());
        jButtonSendMessage.setEnabled(!jButtonSendMessage.isEnabled());
        tfPort.setEnabled(!tfPort.isEnabled());
        System.out.println("buttons toggled");
    }

    private void Stop() {
        System.out.println("Initialized stop sequence");
        currentState = state.STOPPED;
        SendMessage(CLIENT_STOPPED);
    }

    private void init() {
        currentState = state.ACTIVE;
        SendMessage(CLIENT_STARTED);
        submitTask(this::listen);
    }

    private Void listen() {
        System.out.println("started listening");
        while (currentState == state.ACTIVE) {
            DatagramPacket packet = getPacketForReceive();
            waitFor(packet);
            String message = getMessageFrom(packet);
            Print(message);
            System.out.println("message from: " + packet.getPort());
        }
        socket.close();
        toggleButtons();
        return null;
    }

    private void SendMessage(String message) {

        if (message.isEmpty()) {
            System.out.println("message was empty");
            return;
        }

        byte[] data = message.getBytes();
        DatagramPacket packet = getPacketForSend(data);
        send(packet);
    }

    private void send(DatagramPacket packet) {
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void submitTask(Callable<Void> func) {
        executor.submit(func);
    }

    private void Print(String message) {
        int sender = Integer.parseInt(message.split(" ")[0]);
        if (message.contains("CLIENT")) {
            String result = processCommand(message, sender);
            printMessage(result);
            return;
        }
        printMessage(message);
    }

    private String processCommand(String message, int sender) {
        System.out.println("message: " + message);
        if (message.contains("STARTED")) {
            message = sender + " joined the room";
            incrementActiveCount();
        } else if (message.contains("STOPPED")) {
            message = sender + " left the room";
            decrementActiveCount();
        }
        return message;
    }

    DatagramPacket getPacketForSend(byte[] data) {
        try {
            return new DatagramPacket(
                    data,
                    data.length,
                    InetAddress.getByName(HOST),
                    SERVER_PORT);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    DatagramPacket getPacketForReceive() {
        byte[] data = new byte[2048];
        try {
            return new DatagramPacket(data, data.length, InetAddress.getByName(HOST), SERVER_PORT);
        } catch (UnknownHostException ex) {
            Logger.getLogger(UDPListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void decrementActiveCount() {
        setActiveCount(getActiveCount() - 1);
    }

    private void incrementActiveCount() {
        setActiveCount(getActiveCount() + 1);
    }

    private int getActiveCount() {
        return Integer.parseInt(lblActiveCount.getText());
    }

    private void setActiveCount(int newCount) {
        updateGUI(() -> {
            lblActiveCount.setText(String.format("%d", newCount));
        });
    }

    private void updateGUI(Runnable update) {
        java.awt.EventQueue.invokeLater(update);
    }

    private void printMessage(String message) {
        updateGUI(() -> taHistory.append(message + "\n"));
    }

    private void waitFor(DatagramPacket packet) {
        try {
            socket.receive(packet);
        } catch (IOException ex) {
            ex.printStackTrace();
            //socket.close();
            //System.out.println("Socket closed in Exception by WaitFor");
        }
    }

    private static String getMessageFrom(DatagramPacket packet) {
        return new String(packet.getData(), 0, packet.getLength());
    }

    private DatagramSocket createSocket(int port) {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(port);
        } catch (SocketException se) {
            se.printStackTrace();
        }
        return datagramSocket;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            Logger.getLogger(UDPListener.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new UDPListener().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSendMessage;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JButton jButtonStop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelHistory;
    private javax.swing.JLabel jLabelPort;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblActiveCount;
    private javax.swing.JTextArea taHistory;
    private javax.swing.JTextField tfMessage;
    private javax.swing.JTextField tfPort;
    // End of variables declaration//GEN-END:variables
}
