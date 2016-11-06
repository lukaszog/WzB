package pl.lenda.marcin.wzb.service.user_account;

import pl.lenda.marcin.wzb.entity.UserAccount;

/**
 * Created by Promar on 06.11.2016.
 */
public interface UserAccountService {

    void registerNewUser(UserAccount userAccount);

    UserAccount findByUsername(String username);
}
