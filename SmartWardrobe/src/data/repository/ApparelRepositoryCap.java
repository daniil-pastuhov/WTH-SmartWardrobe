package data.repository;

import constants.Category;
import data.Apparel;

import java.io.Serializable;
import java.util.*;

/**
 * Created by TDiva on 18.10.2014.
 */

public class ApparelRepositoryCap implements ApparelRepository, Serializable {

    public static final Integer WEAR_LEVEL = 5;

    private Map<Category, List<Apparel>> catalog = new HashMap<Category, List<Apparel>>();
    private List<Apparel> wash = new ArrayList<Apparel>();
    private List<Apparel> clothes = new ArrayList<Apparel>();

    private List<String> targets = new ArrayList<String>();

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
    public List<Apparel> getByTarget(String target) {
        List<Apparel> list = new ArrayList<Apparel>();
        for (Apparel ap : getNotInWash()) {
            if (ap.getTags().contains(target)) {
                list.add(ap);
            }
        }
        return list;
    }

    @Override
    public List<Apparel> getDirty() {
        List<Apparel> list = new ArrayList<Apparel>();
//        for (Apparel app : clothes) {
//            if (app.getWear() > WEAR_LEVEL) {
//                list.add(app);
//            }
//        }
//        Collections.sort(list, ne Comparator<Apparel>() {
//            @Override
//            public int compare(Apparel apparel, Apparel apparel2) {
//                return apparel2.getWear() - apparel.getWear();
//            }
//        });
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
    public List<Apparel> getTopByTarget(String target) {
        List<Apparel> list = new ArrayList<Apparel>();
        for (Apparel ap : getByTarget(target)) {
            if (Category.SWEATER.equals(ap.getCategory()) ||
                    Category.SHIRT.equals(ap.getCategory()) ||
                    Category.TSHIRTS.equals(ap.getCategory())) {
                list.add(ap);
            }
        }
        return list;
    }

    @Override
    public List<Apparel> getBottomByTarget(String target) {
        List<Apparel> list = new ArrayList<Apparel>();
        for (Apparel ap : getByTarget(target)) {
            if (Category.SHOES.equals(ap.getCategory())) {
                list.add(ap);
            }
        }
        return list;
    }

    @Override
    public List<Apparel> getMiddleByTarget(String target) {
        List<Apparel> list = new ArrayList<Apparel>();
        for (Apparel ap : getByTarget(target)) {
            if (Category.TROUSERS.equals(ap.getCategory())) {
                list.add(ap);
            }
        }
        return list;
    }

    @Override
    public List<Apparel> getAccessoriseByTarget(String target) {
        List<Apparel> list = new ArrayList<Apparel>();
        for (Apparel ap : getByTarget(target)) {
            if (Category.ACCESSORIES.equals(ap.getCategory())) {
                list.add(ap);
            }
        }
        return list;
    }

    @Override
    public Apparel getById(Integer id) {
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
//        clothes.add(app);
//        if (app.getInWash()) {
//            wash.add(app);
//        }
//        if (catalog.containsKey(app.getCategory()))
//            catalog.get(app.getCategory()).add(app);
//        else {
//            List<Apparel> list = ne ArrayList<Apparel>();
//            list.add(app);
//            catalog.put(app.getCategory(), list);
//        }

    }

    @Override
    public void deleteApparel(Apparel app) {
//        if (app.getInWash()) {
//            wash.remove(app);
//        }
//        catalog.get(app.getCategory()).remove(app);
//        clothes.remove(app);
    }

    @Override
    public void setWash(Apparel app, boolean flag) {
//        app.setInWash(flag);
//        if (flag) {
//            wash.add(app);
//        } else {
//            wash.remove(app);
//        }
    }

    @Override
    public List<String> getTargetCategories() {
        return targets;
    }

    @Override
    public void addTarget(String s) {
        targets.add(s);
    }

    @Override
    public void removeTarget(String s) {
        targets.remove(s);
    }
}
