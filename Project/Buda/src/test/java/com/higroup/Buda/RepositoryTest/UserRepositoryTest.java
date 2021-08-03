package com.higroup.Buda.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.UserRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
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
public class UserRepositoryTest {

    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");

    @Autowired
    private UserRepository userReposTest;

    @AfterEach
    public void tearDown(){
        userReposTest.deleteAll();
    }
    @Test
    void testFindUserByEmail() {
        String email = "haitq@gmail.com";
        // add new user
        User newUser = new User();
        newUser.setEmail("haitq@gmail.com");
        newUser.setFirstName("Hai");
        newUser.setLastName("Tran");
        newUser.setPassword("BBBBBasdsadBB");
        newUser.setPhoneNumber("21312313");
        newUser.setUserName("haihoho");
        userReposTest.save(newUser);

        boolean exist = userReposTest.findUserByEmail(email).isPresent();
        // assertEquals(exist, true);
        assertEquals(exist, true);

    }

    @Test
    void testFindUserByPhoneNumber() {
        String phone = "21312313";
        // add new user
        User newUser = new User();
        newUser.setEmail("haitq@gmail.com");
        newUser.setFirstName("Hai");
        newUser.setLastName("Tran");
        newUser.setPassword("BBBBBasdsadBB");
        newUser.setPhoneNumber("21312313");
        newUser.setUserName("haihoho");
        userReposTest.save(newUser);

        boolean exist = userReposTest.findUserByPhoneNumber(phone).isPresent();
        assertEquals(exist, true);
    }

    @Test
    void testFindUserByUserID() {
        User newUser = new User();
        newUser.setEmail("haitq@gmail.com");
        newUser.setFirstName("Hai");
        newUser.setLastName("Tran");
        newUser.setPassword("BBBBBasdsadBB");
        newUser.setPhoneNumber("21312313");
        newUser.setUserName("haihoho");
        userReposTest.save(newUser);

        Long id = newUser.getUserID();

        boolean exist = userReposTest.findUserByUserID(id).isPresent();
        assertEquals(exist, true);
    }

    @Test
    void testFindUserByUserName() {
        String userName = "haihoho";

        User newUser = new User();
        newUser.setEmail("haitq@gmail.com");
        newUser.setFirstName("Hai");
        newUser.setLastName("Tran");
        newUser.setPassword("BBBBBasdsadBB");
        newUser.setPhoneNumber("21312313");
        newUser.setUserName("haihoho");
        userReposTest.save(newUser);

        boolean exist = userReposTest.findUserByUserName(userName).isPresent();
        assertEquals(exist, true);
    }

    @Test
    @Disabled
    void testFindUserByUserUUID() {
        User newUser = new User();
        newUser.setEmail("haitq@gmail.com");
        newUser.setFirstName("Hai");
        newUser.setLastName("Tran");
        newUser.setPassword("BBBBBasdsadBB");
        newUser.setPhoneNumber("21312313");
        newUser.setUserName("haihoho");
        userReposTest.save(newUser);

        String userUUID = newUser.getUserUUID();

        boolean exist = userReposTest.findUserByUserUUID(userUUID).isPresent();
        assertEquals(exist, true);
    }
}
