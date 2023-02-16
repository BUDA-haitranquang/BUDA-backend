package com.higroup.Buda.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.SupplierRepository;
import com.higroup.Buda.repositories.UserRepository;



@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SupplierRepositoryTest {

    // @Container
    // MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
    //                 .withDatabaseName("new_db")
    //                 .withUsername("testuser")
    //                 .withPassword("pass");
                    
    @Autowired
    private SupplierRepository supplierReposTest;
    
    @Autowired
    private UserRepository userReposTest;


    private Supplier supplier;
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

        supplier = new Supplier();
        supplier.setName("nguyen Hoang Vu");
        supplier.setEmail("nguyenhoangvudtm23@gmail.com");
        supplier.setPhoneNumber("0367185122");
        supplier.setUserID(newUser.getUserID());
        supplierReposTest.save(supplier);

    }
    
    @AfterEach
    public void tearDown(){
        userReposTest.deleteAll();
        supplierReposTest.deleteAll();
    }

    @Test 
    public void canFindSupplierbySupplierID(){
        Long supplierID = supplier.getSupplierID();

        Optional<Supplier> res = supplierReposTest.findSupplierBySupplierID(supplierID);
        assertEquals(res.get().getSupplierID(), supplier.getSupplierID());
    }

    @Test 
    public void canFindAllbyUserID(){
        Long userID = newUser.getUserID();
        List<Supplier> list = supplierReposTest.findAllByUserID(userID);

        assertEquals(list, Arrays.asList(supplier));
    }

    @Test
    public void canFindSupplierbyUserIDandPhoneNumber(){
        String phoneNumber = supplier.getPhoneNumber();
        Long userID = newUser.getUserID();

        Optional<Supplier> res = supplierReposTest.findSupplierByUserIDAndPhoneNumber(userID, phoneNumber);
        assertEquals(res.get().getSupplierID(), supplier.getSupplierID());
    }
}
