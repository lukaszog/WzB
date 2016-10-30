package pl.lenda.marcin.wzb.validator;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import pl.lenda.marcin.wzb.entity.DocumentWz;
import pl.lenda.marcin.wzb.service.document_wz.DocumentWzService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Promar on 22.10.2016.
 */
@Component
public class Validator {

    @Autowired
    private DocumentWzService documentWzService;

    private List<String> response = new ArrayList<>();
    private JSONObject errors = new JSONObject();
    private JSONObject success = new JSONObject();


    public ResponseEntity<?> checkAndSaveDocument(DocumentWz documentWz) {

        String lengthNumberDocument = String.valueOf(documentWz.getNumberWZ());
        String lengthSubProDocument = String.valueOf(documentWz.getSubProcess());

        if (documentWzService.findByNumberWz(documentWz.getNumberWZ()) != null &&
                documentWzService.findByNumberWz(documentWz.getNumberWZ()).getSubProcess().equals(documentWz.getSubProcess())){

            response.clear();
            response.add("Podana WZ istnieje w bazie danych.");
            errors.put("Error", response);
            return new ResponseEntity<Object>(errors, HttpStatus.CONFLICT);
        }
        if (lengthNumberDocument.length() > 8) {
            response.clear();
            response.add("Podany numer WZ jest za długi.");
            errors.put("Error", response);
            return new ResponseEntity<Object>(errors, HttpStatus.CONFLICT);
        }
        if (lengthNumberDocument.length() < 8) {
            response.clear();
            response.add("Podany numer WZ jest za krótki.");
            errors.put("Error", response);
            return new ResponseEntity<Object>(errors, HttpStatus.CONFLICT);
        }
        if (lengthSubProDocument.length() > 2 || lengthSubProDocument.length() <= 0) {
            response.clear();
            response.add("Błędny podproces.");
            errors.put("Error", response);
            return new ResponseEntity<Object>(errors, HttpStatus.CONFLICT);
        }

        documentWzService.createDocumentWz(documentWz);
        response.clear();
        response.add("Dodałeś nowy dokument WZ do bazy danych!");
        success.put("Succes", response);
        return new ResponseEntity<Object>(success, HttpStatus.CREATED);
    }


}
