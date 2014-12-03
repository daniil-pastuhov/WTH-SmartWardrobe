package activity;

import adapter.ListViewAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import by.idea.SmartWardrobe.R;
import constants.Category;
import data.Apparel;
import data.WardrobeManager;

import java.util.ArrayList;
import java.util.List;

public class Tab2Activity  extends Activity
{
    boolean isMainMenu = true;
    final String FILENAME = "backButton.icon";
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final Activity activity = this;
        setContentView(by.idea.SmartWardrobe.R.layout.catalog);

        final ArrayAdapter<String> adapterMain = new ArrayAdapter<String>(this, by.idea.SmartWardrobe.R.layout.list_item_category, Category.getCategories());
        final ListView lst = (ListView) findViewById(by.idea.SmartWardrobe.R.id.list_of_categories);

        lst.setAdapter(adapterMain);
        final List<Apparel> emptyList = new ArrayList<Apparel>();
        final Apparel backing = Apparel.getEmptyApparel(Integer.toString(R.drawable.backspace));
        emptyList.add(0, backing);
        final ListViewAdapter adapterEmpty = new ListViewAdapter(activity, R.layout.listview_item, emptyList);
        adapterEmpty.setResources(getResources());
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (isMainMenu) {
                    List<Apparel> tempList = WardrobeManager.getInstance().getByCategory(adapterView.getAdapter().getItem(i).toString());
                    if (tempList.size() == 0) {
                        lst.setAdapter(adapterEmpty);
                    }
                    else{
                        if (!tempList.get(0).equals(backing))
                            tempList.add(0, backing);
                        ListViewAdapter adapter = new ListViewAdapter(activity, R.layout.listview_item, tempList);
                        adapter.setResources(getResources());
                        lst.setAdapter(adapter);
                    }
                    isMainMenu = false;
                }
                else {
                    if (i == 0) {
                        lst.setAdapter(adapterMain);
                        isMainMenu = true;
                    }
                    else {
                        Intent intent = new Intent(activity, DetailActivity.class);
                        intent.putExtra(Intent.EXTRA_TEXT, adapterView.getAdapter().getItem(i).toString());
                        isMainMenu = true;
                        startActivity(intent);
                    }
                }
            }
        });
    }
}