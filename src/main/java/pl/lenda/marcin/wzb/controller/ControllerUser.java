package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.service.user.UserServiceImplementation;

/**
 * Created by Promar on 10.10.2016.
 */
public class ControllerUser {

    private UserServiceImplementation userServiceImplementation;

    @Autowired
    public ControllerUser(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public UserAccount createAccount(@RequestBody UserAccount userAccount) {
        return userServiceImplementation.registerUser(userAccount);
    }
}
