package com.higroup.Buda.api.discount.view;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.repositories.DiscountRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class ViewDiscountServiceTest {
    @Autowired
    private ViewDiscountService viewDiscountService;
    
    @Autowired
    private DiscountRepository discountRepository;
    @Test
    public void testFindAllDiscountByUserID() {
        for(long userID = 0; userID < 3; userID++) {
            List<Discount> discounts = this.viewDiscountService.findAllDiscountByUserID(Long.valueOf(userID));
            for(Discount discount : discounts) {
                assertEquals(discount.getUserID(), Long.valueOf(userID));
            }
        }
    }

    @Test
    public void testFindDiscountByDiscountID() {
    //     for (long i=0; i < 60; i++)
    //     {
    //     for(long userID = 0; userID < 3; userID++)
    //     {
    //         Optional<Discount> discountOptional = this.discountRepository.findDiscountByDiscountID(Long.valueOf(i));
    //         assertEquals(discountOptional.get().getDiscountID(), Long.valueOf(i));
    //     }
    // }
    }
}
