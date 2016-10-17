package br.com.simplepass.maplib.interfaces;

import com.google.android.gms.maps.model.Marker;

/**
 * Created by hinovamobile on 12/09/16.
 */
public interface MapPoint {
    long getVanId();
    double getLatitude();
    double getLongitude();
    void setLatitude(double latitude);
    void setLongitude(double longitude);
    Marker getMarker();
    void setMarker(Marker marker);
}
