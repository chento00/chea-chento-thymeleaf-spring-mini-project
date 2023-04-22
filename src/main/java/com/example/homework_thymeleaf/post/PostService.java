package com.example.homework_thymeleaf.post;

import com.example.homework_thymeleaf.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    List<Post> findAllPost();
    List<Post>findByCategory(String category);
    public List<Post> findPostByUserId(String id);
    void removePost(String id);
    Post findPostById(String id);
    List<User> findAllUser();
    User findUserById(String id);
    boolean addPost(Post post, MultipartFile file);
    boolean updatePost(Post post,String id,MultipartFile file);
    void updatePostEmptyFile(Post post,String id);
}

