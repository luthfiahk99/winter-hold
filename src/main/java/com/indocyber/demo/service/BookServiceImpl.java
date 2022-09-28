package com.indocyber.demo.service;

import com.indocyber.demo.dto.book.BookGridDTO;
import com.indocyber.demo.dto.book.UpsertBookDTO;
import com.indocyber.demo.dto.category.CategoryGridDTO;
import com.indocyber.demo.dto.customer.UpsertCustomerDTO;
import com.indocyber.demo.entity.Book;
import com.indocyber.demo.entity.Customer;
import com.indocyber.demo.repository.BookRepository;
import com.indocyber.demo.rest.BookRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    private int rowsInPage = 5;

    @Override
    public Book insertBook(UpsertBookDTO dto) {
        Book newBook = new Book(
                dto.getCode(),
                dto.getTitle(),
                dto.getCategoryName(),
                dto.getAuthorId(),
                false,
                dto.getSummary(),
                dto.getReleaseDate(),
                dto.getTotalPage()
        );

        bookRepository.save(newBook);

        return newBook;
    }

    @Override
    public List<BookGridDTO> getBookGrid(String name, Integer page, String title, String author) {
        Pageable pagination = PageRequest.of(page - 1, rowsInPage, Sort.by("id"));

        List<BookGridDTO> grid = bookRepository.findAll(name, title, author, pagination);

        return grid;
    }

    @Override
    public long getTotalPages(String name, String title, String author) {

        double totalData = (double)(bookRepository.count(name, title, author));

        long totalPage = (long) (Math.ceil(totalData / rowsInPage));

        return totalPage;
    }

    @Override
    public UpsertBookDTO getUpdateBook(String name, String code) {

        Optional<Book> nullableEntity = bookRepository.findById(code);

        Book entity = nullableEntity.get();
        UpsertBookDTO bookDTO = new UpsertBookDTO(
                entity.getCode(),
                entity.getTitle(),
                name,
                entity.getAuthorId(),
                entity.getReleaseDate(),
                entity.getTotalPage(),
                entity.getSummary()
        );

        return bookDTO;
    }


}
