package me.warriorg.spring.jpa.repository;

import me.warriorg.spring.jpa.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author warrior
 */
public interface MovieRepository extends MongoRepository<Movie, String> {
}
