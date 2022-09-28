package com.indocyber.demo.rest;

import com.indocyber.demo.dto.category.UpsertCategoryDTO;
;
import com.indocyber.demo.entity.Category;
import com.indocyber.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody UpsertCategoryDTO dto){

        Category category = categoryService.insertCategory(dto);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
