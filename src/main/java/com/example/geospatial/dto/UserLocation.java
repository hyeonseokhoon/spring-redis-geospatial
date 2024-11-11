package com.example.geospatial.dto;

import org.springframework.data.geo.Point;
import org.springframework.data.redis.domain.geo.GeoLocation;

public class UserLocation extends GeoLocation<String> {
    private UserLocation(String name, Point point) {
        super(name, point);
    }

    public static UserLocation of(String name, Double longitude, Double latitude) {
        Point point = new Point(longitude, latitude);
        return new UserLocation(name, point);
    }

}
