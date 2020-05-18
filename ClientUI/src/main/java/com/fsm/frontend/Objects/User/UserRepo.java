package com.fsm.frontend.Objects.User;

public class UserRepo {
    //TODO STATIC SIKINTI ÇIKARABİLİR
    private static User user;

    public UserRepo(User user) {
        UserRepo.user = user;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserRepo.user = user;
    }

}
