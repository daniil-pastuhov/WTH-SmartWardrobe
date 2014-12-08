package activity;

import adapter.ListViewAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import by.idea.SmartWardrobe.R;
import constants.Category;
import constants.Constants;
import constants.Style;
import data.Apparel;
import data.WardrobeManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class CatalogActivity extends Activity
{
    Button backButton;
    boolean isMainMenu = true;
    ListView lst;
    ArrayAdapter<String> adapterMain;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(by.idea.SmartWardrobe.R.layout.catalog);
        final Activity activity = this;
        lst = (ListView) findViewById(by.idea.SmartWardrobe.R.id.list_of_categories);
        adapterMain = new ArrayAdapter<>(this, R.layout.list_item_category, Category.getCategories());
        final ArrayList<Apparel> apparels = new ArrayList<>(5);
        String filePath = Constants.getHomeDirectory();
        apparels.add(new Apparel(filePath + "head.jpg", "Самое модная шапка", "45", "Зелёная", Category.HEADDRESS, new HashSet<>(new ArrayList<Style>() {{
            add(Style.DAILY);
        }}), new LinkedList<String>() {{
            add("Красивый");
        }}, 18, 25, "25-06-1994", "25-06-1994"));
        apparels.get(0).setWearProgress(20);
        apparels.add(new Apparel(filePath + "ex.jpg", "рубашка", "М", "Синяя", Category.SHIRT, new HashSet<>(new ArrayList<Style>() {{
            add(Style.DAILY);
        }}), new LinkedList<String>() {{
            add("Подарок");
        }}, 10, 20, "25-07-1994", "25-07-1994"));
        apparels.get(1).setWearProgress(99);
        apparels.add(new Apparel(filePath + "trousers.jpg", "Любимые брюки", "48-52", "Темно-синие", Category.TROUSERS, new HashSet<>(new ArrayList<Style>() {{
            add(Style.HOME);
        }}), new LinkedList<String>() {{
            add("Школьные ещё");
        }}, -1, 25, "25-06-1994", "25-06-1994"));

        backButton = (Button) findViewById(R.id.btnBack);
        backButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.backspace));
        lst.setAdapter(adapterMain);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (isMainMenu) {
                    showBackButton(true);
                    List<Apparel> tempList = WardrobeManager.getInstance().getByCategory(adapterView.getAdapter().getItem(i).toString());
                    ListViewAdapter adapter = new ListViewAdapter(activity, R.layout.listview_item);
                    adapter.addAll(apparels);
                    lst.setAdapter(adapter);
                    isMainMenu = false;
                } else {
                    Intent intent = new Intent(activity, DetailActivity.class);
                    intent.putExtra(Intent.EXTRA_TEXT, adapterView.getAdapter().getItem(i).toString());
                    startActivity(intent);
                }
            }
        });

    }

    private void showBackButton(boolean visibility) {
        if (visibility) backButton.setVisibility(View.VISIBLE);
        else backButton.setVisibility(View.GONE);
    }

    public void onBackClick(View v) {
        showBackButton(false);
        lst.setAdapter(adapterMain);
        isMainMenu = true;
    }

    @Override
    public void onBackPressed() {
        if (!isMainMenu) onBackClick(null);
        else super.onBackPressed();
    }
}