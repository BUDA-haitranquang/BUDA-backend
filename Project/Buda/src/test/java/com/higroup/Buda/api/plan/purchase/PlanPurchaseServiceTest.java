package com.higroup.Buda.api.plan.purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.Purchase;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class PlanPurchaseServiceTest {
    @Autowired
    PlanPurchaseService planPurchaseService;
    @Test
    void testCreateNewPurchase() {

    }

    @Test
    void testFindAllByUserID() {
        for (long userID = 0; userID < 3; userID++)
        {
            List<Purchase> purchases = this.planPurchaseService.findAllByUserID(Long.valueOf(userID)); 
            for (Purchase purchase: purchases){
                assertEquals(purchase.getUser().getUserID(), Long.valueOf(userID));
            }
        }
    }
}
