package com.bazgab.h2db_rest_api.demo.repository;

import com.bazgab.h2db_rest_api.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// This interface instantiates the BookRepository class so that we can utilize JPA to manage it
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


}
