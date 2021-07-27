package com.higroup.Buda.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void registerNewUser(@RequestBody User user)
    {
        userService.registerNewUser(user);
    }
    @DeleteMapping(path = "id/{userID}")
    public void deleteUserByID(@PathVariable("userID") Long id)
    {
        userService.deleteUserByID(id);
    }
    @GetMapping(path = "login/{email}&{encodedPassword}")
    public boolean correctLogin(@PathVariable("email") String email, @PathVariable("encodedPassword") String encodedPassword)
    {
        return userService.correctLogin(email, encodedPassword);
    }
    @PutMapping(path = "id/{id}")
    public void updateUserByID(@PathVariable("id") Long id,
    @RequestParam(required = false) String email,
    @RequestParam(required = false) String phoneNumber,
    @RequestParam(required = false) String firstName,
    @RequestParam(required = false) String lastName)
    {
        userService.updateUserByID(id, email, phoneNumber, firstName, lastName);
    }
}
