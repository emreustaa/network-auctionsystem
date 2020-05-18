package com.fsm.backend.Objects.User.Credentials;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class LoginCredentials {

    private String userName;
    private String password;
    private int portNum;
    private UUID auctionId;

    @JsonCreator
    public LoginCredentials(@JsonProperty("userName") String userName,
                            @JsonProperty("password") String password,
                            @JsonProperty("portNum") int portNum,
                            @JsonProperty("auctionId") String auctionId) {
        this.userName = userName;
        this.password = password;
        this.portNum = portNum;
        this.auctionId = UUID.fromString(auctionId);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getPortNum() {
        return portNum;
    }

    public UUID getAuctionId() {
        return auctionId;
    }
}
