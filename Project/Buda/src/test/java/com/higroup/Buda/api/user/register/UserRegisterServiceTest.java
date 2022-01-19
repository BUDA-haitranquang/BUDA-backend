package com.higroup.Buda.api.user.register;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.customDTO.UserRegister;
import com.higroup.Buda.entities.Role;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.RoleRepository;
import com.higroup.Buda.repositories.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class UserRegisterServiceTest {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRegisterService userRegisterService;

    @Test
    void testLoadUserByUsername() {

    }

    @Test
    @Transactional
    void testRegisterNewUser() {
        int firstSize = userRepository.findAll().size();

        UserRegister userRegister = new UserRegister();
        userRegister.setEmail("nguyenhoangvubkhn2301@gmail.com");
        userRegister.setFirstName("Vu");
        userRegister.setLastName("Nguyen Hoang");
        userRegister.setPassword("123456789");
        userRegister.setPhoneNumber("0367185661");
        userRegister.setUsername("nguyenhoangvu");

        userRegisterService.registerNewUser(userRegister);
        int laterSize = userRepository.findAll().size();
        assertEquals(firstSize+1, laterSize);
    }
}
