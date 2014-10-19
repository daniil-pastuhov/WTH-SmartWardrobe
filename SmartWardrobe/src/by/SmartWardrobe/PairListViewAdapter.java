package by.SmartWardrobe;

import android.content.Context;

import java.util.List;

import main.wardrobe.entity.Apparel;

/**
 * Created by TDiva on 19.10.2014.
 */
public class PairListViewAdapter extends ListViewAdapter {
    public PairListViewAdapter(Context context, int resourceId, List<Apparel> apparelList) {
        super(context, resourceId, apparelList);
    }

    @Override
    public void add(Apparel object) {
        apparelList.add(object);
        notifyDataSetChanged();
    }
}
