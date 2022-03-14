package com.higroup.Buda.api.product.quantitylog;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.ProductLeftLog;
import com.higroup.Buda.repositories.ProductLeftLogRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.StaffRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class ProductLeftLogServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ProductLeftLogRepository productLeftLogRepository;

    @Autowired
    private ProductLeftLogService productLeftLogService;
    
    @Test
    void testFindAllProductLeftLogByProduct() {
        for (long userID = 0; userID < 3; userID++)
        {
            for (long productID = 0; productID < productRepository.findAll().size(); productID++)
            { 
                List<ProductLeftLog> productLeftLogs = this.productLeftLogService.findAllProductLeftLogByProduct(Long.valueOf(userID),Long.valueOf(productID));
                for (ProductLeftLog productLeftLog : productLeftLogs){
                    assertEquals(productLeftLog.getUserID(), Long.valueOf(userID));
                    assertEquals(productLeftLog.getProduct().getProductID(), Long.valueOf(productID));
                }
            }
        }
    }

    @Test
    void testFindAllProductLeftLogByStaffID() {
        for (long staffID = 0; staffID < 500; staffID++)
        {
        for (long userID = 0; userID < 3; userID++)
        {
            { 
                List<ProductLeftLog> productLeftLogs = this.productLeftLogService.findAllProductLeftLogByStaffID(Long.valueOf(userID),Long.valueOf(staffID));
                for (ProductLeftLog productLeftLog : productLeftLogs){
                    assertEquals(productLeftLog.getStaffID(), Long.valueOf(staffID));
                }
            }
        }
        }
    }

    @Test
    void testFindAllProductLeftLogByUserID() {
        for (long userID = 0; userID < 3; userID++)
        {
            List<ProductLeftLog> productLeftLogs = this.productLeftLogService.findAllProductLeftLogByUserID(Long.valueOf(userID)); 
            for (ProductLeftLog productLeftLog : productLeftLogs){
                assertEquals(productLeftLog.getUserID(), Long.valueOf(userID));
            }
        }
    }

    @Test
    void testFindProductLeftLogByProductLeftLogID() {
            long userID=1L;
            for (long productLeftLogID = 0; productLeftLogID < productLeftLogRepository.findAll().size(); productLeftLogID++)
            { 
                ProductLeftLog productLeftLog = this.productLeftLogService.findProductLeftLogByProductLeftLogID(Long.valueOf(userID),Long.valueOf(productLeftLogID));
                assertEquals(productLeftLog.getProductLeftLogID(), Long.valueOf(productLeftLogID));;
            }
    }
}
