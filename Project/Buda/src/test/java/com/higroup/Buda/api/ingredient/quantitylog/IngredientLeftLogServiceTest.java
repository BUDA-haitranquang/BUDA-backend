package com.higroup.Buda.api.ingredient.quantitylog;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.IngredientLeftLog;
import com.higroup.Buda.repositories.IngredientLeftLogRepository;
import com.higroup.Buda.repositories.IngredientRepository;
import com.higroup.Buda.repositories.StaffRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class IngredientLeftLogServiceTest {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private IngredientLeftLogRepository ingredientLeftLogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IngredientLeftLogService ingredientLeftLogService;

    @Test
    void testFindAllIngredientLeftLogByIngredient() {
        for (long userID = 0; userID < 3; userID++)
        {
            for (long ingredientID = 0; ingredientID < ingredientRepository.findAll().size(); ingredientID++)
            { 
                List<IngredientLeftLog> ingredientLeftLogs = this.ingredientLeftLogService.findAllIngredientLeftLogByIngredient(Long.valueOf(userID),Long.valueOf(ingredientID));
                for (IngredientLeftLog ingredientLeftLog : ingredientLeftLogs){
                    assertEquals(ingredientLeftLog.getUserID(), Long.valueOf(userID));
                    assertEquals(ingredientLeftLog.getIngredient().getIngredientID(), Long.valueOf(ingredientID));
                }
            }
        }
    }

    @Test
    void testFindAllIngredientLeftLogByStaffID() {
            for(long staffID = 0; staffID < 500; staffID++)
            {
            for (long userID = 0; userID < 3; userID++)
            {
                List<IngredientLeftLog> ingredientLeftLogs = this.ingredientLeftLogService.findAllIngredientLeftLogByStaffID(Long.valueOf(userID),Long.valueOf(staffID));
                for (IngredientLeftLog ingredientLeftLog : ingredientLeftLogs){
                    assertEquals(ingredientLeftLog.getStaffID(), Long.valueOf(staffID));
                }
            }
            }
    }

    @Test
    void testFindAllIngredientLeftLogByUserID() {
        for (long userID = 0; userID < 3; userID++)
        {
            List<IngredientLeftLog> ingredientLeftLogs = this.ingredientLeftLogService.findAllIngredientLeftLogByUserID(Long.valueOf(userID)); 
            for (IngredientLeftLog ingredientLeftLog : ingredientLeftLogs){
                assertEquals(ingredientLeftLog.getUserID(), Long.valueOf(userID));
            }
        }
    }

    @Test
    void testFindIngredientLeftLogByIngredientLeftLogID() {
            for(long ingredientLeftLogID = 0; ingredientLeftLogID < 100; ingredientLeftLogID++){
            { 
                Optional<IngredientLeftLog> ingredientLeftLog = this.ingredientLeftLogRepository.findIngredientLeftLogByIngredientLeftLogID(Long.valueOf(ingredientLeftLogID));
                assertEquals(ingredientLeftLog.get().getIngredientLeftLogID(), Long.valueOf(ingredientLeftLogID));;
            }
        }
    }
}
