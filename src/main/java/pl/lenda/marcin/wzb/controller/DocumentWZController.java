package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.lenda.marcin.wzb.dto.DocumentWzDto;
import pl.lenda.marcin.wzb.dto.DocumentWzToDeleteDto;
import pl.lenda.marcin.wzb.dto.FindByNumberWzDto;
import pl.lenda.marcin.wzb.dto.FindClientNumber;
import pl.lenda.marcin.wzb.entity.DocumentWz;
import pl.lenda.marcin.wzb.service.convert_class.ConvertTo;
import pl.lenda.marcin.wzb.service.document_wz.DocumentWzServiceImplementation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Promar on 09.10.2016.
 */
@RestController
public class DocumentWZController {

    private final DocumentWzServiceImplementation documentWzServiceImplementation;

    private FindByNumberWzDto findByNumberWzDto;
    private DocumentWzDto documentWzDto;
    private DocumentWz documentWz;

    @Autowired
    private ConvertTo convertTo;
    private final Map<String, Object> response = new LinkedHashMap<>();

    @Autowired
    public DocumentWZController(DocumentWzServiceImplementation documentWzServiceImplementation) {
        this.documentWzServiceImplementation = documentWzServiceImplementation;
    }


    @RequestMapping(value = "/saveDocument")
    public
    @ResponseBody
    Map<String, Object> createDocumentWz(@Validated @RequestBody DocumentWzDto documentWzDto) {
        response.clear();
        if(documentWzServiceImplementation.
                findByNumberWZAndSubProcess(documentWzDto.getNumberWZ(), documentWzDto.getSubProcess())!= null){
            response.put("Error", "ExistsDocument");
            return response;
        }else{
            documentWzServiceImplementation.createDocumentWz(convertTo.convertDocumentToEntity(documentWzDto));
            response.put("Success", documentWzDto);
            return response;
        }

    }

    @RequestMapping(value = "/deleteDocument", method = RequestMethod.DELETE)
    public void deleteDocument(@RequestBody DocumentWzToDeleteDto documentWzToDeleteDto) {
        DocumentWz documentWz = documentWzServiceImplementation.findByNumberWZAndSubProcess(
                documentWzToDeleteDto.getNumberWZ(), documentWzToDeleteDto.getSubPro());
        documentWzServiceImplementation.removeDocumentWz(documentWz);
    }

    @RequestMapping(value = "/findByNumber", method = RequestMethod.POST)
    public
    @ResponseBody
    DocumentWzDto findDocumentWz(@RequestBody FindByNumberWzDto findByNumberWzDto) {
        DocumentWz byNumberWz = documentWzServiceImplementation.findByNumberWZAndSubProcess(
                findByNumberWzDto.getNumberWZ(), findByNumberWzDto.getSubPro());

        return convertTo.convertDocumentToDto(byNumberWz);
    }

    @RequestMapping(value = "/findByClient", method = RequestMethod.POST)
    public List<DocumentWzDto> findByClient(@RequestBody String client) {
        List<DocumentWz> listDocumentWZ;
        List<DocumentWzDto> listDocumentWzDto = new ArrayList<>();
        listDocumentWZ = documentWzServiceImplementation.findByNameClient(client);

        for (DocumentWz documentWz : listDocumentWZ) {
            listDocumentWzDto.add(convertTo.convertDocumentToDto(documentWz));
        }
        return listDocumentWzDto;
    }

    @RequestMapping(value = "/findByClientNumber", method = RequestMethod.POST)
    public List<DocumentWzDto> findByClientNumber(@RequestBody FindClientNumber findClientNumber) {
        List<DocumentWz> listDocumentWZ;
        List<DocumentWzDto> listDocumentWzDto = new ArrayList<>();
        listDocumentWZ = documentWzServiceImplementation.findByNumberClient(findClientNumber.getFindClientNumber());

        for (DocumentWz documentWz : listDocumentWZ) {
            listDocumentWzDto.add(convertTo.convertDocumentToDto(documentWz));
        }
        return listDocumentWzDto;
    }

    @RequestMapping(value = "/findByTraderName", method = RequestMethod.POST)
    public List<DocumentWzDto> findByTraderName(@RequestBody String traderName) {
        List<DocumentWz> listDocumentWZ;
        List<DocumentWzDto> listDocumentWzDto = new ArrayList<>();
        listDocumentWZ = documentWzServiceImplementation.findByNameTrader(traderName);

        for (DocumentWz documentWz : listDocumentWZ) {
            listDocumentWzDto.add(convertTo.convertDocumentToDto(documentWz));
        }
        return listDocumentWzDto;
    }

    @RequestMapping(value = "/showAllDocuments", method = RequestMethod.GET)
    public List<DocumentWzDto> findAll() {
        List<DocumentWzDto> listDocumentWzDto = new ArrayList<>();
        List<DocumentWz> listDocumentWZ = documentWzServiceImplementation.showAllDocument();

        for (DocumentWz documentWz : listDocumentWZ) {
            DocumentWzDto documentWzDto = new DocumentWzDto();
            listDocumentWzDto.add(convertTo.convertDocumentToDto(documentWz));
        }
        return listDocumentWzDto;
    }

    @RequestMapping(value = "/by_correct", method = RequestMethod.PATCH)
    public void correctBy(@RequestBody FindByNumberWzDto findByNumberWzDto){
        DocumentWz byNumberWz = documentWzServiceImplementation.findByNumberWZAndSubProcess(
                findByNumberWzDto.getNumberWZ(), findByNumberWzDto.getSubPro());
        byNumberWz.setBeCorrects(true);
        documentWzServiceImplementation.createDocumentWz(byNumberWz);
    }

    @RequestMapping(value = "/find_correct", method = RequestMethod.GET)
    public List<DocumentWz> findCorrectionDocument(){
        return documentWzServiceImplementation.listByCorrectionDocuments();
    }

    @RequestMapping(value = "/find_nameteam", method = RequestMethod.POST)
    public @ResponseBody List<DocumentWz> findByNameTeam(@RequestBody String nameTeam){
        return documentWzServiceImplementation.findByNameTeam(nameTeam);
    }
}