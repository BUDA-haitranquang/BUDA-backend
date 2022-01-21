package com.higroup.Buda.api.user.login;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.entities.Role;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.RoleRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class UserLoginServiceTest {

    @Autowired 
    private RoleRepository roleRepository;
    

    @Transactional
    @BeforeEach
    void addUser(){
        User newUser = new User();
        Role userRole = roleRepository.findById(1l).get();
        newUser.addRole(userRole);
    }

    @Test
    void testCorrectLogin() {

    }

    @Test
    void testLoadUserByUsername() {

    }
}
