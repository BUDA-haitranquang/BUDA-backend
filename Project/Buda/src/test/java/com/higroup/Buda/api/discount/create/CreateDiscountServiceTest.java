package com.higroup.Buda.api.discount.create;

import static org.junit.Assert.assertEquals;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.entities.enumeration.DiscountType;
import com.higroup.Buda.repositories.DiscountRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class CreateDiscountServiceTest {
    @Autowired
    private DiscountRepository discountRepository;

    @Test
    public void testCreateDiscount() {
        int firstSize= discountRepository.findAll().size();
        Discount discount= new Discount();
        discount.setName("testing");
        discount.setDescription("testing");
        discount.setDiscountType(DiscountType.PERCENTAGE_ONLY);
        discount.setPercentage(10.0);
        discountRepository.save(discount);
        int laterSize= discountRepository.findAll().size();
        assertEquals(firstSize+1,laterSize);
    }
}
