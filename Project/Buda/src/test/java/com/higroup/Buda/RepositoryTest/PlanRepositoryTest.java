package com.higroup.Buda.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.higroup.Buda.entities.Plan;
import com.higroup.Buda.repositories.PlanRepository;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlanRepositoryTest {
    // @Container
    // MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
    //                 .withDatabaseName("new_db")
    //                 .withUsername("testuser")
    //                 .withPassword("pass");

    @Autowired
    private PlanRepository planReposTest;

    private Plan plan;

    @AfterEach
    public void tearDown(){
        planReposTest.deleteAll();
    }

    @BeforeEach 
    public void setUp(){
        plan = new Plan();
        plan.setName("Premium");
        plan.setDescription("");
        plan.setPrice(350000);
        plan.setDuration(3);
        planReposTest.save(plan);
    }

    @Test
    void testFindPlanByPlanId(){
        Long planID = plan.getPlanID();

        boolean exist = planReposTest.findPlanByPlanID(planID).isPresent();
        // assertEquals(exist, true);
        assertEquals(exist, true);
    }
}
