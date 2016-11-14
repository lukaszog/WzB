package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.dto.*;
import pl.lenda.marcin.wzb.entity.TraderAccount;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.document_wz.DocumentWzService;
import pl.lenda.marcin.wzb.service.mail.MailService;
import pl.lenda.marcin.wzb.service.trader.TraderService;
import pl.lenda.marcin.wzb.service.user_account.UserAccountService;

import javax.annotation.security.RolesAllowed;
import javax.mail.MessagingException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Promar on 06.11.2016.
 */
@RestController
@RequestMapping("/myAccount")
public class AccountController {

    @Autowired
    UserAccountService userAccountService;
    @Autowired
    TraderService traderService;
    @Autowired
    DocumentWzService documentWzService;
    @Autowired
    ConvertTo convertTo;
    @Autowired
    MailService mailService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private final Map<String, Object> response = new LinkedHashMap<>();

    @CrossOrigin(origins = "${application.url}")
    @RequestMapping(value = "/create_account", method = RequestMethod.POST)
    public Map<String, Object> createNewUser(@Valid @RequestBody UserAccountDto userAccountDto,
                                             BindingResult bindingResult) {
        response.clear();
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            response.put("message", "Error");
            for (FieldError error : errors) {
                response.put(error.getObjectName(), error.getDefaultMessage());
            }
        }

        if (userAccountService.findByUsername(userAccountDto.getUsername()) != null) {
            response.put("Error", "ExistsUser");
            return response;
        }

        if (userAccountService.findByNumberUser(userAccountDto.getNumberUser()) != null) {
            response.put("Error", "ExistsNumber");
            return response;
        }

        if (!userAccountDto.getPassword().equals(userAccountDto.getConfirmPassword())) {
            response.put("Error", "WrongPass");
            return response;
        } else {
            userAccountService.registerNewUser(convertTo.converToUserAccountEntity(userAccountDto));
            response.put("Success", "Create");
        }
        return response;
    }

    @CrossOrigin(origins = "${application.url}")
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @CrossOrigin(origins = "${application.url}")
    @RequestMapping(value = "/find_notactive_account", method = RequestMethod.GET)
    public List<UserAccount> findUserNotActive() {
        return userAccountService.findNotActiveAccount();
    }

    @CrossOrigin(origins = "${application.url}")
    @RequestMapping(value = "/active_account", method = RequestMethod.GET)
    public List<UserAccountDto> findAllActiveAccount() {
        List<UserAccountDto> listDto = new ArrayList();
        List<UserAccount> listEntity = userAccountService.findActiveAccount();

        for (UserAccount entity : listEntity) {
            listDto.add(convertTo.converToUserAccountDto(entity));
        }
        return listDto;
    }

    @CrossOrigin(origins = "${application.url}")
    @RequestMapping(value = "/make_active_account", method = RequestMethod.PATCH)
    public void makeAccountActive(@RequestBody UserAccountActiveDto userAccountActiveDto) throws MessagingException {
        UserAccount userAccount = userAccountService.findByUsername(userAccountActiveDto.getUsername());
        userAccount.setActive(true);
        userAccountService.registerNewUser(userAccount);
        mailService.mailConfirmAccount(userAccount.getUsername(), userAccount.getName(), userAccount.getSurname(), userAccount.getUsername());

    }

    @CrossOrigin(origins = "${application.url}")
    @RequestMapping(value = "/block_account", method = RequestMethod.PATCH)
    public void blockAccount(@RequestBody UserAccountActiveDto userAccountActiveDto) {
        UserAccount userAccount = userAccountService.findByUsername(userAccountActiveDto.getUsername());
        userAccount.setActive(false);
        userAccountService.registerNewUser(userAccount);
    }

    @CrossOrigin(origins = "${application.url}")
    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping(value = "/give_admin", method = RequestMethod.POST)
    public boolean giveRoleAdmin(@RequestBody String username) {

        UserAccount userAccount = userAccountService.findByUsername(username);
        userAccount.setRole("ADMIN");
        return userAccountService.updateRole(userAccount);
    }

    @CrossOrigin(origins = "${application.url}")
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public boolean getRole() {
        if (userAccountService.getRoleOfLoggedUser().equals("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }

    @CrossOrigin(origins = "${application.url}")
    @RequestMapping(value = "/user_info", method = RequestMethod.POST)
    public UserInfoDto userInfo(@RequestBody UserNameDto userNameDto) {

        UserAccount userAccount = userAccountService.findByUsername(userNameDto.getUsername());
        TraderAccount traderAccount = traderService.findBySurname(userAccount.getSurname());
        UserInfoDto userInfoDto = new UserInfoDto();
        if (traderAccount != null) {
            userInfoDto.setNameTeam(traderAccount.getNameTeam());
            userInfoDto.setName(traderAccount.getName());
            userInfoDto.setSurname(traderAccount.getSurname());
            userInfoDto.setUsername(userNameDto.getUsername());
        } else {
            userInfoDto.setNameTeam("X");
            userInfoDto.setName(userAccount.getName());
            userInfoDto.setSurname(userAccount.getSurname());
            userInfoDto.setUsername(userNameDto.getUsername());
        }

        return userInfoDto;
    }

    @CrossOrigin(origins = "${application.url}")
    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    public Map<String, Object> changePassword(@RequestBody ChangePasswordDto changePasswordDto,
                                              BindingResult bindingResult) {
        response.clear();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAccount userAccount = userAccountService.findByUsername(authentication.getName());

        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmNewPassword())) {
            response.put("Error", "diffPass");
            return response;
        }

        if (bCryptPasswordEncoder.matches(changePasswordDto.getOldPassword(), userAccount.getPassword()) &&
                changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmNewPassword())) {

            userAccountService.changePassword(changePasswordDto, userAccount);
            response.put("Success", "newPass");
            return response;
        } else {
            response.put("Error", "wrongPass");
            return response;
        }


    }

}


