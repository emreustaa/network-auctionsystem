//package com.fsm.frontend;
//
//import com.fsm.frontend.Objects.Message.Command;
//import com.fsm.frontend.Objects.Message.Event;
//import com.fsm.frontend.Objects.Message.Message;
//import com.fsm.frontend.Objects.Message.MessageRepo;
//
//import javax.swing.*;
//import java.io.IOException;
//import java.net.*;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// * @author Emre Usta & Salih Karakaya
// */
//public class ClientUI extends javax.swing.JFrame {
//
//    private static final String HOST = "localhost";
//    private static final int SERVER_PORT = 8081;
//    private static int myPort;
//    private DatagramSocket socket;
//
//    enum STATE {
//        ACTIVE, STOPPED
//    }
//
//    STATE currentSTATE;
//
//    private final ExecutorService executor
//            = Executors.newCachedThreadPool(Executors.defaultThreadFactory());
//
//    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed
//        setPort();
//        this.setTitle(myPort + "");
//        socket = createSocket(myPort);
//        init();
//        toggleButtons();
//    }//GEN-LAST:event_jButtonStartActionPerformed
//
//    private void tfMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMessageActionPerformed
//
//    }//GEN-LAST:event_tfMessageActionPerformed
//
//    private void toggleButtons() {
//        jButtonStart.setEnabled(!jButtonStart.isEnabled());
//        jButtonStop.setEnabled(!jButtonStop.isEnabled());
//        jButtonSendMessage.setEnabled(!jButtonSendMessage.isEnabled());
//        tfPort.setEnabled(!tfPort.isEnabled());
//        System.out.println("buttons toggled");
//    }
//
//    private void Stop() {
//        System.out.println("Initialized stop sequence");
//        currentSTATE = STATE.STOPPED;
//        SendMessage(MessageRepo.CLIENT_LEFT_CMD);
//    }
//
//    private void init() {
//        currentSTATE = STATE.ACTIVE;
//        SendMessage(MessageRepo.CLIENT_STARTED_CMD);
//        submitTask(this::listen);
//    }
//
//    private Void listen() {
//        System.out.println("started listening");
//        while (currentSTATE == STATE.ACTIVE) {
//            DatagramPacket packet = getPacketForReceive();
//            waitFor(packet);
//            String message = getMessageFrom(packet);
//
//            System.out.println("message from: " + packet.getPort());
//        }
//        socket.close();
//        toggleButtons();
//        return null;
//    }
//
//    private void SendMessage(String message) {
//
//        if (message.isEmpty()) {
//            System.out.println("message was empty");
//            return;
//        }
//
//        byte[] data = message.getBytes();
//        DatagramPacket packet = getPacketForSend(data);
//        send(packet);
//    }
//
//    private void send(DatagramPacket packet) {
//        try {
//            socket.send(packet);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void submitTask(Callable<Void> func) {
//        executor.submit(func);
//    }
//
//    private void processMessage(Message message) {
//        if (message.isCommand()) {
//            processCommand(message.getSenderName(), message.getCommand());
//        } else if (message.isEvent()) {
//            processEvent(message.getSenderName(), message.getEvent());
//        }
//    }
//
//    private void processEvent(String sender, Event event) {
//        if (price_updated(event)) {
//
//        }
//    }
//
//    private boolean price_updated(Event event) {
//        return event.getEventType().equals(MessageRepo.PRICE_UPDATED_EVT);
//    }
//
//    private void processCommand(String sender, Command command) {
//        if (clientStarted(command)) {
//            incrementActiveCount();
//        } else if (clientLeft(command)) {
//            decrementActiveCount();
//        }
//    }
//
//    private boolean clientStarted(Command command) {
//        return command.getCommandType().equals(MessageRepo.CLIENT_STARTED_CMD);
//    }
//
//    private boolean clientLeft(Command command) {
//        return command.getCommandType().equals(MessageRepo.CLIENT_LEFT_CMD);
//    }
//
//    DatagramPacket getPacketForSend(byte[] data) {
//        try {
//            return new DatagramPacket(
//                    data,
//                    data.length,
//                    InetAddress.getByName(HOST),
//                    SERVER_PORT);
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    DatagramPacket getPacketForReceive() {
//        byte[] data = new byte[2048];
//        try {
//            return new DatagramPacket(data, data.length, InetAddress.getByName(HOST), SERVER_PORT);
//        } catch (UnknownHostException ex) {
//            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    private void decrementActiveCount() {
//        setActiveCount(getActiveCount() - 1);
//    }
//
//    private void incrementActiveCount() {
//        setActiveCount(getActiveCount() + 1);
//    }
//
//    private int getActiveCount() {
//        return Integer.parseInt(lblActiveCount.getText());
//    }
//
//    private void setActiveCount(int newCount) {
//        updateGUI(() -> {
//            lblActiveCount.setText(String.format("%d", newCount));
//        });
//    }
//
//    private void updateGUI(Runnable update) {
//        java.awt.EventQueue.invokeLater(update);
//    }
//
//    private void printMessage(String message) {
//        updateGUI(() -> taHistory.append(message + "\n"));
//    }
//
//    private void waitFor(DatagramPacket packet) {
//        try {
//            socket.receive(packet);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            //socket.close();
//            //System.out.println("Socket closed in Exception by WaitFor");
//        }
//    }
//
//    private static String getMessageFrom(DatagramPacket packet) {
//        return new String(packet.getData(), 0, packet.getLength());
//    }
//
//    private DatagramSocket createSocket(int port) {
//        DatagramSocket datagramSocket = null;
//        try {
//            datagramSocket = new DatagramSocket(port);
//        } catch (SocketException se) {
//            se.printStackTrace();
//        }
//        return datagramSocket;
//    }
//
//    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(ClientUI.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        java.awt.EventQueue.invokeLater(() -> {
//            new ClientUI().setVisible(true);
//        });
//    }
//}
