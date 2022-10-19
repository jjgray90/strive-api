package me.jjgray.strive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class StriveApplication {

	public static void main(String[] args) {
		SpringApplication.run(StriveApplication.class, args);
	}

}
