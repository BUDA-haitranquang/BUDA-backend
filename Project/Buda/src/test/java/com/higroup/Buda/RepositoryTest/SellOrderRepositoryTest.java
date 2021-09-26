package com.higroup.Buda.RepositoryTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.AgeGroup;
import com.higroup.Buda.entities.Gender;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.Status;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.SellOrderRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
public class SellOrderRepositoryTest {
    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");
    @Autowired
    private SellOrderRepository sellorderRepoTest;
    @Autowired
    private UserRepository userReposTest;

    private User newUser;
    private SellOrder newSellOrder;

    @BeforeEach
    public void SetUp(){
        newUser = new User();
        newUser.setEmail("linhnn@gmail.com");
        newUser.setFirstName("Linh");
        newUser.setLastName("Hai");
        newUser.setPassword("BAehrfenwiof");
        newUser.setPhoneNumber("9876543210");
        newUser.setUserName("nnlinh");
        userReposTest.save(newUser);
  
        newSellOrder = new SellOrder();
        newSellOrder.setStatus(Status.FINISHED);
        newSellOrder.setAgeGroup(AgeGroup.FROM_24_TO_30);
        newSellOrder.setFinalCost(420000.0);
        newSellOrder.setGender(Gender.MALE);
        newSellOrder.setCustomerMessage("Excellent");
        newSellOrder.setRealCost(420000.0);
        newSellOrder.setUserID(newUser.getUserID());
    }

    @AfterEach
    public void tearDown(){
        sellorderRepoTest.deleteAll();
        userReposTest.deleteAll();
    }

    @Test 
    public void canFindSellOrderbySellOrderID(){
        Long SellOrderID = newSellOrder.getSellOrderID();

        Optional<SellOrder> res = sellorderRepoTest.findSellOrderBySellOrderID(SellOrderID);
        assertEquals(res.get().getSellOrderID(), newSellOrder.getSellOrderID());
    }

    @Test 
    public void canFindAllbyUserID(){
        Long userID = newUser.getUserID();
        List<SellOrder> list = sellorderRepoTest.findAllSellOrderByUserID(userID);

        assertEquals(list, Arrays.asList(newSellOrder));
    }
}
