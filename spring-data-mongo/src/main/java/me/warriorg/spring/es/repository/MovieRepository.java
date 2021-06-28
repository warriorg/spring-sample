package me.warriorg.spring.es.repository;

import me.warriorg.spring.es.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author warrior
 */
public interface MovieRepository extends MongoRepository<Movie, String> {
}
