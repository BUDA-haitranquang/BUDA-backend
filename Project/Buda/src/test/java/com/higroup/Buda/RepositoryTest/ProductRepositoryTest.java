package com.higroup.Buda.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.ProductRepository;
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
public class ProductRepositoryTest {
    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");

    @Autowired
    private ProductRepository productReposTest;
    @Autowired
    private UserRepository userReposTest;

    private Product newProduct;
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

        newProduct = new Product();
        newProduct.setName("Fried Egg");
        newProduct.setAmountLeft(50);
        newProduct.setCostPerUnit(20000.0);
        newProduct.setDescription("Chicken egg");
        newProduct.setSellingPrice(30000.0);
        //newProduct.setProductGroupID((long)2);
        productReposTest.save(newProduct);
    }

    @AfterEach
    public void tearDown(){
        productReposTest.deleteAll();
        userReposTest.deleteAll();
    }

    @Test 
    public void canFindProductbyProductID(){
        Long ProductID = newProduct.getProductID();
        Product res = productReposTest.findProductByProductID(ProductID).get();
        assertEquals(res.getProductID(), newProduct.getProductID());
    }

    // @Test 
    // public void canFindAllbyUserID(){
    //     Long userID = newUser.getUserID();
    //     List<Product> list = productReposTest.findAllProductByUserID(userID);

    //     assertEquals(list, Arrays.asList(newProduct));
    // }
}
