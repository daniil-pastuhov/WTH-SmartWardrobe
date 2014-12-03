package tasks;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FetchWeatherTask extends AsyncTask<String, Void, String> {
    private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();
    SpannableString errorString = new SpannableString("Проверьте соединение");
    private Activity activity;
    private TextView adapter;
    public FetchWeatherTask (Activity activity, TextView adapter) {
        this.activity = activity;
        this.adapter = adapter;
    }
    @Override
    protected void onPostExecute(String s) {

        if (s!=null && !s.toString().isEmpty()) {
            try {
                SpannedString[] strings = getWeatherDataFromJson(s, 1);
                adapter.setText(strings[0]);
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Unexpected", e);
            }
        }

        else  {
            Toast.makeText(activity, "Проверьте соединение", Toast.LENGTH_LONG);
            adapter.setText(errorString);
        }
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String ... strings) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String forecastJsonStr = null;
        String format = "json";
        try {
            if (!isInternetAvailable())
                throw new UnsupportedOperationException("Check Internet connection");
            final String QUERY_PARAM = "q";
            final String FORMAT_PARAM = "mode";
            final String UNITS_PARAM = "units";
            final String DAYS_PARAM = "cnt";

            Uri uri = Uri.parse("http://api.openweathermap.org/data/2.5/forecast/daily?")
                    .buildUpon().appendQueryParameter(QUERY_PARAM, strings[0])
                    .appendQueryParameter(FORMAT_PARAM, format)
                    .appendQueryParameter(UNITS_PARAM, strings[1])
                    .appendQueryParameter(DAYS_PARAM, "1")
                    .build();
            URL url = new URL(uri.toString());
            Log.v(LOG_TAG, url.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                forecastJsonStr = null;
                return  null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                forecastJsonStr = null;
                return null;
            }
            forecastJsonStr = buffer.toString();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            forecastJsonStr = null;
            return null;
        }
        catch (UnsupportedOperationException e) {
            Log.e(LOG_TAG, "Error with connection", e);
            forecastJsonStr = null;
            return null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return forecastJsonStr;
    }
    private SpannedString[] getWeatherDataFromJson(String forecastJsonStr, int numDays)
            throws JSONException {

        final String OWM_LIST = "list";
        final String OWM_WEATHER = "weather";
        final String OWM_TEMPERATURE = "temp";
        final String OWM_MAX = "max";
        final String OWM_MIN = "min";
        final String OWM_DATETIME = "dt";
        final String OWM_DESCRIPTION = "main";

        JSONObject forecastJson = new JSONObject(forecastJsonStr);
        JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);

        SpannedString[] resultSpans = new SpannedString[numDays];
        for(int i = 0; i < weatherArray.length(); i++) {
            String day;
            String description;
            Spanned highAndLow;

            JSONObject dayForecast = weatherArray.getJSONObject(i);

            long dateTime = dayForecast.getLong(OWM_DATETIME);
            day = getReadableDateString(dateTime);

            JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            description = weatherObject.getString(OWM_DESCRIPTION);

            JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
            double high = temperatureObject.getDouble(OWM_MAX);
            double low = temperatureObject.getDouble(OWM_MIN);

            highAndLow = formatHighLows(high, low);
            resultSpans[i] = new SpannedString(day + " - " + description + " - " + highAndLow);
        }

        return resultSpans;
    }
    private String getReadableDateString(long time){
        Date date = new Date(time * 1000);
        SimpleDateFormat format = new SimpleDateFormat("EE, d MMM");
        return format.format(date).toString();
    }
    private SpannedString formatHighLows(double high, double low) {
        long roundedHigh = Math.round(high);
        long roundedLow = Math.round(low);

        SpannedString highLowStr = new SpannedString(
                Html.fromHtml("<font color='blue'>" + String.valueOf(roundedHigh) + "</font>" + "&#176;" + "/" + "<font color='blue'>" + String.valueOf(roundedLow) + "&#176;" + "</font>"));
        return highLowStr;
    }
    public boolean isInternetAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}