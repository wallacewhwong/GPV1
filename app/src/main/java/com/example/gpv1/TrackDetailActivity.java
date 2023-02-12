package com.example.gpv1;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class TrackDetailActivity extends AppCompatActivity {

    TrackModel trackModel;
    TextView tv_title, tv_district, tv_route, tv_howToAccess, tv_mapurl, tv_latitude, tv_longitude;
    ImageView iv_mapImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackdetail);

        Intent i = getIntent();
        trackModel=new TrackModel();

        // get extract from selected track

        trackModel.title = i.getStringExtra("title");
        trackModel.district = i.getStringExtra("district");
        trackModel.route = i.getStringExtra("route");
        trackModel.howToAccess = i.getStringExtra("HowToAccess");
        trackModel.mapurl = i.getStringExtra("mapurl");
        trackModel.latitude = i.getDoubleExtra("latitude",0.0) ;
        trackModel.longitude = i.getDoubleExtra("longitude",0.0) ;

        tv_title = findViewById(R.id.tv_title);
        tv_district = findViewById(R.id.tv_district);
        tv_howToAccess = findViewById(R.id.tv_howToAccess);
        tv_route = findViewById(R.id.tv_route);
        tv_mapurl = findViewById(R.id.tv_mapurl);
        tv_latitude = findViewById(R.id.tv_latitude);
        tv_longitude = findViewById(R.id.tv_longitude);
        iv_mapImage = findViewById(R.id.iv_mapimage);

        tv_title.setText(trackModel.title);
        tv_district.setText(trackModel.district);
        tv_route.setText(trackModel.route);
        tv_howToAccess.setText(trackModel.howToAccess);
        tv_mapurl.setText(trackModel.mapurl);
        tv_latitude.setText(String.valueOf(trackModel.latitude) );
        tv_longitude.setText(String.valueOf(trackModel.longitude) );

        Picasso.get().load(trackModel.mapurl).into(iv_mapImage);


    }

}
