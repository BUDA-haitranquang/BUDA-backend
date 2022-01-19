package com.higroup.Buda.api.cost.fixedcost.bill;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.FixedCostBill;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class FixedCostBillServiceTest {
    @Autowired
    private FixedCostBillService fixedCostBillService;
    @Test
    void testCreateNewFixedCostBill() {

    }

    @Test
    void testFindAllByFixedCostID() {

    }

    @Test
    void testFindAllByUserID() {
        List<FixedCostBill> fixedCostBills = this.fixedCostBillService.findAllByUserID(2l);
        for (FixedCostBill fixedCostBill: fixedCostBills){
            assertEquals(fixedCostBill.getUserID(), 2l);
        }
    }

    @Test
    void testFindAllFixedCostBillByUserIDLastXDays() {

    }

    @Test
    void testFindAllIncompletedFixedCostBillByUserID() {

    }

    @Test
    void testCreateNewFixedCostBill2() {
        
    }

    @Test
    void testFindAllByFixedCostID2() {
        
    }

    @Test
    void testFindAllByUserID2() {
        
    }

    @Test
    void testFindAllFixedCostBillByUserIDLastXDays2() {
        
    }

    @Test
    void testFindAllIncompletedFixedCostBillByUserID2() {
        
    }
}
