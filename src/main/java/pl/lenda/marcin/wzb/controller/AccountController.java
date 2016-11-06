package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.service.user_account.UserAccountService;

import java.security.Principal;

/**
 * Created by Promar on 06.11.2016.
 */
@RestController
@RequestMapping("/myAccount")
public class AccountController {

    @Autowired
    UserAccountService userAccountService;

    @RequestMapping(value = "/create_account", method = RequestMethod.POST)
    public void createNewUser(@RequestBody UserAccount userAccount){
        userAccountService.registerNewUser(userAccount);
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping(value = "/find_by_email", method = RequestMethod.POST)
    public UserAccount findUser(@RequestParam("username") String username){
         return    userAccountService.findByUsername(username);
        }

    }


