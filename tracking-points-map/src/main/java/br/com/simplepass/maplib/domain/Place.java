package br.com.simplepass.maplib.domain;

import com.google.android.gms.maps.model.Marker;

/**
 * Created by hinovamobile on 14/09/16.
 */
public class Place {
    private long id;
    private double latitude;
    private double longitude;
    private Marker marker;

    public Place() {
    }

    public Place(long id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }
}
