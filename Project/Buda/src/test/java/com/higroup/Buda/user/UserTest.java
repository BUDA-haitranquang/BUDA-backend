package com.higroup.Buda.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.services.UserService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class UserTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    public static User user;
    @BeforeAll
    public static void initializeDB()
    {
        user = new User();
        user.setEmail("default@gmail.com");
        user.setFirstName("default");
        user.setLastName("default");
        user.setPassword("default");
        user.setPhoneNumber("default");
        user.setUserName("default");
    }

    @Test
    public void registerNewUserThenDrop()
    {
        userService.registerNewUser(user);
        long databaseSizeBeforeUpdate = userRepository.findAll().size();
        
        User newUser = new User();
        newUser.setEmail("haitq@gmail.com");
        newUser.setFirstName("Hai");
        newUser.setLastName("Tran");
        newUser.setPassword("BBBBBBB");
        newUser.setPhoneNumber("21312313");
        newUser.setUserName("haihoho");
        userService.registerNewUser(newUser);
        assertEquals(databaseSizeBeforeUpdate + 1, userRepository.count());
        User lastUser = userRepository.findUserByUserUUID(newUser.getUserUUID()).get();
        assertEquals(lastUser.getFirstName(), newUser.getFirstName());
        assertEquals(lastUser.getLastName(), newUser.getLastName());
        userService.deleteUserByID(lastUser.getUserID());
        assertEquals(databaseSizeBeforeUpdate, userRepository.count());
    }
    @Test
    public void registerAnExistingUser()
    {
        userService.registerNewUser(user);
        long databaseSizeBeforeUpdate = userRepository.findAll().size();
        userService.registerNewUser(user);
        assertEquals(databaseSizeBeforeUpdate, userRepository.count());
    }
    
}
