package com.blogging.app.services;

import com.blogging.app.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {


    public CategoryDto createCategory(CategoryDto categoryDto );

    public CategoryDto updateCategory(CategoryDto categoryDto ,Integer categoryId );

    public void deleteCategory(Integer categoryId );
    public CategoryDto getCategory(Integer categoryId );

    public List<CategoryDto> getCategories();


}
