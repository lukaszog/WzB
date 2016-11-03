package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lenda.marcin.wzb.entity.TraderAccount;
import pl.lenda.marcin.wzb.service.trader.TraderServiceImplementation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Promar on 03.11.2016.
 */
@RestController
public class TraderController {

    @Autowired
    TraderServiceImplementation traderServiceImplementation;

    @RequestMapping(value = "/save_trader", method = RequestMethod.POST)
    public TraderAccount saveTrader(@RequestBody TraderAccount traderAccount){
        return traderServiceImplementation.createTrader(traderAccount);
    }

    @RequestMapping(value = "/all_trader", method = RequestMethod.GET)
    public List<TraderAccount> findAllTrader(){
        List listTrader = new ArrayList();
        return listTrader = traderServiceImplementation.findAllTrader();
    }
}
