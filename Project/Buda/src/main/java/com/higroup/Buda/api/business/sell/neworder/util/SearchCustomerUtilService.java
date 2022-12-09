package com.higroup.Buda.api.business.sell.neworder.util;

import java.util.Optional;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SearchCustomerUtilService {
    private final CustomerRepository customerRepository;
    @Autowired
    public SearchCustomerUtilService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public Customer findCustomerByIncompletedInfo(Long userID, Customer customer){
        if (customer.getCustomerID()!=null)
        {
            Optional<Customer> customerOptional = this.customerRepository.findCustomerByCustomerID(customer.getCustomerID());
            if ((customerOptional.isPresent()) && (customerOptional.get().getUserID().equals(userID))){
                return customerOptional.get();
            }
            else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer not found");
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not enough info to search, requires phoneNumber to search");
    }
}
