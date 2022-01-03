package com.higroup.Buda.services;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.higroup.Buda.BeanUtils.NullAwareBeanUtilsBean;
import com.higroup.Buda.customDTO.GoogleUserPayload;
import com.higroup.Buda.customDTO.UserRegister;
import com.higroup.Buda.entities.MailConfirmationToken;
import com.higroup.Buda.entities.Picture;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.entities.enumeration.PlanType;
import com.higroup.Buda.jwt.JwtResponse;
import com.higroup.Buda.repositories.PictureRepository;
import com.higroup.Buda.repositories.RoleRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.JwtTokenUtil;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService implements UserDetailsService{
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MailConfirmationTokenService mailConfirmationTokenService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PictureRepository pictureRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.pictureRepository = pictureRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public void registerNewUser(UserRegister userRegister) {
        String email = userRegister.getEmail();
        String phoneNumber = userRegister.getPhoneNumber();
        String username = userRegister.getUsername();

        Optional<User> mailUser = userRepository.findUserByEmail(email);
        if ((email!=null) && (mailUser.isPresent()))
        {
            //BAD REQUEST da ton tai email
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists email");
        }
        if (!EmailValidator.getInstance().isValid(email) || email.length() > 60)
        {
            //khong phai email
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email");
        }
        Optional<User> phoneUser = userRepository.findUserByPhoneNumber(phoneNumber);
        if ((phoneNumber!=null) && (phoneUser.isPresent()))
        {
            //BAD REQUEST da ton tai phone
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists phoneNumber");
        }
        if (!phoneNumber.matches("[0-9]+"))
        {
            //khong phai phone
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid phone");
        }
        Optional<User> userNameUser = userRepository.findUserByUserName(username);
        if ((username!=null) && (userNameUser.isPresent()))
        {
            //BAD REQUEST da ton tai username
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists userName");
        }
        if (userRegister.getPassword().length() < 8)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Weak password");
        }

        User newUser = new User(userRegister);
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        newUser.addRole(roleRepository.findRoleByName("USER").get());
        newUser.setPlanType(PlanType.BASIC);
        userRepository.save(newUser);

        mailConfirmationTokenService.sendMailConfirmationTo(email);
    }

    // dang ky user moi
    @Transactional
    public JwtResponse registerNewUser(User newUser) {
        String email = newUser.getEmail();
        String phoneNumber = newUser.getPhoneNumber();
        String userName = newUser.getUserName();
        Optional<User> mailUser = userRepository.findUserByEmail(email);
        if ((email!=null) && (mailUser.isPresent()))
        {
            //BAD REQUEST da ton tai email
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists email");
        }
        if (!EmailValidator.getInstance().isValid(email) || email.length() > 60)
        {
            //khong phai email
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email");
        }
        Optional<User> phoneUser = userRepository.findUserByPhoneNumber(phoneNumber);
        if ((phoneNumber!=null) && (phoneUser.isPresent()))
        {
            //BAD REQUEST da ton tai phone
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists phoneNumber");
        }
        if (!phoneNumber.matches("[0-9]+"))
        {
            //khong phai phone
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid phone");
        }
        Optional<User> userNameUser = userRepository.findUserByUserName(userName);
        if ((userName!=null) && (userNameUser.isPresent()))
        {
            //BAD REQUEST da ton tai username
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already exists userName");
        }
        if (newUser.getPassword().length() < 8)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Weak password");
        }
        // System.out.println(newUser.getPassword());
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        newUser.addRole(roleRepository.findRoleByName("USER").get());
        newUser.setPlanType(PlanType.BASIC);
        userRepository.save(newUser);
        UserDetails userDetails = loadUserByUsername(newUser.getEmail());
        String jwtAccessToken = jwtTokenUtil.generateAccessToken(userDetails, newUser.getUserID());
        String jwtRefreshToken = jwtTokenUtil.generateRefreshToken(userDetails, newUser.getUserID());
        return new JwtResponse(jwtAccessToken, jwtRefreshToken);
    }

    @Transactional
    public JwtResponse confirmAccountActivation(String token) {
        MailConfirmationToken confirmationToken = mailConfirmationTokenService.getToken(token);

        if (confirmationToken.getConfirmedAt() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Confirmation token expired");
        }

        mailConfirmationTokenService.setConfirmedAt(token);

        User user = confirmationToken.getUser();
        this.enableUser(confirmationToken.getUser().getEmail());
        UserDetails userDetails = loadUserByUsername(user.getEmail());
        String jwtAccessToken = jwtTokenUtil.generateAccessToken(userDetails, user.getUserID());
        String jwtRefreshToken = jwtTokenUtil.generateRefreshToken(userDetails, user.getUserID());
        return new JwtResponse(jwtAccessToken, jwtRefreshToken);
    }

    private void enableUser(String email) {
        System.out.println(email);
        userRepository.enableUserByEmail(email);
    }

    @Transactional
    public JwtResponse processGoogleUserPostLogin(GoogleUserPayload googleUserPayload)
    {
        String email = googleUserPayload.getEmail();
        Optional<User> mailUser = this.userRepository.findUserByEmail(email);
        //Da tung dang nhap bang email giong voi gmail dang dung de dang nhap
        if (mailUser.isPresent())
        {
            UserDetails userDetails = loadUserByUsername(mailUser.get().getEmail());
            String jwtAccessToken = jwtTokenUtil.generateAccessToken(userDetails, mailUser.get().getUserID());
            String jwtRefreshToken = jwtTokenUtil.generateRefreshToken(userDetails, mailUser.get().getUserID());
            return new JwtResponse(jwtAccessToken, jwtRefreshToken);
        }
        //Chua tung dang nhap bang email nao giong voi gmail dang dung de dang nhap
        else
        {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setFirstName(googleUserPayload.getFamilyName());
            newUser.setLastName(googleUserPayload.getGivenName());
            Picture picture = new Picture();
            picture.setPictureLink(googleUserPayload.getPictureUrl());
            pictureRepository.save(picture);
            newUser.setPictureID(picture.getPictureID());
            newUser.addRole(roleRepository.findRoleByName("USER").get());
            UUID uuid = UUID.randomUUID();
            newUser.setPassword(bCryptPasswordEncoder.encode(uuid.toString()));
            newUser.setPlanType(PlanType.BASIC);
            userRepository.save(newUser);
            UserDetails userDetails = loadUserByUsername(email);
            String jwtAccessToken = jwtTokenUtil.generateAccessToken(userDetails, newUser.getUserID());
            String jwtRefreshToken = jwtTokenUtil.generateRefreshToken(userDetails, newUser.getUserID());
            return new JwtResponse(jwtAccessToken, jwtRefreshToken);
        }
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByID(Long id) {
        // try-catch
        return userRepository.findUserByUserID(id).get();
    }

    public User getUserByUserUUID(String uuid) {
        // try-catch
        return userRepository.findUserByUserUUID(uuid).get();
    }

    public void deleteUserByID(Long id) {
        if (id == null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id");
        }
        userRepository.deleteById(id);
    }

    public JwtResponse correctLogin(String email, String rawPassword)
    {
        Optional<User> mailUser = userRepository.findUserByEmail(email);
        if (mailUser.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }

        if(!mailUser.get().isEnabled()) {
            mailConfirmationTokenService.sendMailConfirmationTo(email);
        }
        //System.out.println(mailUser.get().getPassword());
        //System.out.println(bCryptPasswordEncoder.encode(rawPassword));
        //System.out.println(this.bCryptPasswordEncoder.matches(rawPassword, mailUser.get().getPassword()));
        //System.out.println(rawPassword);
        if (bCryptPasswordEncoder.matches(rawPassword, mailUser.get().getPassword()) && mailUser.get().isEnabled())
        {
            UserDetails userDetails = loadUserByUsername(mailUser.get().getEmail());
            //System.out.println(userDetails);
            String jwtaccessToken = jwtTokenUtil.generateAccessToken(userDetails, mailUser.get().getUserID());
            String jwtrefreshToken = jwtTokenUtil.generateRefreshToken(userDetails, mailUser.get().getUserID());

            return new JwtResponse(jwtaccessToken, jwtrefreshToken);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "false");
    }

    public JwtResponse generateNewToken(String token)
    {
        if ((jwtTokenUtil.isValid(token)) && (jwtTokenUtil.getTokenTypeFromToken(token).equals("Refresh")))
        {
            Optional<User> mailUser = this.userRepository.findUserByUserID(jwtTokenUtil.getUserIDFromToken(token));
            if (mailUser.isPresent())
            {
                UserDetails userDetails = loadUserByUsername(mailUser.get().getEmail());
                String jwtaccessToken = jwtTokenUtil.generateAccessToken(userDetails, mailUser.get().getUserID());
                String jwtrefreshToken = jwtTokenUtil.generateRefreshToken(userDetails, mailUser.get().getUserID());
                return new JwtResponse(jwtaccessToken, jwtrefreshToken);
            }
            else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
    }

    @Transactional
    public User updateUserByID(Long id, String userName, String email, String phoneNumber, String firstName, String lastName, String password) {
        User thisUser = userRepository.findUserByUserID(id).get();
        Optional<User> mailUser = userRepository.findUserByEmail(email);
        if ((email!=null) && (mailUser.isPresent()) && (!mailUser.get().getUserID().equals(thisUser.getUserID())))
        {
            //BAD REQUEST da ton tai email
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already used by another user Email");
        }
        if ((email==null) || ((!EmailValidator.getInstance().isValid(email) || email.length() > 60)))
        {
            //khong phai email
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email");
        }
        Optional<User> phoneUser = userRepository.findUserByPhoneNumber(phoneNumber);
        if ((phoneNumber==null) || (!phoneNumber.matches("[0-9]+")))
        {
            //khong phai phone
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid phoneNumber");
        }
        if (phoneUser.isPresent() && !phoneUser.get().getUserID().equals(thisUser.getUserID()))
        {
            //BAD REQUEST da ton tai phone
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aldready used by another phoneNumber");
        }
        Optional<User> userNameUser = userRepository.findUserByUserName(userName);
        if ((userName!=null) && (userNameUser.isPresent()) && (!userNameUser.get().getUserID().equals(thisUser.getUserID())))
        {
            //BAD REQUEST da ton tai username
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Already used by another username");
        }
        if ((password==null) || (password.length() < 8))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is too short");
        }
        thisUser.setPassword(password);
        thisUser.setEmail(email);
        thisUser.setUserName(userName);
        thisUser.setLastName(lastName);
        thisUser.setFirstName(firstName);
        thisUser.setPhoneNumber(phoneNumber);
        userRepository.save(thisUser);
        return thisUser;
    }

    @Transactional
    public User updateUser(Long userID, User user) throws IllegalAccessException, InvocationTargetException {
        Optional<User> oldUserOptional = this.userRepository.findUserByUserID(userID);
        if (oldUserOptional.isPresent())
        {
            User oldUser = oldUserOptional.get();
            //These information can't be changed by update info request
            user.setUserID(userID);
            user.setEnabled(oldUser.getEnabled());
            user.setPlanType(oldUser.getPlanType());
            user.setEmail(oldUser.getEmail());
            user.setUserName(oldUser.getUserName());
            user.setPhoneNumber(oldUser.getPhoneNumber());
            user.setPassword(oldUser.getPassword());
            BeanUtilsBean notNull = new NullAwareBeanUtilsBean();
            notNull.copyProperties(oldUser, user);
            oldUser.setEnabled(Boolean.TRUE);
            this.userRepository.save(oldUser);
            return oldUser;
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Optional<User> mailUser = this.userRepository.findUserByEmail(email);
        
        if(!mailUser.isPresent()){
            throw new UsernameNotFoundException("Not found user with email: " + email);
        }

        mailUser.get().getRoles().forEach(role  -> 
            {authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(mailUser.get().getEmail(), 
                                                                      mailUser.get().getPassword(), authorities);
        
    }

    // public ResponseEntity<?> addRoleToUser(String userName, String roleName){
    //     Optional<User> user = userRepository.findUserByUserName(userName);
    //     Optional<Role> user = roleRepository.findUser
    // }
    // TUONG UNG VOI 4 REQUEST BEN USER CONTROLLER
    // LOGIC FLOW (IF - ELSE CAC THU SE NAM O DAY)
}
