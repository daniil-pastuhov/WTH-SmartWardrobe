package main.wardrobe.repository;

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

/**
 * Created by TDiva on 18.10.2014.
 */
public class ApparelRepositoryCap implements ApparelRepository {

    private Map<Category, List<Apparel>> catalog = new HashMap<Category, List<Apparel>>();
    private List<Apparel> clothes = new ArrayList<Apparel>();

    @Override
    public List<Apparel> getAll() {
        return clothes;
    }


    public List<Apparel> getByCategory(Category category) {
        if (category == null || !catalog.containsKey(category))
            return Collections.EMPTY_LIST;
        return catalog.get(category);
    }

    @Override
    public List<Apparel> getByCategory(String category) {
        Category c = Category.getByType(category);
        return getByCategory(c);

    }

    @Override
    public List<Apparel> getByTags(List<String> tags) {
        List<Apparel> res = new ArrayList<Apparel>();
        Set<Long> tagsId = new HashSet<Long>();
        for (String s : tags) {
            tagsId.add(Tags.getTagId(s));
        }
        for (Apparel ap : clothes) {
            if (ap.getTags().containsAll(tagsId)) {
                res.add(ap);
            }
        }
        return res;
    }

    @Override
    public Apparel getById(Long id) {
        int index = Collections.binarySearch(clothes, new Apparel(id),
                new Comparator<Apparel>() {
                    @Override
                    public int compare(Apparel lhs, Apparel rhs) {
                        return (int) (rhs.getId() - lhs.getId());
                    }
                });
        return clothes.get(index);
    }

    @Override
    public void addApparel(Apparel ap) {
        if (catalog.containsKey(ap.getCategory())) {
            catalog.put(ap.getCategory(), new ArrayList<Apparel>());
        }
        catalog.get(ap.getCategory()).add(ap);
        clothes.add(ap);
    }

    @Override
    public void deleteApparel(Apparel ap) {
        if (clothes.contains(ap)) {
            clothes.remove(ap);
            catalog.get(ap.getCategory()).remove(ap);
        }
    }
}
