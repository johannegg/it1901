package restserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookTrackerApplication {
    /**
     * Spring Boot Application Class. Run "mvn spring-boot:run" from fra
     * "bookTraker/restserver" to start. The server runs on localhost:8080
     */
    public static void main(String[] args) {
        SpringApplication.run(BookTrackerApplication.class, args);
    }

}
