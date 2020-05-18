package com.fsm.backend.Controller;

import com.fsm.backend.Annotation.Action;
import com.fsm.backend.Annotation.Controller;
import com.fsm.backend.Annotation.QueryParam;
import com.fsm.backend.Enums.TYPE;
import com.fsm.backend.Interfaces.MyHttpHandler;
import com.fsm.backend.Objects.Message.Message;
import com.fsm.backend.Objects.User.Credentials.LoginCredentials;
import com.fsm.backend.Objects.User.Credentials.SignUpCredentials;
import com.fsm.backend.Objects.User.UserRepo;
import com.fsm.backend.Utils.ControllerUtils;

@Controller(path = "users")
public class UserController implements MyHttpHandler {

    @Action(path = "createUser", type = TYPE.POST)
    public Message create(@QueryParam(type = Message.class) Message message) {
        SignUpCredentials credentials = message.getCommand().getSignUpCredentials();
        return UserRepo.getSignUpResponse(credentials);
    }

    @Action(path = "login", type = TYPE.POST)
    public Message login(@QueryParam(type = Message.class) Message message) {
        LoginCredentials credentials = message.getCommand().getLoginCredentials();
        return UserRepo.getLoginResponse(credentials);
    }

    @Action(path = "logout", type = TYPE.POST)
    public Message logout(@QueryParam(type = Message.class) Message message) {
        int sender = message.getCommand().getSenderPort();
        UserRepo.removeUser(sender);
        return ControllerUtils.getDummyResponse();
    }

//    @Action(path = "updateUser", type = TYPE.POST)
//    public User update(@QueryParam(type = User.class) User user) {
//        return User.repository.update(user);
//    }

//    @Action(path = "getAllUsers")
//    public List<User> getAllUsers() {
//        return new ArrayList<>(User.repository.getAll());
//    }

}
