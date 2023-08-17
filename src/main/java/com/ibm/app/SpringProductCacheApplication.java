package com.ibm.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringProductCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringProductCacheApplication.class, args);
	}

}