package pl.lenda.marcin.wzb.service.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.wzb.entity.HistoryDeleteDocumentWz;
import pl.lenda.marcin.wzb.repository.HistoryDeleteDocumentWzRepository;

import java.util.List;

/**
 * Created by Promar on 22.11.2016.
 */
@Service
public class HistoryServiceImplementation implements HistoryService {

    @Autowired
    private HistoryDeleteDocumentWzRepository historyDeleteDocumentWzRepository;



    @Override
    public List<HistoryDeleteDocumentWz> showAllDeleteDocument() {
        return historyDeleteDocumentWzRepository.findAll();
    }
}
