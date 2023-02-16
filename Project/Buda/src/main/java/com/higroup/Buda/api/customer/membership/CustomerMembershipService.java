package com.higroup.Buda.api.customer.membership;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.entities.MembershipType;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.DiscountRepository;
import com.higroup.Buda.repositories.MembershipTypeRepository;
import com.higroup.Buda.util.Checker.PresentChecker;

@Service
public class CustomerMembershipService {
    private final MembershipTypeRepository membershipTypeRepository;
    private final DiscountRepository discountRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerMembershipService(MembershipTypeRepository membershipTypeRepository, DiscountRepository discountRepository, CustomerRepository customerRepository)
    {
        this.membershipTypeRepository = membershipTypeRepository;
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
    @Transactional
    public List<Customer> updateMembershipType(Long userID, Long membershipTypeID)
    {
        Optional<MembershipType> membershipType = this.membershipTypeRepository.findMembershipTypeByMembershipTypeID(membershipTypeID);
        if (membershipType.isEmpty() || !membershipType.get().getUserID().equals(userID))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Membership Type not found");
        }
        List<Customer> customers = this.customerRepository.findAllByUserID(userID);
        List<Customer> changedCustomers = new ArrayList<>();
        List<MembershipType> membershipTypes = this.membershipTypeRepository.findAllByUserID(userID);
        //TODO set up initial limit for number of membership type, change according to User Plan later
        if (membershipTypes.size() > 10)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Too many Membership Type");
        }
        for (Customer customer : customers)
        {
            if (membershipType.get().getMembershipTypeID().equals(customer.getMembershipID())) continue;
            for (MembershipType membershipType1 : membershipTypes)
            {
                if (!membershipType1.getMembershipTypeID().equals(customer.getMembershipID())) continue;
                if (membershipType1.getMinimumSpend() > membershipType.get().getMinimumSpend()) break;
                if (membershipType.get().getMinimumSpend() > customer.getTotalSpend()) break;
                customer.setMembershipID(membershipTypeID);
                changedCustomers.add(customer);
                break;
            }
        }
        this.customerRepository.saveAll(changedCustomers);
        return changedCustomers;
    }
}
