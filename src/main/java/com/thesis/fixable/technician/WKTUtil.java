package com.thesis.fixable.technician;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

final class WKTUtil {
    private static final WKTReader wktReader = new WKTReader(
            new GeometryFactory(new PrecisionModel(), 4326)
    );

    static Point createPoint(double lat, double longitude) {
        try {
            return (Point) wktReader.read(String.format("POINT (%f %f)", longitude, lat));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
