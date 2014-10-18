package main.wardrobe.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import main.wardrobe.entity.Apparel;
import main.wardrobe.repository.ApparelRepository;
import main.wardrobe.repository.ApparelRepositoryCap;

public class WardrobeManager {
    public static final String REPO_NAME = "";

    private static WardrobeManager instance;

    public static void loadFromFile(ObjectInputStream is) {
        try {
            getInstance().repository = (ApparelRepository) is.readObject();
        } catch (ClassNotFoundException e) {
            getInstance().repository = new ApparelRepositoryCap();
            e.printStackTrace();
        } catch (IOException e) {
            getInstance().repository = new ApparelRepositoryCap();
            e.printStackTrace();
        }
    }

    public static void saveToFile(ObjectOutputStream os) {
        try {
            os.writeObject(getInstance().repository);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        return repository.getByCategory(category);
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

    public List<String> getTargetCategories() {
        return repository.getTargetCategories();
    }
}