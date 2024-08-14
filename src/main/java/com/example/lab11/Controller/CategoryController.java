package com.example.lab11.Controller;

import com.example.lab11.Model.Category;
import com.example.lab11.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    //Get
    @GetMapping("/get")
    public ResponseEntity getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    //Add
    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("Category added successfully");
    }

    //Update
    @PutMapping("/update/{categoryId}")
    public ResponseEntity updateCategory(@PathVariable Integer categoryId, @Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());
        }
        categoryService.updateCategory(categoryId, category);
        return ResponseEntity.ok("Category updated successfully");
    }

    //Delete
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted successfully");
    }
}
