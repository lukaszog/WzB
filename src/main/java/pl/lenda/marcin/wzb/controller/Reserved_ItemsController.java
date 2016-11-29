package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.entity.Reserved_Items;
import pl.lenda.marcin.wzb.service.reserved_items.Reserved_ItemsService;

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
    public void saveItems(@RequestBody Reserved_Items reserved_items){
        reserved_itemsService.saveItems(reserved_items);
    }

    @CrossOrigin(origins = "http://wzb24.pl")
    @RequestMapping(value = "/findAll_items", method = RequestMethod.GET)
    public List<Reserved_Items> allItems(){
        return reserved_itemsService.findAll();
    }
}
