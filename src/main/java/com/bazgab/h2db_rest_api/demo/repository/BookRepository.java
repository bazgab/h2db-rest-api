package com.bazgab.h2db_rest_api.demo.repository;

import com.bazgab.h2db_rest_api.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// We need a repository for managing the database which will then be called by a method we will
// create in the controller. For that we need to extend the JpaRepository class which passes
// first the table which this repository will utilize (in our case, using the Book Model we created)
// and then the type of id which is the primary key we are using for the table.
// To summarize:
//
// public interface [Repository Name] extends JpaRepository<[Table to be used], [Primary key Type]>
//
// This interface instantiates the BookRepository class so that we can utilize JPA to manage it

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


}
