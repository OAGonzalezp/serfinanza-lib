package com.bancoserfinanza.bookbank.repository;

import com.bancoserfinanza.models.bookbank.Book;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
}
