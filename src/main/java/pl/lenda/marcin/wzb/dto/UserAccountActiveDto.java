package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 07.11.2016.
 */
public class UserAccountActiveDto {

    private String username;

    public UserAccountActiveDto(){
    }

    public UserAccountActiveDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
