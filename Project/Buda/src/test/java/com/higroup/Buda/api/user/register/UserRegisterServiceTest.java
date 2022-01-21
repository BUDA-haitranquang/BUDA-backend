package com.higroup.Buda.api.user.register;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.higroup.Buda.BudaApplication;
import com.higroup.Buda.customDTO.UserRegister;
import com.higroup.Buda.repositories.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BudaApplication.class)
public class UserRegisterServiceTest {
    
    @Autowired
    private UserRepository userRepository;

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

    @Test
    @Transactional
    void testRegisterDuplicatePhoneNumberUser(){
        int firstSize = userRepository.findAll().size();
        UserRegister userRegister = new UserRegister();
        userRegister.setEmail("nguyenhoangvubkhn2301@gmail.com");
        userRegister.setFirstName("Vu");
        userRegister.setLastName("Nguyen Hoang");
        userRegister.setPassword("123456789");
        userRegister.setPhoneNumber("123131223313");
        userRegister.setUsername("nguyenhoangvu");

        Exception exception = assertThrows(ResponseStatusException.class, () -> {userRegisterService.registerNewUser(userRegister);});
        // userRegisterService.registerNewUser(userRegister);
        int laterSize = userRepository.findAll().size();
        assertEquals(firstSize, laterSize);
        String expectedMessage = "Already exists phoneNumber";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @Transactional
    void testRegisterInvalidPhoneNumberUser(){
        int firstSize = userRepository.findAll().size();
        UserRegister userRegister = new UserRegister();
        userRegister.setEmail("nguyenhoangvubkhn2301@gmail.com");
        userRegister.setFirstName("Vu");
        userRegister.setLastName("Nguyen Hoang");
        userRegister.setPassword("123456789");
        userRegister.setPhoneNumber("123131223313abc");
        userRegister.setUsername("nguyenhoangvu");

        Exception exception = assertThrows(ResponseStatusException.class, () -> {userRegisterService.registerNewUser(userRegister);});
        // userRegisterService.registerNewUser(userRegister);
        int laterSize = userRepository.findAll().size();
        assertEquals(firstSize, laterSize);
        String expectedMessage = "Invalid phone";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @Transactional
    void testRegisterDuplicateEmailUser(){
        int firstSize = userRepository.findAll().size();
        UserRegister userRegister = new UserRegister();
        userRegister.setEmail("budatester@gmail.com");
        userRegister.setFirstName("Vu");
        userRegister.setLastName("Nguyen Hoang");
        userRegister.setPassword("123456789");
        userRegister.setPhoneNumber("123453435232");
        userRegister.setUsername("nguyenhoangvu");

        Exception exception = assertThrows(ResponseStatusException.class, () -> {userRegisterService.registerNewUser(userRegister);});
        // userRegisterService.registerNewUser(userRegister);
        int laterSize = userRepository.findAll().size();
        assertEquals(firstSize, laterSize);
        String expectedMessage = "Already exists email";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @Transactional
    void testRegisterInvalidEmailUser(){
        int firstSize = userRepository.findAll().size();
        UserRegister userRegister = new UserRegister();
        userRegister.setEmail("1231241231241234udatester1231241231241234udatester1231241231241234udatester1231241231241234udatester1231241231241234udatester1231241231241234udatester@gmail.com");
        userRegister.setFirstName("Vu");
        userRegister.setLastName("Nguyen Hoang");
        userRegister.setPassword("123456789");
        userRegister.setPhoneNumber("123453435232");
        userRegister.setUsername("nguyenhoangvu");

        Exception exception = assertThrows(ResponseStatusException.class, () -> {userRegisterService.registerNewUser(userRegister);});
        // userRegisterService.registerNewUser(userRegister);
        int laterSize = userRepository.findAll().size();
        assertEquals(firstSize, laterSize);
        String expectedMessage = "Invalid email";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @Transactional
    void testRegisterWeakPasswordUser(){
        int firstSize = userRepository.findAll().size();
        UserRegister userRegister = new UserRegister();
        userRegister.setEmail("nguyenhoangvubkhn2301@gmail.com");
        userRegister.setFirstName("Vu");
        userRegister.setLastName("Nguyen Hoang");
        userRegister.setPassword("12389");
        userRegister.setPhoneNumber("1231312234");
        userRegister.setUsername("nguyenhoangvu");

        Exception exception = assertThrows(ResponseStatusException.class, () -> {userRegisterService.registerNewUser(userRegister);});
        // userRegisterService.registerNewUser(userRegister);
        int laterSize = userRepository.findAll().size();
        assertEquals(firstSize, laterSize);
        String expectedMessage = "Weak password";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @Transactional
    void testRegisterDuplicateUserNameUser(){
        int firstSize = userRepository.findAll().size();
        UserRegister userRegister = new UserRegister();
        userRegister.setEmail("nguyenhoangvubkhn2301@gmail.com");
        userRegister.setFirstName("Vu");
        userRegister.setLastName("Nguyen Hoang");
        userRegister.setPassword("123456789");
        userRegister.setPhoneNumber("1231312234");
        userRegister.setUsername("budatester");

        Exception exception = assertThrows(ResponseStatusException.class, () -> {userRegisterService.registerNewUser(userRegister);});
        // userRegisterService.registerNewUser(userRegister);
        int laterSize = userRepository.findAll().size();
        assertEquals(firstSize, laterSize);
        String expectedMessage = "Already exists userName";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
