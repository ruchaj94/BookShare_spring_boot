package com.example;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@SpringBootApplication
@ComponentScan("com/example")
public class Demo1SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Demo1SpringBootApplication.class, args);
	}

}
