package himedia.phoneappspring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "himedia.phoneappspring.mappers")
@ComponentScan(basePackages = "himedia.phoneappspring")

public class PhoneAppSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoneAppSpringApplication.class, args);
	}

}
