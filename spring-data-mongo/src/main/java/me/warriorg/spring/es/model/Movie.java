package me.warriorg.spring.es.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author warrior
 */
@Data
@Document("movies")
public class Movie {

    @Id
    private String id;
    private String title;
    private long year;
}
