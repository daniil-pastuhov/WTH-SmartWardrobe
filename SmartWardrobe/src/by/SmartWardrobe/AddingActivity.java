package by.SmartWardrobe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import by.idea.SmartWardrobe.R;

public class AddingActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_activity);
        //TODO сделать добавление предмета в коллекцию + вызов камеры!!!

        startActivity(new Intent(Intent.ACTION_CAMERA_BUTTON));
    }
}