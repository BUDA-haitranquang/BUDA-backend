package com.higroup.Buda.api.discount.delete;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.entities.enumeration.DiscountType;
import com.higroup.Buda.repositories.DiscountRepository;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)

public class DeleteDiscountServiceTest {
    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private DeleteDiscountService deleteDiscountService;

    @Test
    public void testDeleteDiscount() {
        Discount discount= new Discount();
        discount.setName("testing");
        discount.setDescription("testing");
        discount.setDiscountType(DiscountType.PERCENTAGE_ONLY);
        discount.setPercentage(10.0);
        discount.setUserID(2L);
        discountRepository.save(discount);
        int firstSize= discountRepository.findAll().size();
        deleteDiscountService.deleteDiscount(2L, discount.getDiscountID());
        int laterSize= discountRepository.findAll().size();
        assertEquals(firstSize-1,laterSize);
    }
}
