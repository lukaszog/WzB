package pl.lenda.marcin.wzb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.lenda.marcin.wzb.entity.DocumentWz;

import java.util.List;

/**
 * Created by Promar on 09.10.2016.
 */
@Repository
public interface DocumentWzRepository extends MongoRepository<DocumentWz, String> {

    DocumentWz findByNumberWZ(Integer numberWZ);

    List<DocumentWz> findByClient(String client);

    List<DocumentWz> findByClientNumber(Integer clientNumber);

    List<DocumentWz> findByTraderName(String traderName);

}
