package com.indocyber.demo.repository;

import com.indocyber.demo.dto.author.AuthorGridDTO;
import com.indocyber.demo.dto.category.CategoryGridDTO;
import com.indocyber.demo.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query("""
            SELECT new com.indocyber.demo.dto.category.CategoryGridDTO(cat.name, cat.floor, cat.isle, cat.bay, COUNT(bo))
            FROM Category AS cat
                LEFT JOIN cat.bookList AS bo
            WHERE cat.name LIKE %:name%
            GROUP BY cat.name, cat.floor, cat. isle, cat.bay
            """)
    List<CategoryGridDTO> findAll(@Param("name") String name, Pageable pagination);

    @Query("""
            SELECT COUNT(cat.name)
            FROM Category AS cat
            WHERE cat.name LIKE %:name%
            """)
    Long count(@Param("name") String name);

    @Query("""
            SELECT COUNT(bo.title)
            FROM Book AS bo
                JOIN bo.category AS cat
            WHERE cat.name = :name
            """)
    Long countBook(@Param("name") String name);
}
