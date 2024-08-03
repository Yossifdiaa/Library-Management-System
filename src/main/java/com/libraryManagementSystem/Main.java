package com.libraryManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/")
    public GreetResponse greet() {
        return new GreetResponse(
            "Hello World" , 20 , new Person( "fekry" , 20 , 20.00)
        );
    }
    record Person(String name , int age , double savings){}
    record GreetResponse(
        String greet,
        int age,
        Person person
    ) {}

}

