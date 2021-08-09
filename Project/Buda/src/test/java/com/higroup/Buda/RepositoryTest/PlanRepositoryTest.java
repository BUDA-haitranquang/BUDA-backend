package com.higroup.Buda.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.higroup.Buda.entities.Plan;
import com.higroup.Buda.repositories.PlanRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.MySQLContainer;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlanRepositoryTest {
    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");

    @Autowired
    private PlanRepository planReposTest;

    @AfterEach
    public void tearDown(){
        planReposTest.deleteAll();
    }
    @Test
    void testFindPlanByPlanId(){
        Long planID=(long)2;
        Plan newPlan = new Plan();
        newPlan.setName("Premium");
        newPlan.setDescription("");
        newPlan.setPrice(350000);
        newPlan.setDuration(3);
        planReposTest.save(newPlan);

        boolean exist = planReposTest.findPlanByPlanID(planID).isPresent();
        // assertEquals(exist, true);
        assertEquals(exist, true);
    }
}
