package pl.lenda.marcin.wzb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lenda.marcin.wzb.service.document_wz.DocumentWzServiceImplementation;
import pl.lenda.marcin.wzb.service.user.UserServiceImplementation;

/**
 * Created by Promar on 11.10.2016.
 */
@RestController
public class ControllerInfo {

    private final DocumentWzServiceImplementation documentWzServiceImplementation;
    private final UserServiceImplementation userServiceImplementation;

    @Autowired
    public ControllerInfo(DocumentWzServiceImplementation documentWzServiceImplementation, UserServiceImplementation userServiceImplementation) {
        this.documentWzServiceImplementation = documentWzServiceImplementation;
        this.userServiceImplementation = userServiceImplementation;
    }


    @RequestMapping(value = "/howManyTraders", method = RequestMethod.GET)
    public Integer howManyTraders() {
        return userServiceImplementation.findAllUser().size();
    }

    @RequestMapping(value = "/howManyDocument", method = RequestMethod.GET)
    public Integer howManyDocument() {
        return documentWzServiceImplementation.showAllDocument().size();
    }
}
