package com.example.homework_thymeleaf.post;

import com.example.homework_thymeleaf.category.Category;
import com.example.homework_thymeleaf.user.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String uuid;
    @NotBlank(message = "Title is required..!")
    private String title;
    @NotBlank(message = "description is required..!")

    private String description;
    private String image;
    private List<Category> categoryList;
    private User user;

}
