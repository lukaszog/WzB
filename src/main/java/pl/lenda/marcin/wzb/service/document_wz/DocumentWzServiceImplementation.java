package pl.lenda.marcin.wzb.service.document_wz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.wzb.entity.DocumentWz;
import pl.lenda.marcin.wzb.repository.DocumentWzRepository;

import java.util.List;

/**
 * Created by Promar on 28.10.2016.
 */
@Service
public class DocumentWzServiceImplementation implements DocumentWzService {

    private final DocumentWzRepository documentWzRepository;

    @Autowired
    public DocumentWzServiceImplementation(DocumentWzRepository documentWzRepository) {
        this.documentWzRepository = documentWzRepository;
    }

    @Override
    public DocumentWz createDocumentWz(DocumentWz documentWz) {
        documentWzRepository.save(documentWz);
        return documentWz;
    }

    @Override
    public DocumentWz findByNumberWz(Integer numberWZ) {
        return documentWzRepository.findByNumberWZ(numberWZ);
    }

    @Override
    public List<DocumentWz> findByNumberClient(Integer numberClient) {
        return documentWzRepository.findByClientNumber(numberClient);
    }

    @Override
    public List<DocumentWz> findByNameClient(String nameClient) {
        return documentWzRepository.findByClient(nameClient);
    }

    @Override
    public List<DocumentWz> findByNameTrader(String nameTrader) {
        return documentWzRepository.findByTraderName(nameTrader);
    }

    @Override
    public List<DocumentWz> showAllDocument() {
        return documentWzRepository.findAll();
    }

    @Override
    public void removeDocumentWz(DocumentWz documentWz) {
        documentWzRepository.delete(documentWz);
    }
}
