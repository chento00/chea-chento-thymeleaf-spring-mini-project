package com.example.homework_thymeleaf.post;

import com.example.homework_thymeleaf.upload.FileUpload;
import com.example.homework_thymeleaf.upload.FileUploadService;
import com.example.homework_thymeleaf.user.User;
import com.example.homework_thymeleaf.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImp implements PostService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;
    @Override
    public List<Post> findAllPost() {
        return postRepository.findAllPost();
    }

    @Override
    public List<Post> findByCategory(String category) {
        return postRepository.findByCategory(category);
    }

    @Override
    public List<Post> findPostByUserId(String id) {
        return postRepository.findPostByUserId(id);
    }

    @Override
    public void removePost(String id) {
        postRepository.removePostByUserId(id);
    }

    @Override
    public Post findPostById(String id) {
        return postRepository.findPostById(id);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAllUser();
    }

    @Override
    public User findUserById(String id) {
        return postRepository.findUserById(id);
    }

    @Override
    public boolean addPost(Post post, MultipartFile file) {
        FileUpload fileUpload=fileUploadService.uploadSingle(file);
        if(fileUpload.isSuccess()){
            post.setUuid(UUID.randomUUID().toString());
            post.setImage(fileUpload.filename());
            postRepository.getPostList().add(0,post);
        }
        return false;
    }

    @Override
    public boolean updatePost(Post post, String id, MultipartFile file) {
        FileUpload fileUpload=fileUploadService.uploadSingle(file);
        if(fileUpload.isSuccess()){
            post.setUuid(id);
            post.setImage(fileUpload.filename());
            postRepository.updatePostById(id,post);
        }
        return false;
    }

    @Override
    public void updatePostEmptyFile(Post post, String id) {
        post.setUuid(id);
         postRepository.updatePostById(id,post);
    }
}
