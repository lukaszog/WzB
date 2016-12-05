package pl.lenda.marcin.wzb.service.reserved_items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.wzb.entity.Reserved_Items;
import pl.lenda.marcin.wzb.repository.Reserved_ItemsRepository;

import java.util.List;

/**
 * Created by Promar on 26.11.2016.
 */
@Service
public class Reserved_ItemsServiceImplementation implements Reserved_ItemsService {

    @Autowired
    Reserved_ItemsRepository reserved_itemsRepository;


    @Override
    public List<Reserved_Items> findAll() {
        return reserved_itemsRepository.findAll();
    }

    @Override
    public void saveItems(Reserved_Items reserved_items) {
        reserved_itemsRepository.save(reserved_items);
    }

    @Override
    public boolean updateItems(Reserved_Items reserved_items) {
        reserved_itemsRepository.save(reserved_items);
        return true;
    }

    @Override
    public Reserved_Items findItem(String id) {
        return reserved_itemsRepository.findById(id);
    }

    @Override
    public List<Reserved_Items> findByNameTeam(String nameTeam) {
        return reserved_itemsRepository.findByNameTeam(nameTeam);
    }

    @Override
    public void delete(Reserved_Items reserved_items) {
        reserved_itemsRepository.delete(reserved_items);
    }
}
