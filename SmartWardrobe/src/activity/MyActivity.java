package activity;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;
import by.idea.SmartWardrobe.R;
import constants.Category;
import constants.Constants;
import constants.Style;
import data.Apparel;
import data.WardrobeManager;
import tasks.FetchWeatherTask;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class MyActivity extends TabActivity {
    /**
     * Called when the activity is first created.
     */
    final String DIR_SD = ".Apparel";
    final String FILENAMETAG = "deficon";
    final String FILENAME = "basa";
    TextView tvWeather;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        try {
//            writeObject(new ObjectOutputStream(openFileOutput(FILENAMETAG, MODE_PRIVATE)), BitmapFactory.decodeResource(getResources(), R.drawable.ex));
//        }
//
//        catch (Exception e) {
//            e.printStackTrace();
//        };
//        writeFileSD();
        setContentView(by.idea.SmartWardrobe.R.layout.main);
        tvWeather = (TextView) findViewById(by.idea.SmartWardrobe.R.id.tvWeather);
        FetchWeatherTask weatherTask = new FetchWeatherTask(this, tvWeather);
        weatherTask.execute("Minsk", "metric");
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
//        try {
//            ObjectInputStream is = new ObjectInputStream(openFileInput(FILENAME));
//            WardrobeManager.loadFromFile(is);
//            is.close();
//        } catch (IOException e) {
//
        //TODO delete inicializ
        String [] strings1 = {"В унтверситет", "В школу"};
        String [] strings2 = {"Спорт", "В бар"};
        Apparel[] apparels = new Apparel[3];
        String filePath = Constants.getHomeDirectory();
        apparels[0] = new Apparel(filePath + "head.jpg", "Самое модная шапка", "45", "Зелёная", Category.HEADDRESS, new HashSet<>(new ArrayList<Style>() {{
            add(Style.DAILY);
        }}), new LinkedList<String>() {{
            add("Красивый");
        }}, 18, 25, "25-06-1994", "25-06-1994");
        apparels[0].setWearProgress(20);

        apparels[1] = new Apparel(filePath + "ex.jpg", "рубашка", "М", "Синяя", Category.SHIRT, new HashSet<>(new ArrayList<Style>() {{
            add(Style.DAILY);
        }}), new LinkedList<String>() {{
            add("Подарок");
        }}, 10, 20, "25-07-1994", "25-07-1994");
        apparels[1].setWearProgress(99);
        apparels[2] = new Apparel(filePath + "trousers.jpg", "Любимые брюки", "48-52", "Темно-синие", Category.TROUSERS, new HashSet<>(new ArrayList<Style>() {{
            add(Style.HOME);
        }}), new LinkedList<String>() {{
            add("Школьные ещё");
        }}, -1, 25, "25-06-1994", "25-06-1994");
        for (int i = 0; i < 3; i++) {
            WardrobeManager.getInstance().addApparel(apparels[i]);
        }

        TabHost.TabSpec tab1 = tabHost.newTabSpec(getString(R.string.main_menu_auto_find));
        TabHost.TabSpec tab2 = tabHost.newTabSpec(getString(R.string.main_menu_catalog));
        TabHost.TabSpec tab3 = tabHost.newTabSpec(getString(R.string.main_menu_find_by_teg));
        TabHost.TabSpec tab4 = tabHost.newTabSpec(getString(R.string.main_menu_find_to_wash));
        TabHost.TabSpec tab5 = tabHost.newTabSpec(getString(R.string.main_menu_pack_to_trip));

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
        tab1.setIndicator(getString(by.idea.SmartWardrobe.R.string.main_menu_auto_find));
        tab1.setContent(new Intent(this, SuitToDayActivity.class));

        tab2.setIndicator(getString(by.idea.SmartWardrobe.R.string.main_menu_catalog));
        tab2.setContent(new Intent(this, CatalogActivity.class));

        tab3.setIndicator(getString(by.idea.SmartWardrobe.R.string.main_menu_find_by_teg));
        tab3.setContent(new Intent(this, SearchActivity.class));

        tab4.setIndicator(getString(by.idea.SmartWardrobe.R.string.main_menu_find_to_wash));
        tab4.setContent(new Intent(this, WashBasketActivity.class));

        tab5.setIndicator(getString(by.idea.SmartWardrobe.R.string.main_menu_pack_to_trip));
        tab5.setContent(new Intent(this, ToTripActivity.class));
        startActivity(new Intent(this, CatalogActivity.class));
        //TODO
//        tabHost.addTab(tab1);
//        tabHost.addTab(tab2);
//        tabHost.addTab(tab3);
//        tabHost.addTab(tab4);
//        tabHost.addTab(tab5);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                onAddClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onAddClick() {
        Intent intent = new Intent(getApplicationContext(), AddingActivity.class);
        startActivity(intent);
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
////        try {
////            ObjectOutputStream os = new ObjectOutputStream(openFileOutput(FILENAME, MODE_PRIVATE));
////            WardrobeManager.saveToFile(os);
////            os.close();
////        } catch (IOException e) {
////
////        }
//    }
    void writeFileSD() {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return;
        }
        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        // создаем каталог
        sdPath.mkdirs();
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, FILENAMETAG);
        try {
            // открываем поток для записи
            ObjectOutputStream bw = new ObjectOutputStream(new FileOutputStream(sdFile));
            // пишем данные
            bw.writeObject(BitmapFactory.decodeResource(getResources(), R.drawable.ex));
            // закрываем поток
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected class BitmapDataObject implements Serializable {
        private static final long serialVersionUID = 111696345129311948L;
        public byte[] imageByteArray;
    }

    /** Included for serialization - write this layer to the output stream. */
    private void writeObject(ObjectOutputStream out, Bitmap bm) throws IOException{
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        BitmapDataObject bitmapDataObject = new BitmapDataObject();
        bitmapDataObject.imageByteArray = stream.toByteArray();

        out.writeObject(bitmapDataObject);
        out.close();
    }

    /** Included for serialization - read this object from the supplied input stream. */
    private Bitmap readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        BitmapDataObject bitmapDataObject = (BitmapDataObject)in.readObject();
        Bitmap image = BitmapFactory.decodeByteArray(bitmapDataObject.imageByteArray, 0, bitmapDataObject.imageByteArray.length);
        return image;
    }
}
