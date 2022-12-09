package com.higroup.Buda.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.Discount;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)

public class DiscountRepositoryTest {
    @Autowired
    private DiscountRepository discountRepository;

    @Test
    void testFindAllDiscountByUserID() {
        for (long i=0; i < 3; i++)
        {
            List<Discount> discounts = discountRepository.findAllDiscountByUserID(Long.valueOf(i));
            for (Discount discount: discounts)
            {
                assertEquals(discount.getUserID(), Long.valueOf(i));
            }
        }
    }

    @Test
    void testFindDiscountByDiscountID() {
        for (long i=0; i < 60; i++)
        {
            Optional<Discount> discountOptional = this.discountRepository.findDiscountByDiscountID(Long.valueOf(i));
            if (discountOptional.isPresent()){
                assertEquals(discountOptional.get().getDiscountID(), Long.valueOf(i));
            }
        }
    }
}
