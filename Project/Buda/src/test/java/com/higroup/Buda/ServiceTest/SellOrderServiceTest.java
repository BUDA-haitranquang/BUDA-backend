package com.higroup.Buda.ServiceTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.AgeGroup;
import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.Gender;
import com.higroup.Buda.entities.SellOrder;
import com.higroup.Buda.entities.SellOrderItem;
import com.higroup.Buda.entities.Status;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.SellOrderItemRepository;
import com.higroup.Buda.repositories.SellOrderRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.services.CustomerService;
import com.higroup.Buda.services.SellOrderItemService;
import com.higroup.Buda.services.SellOrderService;
import com.higroup.Buda.services.UserService;

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
public class SellOrderServiceTest {
    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");
    @Autowired
    private SellOrderRepository sellOrderRepository;
    private SellOrderService sellOrderService;
    @Autowired
    private static UserRepository userRepository;
    private static User user;
    @Autowired
    private CustomerRepository customerRepository;
    private Customer customer;
    @Autowired
    private SellOrderItemRepository sellOrderItemRepository;
    private SellOrderItem sellOrderItem;

    @AfterEach
    public void tearDown(){
        sellOrderRepository.deleteAll();
    }
    private static SellOrder sellOrder;

    @BeforeEach
    public void Setup(){
        sellOrderService = new SellOrderService(sellOrderRepository, customerRepository, userRepository, sellOrderItemRepository);
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
        userRepository.save(user);

        sellOrder = new SellOrder();
        sellOrder.setAgeGroup(AgeGroup.FROM_18_TO_24);
        sellOrder.setGender(Gender.MALE);
        sellOrder.setStatus(Status.RECEIVING);
        sellOrder.setMessage("Thank you");
        sellOrder.setFinalCost(310000.0);
        sellOrder.setRealCost(400000.0);
        sellOrder.setUserID(user.getUserID());
    }
    @Test
    public void canResigterNewSellOrder(){
        Long userID = user.getUserID();
        long sizebeforeUpdate = sellOrderRepository.count();

        ResponseEntity<?> res =  sellOrderService.registerNewSellOrder(userID, sellOrder);

        // check
        assertEquals(res.getBody().toString(), sellOrder.toString());
        assertEquals(sizebeforeUpdate + 1, sellOrderRepository.count());   
        SellOrder new_SellOrder = sellOrderRepository.findSellOrderBySellOrderID(sellOrder.getSellOrderID()).get();
        
        assertEquals(new_SellOrder, sellOrder);
    }

    @Test 
    public void canFindAllbyUserID(){
        Long userID = user.getUserID();
        sellOrderRepository.save(sellOrder);

        // check
        List<SellOrder> list = sellOrderService.findAllSellOrderByUserID(userID);

        assertEquals(list, Arrays.asList(sellOrder));
    }
}