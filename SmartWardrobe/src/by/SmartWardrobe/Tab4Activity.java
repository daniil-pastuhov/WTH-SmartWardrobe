package by.SmartWardrobe;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import by.SmartWardrobe.wardrobe.Apparel;
import by.idea.SmartWardrobe.R;

import java.util.ArrayList;
import java.util.List;

public class Tab4Activity extends Activity {
    ListView list;
    ListViewAdapter listviewadapter;
    List<Apparel> apprelList = new ArrayList<Apparel>();
    String[] name;
    String[] material;
    String[] washingDate;
    int[] photo;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //TODO получение списка вещей, что нужно постирать
        setContentView(R.layout.to_wash_layout);
        // Generate sample data into string arrays
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

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.lvToWash);

        // Pass results to ListViewAdapter Class
        listviewadapter = new ListViewAdapter(this, R.layout.listview_item,
                apprelList);

        // Binds the Adapter to the ListView
        list.setAdapter(listviewadapter);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        // Capture ListView item click
        list.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode,
                                                  int position, long id, boolean checked) {
                // Capture total checked items
                final int checkedCount = list.getCheckedItemCount();
                // Set the CAB title according to total checked items
                mode.setTitle(checkedCount + " Selected");
                // Calls toggleSelection method from ListViewAdapter Class
                listviewadapter.toggleSelection(position);
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.washing:
                        // Calls getSelectedIds method from ListViewAdapter Class
                        SparseBooleanArray selected = listviewadapter
                                .getSelectedIds();
                        // Captures all selected ids with a loop
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                Apparel selecteditem = listviewadapter
                                        .getItem(selected.keyAt(i));
                                // Remove selected items following the ids
                                //TODO пометить в шкафу, что его удалили!!!
                                listviewadapter.remove(selecteditem);
                            }
                        }
                        // Close CAB
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.activity_main, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                listviewadapter.removeSelection();
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }
        });
    }
}