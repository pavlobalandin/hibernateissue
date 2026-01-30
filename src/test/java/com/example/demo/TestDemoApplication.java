package com.example.demo;

import org.springframework.boot.SpringApplication;

import com.example.demo.config.PostgreSQLTestContainersConfig;

public class TestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.from(DemoApplication::main).with(PostgreSQLTestContainersConfig.class).run(args);
	}

}
