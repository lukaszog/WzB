package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.entity.ClientAccount;
import pl.lenda.marcin.wzb.service.client_account.ClientAccountService;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Promar on 03.11.2016.
 */
@RestController
public class ClientController {

    @Autowired
    private ClientAccountService clientAccountService;
    private ConvertTo convertTo;

    @RequestMapping(value = "/save_client", method = RequestMethod.POST)
    public @ResponseBody ClientAccount  saveClient(@RequestBody ClientAccount clientAccount){
        clientAccountService.createAccount(clientAccount);
        return clientAccount;
    }

    @RequestMapping(value = "/all_client", method = RequestMethod.GET)
    public List<ClientAccount> allClientAccount(){
        List<ClientAccount> listClient = new ArrayList<>();
        listClient = clientAccountService.findAllClient();
        return listClient;
    }

}
