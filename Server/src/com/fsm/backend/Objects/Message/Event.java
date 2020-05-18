package com.fsm.backend.Objects.Message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsm.backend.Objects.Auction.Bid;
import com.fsm.backend.Objects.DAO.AuctionDAO;

public class Event {

    private String eventType;

    @JsonProperty("auctionDAO")
    private AuctionDAO auctionDAO; //used by auction created event

    @JsonProperty("bid")
    private Bid bid;

    //TODO Message Dışında Hiçbirşeyin altına is... metodu koyma

    @JsonCreator
    public Event(@JsonProperty("eventType") String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    public Bid getBid() {
        return bid;
    }

    public Event setBid(Bid bid) {
        this.bid = bid;
        return this;
    }

    public AuctionDAO getAuctionDAO() {
        return auctionDAO;
    }

    public Event setAuctionDAO(AuctionDAO auctionDAO) {
        this.auctionDAO = auctionDAO;
        return this;
    }

}
