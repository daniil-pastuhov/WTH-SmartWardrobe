package by.SmartWardrobe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import by.idea.SmartWardrobe.R;
import main.constants.Category;
import main.wardrobe.entity.Apparel;
import main.wardrobe.service.WardrobeManager;

public class AddingActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_activity);
        //TODO сделать добавление предмета в коллекцию

        ImageView imV = (ImageView)findViewById(R.id.imageViewAdd);
        imV.setImageResource(by.idea.SmartWardrobe.R.drawable.ex);

        final Spinner sp  = (Spinner)findViewById(R.id.spinnerCatAdd);
        final SpinnerAdapter spAdapter = new ArrayAdapter<String>(this, by.idea.SmartWardrobe.R.layout.list_item_category, Category.getCategories());
        sp.setAdapter(spAdapter);

        final EditText edtName = (EditText)findViewById(R.id.editTextNameAdd);
        final EditText edtMinT = (EditText)findViewById(R.id.editTextMinTAdd);
        final EditText edtMaxT = (EditText)findViewById(R.id.editTextMaxTAdd);
        final EditText edtDescr = (EditText)findViewById(R.id.editTextDescrAdd);

        Button btn = (Button)findViewById(R.id.buttonToWardrobeAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cat = sp.getSelectedItem().toString();
                String name = edtName.getText().toString();
                String minT = edtMinT.getText().toString();
                String maxT = edtMaxT.getText().toString();
                String descr = edtDescr.getText().toString();

                Apparel newApparel = new Apparel(name, BitmapFactory.decodeResource(getResources(), R.drawable.ex), Category.getByType(cat), 3, descr);

            }
        });
    }
}