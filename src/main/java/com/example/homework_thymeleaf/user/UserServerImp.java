package com.example.homework_thymeleaf.user;

import com.example.homework_thymeleaf.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServerImp implements UserService{
    private final UserRepository userRepository;
    @Override
    public List<User> findAllUser() {
        return userRepository.findAllUser();
    }
    @Override
    public User findUserById(String id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }


}
