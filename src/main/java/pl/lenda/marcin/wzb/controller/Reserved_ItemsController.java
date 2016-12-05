package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.dto.Reserved_ItemsFindByID;
import pl.lenda.marcin.wzb.dto.Reserved_ItemsFindStatistics;
import pl.lenda.marcin.wzb.entity.Reserved_Items;
import pl.lenda.marcin.wzb.entity.StatisticsItems;
import pl.lenda.marcin.wzb.service.reserved_items.Reserved_ItemsService;

import java.util.Date;
import java.util.List;

/**
 * Created by Promar on 26.11.2016.
 */
@RestController
public class Reserved_ItemsController {

    @Autowired
    Reserved_ItemsService reserved_itemsService;

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save_items", method = RequestMethod.POST)
    public void saveItems(@RequestBody Reserved_Items reserved_items) {
        long diff = 0;
        long diffDay = 0;
        System.out.println("czas " + reserved_items.getDateAccepted().getTime());
        diff = new Date().getTime() - reserved_items.getDateAccepted().getTime();
        diffDay = diff / (24 * 60 * 60 * 1000);
        reserved_items.setDelay(String.valueOf(diffDay));
        reserved_items.setAllPrice(String.valueOf(Integer.parseInt(reserved_items.getPieces()) *
        Integer.parseInt(reserved_items.getPriceItem())));
        reserved_itemsService.saveItems(reserved_items);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/update_items", method = RequestMethod.POST)
    public boolean updateItems(@RequestBody Reserved_ItemsFindByID reserved_itemsFindByID) {
        Reserved_Items reserved_items = reserved_itemsService.findItem(reserved_itemsFindByID.getId());
        int piecesSum = Integer.parseInt(reserved_items.getPieces()) - Integer.parseUnsignedInt(reserved_itemsFindByID.getPieces());
        int sum = Integer.parseInt(reserved_items.getAllPrice()) -
                (Integer.parseInt(reserved_items.getPriceItem()) * Integer.parseInt(reserved_itemsFindByID.getPieces()));
        reserved_items.setPieces(String.valueOf(piecesSum));
        reserved_items.setAllPrice(String.valueOf(sum));
        reserved_itemsService.saveItems(reserved_items);
        return true;
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/findAll_items", method = RequestMethod.GET)
    public List<Reserved_Items> allItems() {
        return reserved_itemsService.findAll();
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/delete_items", method = RequestMethod.DELETE)
    public void deleteItems(@RequestBody Reserved_ItemsFindByID reserved_itemsFindByID) {
        Reserved_Items reserved_items = reserved_itemsService.findItem(reserved_itemsFindByID.getId());
        reserved_itemsService.delete(reserved_items);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/findItemBy_ID", method = RequestMethod.POST)
    public Reserved_Items allItems(@RequestBody Reserved_ItemsFindByID reserved_itemsFindByID) {
        return reserved_itemsService.findItem(reserved_itemsFindByID.getId());
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    public StatisticsItems TeamStatistics(@RequestBody Reserved_ItemsFindStatistics reserved_itemsFindStatistics) {
        List<Reserved_Items> listReserved_items = reserved_itemsService.findByNameTeam(reserved_itemsFindStatistics.getNameTeam());

        Integer last30Days = 0;
        Integer last60Days = 0;
        Integer last90Days = 0;
        Integer last180Days = 0;
        Integer lastYear = 0;
        Integer sum = 0;
        Integer allPieces = 0;

        for (Reserved_Items items : listReserved_items) {
            sum = sum + Integer.parseInt(items.getAllPrice());
            allPieces++;
            if (Integer.parseInt(items.getDelay()) < 30) {
                last30Days = last30Days + (Integer.parseInt(items.getPriceItem()) * Integer.parseInt(items.getPieces()));
            }
            if (Integer.parseInt(items.getDelay()) > 30 && Integer.parseInt(items.getDelay()) < 60) {
                last60Days = last60Days + (Integer.parseInt(items.getPriceItem()) * Integer.parseInt(items.getPieces()));

            }
            if (Integer.parseInt(items.getDelay()) > 60 && Integer.parseInt(items.getDelay()) < 90) {
                last90Days = last90Days + (Integer.parseInt(items.getPriceItem()) * Integer.parseInt(items.getPieces()));

            }
            if (Integer.parseInt(items.getDelay()) < 180) {
                last180Days = last90Days + (Integer.parseInt(items.getPriceItem()) * Integer.parseInt(items.getPieces()));

            }
            if( Integer.parseInt(items.getDelay()) < 360){
                lastYear = lastYear + (Integer.parseInt(items.getPriceItem()) * Integer.parseInt(items.getPieces()));
            }
        }

        StatisticsItems statisticsItems = new StatisticsItems();
        statisticsItems.setLast30Days(last30Days);
        statisticsItems.setLast60Days(last60Days);
        statisticsItems.setLast90Days(last90Days);
        statisticsItems.setLast180Days(last180Days);
        statisticsItems.setLastYear(lastYear);
        statisticsItems.setNameTeam(reserved_itemsFindStatistics.getNameTeam());
        statisticsItems.setAllSum(String.valueOf(sum));
        statisticsItems.setAllPieces(String.valueOf(allPieces));
        return statisticsItems;
    }
}
