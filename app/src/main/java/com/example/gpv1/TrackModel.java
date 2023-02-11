package com.example.gpv1;

public class TrackModel {

    public String title;
    public String district;
    public String route;
    public String howToAccess;
    public String mapurl;
    public double latitude;
    public double longitude;

    @Override
    public String toString() {
        return "TrackModel{" +
                "title='" + title + '\'' +
                ", district='" + district + '\'' +
                ", route='" + route + '\'' +
                ", howToAccess='" + howToAccess + '\'' +
                ", mapurl='" + mapurl + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

}
