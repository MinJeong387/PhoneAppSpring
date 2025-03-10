package himedia.phoneappspring.restserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(basePackages = "com.commit.mappers")
//@ComponentScan(basePackages = { "com.commit" })
public class PhoneAppSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoneAppSpringApplication.class, args);
	}

}
