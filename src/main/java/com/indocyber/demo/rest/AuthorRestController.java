package com.indocyber.demo.rest;

import com.indocyber.demo.dto.author.UpsertAuthorDTO;
import com.indocyber.demo.entity.Author;
import com.indocyber.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/author")
public class AuthorRestController {

    @Autowired
    private AuthorService authorService;

//    @PostMapping
//    public ResponseEntity<Author> addAuthor(@RequestBody UpsertAuthorDTO dto){
//
//        authorService.insertAuthor(dto);
//        return new ResponseEntity<>(authorService.getById(dto.getId()), HttpStatus.OK);
//    }

    @PostMapping
    public Author addAuthor(@RequestBody UpsertAuthorDTO dto){

        Author newAuthor = authorService.insertAuthor(dto);
        return newAuthor;
    }
}
