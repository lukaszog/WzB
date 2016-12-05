package pl.lenda.marcin.wzb.dto;

/**
 * Created by Promar on 02.12.2016.
 */
public class Reserved_ItemsFindByID {

    private String id;

    private String pieces;

    public Reserved_ItemsFindByID(){
    }

    public Reserved_ItemsFindByID(String id, String pieces) {
        this.id = id;
        this.pieces = pieces;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPieces() {
        return pieces;
    }

    public void setPieces(String pieces) {
        this.pieces = pieces;
    }
}
