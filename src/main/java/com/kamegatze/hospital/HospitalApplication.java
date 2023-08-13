package com.kamegatze.hospital;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.logging.LogManager;

@SpringBootApplication
public class HospitalApplication implements CommandLineRunner {

	@Override
	public void run(String... args) throws IOException {
		LogManager.getLogManager().readConfiguration(
				HospitalApplication.class.getResourceAsStream("/application.properties")
		);

	}

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}
}