package com.fsm.backend.Objects.DAO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsm.backend.Objects.Auction.Auction;
import com.fsm.backend.Objects.Valuable.Valuable;

import java.util.UUID;

public class AuctionDAO {

    private UUID id;
    private String name;
    private int currentPrice;
    private Valuable item;

    @JsonProperty("winner")
    private String winner;

    @JsonCreator
    public AuctionDAO(@JsonProperty("id") String id,
                      @JsonProperty("name") String name,
                      @JsonProperty("currentPrice") int currentPrice,
                      @JsonProperty("item") Valuable item) {
        this.id = UUID.fromString(id);
        this.name = name;
        this.currentPrice = currentPrice;
        this.item = item;
    }

    public AuctionDAO(UUID id,
                      String name,
                      int currentPrice,
                      Valuable item) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
        this.item = item;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public String getWinner() {
        return winner;
    }

    public AuctionDAO setWinner(String winner) {
        this.winner = winner;
        return this;
    }

    public Valuable getItem() {
        return item;
    }

    public static AuctionDAO getDAOFromAuction(Auction auction) {
        return new AuctionDAO(auction.getId(),
                auction.getName(),
                auction.getCurrentPrice(),
                auction.getItem());
    }

}
