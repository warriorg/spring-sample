package me.warriorg.spring.mongo.repository;

import me.warriorg.spring.mongo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author warrior
 */
public interface MovieRepository extends JpaRepository<Movie, String> {
}
