package com.indocyber.demo.service;

import com.indocyber.demo.dto.author.AuthorGridDTO;
import com.indocyber.demo.dto.author.AuthorHeaderDTO;
import com.indocyber.demo.dto.author.UpsertAuthorDTO;
import com.indocyber.demo.dto.book.BookGridDTO;
import com.indocyber.demo.entity.Author;

import java.util.List;

public interface AuthorService {
    Author getById(Long id);

    Author insertAuthor(UpsertAuthorDTO dto);

    int getAge();

    List<AuthorGridDTO> getAuthorGrid(Integer page, String fullName);

    long getTotalPages(String fullName);

    String getStatus();

    UpsertAuthorDTO getUpdateAuthor(Long id);

    void saveAuthor(UpsertAuthorDTO dto);

    void deleteAuthorById(Long id);

    Long dependentBook(Long id);

    AuthorHeaderDTO getAuthorHeader(Long id);

    List<BookGridDTO> getBookGridByAuthor(Long id, Integer page, String book);

    Long getDetailTotalPages(Long id, String book);
}
