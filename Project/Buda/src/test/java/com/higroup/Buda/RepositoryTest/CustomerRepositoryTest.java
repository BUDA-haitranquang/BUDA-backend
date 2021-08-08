package com.higroup.Buda.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.AgeGroup;
import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.Gender;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
public class CustomerRepositoryTest {
    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");

    @Autowired
    private CustomerRepository customerRepoTest;

    @Autowired
    private UserRepository userReposTest;

    private Customer customer;
    private User newUser;

    @BeforeEach
    public void SetUp(){
        // user
        newUser = new User();
        newUser.setEmail("linhnn@gmail.com");
        newUser.setFirstName("Linh");
        newUser.setLastName("Hai");
        newUser.setPassword("BAehrfenwiof");
        newUser.setPhoneNumber("9876543210");
        newUser.setUserName("nnlinh");
        userReposTest.save(newUser);
        
        // customer
        customer = new Customer();
        customer.setGender(Gender.MALE);
        customer.setAgeGroup(AgeGroup.FROM_18_TO_24);
        customer.setName("DoHuyAnh");
        customer.setAddress("address");
        customer.setPhoneNumber("0915437599");
        customer.setTotalSpend(1000.0);
        customer.setUserID(newUser.getUserID());

    }

    @AfterEach
    public void tearDown(){
        customerRepoTest.deleteAll();
        userReposTest.deleteAll();
    }


    @Test
    public void canFindAllbyCustomerID(){
        String cusName = customer.getName(), address = customer.getAddress(), phoneNumber = customer.getPhoneNumber();
        Double totalspend = customer.getTotalSpend();
        Gender gen = customer.getGender();
        AgeGroup age = customer.getAgeGroup();

        customerRepoTest.save(customer);
        Long customerID = customer.getCustomerID();
        
        Optional<Customer> findCustomer = customerRepoTest.findCustomerByCustomerID(customerID);
        
        if(findCustomer.isPresent()){
            assertEquals(findCustomer.get().getName(), cusName);
            assertEquals(findCustomer.get().getAddress(), address);
            assertEquals(findCustomer.get().getPhoneNumber(), phoneNumber);
            assertEquals(findCustomer.get().getTotalSpend(), totalspend);
            assertEquals(findCustomer.get().getGender(), gen);
            assertEquals(findCustomer.get().getAgeGroup(), age);
        }
        else fail("cant find customer");

    }

    @Test 
    public void canFindAllByUserID(){
        Long UserID = newUser.getUserID();
        customerRepoTest.save(customer);

        List<Customer> find = customerRepoTest.findAllByUserID(UserID);
        
        assertEquals(find, Arrays.asList(customer));
    }
}