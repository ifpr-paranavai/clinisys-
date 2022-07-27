package com.clinisys.clinisys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.clinisys.clinisys"})
public class ClinisysApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinisysApplication.class, args);
		System.out.print("teste");
	}

}
