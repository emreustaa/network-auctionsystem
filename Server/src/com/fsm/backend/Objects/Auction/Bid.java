package com.fsm.backend.Objects.Auction;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Bid {

    private UUID auctionId;
    private String userName;
    private UUID bidId;
    private int newPrice;

    @JsonCreator
    public Bid(@JsonProperty("bidId") String bidId,
               @JsonProperty("auctionId") String auctionId,
               @JsonProperty("userName") String userName,
               @JsonProperty("newPrice") int newPrice) {
        this.bidId = UUID.fromString(bidId);
        this.auctionId = UUID.fromString(auctionId);
        this.userName = userName;
        this.newPrice = newPrice;
    }

    public Bid(UUID auctionId, String userName, int newPrice) {
        this.auctionId = auctionId;
        this.userName = userName;
        this.newPrice = newPrice;
        this.bidId = UUID.randomUUID();
    }

    public UUID getAuctionId() {
        return auctionId;
    }

    public String getUserName() {
        return userName;
    }

    public int getNewPrice() {
        return newPrice;
    }

    public UUID getBidId() {
        return bidId;
    }
}
