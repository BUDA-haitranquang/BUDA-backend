// package com.higroup.Buda.ServiceTest;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.fail;

// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

// import com.higroup.Buda.entities.User;
// import com.higroup.Buda.repositories.UserRepository;
// import com.higroup.Buda.services.UserService;

// import org.junit.jupiter.api.AfterEach;
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
// public class UserServiceTest {
//     @Container
//     MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
//                     .withDatabaseName("new_db")
//                     .withUsername("testuser")
//                     .withPassword("pass");

//     @Autowired
//     private UserRepository userRepository;

//     private UserService userService;

//     public static User user;

//     @AfterEach
//     public void tearDown(){
//         userRepository.deleteAll();
//     }

//     @BeforeEach
//     void Setup(){
//         userService = new UserService(userRepository);
//     }

//     @BeforeEach
//     public void initializeDB()
//     {
//         user = new User();
//         user.setEmail("2131248124@gmail.com");
//         user.setFirstName("default");
//         user.setLastName("default");
//         user.setPassword("default123");
//         user.setPhoneNumber("01234567812");
//         user.setUserName("defaultasd");

//     }

//     @Test
//     public void canRegisterNewUser(){
//         // initilize
//         long sizebeforeUpdate = userRepository.findAll().size();
//         User response =  userService.registerNewUser(user);

//         // check user added
//         User addedUser = userRepository.findUserByUserID(user.getUserID()).get();
//         assertEquals(response.toString(), addedUser.toString());
//         assertEquals(sizebeforeUpdate + 1, userRepository.count());
//         assertEquals(addedUser.getPhoneNumber(), user.getPhoneNumber());
//         assertEquals(addedUser.getEmail(), user.getEmail());
//     }

//     @Test 
//     public void canGetAllUser(){
//         // initialize
//         User newUser = new User();
//         newUser.setEmail("haitq@gmail.com");
//         newUser.setFirstName("Hai");
//         newUser.setLastName("Tran");
//         newUser.setPassword("BBBBasdBBB");
//         newUser.setPhoneNumber("21312313");
//         newUser.setUserName("haihoho");
//         userService.registerNewUser(newUser);
//         userService.registerNewUser(user);

//         // check
//         List<User> expected_list = Arrays.asList(newUser, user);
//         List<User> actual_list = userService.getUsers();

//         assertEquals(expected_list, actual_list);
//     }

//     @Test
//     public void canGetUserByID(){
//         // initialize 
//         userRepository.save(user);
//         // User findUser = userService.getUserByID(user.getUserID());
//         User findUser = userRepository.findUserByUserID(user.getUserID()).get();
//         assertEquals(user, findUser);
//     }

//     @Test
//     public void canDeleteUserbyID(){
//         // initialize
//         User register_reponse = userService.registerNewUser(user);
//         long id = user.getUserID();
//         long sizebeforeUpdate = userRepository.count();
        
//         // action 
//         userService.deleteUserByID(id);
//         // check
//         assertEquals(sizebeforeUpdate - 1, userRepository.count());
//         assertEquals(register_reponse.toString(), user.toString());
//         // assertEquals(del_reponse.getBody().toString(), "Deleted successfully");

//     }

//     @Test
//     public void canGetUserbyUserUUID(){
//         // initialize 
//         userRepository.save(user);
//         // User findUser = userService.getUserByID(user.getUserID());
//         Optional<User> findUser = userRepository.findUserByUserUUID(user.getUserUUID());
//         if (findUser.isPresent()){
//             assertEquals(user, findUser.get());
//         }
//         else fail("can't find user");

//     }

//     @Test
//     public void testCorrectLogin(){
//         String email = "default@gmail.com", password = "default123";
//         userService.registerNewUser(user);
        
//         Optional<User> mailUser = userRepository.findUserByEmail(email);
//         if (mailUser.isPresent()){
//             assertEquals(password, mailUser.get().getPassword());
//         }
//         else{
//             fail("password not correct");
//         }
//     }

//     @Test
//     public void canUpdateUserbyID(){
//         // initialize
//         userService.registerNewUser(user);
//         Long id = user.getUserID();

