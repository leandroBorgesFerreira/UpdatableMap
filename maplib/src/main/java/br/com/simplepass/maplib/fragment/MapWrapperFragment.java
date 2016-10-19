package br.com.simplepass.maplib.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import br.com.simplepass.maplib.R;
import br.com.simplepass.maplib.animation.LatLngInterpolator;
import br.com.simplepass.maplib.animation.MarkerAnimation;
import br.com.simplepass.maplib.domain.Car;
import br.com.simplepass.maplib.interfaces.MapPoint;
import br.com.simplepass.maplib.interfaces.MapSyncer;
import br.com.simplepass.maplib.interfaces.MapWrapper;
import br.com.simplepass.maplib.interfaces.MapWrapperListener;
import br.com.simplepass.maplib.interfaces.PointsDrawer;
import br.com.simplepass.maplib.interfaces.UpdatableMap;
import rx.Observer;
import rx.Subscription;

/**

 */
public class MapWrapperFragment extends Fragment
        implements MapWrapper,
        OnMapReadyCallback,
        UpdatableMap,
        PointsDrawer{

    private GoogleMap mGoogleMap;
    private Map<Long, MapPoint> mMovingPointsMap;
    private Map<Long, MapPoint> mFixedPointsMap;

    private MapWrapperListener mWrapperListener;
    private MapSyncer<Car> mMapSyncer;
    private Subscription mSubscription;

    public MapWrapperFragment() {
        // Required empty public constructor
    }

    public void setMapSyncer(MapSyncer<Car> mMapSyncer) {
        this.mMapSyncer = mMapSyncer;
    }

    @Override
    public void startUpdates() {
        if(mMapSyncer == null){
            throw new IllegalStateException("You have to provide a MapSyncer to call this method!");
        }

        mSubscription = mMapSyncer.getCarsObservable().subscribe(new Observer<Collection<Car>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Collection<Car> cars) {
                updateMovingPoints(cars);
            }
        });
    }

    @Override
    public void stopUpdates() {
        mSubscription.unsubscribe();
    }


    //ToDo: Aqui deve entrar subscribe!
    @Override
    public void drawFixedPoints(Iterable<? extends MapPoint> mapPoints) {
        for(MapPoint mapPoint : mapPoints) {
            MapPoint pointOnMap = mFixedPointsMap.get(mapPoint.getVanId());

            if(pointOnMap == null){
                pointOnMap = mapPoint;

                LatLng latLng = new LatLng(pointOnMap.getLatitude(), pointOnMap.getLongitude());

                pointOnMap.setMarker(mGoogleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Local Fixo")));

                mFixedPointsMap.put(pointOnMap.getVanId(), pointOnMap);
            } else{
                Marker marker = pointOnMap.getMarker();
                LatLng latLng = new LatLng(pointOnMap.getLatitude(), pointOnMap.getLongitude());

                LatLngInterpolator latLngInterpolator = new LatLngInterpolator.Linear();
                MarkerAnimation.animateMarkerToGB(marker, latLng, latLngInterpolator);
            }
        }
    }

    //ToDo: Aqui deve entrar subscribe!
    @Override
    public void updateMovingPoints(Iterable<? extends MapPoint> movingPoints) {
        for(MapPoint mapPoint : movingPoints){
            MapPoint pointOnMap = mMovingPointsMap.get(mapPoint.getVanId());

            if(pointOnMap == null){
                pointOnMap = mapPoint;

                LatLng latLng = new LatLng(pointOnMap.getLatitude(), pointOnMap.getLongitude());

                pointOnMap.setMarker(mGoogleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Isso é um carro")));

                mMovingPointsMap.put(pointOnMap.getVanId(), pointOnMap);
            } else{
                Marker marker = pointOnMap.getMarker();
                LatLng latLng = new LatLng(pointOnMap.getLatitude(), pointOnMap.getLongitude());

                LatLngInterpolator latLngInterpolator = new LatLngInterpolator.Linear();
                MarkerAnimation.animateMarkerToGB(marker, latLng, latLngInterpolator);
            }
        }
    }

    @Override
    public void updateMovingPoints(MapPoint mapPoint) {

    }

    @Override
    public GoogleMap getGoogleMap() {
        return mGoogleMap;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        mWrapperListener.onMapReady();
    }

    public static MapWrapperFragment newInstance(String param1, String param2) {
        return new MapWrapperFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMovingPointsMap = new HashMap<>();
        mFixedPointsMap = new HashMap<>();
    }

    private void mapInit(){
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        view.findViewById(R.id.btn_maps_zoom_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mGoogleMap != null) {
                    mGoogleMap.animateCamera(CameraUpdateFactory.zoomIn());
                }
            }
        });

        view.findViewById(R.id.btn_maps_zoom_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mGoogleMap != null) {
                    mGoogleMap.animateCamera(CameraUpdateFactory.zoomOut());
                }
            }
        });

        mapInit();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MapWrapperListener) {
            mWrapperListener = (MapWrapperListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MapWrapperListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mWrapperListener = null;
    }
}
