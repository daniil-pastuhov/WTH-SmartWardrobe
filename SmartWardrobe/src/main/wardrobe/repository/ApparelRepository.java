package main.wardrobe.repository;

import main.wardrobe.entity.Apparel;

import java.util.List;

/**
 * Created by TDiva on 18.10.2014.
 */
public interface ApparelRepository {

    List<Apparel> getAll();
    List<Apparel> getByCategory(String category);
    List<Apparel> getDirty();
    Apparel getById(Long id);
    void setWash(Apparel app, boolean flag);
    void addApparel(Apparel app);
    void deleteApparel(Apparel app);
}
