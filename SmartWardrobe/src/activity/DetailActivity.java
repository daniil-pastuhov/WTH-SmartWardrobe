package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import by.idea.SmartWardrobe.R;

public class DetailActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(by.idea.SmartWardrobe.R.layout.apparel_detail);
        TextView textView = (TextView) findViewById(by.idea.SmartWardrobe.R.id.tvApparelName);
        String nameOfApparel = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        textView.setText(nameOfApparel);
        ImageView imageView = (ImageView) findViewById(by.idea.SmartWardrobe.R.id.imageViewApparel);
        imageView.setImageResource(by.idea.SmartWardrobe.R.drawable.ex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.element_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
            //TODO сюда передать id текущего объекта
                int id = 0;
                deleteItemById(id);
                finish();
            break;
            case R.id.action_wash:
                //TODO пометить текущий грязным
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void deleteItemById(int id) {
        //TODO удаление элемента из шкафа
    }
}