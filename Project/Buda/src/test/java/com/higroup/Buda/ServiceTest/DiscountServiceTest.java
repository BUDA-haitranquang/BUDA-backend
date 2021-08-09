package com.higroup.Buda.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.repositories.DiscountRepository;
import com.higroup.Buda.services.DiscountService;

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
public class DiscountServiceTest {
    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");

    @Autowired
    private DiscountRepository discountRepository;

    private DiscountService discountService;

    @AfterEach
    public void tearDown(){
        discountRepository.deleteAll();
    }

    public static Discount discount;

    @BeforeEach
    void Setup(){
        discountService = new DiscountService(discountRepository, null);
    }

    @BeforeAll
    public static void initializeDB()
    {
        discount = new Discount();
        discount.setDescription("default");
        discount.setName("default");
        discount.setOrderCount(0);
        discount.setCash(0.0);
        discount.setCashLimit(0.0);
        discount.setPercentage(0.0);
    }
}