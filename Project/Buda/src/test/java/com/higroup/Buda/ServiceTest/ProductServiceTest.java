package com.higroup.Buda.ServiceTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.ProductGroupRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.services.ProductService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;



// @ExtendWith(MockitoExtension.class)

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductServiceTest {
    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");

    @Autowired
    private ProductRepository productRepository;
    private ProductService productService;

    @Autowired
    private static UserRepository userRepository;
    @Autowired
    private ProductGroupRepository productGroupRepository;
    private static User user;

    @AfterEach
    public void tearDown(){
        productRepository.deleteAll();
    }

    public static Product product;

    @BeforeEach
    public void Setup(){
        productService = new ProductService(productRepository, productGroupRepository, userRepository);
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

        product = new Product();
        product.setAmountLeft(200);
        product.setCostPerUnit(50000.0);
        product.setDescription("Chicken, mushrooms");
        product.setName("Fried Chicken");
        product.setSellingPrice(75000.0);

        userRepository.save(user);
        product.setUserID(user.getUserID());
    }
    @Test
    public void canResigterNewproduct(){
        Long userID = user.getUserID();
        long sizebeforeUpdate = productRepository.count();

        ResponseEntity<?> res =  productService.registerNewProduct(userID, product);

        // check
        assertEquals(res.getBody().toString(), product.toString());
        assertEquals(sizebeforeUpdate + 1, productRepository.count());   
        Product new_product = productRepository.findProductByProductID(product.getProductID()).get();
        
        assertEquals(new_product, product);
    }

    @Test 
    public void canFindAllbyUserID(){
        Long userID = user.getUserID();
        productRepository.save(product);

        // check
        List<Product> list = productService.findAllProductByUserID(userID);

        assertEquals(list, Arrays.asList(product));
    }
}