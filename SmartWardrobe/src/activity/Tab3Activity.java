package activity;

import adapter.ListViewAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import by.idea.SmartWardrobe.R;
import data.Apparel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Tab3Activity  extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_by_teg_layout);
        final EditText editText = (EditText) findViewById(R.id.etTags);
        Button button = (Button) findViewById(R.id.findByTegs);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText() != null && !editText.getText().toString().isEmpty()) {
                    StringTokenizer stringTokenizer = new StringTokenizer(editText.getText().toString(), "., ");
                    ArrayList<String> arrayList = new ArrayList<String>();
                    while (stringTokenizer.hasMoreTokens()) {
                        arrayList.add(stringTokenizer.nextToken());
                    }
                    List<Apparel> list = null;
                    //TODO List<Apparel> list  = getThingsByKeys(arrayList);

                    if (list != null && list.size() != 0) {
                        ListView listView = (ListView) findViewById(R.id.lvFounded);

                        ListViewAdapter listviewadapter = new ListViewAdapter(getApplicationContext(), R.layout.listview_item, list);
                        listviewadapter.setResources(getResources());
                        // Binds the Adapter to the ListView
                        listView.setAdapter(listviewadapter);
                        listView.setChoiceMode(ListView.CHOICE_MODE_NONE);
                    } else Toast.makeText(getApplicationContext(), "Ничего не найдено. Проверьте данные", Toast.LENGTH_LONG).show();
                } else Toast.makeText(getApplicationContext(), "Ничего не найдено. Проверьте данные", Toast.LENGTH_LONG).show();
            }

        });
    }
}