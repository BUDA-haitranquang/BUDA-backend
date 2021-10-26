package com.higroup.Buda.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.OtherCost;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.OtherCostRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
public class OtherCostRepositoryTest {

    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");
                    
    @Autowired
    private OtherCostRepository othercostReposTest;
    
    @Autowired
    private UserRepository userReposTest;


    private OtherCost newOtherCost;
    private User newUser;
    @BeforeEach
    public void setUp(){

        newUser = new User();
        newUser.setEmail("haitq@gmail.com");
        newUser.setFirstName("Hai");
        newUser.setLastName("Tran");
        newUser.setPassword("BBBBBasdsadBB");
        newUser.setPhoneNumber("21312313");
        newUser.setUserName("haihoho");
        userReposTest.save(newUser);

        newOtherCost = new OtherCost();
        newOtherCost.setName("VAT");
        newOtherCost.setDescription("Testing");
        newOtherCost.setUserID(newUser.getUserID());
        newOtherCost.setTotalCost(30000.0);
        othercostReposTest.save(newOtherCost);
    }
    
    @AfterEach
    public void tearDown(){
        userReposTest.deleteAll();
        othercostReposTest.deleteAll();
    }

    @Test 
    public void canFindOtherCostbyOtherCostID(){
        // Long OtherCostID = newOtherCost.getOtherCostID();

        // Optional<OtherCost> res = othercostReposTest.findOtherCostByOtherCostID(OtherCostID);
        // assertEquals(res.get().getOtherCostID(), newOtherCost.getOtherCostID());
    }

    @Test 
    public void canFindAllbyUserID(){
        Long userID = newUser.getUserID();
        List<OtherCost> list = othercostReposTest.findAllByUserID(userID);

        assertEquals(list, Arrays.asList(newOtherCost));
    }
}