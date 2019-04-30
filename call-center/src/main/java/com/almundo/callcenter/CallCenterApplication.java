package com.almundo.callcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The Class CallCenterApplication is responsible to starts the application.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
@EnableEurekaClient
public class CallCenterApplication {

	/**
	 * Main method that starts the spring application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(CallCenterApplication.class, args);
	}
}
