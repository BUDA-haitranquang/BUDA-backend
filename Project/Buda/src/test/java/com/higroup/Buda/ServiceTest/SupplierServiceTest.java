package com.higroup.Buda.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.SupplierRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.services.SupplierService;

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
public class SupplierServiceTest {
    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");

    @Autowired
    private SupplierRepository supplierRepository;

    private SupplierService supplierService;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @AfterEach
    public void tearDown(){
        supplierRepository.deleteAll();
    }


    public Supplier supplier;

    @BeforeEach
    public void Setup(){
        supplierService = new SupplierService(supplierRepository);
    }

    @BeforeEach
    public void initializeDB()
    {
        user = new User();
        user.setEmail("haitq@gmail.com");
        user.setFirstName("Hai");
        user.setLastName("Tran");
        user.setPassword("BBBBBasdsadBB");
        user.setPhoneNumber("21312313");
        user.setUserName("haihoho");
        
        supplier = new Supplier();
        supplier.setName("nguyen Hoang Vu");
        supplier.setEmail("nguyenhoangvudtm23@gmail.com");
        supplier.setPhoneNumber("0367185122");

        userRepository.save(user);
        supplier.setUserID(user.getUserID());
    }

    @Test
    public void canResigterNewSupplier(){
        Long userID = user.getUserID();
        long sizebeforeUpdate = supplierRepository.count();

        ResponseEntity<?> res =  supplierService.registerNewSupplier(userID, supplier);

        // check
        assertEquals(res.getBody().toString(), supplier.toString());
        assertEquals(sizebeforeUpdate + 1, supplierRepository.count());   
        Supplier new_supplier = supplierRepository.findSupplierBySupplierID(supplier.getSupplierID()).get();
        
        assertEquals(new_supplier, supplier);
    }

    @Test 
    public void canFindAllbyUserID(){
        Long userID = user.getUserID();
        supplierRepository.save(supplier);

        // check
        List<Supplier> list = supplierService.findAllByUserID(userID);

        assertEquals(list, Arrays.asList(supplier));
    }

    @Test 
    public void canFindSupplierbyUserIDandPhoneNumber(){
        Long userID = user.getUserID();
        String phoneNumber = supplier.getPhoneNumber();
        supplierRepository.save(supplier);

        // check
        ResponseEntity<?> res = supplierService.findSupplierByUserIDAndPhoneNumber(userID, phoneNumber);
        assertEquals(res.getBody().toString(), supplier.toString());

    }
}

