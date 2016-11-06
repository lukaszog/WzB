package pl.lenda.marcin.wzb.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.repository.UserAccountRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Promar on 06.11.2016.
 */
@Service
public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = getUserDetail(username);
        UserDetails userDetails = new User(userAccount.getUsername(), userAccount.getPassword(), getAuthorities(userAccount.getRole()));

        return userDetails;
    }

    public List<GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authList = new ArrayList<>();
        if (role.equalsIgnoreCase("ADMIN")) {
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        } else if (role.equalsIgnoreCase("USER")) {
            authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authList;
    }

    public UserAccount getUserDetail(String username) {
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        return userAccount;
    }
}
