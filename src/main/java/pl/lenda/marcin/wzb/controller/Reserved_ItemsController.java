package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.lenda.marcin.wzb.dto.Reserved_ItemsFindByID;
import pl.lenda.marcin.wzb.dto.Reserved_ItemsFindStatistics;
import pl.lenda.marcin.wzb.entity.Reserved_Items;
import pl.lenda.marcin.wzb.entity.StatisticsItems;
import pl.lenda.marcin.wzb.service.reserved_items.Reserved_ItemsService;
import pl.lenda.marcin.wzb.service.upload.UploadFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Promar on 26.11.2016.
 */
@RestController
public class Reserved_ItemsController {

    @Autowired
    Reserved_ItemsService reserved_itemsService;
    @Autowired
    UploadFile uploadFile;
    @Autowired
    MongoTemplate mongoTemplate;

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody
    void handleFileUpload(MultipartFile file){
        System.out.println("HandleFileUpload");
        uploadFile.uploadPhoto(file);

    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/save_items", method = RequestMethod.GET)
    public void saveItems() {

        mongoTemplate.dropCollection("reserved_Items");

        String csvFile = "/home/ubuntu/WzB/src/main/resources/static/upload/last_correct.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        boolean firstLine = true;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                // use comma as separator
                String[] items = line.split(cvsSplitBy);

                if (items[2].equals(String.valueOf(0))) {
                    items[2] = "Brak";
                }

                if (items[3].equals(String.valueOf(0))) {
                    items[3] = "Brak";
                }

                if (items[4].equals(String.valueOf(0))) {
                    items[4] = "Brak";
                }

                if (items[5].equals(String.valueOf(0))) {
                    items[5] = "Brak";
                }

                if (items[6].equals(String.valueOf(0))) {
                    items[6] = "Brak";
                }

                if (items[7].equals(String.valueOf(0))) {
                    items[7] = "Brak";
                }

                if (items[8].equals(String.valueOf(0))) {
                    items[8] = "Brak";
                }

                if (items[9].equals(String.valueOf(0))) {
                    items[9] = "Brak";
                }

                if (items[10].equals(String.valueOf(0))) {
                    items[10] = "Brak";
                }


                if (items[11].equals(String.valueOf(0))) {
                    items[11] = "Brak";
                }

                if (items[12].equals(String.valueOf(0))) {
                    items[12] = "Brak";
                }

                if (items[13].equals(String.valueOf(0))) {
                    items[13] = "Brak";
                }

                if (items[14].equals(String.valueOf(0))) {
                    items[14] = "Brak";
                }

                if (items[15].equals(String.valueOf(0))) {
                    items[15] = "Brak";
                }

                Reserved_Items reserved_items = new Reserved_Items();
                reserved_items.setKbn(items[2]);
                reserved_items.setContentItem(items[3]);
                reserved_items.setDetailsContentItem(items[5]);
                reserved_items.setNumberFactory(items[4]);
                reserved_items.setPieces(items[6]);
                reserved_items.setNumberPro(items[10]);
                reserved_items.setProvider(items[9]);
                reserved_items.setSubPro(items[11]);
                reserved_items.setNameTeam(items[13]);
                reserved_items.setNameTeamCDS(items[14]);
                reserved_items.setCreator(items[15]);
                reserved_items.setDateAccepted(new Date());

                reserved_itemsService.saveItems(reserved_items);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/update_items", method = RequestMethod.POST)
    public boolean updateItems(@RequestBody Reserved_ItemsFindByID reserved_itemsFindByID) {
        Reserved_Items reserved_items = reserved_itemsService.findItem(reserved_itemsFindByID.getId());
        double piecesSum = Double.parseDouble(reserved_items.getPieces()) - Double.parseDouble(reserved_itemsFindByID.getPieces());

        reserved_items.setPieces(String.valueOf(piecesSum));
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
