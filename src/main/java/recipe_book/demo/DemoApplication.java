package recipe_book.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "recipe_book.demo.model")
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}




// TODO: Logging for all methods but keep simple
// TODO: Rate Limiter
// TODO: Postman Recipe Category


// https://dribbble.com/shots/20792040-Cookpedia-Food-Recipe-Mobile-App