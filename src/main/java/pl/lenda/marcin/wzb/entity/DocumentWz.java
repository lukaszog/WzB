package pl.lenda.marcin.wzb.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

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

    private String numberWZ;
    @NotNull
    private String subProcess;
    @NotNull
    private String client;
    @NotNull
    private String clientNumber;
    @NotNull
    private String traderName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;

    public DocumentWz(){
    }

    public DocumentWz(String numberWZ, String subProcess, String client, String clientNumber, String traderName, Date date) {
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

    public String getNumberWZ() {
        return numberWZ;
    }

    public void setNumberWZ(String numberWZ) {
        this.numberWZ = numberWZ;
    }

    public String getSubProcess() {
        return subProcess;
    }

    public void setSubProcess(String subProcess) {
        this.subProcess = subProcess;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
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
