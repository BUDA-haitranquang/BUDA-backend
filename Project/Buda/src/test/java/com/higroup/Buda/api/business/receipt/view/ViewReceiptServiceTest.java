package com.higroup.Buda.api.business.receipt.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.Receipt;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class ViewReceiptServiceTest {
    @Autowired
    private ViewReceiptService viewReceiptService;
    @Test
    void testFindAllReceiptByUserID() {
        List<Receipt> receipts = this.viewReceiptService.findAllReceiptByUserID(2l);
        for (Receipt receipt: receipts){
            assertEquals(receipt.getUserID(), 2l);
        }
    }
}
