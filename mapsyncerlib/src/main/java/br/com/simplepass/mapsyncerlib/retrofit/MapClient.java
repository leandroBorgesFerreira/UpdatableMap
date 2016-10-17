package br.com.simplepass.mapsyncerlib.retrofit;



import java.util.Map;

import br.com.simplepass.maplib.domain.Car;
import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by leandro on 3/7/16.
 */
public interface MapClient {
    @GET("vans")
    Observable<Map<Long, Car>> getCars();

}
