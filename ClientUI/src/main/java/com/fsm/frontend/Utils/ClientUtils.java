package com.fsm.frontend.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsm.frontend.Ekranlar.Muzayede.MuzayedeEkrani;
import com.fsm.frontend.Objects.Auction.Auction;
import com.fsm.frontend.Objects.Auction.Bid;
import com.fsm.frontend.Objects.DAO.AuctionDAO;
import com.fsm.frontend.Objects.Message.Command;
import com.fsm.frontend.Objects.Message.Event;
import com.fsm.frontend.Objects.Message.Message;
import com.fsm.frontend.Objects.Message.MessageRepo;
import com.fsm.frontend.Objects.User.Credentials.LoginCredentials;
import com.fsm.frontend.Objects.User.Credentials.SignUpCredentials;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ClientUtils {
    private static final DatagramSocket socket = MuzayedeEkrani.getSocket();
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final int UDP_SERVER_PORT = MuzayedeEkrani.getUDPServerPort();
    private static final String HOST = MuzayedeEkrani.getHOST();
    public static List<Auction> auctionCache = new ArrayList<>();
    private static Auction currentAuction;

    public static Auction getCurrentAuction() {
        return currentAuction;
    }

    public static void setCurrentAuction(Auction currentAuction) {
        ClientUtils.currentAuction = currentAuction;
    }

    private static final ExecutorService executor
            = Executors.newCachedThreadPool(Executors.defaultThreadFactory());

    public static ObjectMapper getMapper() {
        return MAPPER;
    }

    public static String getMessageJson(Message message) {
        String jsonString = null;
        try {
            jsonString = MAPPER.
                    writerWithDefaultPrettyPrinter()
                    .writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(jsonString);
    }

    public static String getMessageJsonFrom(DatagramPacket packet) {
        return new String(packet.getData(), 0, packet.getLength());
    }

    public static Message getPriceUpdatedEvent(Bid bid) {
        return new Message()
                .setEvent(new Event(MessageRepo.PRICE_UPDATED_EVT)
                        .setBid(bid));
    }

    public static void submitTask(Callable<Void> func) {
        executor.submit(func);
    }

    public static Message getUserLogoutCommand(int senderPort) {
        return new Message()
                .setCommand(new Command(MessageRepo.USER_LOGOUT_CMD)
                .setSenderPort(senderPort));
    }

    public static Message getUserLoginCommand(LoginCredentials credentials) {
        return new Message()
                .setCommand(new Command(MessageRepo.USER_LOGIN_CMD)
                        .setLoginCredentials(credentials));
    }

    public static Message getUserSignUpCommand(SignUpCredentials credentials) {
        return new Message()
                .setCommand(new Command(MessageRepo.USER_SIGNUP_CMD)
                        .setSignUpCredentials(credentials));
    }

    public static Object getMessageFrom(String messageJson) {
        Object message = null;
        try {
            message = MAPPER.readValue(messageJson, Message.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(message);
    }

    public static void sendMessageToUDPServer(Message message) {
        String messageJson = getMessageJson(message);
        System.out.println("UDP MESSAGE SENT");
        System.out.println(messageJson);
        byte[] stopBytes = messageJson.getBytes();
        DatagramPacket packet = getPacketForSend(stopBytes);
        try {
            socket.send(packet);
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
                    InetAddress.getByName(HOST),
                    UDP_SERVER_PORT);
        } catch (UnknownHostException e) {
            e.printStackTrace();
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
        byte[] data = new byte[2048];
        DatagramPacket packet = null;
        try {
            packet = new DatagramPacket(data,
                    data.length,
                    InetAddress.getByName(HOST),
                    UDP_SERVER_PORT);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        System.out.println("udp server port: " + UDP_SERVER_PORT);
        return Objects.requireNonNull(packet);
    }

    public static int getAsNumber(String input) {
        int portNum;
        try {
            portNum = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
        return portNum;
    }

    public static Message getAuctionsCommand() {
        return new Message()
                .setCommand(new Command(MessageRepo.GET_AUCTIONS_CMD));
    }

    public static List<Auction> getAuctionsFrom(List<AuctionDAO> auctionDAOS) {
        return auctionDAOS.stream()
                .map(Auction::new)
                .collect(Collectors.toList());
    }

    public static String getAuctionId(String auctionName) {
        Auction auction = auctionCache.stream()
                .filter(au -> au.getName().equals(auctionName))
                .findFirst()
                .orElseThrow();
        return auction.getId().toString();
    }


}
