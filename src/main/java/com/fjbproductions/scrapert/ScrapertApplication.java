package com.fjbproductions.scrapert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:beans.xml"})
public class ScrapertApplication {

	public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(ScrapertApplication.class, args);
	}
}