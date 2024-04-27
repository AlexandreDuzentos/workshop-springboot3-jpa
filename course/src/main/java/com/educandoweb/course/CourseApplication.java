package com.educandoweb.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* 
 * @SpringBootApplication - Annotation do spring boot para
 * configurá-lo para que ele seja uma aplicação do spring boot.
 * 
 * O Spring faz muito o uso de annotations para configurar o nosso
 * código, a annotation faz o processamento por baixo dos panos
 * na hora de compilar o seu código fonte.
 * */
@SpringBootApplication
public class CourseApplication {

	public static void main(String[] args) {
		/* comando que roda a aplicação do spring boot */
		SpringApplication.run(CourseApplication.class, args);
	}

}
