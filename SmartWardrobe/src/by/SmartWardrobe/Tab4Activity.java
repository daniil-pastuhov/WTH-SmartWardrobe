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
    PairListViewAdapter leftAdapter, rightAdapter;
    List<Apparel> apparelList = new ArrayList<Apparel>(), washList = new ArrayList<Apparel>();

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
        leftAdapter = new PairListViewAdapter(this, R.layout.listview_item,
                apparelList);

        // Binds the Adapter to the ListView
        list.setAdapter(leftAdapter);
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
                leftAdapter.toggleSelection(position);
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.washing:
                        // Calls getSelectedIds method from ListViewAdapter Class
                        SparseBooleanArray selected = leftAdapter
                                .getSelectedIds();
                        // Captures all selected ids with a loop
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                Apparel selecteditem = leftAdapter
                                        .getItem(selected.keyAt(i));
                                WardrobeManager.getInstance().setInWash(selecteditem, true);
                                leftAdapter.remove(selecteditem);
                                rightAdapter.add(selecteditem);
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
                leftAdapter.removeSelection();
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }
        });

        washList = new ArrayList<Apparel>(Arrays.asList(
                new Apparel("1", BitmapFactory.decodeResource(getResources(), R.drawable.ex), Category.SWEATER, 3, "desc"),
                new Apparel("2", BitmapFactory.decodeResource(getResources(), R.drawable.ex), Category.SWEATER, 3, "desc"),
                new Apparel("3", BitmapFactory.decodeResource(getResources(), R.drawable.ex), Category.SWEATER, 3, "desc"),
                new Apparel("4", BitmapFactory.decodeResource(getResources(), R.drawable.ex), Category.SWEATER, 3, "desc")
        ));

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.lvFromWashWash);


        // Pass results to ListViewAdapter Class
        rightAdapter = new PairListViewAdapter(this, R.layout.listview_item,
                washList);

        // Binds the Adapter to the ListView
        list.setAdapter(rightAdapter);
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
                rightAdapter.toggleSelection(position);
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.washing:
                        // Calls getSelectedIds method from ListViewAdapter Class
                        SparseBooleanArray selected = rightAdapter
                                .getSelectedIds();
                        // Captures all selected ids with a loop
                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                Apparel selecteditem = rightAdapter
                                        .getItem(selected.keyAt(i));
                                WardrobeManager.getInstance().setInWash(selecteditem, false);
                                rightAdapter.remove(selecteditem);
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
                rightAdapter.removeSelection();
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }
        });
    }
}