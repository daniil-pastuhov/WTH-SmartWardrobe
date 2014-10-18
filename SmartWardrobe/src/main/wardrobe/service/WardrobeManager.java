package main.wardrobe.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.constants.Category;
import main.constants.Tags;
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

    void addApparel(Apparel app) {
        repository.addApparel(app);
    }
    void deleteApparel(Apparel app) {
        repository.deleteApparel(app);
    }


}
