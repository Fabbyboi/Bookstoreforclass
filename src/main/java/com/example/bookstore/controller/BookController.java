package com.example.bookstore.controller;

import com.example.bookstore.bean.Book;
import com.example.bookstore.bean.BookRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;


    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/booklist")
    public String bookList(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long id){
        bookRepository.delete(id);
        return "redirect:../booklist";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addBook(Book book){
        bookRepository.save(book);
        return "redirect:booklist";
    }

    @RequestMapping(value="/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }
	
    @RequestMapping(value="/api/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) bookRepository.findAll();
    }
    
    @RequestMapping(value="/api/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Book findbookRest(@PathVariable("id") Long bookId) {	
    	return bookRepository.findOne(bookId);
    } 
}
