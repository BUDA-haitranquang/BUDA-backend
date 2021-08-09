package com.higroup.Buda.ServiceTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import com.higroup.Buda.entities.Plan;
import com.higroup.Buda.repositories.PlanRepository;
import com.higroup.Buda.services.PlanService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;



// @ExtendWith(MockitoExtension.class)

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlanServiceTest {
    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");

    @Autowired
    private PlanRepository planRepository;
    private PlanService planService;

    @AfterEach
    public void tearDown(){
        planRepository.deleteAll();
    }

    public static Plan plan;

    @BeforeEach
    void Setup(){
        planService = new PlanService(planRepository);
    }

    @BeforeEach
    public void initializeDB()
    {
        plan = new Plan();
        plan.setName("Premium");
        plan.setDescription("");
        plan.setPrice(350000);
        plan.setDuration(3);
        
    }

    @Test 
    public void canAddPlan(){
        long sizebeforeUpdate = planRepository.count();
        planService.addPlan(plan);

        assertEquals(sizebeforeUpdate + 1, planRepository.count());
    }

    @Test 
    public void canDeletePlanbyID(){
        planRepository.save(plan);
        long sizebeforeUpdate = planRepository.count();

        planService.deletePlanByID(plan.getPlanID());

        assertEquals(sizebeforeUpdate - 1, planRepository.count());
    }
}
