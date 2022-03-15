package com.higroup.Buda.api.business.sell.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.SellOrder;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class ViewNewSellOrderServiceTest {
    @Autowired
    private ViewSellOrderService viewNewSellOrderService;
    @Test
    void testFindAllIIncompletedSellOrderByUserID() {

    }

    @Test
    void testFindAllSellOrderByCustomerID() {

    }

    @Test
    void testFindAllSellOrderByUserAndStatus() {

    }

    @Test
    void testFindAllSellOrderByUserID() {
        List<SellOrder> sellOrders = this.viewNewSellOrderService.findAllSellOrderByUserID(2l);
        for (SellOrder sellOrder: sellOrders){
            assertEquals(sellOrder.getUserID(), 2l);
        }
    }

    @Test
    void testFindAllSellOrderByUserIDLastXDays() {

    }
}
