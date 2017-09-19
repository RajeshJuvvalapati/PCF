package com.example.demo;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@EnableCircuitBreaker
@RestController
@SpringBootApplication
@EnableHystrixDashboard
public class GsCircuitBreakerApplication {

	
	@Autowired
	  private BookService bookService;

	  @Bean
	  public RestTemplate rest(RestTemplateBuilder builder) {
	    return builder.build();
	  }
	
	public static void main(String[] args) {
		SpringApplication.run(GsCircuitBreakerApplication.class, args);
	}
	
	
	 @HystrixCommand(fallbackMethod = "reliable")
	@RequestMapping("/to-read")
	  public String readingList() {
	    RestTemplate restTemplate = new RestTemplate();
	    URI uri = URI.create("http://localhost:8090/recommended");

	    return restTemplate.getForObject(uri, String.class);
	  }
	
	 public String reliable() {
		    return "Cloud Native Java (O'Reilly)";
		  }
	
}
