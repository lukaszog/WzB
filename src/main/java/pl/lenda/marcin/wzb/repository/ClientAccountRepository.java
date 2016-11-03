package pl.lenda.marcin.wzb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.lenda.marcin.wzb.entity.ClientAccount;

/**
 * Created by Promar on 03.11.2016.
 */
public interface ClientAccountRepository extends MongoRepository<ClientAccount, String> {

    ClientAccount findByName(String name);

    ClientAccount findByNumberClient(String numberClient);

}
