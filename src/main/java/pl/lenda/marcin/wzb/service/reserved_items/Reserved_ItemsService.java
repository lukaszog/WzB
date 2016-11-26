package pl.lenda.marcin.wzb.service.reserved_items;

import pl.lenda.marcin.wzb.entity.Reserved_Items;

import java.util.List;

/**
 * Created by Promar on 26.11.2016.
 */
public interface Reserved_ItemsService {

    List<Reserved_Items> findAll();

    void saveItems(Reserved_Items reserved_items);
}
