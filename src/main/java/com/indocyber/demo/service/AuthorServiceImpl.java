package com.indocyber.demo.service;

import com.indocyber.demo.dto.author.AuthorGridDTO;
import com.indocyber.demo.dto.author.AuthorHeaderDTO;
import com.indocyber.demo.dto.author.UpsertAuthorDTO;
import com.indocyber.demo.dto.book.BookGridDTO;
import com.indocyber.demo.entity.Author;
import com.indocyber.demo.repository.AuthorRepository;
import com.indocyber.demo.repository.BookRepository;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    private int rowsInPage = 5;

    @Override
    public Author getById(Long id) {
        Optional<Author> author = authorRepository.findById(id);

        Author newAuthor = null;
        if(author.isPresent()){
            newAuthor = author.get();
        }
        return newAuthor;
    }

    @Override
    public Author insertAuthor(UpsertAuthorDTO dto) {

        Author newAuthor = new Author(
                dto.getId(),
                dto.getTitle(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getBirthDate(),
                dto.getDeceasedDate(),
                dto.getEducation(),
                dto.getSummary()
        );

        authorRepository.save(newAuthor);
//        System.out.println(newAuthor);
        return newAuthor;
    }

    @Override
    public int getAge() {
        List<Author> authorList = authorRepository.findAll();
        int age = 0;

        for (Author aut : authorList) {
            int date = 0;
            int birth = aut.getBirthDate().getYear();

            if (aut.getDeceasedDate() != null) {
                date = aut.getDeceasedDate().getYear();
                age = date-birth;
            } else {
                date = LocalDate.now().getYear();
                age = date-birth;
            }
        }
        return age;
    }

    @Override
    public List<AuthorGridDTO> getAuthorGrid(Integer page, String fullName) {

        Pageable pagination = PageRequest.of(page - 1, rowsInPage, Sort.by("id"));

        List<AuthorGridDTO> grid = authorRepository.findAll(fullName , pagination);

        return grid;
    }

    @Override
    public long getTotalPages(String fullName) {

        double totalData = (double)(authorRepository.count(fullName));

        long totalPage = (long) (Math.ceil(totalData / rowsInPage));

        return totalPage;
    }

    @Override
    public String getStatus() {

        List<Author> authorList = authorRepository.findAll();

        String status = "";

        for (Author aut : authorList){
            if (aut.getDeceasedDate() != null){
                status = "Deceased";
            }else {
                status = "Alive";
            }
        }
        return status;
    }

    @Override
    public UpsertAuthorDTO getUpdateAuthor(Long id) {
        Optional<Author> nullableEntity = authorRepository.findById(id);

        Author entity = nullableEntity.get();

        UpsertAuthorDTO authorDTO = new UpsertAuthorDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getBirthDate(),
                entity.getDeceasedDate(),
                entity.getEducation(),
                entity.getSummary());

        return authorDTO;
    }

    @Override
    public void saveAuthor(UpsertAuthorDTO dto) {
        Author entity = new Author(
                dto.getId(),
                dto.getTitle(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getBirthDate(),
                dto.getDeceasedDate(),
                dto.getEducation(),
                dto.getSummary());

        authorRepository.save(entity);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Long dependentBook(Long id) {
        Long totalDependentBook = bookRepository.countByAuthorId(id);

        return totalDependentBook;
    }

    @Override
    public AuthorHeaderDTO getAuthorHeader(Long id) {
        Optional<Author> nullableEntity = authorRepository.findById(id);
        Author entity = nullableEntity.get();
        String fullName = String.format(String.format("%s %s %s", entity.getTitle(), entity.getFirstName(), entity.getLastName()));
        AuthorHeaderDTO courseDTO = new AuthorHeaderDTO(
                entity.getId(),
                fullName,
                entity.getBirthDate(),
                entity.getDeceasedDate(),
                entity.getEducation(),
                entity.getSummary()
        );
        return courseDTO;
    }

    @Override
    public List<BookGridDTO> getBookGridByAuthor(Long id, Integer page, String book) {

        Pageable pagination = PageRequest.of(page - 1, rowsInPage, Sort.by("id"));
        List<BookGridDTO> grid = bookRepository.findAllBook(id, book, pagination);
        return grid;
    }

    @Override
    public Long getDetailTotalPages(Long id, String book) {
        double totalData = (double)(bookRepository.countBook(id, book));
        long totalPage = (long)(Math.ceil(totalData / rowsInPage));
        return totalPage;
    }


}
