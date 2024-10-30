package com.momentum.momentum;

import org.springframework.boot.SpringApplication;

public class TestMomenTumBackendApplication {

	public static void main(String[] args) {
		SpringApplication.from(MomenTumBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
