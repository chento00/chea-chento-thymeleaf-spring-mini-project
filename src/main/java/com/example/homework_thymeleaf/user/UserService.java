package com.example.homework_thymeleaf.user;

import java.util.List;

public interface UserService {
    List<User> findAllUser();
    User findUserById(String id);
    void addUser(User user);
    void deleteUserById(String id);
    void updateUserById(String id);
}
