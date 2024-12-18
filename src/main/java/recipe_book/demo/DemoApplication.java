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



// TODO: Comment ++
// TODO: Follow ++
// TODO: Verify Email !!! (Email HTML format )

// TODO: ADD COMMENT REQUESTS To POSTMAN !
