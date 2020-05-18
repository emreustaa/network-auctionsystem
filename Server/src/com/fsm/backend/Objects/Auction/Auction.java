package com.fsm.backend.Objects.Auction;

import com.fsm.backend.Interfaces.MyObject;
import com.fsm.backend.Objects.Valuable.Valuable;

import java.util.*;


@SuppressWarnings("FieldCanBeLocal")
public class Auction implements MyObject {

    private UUID id;
    private String name;
    private int currentPrice;
    private String winner;
    private Valuable item;
    public static AuctionRepository repository =
            new AuctionRepository(getSample());

    List<Bid> bids = new ArrayList<>();

    public Auction(Valuable item) {
        this.id = UUID.randomUUID();
        this.name = item.getName() + " auction";
        this.item = item;
        currentPrice = item.getInitialPrice();
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public String getWinner() {
        return winner;
    }

    public Valuable getItem() {
        return item;
    }

    public Bid updatePrice(Bid bid) {
        currentPrice = bid.getNewPrice();
        winner = bid.getUserName();
        bids.add(bid);
        return bid;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    private static Collection<Auction> getSample() {
        return Arrays.asList(
                new Auction(new Valuable("Tablo", 500)),
                new Auction(new Valuable("Antik Vazo", 1000)),
                new Auction(new Valuable("Klasik Araba", 1500))
        );
    }

//    public void sellToWinner() {
//        User.repository.findById(winnerId).buy(item, currentPrice);
//    }

}
