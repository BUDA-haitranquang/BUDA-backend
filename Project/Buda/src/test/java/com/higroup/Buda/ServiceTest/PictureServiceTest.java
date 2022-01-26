// package com.higroup.Buda.ServiceTest;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.fail;

// import java.util.Optional;

// import com.higroup.Buda.api.picture.PictureService;
// import com.higroup.Buda.entities.Picture;
// import com.higroup.Buda.repositories.PictureRepository;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.testcontainers.containers.MySQLContainer;
// import org.testcontainers.junit.jupiter.Container;
// import org.testcontainers.junit.jupiter.Testcontainers;



// // @ExtendWith(MockitoExtension.class)

// @DataJpaTest
// @Testcontainers
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// public class PictureServiceTest {
//     @Container
//     MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
//                     .withDatabaseName("new_db")
//                     .withUsername("testuser")
//                     .withPassword("pass");

//     @Autowired
//     private PictureRepository pictureRepository;

//     private PictureService pictureService;

//     @AfterEach
//     public void tearDown(){
//         pictureRepository.deleteAll();
//     }


//     public static Picture picture;

//     @BeforeEach
//     void Setup(){
//         pictureService = new PictureService(pictureRepository);
//     }

//     @BeforeAll
//     public static void initializeDB()
//     {
//         picture = new Picture();
//         picture.setPictureLink("default");
//     }
// }