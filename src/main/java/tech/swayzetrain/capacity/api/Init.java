package tech.swayzetrain.capacity.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan( {"tech.swayzetrain.capacity"} )
@EntityScan( {"tech.swayzetrain.capacity"} )
@EnableJpaRepositories( {"tech.swayzetrain.capacity"} )
@SpringBootApplication
public class Init {

	public static void main(String[] args) {
		SpringApplication.run(Init.class, args);
	}

}
