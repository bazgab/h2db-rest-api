package com.bazgab.h2db_rest_api.demo.repository;

import com.bazgab.h2db_rest_api.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


}
