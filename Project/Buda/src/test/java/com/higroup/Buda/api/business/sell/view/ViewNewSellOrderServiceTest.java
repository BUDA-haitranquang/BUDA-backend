package com.higroup.Buda.api.business.sell.view;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.higroup.Buda.BudaApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class ViewNewSellOrderServiceTest {
    @Test
    void testFindAllIIncompletedSellOrderByUserID() {

    }

    @Test
    void testFindAllSellOrderByCustomerID() {

    }

    @Test
    void testFindAllSellOrderByUserAndStatus() {

    }

    // @Test
    // void testFindAllSellOrderByUserID() {
    //     List<SellOrder> sellOrders = this.viewNewSellOrderService.findAllSellOrderByUserID(2l);
    //     for (SellOrder sellOrder: sellOrders){
    //         assertEquals(sellOrder.getUserID(), 2l);
    //     }
    // }

    @Test
    void testFindAllSellOrderByUserIDLastXDays() {

    }
}
