package com.example.homework_thymeleaf.category;
import java.util.List;
public interface CategoryService {
    List<Category> findAllCategory();
    Category findById(String id);

    void addCategory(Category category);
}
