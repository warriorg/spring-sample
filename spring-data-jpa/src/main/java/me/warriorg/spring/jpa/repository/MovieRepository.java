package me.warriorg.spring.jpa.repository;

import me.warriorg.spring.jpa.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author warrior
 */
public interface MovieRepository extends JpaRepository<Movie, String> {
}
