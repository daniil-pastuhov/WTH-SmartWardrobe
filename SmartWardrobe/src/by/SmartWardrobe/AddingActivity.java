package by.SmartWardrobe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import by.idea.SmartWardrobe.R;
import main.constants.Category;
import main.wardrobe.entity.Apparel;
import main.wardrobe.service.WardrobeManager;

public class AddingActivity extends Activity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    //Bitmap imageBitmap;

    String mCurrentPhotoPath = "...";
    Set<String> targets;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_activity);
        //TODO сделать добавление предмета в коллекцию

        targets = new HashSet<>();

        final ImageView imV = (ImageView)findViewById(R.id.imageViewAdd);
        imV.setImageResource(R.drawable.question);

        final Button makePhoto = (Button)findViewById(R.id.btnPhotoAdd);
        makePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Ensure that there's a camera activity to handle the intent
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                        ex.printStackTrace();
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(photoFile));
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }

                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                File f = new File(mCurrentPhotoPath);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                AddingActivity.this.sendBroadcast(mediaScanIntent);

                imV.setImageURI(contentUri);
            }
        });

        final Spinner sp  = (Spinner)findViewById(R.id.spinnerCatAdd);
        final SpinnerAdapter spAdapter = new ArrayAdapter<String>(this, by.idea.SmartWardrobe.R.layout.list_item_category, Category.getCategories());
        sp.setAdapter(spAdapter);

        final EditText edtMinT = (EditText)findViewById(R.id.editTextMinTAdd);
        final EditText edtMaxT = (EditText)findViewById(R.id.editTextMaxTAdd);
        final EditText edtDescr = (EditText)findViewById(R.id.editTextDescrAdd);

        final CheckBox chB0 = (CheckBox)findViewById(R.id.checkBox0);
        final CheckBox chB1 = (CheckBox)findViewById(R.id.checkBox1);
        final CheckBox chB2 = (CheckBox)findViewById(R.id.checkBox2);

        Button btn = (Button)findViewById(R.id.buttonToWardrobeAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String cat = sp.getSelectedItem().toString();
                    String minT = edtMinT.getText().toString();
                    String maxT = edtMaxT.getText().toString();
                    String descr = edtDescr.getText().toString();

                    Apparel newApparel = new Apparel(mCurrentPhotoPath, Category.getByType(cat), 3, descr, targets);
                    WardrobeManager.getInstance().addApparel(newApparel);

                    sp.setSelection(0);
                    edtMinT.setText("");
                    edtMaxT.setText("");
                    edtDescr.setText("");
                    imV.setImageResource(R.drawable.question);
                    chB0.setChecked(false);
                    chB1.setChecked(false);
                    chB2.setChecked(false);
                    targets.clear();

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,   /*prefix */
                ".jpg",          /*suffix */
                storageDir       /*directory*/
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    public void onCheckBoxClick(View view) {

        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if(checked) {
            targets.add(((CheckBox) view).getText().toString());
        }

        /*// Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkBox0:
                if (checked)
                // Put some meat on the sandwich

                {}
                else
                // Remove the meat
                {}
                break;
            case R.id.checkBox1:
                if (checked)
                // Cheese me
                {}
                else
                // I'm lactose intolerant
                {}
                break;
            case R.id.checkBox2:
                if (checked)
                // Cheese me
                {}
                else
                // I'm lactose intolerant
                {}
                break;
            // TODO: Veggie sandwich
        }*/
    }
}

