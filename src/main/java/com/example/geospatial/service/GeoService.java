package com.example.geospatial.service;

import com.example.geospatial.dto.GatherLocation;
import com.example.geospatial.dto.UserLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.domain.geo.GeoReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.redis.connection.RedisGeoCommands.DistanceUnit.METERS;

@Service
@RequiredArgsConstructor
public class GeoService {

    public static final String key = "gather";
    private final GeoOperations<String, String> geoOperations;

    public void saveLocation(GatherLocation location) {
        geoOperations.add(key, location.getPoint(), location.getName());
    }

    public List<GatherLocation> searchByUser(UserLocation location, int radiusM) {
        GeoReference<String> reference = GeoReference.fromCoordinate(location.getPoint());

        Distance radius = new Distance(radiusM, METERS);

        RedisGeoCommands.GeoSearchCommandArgs args = RedisGeoCommands.GeoSearchCommandArgs
                .newGeoSearchArgs()
                .includeDistance()
                .includeCoordinates()
                .sortAscending()
                .limit(20);

        GeoResults<RedisGeoCommands.GeoLocation<String>> results = geoOperations.search(key, reference, radius, args);

        List<GatherLocation> list = new ArrayList<>();
        assert results != null;
        for (GeoResult<RedisGeoCommands.GeoLocation<String>> result : results) {
            RedisGeoCommands.GeoLocation<String> geoLocation = result.getContent();
            String name = geoLocation.getName();
            double distance = result.getDistance().getValue();

            list.add(GatherLocation.of(name, geoLocation.getPoint(), distance));
        }
        return list;
    }

}
