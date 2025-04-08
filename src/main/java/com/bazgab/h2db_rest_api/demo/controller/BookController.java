package com.bazgab.h2db_rest_api.demo.controller;


import com.bazgab.h2db_rest_api.demo.model.Book;
import com.bazgab.h2db_rest_api.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

public class BookController {
    //firstly to add a controller we need to instantiate the previously added repository
    @Autowired
    //Autowiring for pulling the interface
    private BookRepository bookRepository;


    //Below we will have to declare the CRUD methods for the controller to control
    //Annotations will correspond to the HTTP Method required by the function
    @GetMapping("/getAllBooks")
    //In order for the method to return something, we have can't have the return type be 'void'
    //So we need to specify the return as a List since we are utilizing the in-memory DB
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            //Declare a new ArrayList utilizing the ResponseEntity List
            List<Book> bookList = new ArrayList<>();
            //Using the repository, we use the findAll() Method from JPA and
            bookRepository.findAll().forEach(bookList::add);
            //Firstly we need to check if the List is empty and return no content in case it is
            if (bookList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                //Otherwise we return the HTTP Status as OK
                return new ResponseEntity<>(bookList,HttpStatus.OK);
            }
        } catch (Exception e) {
            //Return HTTP Status as Internal Server Error/Bad Request
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    //Passing the Path variable to fetch id
    @GetMapping("/getBookById/{id}")
    //Here we return not the entire Book List, but a single book, therefore the ResponseEntity is represented
    //by just the single value
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        //We are introducing a try catch method in all API functions to ensure proper handling of errors
        try {
            //This is going to return an Optional object (because the id might not be present)
            Optional<Book> bookOptional =  bookRepository.findById(id);
            //If the bookOptional object, referenced by the id is present, we will return the HTTP Status as OK
            if (bookOptional.isPresent()) {
                //We return the bookOptional object with the .get() method to fetch the data
                return new ResponseEntity<>(bookOptional.get(), HttpStatus.OK);
            } else {
                //If it isn't present we will return that the object has not been found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Here we have a post mapping for addBook() which means we need to create a RequestBody to save the request
    @PostMapping("/addBook")
    // We add the @RequestBody annotation which passes firstly the model, and then the parameter to show in the response
    // (@RequestBody *model* *parameter*)
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        try {
            // The Book model will create a new Object, this time it's not Optional because it's a post
            // request which we will then use the bookRepository function we created to save the parameter
            // we passed in the RequestBody
            Book bookObject = bookRepository.save(book);
            // and then return the saved Object and the HTTP Status as OK
            return new ResponseEntity<>(bookObject, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/updateBookById/{id}")
    // For updating the book by Id, as expected we are going to need to pass the Id path variable again
    // but not only that, we also need to pass the RequestBody of the updated book
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book newBookOptional) {
        try {
            // Here we will create an Optional Object to see if it exists
            Optional<Book> oldBookOptional = bookRepository.findById(id);
            // If it does, we will need to update the data
            if (oldBookOptional.isPresent()) {
                // Firstly we create the updatedBookOptional to be the same as the oldBookOptional
                Book updatedBookOptional = oldBookOptional.get();
                // Utilizing the setter and getter methods from Lombok we will update the book
                // to store the information on the newBookOptional Object
                updatedBookOptional.setTitle(newBookOptional.getTitle());
                updatedBookOptional.setAuthor(newBookOptional.getAuthor());
                // Now we are going to save the updatedBookOptional into the bookObject to be displayed
                // in the response entity and return the HTTP Status as OK.
                Book bookObject = bookRepository.save(updatedBookOptional);
                return new ResponseEntity<>(bookObject, HttpStatus.OK);
            } else {
                // In case the Id of the book that was requested doesn't exist, we will return
                // the HTTP Status as not found
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //To delete the book by Id, we will have to pass similar arguments to the previous function
    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id) {
        // We will still implement a try catch statement to make sure our logs can show if the book has been deleted
        try {
           // Since JPA cannot return an Object after it has been deleted, we will not instantiate an Optional
           // Object in this statement, but instead just return the HTTP Status as OK
           bookRepository.deleteById(id);
           return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // If the book can't be found by Id, we will return a simple error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
