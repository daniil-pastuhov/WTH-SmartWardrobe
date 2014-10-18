package by.SmartWardrobe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import constants.Category;

import java.util.ArrayList;

public class Tab2Activity  extends Activity
{
    boolean isMainMenu = true;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final Activity activity = this;
        setContentView(by.idea.SmartWardrobe.R.layout.catalog);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, by.idea.SmartWardrobe.R.layout.list_item_category, Category.getCategories());
        final ListView lst = (ListView) findViewById(by.idea.SmartWardrobe.R.id.list_of_categories);
        lst.setAdapter(adapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<String> tempList = new ArrayList<String>() {{
                    add("Вещь 1");
                    add("Вещь 2");
                    add("Вещь 3");
                }};
                //TODO заменить tempList
                //TODO ArrayList tempList = SOMETHING.getList(adapter.getItem(i).toString()));
                tempList.add(0, "...");

                ArrayAdapter<String> adapterT = adapter;
                if (i==0 && !isMainMenu) {
                     adapterT = new ArrayAdapter<String>(activity, by.idea.SmartWardrobe.R.layout.list_item_category, Category.getCategories());
                    isMainMenu = true;
                }
                else {
                    if (isMainMenu) {
                        adapterT = new ArrayAdapter<String>(activity, by.idea.SmartWardrobe.R.layout.list_item_category, tempList);
                        isMainMenu = false;
                    }
                    else {
                        Intent intent = new Intent(activity, DetailActivity.class);
                        intent.putExtra(Intent.EXTRA_TEXT, adapterView.getAdapter().getItem(i).toString());
                        isMainMenu = true;
                        startActivity(intent);
                    }
                }
                lst.setAdapter(adapterT);
            }
        });
    }
}