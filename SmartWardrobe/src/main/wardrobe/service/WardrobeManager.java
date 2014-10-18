package main.wardrobe.service;

import main.wardrobe.entity.Apparel;
import main.wardrobe.repository.ApparelRepository;
import main.wardrobe.repository.ApparelRepositoryCap;

import java.util.ArrayList;
import java.util.List;

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

    public List<Apparel> getAll() {
        return repository.getAll();
    }

    public List<Apparel> getByCategory(String category) {
        return new ArrayList<Apparel>(repository.getByCategory(category));
    }

    public Apparel getById(Long id) {
        return repository.getById(id);
    }

    public List<Apparel> getDirty() {
        return repository.getDirty();
    }

    public List<Apparel> getInWash() {
        return repository.getInWash();
    }

    public List<Apparel> getNotInWash() {
        return repository.getNotInWash();
    }

    public void addApparel(Apparel app) {
        repository.addApparel(app);
    }

    public void deleteApparel(Apparel app) {
        repository.deleteApparel(app);
    }
}