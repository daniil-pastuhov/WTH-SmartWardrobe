package by.SmartWardrobe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import by.idea.SmartWardrobe.R;
import main.constants.Category;
import main.wardrobe.entity.Apparel;
import main.wardrobe.service.WardrobeManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tab1Activity  extends Activity
{
    ListView list;
    ListViewAdapter listviewadapter;
    List<Apparel> apparelList = new ArrayList<Apparel>();
    Activity activity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_suit);
        activity = this;
        final EditText editText = (EditText) findViewById(R.id.findByTegs);
        //TODO delete this code below


        list = (ListView) findViewById(R.id.lvTodaySuit);

        // Pass results to ListViewAdapter Class
        listviewadapter = new ListViewAdapter(this, R.layout.listview_item,
                apparelList);
        listviewadapter.setResources(getResources());
        // Binds the Adapter to the ListView
        list.setAdapter(listviewadapter);
        list.setChoiceMode(ListView.CHOICE_MODE_NONE);
         final List<String> arr1 = WardrobeManager.getInstance().getTargetCategories();
        final Spinner sp1 = (Spinner) findViewById(R.id.spinner);
        final SpinnerAdapter spAdapter1 = new ArrayAdapter<String>(this, R.layout.list_item_category, arr1);
        sp1.setAdapter(spAdapter1);
        apparelList = new ArrayList<Apparel>(Arrays.asList(
                new Apparel(Integer.toString(R.drawable.head), Category.OTHER, 3, "Самое модная шапка", Arrays.asList("Bar")),
                new Apparel(Integer.toString(R.drawable.ex), Category.SWEATER, 3, "Самое модная в этом сезоне кофта", Arrays.asList("Bar")),
                new Apparel(Integer.toString(R.drawable.trousers), Category.TROUSERS, 3, "Самое модные брюки", Arrays.asList("Bar"))
        ));
        Button btnChange = (Button) findViewById(R.id.btnChange);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String a = arr1.get(sp1.getSelectedItemPosition());
//                String b = editText.getText().toString();
                //TODO apprel = getTodaySuit(s, k, g, d, t, k);

                listviewadapter = new ListViewAdapter(activity, R.layout.listview_item,
                        apparelList);
                listviewadapter.setResources(getResources());

                // Binds the Adapter to the ListView
                list.setAdapter(listviewadapter);
                list.setAdapter(listviewadapter);
            }
        });
    }
}