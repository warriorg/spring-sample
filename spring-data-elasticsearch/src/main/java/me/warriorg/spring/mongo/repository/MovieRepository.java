package me.warriorg.spring.mongo.repository;

import me.warriorg.spring.mongo.model.Movie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author warrior
 */
public interface MovieRepository extends ElasticsearchRepository<Movie, String> {
}
