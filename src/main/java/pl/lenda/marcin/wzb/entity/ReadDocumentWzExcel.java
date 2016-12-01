package pl.lenda.marcin.wzb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Promar on 30.11.2016.
 */
@Document
public class ReadDocumentWzExcel {

    @Id
    private String id;

    private String numberWZ;

    private String subPro;

    private String numberClient;

    private String nameClient;

    public ReadDocumentWzExcel(){
    }

    public ReadDocumentWzExcel(String id, String numberWZ, String subPro, String numberClient, String nameClient) {
        this.id = id;
        this.numberWZ = numberWZ;
        this.subPro = subPro;
        this.numberClient = numberClient;
        this.nameClient = nameClient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumberWZ() {
        return numberWZ;
    }

    public void setNumberWZ(String numberWZ) {
        this.numberWZ = numberWZ;
    }

    public String getSubPro() {
        return subPro;
    }

    public void setSubPro(String subPro) {
        this.subPro = subPro;
    }

    public String getNumberClient() {
        return numberClient;
    }

    public void setNumberClient(String numberClient) {
        this.numberClient = numberClient;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }
}
