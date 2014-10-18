package main.wardrobe.repository;

import java.util.List;

import main.constants.Category;
import main.wardrobe.entity.Apparel;

/**
 * Created by TDiva on 18.10.2014.
 */
public interface ApparelRepository {

    List<Apparel> getAll();
    List<Apparel> getByCategory(String category);
    List<Apparel> getDirty();
    Apparel getById(Long id);

    void addApparel(Apparel app);
    void deleteApparel(Apparel app);
    void setWash(Apparel app, boolean flag);
}
