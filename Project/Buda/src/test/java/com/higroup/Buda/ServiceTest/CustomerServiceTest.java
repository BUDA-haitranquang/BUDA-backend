package com.higroup.Buda.ServiceTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.services.CustomerService;

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
public class CustomerServiceTest {
    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");

    @Autowired
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @Autowired
    private static UserRepository userRepository;

    private static User user;

    @AfterEach
    public void tearDown(){
        customerRepository.deleteAll();
    }

    public static Customer customer;

    @BeforeEach
    public void Setup(){
        customerService = new CustomerService(customerRepository);
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

        customer = new Customer();
        customer.setAddress("Hoang Hoa Tham");
        customer.setName("Nguyen Xuan Huy");
        customer.setPhoneNumber("0231874927");
        customer.setTotalSpend(500000.0);
        
        userRepository.save(user);
        customer.setUserID(user.getUserID());
    }
    @Test
    public void canResigterNewcustomer(){
        Long userID = user.getUserID();
        long sizebeforeUpdate = customerRepository.count();

        ResponseEntity<?> res =  customerService.registerNewCustomer(userID, customer);

        // check
        assertEquals(res.getBody().toString(), customer.toString());
        assertEquals(sizebeforeUpdate + 1, customerRepository.count());   
        Customer new_customer = customerRepository.findCustomerByCustomerID(customer.getCustomerID()).get();
        
        assertEquals(new_customer, customer);
    }

    @Test 
    public void canFindAllbyUserID(){
        Long userID = user.getUserID();
        customerRepository.save(customer);

        // check
        List<Customer> list = customerService.findAllByUserID(userID);

        assertEquals(list, Arrays.asList(customer));
    }

    @Test 
    public void canFindcustomerbyUserIDandPhoneNumber(){
        Long userID = user.getUserID();
        String phoneNumber = customer.getPhoneNumber();
        customerRepository.save(customer);

        // check
        ResponseEntity<?> res = customerService.findCustomerByUserIDAndPhoneNumber(userID, phoneNumber);
        assertEquals(res.getBody().toString(), customer.toString());

    }
}