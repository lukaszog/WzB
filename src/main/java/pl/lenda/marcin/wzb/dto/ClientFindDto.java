package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 16.11.2016.
 */
public class ClientFindDto {

    String name;

    public ClientFindDto(){
    }

    public ClientFindDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
