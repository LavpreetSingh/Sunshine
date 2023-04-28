package com.example.sunshine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//import org.json.JSONArray;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.jar.JarException;

public  class ForecastFragment extends Fragment {
    public static boolean strt=false;
    public static String de;
    Context con;
    ArrayList<AndroidFlavor> andflavor;
    ListView lip;
    public AndroidFlavor and;
   public AndroidFlavorAdapter flavorAdapter;
   public ArrayAdapter mforecastArray=null;
    private  final String LOGe="heloo";
    URL url=null;
    private String Item;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        menuInflater.inflate(R.menu.forecastfragment,menu);
        Log.v("INONCREATE","forecastFragment");
    }

  /*  public boolean onOptionItemSelected(MenuItem item){
        int id=item.getItemId();
        Log.v(LOGe,"in onptionItemselected");
        if(id==R.id.action_refresh){
       FetchWeatherTask weatherTask=new FetchWeatherTask();
       weatherTask.execute("74043");
       Log.v(LOGe,"in onptionItemselected");
            return true;
        }
return super.onOptionsItemSelected(item);
    }*/
  @Override
  public boolean onOptionsItemSelected(MenuItem item){
      int id=item.getItemId();
      Log.v(LOGe,"in onptionItemselected");
      if(id==R.id.action_refresh){
          Log.v(LOGe,"in onptionItemselected");
          if(isConnectingToInternet(getContext())==true)
         updateWeather();

          return true;
      }
      if(id==R.id.action_settings){
          Intent intent=new Intent(getActivity(),SettingsActivity.class);
          startActivity(intent);
        //  updateWeather();
          return true;
      }
if(id==R.id.action_refresh){
    updateWeather();
}
      return super.onOptionsItemSelected(item);
  }

    @Override
    public void onStart() {
        super.onStart();
 if(strt==true)
        updateWeather();
 strt=false;
    }

    public void updateWeather(){
        andflavor.clear();
        flavorAdapter.clear();
    FetchWeatherTask weatherTask=new FetchWeatherTask();
    SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
    String location=preferences.getString(getString(R.string.pref_location_key),
            getString(R.string.pref_location_default));
    weatherTask.execute(location);
    return;
}
   /* public StringBuffer dowork(){
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Chandigarh,country&APPID=e71b19e6bbe0ab36a44a4b69a4a6a396");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try{
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();;
            InputStream inputStream=urlConnection.getInputStream();
            stringBuffer=new StringBuffer();
            if(stringBuffer==null){
                return null;
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuffer;
    }
*/

    public boolean isInternetWorking() {
        boolean success = false;
        try {
            URL url = new URL("https://google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);
            connection.connect();
            success = connection.getResponseCode() == 200;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }
    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {

            NetworkInfo[] info = connectivity.getAllNetworkInfo();

            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater,container,savedInstanceState);
        andflavor=new ArrayList<AndroidFlavor>();
      View RootView = inflater.inflate(R.layout.fragment_main, container, false);
       /*   Button li=(Button) RootView.findViewById(R.id.bu);
        li.setOnClickListener(new View.OnClickListener(){;


            public void onClick(View view) {

                //Toast.makeText(view.getContext(),
                //   "open the list of numbers",Toast.LENGTH_SHORT).show();
                Intent numbersIntent=new Intent(view.getContext(),DetailActivity.class);
                view.getContext().startActivity(numbersIntent);

            }
        });
*/
   /*     String[] array = {

        };
        List<String> weekForecast = new ArrayList<String>(Arrays.asList(array));

          mforecastArray = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast, R.id.text_view, weekForecast);
        ListView listView = (ListView) RootView.findViewById(R.id.listview_flavor);
        listView.setAdapter(mforecastArray);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String forecast= (String) mforecastArray.getItem(position);
                Toast.makeText(getActivity(),forecast,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),DetailActivity.class).putExtra(Intent.EXTRA_TEXT,forecast);
                startActivity(intent);
            }
        });
*/
        //Populate the androidFlavors array with AndroidFlavor objects
       // final AndroidFlavor[] androidFlavors = {
//
       // };
        andflavor.add(new AndroidFlavor("1","2","afdfd",3, 4,2020,"this is sunny", R.drawable.cupcake));
        //Instantiate a custom adapter called flavorAdapter
       // flavorAdapter = new AndroidFlavorAdapter((MainActivity) this.getActivity(), Arrays.asList(androidFlavors));
flavorAdapter=new AndroidFlavorAdapter((MainActivity) this.getActivity(),andflavor);
con=(MainActivity) this.getActivity();
        // Get a reference to the ListView, and attach the adapter to the listView.
        lip = (ListView) RootView.findViewById(R.id.listview_flavor);
        lip.setAdapter(flavorAdapter);
      /* LinearLayout lnlay=(LinearLayout)container.findViewById(R.id.box_layoutdisp);
        lip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                AndroidFlavor forecast= (AndroidFlavor) flavorAdapter.getItem(position);
                Toast.makeText(getActivity(),"forecast",Toast.LENGTH_SHORT).show();
            //   Intent intent=new Intent(getActivity(),DetailActivity.class);
               // startActivity(intent);
            }
        });*/
        Toast.makeText(getActivity(),"forecast",Toast.LENGTH_SHORT).show();
        lip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
             //   String forecast= (String) mforecastArray.getItem(position);
                Toast.makeText(getActivity(),"forecast",Toast.LENGTH_SHORT).show();
              AndroidFlavor and  =flavorAdapter.getItem(position);
                Intent intent=new Intent(getActivity(),DetailActivity.class).putExtra(Intent.EXTRA_TEXT,"forecast");
                intent.putExtra("description",and.description ).putExtra("day",and.day);
                intent.putExtra("high",and.high).putExtra("low",and.low).putExtra("pic",and.pic);
              //  intent.putExtra("AndroidFlavor", (Serializable) and);
                startActivity(intent);
            }
        });
       if(isConnectingToInternet(getContext())==true)
