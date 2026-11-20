package org.treatments.platform.u20211c201;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Bootstrap class for the Open Treatment platform application.
 *
 * @author Apellidos, Nombres
 */
@EnableJpaAuditing
@SpringBootApplication
public class Pc216692u20211c201pruebaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Pc216692u20211c201pruebaApplication.class, args);
    }

}