//         String userName = "tatcadeuga", email = "nguyenhoangvudtm23@gmail.com",
//                 phoneNumber = "0312671712", firstName = "Nguyen", lastName = "HoangVu",
//                 password = "vu123ajdjfads";
        
//         // check
//         User response = userService.updateUserByID(id, userName, email, phoneNumber, firstName, lastName, password);
//         User getUser = userRepository.findUserByUserID(id).get();


//         assertEquals(getUser.toString(), response.toString());
//         assertEquals(getUser.getUserName(), userName);
//         assertEquals(getUser.getEmail(), email);
//         assertEquals(getUser.getPhoneNumber(), phoneNumber);
//         assertEquals(getUser.getFirstName(), firstName);
//         assertEquals(getUser.getLastName(), lastName);
//         assertEquals(getUser.getPassword(), password);
//     }

//     @Test
//     public void registerNewUserThenDrop()
//     {
//         // Mockito.when(userService.registerNewUser(user)).thenReturn(null);
//         userService.registerNewUser(user);
//         long databaseSizeBeforeUpdate = userRepository.findAll().size();
//         User newUser = new User();
//         newUser.setEmail("haitq@gmail.com");
//         newUser.setFirstName("Hai");
//         newUser.setLastName("Tran");
//         newUser.setPassword("BBBBasdBBB");
//         newUser.setPhoneNumber("21312313");
//         newUser.setUserName("haihoho");
//         // Mockito.when(userService.registerNewUser(newUser)).thenReturn(null);
//         userService.registerNewUser(newUser);
//         assertEquals(databaseSizeBeforeUpdate + 1, userRepository.count());
//         User lastUser = userRepository.findUserByUserID(newUser.getUserID()).get();
//         assertEquals(lastUser.getFirstName(), newUser.getFirstName());
//         assertEquals(lastUser.getLastName(), newUser.getLastName());
//         userService.deleteUserByID(lastUser.getUserID());
//         assertEquals(databaseSizeBeforeUpdate, userRepository.count());

//     }

//     @Test
//     public void registerAnExistingUser()
//     {
//         userService.registerNewUser(user);
//         long databaseSizeBeforeUpdate = userRepository.findAll().size();
//         userService.registerNewUser(user);
//         assertEquals(databaseSizeBeforeUpdate, userRepository.count());
//     }
    
//     @Test 
//     public void registerInvalidPasswordUser()
//     {   
//         userService.registerNewUser(user);
//         // wrong pass user
//         User WrongPassUser = new User();
//         WrongPassUser.setEmail("default@gmail.com");
//         WrongPassUser.setFirstName("default");
//         WrongPassUser.setLastName("default");
//         WrongPassUser.setPassword("123sa");
//         WrongPassUser.setPhoneNumber("01321432");
//         WrongPassUser.setUserName("default");
//         long databseSizeBeforeUpdate = userRepository.findAll().size();
//         userService.registerNewUser(WrongPassUser);
//         assertEquals(databseSizeBeforeUpdate, userRepository.count());
//     }

//     @Test
//     public void registerInvalidEmailUser()
//     {   
        
//         // invalid email user
//         User invalidEmailUser= new User();
//         invalidEmailUser.setEmail("default@gmil.com");
//         invalidEmailUser.setFirstName("default");
//         invalidEmailUser.setLastName("default");
//         invalidEmailUser.setPassword("12asdsadsad3");
//         invalidEmailUser.setPhoneNumber("03124132");
//         invalidEmailUser.setUserName("default");
//         long databaseSizeBeforeUpdate = userRepository.findAll().size();
//         userService.registerNewUser(invalidEmailUser);
//         assertEquals(databaseSizeBeforeUpdate, userRepository.count());
//     }

//     @Test
//     public void registerInvalidPhoneUser()
//     {
//         userService.registerNewUser(user);
//         long databaseSizeBeforeUpdate = userRepository.findAll().size();

