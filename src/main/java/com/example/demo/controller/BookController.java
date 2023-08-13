package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.services.BookService;
import com.example.demo.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "book/add";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
        if (result.hasErrors()){
            model.addAttribute("book", book);
            model.addAttribute("categories", categoryService.getAllCategory());
            return "book/add";
        } else {
            bookService.addBook(book);
            return "redirect:/books";
        }
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model){
        Book editBook = bookService.getBookById(id);
        model.addAttribute("book", editBook);
        model.addAttribute("categories", categoryService.getAllCategory());
        return "book/edit";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@Valid @ModelAttribute("book") Book book,  BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("book", book);
            model.addAttribute("categories", categoryService.getAllCategory());
            return "book/edit";
        } else {
            bookService.updateBook(book);
            return "redirect:/books";
        }
    }

//    @GetMapping("/delete/{id}")
//    public String deleteBookForm(@PathVariable("id") Long id, Model model){
//        Book editBook = bookService.getBookById(id);
//        model.addAttribute("book", editBook);
//        model.addAttribute("categories", categoryService.getAllCategory());
//        return "book/edit";
//    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }

//
//        @GetMapping("/edit/{id}")
//        public String editBookForm(@PathVariable("id") Long id, Model model){
//            Optional<Book> editBook = books.stream()
//                    .filter(book -> book.getId().equals(id))
//                    .findFirst();
//            if(editBook.isPresent()){
//                model.addAttribute("book", editBook.get());
//                return "book/edit";
//            }else{
//                return "not-found";
//            }
//        }
//
//        @PostMapping("/edit")
//        public String editBook(@ModelAttribute("book") Book updatedBook){
//            books.stream()
//                    .filter(book -> book.getId() == updatedBook.getId())
//                    .findFirst()
//                    .ifPresent(book -> books.set(books.indexOf(book), updatedBook));
//            return "redirect:/books";
//        }
//
//        @GetMapping("/delete/{id}")
//        public String deleteBook(@PathVariable("id") Long id){
//            books.removeIf(book -> book.getId().equals(id));
//            return "redirect:/books";
//        }
//    @Autowired
//    private List<Book> books;
//
//    @GetMapping
//    public String listBooks(Model model) {
//        model.addAttribute("books", books);
//        model.addAttribute("title", "Book List");
//        return "book/list";
//    }
//
//    @GetMapping("/add")
//    public String addBookForm(Model model) {
//        model.addAttribute("book", new Book());
//        return "book/add";
//    }
//
//    @PostMapping("/add")
//    public String addBookForm(@ModelAttribute("book") Book book) {
//        books.add(book);
//        return "redirect:/books";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editBookForm(@PathVariable("id") Long id, Model model){
//        Optional<Book> editBook = books.stream()
//                .filter(book -> book.getId().equals(id))
//                .findFirst();
//        if(editBook.isPresent()){
//            model.addAttribute("book", editBook.get());
//            return "book/edit";
//        }else{
//            return "not-found";
//        }
//    }
//
//    @PostMapping("/edit")
//    public String editBook(@ModelAttribute("book") Book updatedBook){
//        books.stream()
//                .filter(book -> book.getId() == updatedBook.getId())
//                .findFirst()
//                .ifPresent(book -> books.set(books.indexOf(book), updatedBook));
//        return "redirect:/books";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteBook(@PathVariable("id") Long id){
//        books.removeIf(book -> book.getId().equals(id));
//        return "redirect:/books";
//    }
}
