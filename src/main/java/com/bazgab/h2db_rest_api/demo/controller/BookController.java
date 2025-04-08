package com.bazgab.h2db_rest_api.demo.controller;


import com.bazgab.h2db_rest_api.demo.model.Book;
import com.bazgab.h2db_rest_api.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

public class BookController {
    //firstly to add a controller we need to instantiate the previously added repository
    @Autowired
    //Autowiring for pulling the interface
    private BookRepository bookRepository;


    //Below we will have to declare the CRUD methods for the controller to control
    //Annotations will correspond to the HTTP Method required by the function
    @GetMapping
    //In order for the method to return something, we have can't have the return type be 'void'
    //So we need to specify the return as a List since we are utilizing the in-memory DB
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            //Declare a new ArrayList utilizing the ResponseEntity List
            List<Book> bookList = new ArrayList<>();
            //Using the repository, we use the findAll() Method from JPA and
            bookRepository.findAll().forEach(bookList::add);
        } catch (Exception e) {
            //Return HTTP Status as Internal Server Error/Bad Request
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping
    public void getBookById() {

    }
    @PostMapping
    public void addBook() {

    }
    @PostMapping
    public void updateBookById() {

    }
    @DeleteMapping
    public void deleteBookById() {

    }
}
