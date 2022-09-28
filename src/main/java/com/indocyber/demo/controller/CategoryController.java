package com.indocyber.demo.controller;

import com.indocyber.demo.dto.author.AuthorGridDTO;
import com.indocyber.demo.dto.author.UpsertAuthorDTO;
import com.indocyber.demo.dto.book.BookGridDTO;
import com.indocyber.demo.dto.book.UpsertBookDTO;
import com.indocyber.demo.dto.category.CategoryGridDTO;
import com.indocyber.demo.dto.category.UpsertCategoryDTO;
import com.indocyber.demo.entity.Author;
import com.indocyber.demo.service.BookService;
import com.indocyber.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String name,
                        Model model){

        List<CategoryGridDTO> grid = categoryService.getCategoryGrid(page, name);

        long totalPages = categoryService.getTotalPages(name);
        System.out.println("category index: " + grid);

        model.addAttribute("grid", grid);
        model.addAttribute("currentPage", page);
        model.addAttribute("name", name);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("breadCrumbs", "Book Index");

        return "category/category-index";
    }

    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = false) String name, Model model) {

        if (name != null) {
            UpsertCategoryDTO dto = categoryService.getUpdateCategory(name);

            model.addAttribute("category", dto);
            model.addAttribute("type", "Update");
            model.addAttribute("breadCrumbs", "Book Index / Update Category");
        } else {
            UpsertCategoryDTO dto = new UpsertCategoryDTO();

            model.addAttribute("category", dto);
            model.addAttribute("type", "Insert");
            model.addAttribute("breadCrumbs", "Book Index / Insert Category");
        }
        return "category/category-form";
    }

    @PostMapping("/upsert")
    public String upsert(@Valid @ModelAttribute("category") UpsertCategoryDTO dto,
                         BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {

            if(dto.getName() != null) {
                model.addAttribute("type", "Update");
                model.addAttribute("breadCrumbs", "Book Index / Update Category");
            } else {
                model.addAttribute("type", "Insert");
                model.addAttribute("breadCrumbs", "Book Index / Insert Category");
            }
            return "category/category-form";

        } else {

            categoryService.insertCategory(dto);
            return "redirect:/category/index";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) String name, Model model){

        Long dependentBook = categoryService.dependentBook(name);

        if(dependentBook > 0){
            model.addAttribute("dependencies", dependentBook);
            model.addAttribute("breadCrumbs", "Book Index / Fail To Delete Category");

            return "category/category-delete";
        }

        categoryService.deleteCategoryById(name);
        return "redirect:/category/index";
    }

    @GetMapping("/book")
    public String detail(@RequestParam(required = true) String name,
                         @RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String title,
                        @RequestParam(defaultValue = "")String author,
                        Model model){

        List<BookGridDTO> grid = bookService.getBookGrid(name, page, title, author);

        long totalPages = bookService.getTotalPages(name, title, author);

        CategoryGridDTO book = categoryService.getByName(name);
        String addButton = String.format("Add New %s Book", book.getName() );

        model.addAttribute("name", name);
        model.addAttribute("grid", grid);
        model.addAttribute("currentPage", page);
        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("addButton", addButton);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("breadCrumbs", "Book Index / Book Category");

        return "book/book-index";
    }

    @GetMapping("/upsertBookForm")
    public String upsertBookForm(@RequestParam(required = true) String name,
                                 @RequestParam(required = false) String code, Model model) {

        if (code != null) {
            UpsertBookDTO dto = bookService.getUpdateBook(name, code);

            model.addAttribute("name", name);
            model.addAttribute("book", dto);
            model.addAttribute("type", "Update");
            model.addAttribute("breadCrumbs", "Book Index / Update Book");
        } else {
            UpsertBookDTO dto = new UpsertBookDTO();

            model.addAttribute("name", name);
            model.addAttribute("book", dto);
            model.addAttribute("type", "Insert");
            model.addAttribute("breadCrumbs", "Book Index / Insert Book");
        }
        return "book/book-form";
    }

    @PostMapping("/upsertBook")
    public String upsertBook(@Valid @ModelAttribute("book") UpsertBookDTO dto,
                         BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {

            if(dto.getCode() != null) {

                model.addAttribute("type", "Update");
                model.addAttribute("breadCrumbs", "Book Index / Update Book");
            } else {
                model.addAttribute("type", "Insert");
                model.addAttribute("breadCrumbs", "Book Index / Insert Book");
            }
            return "book/book-form";

        } else {

            bookService.insertBook(dto);
            return "redirect:/category/index";
        }
    }


}
