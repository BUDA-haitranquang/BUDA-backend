package com.higroup.Buda.api.business.payslip.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.PaySlip;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class ViewPaySlipServiceTest {
    private ViewPaySlipService viewPaySlipService;
    @Test
    void testFindAllPaySlipByUserID() {
        List<PaySlip> paySlips = this.viewPaySlipService.findAllPaySlipByUserID(2l);
        for (PaySlip paySlip: paySlips){
            assertEquals(paySlip.getUserID(), 2l);
        }
    }

    @Test
    void testFindAllPaySlipByUserID2() {
        
    }
}