updateWeather();

        return RootView;
        // BufferedReader reader=null;
        // String forecaseJsonStr=null;


    }


public class FetchWeatherTask extends AsyncTask<String,Void,String[]>{
    StringBuffer stringBuffer=null;
    String forecastJsonStr=null;
private  final String LOG_TAG=FetchWeatherTask.class.getSimpleName();

    private String getReadableDateString(long time){
        // Because the API returns a unix timestamp (measured in seconds),
        // it must be converted to milliseconds in order to be converted to valid date.
        Date date = new Date(time * 1000);
        SimpleDateFormat format = new SimpleDateFormat("E, MMM d");
        return format.format(date).toString();
    }

    /**
     * Prepare the weather high/lows for presentation.
     */
    private String formatHighLows(double high, double low) {
        // For presentation, assume the user doesn't care about tenths of a degree.
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        String unitType=preferences.getString(getString(R.string.pref_units_key),
                getString(R.string.pref_units_metric));
        if(unitType.equals(getString(R.string.pref_units_imperial))){
            high=(high*1.8)+32;
            low=(low*1.8)+32;
        }
        long roundedHigh = Math.round(high);
        long roundedLow = Math.round(low);

        String highLowStr = roundedHigh + "/" + roundedLow;
        return highLowStr;
    }

