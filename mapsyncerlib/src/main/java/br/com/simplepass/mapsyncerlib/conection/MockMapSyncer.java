package br.com.simplepass.mapsyncerlib.conection;


/**
 * Created by hinovamobile on 13/09/16.
 */
public class MockMapSyncer{

    /*private Handler mHandler;
    private Map<Long ,? extends MapPoint> mMapPoints;
    private Runnable mRunnable;

    public MockMapSyncer(MapSyncListener mapSyncListener, Map<Long, ? extends MapPoint> mockList) {
        super(mapSyncListener);

        mHandler = new Handler();
        mMapPoints = mockList;

        mRunnable = new Runnable() {

            @Override
            public void run() {
                for(Map.Entry<Long ,? extends MapPoint> entry : mMapPoints.entrySet()){
                    MapPoint mapPoint = entry.getValue();

                    mapPoint.setLatitude(mapPoint.getLatitude() + 0.01);
                }

                sendUpdate(mMapPoints);

                mHandler.postDelayed(this, 6000);
            }
        };
    }

    @Override
    public void start() {
        mHandler.post(mRunnable);
    }

    @Override
    public void stop() {
        mHandler.removeCallbacks(mRunnable);
    }
    */
}
