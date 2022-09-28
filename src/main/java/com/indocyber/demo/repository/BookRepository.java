package com.indocyber.demo.repository;

import com.indocyber.demo.dto.book.BookGridDTO;
import com.indocyber.demo.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    @Query("""
			SELECT COUNT(bo.id) 
			FROM Book AS bo
			WHERE bo.authorId = :authorId
			""")
    Long countByAuthorId(@Param("authorId") Long authorId);

	@Query("""
			SELECT COUNT(bo.id) 
			FROM Book AS bo
			WHERE bo.categoryName = :name
			""")
    Long countByCategory(@Param("name") String name);

	@Query("""
            SELECT new com.indocyber.demo.dto.book.BookGridDTO(bo.code, bo.title, bo.categoryName, CONCAT(au.title, ' ', au.firstName, ' ', au.lastName),
            	bo.isBorrowed, bo.releaseDate, bo.totalPage)
            FROM Book AS bo
            	JOIN bo.author AS au
            	JOIN bo.category AS cat
            WHERE cat.name = :name
            	AND bo.title LIKE %:title%
            	AND (:author = '' OR CONCAT(au.title, ' ', au.firstName, ' ', au.lastName) LIKE CONCAT('%', :author, '%'))
            """)
    List<BookGridDTO> findAll(@Param("name") String name, @Param("title") String title,
							  @Param("author") String author, Pageable pagination);


	@Query("""
            SELECT COUNT(bo.code)
            FROM Book AS bo
            	JOIN bo.category AS cat
            	JOIN bo.author AS au
            WHERE cat.name = :name
            	AND bo.title LIKE %:title%
            	AND (:author = '' OR CONCAT(au.title, ' ', au.firstName, ' ', au.lastName) LIKE CONCAT('%', :author, '%'))
            """)
	Long count(@Param("name")String name, @Param("title") String title, @Param("author") String author);


	@Query("""
			SELECT new com.indocyber.demo.dto.book.BookGridDTO(bo.code, bo.title, bo.categoryName,
				CONCAT(au.title, ' ', au.firstName, ' ', au.lastName), bo.isBorrowed, bo.releaseDate, bo.totalPage)
			FROM Book AS bo
				JOIN bo.author AS au
			WHERE au.id = :id
			 	AND bo.title LIKE %:book%
			""")
    List<BookGridDTO> findAllBook(@Param("id") Long id, @Param("book") String book, Pageable pagination);

	@Query("""
			SELECT COUNT(bo.title)
			FROM Book AS bo
				JOIN bo.author AS au
			WHERE au.id = :id
			    AND bo.title LIKE %:book%
			""")
	Long countBook(@Param("id") Long id, @Param("book") String book);
}
