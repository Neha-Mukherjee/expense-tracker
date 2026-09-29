package net.javaguides.expense.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.expense.dto.CategoryDto;
import net.javaguides.expense.entity.Category;
import net.javaguides.expense.exceptions.ResourceNotFoundException;
import net.javaguides.expense.mapper.CategoryMapper;
import net.javaguides.expense.repository.CategoryRepository;
import net.javaguides.expense.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;



    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.maptoCategory(categoryDto);

        Category savedCategory=categoryRepository.save(category);
        return CategoryMapper.maptoCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category not found with id"+categoryId));
        return CategoryMapper.maptoCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {

       List<Category> categories =categoryRepository.findAll();

        return categories.stream().map((category) -> CategoryMapper.maptoCategoryDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        //get category entity from the database by category id
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(("Category not found with id" + categoryId)));


        //update the category entity object and save to db table
        category.setName(categoryDto.name());
        Category updatedCategory=categoryRepository.save(category);
        return CategoryMapper.maptoCategoryDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        //check if a category with given id exists
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("not found category with id"+categoryId));

       categoryRepository.delete(category);
    }
}
