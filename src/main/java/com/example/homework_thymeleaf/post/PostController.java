package com.example.homework_thymeleaf.post;

import com.example.homework_thymeleaf.category.Category;
import com.example.homework_thymeleaf.category.CategoryController;
import com.example.homework_thymeleaf.user.User;
import com.example.homework_thymeleaf.user.UserController;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.unit.DataSize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final CategoryController categoryController;
    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;
    @GetMapping("/view-post")
    public ModelAndView viewPost(@RequestParam(value = "category", required = false ,defaultValue = "") String category){
        ModelAndView modelAndView = new ModelAndView();
        if(!category.isEmpty()){
            modelAndView.addObject("posts",postService.findByCategory(category));
            modelAndView.setViewName("page/post-by-cate");
        }else{
            modelAndView.setViewName("page/index");
            modelAndView.addObject("posts", postService.findAllPost());
            modelAndView.addObject("categories",categoryController.findAllCategory());
            modelAndView.addObject("postController",postService);
        }
        System.out.println(postService.findAllPost().size());
        return modelAndView;
    }
    public List<Post> findPostByUserId(String id){
        return postService.findPostByUserId(id);
    }
    public List<Post> findAllPost(){
        return postService.findAllPost();
    }
    @PostMapping("/delete-post")
    public String handleFormSubmission(@RequestParam("id") String id) {
        postService.removePost(id);
        return "redirect:/view-post";
    }
    @GetMapping("/view-post/{id}")
    String viewPostDetail(@PathVariable("id") String id, Model model){
        model.addAttribute("post",postService.findPostById(id));
        return "page/view-post-detail";
    }
    @GetMapping("/add-post")
    String viewAddPost(Post post, Model model){
        model.addAttribute("post",post);
        model.addAttribute("categories",categoryController.findAllCategory());
        model.addAttribute("users",postService.findAllUser());
        return "page/add-post";
    }
    @PostMapping("/add-post")
    String addPost(@ModelAttribute @Valid Post post ,
                   BindingResult result,
                   @RequestParam("userId") String userId,
                   @RequestParam("categoryID") ArrayList<String> listCateID,
                   @RequestParam("thumbnail") MultipartFile file,
                   Model model){
        DataSize maxSize = DataSize.parse(maxFileSize);
        if(result.hasErrors() || file.getSize() > maxSize.toBytes()){
            model.addAttribute("article", post);
            model.addAttribute("users",postService.findAllUser());
            model.addAttribute("categories",categoryController.findAllCategory());
            model.addAttribute("error", "File size exceeds the maximum allowed size");
            return "page/add-post";
        }
        post.setUser(postService.findUserById(userId));
        List<Category> categoryList=new ArrayList<>();
        for(String id:listCateID){
            categoryList.add(categoryController.findById(id));
        }
        post.setCategoryList(categoryList);
        postService.addPost(post,file);
        return "redirect:/view-post";
    }
    @GetMapping("/update-post/{id}")
    String viewUpdate(@PathVariable("id") String id,Model model){
        Post post=postService.findPostById(id);
        model.addAttribute("categories",categoryController.findAllCategory());
        model.addAttribute("users",postService.findAllUser());
        model.addAttribute("post",post);
        return "page/update-post-page";
    }
    @PostMapping("/update-post/{id}")
    String updatePost(
                   @PathVariable("id") String postId,
                   @ModelAttribute Post post ,
                   @RequestParam("userId") String userId,
                   @RequestParam("categoryID") ArrayList<String> listCateID,
                   @RequestParam("thumbnail") MultipartFile file,
                   Model model
    ){
        post.setUser(postService.findUserById(userId));
        List<Category> categoryList=new ArrayList<>();
        for(String id:listCateID){
            categoryList.add(categoryController.findById(id));
        }
        post.setCategoryList(categoryList);
        if(file.isEmpty()){
            postService.updatePostEmptyFile(post,postId);
        }else{
            postService.updatePost(post,postId,file);
        }
        return "redirect:/view-post";
    }

}
//        DataSize maxSize = DataSize.parse(maxFileSize);
//        if(result.hasErrors() || file.getSize() > maxSize.toBytes()){
//            model.addAttribute("article", post);
//            model.addAttribute("users",postService.findAllUser());
//            model.addAttribute("categories",categoryController.findAllCategory());
//            model.addAttribute("error", "File size exceeds the maximum allowed size");
//            return "page/add-post";
//        }