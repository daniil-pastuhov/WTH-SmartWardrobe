package by.SmartWardrobe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import by.SmartWardrobe.wardrobe.Apparel;
import by.idea.SmartWardrobe.R;

import java.util.ArrayList;
import java.util.List;

public class Tab1Activity  extends Activity
{
    ListView list;
    ListViewAdapter listviewadapter;
    List<Apparel> apprelList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_suit);

        //TODO apprelList = getTodaySuit(s, k, g, b, n);
        //TODO delete this code below
        apprelList = new ArrayList<Apparel>();
        String[] name;
        String[] material;
        String[] washingDate;
        int[] photo;
        name = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

        material = new String[] { "Хлопок", "Шёлк", "Лён",
                "Шерсть", "Вискоза", "Хлопок", "Шёлк", "Лён",
                "Шерсть", "Вискоза"};

        washingDate = new String[] { "1,354,040,000", "1,210,193,422",
                "315,761,000", "237,641,326", "193,946,886", "182,912,000",
                "170,901,000", "152,518,015", "143,369,806", "127,360,000" };

        photo = new int[] { R.drawable.ex, R.drawable.ex,R.drawable.ex,R.drawable.ex,R.drawable.ex,R.drawable.ex,R.drawable.ex,R.drawable.ex,R.drawable.ex,R.drawable.ex };

        for (int i = 0; i < name.length; i++) {
            Apparel apparel = new Apparel(photo[i],
                    name[i], material[i], washingDate[i]);
            apprelList.add(apparel);
        }
        //
        list = (ListView) findViewById(R.id.lvTodaySuit);

        // Pass results to ListViewAdapter Class
        listviewadapter = new ListViewAdapter(this, R.layout.listview_item,
                apprelList);

        // Binds the Adapter to the ListView
        list.setAdapter(listviewadapter);
        list.setChoiceMode(ListView.CHOICE_MODE_NONE);
       //TODO вставить всевозможные варианты
        final ArrayList<String> arr1 = new ArrayList<String>() {{
            add("Вариант 1");
            add("Вариант 2");
            add("Вариант 3");
        }};
        final Spinner sp1 = (Spinner) findViewById(R.id.spinner);
        Spinner sp2 = (Spinner) findViewById(R.id.spinner2);
        Spinner sp3 = (Spinner) findViewById(R.id.spinner3);
        final SpinnerAdapter spAdapter1 = new ArrayAdapter<String>(this, R.layout.list_item_category, arr1);
        SpinnerAdapter spAdapter2 = new ArrayAdapter<String>(this, R.layout.list_item_category, arr1);
        SpinnerAdapter spAdapter3 = new ArrayAdapter<String>(this, R.layout.list_item_category, arr1);
        sp1.setAdapter(spAdapter1);
        sp2.setAdapter(spAdapter2);
        sp3.setAdapter(spAdapter3);
        Button btnChange = (Button) findViewById(R.id.btnChange);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = arr1.get(sp1.getSelectedItemPosition());
//                String b = arr2.get(sp2.getBaseline());
//                String c = arr3.get(sp3.getBaseline());
                //TODO apprel = getTodaySuit(s, k, g, d, t, k);
            }
        });
    }
}