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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import by.idea.SmartWardrobe.R;
import main.constants.Category;
import main.wardrobe.entity.Apparel;
import main.wardrobe.service.WardrobeManager;

public class AddingActivity extends Activity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    //Bitmap imageBitmap;

    String mCurrentPhotoPath = "...";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adding_activity);
        //TODO сделать добавление предмета в коллекцию

        Button makePhoto = (Button)findViewById(R.id.btnPhotoAdd);
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
            }
        });

        ImageView imV = (ImageView)findViewById(R.id.imageViewAdd);
        imV.setImageResource(by.idea.SmartWardrobe.R.drawable.ex);

        final Spinner sp  = (Spinner)findViewById(R.id.spinnerCatAdd);
        final SpinnerAdapter spAdapter = new ArrayAdapter<String>(this, by.idea.SmartWardrobe.R.layout.list_item_category, Category.getCategories());
        sp.setAdapter(spAdapter);

       // final EditText edtName = (EditText)findViewById(R.id.editTextNameAdd);
        final EditText edtMinT = (EditText)findViewById(R.id.editTextMinTAdd);
        final EditText edtMaxT = (EditText)findViewById(R.id.editTextMaxTAdd);
        final EditText edtDescr = (EditText)findViewById(R.id.editTextDescrAdd);

        Button btn = (Button)findViewById(R.id.buttonToWardrobeAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cat = sp.getSelectedItem().toString();
               // String name = edtName.getText().toString();
                String minT = edtMinT.getText().toString();
                String maxT = edtMaxT.getText().toString();
                String descr = edtDescr.getText().toString();

                Apparel newApparel = new Apparel(mCurrentPhotoPath, Category.getByType(cat), 3, descr);

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
}