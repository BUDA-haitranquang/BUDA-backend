package com.higroup.Buda.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.DiscountRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.MySQLContainer;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DiscountRepositoryTest {
    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");

    @Autowired
    private DiscountRepository discountReposTest;
    @Autowired
    private UserRepository userReposTest;

    private Discount newDiscount;
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

        newDiscount = new Discount();
        newDiscount.setCash(20000.0);
        newDiscount.setCashLimit(30000.0);
        newDiscount.setDescription("demo");
        newDiscount.setName("Alpha");
        newDiscount.setOrderCount(1);
        newDiscount.setUserID(newUser.getUserID());
        discountReposTest.save(newDiscount);
    }

    @AfterEach
    public void tearDown(){
        discountReposTest.deleteAll();
        userReposTest.deleteAll();
    }

    @Test 
    public void canFindDiscountbyDiscountID(){
        Long DiscountID = newDiscount.getDiscountID();

        Optional<Discount> res = discountReposTest.findDiscountByDiscountID(DiscountID);
        assertEquals(res.get().getDiscountID(), newDiscount.getDiscountID());
    }

    @Test 
    public void canFindAllbyUserID(){
        Long userID = newUser.getUserID();
        List<Discount> list = discountReposTest.findAllDiscountByUserID(userID);

        assertEquals(list, Arrays.asList(newDiscount));
    }
}
