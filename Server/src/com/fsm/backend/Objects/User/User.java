package com.fsm.backend.Objects.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsm.backend.Objects.User.Credentials.SignUpCredentials;
import com.fsm.backend.Objects.Valuable.Valuable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "unused"})
public class User {

    private String userName;
    private String password;
    private String mail;
    private int currency;
    private int limit;
    private int increaseRate = 0;
    private int portNum;

    private final List<Valuable> itemCollection = new ArrayList<>();

    @JsonCreator
    public User(@JsonProperty("credentials") SignUpCredentials credentials) {
        this.userName = credentials.getUserName();
        this.password = credentials.getPassword();
        this.currency = credentials.getCurrency();
        this.limit = currency;
        this.mail = credentials.getMail();
    }

    public void buy(Valuable valuable, int lastPrice) {
        itemCollection.add(valuable);
        currency -= lastPrice;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public int getCurrency() {
        return currency;
    }

    public int getLimit() {
        return limit;
    }

    public int getIncreaseRate() {
        return increaseRate;
    }

    public int getPortNum() {
        return portNum;
    }

    public User setPortNum(int portNum) {
        this.portNum = portNum;
        return this;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "User{" +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", currency=" + currency +
                ", limit=" + limit +
                ", increaseRate=" + increaseRate +
                '}';
    }}
