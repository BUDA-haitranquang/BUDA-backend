package com.higroup.Buda.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;

import com.higroup.Buda.entities.Discount;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.DiscountRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.services.DiscountService;

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
public class DiscountServiceTest {
    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");

    @Autowired
    private DiscountRepository discountRepository;

    private DiscountService discountService;

    @Autowired
    private static UserRepository userRepository;

    private static User user;

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
        user = new User();
        user.setEmail("haitq@gmail.com");
        user.setFirstName("Hai");
        user.setLastName("Tran");
        user.setPassword("BBBBBasdsadBB");
        user.setPhoneNumber("21312313");
        user.setUserName("haihoho");

        discount = new Discount();
        discount.setCash(30000.0);
        discount.setCashLimit(40000.0);
        discount.setDescription("10%, max 40000");
        discount.setName("First");

        userRepository.save(user);
        discount.setUserID(user.getUserID());
    }

    @Test
    public void canResigterNewdiscount(){
        Long userID = user.getUserID();
        long sizebeforeUpdate = discountRepository.count();

        ResponseEntity<?> res =  discountService.registerNewDiscount(userID, discount);

        // check
        assertEquals(res.getBody().toString(), discount.toString());
        assertEquals(sizebeforeUpdate + 1, discountRepository.count());   
        Discount new_discount = discountRepository.findDiscountByDiscountID(discount.getDiscountID()).get();
        
        assertEquals(new_discount, discount);
    }

    @Test 
    public void canFindAllbyUserID(){
        Long userID = user.getUserID();
        discountRepository.save(discount);

        // check
        List<Discount> list = discountService.findAllDiscountByUserID(userID);

        assertEquals(list, Arrays.asList(discount));
    }
}