package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.entity.DocumentWz;
import pl.lenda.marcin.wzb.service.document_wz.DocumentWzServiceImplementation;
import pl.lenda.marcin.wzb.validator.Validator;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Promar on 09.10.2016.
 */
@RestController
public class ControllerDocumentWz {

    private final DocumentWzServiceImplementation documentWzServiceImplementation;
    @Resource
    private Validator validator;

    @Autowired
    public ControllerDocumentWz(DocumentWzServiceImplementation documentWzServiceImplementation) {
        this.documentWzServiceImplementation = documentWzServiceImplementation;
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/saveDocument")
    public
    @ResponseBody
    ResponseEntity<?> createDocumentWz(@RequestBody DocumentWz documentWz) {
        return validator.checkAndSaveDocument(documentWz);
    }

    @RequestMapping(value = "/deleteDocument", method = RequestMethod.DELETE)
    public void deleteDocument(Integer numberWZ) {
        DocumentWz documentWz = documentWzServiceImplementation.findByNumberWz(numberWZ);
        documentWzServiceImplementation.removeDocumentWz(documentWz);

    }

    @RequestMapping(value = "/findByNumber", method = RequestMethod.POST)
    public DocumentWz findDocumentWz(@RequestBody Integer numberWZ) {
        DocumentWz byNumberWz = documentWzServiceImplementation.findByNumberWz(numberWZ);
        return byNumberWz;
    }

    @RequestMapping(value = "/findByClient", method = RequestMethod.POST)
    public List<DocumentWz> findByClient(@RequestBody String client) {
        return documentWzServiceImplementation.findByNameClient(client);
    }

    @RequestMapping(value = "/findByClientNumber", method = RequestMethod.POST)
    public List<DocumentWz> findByClientNumber(@RequestBody Integer clientNumber) {
        return documentWzServiceImplementation.findByNumberClient(clientNumber);
    }

    @RequestMapping(value = "/findByTraderName", method = RequestMethod.POST)
    public List<DocumentWz> findByTraderName(@RequestBody String traderName) {
        return documentWzServiceImplementation.findByNameTrader(traderName);
    }

}
