package pl.lenda.marcin.wzb.service.user_account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.repository.UserAccountRepository;

import java.util.List;

/**
 * Created by Promar on 06.11.2016.
 */
@Service
public class UserAccountImplementation implements UserAccountService{

    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void registerNewUser(UserAccount newUserAccount) {
        newUserAccount.setPassword(bCryptPasswordEncoder.encode(newUserAccount.getPassword()));
        if (checkIfAdminExists()) {
            newUserAccount.setRole("USER");
        } else {
            newUserAccount.setRole("ADMIN");
        }
        userAccountRepository.save(newUserAccount);

    }

    @Override
    public UserAccount findByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }


    private boolean checkIfAdminExists() {
        List<UserAccount> userEntityList = userAccountRepository.findByRole("ADMIN");
        for (UserAccount userEntity : userEntityList) {
            if (userEntity.getRole().equals("ADMIN")) {
                return true;
            }
        }
        return false;
    }
}