    /**
     * Take the String representing the complete forecast in JSON Format and
     * pull out the data we need to construct the Strings needed for the wireframes.
     *
     * Fortunately parsing is easy:  constructor takes the JSON string and converts it
     * into an Object hierarchy for us.
     */
    private String[] getWeatherDataFromJson(String forecastJsonStr, int numDays)
            throws JSONException {

        // These are the names of the JSON objects that need to be extracted.
        final String OWM_LIST = "list";
        final String OWM_WEATHER = "weather";
        final String OWM_TEMPERATURE = "main";
        final String OWM_MAX = "temp_max";
        final String OWM_MIN = "temp_min";
        final String OWM_DATETIME = "dt";
        final String OWM_DESCRIPTION = "main";
        String day;
        JSONObject forecastJson = new JSONObject(forecastJsonStr);
        JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);
Log.v("objectmad","hello");
        String[] resultStrs = new String[numDays];
        for(int i = 0; i < weatherArray.length(); i++) {
            // For now, using the format "Day, description, hi/low"

            String description;
            String highAndLow;

            // Get the JSON object representing the day
            JSONObject dayForecast = weatherArray.getJSONObject(i);
Log.v("object day","f");
            // The date/time is returned as a long.  We need to convert that
            // into something human-readable, since most people won't read "1400356800" as
            // "this saturday".
            long dateTime = dayForecast.getLong(OWM_DATETIME);
            day = getReadableDateString(dateTime);

            // description is in a child array called "weather", which is 1 element long.
            JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            description = weatherObject.getString(OWM_DESCRIPTION);

            // Temperatures are in a child object called "temp".  Try not to name variables
            // "temp" when working with temperature.  It confuses everybody.
            JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
            double high = temperatureObject.getDouble(OWM_MAX);
            double low = temperatureObject.getDouble(OWM_MIN);

          //  highAndLow = formatHighLows(high, low);
            SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
            String unitType=preferences.getString(getString(R.string.pref_units_key),
                    getString(R.string.pref_units_metric));
            if(unitType.equals(getString(R.string.pref_units_imperial))){
                high=(high*1.8)+32;
                low=(low*1.8)+32;
                high = Math.round(high);
                low = Math.round(low);
            }
            long roundedHigh = Math.round(high);
            long roundedLow = Math.round(low);
highAndLow=roundedHigh + "/" + roundedLow;
            resultStrs[i] = day + " - " + description + " - " + highAndLow;



            //   String[] values = day.split("-");
   int date = 11;
   int month = 2;
   int year = 2020;
   Log.v("tag",resultStrs[i]);
   int pic;
 //  if(description.equals("clear sky")) {
      pic = R.drawable.cupcake;
   // }
   // else
       // pic=R.drawable.donut;

if(day.equals(de)){

}else {
    if(description.equals("Clear")) {
        andflavor.add(new AndroidFlavor(Double.toString(high), Double.toString(low), day, date, month, year, description, R.drawable.clear));
    }else if(description.equals("Clouds")){
        andflavor.add(new AndroidFlavor(Double.toString(high), Double.toString(low), day, date, month, year, description, R.drawable.clouds1));
    }else{
        andflavor.add(new AndroidFlavor(Double.toString(high), Double.toString(low), day, date, month, year, description, R.drawable.thunderstorm));
    }
    de=day;
}
    }


        return resultStrs;
    }

    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);
//flavorAdapter.clear();
     //   andflavor.clear();
    //    flavorAdapter.addAll(andflavor);

     //   flavorAdapter.add(andflavor);
       // updateWeather();
     flavorAdapter=new AndroidFlavorAdapter((MainActivity) con,andflavor);
       lip.setAdapter(flavorAdapter);
    }

    /*
        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
        if(strings!=null){
            mforecastArray.clear();
            for(String dayForecastStr: strings){
                mforecastArray.add(dayForecastStr);
            }
        }

        }

    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);
            mforecastArray.clear();

                mforecastArray.add(andflavor);



    }

     */
    @Override
    protected String[] doInBackground(String...params ) {
        BufferedReader reader;
        if(params.length==0){
            return null;
        }
      /*  try {
            url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=Chandigarh,country&APPID=e71b19e6bbe0ab36a44a4b69a4a6a396");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/

      String format="json";
      String units="metric";
      int numDays=35;
     String appid="e71b19e6bbe0ab36a44a4b69a4a6a396";
        try{
            final String FORECAST_BASE_URL="http://api.openweathermap.org/data/2.5/forecast?";
            final String QUERY_PARAM="q";
            final String FORMAT_PARAM="mode";
            final String UNITS_PARAM="units";
            final String DAYS_PARAM="cnt";
            final String AMP="&";
            final  String APPID="APPID";
            Uri buildUri=Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM,params[0])
                    .appendQueryParameter(FORMAT_PARAM,format)
                    .appendQueryParameter(UNITS_PARAM,units)
                    .appendQueryParameter(DAYS_PARAM,Integer.toString(numDays))
                    .appendQueryParameter(APPID,appid)
                   .build();
URL url=new URL(buildUri.toString());
            Log.v(LOG_TAG,"BUILDURI"+buildUri.toString());
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            Log.v("h2","hello");
            urlConnection.connect();
            Log.v("h3","hello");
            InputStream inputStream=urlConnection.getInputStream();
            Log.v("h1","hello");
            stringBuffer=new StringBuffer();
            if(stringBuffer==null){
                return null;
            }


        reader=new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line=reader.readLine())!=null){
                stringBuffer.append(line+"/n");
            }
            if(stringBuffer.length()==0){
                return null;
            }
            reader.close();
            urlConnection.disconnect();
            forecastJsonStr=stringBuffer.toString();
Log.v("strs",forecastJsonStr);
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return getWeatherDataFromJson(forecastJsonStr,numDays);
        }catch (JSONException e){
            Log.e(LOG_TAG,e.getMessage(),e);
            e.printStackTrace();
        }
   //   Log.v("sstring",forecastJsonStr);
        return null;
    }
}
}
