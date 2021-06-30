package me.warriorg.spring.jpa.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author warrior
 */
@Data
@Document(indexName = "movies")
public class Movie {

    @Id
    private String id;
    private String title;
    private long year;
}
