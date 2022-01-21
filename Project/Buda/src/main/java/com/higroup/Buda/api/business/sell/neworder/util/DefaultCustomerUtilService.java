package com.higroup.Buda.api.business.sell.neworder.util;


import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.enumeration.AgeGroup;
import com.higroup.Buda.entities.enumeration.Gender;
import com.higroup.Buda.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DefaultCustomerUtilService {
    private final CustomerRepository customerRepository;
    @Autowired
    public DefaultCustomerUtilService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    @Transactional
    public Customer defaultCustomer(Long userID){
        String defaultPhoneNumber = "000000000";
        Optional<Customer> customerOptional = this.customerRepository.findCustomerByUserIDAndPhoneNumber(userID, defaultPhoneNumber);
        if (customerOptional.isPresent()){
            return customerOptional.get();
        }
        else{
            Customer customer = new Customer();
            customer.setAgeGroup(AgeGroup.UNKNOWN);
            customer.setGender(Gender.UNKNOWN);
            customer.setUserID(userID);
            customer.setName("UNKNOWN");
            customer.setPhoneNumber(defaultPhoneNumber);
            customer.setVisible(Boolean.TRUE);
            customer.setTotalSpend(0.0);
            this.customerRepository.save(customer);
            return customer;
        }
    }
}
