package com.higroup.Buda.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.repositories.SupplierRepository;
import com.higroup.Buda.services.SupplierService;

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
public class SupplierServiceTest {
    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");

    @Autowired
    private SupplierRepository supplierRepository;

    private SupplierService supplierService;

    @AfterEach
    public void tearDown(){
        supplierRepository.deleteAll();
    }


    public static Supplier supplier;

    @BeforeEach
    void Setup(){
        supplierService = new SupplierService(supplierRepository);
    }

    @BeforeAll
    public static void initializeDB()
    {
        supplier = new Supplier();
        supplier.setAddress("default");
        supplier.setEmail("123456789@gmail.com");
        supplier.setName("default");
        supplier.setPhoneNumber("0123456789");
    }
}