package com.higroup.Buda.api.user.updateinfo;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.util.BeanUtils.NullAwareBeanUtilsBean;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UpdateUserInfoService {
    private final UserRepository userRepository;
    @Autowired
    public UpdateUserInfoService(UserRepository userRepository){
        this.userRepository = userRepository;
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
            user.setRoles(oldUser.getRoles());
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
}
