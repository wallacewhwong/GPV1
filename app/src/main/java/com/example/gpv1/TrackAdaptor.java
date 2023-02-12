package com.example.gpv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TrackAdaptor extends BaseAdapter {

    private ArrayList<TrackModel> trackModels;
    private Context context;

    public TrackAdaptor(Context context,ArrayList<TrackModel> trackModels){
        this.trackModels = trackModels;
        this.context = context;
    }

    @Override
    public int getCount() {
        return trackModels.size();
    }

    @Override
    public Object getItem(int position) {
        return trackModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.track_item, parent,false);
        TextView tv_title, tv_district;
        ImageView iv_mapImage;

        tv_title = view.findViewById(R.id.tv_title);
        tv_district = view.findViewById(R.id.tv_district);
        iv_mapImage = view.findViewById(R.id.iv_mapimage);

        tv_title.setText(trackModels.get(position).title);
        tv_district.setText(trackModels.get(position).district);

        Picasso.get().load(trackModels.get(position).mapurl).into(iv_mapImage);

        return view;
    }
}
