package com.example.sunshine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            Log.v("logm", "hello there its starting");
            getSupportFragmentManager().beginTransaction().add(R.id.container2, new DetailFragment()).commit();
        }

    }


    public static class DetailFragment extends Fragment {
        ArrayAdapter mforecastArray = null;
        private String FORECAST_SHARE_HASHTAG="#SUNSHINEAPP";
public String mforecastStr;
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
            //    setContentView(R.layout.activity_detail);
        }
        public DetailFragment(){
            setHasOptionsMenu(true);
        }

           @Override
       public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
            menuInflater.inflate(R.menu.detailfragment,menu);
            Log.v("INONCREATE","detailFragment");
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item){
            int id=item.getItemId();
            Log.v("LOGe","in onptionItemselected");
            if(id==R.id.action_refresh){
                Log.v("LOGe","in onptionItemselected");
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
        private Intent createShareForecast(){
            Intent shareIntent=new Intent(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT,mforecastStr+FORECAST_SHARE_HASHTAG);
            return shareIntent;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            View RootView = inflater.inflate(R.layout.fragment_detail, container, false);
            Intent intent=getActivity().getIntent();
            if(intent!=null&&intent.hasExtra(Intent.EXTRA_TEXT)){
                String forecastStr=intent.getStringExtra(Intent.EXTRA_TEXT);
               // ((TextView)RootView.findViewById(R.id.listview_details)).setText(forecastStr);
            }
            if(intent.hasExtra("description")){
                String desc=intent.getStringExtra("description");
                ((TextView)RootView.findViewById(R.id.desc_textview)).setText(desc);
            }

            if(intent.hasExtra("day")){
                String desc=intent.getStringExtra("day");
                ((TextView)RootView.findViewById(R.id.weathertype)).setText(desc);
            }
            if(intent.hasExtra("pic")){
                int desc=intent.getIntExtra("pic",0);
                ((ImageView)RootView.findViewById(R.id.imgviewsrc)).setImageResource(desc);
            }
            if(intent.hasExtra("high")){
                String desc=intent.getStringExtra("high");
                ((TextView)RootView.findViewById(R.id.highid)).setText(desc+'\u00B0');
            }
            if(intent.hasExtra("low")){
                String desc=intent.getStringExtra("low");
                ((TextView)RootView.findViewById(R.id.lowid)).setText(desc+'\u00B0');
            }

            // Inflate the layout for this fragment

          //  return RootView;
        /*    String[] array = {
                    "ssthis is first",
                    "ssthis is second",
                    "ssthis is third",
                    "ssthis is forth"
            };
            List<String> weekForecast = new ArrayList<String>(Arrays.asList(array));

            mforecastArray = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast, R.id.text_view, weekForecast);
            ListView listView = (ListView) RootView.findViewById(R.id.listview_details);
            listView.setAdapter(mforecastArray);

*/
               return RootView;
            // BufferedReader reader=null;
            // String forecaseJsonStr=null;


        }

    }
}


