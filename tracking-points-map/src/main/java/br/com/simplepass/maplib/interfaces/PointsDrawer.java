package br.com.simplepass.maplib.interfaces;

import java.util.Iterator;
import java.util.Map;


/**
 * Created by leandro on 11/18/15.
 */
public interface PointsDrawer {
    void updateMovingPoints(Iterable<? extends MapPoint> points);
    void updateMovingPoints(MapPoint mapPoint);
    void drawFixedPoints(Iterable<? extends MapPoint> points);
}
