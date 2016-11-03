package pl.lenda.marcin.wzb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.lenda.marcin.wzb.entity.TraderAccount;

/**
 * Created by Promar on 03.11.2016.
 */
public interface TraderAccountRepository extends MongoRepository<TraderAccount, String> {

    TraderAccount findBySurname(String surname);
}
