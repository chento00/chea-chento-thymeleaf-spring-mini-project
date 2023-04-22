package com.example.homework_thymeleaf.category;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
    private  List<Category> categoryList;
    private Faker faker;
    @Autowired
    void setFaker(Faker faker){
        this.faker=faker;
    }
    List<Category> findAllCategory(){
        return categoryList;
    }
    Category findById(String id){
        return categoryList.stream().
                filter(category -> category.getUuid().equals(id)).
                findFirst().
                orElse(null);
    }
    @PostConstruct
    void init(){
        categoryList=new ArrayList<>(){{
            add(new Category(
                    UUID.randomUUID().toString(),
                    "Education"
            ));
            add(new Category(
                    UUID.randomUUID().toString(),
                    "Sport"
            ));
            add(new Category(
                    UUID.randomUUID().toString(),
                    "Lovely"
            ));
            add(new Category(
                    UUID.randomUUID().toString(),
                    "Science"
            ));
        }};
    }

    public void addCategory(Category category) {
        categoryList.add(category);
    }
}
