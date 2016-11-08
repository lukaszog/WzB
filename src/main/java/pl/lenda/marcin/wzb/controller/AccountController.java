package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.dto.UserAccountActiveDto;
import pl.lenda.marcin.wzb.dto.UserAccountDto;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.user_account.UserAccountService;

import java.security.Principal;
import java.util.List;

/**
 * Created by Promar on 06.11.2016.
 */
@RestController
@RequestMapping("/myAccount")
public class AccountController {

    @Autowired
    UserAccountService userAccountService;
    @Autowired
    ConvertTo convertTo;

    @RequestMapping(value = "/create_account", method = RequestMethod.POST)
    public void createNewUser(@RequestBody UserAccountDto userAccountDto){
        userAccountService.registerNewUser(convertTo.converToUserAcountEntity(userAccountDto));
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping(value = "/find_notactive_account", method = RequestMethod.GET)
    public List<UserAccount> findUserNotActive(){
         return    userAccountService.findNotActiveAccount();
        }

    @RequestMapping(value = "/make_active_account", method = RequestMethod.PATCH)
    public void makeAccountActive(@RequestBody UserAccountActiveDto userAccountActiveDto){
       UserAccount userAccount = userAccountService.findByUsername(userAccountActiveDto.getUsername());
        userAccount.setActive(true);
        userAccountService.registerNewUser(userAccount);
    }

    }


