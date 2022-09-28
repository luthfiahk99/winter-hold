package com.indocyber.demo.controller;

import com.indocyber.demo.dto.author.AuthorGridDTO;
import com.indocyber.demo.dto.author.AuthorHeaderDTO;
import com.indocyber.demo.dto.author.UpsertAuthorDTO;
import com.indocyber.demo.dto.book.BookGridDTO;
import com.indocyber.demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String fullName,
                        Model model){

        List<AuthorGridDTO> grid = authorService.getAuthorGrid(page, fullName);

        long totalPages = authorService.getTotalPages(fullName);

        model.addAttribute("grid", grid);
        model.addAttribute("currentPage", page);
        model.addAttribute("fullName", fullName);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("breadCrumbs", "Author Index");

        return "author/author-index";
    }

    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = false) Long id, Model model) {

        if (id != null) {
            UpsertAuthorDTO dto = authorService.getUpdateAuthor(id);

            model.addAttribute("author", dto);
            model.addAttribute("type", "Update");
            model.addAttribute("breadCrumbs", "Author Index / Update Author");
        } else {
            UpsertAuthorDTO dto = new UpsertAuthorDTO();

            model.addAttribute("author", dto);
            model.addAttribute("type", "Insert");
            model.addAttribute("breadCrumbs", "Author Index / Insert Author");
        }
        return "author/author-form";
    }

    @PostMapping("/upsert")
    public String upsert(@Valid @ModelAttribute("author") UpsertAuthorDTO dto,
                         BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {

            if(dto.getId() != null) {
                model.addAttribute("type", "Update");
                model.addAttribute("breadCrumbs", "Author Index / Update Author");
            } else {
                model.addAttribute("type", "Insert");
                model.addAttribute("breadCrumbs", "Author Index / Insert Author");
            }
            return "author/author-form";

        } else {

            authorService.insertAuthor(dto);
            return "redirect:/author/index";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) Long id, Model model){

        Long dependentBook = authorService.dependentBook(id);

        if(dependentBook > 0){
            model.addAttribute("dependencies", dependentBook);
            model.addAttribute("breadCrumbs", "Instructor Index / Fail To Delete Instructor");

            return "author/author-delete";
        }

        authorService.deleteAuthorById(id);
        return "redirect:/author/index";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(required = true) Long id,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "")String book,
                         Model model) {

        AuthorHeaderDTO header = authorService.getAuthorHeader(id);

        List<BookGridDTO> grid = authorService.getBookGridByAuthor(id, page, book);
        long totalPages = authorService.getDetailTotalPages(id, book);

        String breadCrumbs = String.format("Author Index / Book of %s", header.getFullName());
//        System.out.println("author: " +header.getFullName());

        model.addAttribute("headerId", header.getId());
        model.addAttribute("headerFullName", header.getFullName());
        model.addAttribute("headerBirthDate", header.getBirthDate());
        model.addAttribute("headerDeceasedDate", header.getDeceasedDate());
        model.addAttribute("headerEducation", header.getEducation());
        model.addAttribute("headerSummary", header.getSummary());
        model.addAttribute("grid", grid);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("breadCrumbs", breadCrumbs);

        return "author/author-detail";
    }

}
