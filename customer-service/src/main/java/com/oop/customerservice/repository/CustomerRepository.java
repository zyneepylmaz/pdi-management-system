package com.oop.customerservice.repository;

import com.oop.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByActiveTrue();

    boolean existsByContactEmail(String contactEmail);
}