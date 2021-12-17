package com.higroup.Buda.restcontroller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.higroup.Buda.customDTO.GoogleUserPayload;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.UserLogin;
import com.higroup.Buda.entities.UserRegister;
import com.higroup.Buda.jwt.JwtSimple;
import com.higroup.Buda.security.BudaGoogleTokenVerifier;
import com.higroup.Buda.services.UserService;
import com.higroup.Buda.util.Checker.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private final RequestUtil requestUtil;
    private final UserService userService;

    @Autowired
    public UserController(UserService userService, RequestUtil requestUtil) {
        this.userService = userService;
        this.requestUtil = requestUtil;
    }

    // Lam du 4 request: CREATE - READ - UPDATE - DELETE
    // BEN NAY CAC FUNCTION CHI CO MOT DONG DUY NHAT
    // return this.userService.get(tham so)/update(tham so)/...

    // only admin can use this request
    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable("id") Long id, HttpServletRequest request) {
        Long userID = requestUtil.getUserIDFromUserToken(request);
        if(userID != id){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
        }
        return ResponseEntity.ok().body(userService.getUserByID(id));
    }
    // get current user 
    @GetMapping(path = "/me")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request){
        Long userID = requestUtil.getUserIDFromUserToken(request);
        return ResponseEntity.ok().body(userService.getUserByID(userID));
    }

    @GetMapping(path = "uuid/{userUUID}")
    public User getUserByUUID(@PathVariable("userUUID") String userUUID) {
        return userService.getUserByUserUUID(userUUID);
    }

    @PostMapping("/register")
    public void registerNewUser(@Valid @RequestBody UserRegister userRegister) {
        userService.registerNewUser(userRegister);
    }

    @GetMapping("/register/confirm")
    public ResponseEntity<?> confirmAccountActivationEmail(@RequestParam(name = "token") String token) {
        return ResponseEntity.ok(userService.confirmAccountActivation(token));
    }

    @DeleteMapping(path = "id/{userID}")
    public ResponseEntity<?> deleteUserByID(@PathVariable("userID") Long id, HttpServletRequest request) {
        Long get_userid = requestUtil.getUserIDFromUserToken(request);
        if(get_userid.equals(id)){
            userService.deleteUserByID(id);
            return ResponseEntity.ok().body("Delete Succesfully");
        }
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No authorized");
    }

    @PostMapping("/login")
    public ResponseEntity<?> correctLogin(@RequestBody @Valid UserLogin userLogin) {
        String email = userLogin.getEmail();
        String password = userLogin.getPassword();
        return ResponseEntity.ok(userService.correctLogin(email, password));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> generateNewAccessToken(@RequestBody JwtSimple jwtSimple)
    {
        String token = jwtSimple.getToken();
        return ResponseEntity.ok().body(userService.generateNewToken(token));
    }

    @PostMapping("/login/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody JwtSimple jwtSimple) throws GeneralSecurityException, IOException{
        GoogleUserPayload googleUserPayload = BudaGoogleTokenVerifier.userCustomPayload(jwtSimple.getToken());
        return ResponseEntity.ok().body(this.userService.processGoogleUserPostLogin(googleUserPayload));
    }

    @PutMapping(path = "/id/{userID}")
    public ResponseEntity<?> updateUserByID(@PathVariable("userID") Long id,
            @RequestParam(required = false) String userName, @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber, @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName, @RequestParam(required = false) String password) {
        
        return ResponseEntity.ok(
            userService.updateUserByID(id, userName, email, phoneNumber, firstName, lastName, password)
        );
    }

}
