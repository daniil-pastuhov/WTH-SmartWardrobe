package main.wardrobe.service;

import java.util.Arrays;
import java.util.List;

import main.constants.Category;
import main.wardrobe.entity.Apparel;
import main.wardrobe.repository.ApparelRepository;
import main.wardrobe.repository.ApparelRepositoryCap;

public class WardrobeManager {
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
    }

    List<Apparel> getAll() {
        return repository.getAll();
    }

    List<Apparel> getByCategory(String category) {
        return repository.getByCategory(category);
    }

    List<Apparel> getByTags(List<String> tags) {
        return repository.getByTags(tags);
    }

    Apparel getById(Long id) {
        return repository.getById(id);
    }

    private static List<Apparel> testList = Arrays.asList(
            new Apparel("Ap1", null, Category.ACCESSORIES, 0, "desc"),
            new Apparel("Ap2", null, Category.DRESS, 4, "desc"),
            new Apparel("Ap3", null, Category.SHIRT, 1, "desc")
    );

    public List<Apparel> getDirty() {
        // FIXME: return
        return testList;
    }

    void addApparel(Apparel app) {
        repository.addApparel(app);
    }

    void deleteApparel(Apparel app) {
        repository.deleteApparel(app);
    }


}
