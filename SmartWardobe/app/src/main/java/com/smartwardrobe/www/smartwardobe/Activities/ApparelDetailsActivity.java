package com.smartwardrobe.www.smartwardobe.Activities;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.smartwardrobe.www.smartwardobe.R;
import com.smartwardrobe.www.smartwardobe.database.Apparel;

import java.net.URI;

/**
 * Created by eugene on 18.10.14.
 */
public class ApparelDetailsActivity extends Activity {
    Apparel apparel;

    TextView name;
    TextView material;
    TextView minTemperature;
    TextView maxTemperature;
    TextView description;
    TextView countWash;
    ImageView photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apparel_details_layout);

        init();


    }

    public void init(){
        apparel = new Apparel();
        long id = getIntent().getLongExtra("apparelId", 0);
        apparel = Apparel.load(Apparel.class,getIntent().getLongExtra("apparelId", 0));//  getbyId(getIntent().getLongExtra("apparelId", 0));

        photo = (ImageView) findViewById(R.id.apparel_details_photo);
        name = (TextView) findViewById(R.id.apparel_details_name);

        name = (TextView) findViewById(R.id.apparel_details_name);
        material = (TextView) findViewById(R.id.apparel_details_material);
        countWash = (TextView) findViewById(R.id.apparel_details_count_wash);
        description = (TextView) findViewById(R.id.apparel_details_description);


        photo.setImageURI(Uri.parse(apparel.imagePath));
        name.setText(name.getText() + apparel.name);
        material.setText(material.getText()+apparel.material);
        countWash.setText(countWash.getText()+ String.valueOf(apparel.wear));
        description.setText(apparel.description);
    }
}
