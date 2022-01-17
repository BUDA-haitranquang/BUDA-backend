package com.higroup.Buda.api.product.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.services.ProductService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class CRUDProductServiceTest {
    @Autowired
    private ProductService productService;
    @Autowired 
    private 
    
    @Test
    void testDeleteProductByProductID() {
        
    }
    @Test
    void testEditProduct() {
        
    }
    @Test
    void testEditProductQuantity() {
        
    }
    @Test
    void testFindAlertAmountProduct() {
        
    }
    @Test
    void testFindAllHiddenProductByUserID() {
        
    }
    @Test
    void testFindAllProductByProductGroupID() {
        
    }
    @Test
    void testFindAllProductByUserID() {
        int size = productService.findAllProductByUserID(2l).size();
        assertEquals(size, 245);
    }
    @Test
    void testFindProductByProductID() {
        
    }
    @Test
    void testHideProductByProductID() {
        
    }
    @Test
    void testRegisterNewProduct() {
        
    }
    @Test
    void testUpdateProductbyProductID() {
        
    }
}
