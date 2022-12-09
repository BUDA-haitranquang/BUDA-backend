package com.higroup.Buda.api.customer.membership;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.MembershipType;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class CustomerMembershipServiceTest {

    @Autowired
    private CustomerMembershipService customerMembershipService;

    @Test
    void testCreateNewMembershipType() {

    }

    @Test
    void testFindAllByUserID() {
        for (long userID = 0; userID < 3; userID++) {
            List<MembershipType> membershipTypes = this.customerMembershipService.findAllByUserID(Long.valueOf(userID));
            for (MembershipType membershipType : membershipTypes)  {
                assertEquals(membershipType.getUserID(), Long.valueOf(userID));
            }
        }
    }

    @Test
    void testFindMembershipTypeByMembershipTypeID() {

    }
}
