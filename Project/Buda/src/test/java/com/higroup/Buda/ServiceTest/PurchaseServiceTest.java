package com.higroup.Buda.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import com.higroup.Buda.entities.Purchase;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.PurchaseRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.services.PurchaseService;

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
public class PurchaseServiceTest {
    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");

    @Autowired
    private PurchaseRepository purchaseRepository;

    private PurchaseService purchaseService;
    @Autowired
    private static UserRepository userRepository;

    private static User user;
    @AfterEach
    public void tearDown(){
        purchaseRepository.deleteAll();
    }


    public static Purchase purchase;

    @BeforeEach
    void Setup(){
        purchaseService = new PurchaseService(purchaseRepository, null);
    }

    @BeforeAll
    public static void initializeDB()
    {
        user = new User();
        user.setEmail("haitq@gmail.com");
        user.setFirstName("Hai");
        user.setLastName("Tran");
        user.setPassword("BBBBBasdsadBB");
        user.setPhoneNumber("21312313");
        user.setUserName("haihoho");

        purchase = new Purchase();
        purchase.setMessage("Demo");
        purchase.setPurchaseID((long)1);
        purchase.setTotalCost(250000);

        userRepository.save(user);
    }
}
