package com.springapp.mvc;

import com.springapp.mvc.entity.Location;
import com.springapp.mvc.repository.LocationRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;

import static org.junit.Assert.*;

import java.util.List;

/**
 * Created by Taras S. on 27.01.15.
 */
public class MongoDbGeoSpatialTest extends AppTests {

    private final Point point = new Point(50.432578, 30.61291);

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    MongoTemplate template;

    @Before
    public void setUp() {
        super.setup();

        template.indexOps(Location.class).ensureIndex( new GeospatialIndex("position") );

        locationRepository.save(new Location("Work", 50.414773, 30.519687));
        locationRepository.save(new Location("Stadion", 50.450537, 30.535116));
        locationRepository.save(new Location("Hidropark", 50.439387, 30.576830));
        locationRepository.save(new Location("Village", 50.385128, 30.790376));
    }

    @Test
    public void findHidropark() {
        List<Location> locations = locationRepository.findByPositionNear(point, new Distance(5, Metrics.KILOMETERS));
        assertLocations(locations, "Hidropark");
    }

    @Test
    public void findHidroparkAndStadion() {
        List<Location> locations = locationRepository.findByPositionNear(point, new Distance(10, Metrics.KILOMETERS));
        assertLocations(locations, "Hidropark", "Stadion");
    }

//    @Test
//    public void findAroundOriginTest() {
//        List<Location> locations = locationRepository.findByPositionWithin(new Circle(0, 0, 0.75));
//        assertLocations(locations, "A", "C", "D");
//    }
//
//    @Test
//    public void findWithinBox() {
//        List<Location> locations = locationRepository.findByPositionWithin(new Box(new Point(0.25, 0.25), new Point(1, 1)));
//        assertLocations(locations, "B", "C");
//    }

    public void assertLocations(List<Location> locations, String... names) {
        getLog().info("******Found******");
        locations.forEach(location -> getLog().info(location.getName()));
        assertEquals(names.length, locations.size());
        int i = 0;
        for (String name: names) {
            assertEquals(name, locations.get(i++).getName());
        }
    }
}
