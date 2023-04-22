package com.example.homework_thymeleaf.category;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import java.util.List;
@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService=categoryService;
    }

    public List<Category> findAllCategory(){
        return categoryService.findAllCategory();
    }
    public Category findById(String id){
        return categoryService.findById(id);
    }
    public void addCategory(Category category){
        categoryService.addCategory(category);
    }

}
