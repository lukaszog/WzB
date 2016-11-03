package pl.lenda.marcin.wzb.dto;

import java.util.Date;

/**
 * Created by Promar on 30.10.2016.
 */

public class DocumentWzDto {


    private String numberWZ;

    private String subProcess;

    private String client;

    private String clientNumber;

    private String nameTeam;

    private String traderName;

    private Date date;

    private boolean beCorrects;

    public DocumentWzDto(String numberWZ, String subProcess, String client, String clientNumber, String nameTeam,
                         String traderName, Date date, boolean beCorrects) {
        this.numberWZ = numberWZ;
        this.subProcess = subProcess;
        this.client = client;
        this.clientNumber = clientNumber;
        this.nameTeam = nameTeam;
        this.traderName = traderName;
        this.date = date;
        this.beCorrects = beCorrects;
    }

    public DocumentWzDto() {
    }

    public DocumentWzDto(String numberWZ, String subProcess, String client, String traderName, Date date) {
        this.numberWZ = numberWZ;
        this.subProcess = subProcess;
        this.client = client;
        this.traderName = traderName;
        this.date = date;
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

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
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

    public boolean isBeCorrects() {
        return beCorrects;
    }

    public void setBeCorrects(boolean beCorrects) {
        this.beCorrects = beCorrects;
    }
}
