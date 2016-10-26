
# UpdatableMap
This is a MapWrapper on top of GoogleMap for developers build apps for track moving points (like Uber, Cabify, EasyTaxi...) more easily and with a better code design

# How to Use
###1 - Include  de MapFragment
Put the fragment in the activity layout.

	    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:tools="http://schemas.android.com/tools"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
    tools:context="br.com.simplepass.trackermap.MainActivity">

    <fragment
        android:id="@+id/main_fragment_map"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        class="br.com.simplepass.maplib.fragment.MapWrapperFragment"/>
        </RelativeLayout>

### 2 - Implement the MapWrapperListener
You activity mus implement MapWrapperListener


    public class MainActivity extends AppCompatActivity implements
            MapWrapperListener,
            ProgressShower{
    
    MapWrapperFragment mMapWrapperFragment;
    
    @Override
    public void onMapReady() {
        //Put code here - I will explain this in the section 5
    }

### 3 - Implement MapPoint
You must track a Class the implements MapPoint like:

    public class Car implements MapPoint {
    
    private long vanId;
        private double latitude;
        private double longitude;
    
    [...]
    
    public long getId() {return vanId;}
    
    @Override
        public double getLatitude() {return latitude;}
        
    @Override
        public double getLongitude() {return longitude;}

### 4 - Create a MapSync
The MapSync is a interface based on RxJava, so you need to return a Observable.

Like this: 

   

     public class MapSync implements MapSyncer<Car> {
        
        private static final int UPDATE_FREQUENCY_SECONDS = 5; //5 seconds
        
        public Observable<Collection<Car>> getCarsObservable(){
    /* Here I am consulting the webserver with Retrofit, but you can use you favorite library  */
            return Observable.interval(UPDATE_FREQUENCY_SECONDS, TimeUnit.SECONDS)
                    .flatMap(n -> ServiceGenerator.createService(MapClient.class).getCars().map(Map::values))
                    .repeat()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }

note: If you are having a hard time to write your Retrofit code, you can look in the repo to find a exemple =]

### 5 - Implement the onMapReady logic

    @Override
    public void onMapReady() {
            mMapWrapperFragment =(MapWrapperFragment) getSupportFragmentManager().findFragmentById(br.com.simplepass.trackermap.R.id.main_fragment_map);
    
            mMapWrapperFragment.setMapSyncer(new MapSync());
            mMapWrapperFragment.startUpdates();
        }

### 6 - You're done!
But if you don't want to implement te MapSync, you can you you these methods:

    void updateMovingPoints(Iterable<? extends MapPoint> points);
    
    void updateMovingPoints(MapPoint mapPoint);
    
    void drawFixedPoints(Iterable<? extends MapPoint> points);

#Instalation

    dependencies {
	    compile 'br.com.simplepass:tracking-points-map:0.8.0'
		/* If you want to use Retrofit, use this */
		compile 'com.squareup.retrofit2:retrofit:2.1.0'
	    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
	    compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
    }

# Bugs and Feedback


For bugs, feature requests, and discussion please use [GitHub Issues](https://github.com/leandroBorgesFerreira/UpdatableMap/issues)

#Have Fun!
