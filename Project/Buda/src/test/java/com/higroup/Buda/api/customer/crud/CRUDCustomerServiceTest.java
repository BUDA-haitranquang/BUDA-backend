package com.higroup.Buda.api.customer.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.Customer;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class CRUDCustomerServiceTest {
    @Autowired
    private CRUDCustomerService crudCustomerService;
    @Test
    void testFindAllByUserID() {
        List<Customer> customers = this.crudCustomerService.findAllByUserID(2l);
        for (Customer customer: customers){
            assertEquals(customer.getUserID(), 2l);
        }
    }

    @Test
    void testFindCustomerByUserIDAndPhoneNumber() {

    }

    @Test
    void testHideCustomer() {

    }

    @Test
    void testRegisterNewCustomer() {

    }

    @Test
    void testUpdateCustomer() {

    }
}
