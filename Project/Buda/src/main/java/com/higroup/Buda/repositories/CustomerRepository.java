package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    @Query(value = "select c from Customer c where c.userID = :userID and c.visible = true")
    List<Customer> findAllByUserID(@Param("userID") Long userID);
    Optional<Customer> findCustomerByCustomerID(@Param("customerID") Long customerID);
    Optional<Customer> findCustomerByUserIDAndPhoneNumber(@Param("userID") Long userID, @Param("phoneNumber") String phoneNumber);
}
