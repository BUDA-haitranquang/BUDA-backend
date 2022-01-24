package com.higroup.Buda.api.product.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.Product;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class CRUDProductServiceTest {
    @Autowired
    private ProductViewService productService;
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
        List<Product> products = productService.findAllProductByUserID(2l);
        for (Product product: products)
        {
            assertEquals(product.getUserID(), 2l);
            assertEquals(product.getVisible(), Boolean.TRUE);
        }
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
