package com.bancoserfinanza.bookbank.repository;

import com.bancoserfinanza.models.bookbank.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}
