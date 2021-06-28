package me.warriorg.spring.es.repository;

import me.warriorg.spring.es.model.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author warrior
 */
public interface MovieRepository extends ElasticsearchRepository<Movie, String> {
}
