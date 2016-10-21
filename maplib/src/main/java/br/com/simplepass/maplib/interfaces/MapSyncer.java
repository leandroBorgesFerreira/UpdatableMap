package br.com.simplepass.maplib.interfaces;

import java.util.Collection;

import br.com.simplepass.maplib.domain.Car;
import rx.Observable;

/**
 * Created by hinovamobile on 13/09/16.
 *
 * Interface that need to be passed to MapWrapperFragment so it can automatically update the MapPoints.
 *
 */
public interface MapSyncer<T extends MapPoint> {
    Observable<Collection<T>> getCarsObservable();
}
