package pl.lenda.marcin.wzb.entity;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by Promar on 10.10.2016.
 */
public class UserAccount {

    @Id
    private String id;
    @NotNull
    private String surnameTrader;
    @NotNull
    private String password;
    @NotNull
    private String email;

    public UserAccount() {
    }

    public UserAccount(String surnameTrader, String password, String email) {
        this.surnameTrader = surnameTrader;
        this.password = password;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurnameTrader() {
        return surnameTrader;
    }

    public void setSurnameTrader(String surnameTrader) {
        this.surnameTrader = surnameTrader;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
