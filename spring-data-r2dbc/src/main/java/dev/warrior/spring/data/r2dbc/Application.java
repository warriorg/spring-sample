package dev.warrior.spring.data.r2dbc;

import dev.warrior.spring.data.r2dbc.converter.MoneyReadConverter;
import dev.warrior.spring.data.r2dbc.converter.MoneyWriteConverter;
import dev.warrior.spring.data.r2dbc.model.Coffee;
import dev.warrior.spring.data.r2dbc.repository.CoffeeRepository;
import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.R2dbcDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;


@Slf4j
@SpringBootApplication
//@EnableR2dbcRepositories
public class Application extends AbstractR2dbcConfiguration implements ApplicationRunner {

//    @Autowired
//    private CoffeeRepository repository;



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
//        ConnectionFactory connectionFactory = ConnectionFactories.get("r2dbc:h2:mem:///test?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
//        return connectionFactory;
        return new H2ConnectionFactory(
                H2ConnectionConfiguration.builder()
                        .inMemory("db")
                        .username("sa")
                        .build());
    }

    @Bean
    @Override
    public R2dbcCustomConversions r2dbcCustomConversions() {
        R2dbcDialect dialect = getDialect(connectionFactory());
        CustomConversions.StoreConversions storeConversions =
                CustomConversions.StoreConversions.of(dialect.getSimpleTypeHolder());
        return new R2dbcCustomConversions(storeConversions,
                Arrays.asList(new MoneyReadConverter(), new MoneyWriteConverter()));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        CountDownLatch cdl = new CountDownLatch(2);

//        repository.findAllById(Flux.just(1L, 2L))
//                .map(c -> c.getName() + "-" + c.getPrice().toString())
//                .doFinally(s -> cdl.countDown())
//                .subscribe(c -> log.info("Find {}", c));
//
//        repository.findByName("mocha")
//                .doFinally(s -> cdl.countDown())
//                .subscribe(c -> log.info("Find {}", c));

        cdl.await();
    }

    private void client() throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(2);
        R2dbcEntityTemplate template = new R2dbcEntityTemplate(connectionFactory());

//        client.sql("select * from t_coffee")
//                .as(Coffee.class)
//                .fetch()
//                .first()
//                .doFinally(s -> cdl.countDown())
////				.subscribeOn(Schedulers.elastic())
//                .subscribe(c -> log.info("Fetch execute() {}", c));
//
//        template.select(Coffee.class)
//                .from("t_coffee")
//                .sort(Sort.by(Sort.Direction.DESC, "id"))
//                .page(PageRequest.of(0, 3))
//                .as(Coffee.class)
//                .fetch()
//                .all()
//                .doFinally(s -> cdl.countDown())
////				.subscribeOn(Schedulers.elastic())
//                .subscribe(c -> log.info("Fetch select() {}", c));

        log.info("After Starting.");
        cdl.await();
    }
}
