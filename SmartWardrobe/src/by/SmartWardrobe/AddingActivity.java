package by.SmartWardrobe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import by.idea.SmartWardrobe.R;
import main.constants.Category;

public class AddingActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_activity);
        //TODO сделать добавление предмета в коллекцию + вызов камеры!!!

        //startActivity(new Intent(Intent.ACTION_CAMERA_BUTTON));


    }
}