package constants;

import android.os.Environment;

public class Constants {
    public static String styleSeparator = ";";
    public static String tagsSeparator = ";";
    public static String homeDirectory = "/.SmartWardrobeImage/";

    public static String getHomeDirectory() {
        return Environment.getExternalStorageDirectory() + homeDirectory;
    }
}
