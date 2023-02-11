package com.example.gpv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
//
//
//
//
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


    }  // end oncreate

    private void convertJsonToArray(JSONArray jsonArray){

        for(int x=1; x<=jsonArray.length()-1; x++){

            try{

                TrackModel trackModel = new TrackModel();
                JSONObject jsonObject = jsonArray.getJSONObject(x);

                trackModel.title = jsonObject.getString("Title_en");
                trackModel.district = jsonObject.getString("District_en");
                trackModel.howToAccess = jsonObject.getString("HowToAccess_en");
                trackModel.route = jsonObject.getString("Route_en");
                trackModel.latitude = jsonObject.getDouble("Latitude");
                trackModel.longitude = jsonObject.getDouble("Longitude");
                trackModel.mapurl = jsonObject.getString("MapURL_en");

                tracksArrayList.add(trackModel);

                Log.e("abc",trackModel.toString());

            }catch (Exception e){
                e.printStackTrace();
            }

        } // end for

    } // end convertJsonArray

}