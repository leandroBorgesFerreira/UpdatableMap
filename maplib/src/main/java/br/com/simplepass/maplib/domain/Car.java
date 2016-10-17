package br.com.simplepass.maplib.domain;

import com.google.android.gms.maps.model.Marker;

import br.com.simplepass.maplib.interfaces.MapPoint;

/**
 * Created by hinovamobile on 12/09/16.
 */
public class Car implements MapPoint {

    private long vanId;
    private double latitude;
    private double longitude;
    private Marker marker;

    public Car() {
    }

    public Car(long vanId, double latitude, double longitude) {
        this.vanId = vanId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Car(long vanId, double latitude, double longitude, Marker marker) {
        this.vanId = vanId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.marker = marker;
    }

    public long getVanId() {
        return vanId;
    }

    public void setVanId(long vanId) {
        this.vanId = vanId;
    }

    @Override
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

    @Override
    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    @Override
    public String toString() {
        return "Car{" +
                "vanId=" + vanId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
