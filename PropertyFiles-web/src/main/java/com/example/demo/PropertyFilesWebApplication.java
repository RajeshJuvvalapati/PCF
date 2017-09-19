package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class PropertyFilesWebApplication {

	public static void main(String[] args) {
		
			SpringApplication.run(PropertyFilesWebApplication.class, args);
		}

		@Value("${message}")
		private String message;

		@RequestMapping("/")
		public String greetings(){
			return message;
		}

}
