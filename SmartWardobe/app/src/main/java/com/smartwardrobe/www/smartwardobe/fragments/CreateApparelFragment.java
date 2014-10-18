package com.smartwardrobe.www.smartwardobe.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.smartwardrobe.www.smartwardobe.R;
import com.smartwardrobe.www.smartwardobe.database.Apparel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by eugene on 18.10.14.
 */
public class CreateApparelFragment extends Fragment implements View.OnClickListener {

    EditText name;
    EditText material;
    EditText minTemperature;
    EditText maxTemperature;
    Spinner category;
    EditText description;
    ImageView photo;
    int cameraRotate = 0;
    public static final int CAMERA = 1;
    public static final int GALLERY = 2;
    Uri selectedImageUri ;
    String path ;
    Boolean photoSelected = false;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = (View) inflater.inflate(R.layout.new_apparel_fragment, null);
        setHasOptionsMenu(true);
        init(v);

        return v;
    }


    public void init(View view){

        name = (EditText)  view.findViewById(R.id.new_apparel_name);
        material = (EditText)  view.findViewById(R.id.new_apparel_material);
        minTemperature = (EditText)  view.findViewById(R.id.new_apparel_min_temperature);
        maxTemperature = (EditText)  view.findViewById(R.id.new_apparel_max_temperature);
        description = (EditText)  view.findViewById(R.id.new_apparel_description);
        photo = (ImageView) view.findViewById(R.id.new_apparel_photo);
        category = (Spinner) view.findViewById(R.id.new_apparel_category);

        photo.setOnClickListener(this);

    }

    private void showImageDialog() {

        final String[] items = new String[]{("Камера"), ("Галерея")};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(("Выберите изображение"));
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                if (item == 0) {

                    try {

                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, CAMERA);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dialog.cancel();
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, ("Завершите выбор")), GALLERY);
                }
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.new_apparel_photo:
                showImageDialog();
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getActionBar().setHomeButtonEnabled(true);
        menu.getItem(0).setVisible(false);
        inflater.inflate(R.menu.save_new_apparel, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.menu_save_apparel:
                Apparel apparel = new Apparel();
                apparel.name= name.getText().toString();
                apparel.material = material.getText().toString();

                apparel.minWarm = Integer.parseInt(minTemperature.getText().toString());
                apparel.maxWarm = Integer.parseInt(maxTemperature.getText().toString());
                apparel.description = description.getText().toString();
                apparel.imagePath = path;
                apparel.save();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                ApparelListFragment newApparelFragment = new ApparelListFragment();
                fragmentTransaction.replace(R.id.container, newApparelFragment, "listapparel").commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }







    public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if(resultCode == -1){

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
                String  currentTimeStamp = dateFormat.format(new Date());
            switch (requestCode) {
                case CAMERA:
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    FileOutputStream out = null;
                    try {
                        out = new FileOutputStream(getActivity().getExternalCacheDir().toString() + "/"+currentTimeStamp+".JPG");
                        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();

                        selectedImageUri = Uri.fromFile(new File(getActivity().getExternalCacheDir().toString() + "/"+currentTimeStamp+".JPG"));
                        path = getActivity().getExternalCacheDir().toString() + "/"+currentTimeStamp+".JPG";
                        cameraRotate = (getCameraPhotoOrientation(getActivity().getApplicationContext(), selectedImageUri, path));
                        imageBitmap = getScaledImage(getActivity().getApplicationContext(), selectedImageUri);

                        out = new FileOutputStream(getActivity().getExternalCacheDir().toString() + "/"+currentTimeStamp+".JPG");
                        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                        out.flush();
                        out.close();
                        photo.setImageBitmap(imageBitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case GALLERY:
                    try {
                        selectedImageUri = data.getData();
                        path = getRealPathFromURI(getActivity().getApplicationContext(), data.getData());
                        cameraRotate = (getCameraPhotoOrientation(getActivity().getApplicationContext(), selectedImageUri, path));

                        Bitmap image = getScaledImage(getActivity().getApplicationContext(), selectedImageUri);
                        FileOutputStream outGallery = null;
                        outGallery = new FileOutputStream(getActivity().getExternalCacheDir().toString() + "/"+currentTimeStamp+".JPG");
                        image.compress(Bitmap.CompressFormat.JPEG, 90, outGallery);
                        outGallery.flush();
                        outGallery.close();
                        photo.setImageBitmap(image);
                        selectedImageUri = Uri.fromFile(new File(getActivity().getExternalCacheDir().toString() + "/"+currentTimeStamp+".JPG"));
                        path = getActivity().getExternalCacheDir().toString() + "/"+currentTimeStamp+".JPG";
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;


            }



            photoSelected = true;

        } else if(resultCode == 0){
//            addPhoto.setBackgroundColor(getResources().getColor(R.color.register_step3_nophoto));
            if(requestCode == CAMERA || requestCode == GALLERY){
                if(path == null || path.isEmpty()) {
                    photoSelected = false;
                }else{
                    photoSelected = true;
                }
            }

        }
    }

    public static int getCameraPhotoOrientation(Context context, Uri imageUri, String imagePath) {
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(imageUri, null);
            File imageFile = new File(imagePath);
            ExifInterface exif = new ExifInterface(
                    imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        String result;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor == null) {
            result = contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public Bitmap getScaledImage(Context context, Uri uri) {
        try {
            InputStream inStr = context.getContentResolver().openInputStream(uri);
            Log.i("TAG", "File Size: " + inStr.available());
            float scaleHeight = getResources().getDisplayMetrics().density;
            float scaleWidth = getResources().getDisplayMetrics().density;
            int height = (int) (150 * scaleHeight + 0.5f);
            int width = (int) (150 * scaleWidth + 0.5f);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inStr, null, options);
            inStr.close();
            options.inSampleSize = calculateInSampleSize(options, width, height);
            options.inJustDecodeBounds = false;
            inStr = context.getContentResolver().openInputStream(uri);
            Bitmap selectImg = BitmapFactory.decodeStream(inStr, null, options);
            Matrix matrix = new Matrix();
            matrix.postRotate(cameraRotate);
            Bitmap resizedBitmap = Bitmap.createBitmap(selectImg, 0, 0, options.outWidth, options.outHeight, matrix, true);
            inStr.close();
            return resizedBitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }





}
