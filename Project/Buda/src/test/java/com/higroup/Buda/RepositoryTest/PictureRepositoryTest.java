package com.higroup.Buda.RepositoryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.higroup.Buda.entities.Picture;
import com.higroup.Buda.repositories.PictureRepository;

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
public class PictureRepositoryTest {

    @Container
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
                    .withDatabaseName("new_db")
                    .withUsername("testuser")
                    .withPassword("pass");

    @Autowired
    private PictureRepository pictureReposTest;

    @AfterEach
    public void tearDown(){
        pictureReposTest.deleteAll();
    }
    @Test
    void testFindPicturebyID()
    {
        Long pictureID = (long) 1;
        Picture newPicture = new Picture();
        newPicture.setPictureID((long)1);
        newPicture.setLink("facebook.com");
        pictureReposTest.save(newPicture);

        boolean exist = pictureReposTest.findPictureByPictureID(pictureID).isPresent();
        // assertEquals(exist, true);
        assertEquals(exist, true);

    }
}