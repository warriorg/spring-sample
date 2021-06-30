package me.warriorg.spring.jpa;


import me.warriorg.spring.jpa.model.Movie;
import me.warriorg.spring.jpa.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
