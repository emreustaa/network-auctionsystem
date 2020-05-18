package com.fsm.backend.Objects.User;

import com.fsm.backend.Objects.Auction.Bid;
import com.fsm.backend.Objects.Message.Error;
import com.fsm.backend.Objects.Message.Message;
import com.fsm.backend.Objects.Message.MessageRepo;
import com.fsm.backend.Objects.Message.Response;
import com.fsm.backend.Objects.User.Credentials.LoginCredentials;
import com.fsm.backend.Objects.User.Credentials.SignUpCredentials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserRepo {

    public static final ArrayList<User> users = new ArrayList<>();
    //key: userPortNum value: user AuctionID
    public static final Map<Integer, UUID> activeUsers = new HashMap<>();

    public static User findById(String userName) {
        return users.stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst()
                .orElseThrow();
    }

    public static void removeUser(int userPort) {
        activeUsers.remove(userPort);
    }

    public static void sendPriceUpdate(Bid bid) {

    }

    public static Message getLoginResponse(LoginCredentials credentials) {

        if (!UserRepo.isAuthenticated(credentials)) {
            return new Message()
                    .setError(new Error(MessageRepo.WRONG_CREDENTIALS_ERR));
        }

        if (isPortInUse(credentials.getPortNum())) {
            return new Message()
                    .setError(new Error(MessageRepo.PORT_ALREADY_TAKEN_ERR));
        }

        SignUpCredentials userInfo = getSignUpCredentials(credentials);
        activeUsers.put(credentials.getPortNum(), credentials.getAuctionId());
        return getLoginOKMessage(userInfo);
    }

    public static Message getSignUpResponse(SignUpCredentials credentials) {

        if(isUserNameTaken(credentials.getUserName())) {
            return new Message()
                    .setError(new Error(MessageRepo.USERNAME_ALREADY_TAKEN_ERR));
        }

        users.add(new User(credentials));
        return getSignUpOKMessage(credentials);
    }

    private static Message getLoginOKMessage(SignUpCredentials userInfo) {
        return new Message()
                .setResponse(new Response(MessageRepo.USER_LOGIN_RSP)
                        .setCredentials(userInfo));
    }

    private static Message getSignUpOKMessage(SignUpCredentials credentials) {
        return new Message()
                .setResponse(new Response(MessageRepo.USER_LOGIN_RSP)
                        .setCredentials(credentials));
    }

    private static boolean isAuthenticated(LoginCredentials credentials) {
        return users.stream()
                .anyMatch(user -> user.getUserName().equals(credentials.getUserName())
                        && user.getPassword().equals(credentials.getPassword()));
    }

    private static boolean isPortInUse(int portNum) {
        return activeUsers.get(portNum) != null;
    }

    public static SignUpCredentials getSignUpCredentials(LoginCredentials credentials) {
        User user = findById(credentials.getUserName());
        return SignUpCredentials.getCredentialsFrom(user);
    }

    private static boolean isUserNameTaken(String userName) {
        return users.stream()
                .anyMatch(user -> user.getUserName().equals(userName));
    }

}
