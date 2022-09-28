package com.indocyber.demo.repository;

import com.indocyber.demo.dto.author.AuthorGridDTO;
import com.indocyber.demo.entity.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("""
            SELECT new com.indocyber.demo.dto.author.AuthorGridDTO(au.id, CONCAT(au.title , ' ',  au.firstName, ' ', au.lastName),
                au.birthDate, au.deceasedDate, au.education)
            FROM Author AS au
            WHERE (:fullName = '' OR CONCAT(au.firstName, ' ', au.lastName) LIKE CONCAT('%', :fullName, '%'))
            """)
    List<AuthorGridDTO> findAll(@Param("fullName") String fullName, Pageable pagination);

    @Query("""
            SELECT COUNT(au.id)
            FROM Author AS au
            WHERE (:fullName = '' OR CONCAT(au.firstName, ' ', au.lastName) LIKE CONCAT('%', :fullName, '%'))
            """)
    Long count(@Param("fullName") String fullName);
}
