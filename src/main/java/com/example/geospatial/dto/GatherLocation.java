package com.example.geospatial.dto;

import lombok.Getter;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.domain.geo.GeoLocation;

@Getter
public class GatherLocation extends GeoLocation<String> {

    private Double distance;

    private GatherLocation(String name, Point point) {
        super(name, point);
    }

    private GatherLocation(String name, Point point, Double distance) {
        super(name, point);
        this.distance = distance;
    }

    public static GatherLocation of(String name, Double longitude, Double latitude) {
        Point point = new Point(longitude, latitude);
        return new GatherLocation(name, point);
    }

    public static GatherLocation of(String name, Point point, Double distance) {
        return new GatherLocation(name, point, distance);
    }

    public String toString() {
        return String.format("%s (%.6f, %.6f)", getName(), getPoint().getX(), getPoint().getY());
    }
}
