package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Category;
import com.example.lab11.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    //Get All
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    //Add
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    //Update
    public void updateCategory(Integer categoryId, Category category) {
        Category c = categoryRepository.findCategoryByCategoryId(categoryId);
        if (c == null) {
            throw new ApiException("Category not found");
        }
        c.setCategoryName(category.getCategoryName());
        categoryRepository.save(c);
    }

    //Delete
    public void deleteCategory(Integer categoryId) {
        Category c = categoryRepository.findCategoryByCategoryId(categoryId);
        if (c == null) {
            throw new ApiException("Category not found");
        }
        categoryRepository.delete(c);
    }

}
