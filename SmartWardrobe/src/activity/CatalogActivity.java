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
    final MainMenuState mainMenuState = new MainMenuState();
    final CategoryMenuState categoryMenuState = new CategoryMenuState();
    final ArrayList<Apparel> apparels = new ArrayList<>(5);
    final String mainMenuStateStr = "MainMenuState";
    final String categoryStateStr = "categoryState";
    Button backButton;
    ListView lst;
    State state;
    ArrayAdapter<String> adapterMain;

    {
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
            add(Style.PICKNIK);
            add(Style.THEATRE);
        }}), new LinkedList<String>() {{
            add("Школьные ещё");
        }}, -1, 25, "25-06-1994", "25-07-1994"));

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(by.idea.SmartWardrobe.R.layout.catalog);
        if (savedInstanceState != null && !savedInstanceState.getBoolean(mainMenuStateStr, true)) {
            state = categoryMenuState;
            state.setCategory(savedInstanceState.getString(categoryStateStr));
        } else {
            state = mainMenuState;
        }
        lst = (ListView) findViewById(by.idea.SmartWardrobe.R.id.list_of_categories);
        adapterMain = new ArrayAdapter<>(this, R.layout.list_item_category, Category.getCategories());
        backButton = (Button) findViewById(R.id.btnBack);
        backButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.back));
        state.init();
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                state.onItemClicked(adapterView, i, l);
            }
        });

    }

    private void showBackButton(boolean visibility) {
        if (visibility) backButton.setVisibility(View.VISIBLE);
        else backButton.setVisibility(View.GONE);
    }

    public void onBackClick(View v) {
        state.prevState();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(mainMenuStateStr, state instanceof MainMenuState);
        outState.putString(categoryStateStr, state.getCategory());
    }

    @Override
    public void onBackPressed() {
        if (state != mainMenuState) onBackClick(null);
        else super.onBackPressed();
    }

    abstract private class State {
        protected String category;

        abstract public void onItemClicked(AdapterView adapterView, int i, long l);

        abstract public void nextState();

        abstract public void prevState();

        abstract public void init();

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }

    private class MainMenuState extends State {
        @Override
        public void onItemClicked(AdapterView adapterView, int i, long l) {
            nextState();
            state.setCategory(adapterView.getAdapter().getItem(i).toString());
            state.init();
        }

        @Override
        public void nextState() {
            state = categoryMenuState;
        }

        @Override
        public void prevState() {
            //do nothing
        }

        @Override
        public void init() {
            showBackButton(false);
            lst.setAdapter(adapterMain);
        }
    }

    private class CategoryMenuState extends State {
        @Override
        public void onItemClicked(AdapterView adapterView, int i, long l) {
            Intent intent = new Intent(CatalogActivity.this, DetailActivity.class);
            category = adapterView.getAdapter().getItem(i).toString();
            intent.putExtra(Intent.EXTRA_TEXT, category);
            CatalogActivity.this.startActivity(intent);
            nextState();
        }

        @Override
        public void nextState() {
            //go nowhere
        }

        @Override
        public void prevState() {
            state = mainMenuState;
            state.init();
        }

        @Override
        public void init() {
            showBackButton(true);
            List<Apparel> tempList = WardrobeManager.getInstance().getByCategory(category);
            ListViewAdapter adapter = new ListViewAdapter(CatalogActivity.this, R.layout.listview_item);
            adapter.addAll(apparels);
            lst.setAdapter(adapter);
        }
    }
}