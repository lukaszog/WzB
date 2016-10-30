package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.dto.DocumentWzDto;
import pl.lenda.marcin.wzb.dto.FindByNumberWzDto;
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
    private FindByNumberWzDto findByNumberWzDto;
    private DocumentWzDto documentWzDto;

    @Autowired
    public ControllerDocumentWz(DocumentWzServiceImplementation documentWzServiceImplementation) {
        this.documentWzServiceImplementation = documentWzServiceImplementation;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/saveDocument")
    public
    @ResponseBody
    ResponseEntity<?> createDocumentWz(@RequestBody DocumentWzDto documentWzDto) {
        return validator.checkAndSaveDocument(documentWzDto);
    }

    @RequestMapping(value = "/deleteDocument", method = RequestMethod.DELETE)
    public void deleteDocument(String numberWZ, String subPro) {
        DocumentWz documentWz = documentWzServiceImplementation.findByNumberWZAndSubProcess(numberWZ, subPro);
        documentWzServiceImplementation.removeDocumentWz(documentWz);

    }

    @RequestMapping(value = "/findByNumber", method = RequestMethod.POST)
    public
    DocumentWz findDocumentWz(@RequestBody FindByNumberWzDto findByNumberWzDto) {
        DocumentWz byNumberWz = documentWzServiceImplementation.findByNumberWZAndSubProcess(findByNumberWzDto.getNumberWZ(), findByNumberWzDto.getSubPro());
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
