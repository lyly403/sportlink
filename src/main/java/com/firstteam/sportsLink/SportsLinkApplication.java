package com.firstteam.sportsLink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.context.config.annotation.EnableContextInstanceData;
import org.springframework.context.annotation.ComponentScan;

@EnableContextInstanceData
@SpringBootApplication
public class SportsLinkApplication {
	public static void main(String[] args) {
		SpringApplication.run(SportsLinkApplication.class, args);
	}
}
