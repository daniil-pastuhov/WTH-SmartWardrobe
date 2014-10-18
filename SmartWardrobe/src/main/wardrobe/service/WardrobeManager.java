package main.wardrobe.service;

import java.util.Arrays;
import java.util.List;

import main.constants.Category;
import main.wardrobe.entity.Apparel;
import main.wardrobe.repository.ApparelRepository;
import main.wardrobe.repository.ApparelRepositoryCap;

public class WardrobeManager {

    private static List<Apparel> testList = Arrays.asList(
            new Apparel("Ap1", null, Category.ACCESSORIES, 0, "desc"),
            new Apparel("Ap2", null, Category.DRESS, 4, "desc"),
            new Apparel("Ap3", null, Category.SHIRT, 1, "desc")
    );

    private static WardrobeManager instance;

    public static WardrobeManager getInstance() {
        if (instance == null) {
            instance = new WardrobeManager();
        }
        return instance;
    }

    private ApparelRepository repository;

    private WardrobeManager() {
        repository = new ApparelRepositoryCap();
        for (Apparel ap: testList) {
            repository.addApparel(ap);
        }
    }

    public List<Apparel> getByCategory(String category) {
        return repository.getByCategory(category);
    }

    public Apparel getById(Long id) {
        return repository.getById(id);
    }


    public List<Apparel> getDirty() {
        return repository.getDirty();
    }

    public void addApparel(Apparel app) {
        repository.addApparel(app);
    }

    public void deleteApparel(Apparel app) {
        repository.deleteApparel(app);
    }


}
