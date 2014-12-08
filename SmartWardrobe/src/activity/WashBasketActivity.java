package activity;

import adapter.ListViewAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import by.idea.SmartWardrobe.R;
import data.Apparel;
import data.WardrobeManager;

import java.util.ArrayList;
import java.util.List;

public class WashBasketActivity extends Activity {
    ListView list1, list2;
    ListViewAdapter leftAdapter, rightAdapter;
    List<Apparel> apparelList = new ArrayList<Apparel>(), washList = new ArrayList<Apparel>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.to_wash_layout);

        apparelList = WardrobeManager.getInstance().getDirty();

        // Locate the ListView in listview_main.xml
        list1 = (ListView) findViewById(R.id.lvToWash);

        // Pass results to ListViewAdapter Class
        leftAdapter = new ListViewAdapter(this, R.layout.listview_item);

        // Binds the Adapter to the ListView
        list1.setAdapter(leftAdapter);
        list1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        // Capture ListView item click

        washList = WardrobeManager.getInstance().getNotInWash();

        // Locate the ListView in listview_main.xml
        list2 = (ListView) findViewById(R.id.lvFromWashWash);


        // Pass results to ListViewAdapter Class
        rightAdapter = new ListViewAdapter(this, R.layout.listview_item);

        // Binds the Adapter to the ListView
        list2.setAdapter(rightAdapter);
        list2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        // Capture ListView item click
    }
}