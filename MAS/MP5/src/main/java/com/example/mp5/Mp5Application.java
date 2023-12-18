package com.example.mp5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.mp5.repository.DormitoryRepository;
import com.example.mp5.model.Dormitory;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class Mp5Application {

	public static void main(String[] args) {
		SpringApplication.run(Mp5Application.class, args);
	}

}
