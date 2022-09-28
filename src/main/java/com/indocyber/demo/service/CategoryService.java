package com.indocyber.demo.service;

import com.indocyber.demo.dto.category.CategoryGridDTO;
import com.indocyber.demo.dto.category.UpsertCategoryDTO;
import com.indocyber.demo.entity.Category;

import java.util.List;

public interface CategoryService {
    Category insertCategory(UpsertCategoryDTO dto);

    List<CategoryGridDTO> getCategoryGrid(Integer page, String name);

    long getTotalPages(String name);

    long getTotalBook(String name);

    UpsertCategoryDTO getUpdateCategory(String name);

    Long dependentBook(String name);

    void deleteCategoryById(String name);

    CategoryGridDTO getByName(String name);
}
