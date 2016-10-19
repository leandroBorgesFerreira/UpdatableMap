package br.com.simplepass.mapsyncerlib.conection;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import br.com.simplepass.maplib.domain.Car;
import br.com.simplepass.maplib.interfaces.MapPoint;
import br.com.simplepass.maplib.interfaces.MapSyncer;
import br.com.simplepass.mapsyncerlib.retrofit.MapClient;
import br.com.simplepass.mapsyncerlib.retrofit.ServiceGenerator;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Classe de sincronização com o mapa. Aqui é pedido ao servidor a posição das vans e depois a
 * classe dispara o método da classe PointsDrawer que a invocou.
 */
public class MapSync implements MapSyncer<Car> {

    private static final int UPDATE_FREQUENCY_SECONDS = 5; //5 segungos

    public Observable<Collection<Car>> getCarsObservable(){
        return Observable.interval(UPDATE_FREQUENCY_SECONDS, TimeUnit.SECONDS)
                .flatMap(n -> ServiceGenerator.createService(MapClient.class).getCars().map(Map::values))
                .repeat()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /*public Observable<Collection<Car>> getCarsObservable(){
        return Observable.create(new Observable.OnSubscribe<Collection<Car>>() {
            @Override
            public void call(Subscriber<? super Collection<Car>> subscriber) {
                List<Car> carList = new ArrayList<>();

                carList.add(new Car(1, -19.5, -43.6));
                carList.add(new Car(2, -20.5, -44.7));
                carList.add(new Car(3, -21.5, -45.8));

                subscriber.onNext(carList);
            }
        });
    }*/
}
