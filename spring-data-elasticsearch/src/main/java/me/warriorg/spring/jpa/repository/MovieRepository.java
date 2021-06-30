package me.warriorg.spring.jpa.repository;

import me.warriorg.spring.jpa.model.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author warrior
 */
public interface MovieRepository extends ElasticsearchRepository<Movie, String> {
}
