package com.fsm.backend.Objects.Message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsm.backend.Objects.DAO.AuctionDAO;
import com.fsm.backend.Objects.User.Credentials.SignUpCredentials;

import java.util.List;

public class Response {

    private String responseType;

    @JsonProperty("signUpCredentials")
    private SignUpCredentials credentials;

    private List<AuctionDAO> auctionDAOS;


    @JsonCreator
    public Response(@JsonProperty("responseType") String responseType) {
        this.responseType = responseType;
    }

    public SignUpCredentials getCredentials() {
        return credentials;
    }

    public Response setCredentials(SignUpCredentials credentials) {
        this.credentials = credentials;
        return this;
    }

    public List<AuctionDAO> getAuctionDAOS() {
        return auctionDAOS;
    }

    public Response setAuctionDAOs(List<AuctionDAO> auctions) {
        this.auctionDAOS = auctions;
        return this;
    }
}
