package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }
    public List<Customer> findAllByUserID(Long userID)
    {
        return this.customerRepository.findAllByUserID(userID);
    }
    public ResponseEntity<?> registerNewCustomer(Long userID, Customer customer)
    {
        Optional<Customer> phoneCustomer = this.customerRepository.findCustomerByUserIDAndPhoneNumber(userID, customer.getPhoneNumber());
        if (phoneCustomer.isPresent())
        {
            return ResponseEntity.badRequest().body("Already used phoneNumber");
        }
        customer.setUserID(userID);
        this.customerRepository.save(customer);
        return ResponseEntity.ok().body(customer.toString());
    }
    public ResponseEntity<?> findCustomerByUserIDAndPhoneNumber(Long userID, String phoneNumber)
    {
        Optional<Customer> phoneCustomer = this.customerRepository.findCustomerByUserIDAndPhoneNumber(userID, phoneNumber);
        if (phoneCustomer.isPresent())
        {
            return ResponseEntity.ok().body(phoneCustomer.get().toString());
        }
        return ResponseEntity.badRequest().body("Not found");
    }
}
