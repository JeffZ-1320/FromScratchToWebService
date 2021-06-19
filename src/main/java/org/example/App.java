package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.text.ParseException;

@SpringBootApplication
//@ComponentScan({"org.example"})
//@EntityScan("com.example.")
//@EnableJpaRepositories("org.example")
public class App
{
    public static void main( String[] args ) throws ParseException {
        SpringApplication.run(App.class, args);
    }
}
