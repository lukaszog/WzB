package pl.lenda.marcin.wzb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.lenda.marcin.wzb.entity.Reserved_Items;

import java.util.List;

/**
 * Created by Promar on 26.11.2016.
 */
@Repository
public interface Reserved_ItemsRepository extends MongoRepository<Reserved_Items, String> {

    List<Reserved_Items> findAll();
}