//         // invalid phone user
//         User newUser = new User();
//         newUser.setEmail("haitq@gmail.com");
//         newUser.setFirstName("Hai");
//         newUser.setLastName("Tran");
//         newUser.setPassword("BBBBBBasdaB");
//         newUser.setPhoneNumber("012asd");
//         newUser.setUserName("haihoho");
//         userService.registerNewUser(newUser);
//         assertEquals(databaseSizeBeforeUpdate, userRepository.count());
//     }

//     @Test 
//     public void registerDuplicateEmailUser()
//     {
//         userService.registerNewUser(user);
//         long databaseSizeBeforeUpdate = userRepository.findAll().size();


//         // user with duplicate email user
//         User newUser = new User();
//         newUser.setEmail("default@gmail.com");
//         newUser.setFirstName("Hai");
//         newUser.setLastName("Tran");
//         newUser.setPassword("BBBBBBasdsB");
//         newUser.setPhoneNumber("21312313");
//         newUser.setUserName("haihoho");
//         userService.registerNewUser(newUser);
//         assertEquals(databaseSizeBeforeUpdate, userRepository.count());
//     }
//     @Test
//     public void registerDuplicatePhoneUser()
//     {
//         userService.registerNewUser(user);
//         long databaseSizeBeforeUpdate = userRepository.findAll().size();

//         // user with duplicate email user
//         User newUser = new User();
//         newUser.setEmail("haitq@gmail.com");
//         newUser.setFirstName("Hai");
//         newUser.setLastName("Tran");
//         newUser.setPassword("BBBsaBBBB");
//         newUser.setPhoneNumber("012345678");
//         newUser.setUserName("haihoho");
//         userService.registerNewUser(newUser);
//         assertEquals(databaseSizeBeforeUpdate, userRepository.count());
//     }

//     @Test
//     public void registerDuplicateUserName(){
//         userService.registerNewUser(user);
//         long databaseSizeBeforeUpdate = userRepository.findAll().size();

//         // user with duplicate user name user
//         User newUser = new User();
//         newUser.setEmail("haitq@gmail.com");
//         newUser.setFirstName("Hai");
//         newUser.setLastName("Tran");
//         newUser.setPassword("BBBBBBasdB");
//         newUser.setPhoneNumber("65718234");
//         newUser.setUserName("default");
//         userService.registerNewUser(newUser);
//         assertEquals(databaseSizeBeforeUpdate, userRepository.count());
//     }

//     @Test
//     public void registerDuplicatePhoneandEmailUser(){
//         userService.registerNewUser(user);
//         long databaseSizeBeforeUpdate = userRepository.findAll().size();

//         // deuplicate phone and email user
//         User newUser = new User();
//         newUser.setEmail("default@gmail.com");
//         newUser.setFirstName("Hai");
//         newUser.setLastName("Tran");
//         newUser.setPassword("BBBBBasdBB");
//         newUser.setPhoneNumber("012asd");
//         newUser.setUserName("haihoho");
//         userService.registerNewUser(newUser);
//         assertEquals(databaseSizeBeforeUpdate, userRepository.count());
//     }

//     @Test
//     public void updateDuplicateEmailUser(){
//         userService.registerNewUser(user);
//         long databaseSizeBeforeUpdate = userRepository.findAll().size();
//         // new user add
//         User newUser = new User();
//         newUser.setEmail("haitq@gmail.com");
//         newUser.setFirstName("Hai");
//         newUser.setLastName("Tran");
//         newUser.setPassword("BBBBBasdBB");
//         newUser.setPhoneNumber("0123412");
//         newUser.setUserName("haihoho");
//         userService.registerNewUser(newUser);
//         assertEquals(databaseSizeBeforeUpdate + 1, userRepository.count());
//         // update duplicate email
//         String lastemail = newUser.getEmail();
//         String newemail = "default@gmail.com";
//         userService.updateUserByID(newUser.getUserID(), newUser.getUserName(), newemail, newUser.getPhoneNumber(),
//                                     newUser.getLastName(), newUser.getLastName(), newUser.getPassword());
//         // check if email after update equal olde mail
//         assertEquals(lastemail, userService.getUserByID(newUser.getUserID()).getEmail());
//         assertEquals(databaseSizeBeforeUpdate + 1, userRepository.count());

