package com.indocyber.demo.service;

import com.indocyber.demo.dto.author.AuthorGridDTO;
import com.indocyber.demo.dto.author.UpsertAuthorDTO;
import com.indocyber.demo.dto.category.CategoryGridDTO;
import com.indocyber.demo.dto.category.UpsertCategoryDTO;
import com.indocyber.demo.entity.Author;
import com.indocyber.demo.entity.Category;
import com.indocyber.demo.repository.BookRepository;
import com.indocyber.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BookRepository bookRepository;


    private int rowsInPage = 5;

    @Override
    public Category insertCategory(UpsertCategoryDTO dto) {
        Category newCategory = new Category(
                dto.getName(),
                dto.getFloor(),
                dto.getIsle(),
                dto.getBay()
        );

        categoryRepository.save(newCategory);

        return newCategory;
    }

    @Override
    public List<CategoryGridDTO> getCategoryGrid(Integer page, String name) {
        Pageable pagination = PageRequest.of(page - 1, rowsInPage, Sort.by("id"));

        List<CategoryGridDTO> grid = categoryRepository.findAll(name , pagination);

        return grid;
    }

    @Override
    public long getTotalPages(String name) {

        double totalData = (double)(categoryRepository.count(name));

        long totalPage = (long) (Math.ceil(totalData / rowsInPage));

        return totalPage;
    }

    @Override
    public long getTotalBook(String categoryName) {
        long totalBook = bookRepository.countByCategory(categoryName);
        return totalBook;
    }

    @Override
    public UpsertCategoryDTO getUpdateCategory(String name) {
        Optional<Category> nullableEntity = categoryRepository.findById(name);

        Category entity = nullableEntity.get();
//        System.out.println(entity);

        UpsertCategoryDTO categoryDTO = new UpsertCategoryDTO(
                entity.getName(),
                entity.getFloor(),
                entity.getIsle(),
                entity.getBay());

        return categoryDTO;
    }

    @Override
    public Long dependentBook(String name) {
        Long totalDependentBook = bookRepository.countByCategory(name);

        return totalDependentBook;
    }

    @Override
    public void deleteCategoryById(String name) {
        categoryRepository.deleteById(name);
    }

    @Override
    public CategoryGridDTO getByName(String name) {
        Optional<Category> nullableEntity = categoryRepository.findById(name);

        Category entity = nullableEntity.get();
//        System.out.println(entity);

        CategoryGridDTO categoryDTO = new CategoryGridDTO(
                entity.getName(),
                entity.getFloor(),
                entity.getIsle(),
                entity.getBay());

        return categoryDTO;
    }

}
