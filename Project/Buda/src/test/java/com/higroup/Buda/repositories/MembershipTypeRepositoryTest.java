package com.higroup.Buda.repositories;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.MembershipType;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class MembershipTypeRepositoryTest {
    @Autowired
    private MembershipTypeRepository membershipTypeRepository;

    @Test
    void testFindAllByUserID() {
        for (long i=0; i < 3; i++)
        {
            List<MembershipType> membershipTypes = membershipTypeRepository.findAllByUserID(Long.valueOf(i));
            for (MembershipType membershipType: membershipTypes)
            {
                assertEquals(membershipType.getUserID(), Long.valueOf(i));
            }
        }
    }

    @Test
    void testFindMembershipTypeByMembershipTypeID() {
        for (long i=0; i < 300; i++)
        {
            Optional<MembershipType> membershipTypeOptional = this.membershipTypeRepository.findMembershipTypeByMembershipTypeID(Long.valueOf(i));
            if (membershipTypeOptional.isPresent()){
                assertEquals(membershipTypeOptional.get().getMembershipTypeID(), Long.valueOf(i));
            }
        }
    }
}
