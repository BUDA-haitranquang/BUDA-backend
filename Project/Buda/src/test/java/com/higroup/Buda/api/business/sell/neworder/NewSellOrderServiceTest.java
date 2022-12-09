package com.higroup.Buda.api.business.sell.neworder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.enumeration.AgeGroup;
import com.higroup.Buda.entities.enumeration.Gender;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class NewSellOrderServiceTest {
    
    @Autowired 
    private CustomerRepository customerRepository;

    @Autowired 
    private NewSellOrderService newSellOrderService;

    @Autowired 
    private ProductRepository productRepository;
    
    @BeforeEach
    void prepareData()
    {   
        // customer
        Customer customer = new Customer();
        customer.setName("Nguyen Hoang Vu");
        customer.setAgeGroup(AgeGroup.FROM_18_TO_24);
        customer.setGender(Gender.MALE);
        customer.setPhoneNumber("0367185666");
        customer.setUserID(2l);
        customer.setTotalSpend(0.0);
        customerRepository.save(customer);
        
    }

    @Test
    // @Transactional
    void testDeleteSellOrderBySellOrderID() {
        Optional<Customer> customer = customerRepository.findCustomerByUserIDAndPhoneNumber(2l, "0367185666");

        assertNull(customer.get().getTotalSpend());
        assertEquals(true, customer.isPresent());
    }

    @Test
    @Transactional
    void testDeleteSellOrderItem() {
        // Customer customer = new Customer();
        // customer.setName("Nguyen Hoang Vu");
        // customer.setAgeGroup(AgeGroup.FROM_18_TO_24);
        // customer.setGender(Gender.MALE);
        // customer.setPhoneNumber("0367185666");
        // customer.setUserID(2l);
        // customerRepository.save(customer);
        // assertNull(customer.getTotalSpend());
    }

    @Test
    void testEditProductQuantity() {
        
    }

    @Test
    void testRegisterNewSellOrderItem() {
    }

    @Test
    @Transactional
    void testRegisterSellOrder() {
        // customer 
        Optional<Customer> customerOptional = customerRepository.findCustomerByUserIDAndPhoneNumber(2l, "0367185666");
        assertEquals(true, customerOptional.isPresent());
        Customer customer = customerOptional.get();

        // create sellOrderDTO
        SellOrderDTO sellOrderDTO = new SellOrderDTO();
        sellOrderDTO.setAddress("hai ba trung ha noi");
        sellOrderDTO.setCustomer(customer);
        sellOrderDTO.setCustomerMessage("can than do de vo");
        sellOrderDTO.setStatus(Status.FINISHED);

        Double totalPrice = 0.0;

        SellOrderItemDTO sellOrderItemDTO1 = new SellOrderItemDTO();
        sellOrderItemDTO1.setProductID(1l);
        sellOrderItemDTO1.setQuantity(10);
        totalPrice += productRepository.findProductByProductID(1l).get().getSellingPrice()*10;

        SellOrderItemDTO sellOrderItemDTO2 = new SellOrderItemDTO();
        sellOrderItemDTO2.setProductID(2l);
        sellOrderItemDTO2.setQuantity(10);
        totalPrice += productRepository.findProductByProductID(2l).get().getSellingPrice()*10;

        SellOrderItemDTO sellOrderItemDTO3 = new SellOrderItemDTO();
        sellOrderItemDTO3.setProductID(10l);
        sellOrderItemDTO3.setQuantity(10);
        totalPrice += productRepository.findProductByProductID(10l).get().getSellingPrice()*10;

        sellOrderDTO.setSellOrderItemDTOs(Arrays.asList(sellOrderItemDTO1, sellOrderItemDTO2, sellOrderItemDTO3));

        Double beforeCustomerUse = customer.getTotalSpend();
        SellOrder sellOrder = newSellOrderService.registerSellOrder(2l, sellOrderDTO);
        
        
        // check total Price
        assertEquals(sellOrder.getRealCost(), totalPrice);
        assertEquals(beforeCustomerUse + totalPrice, customer.getTotalSpend());
    }

    @Test
    void testUpdateSellOrder() {
        
    }
}
