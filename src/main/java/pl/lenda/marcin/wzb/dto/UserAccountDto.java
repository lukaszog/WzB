package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 30.10.2016.
 */
public class UserAccountDto {

    private String username;

    private String name;

    private String surname;

    private String password;

    private String confirmPassword;

    private String role;

    public UserAccountDto(){
    }

    public UserAccountDto(String username, String name, String surname, String password, String confirmPassword, String role) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
