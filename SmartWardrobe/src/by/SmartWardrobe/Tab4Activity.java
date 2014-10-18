package by.SmartWardrobe;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import by.idea.SmartWardrobe.R;
import main.constants.Category;
import main.wardrobe.entity.Apparel;
import main.wardrobe.service.WardrobeManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tab4Activity extends Activity {
    ListView list;
    ListViewAdapter listviewadapter;
    List<Apparel> apparelList = new ArrayList<Apparel>();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.to_wash_layout);

//        apparelList = WardrobeManager.getInstance().getDirty();
        apparelList = new ArrayList<Apparel>(Arrays.asList(
                new Apparel("1", BitmapFactory.decodeResource(getResources(), R.drawable.ex), Category.SWEATER, 3, "desc"),
                new Apparel("2", BitmapFactory.decodeResource(getResources(), R.drawable.ex), Category.SWEATER, 3, "desc"),
                new Apparel("3", BitmapFactory.decodeResource(getResources(), R.drawable.ex), Category.SWEATER, 3, "desc"),
                new Apparel("4", BitmapFactory.decodeResource(getResources(), R.drawable.ex), Category.SWEATER, 3, "desc")
        ));

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.lvToWash);

        // Pass results to ListViewAdapter Class
        listviewadapter = new ListViewAdapter(this, R.layout.listview_item,
                apparelList);

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
                                selecteditem.setInWash(true);
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