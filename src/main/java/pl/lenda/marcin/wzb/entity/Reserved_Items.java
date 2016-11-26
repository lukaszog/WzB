package pl.lenda.marcin.wzb.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by Promar on 26.11.2016.
 */
@Document
public class Reserved_Items {

    private String numberPro;

    private String contentItem;

    private String clientName;

    private String traderName;

    private String nameTeam;

    private String kbn;

    private String provider;

    private String businessSector;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String dateAccepted;

    private String priceItem;

    public Reserved_Items(){
    }

    public Reserved_Items(String numberPro, String contentItem, String clientName, String traderName, String nameTeam,
                          String kbn, String provider, String businessSector, String dateAccepted, String priceItem) {
        this.numberPro = numberPro;
        this.contentItem = contentItem;
        this.clientName = clientName;
        this.traderName = traderName;
        this.nameTeam = nameTeam;
        this.kbn = kbn;
        this.provider = provider;
        this.businessSector = businessSector;
        this.dateAccepted = dateAccepted;
        this.priceItem = priceItem;
    }

    public String getNumberPro() {
        return numberPro;
    }

    public void setNumberPro(String numberPro) {
        this.numberPro = numberPro;
    }

    public String getContentItem() {
        return contentItem;
    }

    public void setContentItem(String contentItem) {
        this.contentItem = contentItem;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getBusinessSector() {
        return businessSector;
    }

    public void setBusinessSector(String businessSector) {
        this.businessSector = businessSector;
    }

    public String getDateAccepted() {
        return dateAccepted;
    }

    public void setDateAccepted(String dateAccepted) {
        this.dateAccepted = dateAccepted;
    }

    public String getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(String priceItem) {
        this.priceItem = priceItem;
    }

    public String getKbn() {
        return kbn;
    }

    public void setKbn(String kbn) {
        this.kbn = kbn;
    }
}
