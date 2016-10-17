package br.com.simplepass.maplib.interfaces;

import java.util.Map;


/**
 * Created by leandro on 11/18/15.
 */
public interface PointsDrawer {
    void updateMovingPoints(Map<Long, ? extends MapPoint> points);
    void updateMovingPoints(MapPoint mapPoint);
    void drawFixedPoints(Map<Long, ? extends MapPoint> points);
}
