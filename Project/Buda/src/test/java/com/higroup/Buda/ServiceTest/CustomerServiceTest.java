package com.higroup.Buda.ServiceTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.services.CustomerService;

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
public class CustomerServiceTest {
    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");

    @Autowired
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @AfterEach
    public void tearDown(){
        customerRepository.deleteAll();
    }

    public static Customer customer;

    @BeforeEach
    void Setup(){
        customerService = new CustomerService(customerRepository);
    }

    @BeforeAll
    public static void initializeDB()
    {
        customer = new Customer();
        customer.setAddress("default");
        customer.setName("default");
        customer.setPhoneNumber("0123456789");
    }
}
