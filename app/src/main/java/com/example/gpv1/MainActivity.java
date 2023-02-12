package com.example.gpv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
//
// https://www.lcsd.gov.hk/datagovhk/facility/facility-fw.json
//
//  Volley http
//  https://google.github.io/volley/simple.html
//
//  get image from url
//  https://square.github.io/picasso/
//
//  Animation reference
// https://gist.github.com/codinginflow/6d606f12f4db20f5133fc90a42a1b9c5
//
//  Google map intent
//  https://developers.google.com/maps/documentation/urls/android-intents
//


public class MainActivity extends AppCompatActivity {

    Button btn_loadJson;
    ListView lv_tracklist;
    TextView tv_result;
    DataService dataService;
    JSONArray jsonArraytracks;

    // listview data
    ArrayList<TrackModel> tracksArrayList = new ArrayList<>();
    TrackAdaptor trackAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_loadJson = findViewById(R.id.btn_loadJson);
        lv_tracklist = findViewById(R.id.lv_tracklist);
        tv_result = findViewById(R.id.tv_result);

        btn_loadJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataService = new DataService(MainActivity.this);

                dataService.getTrackJsons(new DataService.DataServiceListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        convertJsonToArray(response);
                        trackAdaptor = new TrackAdaptor(MainActivity.this, tracksArrayList);
                        lv_tracklist.setAdapter(trackAdaptor);
                    }

                    @Override
                    public void onError(String msg) {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                    }
                });

            }
        });  // end button

        // listview onItemClick
        lv_tracklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainActivity.this, TrackDetailActivity.class);

                TrackModel selectedTrack = tracksArrayList.get(position);

                Log.e("abc",selectedTrack.toString());

                i.putExtra("title",selectedTrack.title);
                i.putExtra("district",selectedTrack.district);
                i.putExtra("route",selectedTrack.route);
                i.putExtra("howToAccess",selectedTrack.howToAccess);
                i.putExtra("mapurl",selectedTrack.mapurl);
                i.putExtra("latitude", selectedTrack.latitude);
                i.putExtra("longitude",selectedTrack.longitude);

                // animation
                Fade fade = new Fade();

                getWindow().setEnterTransition(fade);
                getWindow().setExitTransition(fade);


                View imageView =findViewById(R.id.iv_mapimage);

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        MainActivity.this, imageView, ViewCompat.getTransitionName(imageView));

                startActivity(i, options.toBundle());

//                startActivity(i);

            }
        });

    }  // end oncreate

    private void convertJsonToArray(JSONArray jsonArray){

        Language language = new Language();

        for(int x=1; x<=jsonArray.length()-1; x++){

            try{

                TrackModel trackModel = new TrackModel();
                JSONObject jsonObject = jsonArray.getJSONObject(x);

                if (language.language.equals("en")) {

                    trackModel.title = jsonObject.getString("Title_en");
                    trackModel.district = jsonObject.getString("District_en");
                    trackModel.howToAccess = jsonObject.getString("HowToAccess_en");
                    trackModel.route = jsonObject.getString("Route_en");
                    trackModel.latitude = jsonObject.getDouble("Latitude");
                    trackModel.longitude = jsonObject.getDouble("Longitude");
                    trackModel.mapurl = jsonObject.getString("MapURL_en");

                } else if (language.language.equals("tc")){

                    trackModel.title = jsonObject.getString("Title_tc");
                    trackModel.district = jsonObject.getString("District_tc");
                    trackModel.howToAccess = jsonObject.getString("HowToAccess_tc");
                    trackModel.route = jsonObject.getString("Route_tc");
                    trackModel.latitude = jsonObject.getDouble("Latitude");
                    trackModel.longitude = jsonObject.getDouble("Longitude");
                    trackModel.mapurl = jsonObject.getString("MapURL_tc");

                } else if (language.language.equals("sc")){

                    trackModel.title = jsonObject.getString("Title_sc");
                    trackModel.district = jsonObject.getString("District_sc");
                    trackModel.howToAccess = jsonObject.getString("HowToAccess_sc");
                    trackModel.route = jsonObject.getString("Route_sc");
                    trackModel.latitude = jsonObject.getDouble("Latitude");
                    trackModel.longitude = jsonObject.getDouble("Longitude");
                    trackModel.mapurl = jsonObject.getString("MapURL_sc");
                }  // end if


                tracksArrayList.add(trackModel);

                Log.e("abc",trackModel.toString());

            }catch (Exception e){
                e.printStackTrace();
            }

        } // end for

    } // end convertJsonArray

}