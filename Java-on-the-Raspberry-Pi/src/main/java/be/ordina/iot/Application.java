package be.ordina.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point.
 */
@SpringBootApplication
public class Application {

    /**
     * Used to boot the application.
     *
     * @param args Contains the arguments passed to the application, if any.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}