//     }

//     @Test
//     public void updateDuplicatePhoneUser(){
//         userService.registerNewUser(user);
//         long databaseSizeBeforeUpdate = userRepository.findAll().size();
//         // new user add
//         User newUser = new User();
//         newUser.setEmail("haitq@gmail.com");
//         newUser.setFirstName("Hai");
//         newUser.setLastName("Tran");
//         newUser.setPassword("BBBBasdBBB");
//         newUser.setPhoneNumber("0123412");
//         newUser.setUserName("haihoho");
//         userService.registerNewUser(newUser);
//         assertEquals(databaseSizeBeforeUpdate + 1, userRepository.count());
//         // update duplicate phone
//         String lastphone = newUser.getPhoneNumber();
//         String newphone = "012345678";
//         userService.updateUserByID(newUser.getUserID(), newUser.getUserName(), newUser.getEmail(), newphone,
//                                     newUser.getLastName(), newUser.getLastName(), newUser.getPassword());
//         // check if phone after update equal old phone
//         assertEquals(lastphone, userService.getUserByID(newUser.getUserID()).getPhoneNumber());
//         assertEquals(databaseSizeBeforeUpdate + 1, userRepository.count());
//     }

//     @Test 
//     public void updateDuplicateUserName(){
//         userService.registerNewUser(user);
//         long databaseSizeBeforeUpdate = userRepository.findAll().size();
//         // new user add
//         User newUser = new User();
//         newUser.setEmail("haitq@gmail.com");
//         newUser.setFirstName("Hai");
//         newUser.setLastName("Tran");
//         newUser.setPassword("BBBBBasBB");
//         newUser.setPhoneNumber("0123412");
//         newUser.setUserName("haihoho");
//         userService.registerNewUser(newUser);
//         assertEquals(databaseSizeBeforeUpdate + 1, userRepository.count());
//         // update duplicate email
//         String last_username = newUser.getUserName();
//         String new_username = "default";
//         // lack user name
//         userService.updateUserByID(newUser.getUserID(), newUser.getUserName(), newUser.getEmail(), newUser.getPhoneNumber(),
//                                     newUser.getLastName(), newUser.getLastName(), newUser.getPassword());
//         // check if username after update equal old username
//         assertEquals(last_username, userService.getUserByID(newUser.getUserID()).getUserName());
//         assertEquals(databaseSizeBeforeUpdate + 1, userRepository.count());
//     }

//     @Test
//     public void updateDuplicatePhoneandEmailUser(){
//         userService.registerNewUser(user);
//         long databaseSizeBeforeUpdate = userRepository.findAll().size();
//         // new user add
//         User newUser = new User();
//         newUser.setEmail("haitq@gmail.com");
//         newUser.setFirstName("Hai");
//         newUser.setLastName("Tran");
//         newUser.setPassword("BBasdBBBBB");
//         newUser.setPhoneNumber("0123412");
//         newUser.setUserName("haihoho");
//         userService.registerNewUser(newUser);
//         assertEquals(databaseSizeBeforeUpdate + 1, userRepository.count());
//         // update duplicate email and phone
//         String lastphone = newUser.getPhoneNumber(), lastemail = newUser.getEmail();
//         String newphone = "012345678", newemail = "default@gmail.com";

//         userService.updateUserByID(newUser.getUserID(), newUser.getUserName(), newemail, newphone,
//                                     newUser.getLastName(), newUser.getLastName(), newUser.getPassword());
//         // check if email after update equal olde mail
//         assertEquals(lastphone, userService.getUserByID(newUser.getUserID()).getPhoneNumber());

//         // check if email after update equal olde mail
//         assertEquals(lastemail, userService.getUserByID(newUser.getUserID()).getEmail());
//         assertEquals(databaseSizeBeforeUpdate + 1, userRepository.count());
//     }

