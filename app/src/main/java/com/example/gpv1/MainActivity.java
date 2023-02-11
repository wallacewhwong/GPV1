package com.example.gpv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_loadJson = findViewById(R.id.btn_loadJson);
        lv_tracklist = findViewById(R.id.lv_tracklist);

        btn_loadJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final TextView textView = (TextView) findViewById(R.id.tv_result);

            // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://www.lcsd.gov.hk/datagovhk/facility/facility-fw.json";

                // Request a string response from the provided URL.

                JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                // Display the first 500 characters of the response string.
                                textView.setText("Response is: " + response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("That didn't work!");
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });  // end button

    }  // end oncreate
}