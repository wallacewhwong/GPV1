package com.example.gpv1;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class DataService {
    Context context;
    String url = "https://www.lcsd.gov.hk/datagovhk/facility/facility-fw.json";

    public DataService(Context context){
        this.context = context;
    }

    public interface DataServiceListener{
        void onResponse(JSONArray response);
        void onError(String msg);
    }

    public void getTrackJsons(DataServiceListener dataServiceListener) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.

        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        dataServiceListener.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dataServiceListener.onError("Something wrong");
            }
        });


        // Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }


}
