package com.higroup.Buda.api.customer.view;

import java.util.List;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.repositories.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewCustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public ViewCustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public List<Customer> findAllByUserID(Long userID)
    {
        return this.customerRepository.findAllByUserID(userID);
    }
}
