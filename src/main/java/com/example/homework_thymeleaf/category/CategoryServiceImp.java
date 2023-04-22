package com.example.homework_thymeleaf.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService{
    private final CategoryRepository categoryRepository;
//    select all
    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAllCategory();
    }
//  find by category id
    @Override
    public Category findById(String id) {
        return categoryRepository.findById(id);
    }
//  insert
    @Override
    public void addCategory(Category category) {
        categoryRepository.addCategory(category);
    }

    @Override
    public void deleteByCateId(String id) {
        categoryRepository.deleteCategoryById(id);
    }

    @Override
    public void updateByCateID(String id, Category category) {
        categoryRepository.updateCategoryById(id,category);
    }
}
