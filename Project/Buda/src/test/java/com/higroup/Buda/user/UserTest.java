package com.higroup.Buda.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    User user;
    @BeforeAll
    public void initializeDB()
    {
        user = new User();
    }

    @Test
    public void registerNewUserThenDrop()
    {
        long databaseSizeBeforeUpdate = userRepository.findAll().size();
        user = new User();
        user.setEmail("haitq@gmail.com");
        user.setFirstName("Hai");
        user.setLastName("Tran");
        user.setPassword("BBBBBBB");
        user.setPhoneNumber("21312313");
        user.setUserName("haihoho");
        userService.registerNewUser(user);
        assertEquals(databaseSizeBeforeUpdate + 1, userRepository.count());
        userService.deleteUserByID(userRepository.findUserByUserUUID(user.getUserUUID()).get().getId());
        assertEquals(databaseSizeBeforeUpdate, userRepository.count());
    }
    @Test
    public void registerAnExistingUser()
    {

    }
    @Test
    public void deleteUserByID(Long userID)
    {

    }
}
