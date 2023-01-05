package com.higroup.Buda.api.customer.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.MembershipType;
import com.higroup.Buda.repositories.CustomerRepository;

import com.higroup.Buda.repositories.MembershipTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ViewCustomerService {
    private final CustomerRepository customerRepository;
    private final MembershipTypeRepository membershipTypeRepository;

    @Autowired
    public ViewCustomerService(CustomerRepository customerRepository, MembershipTypeRepository membershipTypeRepository) {
        this.customerRepository = customerRepository;
        this.membershipTypeRepository = membershipTypeRepository;
    }

    public List<CustomerViewDTO> findAllByUserID(Long userID)
    {
        List<Customer> customers =  this.customerRepository.findAllByUserID(userID);
        List<CustomerViewDTO> customerViews = new ArrayList<>();
        for (Customer customer : customers)
        {
            System.out.println(customer.getMembershipID() + " " + customer.getCustomerID());
            Optional<MembershipType> membershipType = this.membershipTypeRepository.findMembershipTypeByMembershipTypeID(customer.getMembershipID());
            if (membershipType.isEmpty())
            {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Membership Type not found");
            }
            customerViews.add(new CustomerViewDTO(customer, membershipType.get()));
        }
        return customerViews;
    }
}
