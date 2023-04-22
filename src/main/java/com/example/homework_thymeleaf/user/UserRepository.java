package com.example.homework_thymeleaf.user;

import com.example.homework_thymeleaf.category.Category;
import com.example.homework_thymeleaf.post.Post;
import com.example.homework_thymeleaf.post.PostController;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Getter
@RequiredArgsConstructor
public class UserRepository {
    private Faker faker;
    private List<User> userList;
//    private List<ViewUserDTO> viewUser;
    @Autowired
    void setFaker(Faker faker){
        this.faker=faker;
    }
    public List<User> findAllUser(){
        return userList;
    }
    public User findUserById(String id){
        return userList.stream().
                filter(user -> user.getUuid().equals(id)).
                findFirst().
                orElse(null);
    }
    void addUser(User user){
        userList.add(user);
    }
    @PostConstruct
    void init(){
        userList=new ArrayList<>(){{
            String name[]=faker.name().fullName().split(" ");
            add(new User(
                    UUID.randomUUID().toString(),
                    name[0]
                    ,faker.lorem().paragraph(),
                    (faker.name().title().contains("Mr."))?"Male":"Female",
                    faker.address().streetName()+","+faker.address().city()+","+faker.address().state()+","+faker.address().zipCode(),
                    faker.internet().emailAddress(),
                    "1.jpg"
            ));
            add(new User(
                    UUID.randomUUID().toString(),
                    faker.name().fullName()
                    ,faker.lorem().paragraph(),
                    (faker.name().title().contains("Mr."))?"Male":"Female",
                    faker.address().streetName()+","+faker.address().city()+","+faker.address().state()+","+faker.address().zipCode(),
                    faker.internet().emailAddress(),
                    "2.jpg"
            ));
            add(new User(
                    UUID.randomUUID().toString(),
                    faker.name().fullName()
                    ,faker.lorem().paragraph(),
                    (faker.name().title().contains("Mr."))?"Male":"Female",
                    faker.address().streetName()+","+faker.address().city()+","+faker.address().state()+","+faker.address().zipCode(),
                    faker.internet().emailAddress(),
                    "3.jpg"
            ));
        }};
//        viewUser=new ArrayList<>(){{
//            for(User userlst: userList){
//                add(new ViewUserDTO(
//                        userlst.getName(),
//                        userlst.getDescription(),
//                        userlst.getImage()
//                ));
//            }
//        }};

    }
}
