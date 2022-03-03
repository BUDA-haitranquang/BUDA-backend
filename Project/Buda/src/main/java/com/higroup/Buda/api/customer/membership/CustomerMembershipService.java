package com.higroup.Buda.api.customer.membership;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.entities.MembershipType;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.enumeration.DiscountType;
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

    @Autowired
    public CustomerMembershipService(MembershipTypeRepository membershipTypeRepository, UserRepository userRepository, DiscountRepository discountRepository) 
    {
        this.membershipTypeRepository = membershipTypeRepository;
        this.userRepository = userRepository;
        this.discountRepository = discountRepository;
    }
    @Autowired
    public PresentChecker presentChecker;
    public List<MembershipType> findAllByUserID(Long userID)
    {
        presentChecker.checkId(userID);
        return this.membershipTypeRepository.findAllByUserID(userID);
    }
    public MembershipType findMembershipTypeByMembershipTypeID(Long membershipTypeID)
    {
        presentChecker.checkIdAndRepository(membershipTypeID, this.membershipTypeRepository);
        return this.membershipTypeRepository.findMembershipTypeByMembershipTypeID(membershipTypeID);
    }
    @Transactional
    public MembershipType createNewMembershipType(Long userID, MembershipType membershipType)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
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
}
