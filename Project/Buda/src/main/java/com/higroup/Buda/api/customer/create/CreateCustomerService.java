package com.higroup.Buda.api.customer.create;

import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CreateCustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CreateCustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    @Transactional
    public Customer createNewCustomer(Long userID, Customer customer)
    {
        Optional<Customer> phoneCustomer = this.customerRepository.findCustomerByUserIDAndPhoneNumber(userID, customer.getPhoneNumber());
        if (phoneCustomer.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already existed customer");
        }
        customer.setTotalSpend(0.0);
        customer.setUserID(userID);
        this.customerRepository.save(customer);
        return customer;
    }
}
