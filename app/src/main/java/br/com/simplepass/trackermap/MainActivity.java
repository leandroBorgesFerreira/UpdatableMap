package br.com.simplepass.trackermap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Map;

import br.com.simplepass.maplib.fragment.MapWrapperFragment;
import br.com.simplepass.maplib.interfaces.MapPoint;
import br.com.simplepass.maplib.interfaces.MapSyncer;
import br.com.simplepass.maplib.interfaces.ProgressShower;
import br.com.simplepass.maplib.interfaces.UpdatableMap;
import br.com.simplepass.mapsyncerlib.conection.MapSync;
import br.com.simplepass.maplib.interfaces.MapWrapperListener;

public class MainActivity extends AppCompatActivity implements
        MapWrapperListener,
        ProgressShower{

    MapWrapperFragment mMapWrapperFragment;

    @Override
    public void showProgress(boolean b) {
        if(b){
            Log.d("ShowProgress", "Mostra progresso!");
        } else {
            Log.d("ShowProgress", "Não mostra progresso!");
        }

    }

    @Override
    public void onMapReady() {
        mMapWrapperFragment =
                (MapWrapperFragment) getSupportFragmentManager().findFragmentById(br.com.simplepass.trackermap.R.id.main_fragment_map);

        mMapWrapperFragment.setMapSyncer(new MapSync());
        mMapWrapperFragment.startUpdates();
    }

    @Override
    public void onDestroy(){
        mMapWrapperFragment.stopUpdates();

        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(br.com.simplepass.trackermap.R.layout.activity_main);
    }
}
