package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 03.11.2016.
 */
public class TraderAccountDto {

    private String name;

    private String surname;

    private String nameTeam;

    public TraderAccountDto(){
    }

    public TraderAccountDto(String name, String surname, String nameTeam) {
        this.name = name;
        this.surname = surname;
        this.nameTeam = nameTeam;
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

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }
}
