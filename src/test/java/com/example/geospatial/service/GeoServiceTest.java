package com.example.geospatial.service;

import com.example.geospatial.dto.GatherLocation;
import com.example.geospatial.dto.UserLocation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class GeoServiceTest {
    @Autowired GeoService geoService;

    // 내 위치 37.7097976, 127.1185073

    @Test
    public void test() {
        geoService.saveLocation(GatherLocation.of("test1", 127.1184035, 37.7086833));
        geoService.saveLocation(GatherLocation.of("test2", 127.1190244, 37.7086114));
        geoService.saveLocation(GatherLocation.of("test3", 127.117813, 37.7081866));
        geoService.saveLocation(GatherLocation.of("test4", 127.1163024, 37.7089644));
        geoService.saveLocation(GatherLocation.of("test5", 127.1153517, 37.7090816));
        geoService.saveLocation(GatherLocation.of("test6", 127.11523, 37.7084242));
        geoService.saveLocation(GatherLocation.of("test7", 127.1209382, 37.7076638));
        geoService.saveLocation(GatherLocation.of("test8", 127.1213076, 37.7065348));

        List<GatherLocation> list = geoService.searchByUser(
                UserLocation.of("my_location", 127.1185073, 37.7097976), 300);

        for (GatherLocation gatherLocation : list) {
            System.out.println(gatherLocation);
        }
    }
}
