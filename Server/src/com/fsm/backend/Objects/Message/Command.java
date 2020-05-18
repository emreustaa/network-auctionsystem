package com.fsm.backend.Objects.Message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsm.backend.Objects.User.Credentials.LoginCredentials;
import com.fsm.backend.Objects.User.Credentials.SignUpCredentials;

public class Command {

    private String commandType;
    @JsonProperty("loginCredentials")
    private LoginCredentials loginCredentials; //used by user login command
    @JsonProperty("signUpCredentials")
    private SignUpCredentials signUpCredentials; //used by user login command

    private int senderPort;

    @JsonCreator
    public Command(@JsonProperty("commandType") String commandType) {
        this.commandType = commandType;
    }

    public int getSenderPort() {
        return senderPort;
    }

    public Command setSenderPort(int senderPort) {
        this.senderPort = senderPort;
        return this;
    }

    public String getCommandType() {
        return commandType;
    }

    public LoginCredentials getLoginCredentials() {
        return loginCredentials;
    }

    public SignUpCredentials getSignUpCredentials() {
        return signUpCredentials;
    }

    public Command setLoginCredentials(LoginCredentials loginCredentials) {
        this.loginCredentials = loginCredentials;
        return this;
    }

    public Command setSignUpCredentials(SignUpCredentials signUpCredentials) {
        this.signUpCredentials = signUpCredentials;
        return this;
    }

}
