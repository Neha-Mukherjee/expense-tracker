package net.javaguides.expense.mapper;

import net.javaguides.expense.dto.CategoryDto;
import net.javaguides.expense.entity.Category;

public class CategoryMapper {
    //map CategoryDto to Category entity

    public static Category maptoCategory(CategoryDto categoryDto){
        return new Category(categoryDto.id(), categoryDto.name());
    }

    //map category entity to categoryDto

    public static CategoryDto maptoCategoryDto(Category category){
        return new CategoryDto(category.getId(), category.getName());
    }
}
