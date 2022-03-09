package com.higroup.Buda.api.customer.update;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.util.BeanUtils.NullAwareBeanUtilsBean;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
public class UpdateCustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public UpdateCustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    @Transactional
    public Customer updateCustomer(Long userID, Customer customer) throws IllegalAccessException, InvocationTargetException
    {
        Optional<Customer> oldCustomerOptional = this.customerRepository.findCustomerByCustomerID(customer.getCustomerID());
        if ((oldCustomerOptional.isPresent())&&(oldCustomerOptional.get().getUserID().equals(userID)))
        {
            Customer oldCustomer = oldCustomerOptional.get();
            BeanUtilsBean bean = new NullAwareBeanUtilsBean();
            customer.setTotalSpend(oldCustomer.getTotalSpend());
            if (customer.getPhoneNumber()!=null){
                Optional<Customer> phoneCustomerOptional = this.customerRepository.findCustomerByUserIDAndPhoneNumber(userID, customer.getPhoneNumber());
                if ((phoneCustomerOptional.isPresent()) && 
                (!phoneCustomerOptional.get().getCustomerID().equals(oldCustomer.getCustomerID()))){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                    "This phone number has been used for another customer with name: " + 
                    phoneCustomerOptional.get().getName());
                }
            }
            bean.copyProperties(oldCustomer, customer);
            this.customerRepository.save(oldCustomer);
            return oldCustomer;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer not found");
    }
}
