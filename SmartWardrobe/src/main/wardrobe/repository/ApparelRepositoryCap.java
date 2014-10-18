package main.wardrobe.repository;

import main.constants.Category;
import main.wardrobe.entity.Apparel;

import java.util.*;

/**
 * Created by TDiva on 18.10.2014.
 */
public class ApparelRepositoryCap implements ApparelRepository {

    public static final Integer WEAR_LEVEL = 5;

    private Map<Category, List<Apparel>> catalog = new HashMap<Category, List<Apparel>>();
    private List<Apparel> wash = new ArrayList<Apparel>();
    private List<Apparel> clothes = new ArrayList<Apparel>();

    @Override
    public List<Apparel> getAll() {
        return clothes;
    }

    @Override
    public List<Apparel> getByCategory(String category) {
        Category c = Category.getByType(category);
        if (c == null || !catalog.containsKey(c))
            return new ArrayList<Apparel>();
        return catalog.get(c);
    }

    @Override
    public List<Apparel> getDirty() {
        List<Apparel> list = new ArrayList<Apparel>();
        for (Apparel app : clothes) {
            if (app.getWear() > WEAR_LEVEL) {
                list.add(app);
            }
        }
        Collections.sort(list, new Comparator<Apparel>() {
            @Override
            public int compare(Apparel apparel, Apparel apparel2) {
                return apparel2.getWear() - apparel.getWear();
            }
        });
        return list;
    }

    @Override
    public List<Apparel> getNotInWash() {
        List ret = (new ArrayList<Apparel>(clothes));
        ret.removeAll(wash);
        return ret;
    }

    @Override
    public List<Apparel> getInWash() {
        return wash;
    }

    @Override
    public Apparel getById(Long id) {
        int index = Collections.binarySearch(clothes, new Apparel(id), new Comparator<Apparel>() {
            @Override
            public int compare(Apparel apparel, Apparel apparel2) {
                return (int) (apparel2.getId() - apparel.getId());
            }
        });
        return clothes.get(index);
    }

    @Override
    public void addApparel(Apparel app) {
        clothes.add(app);
        if (app.getInWash()) {
            wash.add(app);
        }
        if (catalog.containsKey(app.getCategory()))
            catalog.get(app.getCategory()).add(app);
        else {
            List<Apparel> list = new ArrayList<Apparel>();
            list.add(app);
            catalog.put(app.getCategory(), list);
        }

    }

    @Override
    public void deleteApparel(Apparel app) {
        if (app.getInWash()) {
            wash.remove(app);
        }
        catalog.get(app.getCategory()).remove(app);
        clothes.remove(app);
    }

    @Override
    public void setWash(Apparel app, boolean flag) {
        app.setInWash(flag);
        if (flag) {
            wash.add(app);
        } else {
            wash.remove(app);
        }
    }

    @Override
    public List<String> getTargetCategories() {
        //TODO FIX ME!!
        ArrayList<String> ret = new ArrayList<String>() {{
            add("Бал");
            add("Спорт");
            add("Дела");
        }};
        return ret;
    }
}
