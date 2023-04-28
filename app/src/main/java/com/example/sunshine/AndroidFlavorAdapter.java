package com.example.sunshine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AndroidFlavorAdapter extends ArrayAdapter<AndroidFlavor> implements View.OnClickListener{
    public String description,day,low,high,country;
    public int pic;
    private static final String LOG_TAG = AndroidFlavorAdapter.class.getSimpleName();
    //public ColorsActivity colo;
//private MediaPlayer mediaPlayer;
    int songid;

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the List is the data we want
     * to populate into the lists
     *  @param context        The current context. Used to inflate the layout file.
     * @param androidFlavors A List of AndroidFlavor objects to display in a list
     */
    public AndroidFlavorAdapter(MainActivity context, List<AndroidFlavor> androidFlavors) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, androidFlavors);
    }

    public AndroidFlavorAdapter(MainActivity forecastFragment, ArrayList<AndroidFlavor> andflavor) {
        super(forecastFragment,0, andflavor);
    }




    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The AdapterView position that is requesting a view
     * @param convertView The recycled view to populate.
     *                    (search online for "android view recycling" to learn more)
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gets the AndroidFlavor object from the ArrayAdapter at the appropriate position
        AndroidFlavor androidFlavor = getItem(position);
        final AndroidFlavor android = getItem(position);
        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_flavor, parent, false);
        }
        LinearLayout lnlay=(LinearLayout)convertView.findViewById(R.id.box_layoutdisp);
        //lnlay.setBackgroundColor(R.color.great);

        ImageView iconView = (ImageView) convertView.findViewById(R.id.list_item_icon);
        iconView.setImageResource(androidFlavor.getImage());

        TextView versionNameView = (TextView) convertView.findViewById(R.id.list_item_version_name);
        versionNameView.setText(androidFlavor.getVersionName());

        TextView versionNumberView = (TextView) convertView.findViewById(R.id.list_item_versionnumber_textview);
        versionNumberView.setText(androidFlavor.getVersionNumber());
        TextView maxtemp=(TextView)convertView.findViewById(R.id.max_textview);
        maxtemp.setText( androidFlavor.getmax()+'\u00B0');
        TextView mintemp=(TextView)convertView.findViewById(R.id.min_textview);
        mintemp.setText(  androidFlavor.getmin()+'\u00B0');
ImageView img=(ImageView)convertView.findViewById(R.id.list_item_icon);


             img.setImageResource(androidFlavor.getPic());;
        //play.setVisibility(View.GONE );

      //  songid=androidFlavor.getSong();

   // play.setOnClickListener(this);
       /*  lnlay.setOnClickListener(new View.OnClickListener(){


           public void onClick(View view) {
                description=android.getVersionName();
day=android.getVersionNumber();
pic=android.getPic();
high=android.getHigh();
low=android.getLow();
              //  Intent numbersIntent=new Intent(view.getContext(),DetailActivity.class).putExtra("description",description).putExtra("day",day).putExtra("pic",pic)
                     //   .putExtra("high",high).putExtra("low",low);
             //   view.getContext().startActivity(numbersIntent);

            }
        });*/

        return convertView;
    }
    @Override
    public void onClick(View v) {
   //    Toast.makeText(getContext(),"forecast",Toast.LENGTH_SHORT).show();
//Intent intent=new Intent(v.getContext(),DetailActivity.class).putExtra("description",description);
//v.getContext().startActivity(intent);

/*
        if(AndroidFlavor.mediaPlayer==null) {
            AndroidFlavor.mediaPlayer = MediaPlayer.create(getContext(), songid);
            AndroidFlavor.mediaPlayer.start();
        }else
        if(!AndroidFlavor.mediaPlayer.isPlaying())
            AndroidFlavor.mediaPlayer = MediaPlayer.create(getContext(), songid);
        AndroidFlavor.mediaPlayer.start();
        if(!AndroidFlavor.mediaPlayer.isPlaying()){
            AndroidFlavor.mediaPlayer.start();
        }

 */
    }


    public void add(ArrayList<AndroidFlavor> andflavor) {
    }
}
