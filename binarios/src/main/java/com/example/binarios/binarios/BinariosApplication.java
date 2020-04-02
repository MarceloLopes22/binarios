package com.example.binarios.binarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.binarios.binarios"})
//@ComponentScan(basePackageClasses = {DocumentoService.class, DocumentoRestController.class})
//@EnableJpaRepositories(basePackageClasses = {DocumentoRepository.class})
public class BinariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(BinariosApplication.class, args);
	}

}
