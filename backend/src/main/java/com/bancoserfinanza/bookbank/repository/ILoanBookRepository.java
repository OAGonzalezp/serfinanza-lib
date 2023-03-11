package com.bancoserfinanza.bookbank.repository;

import com.bancoserfinanza.models.bookbank.LoanBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoanBookRepository extends JpaRepository<LoanBook, Long> {
}
