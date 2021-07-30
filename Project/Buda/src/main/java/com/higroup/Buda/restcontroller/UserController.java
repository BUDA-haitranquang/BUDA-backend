package com.higroup.Buda.restcontroller;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.UserLogin;
import com.higroup.Buda.services.UserService;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }
    //Lam du 4 request: CREATE - READ - UPDATE - DELETE
    //BEN NAY CAC FUNCTION CHI CO MOT DONG DUY NHAT
    //return this.userService.get(tham so)/update(tham so)/...
    @GetMapping
    public List<User> getUsers()
    {
        return userService.getUsers();
    }
    @GetMapping(path = "/id/{id}")
    public User getUserByID(@PathVariable("id") Long id)
    {
        return userService.getUserByID(id);
    }
    @GetMapping(path = "uuid/{userUUID}")
    public User getUserByUUID(@PathVariable("userUUID") String userUUID)
    {
        return userService.getUserByUserUUID(userUUID);
    }
    @PostMapping
    public ResponseEntity<?> registerNewUser(@RequestBody User user)
    {
        return userService.registerNewUser(user);
    }
    @DeleteMapping(path = "id/{userID}")
    public ResponseEntity<?> deleteUserByID(@PathVariable("userID") Long id)
    {
        return userService.deleteUserByID(id);
    }
    @PostMapping("/login")
    public ResponseEntity<?> correctLogin(@RequestBody UserLogin userLogin)
    {
        String email = userLogin.getEmail();
        String password = userLogin.getPassword();
        return userService.correctLogin(email, password);
    }
    
    @PutMapping(path = "/id/{userID}")
    public ResponseEntity<?> updateUserByID(@PathVariable("userID") Long id,
    @RequestParam(required = false) String userName,
    @RequestParam(required = false) String email,
    @RequestParam(required = false) String phoneNumber,
    @RequestParam(required = false) String firstName,
    @RequestParam(required = false) String lastName,
    @RequestParam(required = false) String password)
    {
        return userService.updateUserByID(id, userName, email, phoneNumber, firstName, lastName, password);
    }
}
