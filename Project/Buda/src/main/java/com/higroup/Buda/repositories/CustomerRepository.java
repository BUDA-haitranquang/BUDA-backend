package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    List<Customer> findAllByUserID(Long userID);
    Optional<Customer> findCustomerByCustomerID(Long customerID);
}
