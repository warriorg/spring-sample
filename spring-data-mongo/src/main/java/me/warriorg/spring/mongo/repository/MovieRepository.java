package me.warriorg.spring.mongo.repository;

import me.warriorg.spring.mongo.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author warrior
 */
public interface MovieRepository extends MongoRepository<Movie, String> {
}
