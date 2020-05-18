package com.fsm.backend.Objects.User.Credentials;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsm.backend.Objects.User.User;

public class SignUpCredentials {

    private String userName;
    private String password;
    private String mail;
    private int currency;

    @JsonCreator
    public SignUpCredentials(@JsonProperty("userName") String userName,
                             @JsonProperty("password") String password,
                             @JsonProperty("mail") String mail,
                             @JsonProperty("currency") int currency) {
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.currency = currency;
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

    public SignUpCredentials setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public SignUpCredentials setPassword(String password) {
        this.password = password;
        return this;
    }

    public SignUpCredentials setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public SignUpCredentials setCurrency(int currency) {
        this.currency = currency;
        return this;
    }

    public static SignUpCredentials getCredentialsFrom(User user) {
        return new SignUpCredentials(user.getUserName(),
                user.getPassword(),
                user.getMail(),
                user.getCurrency());
    }

}
