package me.warriorg.spring.mongo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author warrior
 */
@Data
@Entity
@Table(name = "t_movies")
@JsonIgnoreProperties(
        ignoreUnknown = true,
        value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Movie {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String title;
    private long year;
}
