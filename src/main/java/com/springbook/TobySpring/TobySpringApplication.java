package com.springbook.TobySpring;

import com.springbook.TobySpring.one.one.DaoFactory;
import com.springbook.TobySpring.one.one.UserDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class TobySpringApplication {

	public static void main(String[] args) {
//		SpringApplication.run(TobySpringApplication.class, args);
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
	}

}
