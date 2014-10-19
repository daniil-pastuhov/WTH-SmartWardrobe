package by.SmartWardrobe;

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
import main.constants.Category;
import main.wardrobe.entity.Apparel;
import main.wardrobe.service.WardrobeManager;

import java.io.*;

public class MyActivity extends TabActivity {
    /**
     * Called when the activity is first created.
     */
    final String DIR_SD = ".Apparel";
    final String FILENAMETAG = "deficon";
    final String FILENAME = "basa";
    String experimentalPath = null;
    TextView tvWeather;
    static Bitmap icon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        try {
//            writeObject(new ObjectOutputStream(openFileOutput(FILENAMETAG, MODE_PRIVATE)), BitmapFactory.decodeResource(getResources(), R.drawable.ex));

            experimentalPath = getFilesDir() + "/" + FILENAMETAG;
            icon = BitmapFactory.decodeResource(getResources(), R.drawable.ex);
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


        WardrobeManager.getInstance().addApparel(new Apparel(Integer.toString(R.drawable.ex), Category.SHIRT, 5, "DG, коллекция 2050 года"));
        WardrobeManager.getInstance().addApparel(new Apparel(Integer.toString(R.drawable.ex), Category.SHIRT, 5, "Марвел студио"));
        WardrobeManager.getInstance().addApparel(new Apparel(Integer.toString(R.drawable.head), Category.DRESS, 1, "Платье моей мамы"));
        WardrobeManager.getInstance().addApparel(new Apparel(Integer.toString(R.drawable.trousers), Category.TROUSERS, 2, "Мои любимые штанны"));
        WardrobeManager.getInstance().addApparel(new Apparel(Integer.toString(R.drawable.ex), Category.SHIRT, 3, "..."));

        TabHost.TabSpec tab1 = tabHost.newTabSpec(getString(by.idea.SmartWardrobe.R.string.main_menu_auto_find));
        TabHost.TabSpec tab2 = tabHost.newTabSpec(getString(by.idea.SmartWardrobe.R.string.main_menu_catalog));
        TabHost.TabSpec tab3 = tabHost.newTabSpec(getString(by.idea.SmartWardrobe.R.string.main_menu_find_by_teg));
        TabHost.TabSpec tab4 = tabHost.newTabSpec(getString(by.idea.SmartWardrobe.R.string.main_menu_find_to_wash));
        TabHost.TabSpec tab5 = tabHost.newTabSpec(getString(by.idea.SmartWardrobe.R.string.main_menu_pack_to_trip));

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
        tab1.setIndicator(getString(by.idea.SmartWardrobe.R.string.main_menu_auto_find));
        tab1.setContent(new Intent(this, Tab1Activity.class));

        tab2.setIndicator(getString(by.idea.SmartWardrobe.R.string.main_menu_catalog));
        tab2.setContent(new Intent(this, Tab2Activity.class));

        tab3.setIndicator(getString(by.idea.SmartWardrobe.R.string.main_menu_find_by_teg));
        tab3.setContent(new Intent(this, Tab3Activity.class));

        tab4.setIndicator(getString(by.idea.SmartWardrobe.R.string.main_menu_find_to_wash));
        tab4.setContent(new Intent(this, Tab4Activity.class));

        tab5.setIndicator(getString(by.idea.SmartWardrobe.R.string.main_menu_pack_to_trip));
        tab5.setContent(new Intent(this, Tab5Activity.class));

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        tabHost.addTab(tab4);
        tabHost.addTab(tab5);

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
        experimentalPath = sdFile.getAbsolutePath();
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
