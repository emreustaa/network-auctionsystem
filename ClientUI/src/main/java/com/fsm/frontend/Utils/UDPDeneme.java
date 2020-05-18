package com.fsm.frontend.Utils;

import com.fsm.frontend.Ekranlar.Muzayede.MuzayedeEkrani;
import com.fsm.frontend.Objects.Auction.Bid;
import com.fsm.frontend.Objects.Message.Message;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpRequest;
import java.util.Objects;

public class UDPDeneme {

    static DatagramSocket socket = createSocket(3001);

    public static void main(String[] args) {
        String message = "sahsuahdısah";
        try {
            socket.send(getPacketForSend(message.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        sendMess();

//        DatagramPacket packet = getPacketForReceive();
//        waitFor(packet);
//        String json = getMessageJsonFrom(packet);
//        System.out.println(json);
    }

    private static void sendMess() {
        String message = "{\n" +
                "  \"event\" : null,\n" +
                "  \"command\" : null,\n" +
                "  \"error\" : null,\n" +
                "  \"response\" : {\n" +
                "    \"auctionDAOS\" : null,\n" +
                "    \"signUpCredentials\" : null\n" +
                "  }\n" +
                "}";
        try {
            socket.send(getPacketForSend(message.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DatagramPacket getPacketForSend(byte[] data) {
        DatagramPacket packet = null;
        try {
            packet = new DatagramPacket(
                    data,
                    data.length,
                    InetAddress.getByName(MuzayedeEkrani.getHOST()),
                    5000);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(packet);
    }

    public static DatagramSocket createSocket(int port) {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(port);
        } catch (SocketException se) {
            se.printStackTrace();
        }
        return datagramSocket;
    }

    public static DatagramPacket getPacketForReceive() {
        byte[] data = new byte[4096];
        DatagramPacket packet = null;
        try {
            packet = new DatagramPacket(data,
                    data.length,
                    InetAddress.getByName(MuzayedeEkrani.getHOST()),
                    MuzayedeEkrani.getUDPServerPort());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        return Objects.requireNonNull(packet);
    }

    public static void waitFor(DatagramPacket packet) {
        try {
            socket.receive(packet);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getMessageJsonFrom(DatagramPacket packet) {
        return new String(packet.getData(), 0, packet.getLength());
    }

//    private void sendPriceUpdatedEvent(String priceInput) {
//        int newPrice = ClientUtils.getAsNumber(priceInput);
//        if (notValid(newPrice)) return;
//        Bid bid = new Bid("ahmad", );
//        Message event = ClientUtils
//                .getPriceUpdatedEvent(bid);
//        HttpRequest request = getPriceUpdateRequest(event);
//        RestUtils.sendMessageToHttpServer(request);
//        System.out.println("PRICE UPDATED EVENT SENT AND DUMMY RESPONSE RECVD");
//    }

}
