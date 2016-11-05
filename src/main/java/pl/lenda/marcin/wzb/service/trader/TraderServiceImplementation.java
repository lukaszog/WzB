package pl.lenda.marcin.wzb.service.trader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.wzb.entity.TraderAccount;
import pl.lenda.marcin.wzb.repository.TraderAccountRepository;

import java.util.List;

/**
 * Created by Promar on 03.11.2016.
 */
@Service
public class TraderServiceImplementation implements TraderService {

    @Autowired
    TraderAccountRepository traderAccountRepository;


    @Override
    public TraderAccount createTrader(TraderAccount traderAccount) {
        return traderAccountRepository.save(traderAccount);
    }

    @Override
    public void deleteTrader(TraderAccount traderAccount) {

    }

    @Override
    public TraderAccount findByTraderSurname(String surname) {

        return traderAccountRepository.findBySurnameIgnoreCase(surname);
    }

    @Override
    public List<TraderAccount> findAllTrader() {
        return traderAccountRepository.findAll();
    }
}
