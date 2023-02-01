package com.higroup.Buda.api.customer.membership;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.entities.MembershipType;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.DiscountRepository;
import com.higroup.Buda.repositories.MembershipTypeRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerMembershipService {
    private final MembershipTypeRepository membershipTypeRepository;
    private final UserRepository userRepository;
    private final DiscountRepository discountRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerMembershipService(MembershipTypeRepository membershipTypeRepository, UserRepository userRepository, DiscountRepository discountRepository, CustomerRepository customerRepository)
    {
        this.membershipTypeRepository = membershipTypeRepository;
        this.userRepository = userRepository;
        this.discountRepository = discountRepository;
        this.customerRepository = customerRepository;
    }
    @Autowired
    public PresentChecker presentChecker;
    public List<MembershipType> findAllByUserID(Long userID)
    {
        presentChecker.checkId(userID);
        return this.membershipTypeRepository.findAllByUserID(userID);
    }
    public Optional<MembershipType> findMembershipTypeByMembershipTypeID(Long membershipTypeID)
    {
        presentChecker.checkIdAndRepository(membershipTypeID, this.membershipTypeRepository);
        return this.membershipTypeRepository.findMembershipTypeByMembershipTypeID(membershipTypeID);
    }
    @Transactional
    public MembershipType createNewMembershipType(Long userID, MembershipType membershipType)
    {
        Optional<Discount> discountOptional = this.discountRepository.findDiscountByDiscountID(membershipType.getDiscount().getDiscountID());
        if (discountOptional.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "discount not found");
        }
        Discount discount = discountOptional.get();
        if (!discount.getUserID().equals(userID)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "discount not belong to user");
        }
        else
        {
            membershipType.setUserID(userID);
        }
        this.membershipTypeRepository.save(membershipType);
        return membershipType;
    }
//    @Transactional
//    public List<Customer> updateMembershipType(Long userID, Long membershipTypeID)
//    {
//        Optional<MembershipType> membershipType = this.membershipTypeRepository.findMembershipTypeByMembershipTypeID(membershipTypeID);
//        if (membershipType.isEmpty())
//        {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Membership Type not found");
//        }
//        List<Customer> customers = this.customerRepository.findAllByUserID(userID);
//        List<Customer> changedCustomers = new ArrayList<>();
//        for (Customer customer : customers)
//        {
//            Optional<MembershipType> membershipType1 = this.membershipTypeRepository.findMembershipTypeByMembershipTypeID(customer.getMembershipID());
//            if (membershipType1.isPresent())
//            {
//                if (membershipType1.get().getMinimumSpend() > membershipType.get().getMinimumSpend()) continue;
//                if (membershipType.get().getMinimumSpend() > customer.getTotalSpend()) continue;
//                customer.setMembershipID(membershipTypeID);
//                changedCustomers.add(customer);
//            }
//        }
//        this.customerRepository.saveAll(changedCustomers);
//        return changedCustomers;
//    }
    @Transactional
    public List<Customer> updateMembershipType(Long userID)
    {
        List<Customer> customers = this.customerRepository.findAllByUserID(userID);
        List<Customer> changedCustomers = new ArrayList<>();
        /*
        Hard code membership type for all user
        Silver: >= 100000
        Gold: >= 1000000
        Platinum: >= 10000000
         */
        for (Customer customer : customers)
        {
            if (customer.getTotalSpend() >= 10000000)
            {
                customer.setMembershipID(3L);
                changedCustomers.add(customer);
            }
            else if (customer.getTotalSpend() >= 1000000)
            {
                customer.setMembershipID(2L);
                changedCustomers.add(customer);
            }
            else if (customer.getTotalSpend() >= 100000)
            {
                customer.setMembershipID(1L);
                changedCustomers.add(customer);
            }
        }
        this.customerRepository.saveAll(changedCustomers);
        return changedCustomers;
    }
}
