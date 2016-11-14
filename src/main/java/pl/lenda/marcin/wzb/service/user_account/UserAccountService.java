package pl.lenda.marcin.wzb.service.user_account;

import pl.lenda.marcin.wzb.dto.ChangePasswordDto;
import pl.lenda.marcin.wzb.entity.UserAccount;

import java.util.List;

/**
 * Created by Promar on 06.11.2016.
 */
public interface UserAccountService {

    void registerNewUser(UserAccount userAccount);

    UserAccount findByUsername(String username);

    UserAccount findByNameAndSurname(String name, String surname);

    UserAccount findByNumberUser(String numberUser);

    List<UserAccount> findActiveAccount();

    List<UserAccount> findNotActiveAccount();

    List<UserAccount> findAllAccount();

    boolean updateRole(UserAccount userAccount);

    void changePassword(ChangePasswordDto changePasswordDto, UserAccount userAccount);

    String getRoleOfLoggedUser();
}