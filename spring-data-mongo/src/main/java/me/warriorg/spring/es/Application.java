package me.warriorg.spring.es;


import me.warriorg.spring.es.model.Movie;
import me.warriorg.spring.es.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @author warrior
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }


    @Bean
    public CommandLineRunner demo(MovieRepository repository) {
        return (args) -> {
            Movie movie = new Movie();
            movie.setTitle("勇敢的心");
            movie.setYear(2002);
            repository.save(movie);

            Iterable<Movie> list = repository.findAll();
            list.forEach(it -> {
                System.out.println(it);
            });
        };
    }
}
