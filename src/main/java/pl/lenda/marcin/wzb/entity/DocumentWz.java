package pl.lenda.marcin.wzb.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Promar on 09.10.2016.
 */
@Document
public class DocumentWz {

    @Id
    private String id;
    @NotNull
    private Integer numberWZ;
    @NotNull
    private Integer subProcess;
    @NotNull
    private String client;
    @NotNull
    private Integer clientNumber;
    @NotNull
    private String traderName;
    @NotNull
    private Date date;

    public DocumentWz(){
    }

    public DocumentWz(Integer numberWZ, Integer subProcess, String client, Integer clientNumber, String traderName, Date date) {
        this.numberWZ = numberWZ;
        this.subProcess = subProcess;
        this.client = client;
        this.clientNumber = clientNumber;
        this.traderName = traderName;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumberWZ() {
        return numberWZ;
    }

    public void setNumberWZ(Integer numberWZ) {
        this.numberWZ = numberWZ;
    }

    public Integer getSubProcess() {
        return subProcess;
    }

    public void setSubProcess(Integer subProcess) {
        this.subProcess = subProcess;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Integer getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(Integer clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
