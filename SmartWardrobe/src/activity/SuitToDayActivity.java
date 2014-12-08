package activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import by.idea.SmartWardrobe.R;
import constants.Category;
import constants.Constants;
import constants.Style;
import data.Apparel;
import data.WardrobeManager;

import java.util.*;

public class SuitToDayActivity extends Activity
{
    AdapterViewFlipper suitToDay[];
    Activity activity;
    ProgressBar progressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.today_suit);
        activity = this;
        final EditText editText = (EditText) findViewById(R.id.findByTegs);
        //TODO delete this code below

        int adapterViewFlipperIds[] = {R.id.avfHead, R.id.avfUnderBody, R.id.avfBodyOut, R.id.avfPants, R.id.avfBoots};
        suitToDay = new AdapterViewFlipper[adapterViewFlipperIds.length];
        for (int i = 0; i < adapterViewFlipperIds.length; ++i) {
            suitToDay[i] = (AdapterViewFlipper) findViewById(adapterViewFlipperIds[i]);
        }
        final List<String> arr1 = WardrobeManager.getInstance().getTargetCategories();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final Spinner sp1 = (Spinner) findViewById(R.id.spinner);
        final SpinnerAdapter spAdapter1 = new ArrayAdapter<String>(this, R.layout.list_item_category, arr1);
        sp1.setAdapter(spAdapter1);
        String filePath = Constants.getHomeDirectory();
        ArrayList<Apparel> apparelList = new ArrayList<Apparel>(Arrays.asList(new Apparel(filePath + "head.jpg", "Самое модная шапка", "45", "Зелёная", Category.HEADDRESS, new HashSet<>(new ArrayList<Style>() {{
            add(Style.DAILY);
        }}), new LinkedList<String>() {{
            add("Красивый");
        }}, 18, 25, "25-06-1994", "25-06-1994"), new Apparel(filePath + "ex.jpg", "рубашка", "М", "Синяя", Category.SHIRT, new HashSet<>(new ArrayList<Style>() {{
            add(Style.DAILY);
        }}), new LinkedList<String>() {{
            add("Подарок");
        }}, 10, 20, "25-07-1994", "25-07-1994"), new Apparel(filePath + "trousers.jpg", "Любимые брюки", "48-52", "Темно-синие", Category.TROUSERS, new HashSet<>(new ArrayList<Style>() {{
            add(Style.HOME);
        }}), new LinkedList<String>() {{
            add("Школьные ещё");
        }}, -1, 25, "25-06-1994", "25-06-1994")));
        View v = getLayoutInflater().inflate(R.layout.listview_item, suitToDay[0]);
        suitToDay[0].addView(v);

        Button btnChange = (Button) findViewById(R.id.btnChange);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String a = arr1.get(sp1.getSelectedItemPosition());
//                String b = editText.getText().toString();
                //TODO apprel = getTodaySuit(s, k, g, d, t, k);
            }
        });
    }
}