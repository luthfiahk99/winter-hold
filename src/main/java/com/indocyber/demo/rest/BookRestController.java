package com.indocyber.demo.rest;

import com.indocyber.demo.dto.book.UpsertBookDTO;
import com.indocyber.demo.entity.Book;
import com.indocyber.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody UpsertBookDTO dto){

        Book newBook = bookService.insertBook(dto);
        return new ResponseEntity<>(newBook, HttpStatus.OK);
    }
}
