package com.higroup.Buda.api.customer.crud;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
public class CRUDCustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CRUDCustomerService(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }
    @Autowired
    private PresentChecker presentChecker;
    public List<Customer> findAllByUserID(Long userID)
    {
        return this.customerRepository.findAllByUserID(userID);
    }
    @Transactional
    public Customer registerNewCustomer(Long userID, Customer customer)
    {
        Optional<Customer> phoneCustomer = this.customerRepository.findCustomerByUserIDAndPhoneNumber(userID, customer.getPhoneNumber());
        if (phoneCustomer.isPresent())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already existed customer");
        }
        customer.setUserID(userID);
        this.customerRepository.save(customer);
        return customer;
    }
    @Transactional
    public Customer updateCustomer(Long userID, Customer customer)
    {
        Optional<Customer> oldCustomer = this.customerRepository.findCustomerByCustomerID(customer.getCustomerID());
        if ((oldCustomer.isPresent())&&(oldCustomer.get().getUserID().equals(userID)))
        {
            presentChecker.checkIdAndRepository(customer.getCustomerID(), customerRepository);
            this.customerRepository.save(customer);
            return customer;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer not found");
    }
    public Customer findCustomerByUserIDAndPhoneNumber(Long userID, String phoneNumber)
    {
        Optional<Customer> phoneCustomer = this.customerRepository.findCustomerByUserIDAndPhoneNumber(userID, phoneNumber);
        if (phoneCustomer.isPresent())
        {
            return phoneCustomer.get();
        }
        return null;
    }
    @Transactional
    public Customer hideCustomer(Long userID, Long customerID)
    {
        Optional<Customer> customer = this.customerRepository.findCustomerByCustomerID(customerID);
        if ((customer.isPresent()) && (customer.get().getUserID().equals(userID)))
        {
            customer.get().setVisible(Boolean.FALSE);
            this.customerRepository.save(customer.get());
            return customer.get();
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"); 
    }
}