//     @Test
//     public void updateInvalidPhoneUser(){
//         userService.registerNewUser(user);
//         long databaseSizeBeforeUpdate = userRepository.findAll().size();
//         // new user add
//         User newUser = new User();
//         newUser.setEmail("haitq@gmail.com");
//         newUser.setFirstName("Hai");
//         newUser.setLastName("Tran");
//         newUser.setPassword("BBBBadsBBB");
//         newUser.setPhoneNumber("0123412");
//         newUser.setUserName("haihoho");
//         userService.registerNewUser(newUser);
//         assertEquals(databaseSizeBeforeUpdate + 1, userRepository.count());
//         // update duplicate email
//         String lastphone = newUser.getPhoneNumber();
//         String newphone = "012345678abc";
//         userService.updateUserByID(newUser.getUserID(), newUser.getPassword(), newUser.getEmail(), newphone,
//                                     newUser.getLastName(), newUser.getLastName(), newUser.getPassword());
//         // check if email after update equal olde mail
//         assertEquals(lastphone, userService.getUserByID(newUser.getUserID()).getPhoneNumber());
//         assertEquals(databaseSizeBeforeUpdate + 1, userRepository.count());
//     }

//     @Test
//     public void updateInvalidEmailUser(){
//         userService.registerNewUser(user);
//         long databaseSizeBeforeUpdate = userRepository.findAll().size();
//         // new user add
//         User newUser = new User();
//         newUser.setEmail("haitq@gmail.com");
//         newUser.setFirstName("Hai");
//         newUser.setLastName("Tran");
//         newUser.setPassword("BBBBasdBBB");
//         newUser.setPhoneNumber("0123412");
//         newUser.setUserName("haihoho");
//         userService.registerNewUser(newUser);
//         assertEquals(databaseSizeBeforeUpdate + 1, userRepository.count());
//         // update duplicate email
//         String lastemail = newUser.getEmail();
//         String newemail = "default@gmil.com";
//         userService.updateUserByID(newUser.getUserID(), newUser.getEmail(), newemail, newUser.getPhoneNumber(),
//                                     newUser.getLastName(), newUser.getLastName(), newUser.getPassword());
//         // check if email after update equal olde mail
//         assertEquals(lastemail, userService.getUserByID(newUser.getUserID()).getEmail());
//         assertEquals(databaseSizeBeforeUpdate + 1, userRepository.count());
//     }

//     @Test
//     public void deleteInexistenceUser(){
//         userService.registerNewUser(user);
//         long databaseSizeBeforeDelete = userRepository.findAll().size();

//         // new user
//         User newUser = new User();
//         newUser.setEmail("haitq@gmail.com");
//         newUser.setFirstName("Hai");
//         newUser.setLastName("Tran");
//         newUser.setPassword("BBBBBasdBB");
//         newUser.setPhoneNumber("21312313");
//         newUser.setUserName("haihoho");
        
//         userService.deleteUserByID(newUser.getUserID());
//         assertEquals(databaseSizeBeforeDelete, userRepository.count());

//     }

//     @Test 
//     public void registerUpdateDeleteUser(){
//         userService.registerNewUser(user);
//         long databaseSizeBeforeUpdate = userRepository.findAll().size();

//         // new User
//         User newUser = new User();
//         newUser.setEmail("haitq@gmail.com");
//         newUser.setFirstName("Hai");
//         newUser.setLastName("Tran");
//         newUser.setPassword("BBBBBasdsadBB");
//         newUser.setPhoneNumber("21312313");
//         newUser.setUserName("haihoho");
//         userService.registerNewUser(newUser);
//         assertEquals(databaseSizeBeforeUpdate + 1, userRepository.count());
//         // update 
//         userService.updateUserByID(newUser.getUserID(), newUser.getUserName(), newUser.getEmail(), "09123456782", "Hai Tran", "Quang", newUser.getPassword());
//         User lastUser = userService.getUserByID(newUser.getUserID());
//         assertEquals(lastUser.getFirstName(), "Hai Tran");
//         assertEquals(lastUser.getLastName(), "Quang");
//         assertEquals(lastUser.getPhoneNumber(), "09123456782");
//     }

    

// }

