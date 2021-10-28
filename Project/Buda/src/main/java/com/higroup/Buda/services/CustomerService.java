package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }
    @Autowired
    private PresentChecker presentChecker;
    public List<Customer> findAllByUserID(Long userID)
    {
        return this.customerRepository.findAllByUserID(userID);
    }
    public Customer registerNewCustomer(Long userID, Customer customer)
    {
        Optional<Customer> phoneCustomer = this.customerRepository.findCustomerByUserIDAndPhoneNumber(userID, customer.getPhoneNumber());
        if (phoneCustomer.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.OK, "Already existed customer");
        }
        customer.setUserID(userID);
        this.customerRepository.save(customer);
        return customer;
    }
    public Customer updateCustomer(Long userID, Customer customer)
    {
        if (customer.getUserID()!=userID)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserID does not match");
        }
        presentChecker.checkIdAndRepository(customer.getCustomerID(), customerRepository);
        this.customerRepository.save(customer);
        return customer;
    }
    public ResponseEntity<?> findCustomerByUserIDAndPhoneNumber(Long userID, String phoneNumber)
    {
        Optional<Customer> phoneCustomer = this.customerRepository.findCustomerByUserIDAndPhoneNumber(userID, phoneNumber);
        return phoneCustomer.<ResponseEntity<?>>map(customer -> ResponseEntity.ok().body(customer)).orElseGet(() -> ResponseEntity.badRequest().body("Not found"));
    }
}
