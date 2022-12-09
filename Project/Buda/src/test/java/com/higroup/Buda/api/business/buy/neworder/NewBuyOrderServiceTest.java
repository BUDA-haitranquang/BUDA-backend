package com.higroup.Buda.api.business.buy.neworder;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.SupplierRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class NewBuyOrderServiceTest {

    @Autowired
    private SupplierRepository supplierRepository;

    @BeforeEach
    void prepareData()
    {
        Supplier supplier = new Supplier();
        supplier.setEmail("nguyenhoangvudtm23@gmail.com");
        supplier.setAddress("Hai Ba Trung");
        supplier.setPhoneNumber("0367185666");
        supplier.setUserID(2l);
        supplier.setName("Nguyen Hoang Vu");
        supplierRepository.save(supplier);
    }

    @Test
    void testCreateNewBuyOrder() {

    }

    @Test
    void testDeleteBuyOrderByBuyOrderID() {

    }
}
