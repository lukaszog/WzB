package pl.lenda.marcin.wzb.dto;

import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotNull;

/**
 * Created by Promar on 30.10.2016.
 */
public class UserAccountDto {

    @NotNull(message = "Podaj login.")
    @Indexed(unique = true)
    private String username;

    @NotNull(message = "Podaj hasło.")
    private String password;

    private String confirmPassword;

    private String role;

    public UserAccountDto(){
    }

    public UserAccountDto(String username, String password, String confirmPassword, String role) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
    }

    public String getEmail() {
        return username;
    }

    public void setEmail(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
