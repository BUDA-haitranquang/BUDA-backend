package com.higroup.Buda.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "nho sua cai nay")
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

    @GetMapping(path = "{userID}")
    public User getUserByID(@PathVariable("userID") Long id)
    {
        return userService.getUserByID(id);
    }
    @GetMapping(path = "uuid/{userUUID}")
    public User getUserByUUID(@PathVariable("userUUID") String userUUID)
    {
        return null;
    }
    @PostMapping
    public void registerNewUser(@RequestBody User user)
    {
        userService.registerNewUser(user);
    }
    @DeleteMapping(path = "{userID}")
    public void deleteUserByID(@PathVariable("userID") Long id)
    {
        userService.deleteUserByID(id);
    }
    
}
