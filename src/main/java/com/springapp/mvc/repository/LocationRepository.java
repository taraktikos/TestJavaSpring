package com.springapp.mvc.repository;

import com.springapp.mvc.entity.Location;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Taras S. on 27.01.15.
 */
public interface LocationRepository extends MongoRepository<Location, String> {

    List<Location> findByPositionNear(Point p, Distance d);

    List<Location> findByPositionWithin(Circle c);

    List<Location> findByPositionWithin(Box b);

}
