package com.example.homework_thymeleaf.utils;

import com.example.homework_thymeleaf.category.CategoryController;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonBean {
    @Bean
    Faker faker(){
        return new Faker();
    }
}
