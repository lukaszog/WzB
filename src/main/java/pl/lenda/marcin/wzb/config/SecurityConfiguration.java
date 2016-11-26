package pl.lenda.marcin.wzb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * Created by Promar on 06.11.2016.
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .loginPage("/unauthorized")
                    .loginProcessingUrl("/perform_login")
                    .defaultSuccessUrl("/success", true)
                .and()
                .authorizeRequests()
                .antMatchers("/unauthorized").permitAll()
                .antMatchers("/", "/index.html", "/views/main.html", "/views/login.html", "/views/register/**").permitAll()
                .antMatchers("/howManyDocument","/howManyTraders","/howManyClient").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/bower_components/**").permitAll()
                .antMatchers("/components/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/home/newUser").permitAll()
                .antMatchers("/myAccount/create_account").permitAll()
                .antMatchers("/myAccount/user").permitAll()
                .antMatchers("/myAccount/user","/myAccount/find_by_email").permitAll()
                .antMatchers("/login?logout").permitAll()
                .antMatchers("/views/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

}
