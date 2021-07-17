package me.warriorg.spring.mongo;


import lombok.extern.slf4j.Slf4j;
import me.warriorg.spring.mongo.model.Movie;
import me.warriorg.spring.mongo.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author warrior
 */
@Slf4j
@SpringBootApplication
public class ElasticsearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class);
    }


    @Bean
    public CommandLineRunner demo(MovieRepository repository) {
        return (args) -> {
            Iterable<Movie> list = repository.findAll();
            list.forEach(it -> {
                System.out.println(it);
            });
        };
    }
}
