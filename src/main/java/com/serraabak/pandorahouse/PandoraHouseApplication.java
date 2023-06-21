package com.serraabak.pandorahouse;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class PandoraHouseApplication {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {
		SpringApplication.run(PandoraHouseApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			String sql = String.format("INSERT INTO Items VALUES ('40e6215d-b5c6-4896-987c-f30f3678f608','example',0,0.0)");
			int rows = jdbcTemplate.update(sql);
			if (rows > 0) {
				System.out.println("A new row has been inserted.");
			}
		};
	}

}
