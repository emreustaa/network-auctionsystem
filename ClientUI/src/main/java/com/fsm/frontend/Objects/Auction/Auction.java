package com.fsm.frontend.Objects.Auction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsm.frontend.Objects.DAO.AuctionDAO;
import com.fsm.frontend.Objects.Valuable.Valuable;

import java.util.UUID;

public class Auction {

    private UUID id;
    private String name;
    private int currentPrice;
    private String winner;
    private Valuable item;

    @JsonCreator
    public Auction(@JsonProperty("auctionDAO") AuctionDAO auctionDAO) {
        this.id = auctionDAO.getId();
        this.name = auctionDAO.getName();
        this.currentPrice = auctionDAO.getCurrentPrice();
        this.winner = auctionDAO.getWinner();
        this.item = auctionDAO.getItem();
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

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Valuable getItem() {
        return item;
    }
}
