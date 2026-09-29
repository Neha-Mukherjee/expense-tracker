package net.javaguides.expense.controller;


import lombok.AllArgsConstructor;
import net.javaguides.expense.dto.CategoryDto;
import net.javaguides.expense.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;

    //create category restapi
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody  CategoryDto categoryDto){
        CategoryDto category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    //getcategory restapi
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long categoryId){
        CategoryDto category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    //getallcategory restapi
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categories=categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    //update category restapi
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id")  Long categoryId,@RequestBody CategoryDto categoryDto){
        CategoryDto updatedCategory =categoryService.updateCategory(categoryId,categoryDto);
        return ResponseEntity.ok(updatedCategory);
    }
     // delete category restapi
    @DeleteMapping({"{id}"})
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("category deleted succesfully");
    }
}
