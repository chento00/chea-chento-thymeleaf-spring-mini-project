package com.example.homework_thymeleaf.post;

import com.example.homework_thymeleaf.category.Category;
import com.example.homework_thymeleaf.category.CategoryController;
import com.example.homework_thymeleaf.user.User;
import com.example.homework_thymeleaf.user.UserRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Getter
public class PostRepository {
    private Faker faker;
    private List<Category> categoryList;
    private  List<Post> postList;
    private final CategoryController categoryController;
    private final UserRepository userRepository;
    public PostRepository(CategoryController categoryController, UserRepository userRepository) {
        this.categoryController = categoryController;
        this.userRepository = userRepository;
    }
    public User findUserById(String id){
        return  userRepository.findUserById(id);
    }
    public List<Post> findPostByUserId(String id){
        return postList.stream().filter(post -> post.getUser().getUuid().equalsIgnoreCase(id)).collect(Collectors.toList());
    }
    public void removePostByUserId(String uuid){
        postList=postList.stream().filter(post -> !post.getUuid().equalsIgnoreCase(uuid)).collect(Collectors.toList());
    }
    public Post findPostById(String id){
        return postList.stream().filter(post -> post.getUuid().equalsIgnoreCase(id)).findFirst().orElse(null);
    }
    @Autowired
    public void setFaker(Faker faker) {
        this.faker = faker;
    }
    List<Post> findAllPost(){
        return postList;
    }
    @PostConstruct
    void init(){
        categoryList=new ArrayList<>();
        categoryList.add(categoryController.findAllCategory().get(0));
        categoryList.add(categoryController.findAllCategory().get(1));
        List<Category> list1=new ArrayList<>();
        list1.add(categoryController.findAllCategory().get(1));
        list1.add(categoryController.findAllCategory().get(3));
        List<Category> list2=new ArrayList<>();
        list2.add(categoryController.findAllCategory().get(0));
        list2.add(categoryController.findAllCategory().get(2));
        postList=new ArrayList<>(){{
            add(new Post(
                    UUID.randomUUID().toString(),
                    faker.book().title(),
                    faker.lorem().paragraph(),
                    "4.jpg",
                    list1,
                    userRepository.findAllUser().get(0)
            ));

            add(new Post(
                    UUID.randomUUID().toString(),
                    faker.book().title(),
                    faker.lorem().paragraph(),
                    "5.jpg",
                    list2,
                    userRepository.findAllUser().get(1)
            ));
            add(new Post(
                    UUID.randomUUID().toString(),
                    faker.book().title(),
                    faker.lorem().paragraph(),
                    "6.jpg",
                    list1,
                    userRepository.findAllUser().get(2)
            ));
        }};
    }
    List<Post> findByCategory(String cate){
        return postList.stream().
                filter(post -> post.getCategoryList().stream().
                        anyMatch(category -> category.getTitle().equalsIgnoreCase(cate))).
                collect(Collectors.toList());
//        List<Post> list=new ArrayList<>();
//        for(Post post:postList){
//            for(Category category:post.getCategoryList()){
//                if(category.getTitle().equalsIgnoreCase(cate)){
//                    list.add(post);
//                }
//            }
//        }
    }
    void addPost(Post post) {
        postList.add(post);
    }
    void updatePostById(String id,Post post){
        for (Post p : postList) {
            if (p.getUuid().equalsIgnoreCase(id)) {
                postList.set(postList.indexOf(p), post);
                System.out.println("success");
                break;
            }
        }
    }
}
