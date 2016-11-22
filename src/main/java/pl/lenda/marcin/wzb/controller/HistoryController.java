package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lenda.marcin.wzb.entity.HistoryDeleteDocumentWz;
import pl.lenda.marcin.wzb.service.history.HistoryService;

import java.util.List;

/**
 * Created by Promar on 22.11.2016.
 */
@RestController
public class HistoryController {


    @Autowired
    HistoryService historyService;

    @RequestMapping(value = "/all_deleteE", method = RequestMethod.GET)
    public List<HistoryDeleteDocumentWz> showAllDocumentDelete(){
        return historyService.showAllDeleteDocument();
    }
}
