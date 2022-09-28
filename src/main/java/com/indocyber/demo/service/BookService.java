package com.indocyber.demo.service;

import com.indocyber.demo.dto.book.BookGridDTO;
import com.indocyber.demo.dto.book.UpsertBookDTO;
import com.indocyber.demo.entity.Book;

import java.util.List;

public interface BookService {
    Book insertBook(UpsertBookDTO dto);

    List<BookGridDTO> getBookGrid(String name, Integer page, String title, String author);

    long getTotalPages(String name, String title, String author);

    UpsertBookDTO getUpdateBook(String name, String code);

}
