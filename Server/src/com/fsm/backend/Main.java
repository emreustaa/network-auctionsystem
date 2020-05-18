package com.fsm.backend;

import com.fsm.backend.Utils.Broadcast;

public class Main {

    public static void main(String[] args) {
        Broadcast.begin();
    }

//    private static void dene(Message message) {
//
//        if(message instanceof Event) {
//            System.out.println("this is an event");
//
//            if(message instanceof PriceUpdatedEvent) {
//                System.out.println("this is price updated event");
//            } else if(message instanceof AuctionCreatedEvent) {
//                System.out.println("this is auction created event");
//            }
//
//        } else if(message instanceof Command) {
//            System.out.println("this is a command");
//
//            if(message instanceof StopServerCommand) {
//                System.out.println("this is price stop server command");
//            }
//        }
//
//    }

}
