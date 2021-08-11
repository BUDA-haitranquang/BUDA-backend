package com.higroup.Buda.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.ZonedDateTime;
import java.util.Optional;

import com.higroup.Buda.entities.Purchase;
import com.higroup.Buda.repositories.PurchaseRepository;

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
public class PurchaseRepositoryTest {

    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");

    @Autowired
    private PurchaseRepository purchaseReposTest;
    @Autowired
    private Purchase newPurchase;

    @BeforeEach
    public void Setup(){
        Purchase newpurchase = new Purchase();
        newpurchase.setMessage("New purchase");
        newpurchase.setPurchaseID((long)2);
        newpurchase.setTotalCost(350000.0);
        newpurchase.setCreationDate(ZonedDateTime.parse("2021-07-20T10:15:30+07:00"));
        purchaseReposTest.save(newpurchase);
    }

    @AfterEach
    public void tearDown(){
        purchaseReposTest.deleteAll();
    }

    @Test 
    public void canFindPurchasebyPurchaseID(){
        Long PurchaseID = newPurchase.getPurchaseID();

        Optional<Purchase> res = purchaseReposTest.findPurchaseByPurchaseID(PurchaseID);
        assertEquals(res.get().getPurchaseID(), newPurchase.getPurchaseID());
    }
}