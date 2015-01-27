package com.springapp.mvc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;


/**
 * Created by taras on 27.01.15.
 */
public class Location {

    @Id
    private String id;

    private String name;

    @GeoSpatialIndexed
    private Point position;

    public Location() {}

    public Location(String name, double lat, double lon) {
        this.name = name;
        this.position = new Point(lat, lon);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Point getPosition() {
        return position;
    }
}
